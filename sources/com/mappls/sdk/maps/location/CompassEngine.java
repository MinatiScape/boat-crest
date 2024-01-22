package com.mappls.sdk.maps.location;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface CompassEngine {
    void addCompassListener(@NonNull CompassListener compassListener);

    int getLastAccuracySensorStatus();

    float getLastHeading();

    void removeCompassListener(@NonNull CompassListener compassListener);
}
