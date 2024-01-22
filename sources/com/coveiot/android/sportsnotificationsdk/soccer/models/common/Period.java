package com.coveiot.android.sportsnotificationsdk.soccer.models.common;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Period {
    @SerializedName("home")

    /* renamed from: a  reason: collision with root package name */
    private Integer f5981a;
    @SerializedName("away")
    private Integer b;

    public Integer getAway() {
        return this.b;
    }

    public Integer getHome() {
        return this.f5981a;
    }

    public void setAway(Integer num) {
        this.b = num;
    }

    public void setHome(Integer num) {
        this.f5981a = num;
    }
}
