package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class StressData extends FitnessVitalData {
    @Nullable
    private String level;
    private long timestamp;
    private int value;

    public StressData() {
        this(0, null, 0L, 7, null);
    }

    public /* synthetic */ StressData(int i, String str, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? 0L : j);
    }

    public static /* synthetic */ StressData copy$default(StressData stressData, int i, String str, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = stressData.value;
        }
        if ((i2 & 2) != 0) {
            str = stressData.level;
        }
        if ((i2 & 4) != 0) {
            j = stressData.timestamp;
        }
        return stressData.copy(i, str, j);
    }

    public final int component1() {
        return this.value;
    }

    @Nullable
    public final String component2() {
        return this.level;
    }

    public final long component3() {
        return this.timestamp;
    }

    @NotNull
    public final StressData copy(int i, @Nullable String str, long j) {
        return new StressData(i, str, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StressData) {
            StressData stressData = (StressData) obj;
            return this.value == stressData.value && Intrinsics.areEqual(this.level, stressData.level) && this.timestamp == stressData.timestamp;
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

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.value) * 31;
        String str = this.level;
        return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Long.hashCode(this.timestamp);
    }

    public final void setLevel(@Nullable String str) {
        this.level = str;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final void setValue(int i) {
        this.value = i;
    }

    @NotNull
    public String toString() {
        return "StressData(value=" + this.value + ", level=" + this.level + ", timestamp=" + this.timestamp + HexStringBuilder.COMMENT_END_CHAR;
    }

    public StressData(int i, @Nullable String str, long j) {
        this.value = i;
        this.level = str;
        this.timestamp = j;
    }
}
