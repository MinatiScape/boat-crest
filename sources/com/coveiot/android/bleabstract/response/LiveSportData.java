package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class LiveSportData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Integer f3643a;
    @Nullable
    public final Double b;
    @Nullable
    public final Integer c;

    public LiveSportData(@Nullable Integer num, @Nullable Double d, @Nullable Integer num2) {
        this.f3643a = num;
        this.b = d;
        this.c = num2;
    }

    public static /* synthetic */ LiveSportData copy$default(LiveSportData liveSportData, Integer num, Double d, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = liveSportData.f3643a;
        }
        if ((i & 2) != 0) {
            d = liveSportData.b;
        }
        if ((i & 4) != 0) {
            num2 = liveSportData.c;
        }
        return liveSportData.copy(num, d, num2);
    }

    @Nullable
    public final Integer component1() {
        return this.f3643a;
    }

    @Nullable
    public final Double component2() {
        return this.b;
    }

    @Nullable
    public final Integer component3() {
        return this.c;
    }

    @NotNull
    public final LiveSportData copy(@Nullable Integer num, @Nullable Double d, @Nullable Integer num2) {
        return new LiveSportData(num, d, num2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LiveSportData) {
            LiveSportData liveSportData = (LiveSportData) obj;
            return Intrinsics.areEqual(this.f3643a, liveSportData.f3643a) && Intrinsics.areEqual((Object) this.b, (Object) liveSportData.b) && Intrinsics.areEqual(this.c, liveSportData.c);
        }
        return false;
    }

    @Nullable
    public final Double getCalories() {
        return this.b;
    }

    @Nullable
    public final Integer getHeartRate() {
        return this.c;
    }

    @Nullable
    public final Integer getSteps() {
        return this.f3643a;
    }

    public int hashCode() {
        Integer num = this.f3643a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Double d = this.b;
        int hashCode2 = (hashCode + (d == null ? 0 : d.hashCode())) * 31;
        Integer num2 = this.c;
        return hashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "LiveSportData(steps=" + this.f3643a + ", calories=" + this.b + ", heartRate=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
