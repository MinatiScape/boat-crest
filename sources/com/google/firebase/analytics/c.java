package com.google.firebase.analytics;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.measurement.zzee;
import com.google.android.gms.measurement.internal.zzgs;
import com.google.android.gms.measurement.internal.zzgt;
import com.google.android.gms.measurement.internal.zzhw;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class c implements zzhw {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzee f11076a;

    public c(zzee zzeeVar) {
        this.f11076a = zzeeVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final int zza(String str) {
        return this.f11076a.zza(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final long zzb() {
        return this.f11076a.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    @Nullable
    public final Object zzg(int i) {
        return this.f11076a.zzh(i);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    @Nullable
    public final String zzh() {
        return this.f11076a.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    @Nullable
    public final String zzi() {
        return this.f11076a.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    @Nullable
    public final String zzj() {
        return this.f11076a.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    @Nullable
    public final String zzk() {
        return this.f11076a.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final List<Bundle> zzm(@Nullable String str, @Nullable String str2) {
        return this.f11076a.zzp(str, str2);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final Map<String, Object> zzo(@Nullable String str, @Nullable String str2, boolean z) {
        return this.f11076a.zzq(str, str2, z);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzp(String str) {
        this.f11076a.zzu(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzq(String str, @Nullable String str2, @Nullable Bundle bundle) {
        this.f11076a.zzv(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzr(String str) {
        this.f11076a.zzw(str);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzs(String str, String str2, Bundle bundle) {
        this.f11076a.zzy(str, str2, bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzt(String str, String str2, Bundle bundle, long j) {
        this.f11076a.zzz(str, str2, bundle, j);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzu(zzgt zzgtVar) {
        this.f11076a.zzB(zzgtVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzv(Bundle bundle) {
        this.f11076a.zzD(bundle);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzw(zzgs zzgsVar) {
        this.f11076a.zzJ(zzgsVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzhw
    public final void zzx(zzgt zzgtVar) {
        this.f11076a.zzO(zzgtVar);
    }
}
