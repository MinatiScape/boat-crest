package com.google.iot.cbor;
/* loaded from: classes10.dex */
public final class CborTag {
    public static final int BASE64 = 34;
    public static final int BASE64URL = 33;
    public static final int BIGFLOAT = 5;
    public static final int BIGNUM_NEG = 3;
    public static final int BIGNUM_POS = 2;
    public static final int CBOR_DATA_ITEM = 24;
    public static final int EXPECTED_BASE16 = 23;
    public static final int EXPECTED_BASE64 = 22;
    public static final int FRACTION = 4;
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    public static final int REGEX = 35;
    public static final int SELF_DESCRIBE_CBOR = 55799;
    public static final int TIMESTAMP_UNIX = 1;
    public static final int TIME_DATE_STRING = 0;
    public static final int UNTAGGED = -1;
    public static final int URI = 32;

    public static boolean isValid(long j) {
        return j >= -1 && j <= 2147483647L;
    }
}
