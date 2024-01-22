package com.coveiot.android.sportsnotificationsdk.soccer.models.common;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Result {
    @SerializedName("home")

    /* renamed from: a  reason: collision with root package name */
    private String f5983a;
    @SerializedName("away")
    private String b;
    @SerializedName("winner")
    private String c;

    public String getAway() {
        return this.b;
    }

    public String getHome() {
        return this.f5983a;
    }

    public String getWinner() {
        return this.c;
    }

    public void setAway(String str) {
        this.b = str;
    }

    public void setHome(String str) {
        this.f5983a = str;
    }

    public void setWinner(String str) {
        this.c = str;
    }
}
