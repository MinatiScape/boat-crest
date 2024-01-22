package com.mappls.sdk.maps.location;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.location.engine.LocationEngine;
import com.mappls.sdk.maps.location.engine.LocationEngineRequest;
import java.util.Objects;
/* loaded from: classes11.dex */
public class LocationComponentActivationOptions {

    /* renamed from: a  reason: collision with root package name */
    public final Context f12746a;
    public final Style b;
    public final LocationEngine c;
    public final LocationEngineRequest d;
    public final LocationComponentOptions e;
    public final int f;
    public final boolean g;
    public final boolean h;

    /* loaded from: classes11.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Context f12747a;
        public final Style b;
        public LocationEngine c;
        public LocationEngineRequest d;
        public LocationComponentOptions e;
        public int f;
        public boolean g = true;
        public boolean h = false;

        public Builder(@NonNull Context context, @NonNull Style style) {
            this.f12747a = context;
            this.b = style;
        }

        public LocationComponentActivationOptions build() {
            if (this.f != 0 && this.e != null) {
                throw new IllegalArgumentException("You've provided both a style resource and a LocationComponentOptions object to the LocationComponentActivationOptions builder. You can't use both and you must choose one of the two to style the LocationComponent.");
            }
            Objects.requireNonNull(this.f12747a, "Context in LocationComponentActivationOptions is null.");
            Style style = this.b;
            Objects.requireNonNull(style, "Style in LocationComponentActivationOptions is null. Make sure the Style object isn't null. Wait for the map to fully load before passing the Style object to LocationComponentActivationOptions.");
            if (style.isFullyLoaded()) {
                return new LocationComponentActivationOptions(this.f12747a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            }
            throw new IllegalArgumentException("Style in LocationComponentActivationOptions isn't fully loaded. Wait for the map to fully load before passing the Style object to LocationComponentActivationOptions.");
        }

        public Builder locationComponentOptions(LocationComponentOptions locationComponentOptions) {
            this.e = locationComponentOptions;
            return this;
        }

        @NonNull
        public Builder locationEngine(@Nullable LocationEngine locationEngine) {
            this.c = locationEngine;
            return this;
        }

        public Builder locationEngineRequest(LocationEngineRequest locationEngineRequest) {
            this.d = locationEngineRequest;
            return this;
        }

        public Builder styleRes(int i) {
            this.f = i;
            return this;
        }

        public Builder useDefaultLocationEngine(boolean z) {
            this.g = z;
            return this;
        }

        public Builder useSpecializedLocationLayer(boolean z) {
            this.h = z;
            return this;
        }
    }

    @NonNull
    public static Builder builder(@NonNull Context context, @NonNull Style style) {
        return new Builder(context, style);
    }

    @NonNull
    public Context context() {
        return this.f12746a;
    }

    @Nullable
    public LocationComponentOptions locationComponentOptions() {
        return this.e;
    }

    @Nullable
    public LocationEngine locationEngine() {
        return this.c;
    }

    @Nullable
    public LocationEngineRequest locationEngineRequest() {
        return this.d;
    }

    @NonNull
    public Style style() {
        return this.b;
    }

    public int styleRes() {
        return this.f;
    }

    public boolean useDefaultLocationEngine() {
        return this.g;
    }

    public boolean useSpecializedLocationLayer() {
        return this.h;
    }

    public LocationComponentActivationOptions(@NonNull Context context, @NonNull Style style, @Nullable LocationEngine locationEngine, @Nullable LocationEngineRequest locationEngineRequest, @Nullable LocationComponentOptions locationComponentOptions, int i, boolean z, boolean z2) {
        this.f12746a = context;
        this.b = style;
        this.c = locationEngine;
        this.d = locationEngineRequest;
        this.e = locationComponentOptions;
        this.f = i;
        this.g = z;
        this.h = z2;
    }
}
