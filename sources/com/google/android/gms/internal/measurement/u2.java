package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class u2 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8932a;
    public final int b;

    public u2(Object obj, int i) {
        this.f8932a = obj;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof u2) {
            u2 u2Var = (u2) obj;
            return this.f8932a == u2Var.f8932a && this.b == u2Var.b;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f8932a) * 65535) + this.b;
    }
}
