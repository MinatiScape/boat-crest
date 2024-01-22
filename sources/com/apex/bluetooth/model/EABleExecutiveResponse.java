package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public enum EABleExecutiveResponse {
    execute(0),
    success(1),
    fail(2);
    
    public int value;

    EABleExecutiveResponse(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
