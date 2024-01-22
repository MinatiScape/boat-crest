package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class OverallStatsHitPercentage {
    @SerializedName("date")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6464a;
    @SerializedName("hitPercentage")
    @Expose
    private int b;

    public String getDate() {
        return this.f6464a;
    }

    public int getHitPercentage() {
        return this.b;
    }

    public void setDate(String str) {
        this.f6464a = str;
    }

    public void setHitPercentage(int i) {
        this.b = i;
    }

    public String toString() {
        return "OverallStatsHitPercentage{date='" + this.f6464a + "', hitPercentage=" + this.b + '}';
    }
}
