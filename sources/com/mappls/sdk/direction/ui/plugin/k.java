package com.mappls.sdk.direction.ui.plugin;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
/* loaded from: classes11.dex */
public final class k implements Style.OnStyleLoaded {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f12620a;

    public k(boolean z) {
        this.f12620a = z;
    }

    @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
    public final void onStyleLoaded(@NonNull Style style) {
        Layer layer = style.getLayer("com.mappls.sdk.directions.directions-marker-bearing-layer");
        if (layer != null) {
            PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
            propertyValueArr[0] = PropertyFactory.visibility(this.f12620a ? Property.VISIBLE : "none");
            layer.setProperties(propertyValueArr);
        }
    }
}
