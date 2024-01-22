package com.coveiot.android.navigation.network;

import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface NetworkMonitor {

    /* loaded from: classes5.dex */
    public enum Status {
        Available,
        Unavailable,
        Losing,
        Lost
    }

    @NotNull
    Flow<Status> observeNetworkStatus();
}
