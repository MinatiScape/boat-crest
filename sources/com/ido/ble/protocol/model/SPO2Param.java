package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class SPO2Param {
    public static final int STATE_OFF = 85;
    public static final int STATE_ON = 170;
    public int endHour;
    public int endMinute;
    public int lowSpo2OnOff;
    public int lowSpo2OnValue;
    public int notifyFlag;
    public int onOff;
    public int startHour;
    public int startMinute;

    public String toString() {
        return "SPO2Param{onOff=" + this.onOff + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", lowSpo2OnOff=" + this.lowSpo2OnOff + ", lowSpo2OnValue=" + this.lowSpo2OnValue + ", notifyFlag=" + this.notifyFlag + '}';
    }
}
