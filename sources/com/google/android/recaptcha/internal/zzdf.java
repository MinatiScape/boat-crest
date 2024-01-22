package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzdf extends ContinuationImpl {
    public long zza;
    public /* synthetic */ Object zzb;
    public final /* synthetic */ zzdi zzc;
    public int zzd;
    public zzdi zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdf(zzdi zzdiVar, Continuation continuation) {
        super(continuation);
        this.zzc = zzdiVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.zzb = obj;
        this.zzd |= Integer.MIN_VALUE;
        Object zzg = this.zzc.zzg(0L, this);
        return zzg == a.getCOROUTINE_SUSPENDED() ? zzg : Result.m122boximpl(zzg);
    }
}
