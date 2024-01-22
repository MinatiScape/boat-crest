package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class TimeOfWeek implements Parcelable {
    @NonNull
    public static TimeOfWeek newInstance(@NonNull DayOfWeek dayOfWeek, @NonNull LocalTime localTime) {
        return new zzaz(dayOfWeek, localTime);
    }

    @NonNull
    public abstract DayOfWeek getDay();

    @NonNull
    public abstract LocalTime getTime();
}
