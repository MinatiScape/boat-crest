package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.BleDevice;
/* loaded from: classes6.dex */
public final class h implements ListenerHolder.Notifier<BleScanCallback> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BleDevice f8460a;

    public h(zza zzaVar, BleDevice bleDevice) {
        this.f8460a = bleDevice;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(BleScanCallback bleScanCallback) {
        bleScanCallback.onDeviceFound(this.f8460a);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
