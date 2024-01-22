package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class MenstruationHistoricalData {
    public int avg_cycle_day;
    public int avg_menstrual_day;
    public List<MenstruationItem> items;
    public int items_len;
    public int version;

    /* loaded from: classes11.dex */
    public static class MenstruationItem {
        public int cycle_day;
        public int day;
        public int menstrual_day;
        public int mon;
        public int year;

        public String toString() {
            return "MenstruationItem{year=" + this.year + ", mon=" + this.mon + ", day=" + this.day + ", menstrual_day=" + this.menstrual_day + ", cycle_day=" + this.cycle_day + '}';
        }
    }

    public String toString() {
        return "MenstruationHistoricalData{version=" + this.version + ", avg_menstrual_day=" + this.avg_menstrual_day + ", avg_cycle_day=" + this.avg_cycle_day + ", items_len=" + this.items_len + ", items=" + this.items + '}';
    }
}
