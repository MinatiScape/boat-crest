package com.google.android.recaptcha.internal;

import android.content.Context;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
final class zzdg extends SuspendLambda implements Function2 {
    public Object zza;
    public Object zzb;
    public Object zzc;
    public int zzd;
    public final /* synthetic */ zzdi zze;
    public final /* synthetic */ String zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdg(zzdi zzdiVar, String str, Continuation continuation) {
        super(2, continuation);
        this.zze = zzdiVar;
        this.zzf = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzdg(this.zze, this.zzf, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdg) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Mutex mutex;
        String str;
        Object obj2;
        Mutex mutex2;
        String str2;
        String str3;
        String str4;
        Context context;
        zzq zzqVar;
        Object coroutine_suspended = a.getCOROUTINE_SUSPENDED();
        int i = this.zzd;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                zzdi zzdiVar = this.zze;
                mutex = zzdiVar.zzo;
                str = this.zzf;
                this.zza = mutex;
                this.zzb = zzdiVar;
                this.zzc = str;
                this.zzd = 1;
                if (mutex.lock(null, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = zzdiVar;
            } else if (i == 1) {
                ?? r1 = this.zzc;
                obj2 = this.zzb;
                ?? r4 = this.zza;
                ResultKt.throwOnFailure(obj);
                str = r1;
                mutex = r4;
            } else {
                mutex2 = this.zza;
                try {
                    ResultKt.throwOnFailure(obj);
                    mutex2 = mutex2;
                    Unit unit = Unit.INSTANCE;
                    mutex2.unlock(null);
                    return unit;
                } catch (Throwable th) {
                    th = th;
                    mutex2.unlock(null);
                    throw th;
                }
            }
            str2 = ((zzdi) obj2).zzd;
            str3 = ((zzdi) obj2).zzh;
            str4 = ((zzdi) obj2).zzg;
            context = ((zzdi) obj2).zze;
            zzqVar = ((zzdi) obj2).zzf;
            this.zza = mutex;
            this.zzb = null;
            this.zzc = null;
            this.zzd = 2;
            if (zzaz.zzb(str2, str, str3, str4, context, zzqVar, this) != coroutine_suspended) {
                mutex2 = mutex;
                Unit unit2 = Unit.INSTANCE;
                mutex2.unlock(null);
                return unit2;
            }
            return coroutine_suspended;
        } catch (Throwable th2) {
            th = th2;
            mutex2 = mutex;
            mutex2.unlock(null);
            throw th;
        }
    }
}
