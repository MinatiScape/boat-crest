package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.SpannableString;
import android.text.style.CharacterStyle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzgi;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class AutocompletePrediction implements Parcelable {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public AutocompletePrediction build() {
            AutocompletePrediction zza = zza();
            setPlaceTypes(zzgi.zza((Collection) zza.getPlaceTypes()));
            List<zza> zzd = zza.zzd();
            if (zzd != null) {
                zza(zzgi.zza((Collection) zzd));
            }
            List<zza> zze = zza.zze();
            if (zze != null) {
                zzb(zzgi.zza((Collection) zze));
            }
            List<zza> zzf = zza.zzf();
            if (zzf != null) {
                zzc(zzgi.zza((Collection) zzf));
            }
            return zza();
        }

        @Nullable
        public abstract Integer getDistanceMeters();

        @NonNull
        public abstract String getFullText();

        @NonNull
        public abstract List<Place.Type> getPlaceTypes();

        @NonNull
        public abstract String getPrimaryText();

        @NonNull
        public abstract String getSecondaryText();

        @NonNull
        public abstract Builder setDistanceMeters(@Nullable Integer num);

        @NonNull
        public abstract Builder setFullText(@NonNull String str);

        @NonNull
        public abstract Builder setPlaceTypes(@NonNull List<Place.Type> list);

        @NonNull
        public abstract Builder setPrimaryText(@NonNull String str);

        @NonNull
        public abstract Builder setSecondaryText(@NonNull String str);

        @NonNull
        public abstract Builder zza(@NonNull String str);

        @NonNull
        public abstract Builder zza(@Nullable List<zza> list);

        @NonNull
        public abstract AutocompletePrediction zza();

        @NonNull
        public abstract Builder zzb(@Nullable List<zza> list);

        @NonNull
        public abstract Builder zzc(@Nullable List<zza> list);
    }

    @AutoValue
    /* loaded from: classes10.dex */
    public static abstract class zza implements Parcelable {
        @NonNull
        public static zzba zzc() {
            return new zzf();
        }

        public abstract int zza();

        public abstract int zzb();
    }

    @NonNull
    public static Builder builder(@NonNull String str) {
        return new zzd().zza(str).setPlaceTypes(new ArrayList()).setFullText("").setPrimaryText("").setSecondaryText("");
    }

    @NonNull
    private static SpannableString zza(@NonNull String str, @Nullable List<zza> list, @Nullable CharacterStyle characterStyle) {
        SpannableString spannableString = new SpannableString(str);
        if (str.length() != 0 && characterStyle != null && list != null) {
            for (zza zzaVar : list) {
                spannableString.setSpan(CharacterStyle.wrap(characterStyle), zzaVar.zza(), zzaVar.zza() + zzaVar.zzb(), 0);
            }
        }
        return spannableString;
    }

    @Nullable
    public abstract Integer getDistanceMeters();

    @NonNull
    public SpannableString getFullText(@Nullable CharacterStyle characterStyle) {
        return zza(zza(), zzd(), characterStyle);
    }

    @NonNull
    public abstract String getPlaceId();

    @NonNull
    public abstract List<Place.Type> getPlaceTypes();

    @NonNull
    public SpannableString getPrimaryText(@Nullable CharacterStyle characterStyle) {
        return zza(zzb(), zze(), characterStyle);
    }

    @NonNull
    public SpannableString getSecondaryText(@Nullable CharacterStyle characterStyle) {
        return zza(zzc(), zzf(), characterStyle);
    }

    @NonNull
    public abstract String zza();

    @NonNull
    public abstract String zzb();

    @NonNull
    public abstract String zzc();

    @Nullable
    public abstract List<zza> zzd();

    @Nullable
    public abstract List<zza> zze();

    @Nullable
    public abstract List<zza> zzf();
}
