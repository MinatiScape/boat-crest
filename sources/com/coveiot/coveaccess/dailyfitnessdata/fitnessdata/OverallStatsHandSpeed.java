package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class OverallStatsHandSpeed {
    @SerializedName("date")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6463a;
    @SerializedName("avgHandSpeed")
    @Expose
    private double b;

    public double getAvgHandSpeed() {
        return this.b;
    }

    public String getDate() {
        return this.f6463a;
    }

    public void setAvgHandSpeed(double d) {
        this.b = d;
    }

    public void setDate(String str) {
        this.f6463a = str;
    }

    public String toString() {
        return "OverallStatsHandSpeed{date='" + this.f6463a + "', avgHandSpeed=" + this.b + '}';
    }
}
