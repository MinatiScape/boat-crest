package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BleWatchDiagnosticFeature {
    private int buttonPosition;
    private int color;
    private BleWatchDiagnosticFeatureType mType;
    private int sensorType;
    private int timeout;
    private int vibrationCount;

    public BleWatchDiagnosticFeature(BleWatchDiagnosticFeatureType bleWatchDiagnosticFeatureType) {
        this.mType = bleWatchDiagnosticFeatureType;
    }

    public int getButtonPosition() {
        return this.buttonPosition;
    }

    public int getColor() {
        return this.color;
    }

    public int getSensorType() {
        return this.sensorType;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public int getVibrationCount() {
        return this.vibrationCount;
    }

    public BleWatchDiagnosticFeatureType getmFeatureType() {
        return this.mType;
    }

    public void setButtonPosition(int i) {
        this.buttonPosition = i;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public void setSensorType(int i) {
        this.sensorType = i;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public void setVibrationCount(int i) {
        this.vibrationCount = i;
    }
}
