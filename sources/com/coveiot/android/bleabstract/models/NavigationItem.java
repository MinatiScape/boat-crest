package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class NavigationItem implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public final int f3437a;
    @NotNull
    public final DynamicSportFieldTextV2 b;
    public final long c;

    public NavigationItem(int i, @NotNull DynamicSportFieldTextV2 dynamicSportField, long j) {
        Intrinsics.checkNotNullParameter(dynamicSportField, "dynamicSportField");
        this.f3437a = i;
        this.b = dynamicSportField;
        this.c = j;
    }

    public static /* synthetic */ NavigationItem copy$default(NavigationItem navigationItem, int i, DynamicSportFieldTextV2 dynamicSportFieldTextV2, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = navigationItem.f3437a;
        }
        if ((i2 & 2) != 0) {
            dynamicSportFieldTextV2 = navigationItem.b;
        }
        if ((i2 & 4) != 0) {
            j = navigationItem.c;
        }
        return navigationItem.copy(i, dynamicSportFieldTextV2, j);
    }

    public final int component1() {
        return this.f3437a;
    }

    @NotNull
    public final DynamicSportFieldTextV2 component2() {
        return this.b;
    }

    public final long component3() {
        return this.c;
    }

    @NotNull
    public final NavigationItem copy(int i, @NotNull DynamicSportFieldTextV2 dynamicSportField, long j) {
        Intrinsics.checkNotNullParameter(dynamicSportField, "dynamicSportField");
        return new NavigationItem(i, dynamicSportField, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NavigationItem) {
            NavigationItem navigationItem = (NavigationItem) obj;
            return this.f3437a == navigationItem.f3437a && Intrinsics.areEqual(this.b, navigationItem.b) && this.c == navigationItem.c;
        }
        return false;
    }

    public final long getDistance() {
        return this.c;
    }

    @NotNull
    public final DynamicSportFieldTextV2 getDynamicSportField() {
        return this.b;
    }

    public final int getImageId() {
        return this.f3437a;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.f3437a) * 31) + this.b.hashCode()) * 31) + Long.hashCode(this.c);
    }

    @NotNull
    public String toString() {
        return "NavigationItem(imageId=" + this.f3437a + ", dynamicSportField=" + this.b + ", distance=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
