package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Target {
    @SerializedName("value")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6468a;
    @SerializedName("baseUnit")
    @Expose
    private String b;

    public Target() {
    }

    public String getBaseUnit() {
        return this.b;
    }

    public Integer getValue() {
        return this.f6468a;
    }

    public void setBaseUnit(String str) {
        this.b = str;
    }

    public void setValue(Integer num) {
        this.f6468a = num;
    }

    public Target(Integer num, String str) {
        this.f6468a = num;
        this.b = str;
    }
}
