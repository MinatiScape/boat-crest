package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.libraries.places.internal.zzft;
import com.google.android.libraries.places.internal.zzgi;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class OpeningHours implements Parcelable {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public OpeningHours build() {
            OpeningHours zza = zza();
            for (String str : zza.getWeekdayText()) {
                zzft.zzb(!TextUtils.isEmpty(str), "WeekdayText must not contain null or empty values.");
            }
            setPeriods(zzgi.zza((Collection) zza.getPeriods()));
            setWeekdayText(zzgi.zza((Collection) zza.getWeekdayText()));
            return zza();
        }

        @NonNull
        public abstract List<Period> getPeriods();

        @NonNull
        public abstract List<String> getWeekdayText();

        @NonNull
        public abstract Builder setPeriods(@NonNull List<Period> list);

        @NonNull
        public abstract Builder setWeekdayText(@NonNull List<String> list);

        @NonNull
        public abstract OpeningHours zza();
    }

    @NonNull
    public static Builder builder() {
        return new zzm().setPeriods(new ArrayList()).setWeekdayText(new ArrayList());
    }

    @NonNull
    public abstract List<Period> getPeriods();

    @NonNull
    public abstract List<String> getWeekdayText();
}
