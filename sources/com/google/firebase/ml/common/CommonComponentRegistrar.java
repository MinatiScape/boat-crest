package com.google.firebase.ml.common;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.firebase_ml.zzmw;
import com.google.android.gms.internal.firebase_ml.zzqb;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.android.gms.internal.firebase_ml.zzqr;
import com.google.android.gms.internal.firebase_ml.zzqu;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import java.util.List;
@KeepForSdk
/* loaded from: classes10.dex */
public class CommonComponentRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return zzmw.zza(zzqg.zzbjt, zzqb.zzbja, zzqr.zzbja, zzqu.zzbja, zzqf.zzbja, Component.builder(zzqg.zzb.class).add(Dependency.required(Context.class)).factory(b.f11375a).build(), Component.builder(FirebaseModelManager.class).add(Dependency.setOf(FirebaseModelManager.RemoteModelManagerRegistration.class)).factory(a.f11374a).build());
    }
}
