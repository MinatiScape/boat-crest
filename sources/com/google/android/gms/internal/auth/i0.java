package com.google.android.gms.internal.auth;

import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public final class i0 implements zzdj {
    @CheckForNull
    public volatile zzdj h;
    public volatile boolean i;
    @CheckForNull
    public Object j;

    public i0(zzdj zzdjVar) {
        Objects.requireNonNull(zzdjVar);
        this.h = zzdjVar;
    }

    public final String toString() {
        Object obj = this.h;
        StringBuilder sb = new StringBuilder();
        sb.append("Suppliers.memoize(");
        if (obj == null) {
            obj = "<supplier that returned " + this.j + ">";
        }
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.auth.zzdj
    public final Object zza() {
        if (!this.i) {
            synchronized (this) {
                if (!this.i) {
                    zzdj zzdjVar = this.h;
                    zzdjVar.getClass();
                    Object zza = zzdjVar.zza();
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
