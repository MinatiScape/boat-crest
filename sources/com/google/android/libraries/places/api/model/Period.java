package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class Period implements Parcelable {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public abstract Period build();

        @Nullable
        public abstract TimeOfWeek getClose();

        @Nullable
        public abstract TimeOfWeek getOpen();

        @NonNull
        public abstract Builder setClose(@Nullable TimeOfWeek timeOfWeek);

        @NonNull
        public abstract Builder setOpen(@Nullable TimeOfWeek timeOfWeek);
    }

    @NonNull
    public static Builder builder() {
        return new zzo();
    }

    @Nullable
    public abstract TimeOfWeek getClose();

    @Nullable
    public abstract TimeOfWeek getOpen();
}
