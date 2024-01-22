package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPO2FitnessSessionDataBean {
    @SerializedName("baseUnit")
    private String baseUnit;
    @SerializedName("clientRefId")
    private String clientRefId;
    @SerializedName("sessionEndDate")
    private String sessionEndDate;
    @SerializedName("sessionStartDate")
    private String sessionStartDate;
    @SerializedName("totalSampleCount")
    private int totalSampleCount;
    @SerializedName("type")
    private String type;
    @SerializedName("tzOffset")
    private String tzOffset;
    @SerializedName("value")
    private double value;

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public String getClientRefId() {
        return this.clientRefId;
    }

    public String getSessionEndDate() {
        return this.sessionEndDate;
    }

    public String getSessionStartDate() {
        return this.sessionStartDate;
    }

    public int getTotalSampleCount() {
        return this.totalSampleCount;
    }

    public String getType() {
        return this.type;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public double getValue() {
        return this.value;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setClientRefId(String str) {
        this.clientRefId = str;
    }

    public void setSessionEndDate(String str) {
        this.sessionEndDate = str;
    }

    public void setSessionStartDate(String str) {
        this.sessionStartDate = str;
    }

    public void setTotalSampleCount(int i) {
        this.totalSampleCount = i;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }

    public void setValue(double d) {
        this.value = d;
    }
}
