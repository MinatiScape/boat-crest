package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPTodayWeatherInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f7671a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;

    public CRPTodayWeatherInfo() {
    }

    public CRPTodayWeatherInfo(String str, String str2, String str3, int i, int i2, int i3) {
        this.f7671a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        this.e = i2;
        this.f = i3;
    }

    public String getCity() {
        return this.f7671a;
    }

    public String getFestival() {
        return this.c;
    }

    public String getLunar() {
        return this.b;
    }

    public int getPm25() {
        return this.d;
    }

    public int getTemp() {
        return this.e;
    }

    public int getWeatherId() {
        return this.f;
    }

    public void setCity(String str) {
        this.f7671a = str;
    }

    public void setFestival(String str) {
        this.c = str;
    }

    public void setLunar(String str) {
        this.b = str;
    }

    public void setPm25(int i) {
        this.d = i;
    }

    public void setTemp(int i) {
        this.e = i;
    }

    public void setWeatherId(int i) {
        this.f = i;
    }
}
