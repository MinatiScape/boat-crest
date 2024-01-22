package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class NightTemperatureMonitoringPara implements Serializable {
    public static final int SWITCH_OFF = 85;
    public static final int SWITCH_ON = 170;
    public static final int UNIT_C = 1;
    public static final int UNIT_F = 16;
    private static final long serialVersionUID = 1;
    public int endHour;
    public int endMinute;
    public int mode;
    public int startHour;
    public int startMinute;
    public int unit;

    public String toString() {
        return "NightTemperatureMonitoringPara{mode=" + this.mode + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", unit=" + this.unit + '}';
    }
}
