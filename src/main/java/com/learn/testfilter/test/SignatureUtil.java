//package com.learn.testfilter.utils;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Base64;
//
//import static com.learn.testfilter.utils.constants.Constants.DATE_TIME_FORMATTER;
//import static com.learn.testfilter.utils.constants.Constants.HMAC_SHA256_ALGORITHM;
//
//public class SignatureUtil {
//
//
//
//    public static String encodeSignature(String url, LocalDateTime date, String key) throws NoSuchAlgorithmException, InvalidKeyException {
//        String data = url + date.format(DATE_TIME_FORMATTER);
//        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
//        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
//        mac.init(signingKey);
//        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
//        return Base64.getEncoder().encodeToString(rawHmac);
//    }
//
//
//}
