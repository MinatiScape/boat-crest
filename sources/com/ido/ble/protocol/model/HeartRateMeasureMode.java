package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class HeartRateMeasureMode implements Serializable {
    public static final int MODE_AUTO = 136;
    public static final int MODE_MANUAL = 170;
    public static final int MODE_OFF = 85;
    public static final int MODE_PERSISTENT = 153;
    public static final int TIME_RANGE_OFF = 0;
    public static final int TIME_RANGE_ON = 1;
    private static final long serialVersionUID = 1;
    public int measurementInterval;
    public int mode;
    public int startHour;
    public int startMinute;
    public int hasTimeRange = 1;
    public int endHour = 23;
    public int endMinute = 59;

    public String toString() {
        return "HeartRateMeasureMode{mode=" + this.mode + ", hasTimeRange=" + this.hasTimeRange + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", measurementInterval=" + this.measurementInterval + '}';
    }
}
