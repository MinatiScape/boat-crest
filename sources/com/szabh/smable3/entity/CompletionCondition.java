package com.szabh.smable3.entity;
/* loaded from: classes12.dex */
public enum CompletionCondition {
    DURATION(0),
    MANUAL(1),
    DURATION_IN_HR_ZONE(2),
    HR_ABOVE(3),
    HR_BELOW(4);
    
    private final int condition;

    CompletionCondition(int i) {
        this.condition = i;
    }

    public final int getCondition() {
        return this.condition;
    }
}
