package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class OtherParams {
    @SerializedName("userAge")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6390a;
    @SerializedName("userGender")
    @Expose
    private String b;
    @SerializedName("userHeight")
    @Expose
    private Integer c;
    @SerializedName("userWeight")
    @Expose
    private Integer d;
    @SerializedName("userWalkingStrideLength")
    @Expose
    private Integer e;
    @SerializedName("userRunningStrideLength")
    @Expose
    private Integer f;
    @SerializedName("userRestHr")
    @Expose
    private Integer g;
    @SerializedName("userMaxHr")
    @Expose
    private Integer h;
    @SerializedName("userSwimmingStrokeLength")
    @Expose
    private Integer i;

    public Integer getUserAge() {
        return this.f6390a;
    }

    public String getUserGender() {
        return this.b;
    }

    public Integer getUserHeight() {
        return this.c;
    }

    public Integer getUserMaxHr() {
        return this.h;
    }

    public Integer getUserRestHr() {
        return this.g;
    }

    public Integer getUserRunningStrideLength() {
        return this.f;
    }

    public Integer getUserSwimmingStrokeLength() {
        return this.i;
    }

    public Integer getUserWalkingStrideLength() {
        return this.e;
    }

    public Integer getUserWeight() {
        return this.d;
    }

    public void setUserAge(Integer num) {
        this.f6390a = num;
    }

    public void setUserGender(String str) {
        this.b = str;
    }

    public void setUserHeight(Integer num) {
        this.c = num;
    }

    public void setUserMaxHr(Integer num) {
        this.h = num;
    }

    public void setUserRestHr(Integer num) {
        this.g = num;
    }

    public void setUserRunningStrideLength(Integer num) {
        this.f = num;
    }

    public void setUserSwimmingStrokeLength(Integer num) {
        this.i = num;
    }

    public void setUserWalkingStrideLength(Integer num) {
        this.e = num;
    }

    public void setUserWeight(Integer num) {
        this.d = num;
    }
}
