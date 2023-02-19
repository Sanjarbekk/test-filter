/*
package com.learn.testfilter.test;

import com.learn.testfilter.utils.SignatureUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class SignatureEncoder {
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");


    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String url = "https://example.com";
        LocalDateTime date = LocalDateTime.now(ZoneOffset.UTC);
        String key = "mySecretKey";

        String signature = SignatureUtil.encodeSignature(url, date, key);
        System.out.println("Signature: " + url);
        System.out.println("Signature: " + date);
        System.out.println("Signature: " + signature);
        System.out.println("Signature: " + SignatureUtil.decodeSignature(signature, key).getUrl());
        System.out.println("Signature: " + SignatureUtil.decodeSignature(signature, key).getDate());
    }
}
*/
