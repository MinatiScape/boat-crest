package com.coveiot.android.bleabstract.response;

import java.util.List;
/* loaded from: classes2.dex */
public class SleepTimeLogBeanData {

    /* renamed from: a  reason: collision with root package name */
    public List<SleepHourData> f3660a;

    public List<SleepHourData> getLogBeans() {
        return this.f3660a;
    }

    public void setLogBeans(List<SleepHourData> list) {
        this.f3660a = list;
    }

    public String toString() {
        return "SleepTimeLogBeanData{logs=" + this.f3660a + '}';
    }
}
