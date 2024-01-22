package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzbc extends zzbt<Object, FetchPhotoRequest> {
    public zzbc(FetchPhotoRequest fetchPhotoRequest, String str, boolean z, zzdr zzdrVar) {
        super(fetchPhotoRequest, null, str, z, zzdrVar);
    }

    @Override // com.google.android.libraries.places.internal.zzbt
    public final Map<String, String> zze() {
        FetchPhotoRequest fetchPhotoRequest = (FetchPhotoRequest) zza();
        PhotoMetadata photoMetadata = fetchPhotoRequest.getPhotoMetadata();
        HashMap hashMap = new HashMap();
        zzbt.zza(hashMap, "maxheight", fetchPhotoRequest.getMaxHeight(), null);
        zzbt.zza(hashMap, "maxwidth", fetchPhotoRequest.getMaxWidth(), null);
        hashMap.put("photoreference", photoMetadata.zza());
        return hashMap;
    }

    @Override // com.google.android.libraries.places.internal.zzbt
    public final String zzf() {
        return "photo";
    }
}
