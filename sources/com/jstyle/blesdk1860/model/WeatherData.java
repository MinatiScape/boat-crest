package com.jstyle.blesdk1860.model;
/* loaded from: classes11.dex */
public class WeatherData {

    /* renamed from: a  reason: collision with root package name */
    public int f12533a;
    public int b;
    public int c;
    public String d;
    public int e;

    public String getCityName() {
        return this.d;
    }

    public int getTempHigh() {
        return this.b;
    }

    public int getTempLow() {
        return this.c;
    }

    public int getTempNow() {
        return this.f12533a;
    }

    public int getWeatherId() {
        return this.e;
    }

    public void setCityName(String str) {
        this.d = str;
    }

    public void setTempHigh(int i) {
        this.b = i;
    }

    public void setTempLow(int i) {
        this.c = i;
    }

    public void setTempNow(int i) {
        this.f12533a = i;
    }

    public void setWeatherId(int i) {
        this.e = i;
    }
}
