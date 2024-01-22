package com.szabh.smable3.entity;
/* loaded from: classes12.dex */
public enum Stage {
    WARM_UP(0),
    GO_FOR(1),
    RECOVERY(2),
    REST_FOR(3),
    COOL_DOWN(4),
    OTHER(255);
    
    private final int stage;

    Stage(int i) {
        this.stage = i;
    }

    public final int getStage() {
        return this.stage;
    }
}
