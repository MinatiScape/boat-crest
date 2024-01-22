package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class Menstrual implements Serializable {
    public static final int STATUS_OFF = 85;
    public static final int STATUS_ON = 170;
    public int last_menstrual_day;
    public int last_menstrual_month;
    public int last_menstrual_year;
    public int menstrual_cycle;
    public int menstrual_length;
    public int notify_flag;
    public int on_off = 85;
    public int ovulation_after_day;
    public int ovulation_before_day;
    public int ovulation_interval_day;

    public String toString() {
        return "Menstrual{on_off=" + this.on_off + ", menstrual_length=" + this.menstrual_length + ", menstrual_cycle=" + this.menstrual_cycle + ", last_menstrual_year=" + this.last_menstrual_year + ", last_menstrual_month=" + this.last_menstrual_month + ", last_menstrual_day=" + this.last_menstrual_day + ", ovulation_interval_day=" + this.ovulation_interval_day + ", ovulation_before_day=" + this.ovulation_before_day + ", ovulation_after_day=" + this.ovulation_after_day + ", notify_flag=" + this.notify_flag + '}';
    }
}
