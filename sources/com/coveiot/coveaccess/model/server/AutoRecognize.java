package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class AutoRecognize {
    @SerializedName("activities")
    private List<AutoRecognizeActivity> activities = new ArrayList();
    @SerializedName("schedule")
    private AutoRecognizeSchedule schedule = null;
    @SerializedName("weekDays")
    private String weekDays;

    public List<AutoRecognizeActivity> getActivities() {
        return this.activities;
    }

    public AutoRecognizeSchedule getSchedule() {
        return this.schedule;
    }

    public String getWeekDays() {
        return this.weekDays;
    }

    public void setActivities(List<AutoRecognizeActivity> list) {
        this.activities = list;
    }

    public void setSchedule(AutoRecognizeSchedule autoRecognizeSchedule) {
        this.schedule = autoRecognizeSchedule;
    }

    public void setWeekDays(String str) {
        this.weekDays = str;
    }
}
