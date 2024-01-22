package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class AllHealthMonitorSwitch {
    public static final int SWITCH_OFF = 85;
    public static final int SWITCH_ON = 170;
    public int drinkwater_mode;
    public int guidance_notify_flag;
    public int handwashing_mode;
    public int heart_mode;
    public int heartmode_notify_flag;
    public int menstrual_mode;
    public int menstrual_notify_flag;
    public int noise_mode;
    public int pressure_mode;
    public int pressure_notify_flag;
    public int reminder_notify_flag;
    public int science_mode;
    public int spo2_mode;
    public int spo2_notify_flag;
    public int temperature_mode;
    public int walk_mode;

    public String toString() {
        return "AllHealthMonitorSwitch{heart_mode=" + this.heart_mode + ", pressure_mode=" + this.pressure_mode + ", spo2_mode=" + this.spo2_mode + ", science_mode=" + this.science_mode + ", temperature_mode=" + this.temperature_mode + ", noise_mode=" + this.noise_mode + ", menstrual_mode=" + this.menstrual_mode + ", walk_mode=" + this.walk_mode + ", handwashing_mode=" + this.handwashing_mode + ", drinkwater_mode=" + this.drinkwater_mode + ", heartmode_notify_flag=" + this.heartmode_notify_flag + ", pressure_notify_flag=" + this.pressure_notify_flag + ", spo2_notify_flag=" + this.spo2_notify_flag + ", menstrual_notify_flag=" + this.menstrual_notify_flag + ", guidance_notify_flag=" + this.guidance_notify_flag + ", reminder_notify_flag=" + this.reminder_notify_flag + '}';
    }
}
