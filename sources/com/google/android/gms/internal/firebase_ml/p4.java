package com.google.android.gms.internal.firebase_ml;

import java.util.concurrent.Callable;
/* loaded from: classes7.dex */
public final /* synthetic */ class p4 implements Callable {
    public final zzqu h;

    public p4(zzqu zzquVar) {
        this.h = zzquVar;
    }

    public static Callable a(zzqu zzquVar) {
        return new p4(zzquVar);
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.h.zzos();
    }
}
