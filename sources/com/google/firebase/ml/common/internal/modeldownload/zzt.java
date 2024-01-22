package com.google.firebase.ml.common.internal.modeldownload;

import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzwz;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
/* loaded from: classes10.dex */
public final class zzt {
    public static zzns.zzak zza(FirebaseRemoteModel firebaseRemoteModel, zzn zznVar) {
        String modelHash = firebaseRemoteModel.getModelHash();
        zzns.zzaj.zzb zzow = zznVar.zzow();
        zzns.zzak.zza zzmn = zzns.zzak.zzmn();
        zzns.zzaj.zza zza = zzns.zzaj.zzml().zzbd(firebaseRemoteModel.getModelNameForBackend()).zza(zzns.zzaj.zzc.CLOUD);
        if (modelHash == null) {
            modelHash = "";
        }
        return (zzns.zzak) ((zzwz) zzmn.zza(zza.zzbf(modelHash).zzd(zzow)).zzvb());
    }
}
