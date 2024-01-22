package com.mappls.sdk.maps.covid;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.maps.covid.d;
@AutoValue
/* loaded from: classes11.dex */
public abstract class RasterOptions {

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract Builder belowLayer(String str);

        public abstract Builder brightnessMax(@FloatRange(from = 0.0d, to = 1.0d) Float f);

        public abstract Builder brightnessMin(Float f);

        public abstract RasterOptions build();

        public abstract Builder contrast(Float f);

        public abstract Builder fadeDuration(Float f);

        public abstract Builder hueRotate(Float f);

        public abstract Builder opacity(Float f);

        public abstract Builder resampling(String str);

        public abstract Builder saturation(Float f);

        public abstract Builder styles(Boolean bool);

        public abstract Builder type(String str);

        public abstract Builder visibility(Boolean bool);
    }

    public static Builder d() {
        return new d.b();
    }

    @Nullable
    public abstract String a();

    @Nullable
    public abstract Float b();

    @Nullable
    public abstract Float c();

    @Nullable
    public abstract Float e();

    @Nullable
    public abstract Float f();

    @Nullable
    public abstract Float g();

    @Nullable
    public abstract Float h();

    @Nullable
    public abstract String i();

    @Nullable
    public abstract Float j();

    @Nullable
    public abstract Boolean k();

    @NonNull
    public abstract String l();

    @Nullable
    public abstract Boolean m();
}
