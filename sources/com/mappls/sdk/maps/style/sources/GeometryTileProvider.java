package com.mappls.sdk.maps.style.sources;

import androidx.annotation.WorkerThread;
import com.mappls.sdk.geojson.FeatureCollection;
import com.mappls.sdk.maps.geometry.LatLngBounds;
/* loaded from: classes11.dex */
public interface GeometryTileProvider {
    @WorkerThread
    FeatureCollection getFeaturesForBounds(LatLngBounds latLngBounds, int i);
}
