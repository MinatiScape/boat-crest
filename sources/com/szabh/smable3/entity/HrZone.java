package com.szabh.smable3.entity;
/* loaded from: classes12.dex */
public enum HrZone {
    LOW(1),
    NORMAL(2),
    MODERATE(3),
    HARD(4),
    MAXIMUM(5);
    
    private final int zone;

    HrZone(int i) {
        this.zone = i;
    }

    public final int getZone() {
        return this.zone;
    }
}
