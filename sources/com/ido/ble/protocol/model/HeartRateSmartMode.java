package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class HeartRateSmartMode implements Serializable {
    public static final int SWITCH_OFF = 85;
    public static final int SWITCH_ON = 170;
    private static final long serialVersionUID = 1;
    public int end_hour;
    public int end_minute;
    public int high_heart_mode;
    public int high_heart_value;
    public int low_heart_mode;
    public int low_heart_value;
    public int mode;
    public int notify_flag;
    public int start_hour;
    public int start_minute;

    public String toString() {
        return "HeartRateSmartMode{mode=" + this.mode + ", notify_flag=" + this.notify_flag + ", high_heart_mode=" + this.high_heart_mode + ", low_heart_mode=" + this.low_heart_mode + ", high_heart_value=" + this.high_heart_value + ", low_heart_value=" + this.low_heart_value + ", start_hour=" + this.start_hour + ", start_minute=" + this.start_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + '}';
    }
}
