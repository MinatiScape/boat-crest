package com.coveiot.coveaccess.ecgsession.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessSessionDatum {
    @SerializedName("clientRefId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6483a;
    @SerializedName("type")
    @Expose
    private String b;
    @SerializedName("tzOffset")
    @Expose
    private String c;
    @SerializedName("baseUnit")
    @Expose
    private String d;
    @SerializedName("sessionStartDate")
    @Expose
    private String e;
    @SerializedName("sessionEndDate")
    @Expose
    private String f;
    @SerializedName("totalSampleCount")
    @Expose
    private Integer g;
    @SerializedName("ecgGraph")
    @Expose
    private EcgGraph h;
    @SerializedName("stressLevel")
    @Expose
    private Integer i;
    @SerializedName(DeviceKey.HRV)
    @Expose
    private Integer j;
    @SerializedName("rriValues")
    @Expose
    private List<Integer> k = null;
    @SerializedName("hrValues")
    @Expose
    private List<Integer> l = null;
    @SerializedName("bpValues")
    @Expose
    private List<List<Integer>> m = null;
    @SerializedName("timeLog")
    @Expose
    private TimeLog n;

    public String getBaseUnit() {
        return this.d;
    }

    public List<List<Integer>> getBpValues() {
        return this.m;
    }

    public String getClientRefId() {
        return this.f6483a;
    }

    public EcgGraph getEcgGraph() {
        return this.h;
    }

    public List<Integer> getHrValues() {
        return this.l;
    }

    public Integer getHrv() {
        return this.j;
    }

    public List<Integer> getRriValues() {
        return this.k;
    }

    public String getSessionEndDate() {
        return this.f;
    }

    public String getSessionStartDate() {
        return this.e;
    }

    public Integer getStressLevel() {
        return this.i;
    }

    public TimeLog getTimeLog() {
        return this.n;
    }

    public Integer getTotalSampleCount() {
        return this.g;
    }

    public String getType() {
        return this.b;
    }

    public String getTzOffset() {
        return this.c;
    }

    public void setBaseUnit(String str) {
        this.d = str;
    }

    public void setBpValues(List<List<Integer>> list) {
        this.m = list;
    }

    public void setClientRefId(String str) {
        this.f6483a = str;
    }

    public void setEcgGraph(EcgGraph ecgGraph) {
        this.h = ecgGraph;
    }

    public void setHrValues(List<Integer> list) {
        this.l = list;
    }

    public void setHrv(Integer num) {
        this.j = num;
    }

    public void setRriValues(List<Integer> list) {
        this.k = list;
    }

    public void setSessionEndDate(String str) {
        this.f = str;
    }

    public void setSessionStartDate(String str) {
        this.e = str;
    }

    public void setStressLevel(Integer num) {
        this.i = num;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.n = timeLog;
    }

    public void setTotalSampleCount(Integer num) {
        this.g = num;
    }

    public void setType(String str) {
        this.b = str;
    }

    public void setTzOffset(String str) {
        this.c = str;
    }
}
