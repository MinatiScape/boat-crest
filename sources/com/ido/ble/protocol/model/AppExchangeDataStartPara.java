package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class AppExchangeDataStartPara extends SportType implements Serializable {
    public static int FORCE_START_INVALID = 0;
    public static int FORCE_START_VALID = 1;
    public static int TARGET_TYPE_CALORIES = 3;
    public static int TARGET_TYPE_DISTANCE = 2;
    public static int TARGET_TYPE_DURATIONS = 4;
    public static int TARGET_TYPE_REPEAT_COUNT = 1;
    public static int TARGET_TYPE_UNKNOW = 0;
    private static final long serialVersionUID = 1;
    public int avg_week_activity_time;
    public int day;
    public int force_start;
    public int hour;
    public int minute;
    public int recover_time;
    public int second;
    public int sportType;
    public int target_type;
    public int target_value;
    public int vo2max;

    public String toString() {
        return "AppExchangeDataStartPara{day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", sportType=" + this.sportType + ", target_type=" + this.target_type + ", target_value=" + this.target_value + ", force_start=" + this.force_start + ", vo2max=" + this.vo2max + ", recover_time=" + this.recover_time + ", avg_week_activity_time=" + this.avg_week_activity_time + '}';
    }
}
