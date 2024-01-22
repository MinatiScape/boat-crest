package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class VoiceControlAlarm {
    public List<AlarmItem> item;

    /* loaded from: classes11.dex */
    public static class AlarmItem {
        public static final int STATUS_OFF = 170;
        public static final int STATUS_ON = 85;
        public int alarm_id;
        public int day;
        public int hour;
        public int minute;
        public int month;
        public int status;
        public int year;

        public String toString() {
            return "AlarmItem{alarm_id=" + this.alarm_id + ", status=" + this.status + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + '}';
        }
    }

    public String toString() {
        return "VoiceControlAlarm{item=" + this.item + '}';
    }
}
