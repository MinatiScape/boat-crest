package com.mappls.sdk.navigation.camera;

import android.location.Location;
import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public interface ProgressChangeListener {
    void onProgressChange(Location location, RouteInformation routeInformation);
}
