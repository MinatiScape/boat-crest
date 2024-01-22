package com.google.android.gms.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzgs;
import com.google.android.gms.measurement.internal.zzgt;
import com.google.android.gms.measurement.internal.zzhw;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class b extends c {

    /* renamed from: a  reason: collision with root package name */
    public final zzhw f10109a;

    public b(zzhw zzhwVar) {
        super(null);
        Preconditions.checkNotNull(zzhwVar);
        this.f10109a = zzhwVar;
    }

    @Override // com.google.android.gms.measurement.c
    public final Boolean a() {
        return (Boolean) this.f10109a.zzg(4);
    }

    @Override // com.google.android.gms.measurement.c
    public final Double b() {
        return (Double) this.f10109a.zzg(2);
    }

    @Override // com.google.android.gms.measurement.c
    public final Integer c() {
        return (Integer) this.f10109a.zzg(3);
    }

    @Override // com.google.android.gms.measurement.c
    public final Long d() {
        return (Long) this.f10109a.zzg(1);
    }

    @Override // com.google.android.gms.measurement.c
    public final String e() {
        return (String) this.f10109a.zzg(0);
    }

    @Override // com.google.android.gms.measurement.c
    public final Map<String, Object> f(boolean z) {
        return this.f10109a.zzo(null, null, z);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final int zza(String str) {
        return this.f10109a.zza(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final long zzb() {
        return this.f10109a.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final Object zzg(int i) {
        return this.f10109a.zzg(i);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final String zzh() {
        return this.f10109a.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final String zzi() {
        return this.f10109a.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final String zzj() {
        return this.f10109a.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final String zzk() {
        return this.f10109a.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final List<Bundle> zzm(String str, String str2) {
        return this.f10109a.zzm(str, str2);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final Map<String, Object> zzo(String str, String str2, boolean z) {
        return this.f10109a.zzo(str, str2, z);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzp(String str) {
        this.f10109a.zzp(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzq(String str, String str2, Bundle bundle) {
        this.f10109a.zzq(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzr(String str) {
        this.f10109a.zzr(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzs(String str, String str2, Bundle bundle) {
        this.f10109a.zzs(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzt(String str, String str2, Bundle bundle, long j) {
        this.f10109a.zzt(str, str2, bundle, j);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzu(zzgt zzgtVar) {
        this.f10109a.zzu(zzgtVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzv(Bundle bundle) {
        this.f10109a.zzv(bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzw(zzgs zzgsVar) {
        this.f10109a.zzw(zzgsVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzx(zzgt zzgtVar) {
        this.f10109a.zzx(zzgtVar);
    }
}
