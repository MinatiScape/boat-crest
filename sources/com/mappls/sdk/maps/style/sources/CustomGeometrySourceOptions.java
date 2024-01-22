package com.mappls.sdk.maps.style.sources;

import androidx.annotation.NonNull;
import java.util.HashMap;
/* loaded from: classes11.dex */
public class CustomGeometrySourceOptions extends HashMap<String, Object> {
    @NonNull
    public CustomGeometrySourceOptions withBuffer(int i) {
        put("buffer", Integer.valueOf(i));
        return this;
    }

    @NonNull
    public CustomGeometrySourceOptions withClip(boolean z) {
        put("clip", Boolean.valueOf(z));
        return this;
    }

    @NonNull
    public CustomGeometrySourceOptions withMaxZoom(int i) {
        put("maxzoom", Integer.valueOf(i));
        return this;
    }

    @NonNull
    public CustomGeometrySourceOptions withMinZoom(int i) {
        put("minzoom", Integer.valueOf(i));
        return this;
    }

    @NonNull
    public CustomGeometrySourceOptions withTolerance(float f) {
        put("tolerance", Float.valueOf(f));
        return this;
    }

    @NonNull
    public CustomGeometrySourceOptions withWrap(boolean z) {
        put("wrap", Boolean.valueOf(z));
        return this;
    }
}
