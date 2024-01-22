package com.google.android.recaptcha.internal;

import android.content.ContentValues;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzan extends SuspendLambda implements Function2 {
    public final /* synthetic */ zzmy zza;
    public final /* synthetic */ zzao zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzan(zzmy zzmyVar, zzao zzaoVar, Continuation continuation) {
        super(2, continuation);
        this.zza = zzmyVar;
        this.zzb = zzaoVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation create(@Nullable Object obj, @NotNull Continuation continuation) {
        return new zzan(this.zza, this.zzb, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzan) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        zzad zzadVar;
        zzad zzadVar2;
        zzad zzadVar3;
        Unit unit;
        zzad zzadVar4;
        zzad zzadVar5;
        a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        zzmy zzmyVar = this.zza;
        zzao zzaoVar = this.zzb;
        synchronized (zzaj.class) {
            byte[] zzd = zzmyVar.zzd();
            zzae zzaeVar = new zzae(zzek.zzg().zzi(zzd, 0, zzd.length), System.currentTimeMillis(), 0);
            zzadVar = zzaoVar.zze;
            ContentValues contentValues = new ContentValues();
            contentValues.put("ss", zzaeVar.zzc());
            contentValues.put(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP, Long.valueOf(zzaeVar.zzb()));
            zzadVar.getWritableDatabase().insert("ce", null, contentValues);
            zzadVar2 = zzaoVar.zze;
            int zzb = zzadVar2.zzb() - 500;
            if (zzb > 0) {
                zzadVar4 = zzaoVar.zze;
                List take = CollectionsKt___CollectionsKt.take(zzadVar4.zzd(), zzb);
                zzadVar5 = zzaoVar.zze;
                zzadVar5.zza(take);
            }
            zzadVar3 = zzaoVar.zze;
            if (zzadVar3.zzb() >= 20) {
                zzaoVar.zzg();
            }
            unit = Unit.INSTANCE;
        }
        return unit;
    }
}
