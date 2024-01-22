package com.google.android.gms.internal.clearcut;

import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX WARN: Incorrect field signature: TK; */
/* loaded from: classes7.dex */
public final class b2<K, V> implements Comparable<b2>, Map.Entry<K, V> {
    public final Comparable h;
    public V i;
    public final /* synthetic */ u1 j;

    /* JADX WARN: Multi-variable type inference failed */
    public b2(u1 u1Var, K k, V v) {
        this.j = u1Var;
        this.h = k;
        this.i = v;
    }

    public b2(u1 u1Var, Map.Entry<K, V> entry) {
        this(u1Var, (Comparable) entry.getKey(), entry.getValue());
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(b2 b2Var) {
        return ((Comparable) getKey()).compareTo((Comparable) b2Var.getKey());
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return a(this.h, entry.getKey()) && a(this.i, entry.getValue());
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.h;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.i;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.h;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        V v = this.i;
        return hashCode ^ (v != null ? v.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        this.j.p();
        V v2 = this.i;
        this.i = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.h);
        String valueOf2 = String.valueOf(this.i);
        StringBuilder sb = new StringBuilder(valueOf.length() + 1 + valueOf2.length());
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        return sb.toString();
    }
}
