package com.coveiot.coveaccess.manualdata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
/* loaded from: classes8.dex */
public class ManualMixedData {
    @SerializedName("clientRefId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6651a;
    @SerializedName("type")
    @Expose
    private String b;
    @SerializedName("tzOffset")
    @Expose
    private String c;
    @SerializedName("sessionStartDate")
    @Expose
    private String d;
    @SerializedName("sessionEndDate")
    @Expose
    private String e;
    @SerializedName("totalSampleCount")
    @Expose
    private Integer f;
    @SerializedName("hr")
    @Expose
    private Integer g;
    @SerializedName(DeviceKey.HRV)
    @Expose
    private Integer h;
    @SerializedName("bp")
    @Expose
    private List<Integer> i = null;
    @SerializedName("spo2")
    @Expose
    private Integer j;
    @SerializedName("stressLevel")
    @Expose
    private Integer k;
    @SerializedName("vascAging")
    @Expose
    private Integer l;
    @SerializedName("baseUnits")
    @Expose
    private BaseUnitsManual m;

    public BaseUnitsManual getBaseUnits() {
        return this.m;
    }

    public List<Integer> getBp() {
        return this.i;
    }

    public String getClientRefId() {
        return this.f6651a;
    }

    public Integer getHr() {
        return this.g;
    }

    public Integer getHrv() {
        return this.h;
    }

    public String getSessionEndDate() {
        return this.e;
    }

    public String getSessionStartDate() {
        return this.d;
    }

    public Integer getSpo2() {
        return this.j;
    }

    public Integer getStressLevel() {
        return this.k;
    }

    public Integer getTotalSampleCount() {
        return this.f;
    }

    public String getType() {
        return this.b;
    }

    public String getTzOffset() {
        return this.c;
    }

    public Integer getVascAging() {
        return this.l;
    }

    public void setBaseUnits(BaseUnitsManual baseUnitsManual) {
        this.m = baseUnitsManual;
    }

    public void setBp(List<Integer> list) {
        this.i = list;
    }

    public void setClientRefId(String str) {
        this.f6651a = str;
    }

    public void setHr(Integer num) {
        this.g = num;
    }

    public void setHrv(Integer num) {
        this.h = num;
    }

    public void setSessionEndDate(String str) {
        this.e = str;
    }

    public void setSessionStartDate(String str) {
        this.d = str;
    }

    public void setSpo2(Integer num) {
        this.j = num;
    }

    public void setStressLevel(Integer num) {
        this.k = num;
    }

    public void setTotalSampleCount(Integer num) {
        this.f = num;
    }

    public void setType(String str) {
        this.b = str;
    }

    public void setTzOffset(String str) {
        this.c = str;
    }

    public void setVascAging(Integer num) {
        this.l = num;
    }
}
