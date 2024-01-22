package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ProcessedFitnessData {
    private double calorie;
    private int distance;
    private int steps;

    public ProcessedFitnessData() {
        this(0, 0, 0.0d, 7, null);
    }

    public ProcessedFitnessData(int i, int i2, double d) {
        this.steps = i;
        this.distance = i2;
        this.calorie = d;
    }

    public static /* synthetic */ ProcessedFitnessData copy$default(ProcessedFitnessData processedFitnessData, int i, int i2, double d, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = processedFitnessData.steps;
        }
        if ((i3 & 2) != 0) {
            i2 = processedFitnessData.distance;
        }
        if ((i3 & 4) != 0) {
            d = processedFitnessData.calorie;
        }
        return processedFitnessData.copy(i, i2, d);
    }

    public final int component1() {
        return this.steps;
    }

    public final int component2() {
        return this.distance;
    }

    public final double component3() {
        return this.calorie;
    }

    @NotNull
    public final ProcessedFitnessData copy(int i, int i2, double d) {
        return new ProcessedFitnessData(i, i2, d);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProcessedFitnessData) {
            ProcessedFitnessData processedFitnessData = (ProcessedFitnessData) obj;
            return this.steps == processedFitnessData.steps && this.distance == processedFitnessData.distance && Double.compare(this.calorie, processedFitnessData.calorie) == 0;
        }
        return false;
    }

    public final double getCalorie() {
        return this.calorie;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final int getSteps() {
        return this.steps;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.steps) * 31) + Integer.hashCode(this.distance)) * 31) + Double.hashCode(this.calorie);
    }

    public final void setCalorie(double d) {
        this.calorie = d;
    }

    public final void setDistance(int i) {
        this.distance = i;
    }

    public final void setSteps(int i) {
        this.steps = i;
    }

    @NotNull
    public String toString() {
        return "ProcessedFitnessData(steps=" + this.steps + ", distance=" + this.distance + ", calorie=" + this.calorie + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ ProcessedFitnessData(int i, int i2, double d, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? 0.0d : d);
    }
}
