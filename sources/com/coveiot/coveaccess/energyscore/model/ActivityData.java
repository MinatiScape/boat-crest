package com.coveiot.coveaccess.energyscore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class ActivityData {
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("fitnessActivitySessions")
    @Expose
    public List<FitnessActivitySession> fitnessActivitySessions;
    @SerializedName("fitnessData")
    @Expose
    public List<FitnessData> fitnessData;
    @SerializedName("sleepSummary")
    @Expose
    public SleepSummary sleepSummary;

    public String getDate() {
        return this.date;
    }

    public List<FitnessActivitySession> getFitnessActivitySessions() {
        return this.fitnessActivitySessions;
    }

    public List<FitnessData> getFitnessData() {
        return this.fitnessData;
    }

    public SleepSummary getSleepSummary() {
        return this.sleepSummary;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setFitnessActivitySessions(List<FitnessActivitySession> list) {
        this.fitnessActivitySessions = list;
    }

    public void setFitnessData(List<FitnessData> list) {
        this.fitnessData = list;
    }

    public void setSleepSummary(SleepSummary sleepSummary) {
        this.sleepSummary = sleepSummary;
    }
}
