package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class AppExchangeDataIngPara implements Serializable {
    public static int STATUS_ALL_VALID = 0;
    public static int STATUS_DISTANCE_INVALID = 1;
    public static int STATUS_GPS_SIGNAL_WEAK = 2;
    private static final long serialVersionUID = 1;
    public int calories;
    public int day;
    public int distance;
    public int duration;
    public int hour;
    public int minute;
    public int second;
    public int status;

    public String toString() {
        return "AppExchangeDataIngPara{day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", status=" + this.status + ", duration=" + this.duration + ", calories=" + this.calories + ", distance=" + this.distance + '}';
    }
}
