package com.google.android.recaptcha;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class Recaptcha$getClient$1 extends ContinuationImpl {
    public /* synthetic */ Object zza;
    public final /* synthetic */ Recaptcha zzb;
    public int zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recaptcha$getClient$1(Recaptcha recaptcha, Continuation continuation) {
        super(continuation);
        this.zzb = recaptcha;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.zza = obj;
        this.zzc |= Integer.MIN_VALUE;
        Object m111getClientBWLJW6A = this.zzb.m111getClientBWLJW6A(null, null, 0L, this);
        return m111getClientBWLJW6A == a.getCOROUTINE_SUSPENDED() ? m111getClientBWLJW6A : Result.m122boximpl(m111getClientBWLJW6A);
    }
}
