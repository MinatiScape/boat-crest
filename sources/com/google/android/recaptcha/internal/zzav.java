package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzav extends SuspendLambda implements Function2 {
    public int zza;
    public final /* synthetic */ zzbh zzb;
    public final /* synthetic */ List zzc;
    public final /* synthetic */ zzay zzd;
    private /* synthetic */ Object zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzav(zzbh zzbhVar, List list, zzay zzayVar, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzbhVar;
        this.zzc = list;
        this.zzd = zzayVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        zzav zzavVar = new zzav(this.zzb, this.zzc, this.zzd, continuation);
        zzavVar.zze = obj;
        return zzavVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzav) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object zzi;
        Object coroutine_suspended = a.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            CoroutineScope coroutineScope = (CoroutineScope) this.zze;
            zzdt zzb = zzdt.zzb();
            while (true) {
                zzbh zzbhVar = this.zzb;
                if (zzbhVar.zzb() < 0 || zzbhVar.zzb() >= this.zzc.size() || !CoroutineScopeKt.isActive(coroutineScope)) {
                    break;
                }
                try {
                    this.zzd.zzj((zznm) this.zzc.get(this.zzb.zzb()), this.zzb);
                } catch (Exception e) {
                    zzay zzayVar = this.zzd;
                    zzbh zzbhVar2 = this.zzb;
                    this.zza = 1;
                    zzi = zzayVar.zzi(e, zzbhVar2, this);
                    if (zzi == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            zzb.zzf();
            Boxing.boxLong(zzb.zza(TimeUnit.MICROSECONDS));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
