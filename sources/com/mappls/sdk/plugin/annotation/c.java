package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Nullable;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.sources.GeoJsonOptions;
import com.mappls.sdk.maps.style.sources.GeoJsonSource;
/* loaded from: classes11.dex */
public interface c<L extends Layer> {
    GeoJsonSource a(@Nullable GeoJsonOptions geoJsonOptions);

    String a();

    String b();

    L c();
}
