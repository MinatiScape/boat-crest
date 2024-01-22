package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class InsightStepsHourModel {

    /* renamed from: a  reason: collision with root package name */
    public boolean f4787a;
    public int b;
    public int c;

    public InsightStepsHourModel(boolean z, int i, int i2) {
        this.f4787a = z;
        this.b = i;
        this.c = i2;
    }

    public static /* synthetic */ InsightStepsHourModel copy$default(InsightStepsHourModel insightStepsHourModel, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = insightStepsHourModel.f4787a;
        }
        if ((i3 & 2) != 0) {
            i = insightStepsHourModel.b;
        }
        if ((i3 & 4) != 0) {
            i2 = insightStepsHourModel.c;
        }
        return insightStepsHourModel.copy(z, i, i2);
    }

    public final boolean component1() {
        return this.f4787a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    @NotNull
    public final InsightStepsHourModel copy(boolean z, int i, int i2) {
        return new InsightStepsHourModel(z, i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InsightStepsHourModel) {
            InsightStepsHourModel insightStepsHourModel = (InsightStepsHourModel) obj;
            return this.f4787a == insightStepsHourModel.f4787a && this.b == insightStepsHourModel.b && this.c == insightStepsHourModel.c;
        }
        return false;
    }

    public final int getNoOfDays() {
        return this.c;
    }

    public final int getTimeTakenInMinutes() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.f4787a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((r0 * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c);
    }

    public final boolean isGoalAchieved() {
        return this.f4787a;
    }

    public final void setGoalAchieved(boolean z) {
        this.f4787a = z;
    }

    public final void setNoOfDays(int i) {
        this.c = i;
    }

    public final void setTimeTakenInMinutes(int i) {
        this.b = i;
    }

    @NotNull
    public String toString() {
        return "InsightStepsHourModel(isGoalAchieved=" + this.f4787a + ", timeTakenInMinutes=" + this.b + ", noOfDays=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
