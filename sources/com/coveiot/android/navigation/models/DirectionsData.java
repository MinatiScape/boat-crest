package com.coveiot.android.navigation.models;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DirectionsData implements Serializable {
    @NotNull
    private final String distance;
    @NotNull
    private final String instruction;
    @Nullable
    private final Drawable manevurImage;

    public DirectionsData(@NotNull String instruction, @NotNull String distance, @Nullable Drawable drawable) {
        Intrinsics.checkNotNullParameter(instruction, "instruction");
        Intrinsics.checkNotNullParameter(distance, "distance");
        this.instruction = instruction;
        this.distance = distance;
        this.manevurImage = drawable;
    }

    public static /* synthetic */ DirectionsData copy$default(DirectionsData directionsData, String str, String str2, Drawable drawable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = directionsData.instruction;
        }
        if ((i & 2) != 0) {
            str2 = directionsData.distance;
        }
        if ((i & 4) != 0) {
            drawable = directionsData.manevurImage;
        }
        return directionsData.copy(str, str2, drawable);
    }

    @NotNull
    public final String component1() {
        return this.instruction;
    }

    @NotNull
    public final String component2() {
        return this.distance;
    }

    @Nullable
    public final Drawable component3() {
        return this.manevurImage;
    }

    @NotNull
    public final DirectionsData copy(@NotNull String instruction, @NotNull String distance, @Nullable Drawable drawable) {
        Intrinsics.checkNotNullParameter(instruction, "instruction");
        Intrinsics.checkNotNullParameter(distance, "distance");
        return new DirectionsData(instruction, distance, drawable);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DirectionsData) {
            DirectionsData directionsData = (DirectionsData) obj;
            return Intrinsics.areEqual(this.instruction, directionsData.instruction) && Intrinsics.areEqual(this.distance, directionsData.distance) && Intrinsics.areEqual(this.manevurImage, directionsData.manevurImage);
        }
        return false;
    }

    @NotNull
    public final String getDistance() {
        return this.distance;
    }

    @NotNull
    public final String getInstruction() {
        return this.instruction;
    }

    @Nullable
    public final Drawable getManevurImage() {
        return this.manevurImage;
    }

    public int hashCode() {
        int hashCode = ((this.instruction.hashCode() * 31) + this.distance.hashCode()) * 31;
        Drawable drawable = this.manevurImage;
        return hashCode + (drawable == null ? 0 : drawable.hashCode());
    }

    @NotNull
    public String toString() {
        return "DirectionsData(instruction=" + this.instruction + ", distance=" + this.distance + ", manevurImage=" + this.manevurImage + HexStringBuilder.COMMENT_END_CHAR;
    }
}
