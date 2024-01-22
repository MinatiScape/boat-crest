package com.google.android.gms.flags;

import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes6.dex */
public final class Singletons {
    public static Singletons c;

    /* renamed from: a  reason: collision with root package name */
    public final FlagRegistry f8469a = new FlagRegistry();
    public final zzb b = new zzb();

    static {
        Singletons singletons = new Singletons();
        synchronized (Singletons.class) {
            c = singletons;
        }
    }

    public static Singletons a() {
        Singletons singletons;
        synchronized (Singletons.class) {
            singletons = c;
        }
        return singletons;
    }

    @KeepForSdk
    public static FlagRegistry flagRegistry() {
        return a().f8469a;
    }

    public static zzb zzd() {
        return a().b;
    }
}
