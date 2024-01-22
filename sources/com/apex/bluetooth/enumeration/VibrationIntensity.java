package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum VibrationIntensity {
    light(0),
    medium(1),
    strong(2),
    not_vibrate(3);
    
    public int value;

    VibrationIntensity(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
