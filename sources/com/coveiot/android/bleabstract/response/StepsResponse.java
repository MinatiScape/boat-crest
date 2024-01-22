package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class StepsResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public StepsDayData f3668a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;

    @Nullable
    public final StepsDayData getStepsDayData() {
        return this.f3668a;
    }

    public final boolean isBandSupportBasicCalories() {
        return this.e;
    }

    public final boolean isCaloriesDistanceCalculatedFromBand() {
        return this.c;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final boolean isDailyWalkDataCalculateFromBand() {
        return this.f;
    }

    public final boolean isDistanceIsInMetresFromBand() {
        return this.d;
    }

    public final void setBandSupportBasicCalories(boolean z) {
        this.e = z;
    }

    public final void setCaloriesDistanceCalculatedFromBand(boolean z) {
        this.c = z;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setDailyWalkDataCalculateFromBand(boolean z) {
        this.f = z;
    }

    public final void setDistanceIsInMetresFromBand(boolean z) {
        this.d = z;
    }

    public final void setStepsDayData(@Nullable StepsDayData stepsDayData) {
        this.f3668a = stepsDayData;
    }
}
