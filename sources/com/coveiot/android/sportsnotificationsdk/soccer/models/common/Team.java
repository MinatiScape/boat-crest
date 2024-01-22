package com.coveiot.android.sportsnotificationsdk.soccer.models.common;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Team {
    @SerializedName("tid")

    /* renamed from: a  reason: collision with root package name */
    private String f5984a;
    @SerializedName("tname")
    private String b;
    @SerializedName("logo")
    private String c;
    @SerializedName("fullname")
    private String d;
    @SerializedName("abbr")
    private String e;

    public String getAbbr() {
        return this.e;
    }

    public String getFullname() {
        return this.d;
    }

    public String getLogo() {
        return this.c;
    }

    public String getTid() {
        return this.f5984a;
    }

    public String getTname() {
        return this.b;
    }

    public void setAbbr(String str) {
        this.e = str;
    }

    public void setFullname(String str) {
        this.d = str;
    }

    public void setLogo(String str) {
        this.c = str;
    }

    public void setTid(String str) {
        this.f5984a = str;
    }

    public void setTname(String str) {
        this.b = str;
    }
}
