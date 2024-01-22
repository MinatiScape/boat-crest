package com.mappls.sdk.navigation.ui.map.route;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import java.util.List;
/* loaded from: classes11.dex */
public class g implements Style.OnStyleLoaded {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f12979a;
    public final /* synthetic */ f b;

    public g(f fVar, boolean z) {
        this.b = fVar;
        this.f12979a = z;
    }

    @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
    public void onStyleLoaded(@NonNull Style style) {
        List<String> list;
        list = this.b.e;
        for (String str : list) {
            Layer layer = style.getLayer(str);
            if (layer != null) {
                String str2 = this.f12979a ? Property.VISIBLE : "none";
                if (!str2.equals(layer.getVisibility().getValue())) {
                    layer.setProperties(PropertyFactory.visibility(str2));
                }
            }
        }
    }
}
