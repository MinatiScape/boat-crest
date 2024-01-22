package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Target {
    @SerializedName("value")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6395a;
    @SerializedName("baseUnit")
    @Expose
    private String b;

    public Target() {
    }

    public String getBaseUnit() {
        return this.b;
    }

    public Integer getValue() {
        return this.f6395a;
    }

    public void setBaseUnit(String str) {
        this.b = str;
    }

    public void setValue(Integer num) {
        this.f6395a = num;
    }

    public Target(Integer num, String str) {
        this.f6395a = num;
        this.b = str;
    }
}
