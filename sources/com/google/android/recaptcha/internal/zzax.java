package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
final class zzax extends SuspendLambda implements Function2 {
    public int zza;
    public final /* synthetic */ zzbh zzb;
    public final /* synthetic */ zzay zzc;
    public final /* synthetic */ String zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzax(zzbh zzbhVar, zzay zzayVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzbhVar;
        this.zzc = zzayVar;
        this.zzd = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzax(this.zzb, this.zzc, this.zzd, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzax) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object zzi;
        zzna zzg;
        Object zzh;
        Object coroutine_suspended = a.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        try {
        } catch (Exception e) {
            zzay zzayVar = this.zzc;
            zzbh zzbhVar = this.zzb;
            this.zza = 2;
            zzi = zzayVar.zzi(e, zzbhVar, this);
            if (zzi == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.zzb.zza = new zzm();
            zzni zzg2 = zzni.zzg(zzek.zzh().zzj(this.zzd));
            zzdt zzb = zzdt.zzb();
            zzg = this.zzc.zzg(zzg2.zzi(), zzg2.zzj());
            zzb.zzf();
            long zza = zzb.zza(TimeUnit.MICROSECONDS);
            zzj zzjVar = zzj.zza;
            zzj.zza(zzl.zzm.zza(), zza);
            zzay zzayVar2 = this.zzc;
            List zzi2 = zzg.zzi();
            zzbh zzbhVar2 = this.zzb;
            this.zza = 1;
            zzh = zzayVar2.zzh(zzi2, zzbhVar2, this);
            if (zzh == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
