package com.coveiot.android.leonardo.dashboard.health.spo2;
/* loaded from: classes3.dex */
public enum SPO2Level {
    NORMAL(90),
    LOW(60),
    VERY_LOW(30);
    
    public int value;

    SPO2Level(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
