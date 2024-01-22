package com.mappls.sdk.nearby.plugin;

import androidx.annotation.Keep;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes10.dex */
public interface INearbyResultCallback {
    void onCancel();

    void onSelectResult(@NotNull NearbyAtlasResult nearbyAtlasResult);
}
