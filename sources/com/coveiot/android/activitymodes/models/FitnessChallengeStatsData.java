package com.coveiot.android.activitymodes.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengeStatsData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f2850a;
    @Nullable
    public Integer b;
    @Nullable
    public Integer c;

    public FitnessChallengeStatsData(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        this.f2850a = num;
        this.b = num2;
        this.c = num3;
    }

    public static /* synthetic */ FitnessChallengeStatsData copy$default(FitnessChallengeStatsData fitnessChallengeStatsData, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = fitnessChallengeStatsData.f2850a;
        }
        if ((i & 2) != 0) {
            num2 = fitnessChallengeStatsData.b;
        }
        if ((i & 4) != 0) {
            num3 = fitnessChallengeStatsData.c;
        }
        return fitnessChallengeStatsData.copy(num, num2, num3);
    }

    @Nullable
    public final Integer component1() {
        return this.f2850a;
    }

    @Nullable
    public final Integer component2() {
        return this.b;
    }

    @Nullable
    public final Integer component3() {
        return this.c;
    }

    @NotNull
    public final FitnessChallengeStatsData copy(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        return new FitnessChallengeStatsData(num, num2, num3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessChallengeStatsData) {
            FitnessChallengeStatsData fitnessChallengeStatsData = (FitnessChallengeStatsData) obj;
            return Intrinsics.areEqual(this.f2850a, fitnessChallengeStatsData.f2850a) && Intrinsics.areEqual(this.b, fitnessChallengeStatsData.b) && Intrinsics.areEqual(this.c, fitnessChallengeStatsData.c);
        }
        return false;
    }

    @Nullable
    public final Integer getTotalCalories() {
        return this.b;
    }

    @Nullable
    public final Integer getTotalDistance() {
        return this.c;
    }

    @Nullable
    public final Integer getTotalSteps() {
        return this.f2850a;
    }

    public int hashCode() {
        Integer num = this.f2850a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.b;
        int hashCode2 = (hashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.c;
        return hashCode2 + (num3 != null ? num3.hashCode() : 0);
    }

    public final void setTotalCalories(@Nullable Integer num) {
        this.b = num;
    }

    public final void setTotalDistance(@Nullable Integer num) {
        this.c = num;
    }

    public final void setTotalSteps(@Nullable Integer num) {
        this.f2850a = num;
    }

    @NotNull
    public String toString() {
        return "FitnessChallengeStatsData(totalSteps=" + this.f2850a + ", totalCalories=" + this.b + ", totalDistance=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
