package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzy extends ContinuationImpl {
    public /* synthetic */ Object zza;
    public final /* synthetic */ zzaa zzb;
    public int zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzy(zzaa zzaaVar, Continuation continuation) {
        super(continuation);
        this.zzb = zzaaVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object mo113executegIAlus = this.zzb.mo113executegIAlus(null, this);
        return mo113executegIAlus == a.getCOROUTINE_SUSPENDED() ? mo113executegIAlus : Result.m122boximpl(mo113executegIAlus);
    }
}
