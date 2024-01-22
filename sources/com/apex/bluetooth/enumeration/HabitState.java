package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum HabitState {
    initial(0),
    in_progress(1),
    completed(2),
    cancel(3);
    
    public int value;

    HabitState(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
