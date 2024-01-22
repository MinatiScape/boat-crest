package com.coveiot.android.leonardo.dashboard.health.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class SPO2Data implements Serializable {
    public Double lastMeasuredValue;
    public Long timestamp;
    public String type;

    public SPO2Data(Double d, Long l) {
        this.lastMeasuredValue = d;
        this.timestamp = l;
    }

    public Double getLastMeasuredValue() {
        return this.lastMeasuredValue;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public String getType() {
        return this.type;
    }

    public void setLastMeasuredValue(Double d) {
        this.lastMeasuredValue = d;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public SPO2Data setType(String str) {
        this.type = str;
        return this;
    }
}
