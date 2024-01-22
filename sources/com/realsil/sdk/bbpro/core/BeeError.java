package com.realsil.sdk.bbpro.core;

import com.coveiot.coveaccess.constants.ErrorConstants;
import java.util.Locale;
/* loaded from: classes12.dex */
public class BeeError {
    public static final int ERROR = 1;
    public static final int ERR_DEVICE_CONNECT_FAILED = 33;
    public static final int ERR_DEVICE_DISCONNECTED = 32;
    public static final int ERR_PARAMETER_INVALID = 48;
    public static final int ERR_SERVICE_NOT_INITIALIZED = 16;
    public static final int ERR_TRANSPORT_RETRAINS_EXCEED_MAX_TIMES = 64;
    public static final int SUCCESS = 0;
    public int code;
    public String message;

    public BeeError(int i) {
        this.code = i;
        this.message = parseError(i);
    }

    public static String parseError(int i) {
        return i != 0 ? i != 1 ? i != 16 ? i != 48 ? i != 32 ? i != 33 ? "Unknown" : "Connect device failed" : "Device disconnected" : "Parameter invalid" : "Service not initialized" : ErrorConstants.GENERIC_ERROR : "Success";
    }

    public String toString() {
        return String.format(Locale.US, "%d--%s", Integer.valueOf(this.code), this.message);
    }

    public BeeError(int i, String str) {
        this.code = i;
        this.message = str;
    }
}
