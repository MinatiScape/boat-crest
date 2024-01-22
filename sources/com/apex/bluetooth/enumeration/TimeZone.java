package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum TimeZone {
    zero(0),
    east(1),
    west(2),
    unknown(3);
    
    public int value;

    TimeZone(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
