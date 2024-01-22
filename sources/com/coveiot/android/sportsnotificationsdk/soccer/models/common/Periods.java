package com.coveiot.android.sportsnotificationsdk.soccer.models.common;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Periods {
    @SerializedName("p1")

    /* renamed from: a  reason: collision with root package name */
    private Period f5982a;
    @SerializedName("p2")
    private Period b;
    @SerializedName("ft")
    private Period c;
    @SerializedName("et")
    private Period d;
    @SerializedName("ps")
    private Period e;

    public Period getEt() {
        return this.d;
    }

    public Period getFt() {
        return this.c;
    }

    public Period getP1() {
        return this.f5982a;
    }

    public Period getP2() {
        return this.b;
    }

    public Period getPs() {
        return this.e;
    }

    public void setEt(Period period) {
        this.d = period;
    }

    public void setFt(Period period) {
        this.c = period;
    }

    public void setP1(Period period) {
        this.f5982a = period;
    }

    public void setP2(Period period) {
        this.b = period;
    }

    public void setPs(Period period) {
        this.e = period;
    }
}
