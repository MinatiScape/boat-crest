package com.coveiot.android.leonardo.dashboard.health.model;
/* loaded from: classes3.dex */
public class SleepScoreLiveData {

    /* renamed from: a  reason: collision with root package name */
    public int f4730a;
    public int b;
    public int c;
    public String d;

    public String getSleepConsistency() {
        return this.d;
    }

    public int getSleepScore() {
        return this.f4730a;
    }

    public int getTimeToDeepSleep() {
        return this.c;
    }

    public int getWascoCount() {
        return this.b;
    }

    public void setSleepConsistency(String str) {
        this.d = str;
    }

    public void setSleepScore(int i) {
        this.f4730a = i;
    }

    public void setTimeToDeepSleep(int i) {
        this.c = i;
    }

    public void setWascoCount(int i) {
        this.b = i;
    }
}
