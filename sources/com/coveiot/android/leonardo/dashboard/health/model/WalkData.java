package com.coveiot.android.leonardo.dashboard.health.model;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class WalkData implements Serializable {
    @NotNull
    private String calories;
    @NotNull
    private String distance;
    @NotNull
    private String dwmValue;
    private int steps;
    private int stepsTarget;
    @Nullable
    private String type;
    private int year;

    public WalkData(int i, @NotNull String calories, @NotNull String distance, @NotNull String dwmValue) {
        Intrinsics.checkNotNullParameter(calories, "calories");
        Intrinsics.checkNotNullParameter(distance, "distance");
        Intrinsics.checkNotNullParameter(dwmValue, "dwmValue");
        this.stepsTarget = 10000;
        this.steps = i;
        this.calories = calories;
        this.distance = distance;
        this.dwmValue = dwmValue;
    }

    @NotNull
    public final String getCalories() {
        return this.calories;
    }

    @NotNull
    public final String getDistance() {
        return this.distance;
    }

    @NotNull
    public final String getDwmValue() {
        return this.dwmValue;
    }

    public final int getSteps() {
        return this.steps;
    }

    public final int getStepsTarget() {
        return this.stepsTarget;
    }

    @Nullable
    public final String getType() {
        return this.type;
    }

    @Nullable
    public final String getType$app_prodRelease() {
        return this.type;
    }

    public final int getYear() {
        return this.year;
    }

    public final void setCalories$app_prodRelease(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.calories = str;
    }

    public final void setDistance$app_prodRelease(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.distance = str;
    }

    public final void setDwmValue$app_prodRelease(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dwmValue = str;
    }

    public final void setSteps$app_prodRelease(int i) {
        this.steps = i;
    }

    public final void setStepsTarget(int i) {
        this.stepsTarget = i;
    }

    @NotNull
    public final WalkData setType(@NotNull String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        return this;
    }

    public final void setType$app_prodRelease(@Nullable String str) {
        this.type = str;
    }

    public final void setYear(int i) {
        this.year = i;
    }
}
