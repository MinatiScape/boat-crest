package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class HabitInfo {
    public List<BrowseInfo> bro_items;
    public int browse_count;
    public List<ImplementInfo> imp_items;
    public int implement_count;

    /* loaded from: classes11.dex */
    public static class BrowseInfo {
        public int count;
        public int day;
        public int evt;
        public int hour;
        public int min;
        public int month;
        public int sec;
        public int type;
        public int year;

        public String toString() {
            return "BrowseInfo{type=" + this.type + ", evt=" + this.evt + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", min=" + this.min + ", sec=" + this.sec + ", count=" + this.count + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class ImplementInfo {
        public int completion_rate;
        public int end_day;
        public int end_hour;
        public int end_min;
        public int end_month;
        public int end_sec;
        public int end_year;
        public int evt;
        public int start_day;
        public int start_hour;
        public int start_min;
        public int start_month;
        public int start_sec;
        public int start_year;
        public int type;

        public String toString() {
            return "ImplementInfo{type=" + this.type + ", evt=" + this.evt + ", start_year=" + this.start_year + ", start_month=" + this.start_month + ", start_day=" + this.start_day + ", start_hour=" + this.start_hour + ", start_min=" + this.start_min + ", start_sec=" + this.start_sec + ", end_year=" + this.end_year + ", end_month=" + this.end_month + ", end_day=" + this.end_day + ", end_hour=" + this.end_hour + ", end_min=" + this.end_min + ", end_sec=" + this.end_sec + ", completion_rate=" + this.completion_rate + '}';
        }
    }

    public String toString() {
        return "HabitInfo{browse_count=" + this.browse_count + ", implement_count=" + this.implement_count + ", bro_items=" + this.bro_items + ", imp_items=" + this.imp_items + '}';
    }
}
