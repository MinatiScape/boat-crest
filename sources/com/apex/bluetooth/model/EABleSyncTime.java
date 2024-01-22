package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.TimeZone;
/* loaded from: classes.dex */
public class EABleSyncTime {
    public int day;
    public HourSystem e_hour_system;
    public SyncMode e_sync_mode;
    public TimeZone e_time_zone;
    public int hour;
    public int minute;
    public int month;
    public int second;
    public int time_zone_hour;
    public int time_zone_minute;
    public int year;

    /* loaded from: classes.dex */
    public enum HourSystem {
        hour_12(0),
        hour_24(1);
        
        public int value;

        HourSystem(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum SyncMode {
        normal(0),
        watch(1);
        
        public int value;

        SyncMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getDay() {
        return this.day;
    }

    public HourSystem getE_hour_system() {
        return this.e_hour_system;
    }

    public SyncMode getE_sync_mode() {
        return this.e_sync_mode;
    }

    public TimeZone getE_time_zone() {
        return this.e_time_zone;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getMonth() {
        return this.month;
    }

    public int getSecond() {
        return this.second;
    }

    public int getTime_zone_hour() {
        return this.time_zone_hour;
    }

    public int getTime_zone_minute() {
        return this.time_zone_minute;
    }

    public int getYear() {
        return this.year;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setE_hour_system(HourSystem hourSystem) {
        this.e_hour_system = hourSystem;
    }

    public void setE_sync_mode(SyncMode syncMode) {
        this.e_sync_mode = syncMode;
    }

    public void setE_time_zone(TimeZone timeZone) {
        this.e_time_zone = timeZone;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public void setTime_zone_hour(int i) {
        this.time_zone_hour = i;
    }

    public void setTime_zone_minute(int i) {
        this.time_zone_minute = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "EABleSyncTime{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", e_hour_system=" + this.e_hour_system + ", e_time_zone=" + this.e_time_zone + ", time_zone_hour=" + this.time_zone_hour + ", time_zone_minute=" + this.time_zone_minute + ", e_sync_mode=" + this.e_sync_mode + '}';
    }
}
