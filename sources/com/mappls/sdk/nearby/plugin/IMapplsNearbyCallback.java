package com.mappls.sdk.nearby.plugin;

import androidx.annotation.Keep;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes10.dex */
public interface IMapplsNearbyCallback {
    void getNearbyCallback(@NotNull NearbyAtlasResult nearbyAtlasResult);
}
