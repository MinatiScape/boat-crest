package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.TuplesKt;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.s;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzay implements zzas {
    @NotNull
    public static final zzat zza = new zzat(null);
    @NotNull
    private final zzbb zzb;
    @NotNull
    private final CoroutineScope zzc;
    @NotNull
    private final zzbj zzd;
    @NotNull
    private final Map zze;
    @NotNull
    private final Map zzf;
    @NotNull
    private final zzcz zzg;

    public zzay(@NotNull zzbb zzbbVar, @NotNull CoroutineScope coroutineScope, @NotNull Context context, @NotNull zzn zznVar) {
        this.zzb = zzbbVar;
        this.zzc = coroutineScope;
        zzbj zzbjVar = new zzbj(zzbbVar, zznVar);
        this.zzd = zzbjVar;
        this.zze = zzbjVar.zzd().zzc();
        this.zzf = s.mapOf(TuplesKt.to(39, zzbs.zza), TuplesKt.to(34, zzck.zza), TuplesKt.to(35, zzcw.zza), TuplesKt.to(25, zzcb.zza), TuplesKt.to(37, zzcu.zza), TuplesKt.to(21, zzbk.zza), TuplesKt.to(22, zzct.zza), TuplesKt.to(23, zzcl.zza), TuplesKt.to(24, zzby.zza), TuplesKt.to(1, zzco.zza), TuplesKt.to(2, zzbr.zza), TuplesKt.to(38, zzcs.zza), TuplesKt.to(3, zzcc.zza), TuplesKt.to(4, zzcd.zza), TuplesKt.to(17, zzbx.zza), TuplesKt.to(32, zzbl.zza), TuplesKt.to(5, zzcf.zza), TuplesKt.to(31, zzbm.zza), TuplesKt.to(36, zzbn.zza), TuplesKt.to(16, zzbt.zza), TuplesKt.to(26, zzcp.zza), TuplesKt.to(6, zzce.zza), TuplesKt.to(27, zzcn.zza), TuplesKt.to(8, zzci.zza), TuplesKt.to(9, zzcj.zza), TuplesKt.to(10, zzcg.zza), TuplesKt.to(11, zzch.zza), TuplesKt.to(12, zzcq.zza), TuplesKt.to(13, zzcr.zza), TuplesKt.to(30, zzbo.zza), TuplesKt.to(15, zzbp.zza), TuplesKt.to(20, zzbq.zza), TuplesKt.to(7, zzcm.zza), TuplesKt.to(18, zzbv.zza), TuplesKt.to(19, zzbw.zza), TuplesKt.to(40, zzcv.zza));
        zzcz zzczVar = new zzcz();
        this.zzg = zzczVar;
        zzbjVar.zzg(3, context);
        zzbjVar.zzg(4, zzczVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zzna zzg(String str, List list) throws zzs {
        if (str.length() != 0) {
            try {
                zzcy zzcyVar = new zzcy((short) this.zzg.zza(CollectionsKt___CollectionsKt.toIntArray(list)), (short) 255);
                StringBuilder sb = new StringBuilder(str.length());
                for (int i = 0; i < str.length(); i++) {
                    sb.append((char) UInt.m158constructorimpl(UInt.m158constructorimpl(str.charAt(i)) ^ UInt.m158constructorimpl(zzcyVar.zza())));
                }
                return zzna.zzg(zzek.zzh().zzj(sb.toString()));
            } catch (Exception e) {
                throw new zzs(3, 18, e);
            }
        }
        throw new zzs(3, 17, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzh(List list, zzbh zzbhVar, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new zzav(zzbhVar, list, this, null), continuation);
        return coroutineScope == a.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzi(Exception exc, zzbh zzbhVar, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new zzaw(exc, zzbhVar, this, null), continuation);
        return coroutineScope == a.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzj(zznm zznmVar, zzbh zzbhVar) throws zzs {
        zzdt zzb = zzdt.zzb();
        int zzb2 = zzbhVar.zzb();
        zzca zzcaVar = (zzca) this.zzf.get(Integer.valueOf(zznmVar.zzf()));
        if (zzcaVar != null) {
            int zzg = zznmVar.zzg();
            zznl[] zznlVarArr = (zznl[]) zznmVar.zzj().toArray(new zznl[0]);
            zzcaVar.zza(zzg, zzbhVar, (zznl[]) Arrays.copyOf(zznlVarArr, zznlVarArr.length));
            if (zzb2 == zzbhVar.zzb()) {
                zzbhVar.zzi(zzbhVar.zzb() + 1);
            }
            zzb.zzf();
            long zza2 = zzb.zza(TimeUnit.MICROSECONDS);
            zzj zzjVar = zzj.zza;
            int zzk = zznmVar.zzk();
            if (zzk != 1) {
                zzj.zza(zzk - 2, zza2);
                zznmVar.zzk();
                zznmVar.zzg();
                CollectionsKt___CollectionsKt.joinToString$default(zznmVar.zzj(), null, null, null, 0, null, new zzau(this), 31, null);
                return;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        throw new zzs(5, 2, null);
    }

    @Override // com.google.android.recaptcha.internal.zzas
    public final void zza(@NotNull String str) {
        e.e(this.zzc, null, null, new zzax(new zzbh(this.zzd), this, str, null), 3, null);
    }
}
