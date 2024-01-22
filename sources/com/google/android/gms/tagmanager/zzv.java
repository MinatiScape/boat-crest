package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.Container;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzv implements zzaq {
    public final /* synthetic */ Container zza;

    @Override // com.google.android.gms.tagmanager.zzaq
    public final Object zza(String str, Map<String, Object> map) {
        Container.FunctionCallMacroCallback zza = this.zza.zza(str);
        if (zza == null) {
            return null;
        }
        return zza.getValue(str, map);
    }
}
