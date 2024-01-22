package com.mappls.sdk.nearby.plugin.fragment;

import androidx.annotation.Keep;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes10.dex */
public interface NearbyResultCallback {
    void onLocationClick();

    void onNearbyResultClick(@NotNull NearbyAtlasResult nearbyAtlasResult);
}
