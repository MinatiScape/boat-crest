package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzo;
import java.util.Map;
/* loaded from: classes10.dex */
public final class a0 implements zzo {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f10111a;
    public final /* synthetic */ zzfj b;

    public a0(zzfj zzfjVar, String str) {
        this.b = zzfjVar;
        this.f10111a = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzo
    public final String zza(String str) {
        Map map;
        map = this.b.b;
        Map map2 = (Map) map.get(this.f10111a);
        if (map2 == null || !map2.containsKey(str)) {
            return null;
        }
        return (String) map2.get(str);
    }
}
