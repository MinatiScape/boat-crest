package com.coveiot.android.sportsnotificationsdk.soccer.models.common;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Teams {
    @SerializedName("home")

    /* renamed from: a  reason: collision with root package name */
    private Team f5985a;
    @SerializedName("away")
    private Team b;

    public Team getAway() {
        return this.b;
    }

    public Team getHome() {
        return this.f5985a;
    }

    public void setAway(Team team) {
        this.b = team;
    }

    public void setHome(Team team) {
        this.f5985a = team;
    }
}
