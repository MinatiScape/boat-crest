package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class StepsWeeklyMonthlyData {

    /* renamed from: a  reason: collision with root package name */
    public double f4794a;
    public double b;
    public double c;
    public int d;
    public int e;

    public StepsWeeklyMonthlyData(double d, double d2, double d3, int i, int i2) {
        this.f4794a = d;
        this.b = d2;
        this.c = d3;
        this.d = i;
        this.e = i2;
    }

    public final double component1() {
        return this.f4794a;
    }

    public final double component2() {
        return this.b;
    }

    public final double component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    @NotNull
    public final StepsWeeklyMonthlyData copy(double d, double d2, double d3, int i, int i2) {
        return new StepsWeeklyMonthlyData(d, d2, d3, i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StepsWeeklyMonthlyData) {
            StepsWeeklyMonthlyData stepsWeeklyMonthlyData = (StepsWeeklyMonthlyData) obj;
            return Double.compare(this.f4794a, stepsWeeklyMonthlyData.f4794a) == 0 && Double.compare(this.b, stepsWeeklyMonthlyData.b) == 0 && Double.compare(this.c, stepsWeeklyMonthlyData.c) == 0 && this.d == stepsWeeklyMonthlyData.d && this.e == stepsWeeklyMonthlyData.e;
        }
        return false;
    }

    public final double getAvgCalorie() {
        return this.c;
    }

    public final double getAvgDistanceKm() {
        return this.f4794a;
    }

    public final double getAvgDistanceMiles() {
        return this.b;
    }

    public final int getAvgSteps() {
        return this.d;
    }

    public final int getAvgTime() {
        return this.e;
    }

    public int hashCode() {
        return (((((((Double.hashCode(this.f4794a) * 31) + Double.hashCode(this.b)) * 31) + Double.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e);
    }

    public final void setAvgCalorie(double d) {
        this.c = d;
    }

    public final void setAvgDistanceKm(double d) {
        this.f4794a = d;
    }

    public final void setAvgDistanceMiles(double d) {
        this.b = d;
    }

    public final void setAvgSteps(int i) {
        this.d = i;
    }

    public final void setAvgTime(int i) {
        this.e = i;
    }

    @NotNull
    public String toString() {
        return "StepsWeeklyMonthlyData(avgDistanceKm=" + this.f4794a + ", avgDistanceMiles=" + this.b + ", avgCalorie=" + this.c + ", avgSteps=" + this.d + ", avgTime=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }
}
