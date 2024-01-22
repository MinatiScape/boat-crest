package com.coveiot.covepreferences.data;

import java.util.Date;
/* loaded from: classes8.dex */
public class LiveHeartRateModel {

    /* renamed from: a  reason: collision with root package name */
    public int f7031a;
    public Date b;

    public LiveHeartRateModel(int i, Date date) {
        this.f7031a = i;
        this.b = date;
    }

    public Date getDate() {
        return this.b;
    }

    public int getLiveHeartRate() {
        return this.f7031a;
    }

    public void setDate(Date date) {
        this.b = date;
    }

    public void setLiveHeartRate(int i) {
        this.f7031a = i;
    }
}
