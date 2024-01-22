package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class AppExchangeDataStopPara extends SportType implements Serializable {
    public static final int IS_SAVE_NO = 0;
    public static final int IS_SAVE_YES = 1;
    private static final long serialVersionUID = 1;
    public int calories;
    public int day;
    public int distance;
    public int durations;
    public int hour;
    public int is_save;
    public int minute;
    public int second;
    public int sport_type;

    public String toString() {
        return "AppExchangeDataStopPara{day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", durations=" + this.durations + ", calories=" + this.calories + ", distance=" + this.distance + ", sport_type=" + this.sport_type + ", is_save=" + this.is_save + '}';
    }
}
