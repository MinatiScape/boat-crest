package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class Logs {
    @SerializedName("codedValues")
    private List<Double> codedValues;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName("startTime")
    private String startTime;

    public List<Double> getCodedValues() {
        return this.codedValues;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setCodedValues(List<Double> list) {
        this.codedValues = list;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }
}
