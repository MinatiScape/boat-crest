package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
/* loaded from: classes6.dex */
public final class f implements ListenerHolder.Notifier<BleScanCallback> {
    public f(zza zzaVar) {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(BleScanCallback bleScanCallback) {
        bleScanCallback.onScanStopped();
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
