package com.google.android.gms.internal.measurement;

import java.util.Map;
/* loaded from: classes8.dex */
public final class e4 implements Map.Entry, Comparable<e4> {
    public final Comparable h;
    public Object i;
    public final /* synthetic */ i4 j;

    public e4(i4 i4Var, Comparable comparable, Object obj) {
        this.j = i4Var;
        this.h = comparable;
        this.i = obj;
    }

    public static final boolean b(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public final Comparable a() {
        return this.h;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(e4 e4Var) {
        return this.h.compareTo(e4Var.h);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return b(this.h, entry.getKey()) && b(this.i, entry.getValue());
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final /* bridge */ /* synthetic */ Object getKey() {
        return this.h;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.i;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.h;
        int hashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.i;
        return hashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.j.o();
        Object obj2 = this.i;
        this.i = obj;
        return obj2;
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
