package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class ScreenBrightness implements Serializable {
    public static final int AUTO_ADJUST_NIGHT_TYPE_CUSTOM_TIME_RANGE = 3;
    public static final int AUTO_ADJUST_NIGHT_TYPE_DEVICE_DEFAULT_TIME_RANGE = 2;
    public static final int AUTO_ADJUST_NIGHT_TYPE_INVALID = 0;
    public static final int AUTO_ADJUST_NIGHT_TYPE_OFF = 1;
    public static final int MODE_ADJUST_LIGHT = 1;
    public static final int MODE_ADJUST_OFF = 0;
    public static final int OPERA_TYPE_AUTO = 0;
    public static final int OPERA_TYPE_USER = 1;
    public int endHour;
    public int endMinute;
    public int nightLevel;
    public int showInterval;
    public int startHour;
    public int startMinute;
    @Deprecated
    public int opera = 1;
    public int level = 100;
    public int mode = 0;
    public int autoAdjustNight = 2;

    public String toString() {
        return "ScreenBrightness{opera=" + this.opera + ", level=" + this.level + ", mode=" + this.mode + ", autoAdjustNight=" + this.autoAdjustNight + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", nightLevel=" + this.nightLevel + ", showInterval=" + this.showInterval + '}';
    }
}
