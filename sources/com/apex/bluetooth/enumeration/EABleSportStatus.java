package com.apex.bluetooth.enumeration;
/* loaded from: classes.dex */
public enum EABleSportStatus {
    close(0),
    start(1),
    pause(2),
    processed(3);
    
    public int value;

    EABleSportStatus(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
