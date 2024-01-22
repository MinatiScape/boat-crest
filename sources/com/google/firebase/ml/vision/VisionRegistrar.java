package com.google.firebase.ml.vision;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.firebase_ml.zzmw;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqg;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.vision.automl.internal.zzb;
import java.util.List;
@KeepForSdk
/* loaded from: classes10.dex */
public class VisionRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return zzmw.zza(Component.builder(FirebaseVision.class).add(Dependency.required(zzqf.class)).factory(b.f11416a).build(), Component.builder(zzb.class).add(Dependency.required(zzqg.zza.class)).add(Dependency.required(zzqf.class)).factory(a.f11409a).build(), Component.intoSetBuilder(FirebaseModelManager.RemoteModelManagerRegistration.class).add(Dependency.requiredProvider(zzb.class)).factory(c.f11435a).build());
    }
}
