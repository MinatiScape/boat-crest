package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.Target;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessDataAggregate {
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6453a;
    @SerializedName("userDeviceId")
    @Expose
    private Integer b;
    @SerializedName("date")
    @Expose
    private String c;
    @SerializedName("tzOffset")
    @Expose
    private String d;
    @SerializedName("totalSteps")
    @Expose
    private Integer e;
    @SerializedName("totalCalories")
    @Expose
    private Float f;
    @SerializedName("totalActiveTime")
    @Expose
    private Integer g;
    @SerializedName("totalDistance")
    @Expose
    private Integer h;
    @SerializedName("totalStrokes")
    @Expose
    private Integer i;
    @SerializedName("timeLog")
    @Expose
    private TimeLog j;
    @SerializedName("otherParams")
    @Expose
    private OtherParams k;
    @SerializedName("baseUnits")
    @Expose
    private BaseUnits l;
    @SerializedName("targets")
    @Expose
    private List<Target> m;
    @SerializedName("lapDistance")
    @Expose
    private Integer n;
    @SerializedName("cricketBatting")
    @Expose
    private CricketBatting o;
    @SerializedName("cricketBowling")
    @Expose
    private CricketBowling p;

    public BaseUnits getBaseUnits() {
        return this.l;
    }

    public CricketBatting getCricketBatting() {
        return this.o;
    }

    public CricketBowling getCricketBowling() {
        return this.p;
    }

    public String getDate() {
        return this.c;
    }

    public Integer getLapDistance() {
        return this.n;
    }

    public OtherParams getOtherParams() {
        return this.k;
    }

    public List<Target> getTargets() {
        return this.m;
    }

    public TimeLog getTimeLog() {
        return this.j;
    }

    public Integer getTotalActiveTime() {
        return this.g;
    }

    public Float getTotalCalories() {
        return this.f;
    }

    public Integer getTotalDistance() {
        return this.h;
    }

    public Integer getTotalSteps() {
        return this.e;
    }

    public Integer getTotalStrokes() {
        return this.i;
    }

    public String getTzOffset() {
        return this.d;
    }

    public Integer getUserDeviceId() {
        return this.b;
    }

    public Integer getUserId() {
        return this.f6453a;
    }

    public void setBaseUnits(BaseUnits baseUnits) {
        this.l = baseUnits;
    }

    public void setCricketBatting(CricketBatting cricketBatting) {
        this.o = cricketBatting;
    }

    public void setCricketBowling(CricketBowling cricketBowling) {
        this.p = cricketBowling;
    }

    public void setDate(String str) {
        this.c = str;
    }

    public void setLapDistance(Integer num) {
        this.n = num;
    }

    public void setOtherParams(OtherParams otherParams) {
        this.k = otherParams;
    }

    public void setTargets(List<Target> list) {
        this.m = list;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.j = timeLog;
    }

    public void setTotalActiveTime(Integer num) {
        this.g = num;
    }

    public void setTotalCalories(Float f) {
        this.f = f;
    }

    public void setTotalDistance(Integer num) {
        this.h = num;
    }

    public void setTotalSteps(Integer num) {
        this.e = num;
    }

    public void setTotalStrokes(Integer num) {
        this.i = num;
    }

    public void setTzOffset(String str) {
        this.d = str;
    }

    public void setUserDeviceId(Integer num) {
        this.b = num;
    }

    public void setUserId(Integer num) {
        this.f6453a = num;
    }
}
