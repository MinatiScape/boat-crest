package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class VoiceControlReminder {
    public List<V3ReminderItem> item;

    /* loaded from: classes11.dex */
    public static class V3ReminderItem {
        public static final int STATUS_OFF = 170;
        public static final int STATUS_ON = 85;
        public int day;
        public int hour;
        public int minute;
        public int month;
        public int reminder_id;
        public String reminder_string;
        public int status;
        public int year;

        public String toString() {
            return "V3ReminderItem{reminder_id=" + this.reminder_id + ", status=" + this.status + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", reminder_string='" + this.reminder_string + "'}";
        }
    }

    public String toString() {
        return "VoiceControlAlarm{item=" + this.item + '}';
    }
}
