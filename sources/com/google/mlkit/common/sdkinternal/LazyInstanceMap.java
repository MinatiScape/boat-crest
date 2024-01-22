package com.google.mlkit.common.sdkinternal;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;
import java.util.Map;
@KeepForSdk
/* loaded from: classes10.dex */
public abstract class LazyInstanceMap<K, V> {
    @GuardedBy("instances")

    /* renamed from: a  reason: collision with root package name */
    public final Map f11587a = new HashMap();

    @NonNull
    @KeepForSdk
    public abstract V create(@NonNull K k);

    @NonNull
    @KeepForSdk
    public V get(@NonNull K k) {
        synchronized (this.f11587a) {
            if (this.f11587a.containsKey(k)) {
                return (V) this.f11587a.get(k);
            }
            V create = create(k);
            this.f11587a.put(k, create);
            return create;
        }
    }
}
