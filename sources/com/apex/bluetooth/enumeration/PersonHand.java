package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum PersonHand {
    left(0),
    right(1);
    
    public int value;

    PersonHand(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
