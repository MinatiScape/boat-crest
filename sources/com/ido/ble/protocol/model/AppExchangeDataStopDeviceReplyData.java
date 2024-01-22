package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class AppExchangeDataStopDeviceReplyData implements Serializable {
    public static final int CODE_FAILED_DEVICE_NOT_IN_SPORT_MODE = 1;
    public static final int CODE_SUCCESS = 0;
    private static final long serialVersionUID = 1;
    public int aerobic_mins;
    public int avg_hr_value;
    public int burn_fat_mins;
    public int calories;
    public int distance;
    public int errCode;
    public int limit_mins;
    public int max_hr_value;
    public int step;

    public String toString() {
        return "AppExchangeDataStopDeviceReplyData{errCode=" + this.errCode + ", step=" + this.step + ", calories=" + this.calories + ", distance=" + this.distance + ", avg_hr_value=" + this.avg_hr_value + ", max_hr_value=" + this.max_hr_value + ", burn_fat_mins=" + this.burn_fat_mins + ", aerobic_mins=" + this.aerobic_mins + ", limit_mins=" + this.limit_mins + '}';
    }
}
