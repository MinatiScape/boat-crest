package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzbi extends zzbt<Object, FetchPlaceRequest> {
    public zzbi(FetchPlaceRequest fetchPlaceRequest, Locale locale, String str, boolean z, zzdr zzdrVar) {
        super(fetchPlaceRequest, locale, str, z, zzdrVar);
    }

    @Override // com.google.android.libraries.places.internal.zzbt
    public final Map<String, String> zze() {
        FetchPlaceRequest fetchPlaceRequest = (FetchPlaceRequest) zza();
        HashMap hashMap = new HashMap();
        zzbt.zza(hashMap, "placeid", fetchPlaceRequest.getPlaceId(), null);
        zzbt.zza(hashMap, "sessiontoken", fetchPlaceRequest.getSessionToken(), null);
        zzbt.zza(hashMap, "fields", zzcm.zzb(fetchPlaceRequest.getPlaceFields()), null);
        return hashMap;
    }

    @Override // com.google.android.libraries.places.internal.zzbt
    public final String zzf() {
        return "details/json";
    }
}
