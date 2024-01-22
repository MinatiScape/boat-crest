package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* loaded from: classes8.dex */
public final class j6 extends LazyInstanceMap {
    public /* synthetic */ j6(zzqi zzqiVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzpt zzptVar = (zzpt) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzpz(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzpu(MlKitContext.getInstance().getApplicationContext(), zzptVar), zzptVar.zzb());
    }
}
