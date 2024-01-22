package com.coveiot.coveaccess.spo2;

import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public class SPO2EstimationReq {
    @SerializedName("signalStr")

    /* renamed from: a  reason: collision with root package name */
    public String f6734a;
    @SerializedName("age")
    public String b;
    @SerializedName("gender")
    public String c;
    @SerializedName("weight")
    public String d;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    public String e;
    @SerializedName("regression")
    public String f;
    public String g;
    public String h = "1";
    public String i = "-1";
    @SerializedName("deviceType")
    public String j;

    public SPO2EstimationReq(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f6734a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.g = str6;
        this.j = str7;
    }

    public String getAge() {
        return this.b;
    }

    public String getDeviceType() {
        return this.j;
    }

    public String getGender() {
        return this.c;
    }

    public String getHeight() {
        return this.e;
    }

    public String getPerson() {
        return this.i;
    }

    public String getRegression() {
        return this.f;
    }

    public String getSide() {
        return this.g;
    }

    public String getSignal() {
        return this.f6734a;
    }

    public String getState() {
        return this.h;
    }

    public String getWeight() {
        return this.d;
    }

    public void setRegression(String str) {
        this.f = str;
    }

    public SPO2EstimationReq(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f6734a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.j = str6;
    }
}
