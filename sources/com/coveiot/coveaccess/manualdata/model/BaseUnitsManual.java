package com.coveiot.coveaccess.manualdata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public class BaseUnitsManual {
    @SerializedName("hr")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6649a;
    @SerializedName(DeviceKey.HRV)
    @Expose
    private String b;
    @SerializedName("bp")
    @Expose
    private String c;
    @SerializedName("spo2")
    @Expose
    private String d;
    @SerializedName(DeviceKey.Stress)
    @Expose
    private String e;
    @SerializedName("vascAging")
    @Expose
    private String f;

    public String getBp() {
        return this.c;
    }

    public String getHr() {
        return this.f6649a;
    }

    public String getHrv() {
        return this.b;
    }

    public String getSpo2() {
        return this.d;
    }

    public String getStress() {
        return this.e;
    }

    public String getVascAging() {
        return this.f;
    }

    public void setBp(String str) {
        this.c = str;
    }

    public void setHr(String str) {
        this.f6649a = str;
    }

    public void setHrv(String str) {
        this.b = str;
    }

    public void setSpo2(String str) {
        this.d = str;
    }

    public void setStress(String str) {
        this.e = str;
    }

    public void setVascAging(String str) {
        this.f = str;
    }
}
