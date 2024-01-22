package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class DeviceNoticeAppExchangeDataStopPara implements Serializable {
    public static final int IS_SAVE_NO = 0;
    public static final int IS_SAVE_YES = 1;
    private static final long serialVersionUID = 1;
    public int aerobic_mins;
    public int avg_hr_value;
    public int burn_fat_mins;
    public int calories;
    public int day;
    public int distance;
    public int hour;
    public int is_save;
    public int limit_mins;
    public int max_hr_value;
    public int minute;
    public int second;
    public int step;

    public String toString() {
        return "DeviceNoticeAppExchangeDataStopPara{day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", step=" + this.step + ", calories=" + this.calories + ", distance=" + this.distance + ", is_save=" + this.is_save + ", avg_hr_value=" + this.avg_hr_value + ", max_hr_value=" + this.max_hr_value + ", burn_fat_mins=" + this.burn_fat_mins + ", aerobic_mins=" + this.aerobic_mins + ", limit_mins=" + this.limit_mins + '}';
    }
}
