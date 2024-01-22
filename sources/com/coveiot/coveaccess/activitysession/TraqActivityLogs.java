package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class TraqActivityLogs {
    @SerializedName("segmentId")

    /* renamed from: a  reason: collision with root package name */
    private String f6396a;
    @SerializedName("startTime")
    @Expose
    private String b;
    @SerializedName("timeSpan")
    @Expose
    private int c;
    @SerializedName("coordinateValues")
    @Expose
    private List<List<Double>> d = null;
    @SerializedName("distanceValues")
    @Expose
    private List<Integer> e = null;
    @SerializedName("speedValues")
    @Expose
    private List<Float> f = null;
    @SerializedName("paceValues")
    @Expose
    private List<Float> g = null;
    @SerializedName("hrValues")
    @Expose
    private List<Integer> h = null;
    @SerializedName("calorieValues")
    @Expose
    private List<Float> i = null;
    @SerializedName("stepValues")
    @Expose
    private List<Integer> j;
    @SerializedName("powerValues")
    @Expose
    private List<Integer> k;
    @SerializedName("cadenceValues")
    @Expose
    private List<Integer> l;
    @SerializedName("strokeValues")
    @Expose
    private List<Integer> m;
    @SerializedName("incaltValues")
    @Expose
    private List<Integer> n;
    @SerializedName("decaltValues")
    @Expose
    private List<Integer> o;
    @SerializedName("handSpeedValues")
    @Expose
    private List<Integer> p;
    @SerializedName("hitValues")
    @Expose
    private List<Boolean> q;

    public List<Integer> getCadenceValues() {
        return this.l;
    }

    public List<Float> getCalorieValues() {
        return this.i;
    }

    public List<List<Double>> getCoordinateValues() {
        return this.d;
    }

    public List<Integer> getDecaltValues() {
        return this.o;
    }

    public List<Integer> getDistanceValues() {
        return this.e;
    }

    public List<Integer> getHandSpeedValues() {
        return this.p;
    }

    public List<Boolean> getHitValues() {
        return this.q;
    }

    public List<Integer> getHrValues() {
        return this.h;
    }

    public List<Integer> getIncaltValues() {
        return this.n;
    }

    public List<Float> getPaceValues() {
        return this.g;
    }

    public List<Integer> getPowerValues() {
        return this.k;
    }

    public String getSegmentId() {
        return this.f6396a;
    }

    public List<Float> getSpeedValues() {
        return this.f;
    }

    public String getStartTime() {
        return this.b;
    }

    public List<Integer> getStepValues() {
        return this.j;
    }

    public List<Integer> getStrokeValues() {
        return this.m;
    }

    public int getTimeSpan() {
        return this.c;
    }

    public void setCadenceValues(List<Integer> list) {
        this.l = list;
    }

    public void setCalorieValues(List<Float> list) {
        this.i = list;
    }

    public void setCoordinateValues(List<List<Double>> list) {
        this.d = list;
    }

    public void setDecaltValues(List<Integer> list) {
        this.o = list;
    }

    public void setDistanceValues(List<Integer> list) {
        this.e = list;
    }

    public void setHandSpeedValues(List<Integer> list) {
        this.p = list;
    }

    public void setHitValues(List<Boolean> list) {
        this.q = list;
    }

    public void setHrValues(List<Integer> list) {
        this.h = list;
    }

    public void setIncaltValues(List<Integer> list) {
        this.n = list;
    }

    public void setPaceValues(List<Float> list) {
        this.g = list;
    }

    public void setPowerValues(List<Integer> list) {
        this.k = list;
    }

    public void setSegmentId(String str) {
        this.f6396a = str;
    }

    public void setSpeedValues(List<Float> list) {
        this.f = list;
    }

    public void setStartTime(String str) {
        this.b = str;
    }

    public void setStepValues(List<Integer> list) {
        this.j = list;
    }

    public void setStrokeValues(List<Integer> list) {
        this.m = list;
    }

    public void setTimeSpan(int i) {
        this.c = i;
    }
}
