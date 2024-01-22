package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.libraries.places.internal.zzfz;
import com.google.android.libraries.places.internal.zzgr;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class PlaceLikelihood implements Parcelable {
    public static final double LIKELIHOOD_MAX_VALUE = 1.0d;
    public static final double LIKELIHOOD_MIN_VALUE = 0.0d;

    @NonNull
    public static PlaceLikelihood newInstance(@NonNull Place place, @FloatRange(from = 0.0d, to = 1.0d) double d) {
        Double valueOf = Double.valueOf(0.0d);
        Double valueOf2 = Double.valueOf(1.0d);
        boolean zzb = zzgr.zza(valueOf, valueOf2).zzb(Double.valueOf(d));
        Double valueOf3 = Double.valueOf(d);
        if (zzb) {
            return new zzat(place, d);
        }
        throw new IllegalArgumentException(zzfz.zza("Likelihood must not be out-of-range: %s to %s, but was: %s.", valueOf, valueOf2, valueOf3));
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public abstract double getLikelihood();

    @NonNull
    public abstract Place getPlace();
}
