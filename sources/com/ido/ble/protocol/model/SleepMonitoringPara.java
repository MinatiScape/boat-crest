package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class SleepMonitoringPara implements Serializable {
    public static final int MODE_SLEEP_NORMAL = 85;
    public static final int MODE_SLEEP_SCIENTIFIC = 170;
    private static final long serialVersionUID = 1;
    public int mode;
    public int startHour = 0;
    public int startMinute = 0;
    public int endHour = 23;
    public int endMinute = 59;

    public String toString() {
        return "SleepMonitoringPara{mode=" + this.mode + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + '}';
    }
}
