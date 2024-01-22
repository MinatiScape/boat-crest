package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class s<T> implements zzcu<T>, Serializable {
    @NullableDecl
    private final T zzlo;

    public s(@NullableDecl T t) {
        this.zzlo = t;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof s) {
            return zzco.equal(this.zzlo, ((s) obj).zzlo);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzcu
    public final T get() {
        return this.zzlo;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzlo});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzlo);
        StringBuilder sb = new StringBuilder(valueOf.length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
