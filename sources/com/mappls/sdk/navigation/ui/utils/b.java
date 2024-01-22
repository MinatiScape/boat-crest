package com.mappls.sdk.navigation.ui.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.layers.Layer;
/* loaded from: classes11.dex */
public final class b {
    public static void a(@NonNull Style style, @NonNull Layer layer, @Nullable String str) {
        if (style.isFullyLoaded() || style.getLayer(layer.getId()) == null) {
            if (style.getLayer(str) == null) {
                style.addLayer(layer);
            } else {
                style.addLayerAbove(layer, str);
            }
        }
    }
}
