package com.veryfit.multi.nativeprotocol;
/* loaded from: classes12.dex */
public enum a {
    SUCCESS,
    RESERVE1,
    RESERVE2,
    RESERVE3,
    ERROR_NO_MEM,
    ERROR_NOT_FIND,
    ERROR_NOT_SUPPORTED,
    ERROR_INVALID_PARAM,
    ERROR_INVALID_STATE,
    ERROR_INVALID_LENGTH,
    ERROR_INVALID_FLAGS,
    ERROR_INVALID_DATA,
    ERROR_DATA_SIZE,
    ERROR_TIMEOUT,
    ERROR_NULL,
    ERROR_FORBIDDEN,
    RESERVE16,
    ERROR_BUSY,
    ERROR_JNI;

    public static a a(int i) {
        return (i < 0 || i >= values().length) ? values()[5] : values()[i];
    }
}
