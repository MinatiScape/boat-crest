package com.google.android.gms.internal.vision;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class q<T> extends zzcn<T> {
    private final T zzll;

    public q(T t) {
        this.zzll = t;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof q) {
            return this.zzll.equals(((q) obj).zzll);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzcn
    public final T get() {
        return this.zzll;
    }

    public final int hashCode() {
        return this.zzll.hashCode() + 1502476572;
    }

    @Override // com.google.android.gms.internal.vision.zzcn
    public final boolean isPresent() {
        return true;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzll);
        StringBuilder sb = new StringBuilder(valueOf.length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
