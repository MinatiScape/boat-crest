package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzk extends FindAutocompletePredictionsResponse {
    private final List<AutocompletePrediction> zza;

    public zzk(List<AutocompletePrediction> list) {
        Objects.requireNonNull(list, "Null autocompletePredictions");
        this.zza = list;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FindAutocompletePredictionsResponse) {
            return this.zza.equals(((FindAutocompletePredictionsResponse) obj).getAutocompletePredictions());
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
    @NonNull
    public final List<AutocompletePrediction> getAutocompletePredictions() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(valueOf.length() + 61);
        sb.append("FindAutocompletePredictionsResponse{autocompletePredictions=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
