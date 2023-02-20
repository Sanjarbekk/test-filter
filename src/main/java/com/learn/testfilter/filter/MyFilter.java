package com.learn.testfilter.filter;

import com.learn.testfilter.service.KeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static com.learn.testfilter.utils.constants.Constants.*;

@Configuration
public class MyFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    private static KeyService keyService = null;

    public MyFilter(KeyService keyService) {
        this.keyService = keyService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        LOGGER.info(REQUEST + PATH + BRACKET, request.getRequestURI());
        LOGGER.info(REQUEST + METHOD + BRACKET, request.getMethod());
        LOGGER.info(REQUEST + SIGNATURE + BRACKET, request.getHeader(SIGNATURE));
        LOGGER.info(REQUEST + DATE + BRACKET, request.getHeader(DATE));
        LOGGER.info(REQUEST + APP_ID + BRACKET, request.getHeader(APP_ID));

        try {
            if (!checkUrlAndDate(request)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkUrlAndDate(HttpServletRequest request) throws Exception {
        String date = request.getHeader(DATE);
        String key = keyService.getById(request.getHeader(APP_ID));
        String createSignature = encode(request.getRequestURI() + date, key);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return createSignature.equals(request.getHeader(SIGNATURE)) && checkDate(dateTime);
    }

    private boolean checkDate(LocalDateTime signDate) {
        long diffInMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), signDate);
        return Math.abs(diffInMinutes) <= 5;
    }

    public String encode(String message, String key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(message.getBytes());
        return new String(Hex.encode(rawHmac));
    }
}
