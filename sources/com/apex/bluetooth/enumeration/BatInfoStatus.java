package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum BatInfoStatus {
    normal(0),
    in_charging(1);
    
    public int value;

    BatInfoStatus(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
