package com.coveiot.android.bleabstract.response;

import java.util.ArrayList;
/* loaded from: classes2.dex */
public class BpHourData extends HourData {
    public int avgBp;
    public ArrayList<BpIntervalData> mCodedList;
    public int mSystolicBpPerHour;
    public int mdiastolicBpPerHour;

    public int getAvgBp() {
        return this.avgBp;
    }

    public int getMdiastolicBpPerHour() {
        return this.mdiastolicBpPerHour;
    }

    public ArrayList<BpIntervalData> getmCodedList() {
        return this.mCodedList;
    }

    public int getmSystolicBpPerHour() {
        return this.mSystolicBpPerHour;
    }

    public void setAvgBp(int i) {
        this.avgBp = i;
    }

    public void setMdiastolicBpPerHour(int i) {
        this.mdiastolicBpPerHour = i;
    }

    public void setmCodedList(ArrayList<BpIntervalData> arrayList) {
        this.mCodedList = arrayList;
    }

    public void setmSystolicBpPerHour(int i) {
        this.mSystolicBpPerHour = i;
    }
}
