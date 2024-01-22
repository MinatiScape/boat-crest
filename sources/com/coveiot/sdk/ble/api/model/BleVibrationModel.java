package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BleVibrationModel {
    private int duration;
    private int strength;
    private int type;

    public BleVibrationModel(int i, int i2, int i3) {
        this.type = i;
        this.strength = i2;
        this.duration = i3;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getType() {
        return this.type;
    }
}
