package com.google.android.gms.internal.measurement;

import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes8.dex */
public final class c2<T> implements zzib<T> {
    @CheckForNull
    public volatile zzib<T> h;
    public volatile boolean i;
    @CheckForNull
    public T j;

    public c2(zzib<T> zzibVar) {
        Objects.requireNonNull(zzibVar);
        this.h = zzibVar;
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

    @Override // com.google.android.gms.internal.measurement.zzib
    public final T zza() {
        if (!this.i) {
            synchronized (this) {
                if (!this.i) {
                    zzib<T> zzibVar = this.h;
                    zzibVar.getClass();
                    T zza = zzibVar.zza();
                    this.j = zza;
                    this.i = true;
                    this.h = null;
                    return zza;
                }
            }
        }
        return this.j;
    }
}
