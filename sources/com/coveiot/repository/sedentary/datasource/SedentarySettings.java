package com.coveiot.repository.sedentary.datasource;
/* loaded from: classes9.dex */
public class SedentarySettings {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7430a;
    public String b;
    public String c;
    public String d;
    public SiestaBean e;

    /* loaded from: classes9.dex */
    public static class SiestaBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7431a;

        public boolean isActive() {
            return this.f7431a;
        }

        public void setActive(boolean z) {
            this.f7431a = z;
        }
    }

    public String getEndTime() {
        return this.c;
    }

    public String getInterval() {
        return this.d;
    }

    public SiestaBean getSiesta() {
        return this.e;
    }

    public String getStartTime() {
        return this.b;
    }

    public boolean isActive() {
        return this.f7430a;
    }

    public void setActive(boolean z) {
        this.f7430a = z;
    }

    public void setEndTime(String str) {
        this.c = str;
    }

    public void setInterval(String str) {
        this.d = str;
    }

    public void setSiesta(SiestaBean siestaBean) {
        this.e = siestaBean;
    }

    public void setStartTime(String str) {
        this.b = str;
    }
}
