package com.mappls.sdk.geojson;

import androidx.annotation.Keep;
import java.io.Serializable;
@Keep
/* loaded from: classes11.dex */
public interface GeoJson extends Serializable {
    BoundingBox bbox();

    String toJson();

    String type();
}
