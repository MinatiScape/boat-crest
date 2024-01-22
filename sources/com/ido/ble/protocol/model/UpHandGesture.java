package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class UpHandGesture implements Serializable {
    public static final int STATE_OFF = 85;
    public static final int STATE_ON = 170;
    public static final int TIME_RANGE_OFF = 0;
    public static final int TIME_RANGE_ON = 1;
    private static final long serialVersionUID = 1;
    public int onOff;
    public int showSecond = 5;
    public int hasTimeRange = 1;
    public int startHour = 0;
    public int startMinute = 0;
    public int endHour = 23;
    public int endMinute = 59;

    public String toString() {
        return "UpHandGesture{onOff=" + this.onOff + ", showSecond=" + this.showSecond + ", hasTimeRange=" + this.hasTimeRange + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + '}';
    }
}
