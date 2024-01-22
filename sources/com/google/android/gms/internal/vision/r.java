package com.google.android.gms.internal.vision;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class r<T> implements zzcu<T>, Serializable {
    @NullableDecl
    private transient T value;
    private final zzcu<T> zzlm;
    private volatile transient boolean zzln;

    public r(zzcu<T> zzcuVar) {
        this.zzlm = (zzcu) zzct.checkNotNull(zzcuVar);
    }

    @Override // com.google.android.gms.internal.vision.zzcu
    public final T get() {
        if (!this.zzln) {
            synchronized (this) {
                if (!this.zzln) {
                    T t = this.zzlm.get();
                    this.value = t;
                    this.zzln = true;
                    return t;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj;
        if (this.zzln) {
            String valueOf = String.valueOf(this.value);
            StringBuilder sb = new StringBuilder(valueOf.length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        } else {
            obj = this.zzlm;
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
