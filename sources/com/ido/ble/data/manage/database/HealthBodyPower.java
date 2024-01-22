package com.ido.ble.data.manage.database;

import java.util.List;
/* loaded from: classes11.dex */
public class HealthBodyPower {
    public int data_size;
    public int day;
    public List<BodyPowerItem> items;
    public int month;
    public int start_time;
    public int year;

    /* loaded from: classes11.dex */
    public static class BodyPowerItem {
        public int diff_value;
        public int offset;
        public int type;
        public int value;

        public String toString() {
            return "BodyPowerItem{offset=" + this.offset + ", type=" + this.type + ", value=" + this.value + ", diff_value=" + this.diff_value + '}';
        }
    }

    public String toString() {
        return "HealthBodyPower{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", start_time=" + this.start_time + ", data_size=" + this.data_size + ", items=" + this.items + '}';
    }
}
