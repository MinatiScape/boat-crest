package com.coveiot.coveaccess.fitness.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class MensSettings {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private Boolean active;
    @SerializedName("cycleLength")
    private Integer cycleLength;
    @SerializedName("cycleStartDate")
    private String cycleStartDate;
    @SerializedName("periodLength")
    private Integer periodLength;
    @SerializedName("pmsLength")
    private Integer pmsLength;

    public Boolean getActive() {
        return this.active;
    }

    public Integer getCycleLength() {
        return this.cycleLength;
    }

    public String getCycleStartDate() {
        return this.cycleStartDate;
    }

    public Integer getPeriodLength() {
        return this.periodLength;
    }

    public Integer getPmsLength() {
        return this.pmsLength;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public void setCycleLength(Integer num) {
        this.cycleLength = num;
    }

    public void setCycleStartDate(String str) {
        this.cycleStartDate = str;
    }

    public void setPeriodLength(Integer num) {
        this.periodLength = num;
    }

    public void setPmsLength(Integer num) {
        this.pmsLength = num;
    }
}
