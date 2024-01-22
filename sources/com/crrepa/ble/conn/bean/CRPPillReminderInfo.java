package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPPillReminderInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7661a;
    public int b;
    public String c;
    public int d;
    public List<ReminderTimeBean> e;

    /* loaded from: classes9.dex */
    public static class ReminderTimeBean {

        /* renamed from: a  reason: collision with root package name */
        public int f7662a;
        public int b;

        public ReminderTimeBean(int i, int i2) {
            this.f7662a = i;
            this.b = i2;
        }

        public int getCount() {
            return this.b;
        }

        public int getTime() {
            return this.f7662a;
        }

        public void setCount(int i) {
            this.b = i;
        }

        public void setTime(int i) {
            this.f7662a = i;
        }
    }

    public CRPPillReminderInfo() {
    }

    public CRPPillReminderInfo(int i, int i2, String str, int i3, List<ReminderTimeBean> list) {
        this.f7661a = i;
        this.b = i2;
        this.c = str;
        this.d = i3;
        this.e = list;
    }

    public int getDateOffset() {
        return this.b;
    }

    public int getId() {
        return this.f7661a;
    }

    public String getName() {
        return this.c;
    }

    public List<ReminderTimeBean> getReminderTimeList() {
        return this.e;
    }

    public int getRepeat() {
        return this.d;
    }

    public void setDateOffset(int i) {
        this.b = i;
    }

    public void setId(int i) {
        this.f7661a = i;
    }

    public void setName(String str) {
        this.c = str;
    }

    public void setReminderTimeList(List<ReminderTimeBean> list) {
        this.e = list;
    }

    public void setRepeat(int i) {
        this.d = i;
    }
}
