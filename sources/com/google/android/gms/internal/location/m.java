package com.google.android.gms.internal.location;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.api.internal.ListenerHolder;
/* loaded from: classes8.dex */
public final class m implements zzcs {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public ListenerHolder f8891a;

    public m(ListenerHolder listenerHolder) {
        this.f8891a = listenerHolder;
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final synchronized ListenerHolder zza() {
        return this.f8891a;
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final void zzb() {
    }

    @Override // com.google.android.gms.internal.location.zzcs
    public final synchronized void zzc(ListenerHolder listenerHolder) {
        ListenerHolder listenerHolder2 = this.f8891a;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            this.f8891a = listenerHolder;
        }
    }
}
