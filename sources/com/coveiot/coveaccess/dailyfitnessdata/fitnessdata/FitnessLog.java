package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessLog {
    @SerializedName("startTime")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6460a;
    @SerializedName("endTime")
    @Expose
    private String b;
    @SerializedName("min")
    @Expose
    private String c;
    @SerializedName(Constants.PRIORITY_MAX)
    @Expose
    private String d;
    @SerializedName("codedValues")
    @Expose
    private List<Integer> e = null;
    @SerializedName("activeTimeValues")
    @Expose
    private List<Integer> f = null;
    @SerializedName("calorieValues")
    @Expose
    private List<Float> g = null;
    @SerializedName("distanceValues")
    @Expose
    private List<Integer> h = null;
    @SerializedName("coordinateValues")
    @Expose
    private List<List<Double>> i;
    @SerializedName("hrValues")
    @Expose
    private List<Integer> j;
    @SerializedName("value")
    @Expose
    private double k;
    @SerializedName("hrvMethod")
    @Expose
    private String l;
    @SerializedName("avgHr")
    @Expose
    private int m;
    @SerializedName("fatigueValues")
    @Expose
    private List<Integer> n;
    @SerializedName("vascOcclValues")
    @Expose
    private List<Double> o;
    @SerializedName("rriValues")
    @Expose
    private List<Integer> p;
    @SerializedName("avg")
    @Expose
    private float q;
    @SerializedName("tzOffset")
    @Expose
    private String r;

    public List<Integer> getActiveTimeValues() {
        return this.f;
    }

    public float getAvg() {
        return this.q;
    }

    public int getAvgHr() {
        return this.m;
    }

    public List<Float> getCalorieValues() {
        return this.g;
    }

    public List<Integer> getCodedValues() {
        return this.e;
    }

    public List<List<Double>> getCoordinateValues() {
        return this.i;
    }

    public List<Integer> getDistanceValues() {
        return this.h;
    }

    public String getEndTime() {
        return this.b;
    }

    public List<Integer> getFatigueValues() {
        return this.n;
    }

    public List<Integer> getHrValues() {
        return this.j;
    }

    public String getHrvMethod() {
        return this.l;
    }

    public String getMax() {
        return this.d;
    }

    public String getMin() {
        return this.c;
    }

    public List<Integer> getRriValues() {
        return this.p;
    }

    public String getStartTime() {
        return this.f6460a;
    }

    public String getTzOffset() {
        return this.r;
    }

    public double getValue() {
        return this.k;
    }

    public List<Double> getVascOcclValues() {
        return this.o;
    }

    public void setActiveTimeValues(List<Integer> list) {
        this.f = list;
    }

    public void setAvg(float f) {
        this.q = f;
    }

    public void setAvgHr(int i) {
        this.m = i;
    }

    public void setCalorieValues(List<Float> list) {
        this.g = list;
    }

    public void setCodedValues(List<Integer> list) {
        this.e = list;
    }

    public void setCoordinateValues(List<List<Double>> list) {
        this.i = list;
    }

    public void setDistanceValues(List<Integer> list) {
        this.h = list;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setFatigueValues(List<Integer> list) {
        this.n = list;
    }

    public void setHrValues(List<Integer> list) {
        this.j = list;
    }

    public void setHrvMethod(String str) {
        this.l = str;
    }

    public void setMax(String str) {
        this.d = str;
    }

    public void setMin(String str) {
        this.c = str;
    }

    public void setRriValues(List<Integer> list) {
        this.p = list;
    }

    public void setStartTime(String str) {
        this.f6460a = str;
    }

    public void setTzOffset(String str) {
        this.r = str;
    }

    public void setValue(double d) {
        this.k = d;
    }

    public void setVascOcclValues(List<Double> list) {
        this.o = list;
    }
}
