//package com.learn.testfilter.test;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import org.springframework.security.crypto.codec.Hex;
//
//public class HmacSha256EncoderDecoder {
//
//    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
//
//    public static String encode(String message, String key) throws Exception {
//        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256_ALGORITHM);
//        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
//        mac.init(signingKey);
//        byte[] rawHmac = mac.doFinal(message.getBytes());
//        return new String(Hex.encode(rawHmac));
//    }
//
//    public static boolean verify(String message, String key, String expectedHmac) throws Exception {
//        String actualHmac = encode(message, key);
//        return expectedHmac.equals(actualHmac);
//    }
//
//    public static void main(String[] args) throws Exception {
//        String message = "Hello, world!";
//        String key = "secret";
//
//        String expectedHmac = "62419bf2fe15b171049ba48b1d5d90d7420421e88b8d6b2e54d8b7a4974d9447";
//
//        boolean verified = HmacSha256EncoderDecoder.verify(message, key, expectedHmac);
//
//        System.out.println("Expected HMAC: " + expectedHmac);
//        System.out.println("Message verified: " + verified);
//
//        String result = SignatureUtil.encodeSignature("1", "2");
//        String myMessage = SignatureUtil.decodeSignature(result, "2");
//        System.out.println(myMessage.equals("1"));
//    }
//}
