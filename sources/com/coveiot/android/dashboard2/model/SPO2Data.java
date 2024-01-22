package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SPO2Data extends FitnessVitalData {
    @Nullable
    private String level;
    private long timestamp;
    private double value;

    public SPO2Data() {
        this(0.0d, 0L, null, 7, null);
    }

    public /* synthetic */ SPO2Data(double d, long j, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0L : j, (i & 4) != 0 ? null : str);
    }

    public static /* synthetic */ SPO2Data copy$default(SPO2Data sPO2Data, double d, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            d = sPO2Data.value;
        }
        double d2 = d;
        if ((i & 2) != 0) {
            j = sPO2Data.timestamp;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            str = sPO2Data.level;
        }
        return sPO2Data.copy(d2, j2, str);
    }

    public final double component1() {
        return this.value;
    }

    public final long component2() {
        return this.timestamp;
    }

    @Nullable
    public final String component3() {
        return this.level;
    }

    @NotNull
    public final SPO2Data copy(double d, long j, @Nullable String str) {
        return new SPO2Data(d, j, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SPO2Data) {
            SPO2Data sPO2Data = (SPO2Data) obj;
            return Double.compare(this.value, sPO2Data.value) == 0 && this.timestamp == sPO2Data.timestamp && Intrinsics.areEqual(this.level, sPO2Data.level);
        }
        return false;
    }

    @Nullable
    public final String getLevel() {
        return this.level;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final double getValue() {
        return this.value;
    }

    public int hashCode() {
        int hashCode = ((Double.hashCode(this.value) * 31) + Long.hashCode(this.timestamp)) * 31;
        String str = this.level;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final void setLevel(@Nullable String str) {
        this.level = str;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final void setValue(double d) {
        this.value = d;
    }

    @NotNull
    public String toString() {
        return "SPO2Data(value=" + this.value + ", timestamp=" + this.timestamp + ", level=" + this.level + HexStringBuilder.COMMENT_END_CHAR;
    }

    public SPO2Data(double d, long j, @Nullable String str) {
        this.value = d;
        this.timestamp = j;
        this.level = str;
    }
}
