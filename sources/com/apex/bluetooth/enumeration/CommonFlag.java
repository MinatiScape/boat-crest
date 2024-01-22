package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum CommonFlag {
    begin(0),
    proceed(1),
    end(2),
    begin_end(3);
    
    public int value;

    CommonFlag(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
