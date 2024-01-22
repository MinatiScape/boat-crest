package com.coveiot.android.leonardo.dashboard.vitals.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SleepInsightsModel {

    /* renamed from: a  reason: collision with root package name */
    public int f4828a;
    public int b;
    public int c;

    public SleepInsightsModel(int i, int i2, int i3) {
        this.f4828a = i;
        this.b = i2;
        this.c = i3;
    }

    public static /* synthetic */ SleepInsightsModel copy$default(SleepInsightsModel sleepInsightsModel, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = sleepInsightsModel.f4828a;
        }
        if ((i4 & 2) != 0) {
            i2 = sleepInsightsModel.b;
        }
        if ((i4 & 4) != 0) {
            i3 = sleepInsightsModel.c;
        }
        return sleepInsightsModel.copy(i, i2, i3);
    }

    public final int component1() {
        return this.f4828a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    @NotNull
    public final SleepInsightsModel copy(int i, int i2, int i3) {
        return new SleepInsightsModel(i, i2, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SleepInsightsModel) {
            SleepInsightsModel sleepInsightsModel = (SleepInsightsModel) obj;
            return this.f4828a == sleepInsightsModel.f4828a && this.b == sleepInsightsModel.b && this.c == sleepInsightsModel.c;
        }
        return false;
    }

    public final int getAwakeTime() {
        return this.b;
    }

    public final int getDeepSleep() {
        return this.c;
    }

    public final int getTotalSleep() {
        return this.f4828a;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.f4828a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c);
    }

    public final void setAwakeTime(int i) {
        this.b = i;
    }

    public final void setDeepSleep(int i) {
        this.c = i;
    }

    public final void setTotalSleep(int i) {
        this.f4828a = i;
    }

    @NotNull
    public String toString() {
        return "SleepInsightsModel(totalSleep=" + this.f4828a + ", awakeTime=" + this.b + ", deepSleep=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
