package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;
/* loaded from: classes10.dex */
final class zzn extends FindCurrentPlaceRequest {
    private final List<Place.Field> zza;
    private final CancellationToken zzb;

    private zzn(List<Place.Field> list, @Nullable CancellationToken cancellationToken) {
        this.zza = list;
        this.zzb = cancellationToken;
    }

    public final boolean equals(Object obj) {
        CancellationToken cancellationToken;
        if (obj == this) {
            return true;
        }
        if (obj instanceof FindCurrentPlaceRequest) {
            FindCurrentPlaceRequest findCurrentPlaceRequest = (FindCurrentPlaceRequest) obj;
            if (this.zza.equals(findCurrentPlaceRequest.getPlaceFields()) && ((cancellationToken = this.zzb) != null ? cancellationToken.equals(findCurrentPlaceRequest.getCancellationToken()) : findCurrentPlaceRequest.getCancellationToken() == null)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FindCurrentPlaceRequest, com.google.android.libraries.places.internal.zzdc
    @Nullable
    public final CancellationToken getCancellationToken() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
    @NonNull
    public final List<Place.Field> getPlaceFields() {
        return this.zza;
    }

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        CancellationToken cancellationToken = this.zzb;
        return hashCode ^ (cancellationToken == null ? 0 : cancellationToken.hashCode());
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(valueOf.length() + 57 + valueOf2.length());
        sb.append("FindCurrentPlaceRequest{placeFields=");
        sb.append(valueOf);
        sb.append(", cancellationToken=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
