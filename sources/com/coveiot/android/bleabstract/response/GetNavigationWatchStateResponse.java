package com.coveiot.android.bleabstract.response;

import com.coveiot.sdk.ble.api.model.NavigationWatchStateData;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetNavigationWatchStateResponse {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final NavigationWatchStateData f3617a = new NavigationWatchStateData();

    @NotNull
    public final NavigationWatchStateData getNavigationWatchStateData() {
        return this.f3617a;
    }

    @NotNull
    public String toString() {
        return "(navigationWatchStateData =" + this.f3617a + " })";
    }
}
