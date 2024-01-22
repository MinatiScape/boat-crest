package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.SystemClock;
/* loaded from: classes9.dex */
public final class zzar {

    /* renamed from: a  reason: collision with root package name */
    public static final zzbf f9545a;

    static {
        zzbf bVar;
        try {
            SystemClock.elapsedRealtimeNanos();
            bVar = new a();
        } catch (Throwable unused) {
            SystemClock.elapsedRealtime();
            bVar = new b();
        }
        f9545a = bVar;
    }

    public static zzbf zza() {
        return f9545a;
    }
}
