package com.coveiot.android.dashboard2.model.state;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class SyncState implements State {
    private boolean isSyncing;
    private int progress;
    private int progressByStage;
    @NotNull
    private String message = "";
    private long lastDataSyncTimestamp = -1;

    public final long getLastDataSyncTimestamp() {
        return this.lastDataSyncTimestamp;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final int getProgressByStage() {
        return this.progressByStage;
    }

    public final boolean isSyncing() {
        return this.isSyncing;
    }

    public final void setLastDataSyncTimestamp(long j) {
        this.lastDataSyncTimestamp = j;
    }

    public final void setMessage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public final void setProgress(int i) {
        this.progress = i;
    }

    public final void setProgressByStage(int i) {
        this.progressByStage = i;
    }

    public final void setSyncing(boolean z) {
        this.isSyncing = z;
    }
}
