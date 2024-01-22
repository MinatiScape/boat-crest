package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.GuardedBy;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public abstract class zzps<K, V> {
    @GuardedBy("instances")

    /* renamed from: a  reason: collision with root package name */
    public final Map<K, V> f8790a = new HashMap();

    public abstract V create(K k);

    public final V get(K k) {
        synchronized (this.f8790a) {
            if (this.f8790a.containsKey(k)) {
                return this.f8790a.get(k);
            }
            V create = create(k);
            this.f8790a.put(k, create);
            return create;
        }
    }
}
