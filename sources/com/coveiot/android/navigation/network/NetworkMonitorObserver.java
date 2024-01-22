package com.coveiot.android.navigation.network;

import android.content.Context;
import android.net.ConnectivityManager;
import com.coveiot.android.navigation.network.NetworkMonitor;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class NetworkMonitorObserver implements NetworkMonitor {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5520a;
    @NotNull
    public final ConnectivityManager b;

    public NetworkMonitorObserver(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5520a = context;
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.b = (ConnectivityManager) systemService;
    }

    @NotNull
    public final Context getContext() {
        return this.f5520a;
    }

    @Override // com.coveiot.android.navigation.network.NetworkMonitor
    @NotNull
    public Flow<NetworkMonitor.Status> observeNetworkStatus() {
        return FlowKt.distinctUntilChanged(FlowKt.flowOn(FlowKt.callbackFlow(new NetworkMonitorObserver$observeNetworkStatus$1(this, null)), Dispatchers.getIO()));
    }
}
