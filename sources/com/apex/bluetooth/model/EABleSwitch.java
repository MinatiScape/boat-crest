package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public enum EABleSwitch {
    off(0),
    on(1);
    
    public int value;

    EABleSwitch(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
