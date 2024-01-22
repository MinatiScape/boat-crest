package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.BleDevice;
/* loaded from: classes6.dex */
public final class zza extends zzag {

    /* renamed from: a  reason: collision with root package name */
    public final ListenerHolder<BleScanCallback> f8461a;

    public zza(ListenerHolder<BleScanCallback> listenerHolder) {
        this.f8461a = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    @Override // com.google.android.gms.fitness.request.zzad
    public final void onDeviceFound(BleDevice bleDevice) {
        this.f8461a.notifyListener(new h(this, bleDevice));
    }

    @Override // com.google.android.gms.fitness.request.zzad
    public final void onScanStopped() {
        this.f8461a.notifyListener(new f(this));
    }

    public final void release() {
        this.f8461a.clear();
    }

    public /* synthetic */ zza(ListenerHolder listenerHolder, h hVar) {
        this(listenerHolder);
    }
}
