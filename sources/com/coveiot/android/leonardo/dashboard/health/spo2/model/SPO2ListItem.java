package com.coveiot.android.leonardo.dashboard.health.spo2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SPO2ListItem {
    @NotNull
    private String description;
    private int drawableResId;
    @NotNull
    private String name;

    public SPO2ListItem(@NotNull String name, @NotNull String description, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        this.name = name;
        this.description = description;
        this.drawableResId = i;
    }

    public static /* synthetic */ SPO2ListItem copy$default(SPO2ListItem sPO2ListItem, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = sPO2ListItem.name;
        }
        if ((i2 & 2) != 0) {
            str2 = sPO2ListItem.description;
        }
        if ((i2 & 4) != 0) {
            i = sPO2ListItem.drawableResId;
        }
        return sPO2ListItem.copy(str, str2, i);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.description;
    }

    public final int component3() {
        return this.drawableResId;
    }

    @NotNull
    public final SPO2ListItem copy(@NotNull String name, @NotNull String description, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        return new SPO2ListItem(name, description, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SPO2ListItem) {
            SPO2ListItem sPO2ListItem = (SPO2ListItem) obj;
            return Intrinsics.areEqual(this.name, sPO2ListItem.name) && Intrinsics.areEqual(this.description, sPO2ListItem.description) && this.drawableResId == sPO2ListItem.drawableResId;
        }
        return false;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    public final int getDrawableResId() {
        return this.drawableResId;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.description.hashCode()) * 31) + Integer.hashCode(this.drawableResId);
    }

    public final void setDescription(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.description = str;
    }

    public final void setDrawableResId(int i) {
        this.drawableResId = i;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @NotNull
    public String toString() {
        return "SPO2ListItem(name=" + this.name + ", description=" + this.description + ", drawableResId=" + this.drawableResId + HexStringBuilder.COMMENT_END_CHAR;
    }
}
