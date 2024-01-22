package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.Date;
/* loaded from: classes12.dex */
public class TGBatteryInfo {
    public static final int STATUS_CHARGING = 1;
    public static final int STATUS_CHARGING_COMPLETE = 2;
    public static final int STATUS_LOW_POWER = 3;
    public static final int STATUS_NORMAL = 0;
    public static final int TYPE_BUTTON_BATTERY = 1;
    public static final int TYPE_LITHIUM_BATTERY = 0;
    private boolean full;
    @Nullable
    private Date lastCharging;
    private int level;
    private int status;
    private int type;
    private int voltage;

    @Nullable
    public Date getLastCharging() {
        return this.lastCharging;
    }

    public int getLevel() {
        return this.level;
    }

    public int getStatus() {
        return this.status;
    }

    public int getType() {
        return this.type;
    }

    public int getVoltage() {
        return this.voltage;
    }

    public boolean isFull() {
        return this.full;
    }

    public void setFull(boolean z) {
        this.full = z;
    }

    public void setLastCharging(@Nullable Date date) {
        this.lastCharging = date;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setVoltage(int i) {
        this.voltage = i;
    }

    public String toString() {
        return "BatteryInfo{type=" + this.type + ", voltage=" + this.voltage + ", status=" + this.status + ", level=" + this.level + ", lastCharging=" + this.lastCharging + '}';
    }
}
