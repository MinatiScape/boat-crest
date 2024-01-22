package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ClassicBluetoothState {
    public static final int CLOSE = 0;
    public static final int CLOSE_SUCCESSFULLY = 2;
    @NotNull
    public static final ClassicBluetoothState INSTANCE = new ClassicBluetoothState();
    public static final int OPEN = 1;
    public static final int OPEN_SUCCESSFULLY = 3;

    private ClassicBluetoothState() {
    }

    @NotNull
    public final String getState(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "Unknown" : "Open Successfully" : "Close Successfully" : "Open" : "Close";
    }
}
