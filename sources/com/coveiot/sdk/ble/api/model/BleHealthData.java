package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.model.HealthDataType;
/* loaded from: classes9.dex */
public class BleHealthData {
    private HealthDataType healthDataType;
    private long timestamp;
    private int value;

    public BleHealthData(HealthDataType healthDataType, long j, int i) {
        this.healthDataType = healthDataType;
        this.timestamp = j;
        this.value = i;
    }

    public HealthDataType getHealthDataType() {
        return this.healthDataType;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public int getValue() {
        return this.value;
    }
}
