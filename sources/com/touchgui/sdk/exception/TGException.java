package com.touchgui.sdk.exception;

import com.touchgui.sdk.TGErrorCode;
import java.util.Locale;
/* loaded from: classes12.dex */
public class TGException extends Exception {
    private static final String[] messages = {"Send data timeout", "receive data timeout"};
    private final int code;
    private final int reason;

    public TGException(String str, int i) {
        super(str);
        this.code = i;
        this.reason = 0;
    }

    public static TGException authFail() {
        return new TGException("Failed to auth.", TGErrorCode.ERROR_AUTH_FAIL);
    }

    public static TGException canceled() {
        return new TGException("Command cancelled", TGErrorCode.ERROR_COMMAND_CANCELED);
    }

    public static TGException noSendData() {
        return new TGException("Send data is empty.", TGErrorCode.ERROR_SEND_DATA_EMPTY);
    }

    public static TGException notConnected() {
        return new TGException("The device is not connected", TGErrorCode.ERROR_NOT_CONNECTED);
    }

    public static TGException notReady() {
        return new TGException("The device is connected, but onReady is not called.", TGErrorCode.ERROR_NOT_READY);
    }

    public static TGException notResponse() {
        return new TGException("Device is busy, please try again later.", TGErrorCode.ERROR_DEVICE_NOT_RESPONSE);
    }

    public static TGException notSupport(String str) {
        return new TGException(str, TGErrorCode.ERROR_FUNC_NOT_SUPPORTED);
    }

    public static Throwable otaEndError(int i) {
        return new TGException(String.format(Locale.getDefault(), "Failed to end file transfer. (%d)", Integer.valueOf(i)), i + 30000);
    }

    public static Throwable otaFileError(int i) {
        return new TGException(String.format(Locale.getDefault(), "Failed to transfer file. (%d)", Integer.valueOf(i)), i + 30000);
    }

    public static Throwable otaRetryError(int i) {
        return new TGException(String.format(Locale.getDefault(), "Failed to retry file transfer. (%d)", Integer.valueOf(i)), i + 30000);
    }

    public static Throwable otaSetTotalError(int i) {
        return new TGException(String.format(Locale.getDefault(), "Failed to set total file size. (%d)", Integer.valueOf(i)), i + 30000);
    }

    public static Throwable otaStartError(int i) {
        return new TGException(String.format(Locale.getDefault(), "Failed to start file transfer. (%d)", Integer.valueOf(i)), i + 30000);
    }

    public static TGException sendDataFail() {
        return new TGException("Failed to send data.", TGErrorCode.ERROR_SEND_DATA_FAILURE);
    }

    public static TGException timeout(int i) {
        return new TGException(messages[i], TGErrorCode.ERROR_COMMAND_TIMEOUT);
    }

    public int getCode() {
        return this.code;
    }

    public int getReason() {
        return this.reason;
    }

    public TGException(String str, int i, int i2) {
        super(str);
        this.code = i;
        this.reason = i2;
    }
}
