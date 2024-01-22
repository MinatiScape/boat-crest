package com.coveiot.android.navigation.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DirectionsDataForBand implements Serializable {
    private final long distance;
    @NotNull
    private final String instruction;
    private final int manevurId;

    public DirectionsDataForBand(@NotNull String instruction, long j, int i) {
        Intrinsics.checkNotNullParameter(instruction, "instruction");
        this.instruction = instruction;
        this.distance = j;
        this.manevurId = i;
    }

    public static /* synthetic */ DirectionsDataForBand copy$default(DirectionsDataForBand directionsDataForBand, String str, long j, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = directionsDataForBand.instruction;
        }
        if ((i2 & 2) != 0) {
            j = directionsDataForBand.distance;
        }
        if ((i2 & 4) != 0) {
            i = directionsDataForBand.manevurId;
        }
        return directionsDataForBand.copy(str, j, i);
    }

    @NotNull
    public final String component1() {
        return this.instruction;
    }

    public final long component2() {
        return this.distance;
    }

    public final int component3() {
        return this.manevurId;
    }

    @NotNull
    public final DirectionsDataForBand copy(@NotNull String instruction, long j, int i) {
        Intrinsics.checkNotNullParameter(instruction, "instruction");
        return new DirectionsDataForBand(instruction, j, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DirectionsDataForBand) {
            DirectionsDataForBand directionsDataForBand = (DirectionsDataForBand) obj;
            return Intrinsics.areEqual(this.instruction, directionsDataForBand.instruction) && this.distance == directionsDataForBand.distance && this.manevurId == directionsDataForBand.manevurId;
        }
        return false;
    }

    public final long getDistance() {
        return this.distance;
    }

    @NotNull
    public final String getInstruction() {
        return this.instruction;
    }

    public final int getManevurId() {
        return this.manevurId;
    }

    public int hashCode() {
        return (((this.instruction.hashCode() * 31) + Long.hashCode(this.distance)) * 31) + Integer.hashCode(this.manevurId);
    }

    @NotNull
    public String toString() {
        return "DirectionsDataForBand(instruction=" + this.instruction + ", distance=" + this.distance + ", manevurId=" + this.manevurId + HexStringBuilder.COMMENT_END_CHAR;
    }
}
