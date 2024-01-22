package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.navigation.NavigationConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzbq extends zzbt<Object, FindCurrentPlaceRequest> {
    private final Location zza;
    private final zzgi<zzq> zzb;

    public zzbq(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzgi<zzq> zzgiVar, Locale locale, String str, boolean z, zzdr zzdrVar) {
        super(findCurrentPlaceRequest, locale, str, z, zzdrVar);
        this.zza = location;
        this.zzb = zzgiVar;
    }

    @Override // com.google.android.libraries.places.internal.zzbt
    public final Map<String, String> zze() {
        HashMap hashMap = new HashMap();
        zzbt.zza(hashMap, FirebaseAnalytics.Param.LOCATION, zzcn.zzb(this.zza), null);
        zzbt.zza(hashMap, "wifiaccesspoints", zzcn.zza(this.zzb, (int) NavigationConstants.UI_HANDLER_MAP_CONTROLS), null);
        zzbt.zza(hashMap, "precision", zzcn.zza(this.zza), null);
        zzbt.zza(hashMap, "timestamp", Long.valueOf(this.zza.getTime()), null);
        zzbt.zza(hashMap, "fields", zzcm.zzb(((FindCurrentPlaceRequest) zza()).getPlaceFields()), null);
        return hashMap;
    }

    @Override // com.google.android.libraries.places.internal.zzbt
    public final String zzf() {
        return "findplacefromuserlocation/json";
    }
}
