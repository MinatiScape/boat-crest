package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class AppExchangeDataResumeDeviceReplyData implements Serializable {
    public static final int CODE_FAILED_DEVICE_NOT_IN_SPORT_MODE = 1;
    public static final int CODE_SUCCESS = 0;
    private static final long serialVersionUID = 1;
    public int err_code;

    public String toString() {
        return "AppExchangeDataResumeDeviceReplyData{err_code=" + this.err_code + '}';
    }
}
