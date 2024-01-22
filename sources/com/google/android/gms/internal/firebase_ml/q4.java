package com.google.android.gms.internal.firebase_ml;

import java.util.concurrent.Callable;
/* loaded from: classes7.dex */
public final /* synthetic */ class q4 implements Callable {
    public static final Callable h = new q4();

    @Override // java.util.concurrent.Callable
    public final Object call() {
        String version;
        version = zzpv.zzod().getVersion("firebase-ml-common");
        return version;
    }
}
