package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class zzj {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, Callable<? extends zzai>> f8960a = new HashMap();

    public final void zza(String str, Callable<? extends zzai> callable) {
        this.f8960a.put(str, callable);
    }
}
