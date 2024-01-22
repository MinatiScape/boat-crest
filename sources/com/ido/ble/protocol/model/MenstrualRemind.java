package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class MenstrualRemind implements Serializable {
    public int hour;
    public int menstrual_day_end_remind;
    public int minute;
    public int ovulation_day;
    public int pregnancy_day_before_remind;
    public int pregnancy_day_end_remind;
    public int start_day;

    public String toString() {
        return "MenstrualRemind{start_day=" + this.start_day + ", ovulation_day=" + this.ovulation_day + ", hour=" + this.hour + ", minute=" + this.minute + ", pregnancy_day_before_remind=" + this.pregnancy_day_before_remind + ", pregnancy_day_end_remind=" + this.pregnancy_day_end_remind + ", menstrual_day_end_remind=" + this.menstrual_day_end_remind + '}';
    }
}
