package com.coveiot.covedb.sleep.model;
/* loaded from: classes8.dex */
public class SleepDataModelMonthWiseCommon {

    /* renamed from: a  reason: collision with root package name */
    public String f6975a;
    public int b;
    public int c;
    public int d;
    public int e;

    public int getAverageMonthlyDeepSleep() {
        return this.b / this.e;
    }

    public int getAverageMonthlyLightSleep() {
        return this.c / this.e;
    }

    public int getAverageMonthlySleep() {
        return getAverageMonthlyDeepSleep() + getAverageMonthlyLightSleep();
    }

    public int getAwake() {
        return this.d;
    }

    public String getMonth() {
        return this.f6975a;
    }

    public int getRowCount() {
        return this.e;
    }

    public int getTotalDeepSleep() {
        return this.b;
    }

    public int getTotalLightSleep() {
        return this.c;
    }

    public void setAverageMonthlyDeepSleep(int i) {
    }

    public void setAverageMonthlyLightSleep(int i) {
    }

    public void setAverageMonthlySleep(int i) {
    }

    public void setAwake(int i) {
        this.d = i;
    }

    public void setMonth(String str) {
        this.f6975a = str;
    }

    public void setRowCount(int i) {
        this.e = i;
    }

    public void setTotalDeepSleep(int i) {
        this.b = i;
    }

    public void setTotalLightSleep(int i) {
        this.c = i;
    }
}
