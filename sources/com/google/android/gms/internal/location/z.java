package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes8.dex */
public final class z implements zzcs {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ListenerHolder f8897a;
    public final /* synthetic */ TaskCompletionSource b;

    public z(zzda zzdaVar, ListenerHolder listenerHolder, TaskCompletionSource taskCompletionSource) {
        this.f8897a = listenerHolder;
        this.b = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final ListenerHolder zza() {
        return this.f8897a;
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final void zzb() {
        this.b.trySetResult(null);
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final void zzc(ListenerHolder listenerHolder) {
        throw new IllegalStateException();
    }
}
