package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.HashMap;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {
    public HashMap<K, SafeIterableMap.c<K, V>> l = new HashMap<>();

    public Map.Entry<K, V> ceil(K k) {
        if (contains(k)) {
            return this.l.get(k).k;
        }
        return null;
    }

    public boolean contains(K k) {
        return this.l.containsKey(k);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public SafeIterableMap.c<K, V> get(K k) {
        return this.l.get(k);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        SafeIterableMap.c<K, V> cVar = get(k);
        if (cVar != null) {
            return cVar.i;
        }
        this.l.put(k, put(k, v));
        return null;
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public V remove(@NonNull K k) {
        V v = (V) super.remove(k);
        this.l.remove(k);
        return v;
    }
}
