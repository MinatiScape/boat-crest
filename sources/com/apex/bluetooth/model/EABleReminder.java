package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.CommonAction;
import java.util.List;
/* loaded from: classes.dex */
public class EABleReminder {
    public ReminderOps e_ops;
    public ReminderType e_type;
    public int id;
    public List<EABleReminderItem> s_index;

    /* loaded from: classes.dex */
    public static class EABleReminderItem {
        public String content;
        public int day;
        public CommonAction e_action;
        public ReminderType e_type;
        public int hour;
        public int id;
        public int minute;
        public int month;
        public int sec_sw;
        public int sleep_duration;
        public int sw;
        public int week_cycle_bit;
        public int year;

        public String getContent() {
            return this.content;
        }

        public int getDay() {
            return this.day;
        }

        public CommonAction getE_action() {
            return this.e_action;
        }

        public ReminderType getE_type() {
            return this.e_type;
        }

        public int getHour() {
            return this.hour;
        }

        public int getId() {
            return this.id;
        }

        public int getMinute() {
            return this.minute;
        }

        public int getMonth() {
            return this.month;
        }

        public int getSec_sw() {
            return this.sec_sw;
        }

        public int getSleep_duration() {
            return this.sleep_duration;
        }

        public int getSw() {
            return this.sw;
        }

        public int getWeek_cycle_bit() {
            return this.week_cycle_bit;
        }

        public int getYear() {
            return this.year;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setDay(int i) {
            this.day = i;
        }

        public void setE_action(CommonAction commonAction) {
            this.e_action = commonAction;
        }

        public void setE_type(ReminderType reminderType) {
            this.e_type = reminderType;
        }

        public void setHour(int i) {
            this.hour = i;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setMinute(int i) {
            this.minute = i;
        }

        public void setMonth(int i) {
            this.month = i;
        }

        public void setSec_sw(int i) {
            this.sec_sw = i;
        }

        public void setSleep_duration(int i) {
            this.sleep_duration = i;
        }

        public void setSw(int i) {
            this.sw = i;
        }

        public void setWeek_cycle_bit(int i) {
            this.week_cycle_bit = i;
        }

        public void setYear(int i) {
            this.year = i;
        }

        public String toString() {
            return "EABleReminderItem{e_type=" + this.e_type + ", id=" + this.id + ", hour=" + this.hour + ", minute=" + this.minute + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", week_cycle_bit=" + this.week_cycle_bit + ", sw=" + this.sw + ", sec_sw=" + this.sec_sw + ", sleep_duration=" + this.sleep_duration + ", e_action=" + this.e_action + ", content='" + this.content + "'}";
        }
    }

    /* loaded from: classes.dex */
    public enum ReminderOps {
        add(0),
        edit(1),
        del(2),
        del_remind(3),
        del_alarm(4),
        del_remind_alarm(5),
        replace_type(6),
        all_in(7),
        unknown(8);
        
        public int value;

        ReminderOps(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum ReminderType {
        alarm(0),
        sleep(1),
        sport(2),
        drink(3),
        medicine(4),
        meeting(5),
        user(6);
        
        public int value;

        ReminderType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public ReminderOps getE_ops() {
        return this.e_ops;
    }

    public ReminderType getE_type() {
        return this.e_type;
    }

    public int getId() {
        return this.id;
    }

    public List<EABleReminderItem> getS_index() {
        return this.s_index;
    }

    public void setE_ops(ReminderOps reminderOps) {
        this.e_ops = reminderOps;
    }

    public void setE_type(ReminderType reminderType) {
        this.e_type = reminderType;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setS_index(List<EABleReminderItem> list) {
        this.s_index = list;
    }

    public String toString() {
        return "EABleReminder{e_ops=" + this.e_ops + ", id=" + this.id + ", s_index=" + this.s_index + '}';
    }
}
