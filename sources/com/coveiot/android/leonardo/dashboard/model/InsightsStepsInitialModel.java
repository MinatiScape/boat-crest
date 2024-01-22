package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class InsightsStepsInitialModel {

    /* renamed from: a  reason: collision with root package name */
    public double f4790a;
    public double b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;

    public InsightsStepsInitialModel(double d, double d2, int i, int i2, int i3, int i4, int i5, int i6) {
        this.f4790a = d;
        this.b = d2;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = i6;
    }

    public final double component1() {
        return this.f4790a;
    }

    public final double component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    public final int component6() {
        return this.f;
    }

    public final int component7() {
        return this.g;
    }

    public final int component8() {
        return this.h;
    }

    @NotNull
    public final InsightsStepsInitialModel copy(double d, double d2, int i, int i2, int i3, int i4, int i5, int i6) {
        return new InsightsStepsInitialModel(d, d2, i, i2, i3, i4, i5, i6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InsightsStepsInitialModel) {
            InsightsStepsInitialModel insightsStepsInitialModel = (InsightsStepsInitialModel) obj;
            return Double.compare(this.f4790a, insightsStepsInitialModel.f4790a) == 0 && Double.compare(this.b, insightsStepsInitialModel.b) == 0 && this.c == insightsStepsInitialModel.c && this.d == insightsStepsInitialModel.d && this.e == insightsStepsInitialModel.e && this.f == insightsStepsInitialModel.f && this.g == insightsStepsInitialModel.g && this.h == insightsStepsInitialModel.h;
        }
        return false;
    }

    public final int getCaloriesCurrent() {
        return this.c;
    }

    public final int getCaloriesPrevious() {
        return this.d;
    }

    public final double getDistanceCurrent() {
        return this.f4790a;
    }

    public final double getDistancePrevious() {
        return this.b;
    }

    public final int getStepsCurrent() {
        return this.e;
    }

    public final int getStepsPrevious() {
        return this.f;
    }

    public final int getTotalNoOfDays() {
        return this.h;
    }

    public final int getTotalTimeCurrent() {
        return this.g;
    }

    public int hashCode() {
        return (((((((((((((Double.hashCode(this.f4790a) * 31) + Double.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Integer.hashCode(this.g)) * 31) + Integer.hashCode(this.h);
    }

    public final void setCaloriesCurrent(int i) {
        this.c = i;
    }

    public final void setCaloriesPrevious(int i) {
        this.d = i;
    }

    public final void setDistanceCurrent(double d) {
        this.f4790a = d;
    }

    public final void setDistancePrevious(double d) {
        this.b = d;
    }

    public final void setStepsCurrent(int i) {
        this.e = i;
    }

    public final void setStepsPrevious(int i) {
        this.f = i;
    }

    public final void setTotalNoOfDays(int i) {
        this.h = i;
    }

    public final void setTotalTimeCurrent(int i) {
        this.g = i;
    }

    @NotNull
    public String toString() {
        return "InsightsStepsInitialModel(distanceCurrent=" + this.f4790a + ", distancePrevious=" + this.b + ", caloriesCurrent=" + this.c + ", caloriesPrevious=" + this.d + ", stepsCurrent=" + this.e + ", stepsPrevious=" + this.f + ", totalTimeCurrent=" + this.g + ", totalNoOfDays=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }
}
