package com.google.android.libraries.places.api.net;

import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import java.util.List;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzh extends FetchPlaceRequest.Builder {
    private String zza;
    private List<Place.Field> zzb;
    private AutocompleteSessionToken zzc;
    private CancellationToken zzd;

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest.Builder
    @Nullable
    public final CancellationToken getCancellationToken() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest.Builder
    @Nullable
    public final AutocompleteSessionToken getSessionToken() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest.Builder
    public final FetchPlaceRequest.Builder setCancellationToken(@Nullable CancellationToken cancellationToken) {
        this.zzd = cancellationToken;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest.Builder
    public final FetchPlaceRequest.Builder setSessionToken(@Nullable AutocompleteSessionToken autocompleteSessionToken) {
        this.zzc = autocompleteSessionToken;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest.Builder
    public final FetchPlaceRequest.Builder zza(String str) {
        Objects.requireNonNull(str, "Null placeId");
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest.Builder
    public final FetchPlaceRequest.Builder zza(List<Place.Field> list) {
        Objects.requireNonNull(list, "Null placeFields");
        this.zzb = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest.Builder
    public final FetchPlaceRequest zza() {
        String concat = this.zza == null ? "".concat(" placeId") : "";
        if (this.zzb == null) {
            concat = String.valueOf(concat).concat(" placeFields");
        }
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzf(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
