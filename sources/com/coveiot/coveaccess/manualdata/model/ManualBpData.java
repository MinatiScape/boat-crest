package com.coveiot.coveaccess.manualdata.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class ManualBpData {
    @SerializedName("clientRefId")

    /* renamed from: a  reason: collision with root package name */
    private String f6650a;
    @SerializedName("tzOffset")
    private String b;
    @SerializedName("baseUnit")
    private String c;
    @SerializedName("sessionStartDate")
    private String d;
    @SerializedName("sessionEndDate")
    private String e;
    @SerializedName("totalSampleCount")
    private int f;
    @SerializedName("value")
    private List<Integer> g;

    public String getBaseUnit() {
        return this.c;
    }

    public String getClientRefId() {
        return this.f6650a;
    }

    public String getSessionEndDate() {
        return this.e;
    }

    public String getSessionStartDate() {
        return this.d;
    }

    public int getTotalSampleCount() {
        return this.f;
    }

    public String getTzOffset() {
        return this.b;
    }

    public List<Integer> getValue() {
        return this.g;
    }

    public void setBaseUnit(String str) {
        this.c = str;
    }

    public void setClientRefId(String str) {
        this.f6650a = str;
    }

    public void setSessionEndDate(String str) {
        this.e = str;
    }

    public void setSessionStartDate(String str) {
        this.d = str;
    }

    public void setTotalSampleCount(int i) {
        this.f = i;
    }

    public void setTzOffset(String str) {
        this.b = str;
    }

    public void setValue(List<Integer> list) {
        this.g = list;
    }
}
