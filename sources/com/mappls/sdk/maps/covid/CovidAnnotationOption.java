package com.mappls.sdk.maps.covid;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.covid.a;
@AutoValue
/* loaded from: classes11.dex */
public abstract class CovidAnnotationOption {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract CovidAnnotationOption build();

        public abstract Builder color(@ColorInt Integer num);

        public abstract Builder opacity(Float f);

        public abstract Builder outlineColor(@ColorInt Integer num);
    }

    public static Builder a() {
        return new a.b().color(Integer.valueOf((int) ViewCompat.MEASURED_STATE_MASK));
    }

    @NonNull
    public abstract Integer b();

    @Nullable
    public abstract Float c();

    @Nullable
    public abstract Integer d();
}
