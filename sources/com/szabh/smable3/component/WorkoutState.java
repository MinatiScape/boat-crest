package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class WorkoutState {
    public static final int END = 4;
    @NotNull
    public static final WorkoutState INSTANCE = new WorkoutState();
    public static final int ONGOING = 2;
    public static final int PAUSE = 3;
    public static final int START = 1;

    private WorkoutState() {
    }

    @NotNull
    public final String getState(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Unknown" : "End" : "Pause" : "Ongoing" : "Start";
    }
}
