package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.google.android.libraries.places.internal.zzft;
import com.google.android.libraries.places.internal.zzgr;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class LocalTime implements Parcelable, Comparable<LocalTime> {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class zza {
        @NonNull
        public abstract zza zza(@IntRange(from = 0, to = 23) int i);

        @NonNull
        public abstract LocalTime zza();

        @NonNull
        public abstract zza zzb(@IntRange(from = 0, to = 59) int i);
    }

    @NonNull
    public static LocalTime newInstance(@IntRange(from = 0, to = 23) int i, @IntRange(from = 0, to = 59) int i2) {
        try {
            LocalTime zza2 = new zzk().zza(i).zzb(i2).zza();
            int hours = zza2.getHours();
            zzft.zza(zzgr.zza(0, 23).zzb(Integer.valueOf(hours)), "Hours must not be out-of-range: 0 to 23, but was: %s.", hours);
            int minutes = zza2.getMinutes();
            zzft.zza(zzgr.zza(0, 59).zzb(Integer.valueOf(minutes)), "Minutes must not be out-of-range: 0 to 59, but was: %s.", minutes);
            return zza2;
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(LocalTime localTime) {
        int hours;
        int hours2;
        if (this == localTime) {
            return 0;
        }
        if (getHours() == localTime.getHours()) {
            hours = getMinutes();
            hours2 = localTime.getMinutes();
        } else {
            hours = getHours();
            hours2 = localTime.getHours();
        }
        return hours - hours2;
    }

    @IntRange(from = 0, to = 23)
    public abstract int getHours();

    @IntRange(from = 0, to = 59)
    public abstract int getMinutes();
}
