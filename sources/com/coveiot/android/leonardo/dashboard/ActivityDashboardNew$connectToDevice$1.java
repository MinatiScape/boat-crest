package com.coveiot.android.leonardo.dashboard;

import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityDashboardNew$connectToDevice$1 implements ConnectionResultListener {
    @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
    public void onConnectionResponse(@NotNull ConnectionStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
    }

    @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
    }
}
