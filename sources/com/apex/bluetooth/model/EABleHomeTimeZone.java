package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.TimeZone;
import java.util.List;
/* loaded from: classes.dex */
public class EABleHomeTimeZone {
    public List<EABleHomeZone> s_home;

    /* loaded from: classes.dex */
    public static class EABleHomeZone {
        public TimeZone e_time_zone;
        public String place;
        public int time_zone_hour;
        public int time_zone_minute;

        public TimeZone getE_time_zone() {
            return this.e_time_zone;
        }

        public String getPlace() {
            return this.place;
        }

        public int getTime_zone_hour() {
            return this.time_zone_hour;
        }

        public int getTime_zone_minute() {
            return this.time_zone_minute;
        }

        public void setE_time_zone(TimeZone timeZone) {
            this.e_time_zone = timeZone;
        }

        public void setPlace(String str) {
            this.place = str;
        }

        public void setTime_zone_hour(int i) {
            this.time_zone_hour = i;
        }

        public void setTime_zone_minute(int i) {
            this.time_zone_minute = i;
        }
    }

    public List<EABleHomeZone> getS_home() {
        return this.s_home;
    }

    public void setS_home(List<EABleHomeZone> list) {
        this.s_home = list;
    }
}
