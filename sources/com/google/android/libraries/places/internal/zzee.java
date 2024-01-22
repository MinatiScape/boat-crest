package com.google.android.libraries.places.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.auto.value.AutoValue;
import java.util.List;
@AutoValue.Builder
/* loaded from: classes10.dex */
public abstract class zzee {
    @NonNull
    public abstract zzee zza(@NonNull Status status);

    @NonNull
    public abstract zzee zza(@NonNull AutocompletePrediction autocompletePrediction);

    @NonNull
    public abstract zzee zza(@NonNull Place place);

    @NonNull
    public abstract zzee zza(@NonNull String str);

    @NonNull
    public abstract zzee zza(@NonNull List<AutocompletePrediction> list);

    @NonNull
    public abstract zzef zza();
}
