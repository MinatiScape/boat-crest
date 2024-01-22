package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.NavigationItem;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetNavigationApplicationContentRequest extends BleBaseRequest {
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final long k;
    @NotNull
    public final List<NavigationItem> l;

    public SetNavigationApplicationContentRequest(int i, int i2, int i3, int i4, int i5, long j, @NotNull List<NavigationItem> navigationItems) {
        Intrinsics.checkNotNullParameter(navigationItems, "navigationItems");
        this.f = i;
        this.g = i2;
        this.h = i3;
        this.i = i4;
        this.j = i5;
        this.k = j;
        this.l = navigationItems;
    }

    public final int getAppId() {
        return this.f;
    }

    public final int getDisplayPosition() {
        return this.g;
    }

    public final long getDistance() {
        return this.k;
    }

    public final int getEtaDay() {
        return this.h;
    }

    public final int getEtaHour() {
        return this.i;
    }

    public final int getEtaMin() {
        return this.j;
    }

    @NotNull
    public final List<NavigationItem> getNavigationItems() {
        return this.l;
    }
}
