package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class OtherParams {
    @SerializedName("userAge")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6454a;
    @SerializedName("userGender")
    @Expose
    private String b;
    @SerializedName("userHeight")
    @Expose
    private Integer c;
    @SerializedName("userWeight")
    @Expose
    private Double d;
    @SerializedName("userWalkingStrideLength")
    @Expose
    private Integer e;
    @SerializedName("userRunningStrideLength")
    @Expose
    private Integer f;
    @SerializedName("userSwimmingStrokeLength")
    @Expose
    private Integer g;

    public Integer getUserAge() {
        return this.f6454a;
    }

    public String getUserGender() {
        return this.b;
    }

    public Integer getUserHeight() {
        return this.c;
    }

    public Integer getUserRunningStrideLength() {
        return this.f;
    }

    public Integer getUserSwimmingStrokeLength() {
        return this.g;
    }

    public Integer getUserWalkingStrideLength() {
        return this.e;
    }

    public Double getUserWeight() {
        return this.d;
    }

    public void setUserAge(Integer num) {
        this.f6454a = num;
    }

    public void setUserGender(String str) {
        this.b = str;
    }

    public void setUserHeight(Integer num) {
        this.c = num;
    }

    public void setUserRunningStrideLength(Integer num) {
        this.f = num;
    }

    public void setUserSwimmingStrokeLength(Integer num) {
        this.g = num;
    }

    public void setUserWalkingStrideLength(Integer num) {
        this.e = num;
    }

    public void setUserWeight(Double d) {
        this.d = d;
    }
}
