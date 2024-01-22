package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public final class zzmw {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static zzmw f9796a;

    public static synchronized zzmw zza() {
        zzmw zzmwVar;
        synchronized (zzmw.class) {
            if (f9796a == null) {
                f9796a = new zzmw();
            }
            zzmwVar = f9796a;
        }
        return zzmwVar;
    }

    public static final boolean zzb() {
        return a5.a("mlkit-dev-profiling");
    }
}
