package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
/* loaded from: classes10.dex */
public final class b0 implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public final String f10112a;
    public final /* synthetic */ zzfp b;

    public b0(zzfp zzfpVar, String str) {
        this.b = zzfpVar;
        Preconditions.checkNotNull(str);
        this.f10112a = str;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.b.zzs.zzay().zzd().zzb(this.f10112a, th);
    }
}
