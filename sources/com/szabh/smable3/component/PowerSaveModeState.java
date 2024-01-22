package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class PowerSaveModeState {
    public static final int CLOSE = 0;
    @NotNull
    public static final PowerSaveModeState INSTANCE = new PowerSaveModeState();
    public static final int OPEN = 1;

    private PowerSaveModeState() {
    }

    @NotNull
    public final String getState(int i) {
        return i != 0 ? i != 1 ? "Unknown" : "Open" : "Close";
    }
}
