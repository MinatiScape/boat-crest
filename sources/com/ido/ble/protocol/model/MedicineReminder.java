package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class MedicineReminder implements Serializable {
    public int do_not_disturb_end_hour;
    public int do_not_disturb_end_minute;
    public int do_not_disturb_on_off;
    public int do_not_disturb_start_hour;
    public int do_not_disturb_start_minute;
    public int end_hour;
    public int end_minute;
    public int interval;
    public int on_off;
    public int repeat;
    public int start_hour;
    public int start_minute;
    public int taking_medicine_id;

    public String toString() {
        return "MedicineReminder{taking_medicine_id=" + this.taking_medicine_id + ", on_off=" + this.on_off + ", start_hour=" + this.start_hour + ", start_minute=" + this.start_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + ", do_not_disturb_on_off=" + this.do_not_disturb_on_off + ", do_not_disturb_start_hour=" + this.do_not_disturb_start_hour + ", do_not_disturb_start_minute=" + this.do_not_disturb_start_minute + ", do_not_disturb_end_hour=" + this.do_not_disturb_end_hour + ", do_not_disturb_end_minute=" + this.do_not_disturb_end_minute + ", repeat=" + this.repeat + ", interval=" + this.interval + '}';
    }
}
