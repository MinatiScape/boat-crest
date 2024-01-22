package com.coveiot.coveaccess.userdevicesetting;

import java.util.List;
/* loaded from: classes8.dex */
public class SaveCustomRemindersSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public CustomReminders f6881a;

    /* loaded from: classes8.dex */
    public static class CustomReminders {

        /* renamed from: a  reason: collision with root package name */
        public String f6882a;
        public List<CustomRemindersListItem> b;

        /* loaded from: classes8.dex */
        public static class CustomRemindersListItem {

            /* renamed from: a  reason: collision with root package name */
            public String f6883a;
            public String b;
            public String c;
            public Integer d;
            public boolean e;
            public String f;
            public String g;
            public String h;
            public String i;
            public String j;
            public String k;
            public List<String> l;
            public String m;

            public String getEndDate() {
                return this.b;
            }

            public String getEndTime() {
                return this.i;
            }

            public String getFrequency() {
                return this.m;
            }

            public String getInterval() {
                return this.g;
            }

            public String getLabel() {
                return this.h;
            }

            public List<String> getRemindAt() {
                return this.l;
            }

            public Integer getRemindBefore() {
                return this.d;
            }

            public String getReminderId() {
                return this.f6883a;
            }

            public String getStartDate() {
                return this.k;
            }

            public String getStartTime() {
                return this.f;
            }

            public String getType() {
                return this.j;
            }

            public String getWeekDays() {
                return this.c;
            }

            public boolean isActive() {
                return this.e;
            }

            public void setActive(boolean z) {
                this.e = z;
            }

            public void setEndDate(String str) {
                this.b = str;
            }

            public void setEndTime(String str) {
                this.i = str;
            }

            public void setFrequency(String str) {
                this.m = str;
            }

            public void setInterval(String str) {
                this.g = str;
            }

            public void setLabel(String str) {
                this.h = str;
            }

            public void setRemindAt(List<String> list) {
                this.l = list;
            }

            public void setRemindBefore(Integer num) {
                this.d = num;
            }

            public void setReminderId(String str) {
                this.f6883a = str;
            }

            public void setStartDate(String str) {
                this.k = str;
            }

            public void setStartTime(String str) {
                this.f = str;
            }

            public void setType(String str) {
                this.j = str;
            }

            public void setWeekDays(String str) {
                this.c = str;
            }
        }

        public List<CustomRemindersListItem> getCustomReminderList() {
            return this.b;
        }

        public String getTzOffset() {
            return this.f6882a;
        }

        public void setCustomReminderList(List<CustomRemindersListItem> list) {
            this.b = list;
        }

        public void setTzOffset(String str) {
            this.f6882a = str;
        }
    }

    public CustomReminders getCustomReminders() {
        return this.f6881a;
    }

    public void setCustomReminders(CustomReminders customReminders) {
        this.f6881a = customReminders;
    }
}
