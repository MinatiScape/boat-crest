package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.List;
/* loaded from: classes11.dex */
public class u implements MapplsMap.OnMapClickListener {
    public MapplsMap h;
    public OnPlaceClickListener i;

    public u(MapplsMap mapplsMap) {
        this.h = mapplsMap;
        mapplsMap.addOnMapClickListener(this);
    }

    public void a(@NonNull OnPlaceClickListener onPlaceClickListener) {
        this.i = onPlaceClickListener;
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMapClickListener
    public boolean onMapClick(@NonNull LatLng latLng) {
        if (this.i == null) {
            return false;
        }
        List<Feature> queryRenderedFeatures = this.h.queryRenderedFeatures(this.h.getProjection().toScreenLocation(latLng), new String[0]);
        if (queryRenderedFeatures.size() > 0) {
            JsonObject properties = queryRenderedFeatures.get(queryRenderedFeatures.size() - 1).properties();
            if (properties != null && properties.size() > 0) {
                String asString = (!properties.has("ELOC") || properties.get("ELOC").isJsonNull()) ? null : properties.get("ELOC").getAsString();
                if (properties.has("name_en") && !properties.get("name_en").isJsonNull()) {
                    properties.get("name_en").getAsString();
                }
                if (asString == null) {
                    asString = (!properties.has("eLoc") || properties.get("eLoc").isJsonNull()) ? null : properties.get("eLoc").getAsString();
                }
                if (asString == null) {
                    asString = (!properties.has("placeId") || properties.get("placeId").isJsonNull()) ? null : properties.get("placeId").getAsString();
                }
                if (asString != null) {
                    return this.i.onPlaceClick(asString);
                }
                this.i.onPlaceClick(null);
            }
        } else {
            this.i.onPlaceClick(null);
        }
        return false;
    }
}
