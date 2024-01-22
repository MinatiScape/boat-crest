package com.coveiot.coveaccess.drowsiness;

import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public class DrowsyEstimationReq {
    @SerializedName("signalStr")

    /* renamed from: a  reason: collision with root package name */
    public String f6479a;
    @SerializedName("age")
    public String b;
    @SerializedName("gender")
    public String c;
    @SerializedName("weight")
    public String d;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    public String e;
    @SerializedName("deviceType")
    public String f;
    @SerializedName("ppgType")
    private Integer g;

    public DrowsyEstimationReq(String str, String str2, String str3, String str4, String str5, String str6, Integer num) {
        this.f6479a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = num;
    }

    public String getAge() {
        return this.b;
    }

    public String getDeviceType() {
        return this.f;
    }

    public String getGender() {
        return this.c;
    }

    public String getHeight() {
        return this.e;
    }

    public Integer getPpgType() {
        return this.g;
    }

    public String getSignal() {
        return this.f6479a;
    }

    public String getWeight() {
        return this.d;
    }
}
