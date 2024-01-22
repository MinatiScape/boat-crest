package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class AppExchangeDataStartDeviceReplyData implements Serializable {
    public static final int CODE_FAILED_DEVICE_ALREADY_IN_SPORT_MODE = 1;
    public static final int CODE_FAILED_DEVICE_CHARGING = 3;
    public static final int CODE_FAILED_DEVICE_IN_CALLING_STATE = 5;
    public static final int CODE_FAILED_DEVICE_IN_VOICE_STATE = 4;
    public static final int CODE_FAILED_DEVICE_LOW_BATTARY = 2;
    public static final int CODE_SUCCESS = 0;
    private static final long serialVersionUID = 1;
    public int ret_code;

    public String toString() {
        return "AppExchangeDataStartDeviceReplyData{ret_code=" + this.ret_code + '}';
    }
}
