package com.google.android.recaptcha.internal;

import java.util.Timer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
final class zzam extends SuspendLambda implements Function2 {
    public final /* synthetic */ zzao zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzam(zzao zzaoVar, Continuation continuation) {
        super(2, continuation);
        this.zza = zzaoVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzam(this.zza, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzam) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        zzad zzadVar;
        Unit unit;
        Timer timer;
        a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzao zzaoVar = this.zza;
        synchronized (zzaj.class) {
            zzadVar = zzaoVar.zze;
            if (zzadVar.zzb() == 0) {
                timer = zzao.zzb;
                if (timer != null) {
                    timer.cancel();
                }
                zzao.zzb = null;
            }
            zzaoVar.zzg();
            unit = Unit.INSTANCE;
        }
        return unit;
    }
}
