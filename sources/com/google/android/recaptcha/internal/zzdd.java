package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzdd extends ContinuationImpl {
    public /* synthetic */ Object zza;
    public final /* synthetic */ zzdi zzb;
    public int zzc;
    public zzdi zzd;
    public String zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdd(zzdi zzdiVar, Continuation continuation) {
        super(continuation);
        this.zzb = zzdiVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object zzf = this.zzb.zzf(null, 0L, this);
        return zzf == a.getCOROUTINE_SUSPENDED() ? zzf : Result.m122boximpl(zzf);
    }
}
