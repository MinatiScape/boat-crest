package com.coveiot.sdk.ble.model;

import java.util.ArrayList;
/* loaded from: classes9.dex */
public class EcgRrHourlyData extends HourlyData {
    public ArrayList<EcgRrMinuteData> mCodedList;
    public int mEcgPerHour;
    public double mRrIntervalPerHour;

    public ArrayList<EcgRrMinuteData> getmCodedList() {
        return this.mCodedList;
    }

    public int getmEcgPerHour() {
        return this.mEcgPerHour;
    }

    public double getmRrIntervalPerHour() {
        return this.mRrIntervalPerHour;
    }

    public void setmCodedList(ArrayList<EcgRrMinuteData> arrayList) {
        this.mCodedList = arrayList;
    }

    public void setmEcgPerHour(int i) {
        this.mEcgPerHour = i;
    }

    public void setmRrIntervalPerHour(double d) {
        this.mRrIntervalPerHour = d;
    }
}
