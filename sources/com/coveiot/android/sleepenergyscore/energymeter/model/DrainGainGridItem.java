package com.coveiot.android.sleepenergyscore.energymeter.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class DrainGainGridItem {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f5719a;
    @Nullable
    public String b;

    public DrainGainGridItem() {
        this(null, null, 3, null);
    }

    public DrainGainGridItem(@Nullable Integer num, @Nullable String str) {
        this.f5719a = num;
        this.b = str;
    }

    public static /* synthetic */ DrainGainGridItem copy$default(DrainGainGridItem drainGainGridItem, Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = drainGainGridItem.f5719a;
        }
        if ((i & 2) != 0) {
            str = drainGainGridItem.b;
        }
        return drainGainGridItem.copy(num, str);
    }

    @Nullable
    public final Integer component1() {
        return this.f5719a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final DrainGainGridItem copy(@Nullable Integer num, @Nullable String str) {
        return new DrainGainGridItem(num, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DrainGainGridItem) {
            DrainGainGridItem drainGainGridItem = (DrainGainGridItem) obj;
            return Intrinsics.areEqual(this.f5719a, drainGainGridItem.f5719a) && Intrinsics.areEqual(this.b, drainGainGridItem.b);
        }
        return false;
    }

    @Nullable
    public final Integer getColor() {
        return this.f5719a;
    }

    @Nullable
    public final String getName() {
        return this.b;
    }

    public int hashCode() {
        Integer num = this.f5719a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.b;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public final void setColor(@Nullable Integer num) {
        this.f5719a = num;
    }

    public final void setName(@Nullable String str) {
        this.b = str;
    }

    @NotNull
    public String toString() {
        return "DrainGainGridItem(color=" + this.f5719a + ", name=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DrainGainGridItem(Integer num, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0 : num, (i & 2) != 0 ? "" : str);
    }
}
