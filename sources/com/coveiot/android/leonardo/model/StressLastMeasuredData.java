package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class StressLastMeasuredData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Integer f4861a;
    @Nullable
    public final Long b;

    public StressLastMeasuredData(@Nullable Integer num, @Nullable Long l) {
        this.f4861a = num;
        this.b = l;
    }

    public static /* synthetic */ StressLastMeasuredData copy$default(StressLastMeasuredData stressLastMeasuredData, Integer num, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            num = stressLastMeasuredData.f4861a;
        }
        if ((i & 2) != 0) {
            l = stressLastMeasuredData.b;
        }
        return stressLastMeasuredData.copy(num, l);
    }

    @Nullable
    public final Integer component1() {
        return this.f4861a;
    }

    @Nullable
    public final Long component2() {
        return this.b;
    }

    @NotNull
    public final StressLastMeasuredData copy(@Nullable Integer num, @Nullable Long l) {
        return new StressLastMeasuredData(num, l);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StressLastMeasuredData) {
            StressLastMeasuredData stressLastMeasuredData = (StressLastMeasuredData) obj;
            return Intrinsics.areEqual(this.f4861a, stressLastMeasuredData.f4861a) && Intrinsics.areEqual(this.b, stressLastMeasuredData.b);
        }
        return false;
    }

    @Nullable
    public final Integer getStress() {
        return this.f4861a;
    }

    @Nullable
    public final Long getTimeStamp() {
        return this.b;
    }

    public int hashCode() {
        Integer num = this.f4861a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Long l = this.b;
        return hashCode + (l != null ? l.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "StressLastMeasuredData(stress=" + this.f4861a + ", timeStamp=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
