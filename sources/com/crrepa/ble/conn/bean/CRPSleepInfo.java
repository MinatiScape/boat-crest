package com.crrepa.ble.conn.bean;

import java.util.List;
/* loaded from: classes9.dex */
public class CRPSleepInfo {
    public static final int SLEEP_STATE_AWAKE = 0;
    public static final int SLEEP_STATE_LIGHT = 1;
    public static final int SLEEP_STATE_REM = 3;
    public static final int SLEEP_STATE_RESTFUL = 2;

    /* renamed from: a  reason: collision with root package name */
    public int f7665a;
    public int b;
    public int c;
    public int d;
    public int e;
    public List<DetailBean> f;

    /* loaded from: classes9.dex */
    public static class DetailBean {

        /* renamed from: a  reason: collision with root package name */
        public int f7666a;
        public int b;
        public int c;
        public int d;

        public int getEndTime() {
            return this.b;
        }

        public int getStartTime() {
            return this.f7666a;
        }

        public int getTotalTime() {
            return this.c;
        }

        public int getType() {
            return this.d;
        }

        public void setEndTime(int i) {
            this.b = i;
        }

        public void setStartTime(int i) {
            this.f7666a = i;
        }

        public void setTotalTime(int i) {
            this.c = i;
        }

        public void setType(int i) {
            this.d = i;
        }
    }

    public int getAwakeTime() {
        return this.d;
    }

    public List<DetailBean> getDetails() {
        return this.f;
    }

    public int getLightTime() {
        return this.c;
    }

    public int getRemTime() {
        return this.e;
    }

    public int getRestfulTime() {
        return this.b;
    }

    public int getTotalTime() {
        return this.f7665a;
    }

    public void setAwakeTime(int i) {
        this.d = i;
    }

    public void setDetails(List<DetailBean> list) {
        this.f = list;
    }

    public void setLightTime(int i) {
        this.c = i;
    }

    public void setRemTime(int i) {
        this.e = i;
    }

    public void setRestfulTime(int i) {
        this.b = i;
    }

    public void setTotalTime(int i) {
        this.f7665a = i;
    }
}
