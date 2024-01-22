package com.google.android.gms.internal.mlkit_vision_barcode;

import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class i1 {
    @CheckForNull
    public static final i1 c;
    @CheckForNull
    public static final i1 d;

    /* renamed from: a  reason: collision with root package name */
    public final boolean f9416a;
    @CheckForNull
    public final Throwable b;

    static {
        if (zzec.k) {
            d = null;
            c = null;
            return;
        }
        d = new i1(false, null);
        c = new i1(true, null);
    }

    public i1(boolean z, @CheckForNull Throwable th) {
        this.f9416a = z;
        this.b = th;
    }
}
