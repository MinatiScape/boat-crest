package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class LiveTemperatureData {
    public double temperature;
    public long unix;

    public LiveTemperatureData(double d, long j) {
        this.temperature = d;
        this.unix = j;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public long getUnix() {
        return this.unix;
    }
}
