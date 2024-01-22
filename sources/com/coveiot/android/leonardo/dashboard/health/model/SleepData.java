package com.coveiot.android.leonardo.dashboard.health.model;

import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes3.dex */
public class SleepData implements Serializable {
    public String awake;
    public String deep_sleep;
    public String dwmValue;
    public String light_sleep;
    public String rem_Sleep;
    public List<SleepDataModelForLastNight> sleepDataModelForLastNights;
    public String type;
    public int year;

    public SleepData(String str, String str2, String str3, String str4, String str5) {
        this.deep_sleep = str;
        this.light_sleep = str2;
        this.awake = str3;
        this.rem_Sleep = str4;
        this.dwmValue = str5;
    }

    public String getAwake() {
        return this.awake;
    }

    public String getDeep_sleep() {
        return this.deep_sleep;
    }

    public String getDwmValue() {
        return this.dwmValue;
    }

    public String getLight_sleep() {
        return this.light_sleep;
    }

    public String getRem_Sleep() {
        return this.rem_Sleep;
    }

    public List<SleepDataModelForLastNight> getSleepDataModelForLastNights() {
        return this.sleepDataModelForLastNights;
    }

    public String getType() {
        return this.type;
    }

    public int getYear() {
        return this.year;
    }

    public void setRem_sleep(String str) {
        this.rem_Sleep = str;
    }

    public void setSleepDataModelForLastNights(List<SleepDataModelForLastNight> list) {
        this.sleepDataModelForLastNights = list;
    }

    public SleepData setType(String str) {
        this.type = str;
        return this;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
