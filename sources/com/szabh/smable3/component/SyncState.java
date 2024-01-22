package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class SyncState {
    public static final int COMPLETED = 0;
    public static final int DISCONNECTED = -2;
    @NotNull
    public static final SyncState INSTANCE = new SyncState();
    public static final int SYNCING = 1;
    public static final int TIMEOUT = -1;

    private SyncState() {
    }

    @NotNull
    public final String getState(int i) {
        return i != -2 ? i != -1 ? i != 0 ? i != 1 ? "Unknown" : "Syncing" : "completed" : "Timeout" : "Disconnected";
    }
}
