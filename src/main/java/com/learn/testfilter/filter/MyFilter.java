package com.learn.testfilter.filter;

import com.learn.testfilter.payload.request.SignatureData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import static com.learn.testfilter.utils.constants.Constants.*;

public class MyFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        LOGGER.info(REQUEST + PATH + BRACKET, request.getRequestURI());
        LOGGER.info(REQUEST + METHOD + BRACKET, request.getMethod());
        LOGGER.info(REQUEST + KEY + BRACKET, request.getHeader(KEY));
        LOGGER.info(REQUEST + ALGORITHM + BRACKET, request.getHeader(ALGORITHM));
        LOGGER.info(REQUEST + SIGNATURE + BRACKET, request.getHeader(SIGNATURE));

        if (checkUrlAndDate(request)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ERROR_MESSAGE);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkUrlAndDate(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        String key = request.getHeader(KEY);
        String signature = request.getHeader(SIGNATURE);

        SignatureData signatureData = decodeSignature(signature, key);
        return checkUrl(signatureData.getUrl(), requestPath) && checkDate(signatureData.getDate());
    }

    private boolean checkUrl(String signUrl, String requestPath) {
        return signUrl.equals(requestPath);
    }

    private boolean checkDate(LocalDateTime signDate) {
        long diffInMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), signDate);
        return Math.abs(diffInMinutes) <= 5;
    }

    public SignatureData decodeSignature(String encodedSignature, String key) {
        try {
            byte[] signature = Base64.getDecoder().decode(encodedSignature);
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(signature);
            String decodedSignature = new String(rawHmac, StandardCharsets.UTF_8);
            String url = decodedSignature.substring(0, decodedSignature.length() - DATE_FORMAT.length());
            String dateString = decodedSignature.substring(decodedSignature.length() - DATE_FORMAT.length());
            LocalDateTime date = LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
            return new SignatureData(url, date);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
