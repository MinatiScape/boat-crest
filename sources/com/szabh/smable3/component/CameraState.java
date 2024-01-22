package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class CameraState {
    public static final int CAPTURE = 2;
    public static final int ENTER = 1;
    public static final int EXIT = 0;
    @NotNull
    public static final CameraState INSTANCE = new CameraState();

    private CameraState() {
    }

    @NotNull
    public final String getState(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "Unknown" : "Capture" : "Enter" : "Exit";
    }
}
