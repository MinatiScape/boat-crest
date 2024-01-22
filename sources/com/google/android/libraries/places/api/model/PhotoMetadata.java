package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.google.android.libraries.places.internal.zzft;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class PhotoMetadata implements Parcelable {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public PhotoMetadata build() {
            PhotoMetadata zza = zza();
            int width = zza.getWidth();
            zzft.zza(width >= 0, "Width must not be < 0, but was: %s.", width);
            int height = zza.getHeight();
            zzft.zza(height >= 0, "Height must not be < 0, but was: %s.", height);
            zzft.zzb(!TextUtils.isEmpty(zza.zza()), "PhotoReference must not be null or empty.");
            return zza;
        }

        @NonNull
        public abstract String getAttributions();

        @IntRange(from = 0)
        public abstract int getHeight();

        @IntRange(from = 0)
        public abstract int getWidth();

        @NonNull
        public abstract Builder setAttributions(@NonNull String str);

        @NonNull
        public abstract Builder setHeight(@IntRange(from = 0) int i);

        @NonNull
        public abstract Builder setWidth(@IntRange(from = 0) int i);

        @NonNull
        public abstract Builder zza(@NonNull String str);

        @NonNull
        public abstract PhotoMetadata zza();
    }

    @NonNull
    public static Builder builder(@NonNull String str) {
        return new zzq().zza(str).setWidth(0).setHeight(0).setAttributions("");
    }

    @NonNull
    public abstract String getAttributions();

    @IntRange(from = 0)
    public abstract int getHeight();

    @IntRange(from = 0)
    public abstract int getWidth();

    @NonNull
    public abstract String zza();
}
