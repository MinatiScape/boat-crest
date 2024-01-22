package com.google.android.gms.fitness.request;

import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public final class zzan {
    public static final zzan b = new zzan();

    /* renamed from: a  reason: collision with root package name */
    public final Map<ListenerHolder.ListenerKey<OnDataPointListener>, zzam> f8463a = new HashMap();

    public static ListenerHolder<OnDataPointListener> a(OnDataPointListener onDataPointListener, Looper looper) {
        return ListenerHolders.createListenerHolder(onDataPointListener, looper, OnDataPointListener.class.getSimpleName());
    }

    public static zzan zzx() {
        return b;
    }

    public final zzam zza(OnDataPointListener onDataPointListener, Looper looper) {
        return zzc(a(onDataPointListener, looper));
    }

    @Nullable
    public final zzam zzb(OnDataPointListener onDataPointListener, Looper looper) {
        return zzd(a(onDataPointListener, looper));
    }

    public final zzam zzc(ListenerHolder<OnDataPointListener> listenerHolder) {
        zzam zzamVar;
        synchronized (this.f8463a) {
            ListenerHolder.ListenerKey<OnDataPointListener> listenerKey = (ListenerHolder.ListenerKey) Preconditions.checkNotNull(listenerHolder.getListenerKey(), "Key must not be null");
            zzamVar = this.f8463a.get(listenerKey);
            if (zzamVar == null) {
                zzamVar = new zzam(listenerHolder, null);
                this.f8463a.put(listenerKey, zzamVar);
            }
        }
        return zzamVar;
    }

    @Nullable
    public final zzam zzd(ListenerHolder<OnDataPointListener> listenerHolder) {
        synchronized (this.f8463a) {
            ListenerHolder.ListenerKey<OnDataPointListener> listenerKey = listenerHolder.getListenerKey();
            if (listenerKey == null) {
                return null;
            }
            zzam remove = this.f8463a.remove(listenerKey);
            if (remove != null) {
                remove.release();
            }
            return remove;
        }
    }
}
