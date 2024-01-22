package com.google.mlkit.vision.barcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes10.dex */
public class ZoomSuggestionOptions {

    /* renamed from: a  reason: collision with root package name */
    public final ZoomCallback f11608a;
    public final float b;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final ZoomCallback f11609a;
        public float b;

        public Builder(@NonNull ZoomCallback zoomCallback) {
            this.f11609a = zoomCallback;
        }

        @NonNull
        public ZoomSuggestionOptions build() {
            return new ZoomSuggestionOptions(this.f11609a, this.b, null);
        }

        @NonNull
        public Builder setMaxSupportedZoomRatio(float f) {
            this.b = f;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public interface ZoomCallback {
        boolean setZoom(float f);
    }

    public /* synthetic */ ZoomSuggestionOptions(ZoomCallback zoomCallback, float f, zzb zzbVar) {
        this.f11608a = zoomCallback;
        this.b = f;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ZoomSuggestionOptions) {
            ZoomSuggestionOptions zoomSuggestionOptions = (ZoomSuggestionOptions) obj;
            return Objects.equal(this.f11608a, zoomSuggestionOptions.f11608a) && this.b == zoomSuggestionOptions.b;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f11608a, Float.valueOf(this.b));
    }

    public final float zza() {
        return this.b;
    }

    @NonNull
    public final ZoomCallback zzb() {
        return this.f11608a;
    }
}
