package com.google.android.gms.measurement.internal;

import java.util.List;
/* loaded from: classes10.dex */
public final class z implements com.google.android.gms.internal.measurement.zzr {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzfj f10137a;

    public z(zzfj zzfjVar) {
        this.f10137a = zzfjVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzr
    public final void zza(int i, String str, List<String> list, boolean z, boolean z2) {
        zzeg zzc;
        int i2 = i - 1;
        if (i2 == 0) {
            zzc = this.f10137a.zzs.zzay().zzc();
        } else if (i2 != 1) {
            if (i2 == 3) {
                zzc = this.f10137a.zzs.zzay().zzj();
            } else if (i2 != 4) {
                zzc = this.f10137a.zzs.zzay().zzi();
            } else if (z) {
                zzc = this.f10137a.zzs.zzay().zzm();
            } else if (!z2) {
                zzc = this.f10137a.zzs.zzay().zzl();
            } else {
                zzc = this.f10137a.zzs.zzay().zzk();
            }
        } else if (z) {
            zzc = this.f10137a.zzs.zzay().zzh();
        } else if (!z2) {
            zzc = this.f10137a.zzs.zzay().zze();
        } else {
            zzc = this.f10137a.zzs.zzay().zzd();
        }
        int size = list.size();
        if (size == 1) {
            zzc.zzb(str, list.get(0));
        } else if (size == 2) {
            zzc.zzc(str, list.get(0), list.get(1));
        } else if (size != 3) {
            zzc.zza(str);
        } else {
            zzc.zzd(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
