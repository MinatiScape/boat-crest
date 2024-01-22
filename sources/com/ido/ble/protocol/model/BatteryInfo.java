package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class BatteryInfo implements Serializable {
    public static final int BATTERY_STATE_CHARGING = 1;
    public static final int BATTERY_STATE_ENERGY_FULL = 2;
    public static final int BATTERY_STATE_ENERGY_LOW = 3;
    public static final int BATTERY_STATE_NORMAL = 0;
    private static final long serialVersionUID = 1;
    public int lastChargingDay;
    public int lastChargingHour;
    public int lastChargingMinute;
    public int lastChargingMonth;
    public int lastChargingSecond;
    public int lastChargingYear;
    public int level;
    public int mode;
    public int status;
    public int voltage;

    public String toString() {
        return "BatteryInfo{voltage=" + this.voltage + ", status=" + this.status + ", level=" + this.level + ", lastChargingYear=" + this.lastChargingYear + ", lastChargingMonth=" + this.lastChargingMonth + ", lastChargingDay=" + this.lastChargingDay + ", lastChargingHour=" + this.lastChargingHour + ", lastChargingMinute=" + this.lastChargingMinute + ", lastChargingSecond=" + this.lastChargingSecond + ", mode=" + this.mode + '}';
    }
}
