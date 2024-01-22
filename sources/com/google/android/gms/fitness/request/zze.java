package com.google.android.gms.fitness.request;

import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public final class zze {
    public static final zze b = new zze();

    /* renamed from: a  reason: collision with root package name */
    public final Map<ListenerHolder.ListenerKey<BleScanCallback>, zza> f8464a = new HashMap();

    public static ListenerHolder<BleScanCallback> a(BleScanCallback bleScanCallback, Looper looper) {
        return ListenerHolders.createListenerHolder(bleScanCallback, looper, BleScanCallback.class.getSimpleName());
    }

    public static zze zzu() {
        return b;
    }

    public final zza zza(BleScanCallback bleScanCallback, Looper looper) {
        return zza(a(bleScanCallback, looper));
    }

    @Nullable
    public final zza zzb(BleScanCallback bleScanCallback, Looper looper) {
        return zzb(a(bleScanCallback, looper));
    }

    public final zza zza(ListenerHolder<BleScanCallback> listenerHolder) {
        zza zzaVar;
        synchronized (this.f8464a) {
            ListenerHolder.ListenerKey<BleScanCallback> listenerKey = (ListenerHolder.ListenerKey) Preconditions.checkNotNull(listenerHolder.getListenerKey(), "Key must not be null");
            zzaVar = this.f8464a.get(listenerKey);
            if (zzaVar == null) {
                zzaVar = new zza(listenerHolder, null);
                this.f8464a.put(listenerKey, zzaVar);
            }
        }
        return zzaVar;
    }

    @Nullable
    public final zza zzb(ListenerHolder<BleScanCallback> listenerHolder) {
        synchronized (this.f8464a) {
            ListenerHolder.ListenerKey<BleScanCallback> listenerKey = listenerHolder.getListenerKey();
            if (listenerKey == null) {
                return null;
            }
            zza zzaVar = this.f8464a.get(listenerKey);
            if (zzaVar != null) {
                zzaVar.release();
            }
            return zzaVar;
        }
    }
}
