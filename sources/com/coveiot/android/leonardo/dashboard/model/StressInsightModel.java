package com.coveiot.android.leonardo.dashboard.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class StressInsightModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f4795a;
    public boolean b;

    public StressInsightModel() {
        this(null, false, 3, null);
    }

    public StressInsightModel(@Nullable Integer num, boolean z) {
        this.f4795a = num;
        this.b = z;
    }

    public static /* synthetic */ StressInsightModel copy$default(StressInsightModel stressInsightModel, Integer num, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            num = stressInsightModel.f4795a;
        }
        if ((i & 2) != 0) {
            z = stressInsightModel.b;
        }
        return stressInsightModel.copy(num, z);
    }

    @Nullable
    public final Integer component1() {
        return this.f4795a;
    }

    public final boolean component2() {
        return this.b;
    }

    @NotNull
    public final StressInsightModel copy(@Nullable Integer num, boolean z) {
        return new StressInsightModel(num, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StressInsightModel) {
            StressInsightModel stressInsightModel = (StressInsightModel) obj;
            return Intrinsics.areEqual(this.f4795a, stressInsightModel.f4795a) && this.b == stressInsightModel.b;
        }
        return false;
    }

    @Nullable
    public final Integer getStressDifference() {
        return this.f4795a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Integer num = this.f4795a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        boolean z = this.b;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public final boolean isStressIncreased() {
        return this.b;
    }

    public final void setStressDifference(@Nullable Integer num) {
        this.f4795a = num;
    }

    public final void setStressIncreased(boolean z) {
        this.b = z;
    }

    @NotNull
    public String toString() {
        return "StressInsightModel(stressDifference=" + this.f4795a + ", isStressIncreased=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ StressInsightModel(Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? false : z);
    }
}
