package com.coveiot.covedb.walk.model;

import androidx.room.Ignore;
/* loaded from: classes8.dex */
public class PersonalBest {
    @Ignore

    /* renamed from: a  reason: collision with root package name */
    public String f6996a;
    public String b;
    @Ignore
    public String c;
    @Ignore
    public String d = "STEPS";
    public long e;
    @Ignore
    public long f;
    @Ignore
    public long g;

    public String getActivityBaseUnit() {
        return this.d;
    }

    public String getActivityType() {
        return this.c;
    }

    public long getCalories() {
        return this.g;
    }

    public String getDate() {
        return this.b;
    }

    public long getMeters() {
        return this.f;
    }

    public String getStatsType() {
        return this.f6996a;
    }

    public long getValue() {
        return this.e;
    }

    public void setActivityBaseUnit(String str) {
        this.d = str;
    }

    public void setActivityType(String str) {
        this.c = str;
    }

    public void setCalories(long j) {
        this.g = j;
    }

    public void setDate(String str) {
        this.b = str;
    }

    public void setMeters(long j) {
        this.f = j;
    }

    public void setStatsType(String str) {
        this.f6996a = str;
    }

    public void setValue(long j) {
        this.e = j;
    }

    public String toString() {
        return "PersonalBest{statsType='" + this.f6996a + "', date='" + this.b + "', activityType='" + this.c + "', activityBaseUnit='" + this.d + "', value=" + this.e + ", meters=" + this.f + ", calories=" + this.g + '}';
    }
}
