package com.coveiot.repository.datasync;

import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.model.FilteredSleepData;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class SleepSummaryData {

    /* renamed from: a  reason: collision with root package name */
    public String f7383a;
    public String b;
    public ArrayList<Integer> c;
    public ArrayList<Integer> d;
    public ArrayList<Integer> e;
    public ArrayList<Integer> f;
    public FilteredSleepData g;
    public SleepDataModel h;

    public ArrayList<Integer> getAwakeList() {
        return this.c;
    }

    public ArrayList<Integer> getDeepSleepList() {
        return this.d;
    }

    public String getEndTime() {
        return this.b;
    }

    public FilteredSleepData getFilteredSleepData() {
        return this.g;
    }

    public ArrayList<Integer> getLightSleepList() {
        return this.e;
    }

    public ArrayList<Integer> getRemSleepList() {
        return this.f;
    }

    public SleepDataModel getSleepDataModel() {
        return this.h;
    }

    public String getStartTime() {
        return this.f7383a;
    }

    public void setAwakeList(ArrayList<Integer> arrayList) {
        this.c = arrayList;
    }

    public void setDeepSleepList(ArrayList<Integer> arrayList) {
        this.d = arrayList;
    }

    public void setEndTime(String str) {
        this.b = str;
    }

    public void setFilteredSleepData(FilteredSleepData filteredSleepData) {
        this.g = filteredSleepData;
    }

    public void setLightSleepList(ArrayList<Integer> arrayList) {
        this.e = arrayList;
    }

    public void setRemSleepList(ArrayList<Integer> arrayList) {
        this.f = arrayList;
    }

    public void setSleepDataModel(SleepDataModel sleepDataModel) {
        this.h = sleepDataModel;
    }

    public void setStartTime(String str) {
        this.f7383a = str;
    }
}
