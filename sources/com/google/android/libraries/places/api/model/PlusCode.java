package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class PlusCode implements Parcelable {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public abstract PlusCode build();

        @Nullable
        public abstract String getCompoundCode();

        @Nullable
        public abstract String getGlobalCode();

        @NonNull
        public abstract Builder setCompoundCode(@Nullable String str);

        @NonNull
        public abstract Builder setGlobalCode(@Nullable String str);
    }

    @NonNull
    public static Builder builder() {
        return new zzt();
    }

    @Nullable
    public abstract String getCompoundCode();

    @Nullable
    public abstract String getGlobalCode();
}
