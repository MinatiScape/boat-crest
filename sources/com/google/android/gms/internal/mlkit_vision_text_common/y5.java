package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* loaded from: classes6.dex */
public final class y5 extends LazyInstanceMap {
    public /* synthetic */ y5(zzop zzopVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zznx zznxVar = (zznx) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzog(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzny(MlKitContext.getInstance().getApplicationContext(), zznxVar), zznxVar.zzb());
    }
}
