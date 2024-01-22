package com.mappls.sdk.direction.ui;

import androidx.annotation.Keep;
import com.mappls.sdk.direction.ui.model.DirectionPoint;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public interface DirectionCallback {
    void onCancel();

    void onStartNavigation(DirectionPoint directionPoint, DirectionPoint directionPoint2, List<DirectionPoint> list, DirectionsResponse directionsResponse, int i);
}
