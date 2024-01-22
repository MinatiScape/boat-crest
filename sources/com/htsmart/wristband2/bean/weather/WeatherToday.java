package com.htsmart.wristband2.bean.weather;
/* loaded from: classes11.dex */
public class WeatherToday extends WeatherForecast {
    public int d;
    public int e;
    public int f;
    public int g;

    public int getCurrentTemperature() {
        return this.d;
    }

    public int getPressure() {
        return this.e;
    }

    public int getVisibility() {
        return this.g;
    }

    public int getWindForce() {
        return this.f;
    }

    public void setCurrentTemperature(int i) {
        this.d = i;
    }

    public void setPressure(int i) {
        this.e = i;
    }

    public void setVisibility(int i) {
        this.g = i;
    }

    public void setWindForce(int i) {
        this.f = i;
    }
}
