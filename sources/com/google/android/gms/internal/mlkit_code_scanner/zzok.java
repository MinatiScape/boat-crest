package com.google.android.gms.internal.mlkit_code_scanner;

import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public final class zzok {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static zzok f9153a;

    public static synchronized zzok zza() {
        zzok zzokVar;
        synchronized (zzok.class) {
            if (f9153a == null) {
                f9153a = new zzok();
            }
            zzokVar = f9153a;
        }
        return zzokVar;
    }
}
