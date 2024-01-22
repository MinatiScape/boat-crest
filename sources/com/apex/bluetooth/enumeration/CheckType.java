package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum CheckType {
    default_type(0),
    hr(1),
    stress(2),
    blood_oxygen(3),
    breathe(4);
    
    public int value;

    CheckType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
