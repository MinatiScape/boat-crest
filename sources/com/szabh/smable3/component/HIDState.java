package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class HIDState {
    public static final int CONNECTED = 1;
    public static final int DISCONNECTED = 0;
    @NotNull
    public static final HIDState INSTANCE = new HIDState();

    private HIDState() {
    }

    @NotNull
    public final String getState(int i) {
        return i != 0 ? i != 1 ? "Unknown" : "Connected" : "Disconnected";
    }
}
