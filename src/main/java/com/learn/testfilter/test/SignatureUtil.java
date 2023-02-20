//package com.learn.testfilter.test;
//
//import org.apache.tomcat.util.codec.binary.StringUtils;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//
///*import static com.learn.testfilter.utils.constants.Constants.HMAC_SHA256_ALGORITHM;
//
//public class SignatureUtil {
//
//    public static String encodeSignature(String url, String key) throws NoSuchAlgorithmException, InvalidKeyException {
//        Mac mac = createMac(key);
//
//        byte[] rawHmac = mac.doFinal(url.getBytes(StandardCharsets.UTF_8));
//        return Base64.getEncoder().encodeToString(rawHmac);
//    }
//
//    public static String decodeSignature(String encodedSignature, String key) throws NoSuchAlgorithmException, InvalidKeyException {
//        Mac mac = createMac(key);
//        byte[] signature = Base64.getDecoder().decode(encodedSignature);
//
//        byte[] rawHmac = mac.doFinal(signature);
//        return new String(rawHmac, StandardCharsets.UTF_8);
//    }
//
//    private static Mac createMac(String key) throws NoSuchAlgorithmException, InvalidKeyException{
//        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
//        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
//        mac.init(signingKey);
//        return mac;
//    }
//}*/
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//
//public class SignatureUtil {
//
//    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
//
//    public static String encodeSignature(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
//        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256_ALGORITHM);
//        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
//        mac.init(signingKey);
//        byte[] rawHmac = mac.doFinal(data.getBytes());
//        return Base64.getEncoder().encodeToString(rawHmac);
//    }
//
//    public static String decodeSignature(String signature, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
//        byte[] decodedSignature = Base64.getDecoder().decode(signature);
//        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256_ALGORITHM);
//        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
//        mac.init(signingKey);
//        byte[] decryptedByteData = mac.doFinal(decodedSignature);
//        return StringUtils.newStringUsAscii(decryptedByteData);
//    }
//}
//
//
