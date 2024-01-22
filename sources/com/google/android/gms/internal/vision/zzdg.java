package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public abstract class zzdg<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zzls = new Map.Entry[0];
    private transient zzdj<Map.Entry<K, V>> zzlt;
    private transient zzdj<K> zzlu;
    private transient zzdc<V> zzlv;

    public static <K, V> zzdg<K, V> zza(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        u.a(k, v);
        u.a(k2, v2);
        u.a(k3, v3);
        u.a(k4, v4);
        return b0.zza(4, new Object[]{k, v, k2, v2, k3, v3, k4, v4});
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        return ((zzdc) values()).contains(obj);
    }

    @Override // java.util.Map
    public /* synthetic */ Set entrySet() {
        zzdj<Map.Entry<K, V>> zzdjVar = this.zzlt;
        if (zzdjVar == null) {
            zzdj<Map.Entry<K, V>> zzce = zzce();
            this.zzlt = zzce;
            return zzce;
        }
        return zzdjVar;
    }

    @Override // java.util.Map
    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public abstract V get(@NullableDecl Object obj);

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    @Override // java.util.Map
    public int hashCode() {
        return zzdo.a((zzdj) entrySet());
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public /* synthetic */ Set keySet() {
        zzdj<K> zzdjVar = this.zzlu;
        if (zzdjVar == null) {
            zzdj<K> zzcf = zzcf();
            this.zzlu = zzcf;
            return zzcf;
        }
        return zzdjVar;
    }

    @Override // java.util.Map
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(size << 3, 1073741824L));
            sb.append('{');
            boolean z = true;
            for (Map.Entry<K, V> entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                z = false;
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            sb.append('}');
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder("size".length() + 40);
        sb2.append("size");
        sb2.append(" cannot be negative but was: ");
        sb2.append(size);
        throw new IllegalArgumentException(sb2.toString());
    }

    @Override // java.util.Map
    public /* synthetic */ Collection values() {
        zzdc<V> zzdcVar = this.zzlv;
        if (zzdcVar == null) {
            zzdc<V> zzcg = zzcg();
            this.zzlv = zzcg;
            return zzcg;
        }
        return zzdcVar;
    }

    public abstract zzdj<Map.Entry<K, V>> zzce();

    public abstract zzdj<K> zzcf();

    public abstract zzdc<V> zzcg();
}
