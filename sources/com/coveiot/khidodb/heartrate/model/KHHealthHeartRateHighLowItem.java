package com.coveiot.khidodb.heartrate.model;
/* loaded from: classes8.dex */
public final class KHHealthHeartRateHighLowItem {

    /* renamed from: a  reason: collision with root package name */
    public final int f7092a;
    public final int b = 1;
    public int c;
    public int d;
    public int e;
    public int f;

    public final int getHeart_rate() {
        return this.f;
    }

    public final int getHour() {
        return this.c;
    }

    public final int getMinute() {
        return this.d;
    }

    public final int getTOO_HIGH() {
        return this.b;
    }

    public final int getTOO_LOW() {
        return this.f7092a;
    }

    public final int getType() {
        return this.e;
    }

    public final void setHeart_rate(int i) {
        this.f = i;
    }

    public final void setHour(int i) {
        this.c = i;
    }

    public final void setMinute(int i) {
        this.d = i;
    }

    public final void setType(int i) {
        this.e = i;
    }
}
