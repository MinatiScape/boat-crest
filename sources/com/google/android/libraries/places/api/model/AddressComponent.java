package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.internal.zzft;
import com.google.android.libraries.places.internal.zzgi;
import com.google.auto.value.AutoValue;
import java.util.Collection;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class AddressComponent implements Parcelable {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public AddressComponent build() {
            AddressComponent zza = zza();
            zzft.zzb(!zza.getName().isEmpty(), "Name must not be empty.");
            List<String> types = zza.getTypes();
            for (String str : types) {
                zzft.zzb(!TextUtils.isEmpty(str), "Types must not contain null or empty values.");
            }
            zza(zzgi.zza((Collection) types));
            return zza();
        }

        @Nullable
        public abstract String getShortName();

        @NonNull
        public abstract Builder setShortName(@Nullable String str);

        @NonNull
        public abstract Builder zza(@NonNull String str);

        @NonNull
        public abstract Builder zza(@NonNull List<String> list);

        @NonNull
        public abstract AddressComponent zza();
    }

    @NonNull
    public static Builder builder(@NonNull String str, @NonNull List<String> list) {
        return new zzc().zza(str).zza(list);
    }

    @NonNull
    public abstract String getName();

    @Nullable
    public abstract String getShortName();

    @NonNull
    public abstract List<String> getTypes();
}
