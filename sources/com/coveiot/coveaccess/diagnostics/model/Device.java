package com.coveiot.coveaccess.diagnostics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Device {
    @SerializedName("customerId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6476a;
    @SerializedName("modelNumber")
    @Expose
    private String b;
    @SerializedName("btName")
    @Expose
    private String c;
    @SerializedName("btMacAddress")
    @Expose
    private String d;
    @SerializedName("firmwareVersion")
    @Expose
    private String e;

    public String getBtMacAddress() {
        return this.d;
    }

    public String getBtName() {
        return this.c;
    }

    public Integer getCustomerId() {
        return this.f6476a;
    }

    public String getFirmwareVersion() {
        return this.e;
    }

    public String getModelNumber() {
        return this.b;
    }

    public void setBtMacAddress(String str) {
        this.d = str;
    }

    public void setBtName(String str) {
        this.c = str;
    }

    public void setCustomerId(Integer num) {
        this.f6476a = num;
    }

    public void setFirmwareVersion(String str) {
        this.e = str;
    }

    public void setModelNumber(String str) {
        this.b = str;
    }
}
