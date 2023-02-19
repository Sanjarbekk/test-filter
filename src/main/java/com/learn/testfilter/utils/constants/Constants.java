package com.learn.testfilter.utils.constants;

import java.time.format.DateTimeFormatter;

public final class Constants {
    public static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static final String REQUEST = "Request";
    public static final String PATH = "Path";
    public static final String METHOD = "Method";
    public static final String KEY = "Key";
    public static final String ALGORITHM = "Algorithm";
    public static final String SIGNATURE = "Signature";
    public static final String BRACKET = ": {}";

    public static final String ERROR_MESSAGE = "Some error message";

}
