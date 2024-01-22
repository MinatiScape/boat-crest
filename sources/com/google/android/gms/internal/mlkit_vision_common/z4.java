package com.google.android.gms.internal.mlkit_vision_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* loaded from: classes8.dex */
public final class z4 extends LazyInstanceMap {
    public /* synthetic */ z4(zzmq zzmqVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzme zzmeVar = (zzme) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzmj(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzmf(MlKitContext.getInstance().getApplicationContext(), zzmeVar), zzmeVar.zzb());
    }
}
