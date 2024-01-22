package com.coveiot.android.dashboard2.listener;

import com.coveiot.android.dashboard2.model.SyncInterruptionType;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public interface SyncInterruptionListener {
    void onInterrupt(@NotNull SyncInterruptionType syncInterruptionType);
}
