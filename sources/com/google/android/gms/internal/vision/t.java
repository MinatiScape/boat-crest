package com.google.android.gms.internal.vision;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class t<T> implements zzcu<T> {
    public volatile zzcu<T> h;
    public volatile boolean i;
    @NullableDecl
    public T j;

    public t(zzcu<T> zzcuVar) {
        this.h = (zzcu) zzct.checkNotNull(zzcuVar);
    }

    @Override // com.google.android.gms.internal.vision.zzcu
    public final T get() {
        if (!this.i) {
            synchronized (this) {
                if (!this.i) {
                    T t = this.h.get();
                    this.j = t;
                    this.i = true;
                    this.h = null;
                    return t;
                }
            }
        }
        return this.j;
    }

    public final String toString() {
        Object obj = this.h;
        if (obj == null) {
            String valueOf = String.valueOf(this.j);
            StringBuilder sb = new StringBuilder(valueOf.length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
