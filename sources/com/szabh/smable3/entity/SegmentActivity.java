package com.szabh.smable3.entity;
/* loaded from: classes12.dex */
public enum SegmentActivity {
    TIMER(0),
    RUN(1),
    JUMP_JACKS(2),
    PUSH_UP(3),
    DISTANCE(4),
    RUN_FAST(5),
    WALK(6),
    SWIM(7),
    BICYCLE(8),
    WORKOUT(9),
    REST(10),
    STRETCH(11),
    SPINNING(12),
    SIT_UP(15),
    WARM_UP(16),
    COOL_DOWN(17);
    
    private final int activity;

    SegmentActivity(int i) {
        this.activity = i;
    }

    public final int getActivity() {
        return this.activity;
    }
}
