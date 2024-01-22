package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class RespiratoryRateData extends FitnessVitalData {
    private long timestamp;
    private int value;

    public RespiratoryRateData() {
        this(0, 0L, 3, null);
    }

    public RespiratoryRateData(int i, long j) {
        this.value = i;
        this.timestamp = j;
    }

    public static /* synthetic */ RespiratoryRateData copy$default(RespiratoryRateData respiratoryRateData, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = respiratoryRateData.value;
        }
        if ((i2 & 2) != 0) {
            j = respiratoryRateData.timestamp;
        }
        return respiratoryRateData.copy(i, j);
    }

    public final int component1() {
        return this.value;
    }

    public final long component2() {
        return this.timestamp;
    }

    @NotNull
    public final RespiratoryRateData copy(int i, long j) {
        return new RespiratoryRateData(i, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RespiratoryRateData) {
            RespiratoryRateData respiratoryRateData = (RespiratoryRateData) obj;
            return this.value == respiratoryRateData.value && this.timestamp == respiratoryRateData.timestamp;
        }
        return false;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        return (Integer.hashCode(this.value) * 31) + Long.hashCode(this.timestamp);
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final void setValue(int i) {
        this.value = i;
    }

    @NotNull
    public String toString() {
        return "RespiratoryRateData(value=" + this.value + ", timestamp=" + this.timestamp + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ RespiratoryRateData(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0L : j);
    }
}
