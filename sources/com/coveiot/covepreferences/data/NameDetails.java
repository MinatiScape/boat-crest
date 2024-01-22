package com.coveiot.covepreferences.data;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class NameDetails {
    @SerializedName("fname")

    /* renamed from: a  reason: collision with root package name */
    private String f7032a;
    @SerializedName("lname")
    private String b;

    public String getFname() {
        return this.f7032a;
    }

    public String getLname() {
        return this.b;
    }

    public void setFname(String str) {
        this.f7032a = str;
    }

    public void setLname(String str) {
        this.b = str;
    }
}
