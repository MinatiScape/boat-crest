package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzfs;
import com.google.android.gms.measurement.internal.zzgs;
import com.google.android.gms.measurement.internal.zzgt;
import com.google.android.gms.measurement.internal.zzhv;
import com.google.android.gms.measurement.internal.zzkq;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class a extends c {

    /* renamed from: a  reason: collision with root package name */
    public final zzfs f10107a;
    public final zzhv b;

    public a(@NonNull zzfs zzfsVar) {
        super(null);
        Preconditions.checkNotNull(zzfsVar);
        this.f10107a = zzfsVar;
        this.b = zzfsVar.zzq();
    }

    @Override // com.google.android.gms.measurement.c
    public final Boolean a() {
        return this.b.zzi();
    }

    @Override // com.google.android.gms.measurement.c
    public final Double b() {
        return this.b.zzj();
    }

    @Override // com.google.android.gms.measurement.c
    public final Integer c() {
        return this.b.zzl();
    }

    @Override // com.google.android.gms.measurement.c
    public final Long d() {
        return this.b.zzm();
    }

    @Override // com.google.android.gms.measurement.c
    public final String e() {
        return this.b.zzr();
    }

    @Override // com.google.android.gms.measurement.c
    public final Map<String, Object> f(boolean z) {
        List<zzkq> zzt = this.b.zzt(z);
        ArrayMap arrayMap = new ArrayMap(zzt.size());
        for (zzkq zzkqVar : zzt) {
            Object zza = zzkqVar.zza();
            if (zza != null) {
                arrayMap.put(zzkqVar.zzb, zza);
            }
        }
        return arrayMap;
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final int zza(String str) {
        this.b.zzh(str);
        return 25;
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final long zzb() {
        return this.f10107a.zzv().zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final Object zzg(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return this.b.zzi();
                    }
                    return this.b.zzl();
                }
                return this.b.zzj();
            }
            return this.b.zzm();
        }
        return this.b.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final String zzh() {
        return this.b.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final String zzi() {
        return this.b.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final String zzj() {
        return this.b.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final String zzk() {
        return this.b.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final List<Bundle> zzm(String str, String str2) {
        return this.b.zzs(str, str2);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final Map<String, Object> zzo(String str, String str2, boolean z) {
        return this.b.zzu(str, str2, z);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzp(String str) {
        this.f10107a.zzd().zzd(str, this.f10107a.zzav().elapsedRealtime());
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzq(String str, String str2, Bundle bundle) {
        this.f10107a.zzq().zzz(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzr(String str) {
        this.f10107a.zzd().zze(str, this.f10107a.zzav().elapsedRealtime());
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzs(String str, String str2, Bundle bundle) {
        this.b.zzC(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzt(String str, String str2, Bundle bundle, long j) {
        this.b.zzD(str, str2, bundle, true, false, j);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzu(zzgt zzgtVar) {
        this.b.zzI(zzgtVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzv(Bundle bundle) {
        this.b.zzO(bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzw(zzgs zzgsVar) {
        this.b.zzS(zzgsVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzx(zzgt zzgtVar) {
        this.b.zzY(zzgtVar);
    }
}
