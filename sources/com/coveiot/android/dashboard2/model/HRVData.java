package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class HRVData extends FitnessVitalData {
    private long timestamp;
    private double value;

    public HRVData() {
        this(0.0d, 0L, 3, null);
    }

    public HRVData(double d, long j) {
        this.value = d;
        this.timestamp = j;
    }

    public static /* synthetic */ HRVData copy$default(HRVData hRVData, double d, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            d = hRVData.value;
        }
        if ((i & 2) != 0) {
            j = hRVData.timestamp;
        }
        return hRVData.copy(d, j);
    }

    public final double component1() {
        return this.value;
    }

    public final long component2() {
        return this.timestamp;
    }

    @NotNull
    public final HRVData copy(double d, long j) {
        return new HRVData(d, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HRVData) {
            HRVData hRVData = (HRVData) obj;
            return Double.compare(this.value, hRVData.value) == 0 && this.timestamp == hRVData.timestamp;
        }
        return false;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final double getValue() {
        return this.value;
    }

    public int hashCode() {
        return (Double.hashCode(this.value) * 31) + Long.hashCode(this.timestamp);
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final void setValue(double d) {
        this.value = d;
    }

    @NotNull
    public String toString() {
        return "HRVData(value=" + this.value + ", timestamp=" + this.timestamp + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ HRVData(double d, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0L : j);
    }
}
