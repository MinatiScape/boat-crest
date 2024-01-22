package com.coveiot.sdk.ble.model;

import java.util.List;
/* loaded from: classes9.dex */
public class TimeLogBeanTemperature {
    public List<TemperatureHourlyData> logs;

    public List<TemperatureHourlyData> getLogBeans() {
        return this.logs;
    }

    public void setLogBeans(List<TemperatureHourlyData> list) {
        this.logs = list;
    }
}
