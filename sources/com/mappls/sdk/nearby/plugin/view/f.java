package com.mappls.sdk.nearby.plugin.view;

import com.mappls.sdk.nearby.plugin.model.MapplsNearbyResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public interface f {
    void onChangeLocationRequest(@Nullable Double d, @Nullable Double d2, @Nullable String str);

    void onSelectCategory(@NotNull MapplsNearbyResponse mapplsNearbyResponse);
}
