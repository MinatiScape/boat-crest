package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessDatum {
    @SerializedName("avgCadence")
    @Expose
    private Integer A;
    @SerializedName("lapDistance")
    @Expose
    private Integer B;
    @SerializedName("type")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6459a;
    @SerializedName("date")
    @Expose
    private String b;
    @SerializedName("baseUnit")
    @Expose
    private String c;
    @SerializedName("min")
    @Expose
    private Integer d;
    @SerializedName(Constants.PRIORITY_MAX)
    @Expose
    private Integer e;
    @SerializedName("rest")
    @Expose
    private Integer f;
    @SerializedName("timeLog")
    @Expose
    private TimeLog g;
    @SerializedName("value")
    @Expose
    private Integer h;
    @SerializedName("calories")
    @Expose
    private Float i;
    @SerializedName("activeTime")
    @Expose
    private Integer j;
    @SerializedName("distance")
    @Expose
    private Integer k;
    @SerializedName("avgSpeed")
    @Expose
    private Double l;
    @SerializedName("maxSpeed")
    @Expose
    private Double m;
    @SerializedName("baseUnits")
    @Expose
    private BaseUnits n;
    @SerializedName("avgPower")
    @Expose
    private Integer o;
    @SerializedName("maxPower")
    @Expose
    private Integer p;
    @SerializedName("avg")
    @Expose
    private Integer q;
    @SerializedName("avgSystolic")
    @Expose
    private Integer s;
    @SerializedName("avgDiastolic")
    @Expose
    private Integer t;
    @SerializedName("avgHrv")
    @Expose
    private double u;
    @SerializedName("hrZoneRanges")
    @Expose
    private List<Integer> w;
    @SerializedName("decalt")
    @Expose
    private Integer x;
    @SerializedName("incalt")
    @Expose
    private Integer y;
    @SerializedName("maxCadence")
    @Expose
    private Integer z;
    @SerializedName("targets")
    @Expose
    private List<Target> r = null;
    @SerializedName("hrZoneDurations")
    @Expose
    private List<Integer> v = null;

    public Integer getActiveTime() {
        return this.j;
    }

    public Integer getAvg() {
        return this.q;
    }

    public Integer getAvgCadence() {
        return this.A;
    }

    public Integer getAvgDiastolic() {
        return this.t;
    }

    public double getAvgHrv() {
        return this.u;
    }

    public Integer getAvgPower() {
        return this.o;
    }

    public Double getAvgSpeed() {
        return this.l;
    }

    public Integer getAvgSystolic() {
        return this.s;
    }

    public String getBaseUnit() {
        return this.c;
    }

    public BaseUnits getBaseUnits() {
        return this.n;
    }

    public Float getCalories() {
        return this.i;
    }

    public String getDate() {
        return this.b;
    }

    public Integer getDecalt() {
        return this.x;
    }

    public Integer getDistance() {
        return this.k;
    }

    public List<Integer> getHrZoneDurations() {
        return this.v;
    }

    public List<Integer> getHrZoneRanges() {
        return this.w;
    }

    public Integer getIncalt() {
        return this.y;
    }

    public Integer getLapDistance() {
        return this.B;
    }

    public Integer getMax() {
        return this.e;
    }

    public Integer getMaxCadence() {
        return this.z;
    }

    public Integer getMaxPower() {
        return this.p;
    }

    public Double getMaxSpeed() {
        return this.m;
    }

    public Integer getMin() {
        return this.d;
    }

    public Integer getRest() {
        return this.f;
    }

    public List<Target> getTargets() {
        return this.r;
    }

    public TimeLog getTimeLog() {
        return this.g;
    }

    public String getType() {
        return this.f6459a;
    }

    public Integer getValue() {
        return this.h;
    }

    public void setActiveTime(Integer num) {
        this.j = num;
    }

    public void setAvg(Integer num) {
        this.q = num;
    }

    public void setAvgCadence(Integer num) {
        this.A = num;
    }

    public void setAvgDiastolic(Integer num) {
        this.t = num;
    }

    public void setAvgHrv(double d) {
        this.u = d;
    }

    public void setAvgPower(Integer num) {
        this.o = num;
    }

    public void setAvgSpeed(Double d) {
        this.l = d;
    }

    public void setAvgSystolic(Integer num) {
        this.s = num;
    }

    public void setBaseUnit(String str) {
        this.c = str;
    }

    public void setBaseUnits(BaseUnits baseUnits) {
        this.n = baseUnits;
    }

    public void setCalories(Float f) {
        this.i = f;
    }

    public void setDate(String str) {
        this.b = str;
    }

    public void setDecalt(Integer num) {
        this.x = num;
    }

    public void setDistance(Integer num) {
        this.k = num;
    }

    public void setHrZoneDurations(List<Integer> list) {
        this.v = list;
    }

    public void setHrZoneRanges(List<Integer> list) {
        this.w = list;
    }

    public void setIncalt(Integer num) {
        this.y = num;
    }

    public void setLapDistance(Integer num) {
        this.B = num;
    }

    public void setMax(Integer num) {
        this.e = num;
    }

    public void setMaxCadence(Integer num) {
        this.z = num;
    }

    public void setMaxPower(Integer num) {
        this.p = num;
    }

    public void setMaxSpeed(Double d) {
        this.m = d;
    }

    public void setMin(Integer num) {
        this.d = num;
    }

    public void setRest(Integer num) {
        this.f = num;
    }

    public void setTargets(List<Target> list) {
        this.r = list;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.g = timeLog;
    }

    public void setType(String str) {
        this.f6459a = str;
    }

    public void setValue(Integer num) {
        this.h = num;
    }
}
