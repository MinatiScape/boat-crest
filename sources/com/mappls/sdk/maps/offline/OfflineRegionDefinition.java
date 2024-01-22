package com.mappls.sdk.maps.offline;

import android.os.Parcelable;
import androidx.annotation.Keep;
import com.mappls.sdk.maps.geometry.LatLngBounds;
@Keep
/* loaded from: classes11.dex */
public interface OfflineRegionDefinition extends Parcelable {
    LatLngBounds getBounds();

    boolean getIncludeIdeographs();

    double getMaxZoom();

    double getMinZoom();

    float getPixelRatio();

    String getStyleURL();

    String getType();
}
