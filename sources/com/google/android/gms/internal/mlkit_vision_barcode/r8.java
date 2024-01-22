package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* loaded from: classes9.dex */
public final class r8 extends LazyInstanceMap {
    public /* synthetic */ r8(zzug zzugVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zztp zztpVar = (zztp) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zztx(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zztq(MlKitContext.getInstance().getApplicationContext(), zztpVar), zztpVar.zzb());
    }
}
