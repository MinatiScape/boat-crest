package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.zzu;
/* loaded from: classes6.dex */
public final class zzam extends zzu {

    /* renamed from: a  reason: collision with root package name */
    public final ListenerHolder<OnDataPointListener> f8462a;

    public zzam(ListenerHolder<OnDataPointListener> listenerHolder) {
        this.f8462a = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void release() {
        this.f8462a.clear();
    }

    @Override // com.google.android.gms.fitness.data.zzv
    public final void zzc(DataPoint dataPoint) {
        this.f8462a.notifyListener(new b(this, dataPoint));
    }

    public /* synthetic */ zzam(ListenerHolder listenerHolder, b bVar) {
        this(listenerHolder);
    }
}
