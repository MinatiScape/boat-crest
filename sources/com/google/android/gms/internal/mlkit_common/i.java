package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes8.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public final Object f9212a;
    public final Object b;
    public final Object c;

    public i(Object obj, Object obj2, Object obj3) {
        this.f9212a = obj;
        this.b = obj2;
        this.c = obj3;
    }

    public final IllegalArgumentException a() {
        String valueOf = String.valueOf(this.f9212a);
        String valueOf2 = String.valueOf(this.b);
        String valueOf3 = String.valueOf(this.f9212a);
        String valueOf4 = String.valueOf(this.c);
        return new IllegalArgumentException("Multiple entries with same key: " + valueOf + "=" + valueOf2 + " and " + valueOf3 + "=" + valueOf4);
    }
}
