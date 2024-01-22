package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class EnergyMeterData extends FitnessVitalData {
    private long timestamp;
    private int value;

    public EnergyMeterData() {
        this(0, 0L, 3, null);
    }

    public EnergyMeterData(int i, long j) {
        this.value = i;
        this.timestamp = j;
    }

    public static /* synthetic */ EnergyMeterData copy$default(EnergyMeterData energyMeterData, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = energyMeterData.value;
        }
        if ((i2 & 2) != 0) {
            j = energyMeterData.timestamp;
        }
        return energyMeterData.copy(i, j);
    }

    public final int component1() {
        return this.value;
    }

    public final long component2() {
        return this.timestamp;
    }

    @NotNull
    public final EnergyMeterData copy(int i, long j) {
        return new EnergyMeterData(i, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnergyMeterData) {
            EnergyMeterData energyMeterData = (EnergyMeterData) obj;
            return this.value == energyMeterData.value && this.timestamp == energyMeterData.timestamp;
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
        return "EnergyMeterData(value=" + this.value + ", timestamp=" + this.timestamp + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EnergyMeterData(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0L : j);
    }
}
