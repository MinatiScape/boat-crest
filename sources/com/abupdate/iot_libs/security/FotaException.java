package com.abupdate.iot_libs.security;
/* loaded from: classes.dex */
public class FotaException extends Exception {
    public static final int REASON_CODE_CLIENT_ERROR = 0;
    public static final int REASON_CODE_DEVICE_PARAMETERS = 202;
    public static final int REASON_CODE_MANIFEST_META_DATA_ERROR = 203;
    public static final int REASON_CODE_MANIFEST_NOT_REGISTER = 201;
    public static final int REASON_ERROR_LOG_NOT_EXIST = 204;
    private Throwable cause;
    private int reasonCode;

    public FotaException(int i) {
        this.reasonCode = i;
    }

    private String getErrorMessage(int i) {
        switch (i) {
            case 201:
                return "AndroidManifest element and permissions is lack";
            case 202:
                return "Fota [DeviceInfo] initPackagePath device parameters exception";
            case 203:
                return "AndroidManifest meta-data is null or should start with string/";
            case 204:
                return "error log file not exist";
            default:
                return "UnExpect Exception";
        }
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return getErrorMessage(this.reasonCode);
    }

    public int getReasonCode() {
        return this.reasonCode;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String str = getMessage() + " (" + this.reasonCode + ")";
        if (this.cause != null) {
            return str + " - " + this.cause.toString();
        }
        return str;
    }

    public FotaException(Throwable th) {
        this.reasonCode = 0;
        this.cause = th;
    }

    public FotaException(int i, Throwable th) {
        this.reasonCode = i;
        this.cause = th;
    }
}
