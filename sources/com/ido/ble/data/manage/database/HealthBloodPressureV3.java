package com.ido.ble.data.manage.database;

import java.util.List;
/* loaded from: classes11.dex */
public class HealthBloodPressureV3 {
    public int day;
    public int item_count;
    public List<HealthBloodPressureItem> items;
    public int max_bp;
    public int month;
    public int sleep_avg_bp;
    public int start_time;
    public int year;

    /* loaded from: classes11.dex */
    public class HealthBloodPressureItem {
        public int dias_blood;
        public int offset;
        public int sys_blood;

        public HealthBloodPressureItem() {
        }

        public String toString() {
            return "HealthBloodPressureItem{offset=" + this.offset + ", sys_blood=" + this.sys_blood + ", dias_blood=" + this.dias_blood + '}';
        }
    }

    public String toString() {
        return "HealthBloodPressureV3{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", start_time=" + this.start_time + ", sleep_avg_bp=" + this.sleep_avg_bp + ", max_bp=" + this.max_bp + ", item_count=" + this.item_count + ", items=" + this.items + '}';
    }
}
