package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;
/* loaded from: classes10.dex */
final class zzf extends FetchPlaceRequest {
    private final String zza;
    private final List<Place.Field> zzb;
    private final AutocompleteSessionToken zzc;
    private final CancellationToken zzd;

    private zzf(String str, List<Place.Field> list, @Nullable AutocompleteSessionToken autocompleteSessionToken, @Nullable CancellationToken cancellationToken) {
        this.zza = str;
        this.zzb = list;
        this.zzc = autocompleteSessionToken;
        this.zzd = cancellationToken;
    }

    public final boolean equals(Object obj) {
        AutocompleteSessionToken autocompleteSessionToken;
        CancellationToken cancellationToken;
        if (obj == this) {
            return true;
        }
        if (obj instanceof FetchPlaceRequest) {
            FetchPlaceRequest fetchPlaceRequest = (FetchPlaceRequest) obj;
            if (this.zza.equals(fetchPlaceRequest.getPlaceId()) && this.zzb.equals(fetchPlaceRequest.getPlaceFields()) && ((autocompleteSessionToken = this.zzc) != null ? autocompleteSessionToken.equals(fetchPlaceRequest.getSessionToken()) : fetchPlaceRequest.getSessionToken() == null) && ((cancellationToken = this.zzd) != null ? cancellationToken.equals(fetchPlaceRequest.getCancellationToken()) : fetchPlaceRequest.getCancellationToken() == null)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest, com.google.android.libraries.places.internal.zzdc
    @Nullable
    public final CancellationToken getCancellationToken() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest
    @NonNull
    public final List<Place.Field> getPlaceFields() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest
    @NonNull
    public final String getPlaceId() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceRequest
    @Nullable
    public final AutocompleteSessionToken getSessionToken() {
        return this.zzc;
    }

    public final int hashCode() {
        int hashCode = (((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003;
        AutocompleteSessionToken autocompleteSessionToken = this.zzc;
        int hashCode2 = (hashCode ^ (autocompleteSessionToken == null ? 0 : autocompleteSessionToken.hashCode())) * 1000003;
        CancellationToken cancellationToken = this.zzd;
        return hashCode2 ^ (cancellationToken != null ? cancellationToken.hashCode() : 0);
    }

    public final String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        String valueOf3 = String.valueOf(this.zzd);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 76 + valueOf.length() + valueOf2.length() + valueOf3.length());
        sb.append("FetchPlaceRequest{placeId=");
        sb.append(str);
        sb.append(", placeFields=");
        sb.append(valueOf);
        sb.append(", sessionToken=");
        sb.append(valueOf2);
        sb.append(", cancellationToken=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }
}
