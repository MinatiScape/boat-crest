package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class DailyLog {
    @SerializedName("startTime")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6452a;
    @SerializedName("endTime")
    @Expose
    private String b;
    @SerializedName("stepValues")
    @Expose
    private List<Integer> c = null;
    @SerializedName("activeTimeValues")
    @Expose
    private List<Integer> d = null;
    @SerializedName("calorieValues")
    @Expose
    private List<Double> e = null;
    @SerializedName("distanceValues")
    @Expose
    private List<Integer> f = null;
    @SerializedName("strokeValues")
    @Expose
    private List<Integer> g = null;

    public List<Integer> getActiveTimeValues() {
        return this.d;
    }

    public List<Double> getCalorieValues() {
        return this.e;
    }

    public List<Integer> getDistanceValues() {
        return this.f;
    }

    public String getEndTime() {
        return this.b;
    }

    public String getStartTime() {
        return this.f6452a;
    }

    public List<Integer> getStepValues() {
        return this.c;
    }

    public List<Integer> getStrokeValues() {
        return this.g;
    }

    public void setActiveTimeValues(List<Integer> list) {
        this.d = list;
    }

    public void setCalorieValues(List<Double> list) {
        this.e = list;
    }

    public void setDistanceValues(List<Integer> list) {
        this.f = list;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setStartTime(String str) {
        this.f6452a = str;
    }

    public void setStepValues(List<Integer> list) {
        this.c = list;
    }

    public void setStrokeValues(List<Integer> list) {
        this.g = list;
    }
}
