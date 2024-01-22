package com.coveiot.android.sportsnotification.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes7.dex */
public class Filters implements Serializable {
    @SerializedName("apiHitInterval")
    private Integer apiHitInterval = 15;
    @SerializedName("filterBy")
    private String filterBy;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;
    @SerializedName("options")
    private List<Options> options;
    @SerializedName("shouldFilterOriginalMatchList")
    private Boolean shouldFilterOriginalMatchList;

    public Integer getApiHitInterval() {
        return this.apiHitInterval;
    }

    public String getFilterBy() {
        return this.filterBy;
    }

    public String getName() {
        return this.name;
    }

    public List<Options> getOptions() {
        return this.options;
    }

    public Boolean getShouldFilterOriginalMatchList() {
        return this.shouldFilterOriginalMatchList;
    }

    public void setApiHitInterval(Integer num) {
        this.apiHitInterval = num;
    }

    public void setFilterBy(String str) {
        this.filterBy = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOptions(List<Options> list) {
        this.options = list;
    }

    public void setShouldFilterOriginalMatchList(Boolean bool) {
        this.shouldFilterOriginalMatchList = bool;
    }
}
