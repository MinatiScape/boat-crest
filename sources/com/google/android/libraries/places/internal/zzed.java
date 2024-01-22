package com.google.android.libraries.places.internal;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class zzed implements Parcelable {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class zza {
        @NonNull
        public abstract zza zza(int i);

        @NonNull
        public abstract zza zza(@Nullable LocationBias locationBias);

        @NonNull
        public abstract zza zza(@Nullable LocationRestriction locationRestriction);

        @NonNull
        public abstract zza zza(@Nullable TypeFilter typeFilter);

        @NonNull
        public abstract zza zza(@NonNull zzec zzecVar);

        @NonNull
        public abstract zza zza(@NonNull AutocompleteActivityMode autocompleteActivityMode);

        @NonNull
        public abstract zza zza(@Nullable String str);

        @NonNull
        public abstract zza zza(@NonNull List<Place.Field> list);

        @NonNull
        public abstract zzed zza();

        @NonNull
        public abstract zza zzb(int i);

        @NonNull
        public abstract zza zzb(@Nullable String str);

        @NonNull
        public abstract zza zzb(@NonNull List<String> list);

        @NonNull
        public final zza zzc(@Nullable String str) {
            return zzb(str == null ? zzgi.zza() : zzgi.zza(str));
        }
    }

    @NonNull
    public static zza zza(@NonNull AutocompleteActivityMode autocompleteActivityMode, @NonNull List<Place.Field> list, @NonNull zzec zzecVar) {
        return new zzdw().zzb(new ArrayList()).zza(autocompleteActivityMode).zza(list).zza(zzecVar).zza(0).zzb(0);
    }

    @NonNull
    public abstract AutocompleteActivityMode zza();

    @NonNull
    public abstract zzgi<Place.Field> zzb();

    @NonNull
    public abstract zzec zzc();

    @Nullable
    public abstract String zzd();

    @Nullable
    public abstract String zze();

    @Nullable
    public abstract LocationBias zzf();

    @Nullable
    public abstract LocationRestriction zzg();

    @NonNull
    public abstract zzgi<String> zzh();

    @Nullable
    public abstract TypeFilter zzi();

    public abstract int zzj();

    public abstract int zzk();

    @NonNull
    public abstract zza zzl();
}
