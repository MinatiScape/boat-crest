package com.google.firebase.ml.common.internal.modeldownload;

import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.firebase.ml.common.FirebaseMLException;
/* loaded from: classes10.dex */
public final class zzg {

    /* renamed from: a  reason: collision with root package name */
    public final zzi f11393a;

    public zzg(zzqf zzqfVar) {
        this.f11393a = new zzi(zzqfVar);
    }

    public final void zza(String str, zzn zznVar) throws FirebaseMLException {
        this.f11393a.zza(str, zznVar);
    }

    public final boolean zzb(String str, zzn zznVar) throws FirebaseMLException {
        return this.f11393a.zzb(str, zznVar);
    }

    public final boolean zza(zzaa zzaaVar) throws FirebaseMLException {
        return this.f11393a.zzb(zzaaVar.zzpl(), zzaaVar.zzpm());
    }
}
