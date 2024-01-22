package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.Container;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzx implements zzaq {
    public final /* synthetic */ Container zza;

    @Override // com.google.android.gms.tagmanager.zzaq
    public final Object zza(String str, Map<String, Object> map) {
        Container.FunctionCallTagCallback zzb = this.zza.zzb(str);
        if (zzb != null) {
            zzb.execute(str, map);
        }
        return zzfv.zzm();
    }
}
