package com.coveiot.android.khperformancecalculator.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class KHPSleepData {

    /* renamed from: a  reason: collision with root package name */
    public final int f4641a;
    public final int b;
    @NotNull
    public final String c;

    public KHPSleepData(int i, int i2, @NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.f4641a = i;
        this.b = i2;
        this.c = date;
    }

    public static /* synthetic */ KHPSleepData copy$default(KHPSleepData kHPSleepData, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = kHPSleepData.f4641a;
        }
        if ((i3 & 2) != 0) {
            i2 = kHPSleepData.b;
        }
        if ((i3 & 4) != 0) {
            str = kHPSleepData.c;
        }
        return kHPSleepData.copy(i, i2, str);
    }

    public final int component1() {
        return this.f4641a;
    }

    public final int component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final KHPSleepData copy(int i, int i2, @NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return new KHPSleepData(i, i2, date);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KHPSleepData) {
            KHPSleepData kHPSleepData = (KHPSleepData) obj;
            return this.f4641a == kHPSleepData.f4641a && this.b == kHPSleepData.b && Intrinsics.areEqual(this.c, kHPSleepData.c);
        }
        return false;
    }

    @NotNull
    public final String getDate() {
        return this.c;
    }

    public final int getTargetSleep() {
        return this.b;
    }

    public final int getTotalSleep() {
        return this.f4641a;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.f4641a) * 31) + Integer.hashCode(this.b)) * 31) + this.c.hashCode();
    }

    @NotNull
    public String toString() {
        return "KHPSleepData(totalSleep=" + this.f4641a + ", targetSleep=" + this.b + ", date=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
