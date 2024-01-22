package com.coveiot.android.smartalert.model;

import com.mappls.sdk.maps.style.layers.Property;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum WatchShape {
    ROUND("round"),
    RECTANGLE("rectangle"),
    SQUARE(Property.LINE_CAP_SQUARE);
    
    @NotNull
    private final String shape;

    WatchShape(String str) {
        this.shape = str;
    }

    @NotNull
    public final String getShape() {
        return this.shape;
    }
}
