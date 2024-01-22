package com.google.android.gms.internal.firebase_ml;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes7.dex */
public final class x1<T> extends zzmi<T> {
    private final T zzakf;

    public x1(T t) {
        this.zzakf = t;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof x1) {
            return this.zzakf.equals(((x1) obj).zzakf);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmi
    public final T get() {
        return this.zzakf;
    }

    public final int hashCode() {
        return this.zzakf.hashCode() + 1502476572;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmi
    public final boolean isPresent() {
        return true;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzakf);
        StringBuilder sb = new StringBuilder(valueOf.length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
