package com.google.android.libraries.places.internal;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzbm extends zzbt<Object, FindAutocompletePredictionsRequest> {
    public zzbm(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, Locale locale, String str, boolean z, zzdr zzdrVar) {
        super(findAutocompletePredictionsRequest, locale, str, z, zzdrVar);
    }

    @Override // com.google.android.libraries.places.internal.zzbt
    public final Map<String, String> zze() {
        FindAutocompletePredictionsRequest findAutocompletePredictionsRequest = (FindAutocompletePredictionsRequest) zza();
        HashMap hashMap = new HashMap();
        String query = findAutocompletePredictionsRequest.getQuery();
        zzbt.zza(hashMap, "input", query == null ? null : query.replaceFirst("^\\s+", "").replaceFirst("\\s+$", HexStringBuilder.DEFAULT_SEPARATOR), null);
        zzbt.zza(hashMap, "types", zzcp.zza(findAutocompletePredictionsRequest.getTypeFilter()), null);
        zzbt.zza(hashMap, "sessiontoken", findAutocompletePredictionsRequest.getSessionToken(), null);
        zzbt.zza(hashMap, "origin", zzcn.zza(findAutocompletePredictionsRequest.getOrigin()), null);
        zzbt.zza(hashMap, "locationbias", zzcn.zza(findAutocompletePredictionsRequest.getLocationBias()), null);
        zzbt.zza(hashMap, "locationrestriction", zzcn.zza(findAutocompletePredictionsRequest.getLocationRestriction()), null);
        zzbt.zza(hashMap, "components", zzcn.zza(findAutocompletePredictionsRequest.getCountries()), null);
        return hashMap;
    }

    @Override // com.google.android.libraries.places.internal.zzbt
    public final String zzf() {
        return "autocomplete/json";
    }
}
