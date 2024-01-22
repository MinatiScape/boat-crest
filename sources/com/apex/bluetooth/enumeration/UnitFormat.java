package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum UnitFormat {
    metric(0),
    british(1);
    
    public int value;

    UnitFormat(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
