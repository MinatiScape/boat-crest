package com.mappls.sdk.services.api.traffic.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class TrafficRoadDetailResponse {
    @SerializedName("creationTime")
    @Expose
    private String creationTime;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("result")
    @Expose
    private TrafficRoadDetailResult result;

    public String getCreationTime() {
        return this.creationTime;
    }

    public Integer getResponseCode() {
        return this.responseCode;
    }

    public TrafficRoadDetailResult getResult() {
        return this.result;
    }

    public void setCreationTime(String str) {
        this.creationTime = str;
    }

    public void setResponseCode(Integer num) {
        this.responseCode = num;
    }

    public void setResult(TrafficRoadDetailResult trafficRoadDetailResult) {
        this.result = trafficRoadDetailResult;
    }
}
