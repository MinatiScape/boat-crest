package com.google.android.libraries.places.api.net;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.internal.zzdc;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class FetchPhotoRequest implements zzdc {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public FetchPhotoRequest build() {
            PhotoMetadata zza = zza();
            if (getMaxWidth() == null && getMaxHeight() == null && zza != null) {
                int width = zza.getWidth();
                if (width > 0) {
                    setMaxWidth(Integer.valueOf(width));
                }
                int height = zza.getHeight();
                if (height > 0) {
                    setMaxHeight(Integer.valueOf(height));
                }
            }
            return zzb();
        }

        @Nullable
        public abstract CancellationToken getCancellationToken();

        @Nullable
        public abstract Integer getMaxHeight();

        @Nullable
        public abstract Integer getMaxWidth();

        @NonNull
        public abstract Builder setCancellationToken(@Nullable CancellationToken cancellationToken);

        @NonNull
        public abstract Builder setMaxHeight(@IntRange(from = 1, to = 1600) @Nullable Integer num);

        @NonNull
        public abstract Builder setMaxWidth(@IntRange(from = 1, to = 1600) @Nullable Integer num);

        @Nullable
        public abstract PhotoMetadata zza();

        @NonNull
        public abstract Builder zza(@NonNull PhotoMetadata photoMetadata);

        @NonNull
        public abstract FetchPhotoRequest zzb();
    }

    @NonNull
    public static Builder builder(@NonNull PhotoMetadata photoMetadata) {
        return new zzd().zza(photoMetadata);
    }

    @NonNull
    public static FetchPhotoRequest newInstance(@NonNull PhotoMetadata photoMetadata) {
        return builder(photoMetadata).build();
    }

    @Override // com.google.android.libraries.places.internal.zzdc
    @Nullable
    public abstract CancellationToken getCancellationToken();

    @IntRange(from = 1, to = 1600)
    @Nullable
    public abstract Integer getMaxHeight();

    @IntRange(from = 1, to = 1600)
    @Nullable
    public abstract Integer getMaxWidth();

    @NonNull
    public abstract PhotoMetadata getPhotoMetadata();
}
