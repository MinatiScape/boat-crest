package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import com.google.android.libraries.places.api.model.Place;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzg extends FetchPlaceResponse {
    private final Place zza;

    public zzg(Place place) {
        Objects.requireNonNull(place, "Null place");
        this.zza = place;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FetchPlaceResponse) {
            return this.zza.equals(((FetchPlaceResponse) obj).getPlace());
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceResponse
    @NonNull
    public final Place getPlace() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(valueOf.length() + 26);
        sb.append("FetchPlaceResponse{place=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
