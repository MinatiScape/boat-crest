package com.coveiot.coveaccess.model.server;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class LogsBean {
    @SerializedName("avg")
    private Integer avg;
    @SerializedName("codedValues")
    private List<Integer> codedValues;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName(Constants.PRIORITY_MAX)
    private Integer max;
    @SerializedName("min")
    private Integer min;
    @SerializedName("startTime")
    private String startTime;

    public Integer getAvg() {
        return this.avg;
    }

    public List<Integer> getCodedValues() {
        return this.codedValues;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public Integer getMax() {
        return this.max;
    }

    public Integer getMin() {
        return this.min;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setAvg(Integer num) {
        this.avg = num;
    }

    public void setCodedValues(List<Integer> list) {
        this.codedValues = list;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void setMax(Integer num) {
        this.max = num;
    }

    public void setMin(Integer num) {
        this.min = num;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String toString() {
        return "LogsBean{startTime='" + this.startTime + "', endTime='" + this.endTime + "', min=" + this.min + ", avg=" + this.avg + ", max=" + this.max + ", codedValues=" + this.codedValues + '}';
    }
}
