package com.coveiot.coveaccess.drowsyfatiguestress;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public class DrowsyFatigueStressDataReq {
    @SerializedName("signalStr")

    /* renamed from: a  reason: collision with root package name */
    public String f6480a;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public String b;
    @SerializedName("age")
    public String c;
    @SerializedName("gender")
    public String d;
    @SerializedName("weight")
    public String e;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    public String f;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    public String g;
    @SerializedName("skintone")
    public String h;
    @SerializedName("handedness")
    public String i;
    @SerializedName("drowsy")
    public String j;
    @SerializedName(DeviceKey.Stress)
    public String k;
    @SerializedName("fatigue")
    public String l;
    @SerializedName("deviceType")
    public String m;

    public DrowsyFatigueStressDataReq(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f6480a = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.m = str7;
    }

    public String getAge() {
        return this.c;
    }

    public String getDeviceType() {
        return this.m;
    }

    public String getDrowsy() {
        return this.j;
    }

    public String getFatigue() {
        return this.l;
    }

    public String getGender() {
        return this.d;
    }

    public String getHandedness() {
        return this.i;
    }

    public String getHeight() {
        return this.f;
    }

    public String getName() {
        return this.b;
    }

    public String getSignal() {
        return this.f6480a;
    }

    public String getSkintone() {
        return this.h;
    }

    public String getStress() {
        return this.k;
    }

    public String getTime() {
        return this.g;
    }

    public String getWeight() {
        return this.e;
    }

    public DrowsyFatigueStressDataReq(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        this.f6480a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = str9;
        this.j = str10;
        this.k = str11;
        this.l = str12;
        this.m = str13;
    }
}
