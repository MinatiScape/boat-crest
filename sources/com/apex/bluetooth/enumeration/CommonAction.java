package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum CommonAction {
    no_action(0),
    one_long_vibration(1),
    one_short_vibration(2),
    two_long_vibration(3),
    two_short_vibration(4),
    long_vibration(5),
    long_short_vibration(6),
    one_ring(7),
    two_ring(8),
    ring(9),
    one_vibration_ring(10),
    vibration_ring(11);
    
    public int value;

    CommonAction(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
