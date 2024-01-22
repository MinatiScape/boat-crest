package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public final class GetWatchtimeInfoResponse {

    /* renamed from: a  reason: collision with root package name */
    public int f3628a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public int h;
    public int i;
    public int j;
    public boolean k = true;

    public final int getDay() {
        return this.c;
    }

    public final int getHour() {
        return this.d;
    }

    public final int getMinute() {
        return this.e;
    }

    public final int getMonth() {
        return this.b;
    }

    public final int getSecond() {
        return this.f;
    }

    public final int getTimeZone() {
        return this.h;
    }

    public final int getTimeZoneHour() {
        return this.i;
    }

    public final int getTimeZoneMinute() {
        return this.j;
    }

    public final int getYear() {
        return this.f3628a;
    }

    public final boolean is12hourFormat() {
        return this.g;
    }

    public final boolean isNormalSyncMode() {
        return this.k;
    }

    public final void set12hourFormat(boolean z) {
        this.g = z;
    }

    public final void setDay(int i) {
        this.c = i;
    }

    public final void setHour(int i) {
        this.d = i;
    }

    public final void setMinute(int i) {
        this.e = i;
    }

    public final void setMonth(int i) {
        this.b = i;
    }

    public final void setNormalSyncMode(boolean z) {
        this.k = z;
    }

    public final void setSecond(int i) {
        this.f = i;
    }

    public final void setTimeZone(int i) {
        this.h = i;
    }

    public final void setTimeZoneHour(int i) {
        this.i = i;
    }

    public final void setTimeZoneMinute(int i) {
        this.j = i;
    }

    public final void setYear(int i) {
        this.f3628a = i;
    }
}
