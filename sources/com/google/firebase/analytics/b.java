package com.google.firebase.analytics;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.measurement.zzee;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public final class b implements Callable<String> {
    public final /* synthetic */ FirebaseAnalytics h;

    public b(FirebaseAnalytics firebaseAnalytics) {
        this.h = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    @Nullable
    public final /* bridge */ /* synthetic */ String call() throws Exception {
        zzee zzeeVar;
        zzeeVar = this.h.f11075a;
        return zzeeVar.zzk();
    }
}
