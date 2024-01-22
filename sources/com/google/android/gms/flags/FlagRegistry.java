package com.google.android.gms.flags;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes6.dex */
public class FlagRegistry {

    /* renamed from: a  reason: collision with root package name */
    public final Collection<Flag> f8468a = new ArrayList();

    public FlagRegistry() {
        new ArrayList();
        new ArrayList();
    }

    @KeepForSdk
    public static void initialize(Context context) {
        Singletons.zzd().initialize(context);
    }

    public final void zza(Flag flag) {
        this.f8468a.add(flag);
    }
}
