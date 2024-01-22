package com.coveiot.android.bleabstract.response;

import java.util.ArrayList;
/* loaded from: classes2.dex */
public class EcgRrHourData extends HourData {
    public ArrayList<EcgRrIntervalData> mCodedList;
    public int mEcgPerHour;
    public double mRrIntervalPerHour;

    public ArrayList<EcgRrIntervalData> getmCodedList() {
        return this.mCodedList;
    }

    public int getmEcgPerHour() {
        return this.mEcgPerHour;
    }

    public double getmRrIntervalPerHour() {
        return this.mRrIntervalPerHour;
    }

    public void setmCodedList(ArrayList<EcgRrIntervalData> arrayList) {
        this.mCodedList = arrayList;
    }

    public void setmEcgPerHour(int i) {
        this.mEcgPerHour = i;
    }

    public void setmRrIntervalPerHour(double d) {
        this.mRrIntervalPerHour = d;
    }
}
