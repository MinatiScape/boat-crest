package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzaw extends SuspendLambda implements Function2 {
    public final /* synthetic */ Exception zza;
    public final /* synthetic */ zzbh zzb;
    public final /* synthetic */ zzay zzc;
    private /* synthetic */ Object zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaw(Exception exc, zzbh zzbhVar, zzay zzayVar, Continuation continuation) {
        super(2, continuation);
        this.zza = exc;
        this.zzb = zzbhVar;
        this.zzc = zzayVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        zzaw zzawVar = new zzaw(this.zza, this.zzb, this.zzc, continuation);
        zzawVar.zzd = obj;
        return zzawVar;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzaw) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        zznb zzf;
        a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.zzd;
        Exception exc = this.zza;
        if (exc instanceof zzs) {
            zzf = ((zzs) exc).zza();
            zzf.zzd(this.zzb.zzb());
        } else {
            zzf = zznc.zzf();
            zzf.zzd(this.zzb.zzb());
            zzf.zzp(2);
            zzf.zze(2);
        }
        zznc zzncVar = (zznc) zzf.zzj();
        zzncVar.zzk();
        zzncVar.zzj();
        Reflection.getOrCreateKotlinClass(this.zza.getClass()).getSimpleName();
        this.zza.getMessage();
        zzbh zzbhVar = this.zzb;
        zzm zzc = zzbhVar.zzc();
        zzm zzmVar = zzbhVar.zza;
        if (zzmVar == null) {
            zzmVar = null;
        }
        zzlx zza = zzar.zza(zzc, zzmVar);
        String zzf2 = this.zzb.zzf();
        if (zzf2.length() == 0) {
            zzf2 = "recaptcha.m.Main.rge";
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            zzay zzayVar = this.zzc;
            zzek zzh = zzek.zzh();
            byte[] zzd = zzncVar.zzd();
            zzek zzh2 = zzek.zzh();
            byte[] zzd2 = zza.zzd();
            zzayVar.zzb.zzb(zzf2, (String[]) Arrays.copyOf(new String[]{zzh.zzi(zzd, 0, zzd.length), zzh2.zzi(zzd2, 0, zzd2.length)}, 2));
        }
        return Unit.INSTANCE;
    }
}
