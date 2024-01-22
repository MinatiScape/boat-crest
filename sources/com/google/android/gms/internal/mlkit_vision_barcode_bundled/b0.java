package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class b0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f9584a;
    public final int b;

    public b0(Object obj, int i) {
        this.f9584a = obj;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof b0) {
            b0 b0Var = (b0) obj;
            return this.f9584a == b0Var.f9584a && this.b == b0Var.b;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f9584a) * 65535) + this.b;
    }
}
