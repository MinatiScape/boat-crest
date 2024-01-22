package com.coveiot.khsmadb.deviceInfo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity
/* loaded from: classes8.dex */
public final class KhSmaDeviceInfo {
    @PrimaryKey
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7142a;
    @ColumnInfo(name = "stepDataLastSyncTime")
    @Nullable
    public Long b;
    @ColumnInfo(name = "hrDataLastSyncTime")
    @Nullable
    public Long c;
    @ColumnInfo(name = "sleepDataLastSyncTime")
    @Nullable
    public Long d;
    @ColumnInfo(name = "temperatureDataLastSyncTime")
    @Nullable
    public Long e;
    @ColumnInfo(name = "bpDataLastSyncTime")
    @Nullable
    public Long f;
    @ColumnInfo(name = "spO2DataLastSyncTime")
    @Nullable
    public Long g;

    public KhSmaDeviceInfo(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7142a = macAddress;
    }

    public static /* synthetic */ KhSmaDeviceInfo copy$default(KhSmaDeviceInfo khSmaDeviceInfo, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = khSmaDeviceInfo.f7142a;
        }
        return khSmaDeviceInfo.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.f7142a;
    }

    @NotNull
    public final KhSmaDeviceInfo copy(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return new KhSmaDeviceInfo(macAddress);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof KhSmaDeviceInfo) && Intrinsics.areEqual(this.f7142a, ((KhSmaDeviceInfo) obj).f7142a);
    }

    @Nullable
    public final Long getBpDataLastSyncTime() {
        return this.f;
    }

    @Nullable
    public final Long getHrDataLastSyncTime() {
        return this.c;
    }

    @NotNull
    public final String getMacAddress() {
        return this.f7142a;
    }

    @Nullable
    public final Long getSleepDataLastSyncTime() {
        return this.d;
    }

    @Nullable
    public final Long getSpO2DataLastSyncTime() {
        return this.g;
    }

    @Nullable
    public final Long getStepDataLastSyncTime() {
        return this.b;
    }

    @Nullable
    public final Long getTemperatureDataLastSyncTime() {
        return this.e;
    }

    public int hashCode() {
        return this.f7142a.hashCode();
    }

    public final void setBpDataLastSyncTime(@Nullable Long l) {
        this.f = l;
    }

    public final void setHrDataLastSyncTime(@Nullable Long l) {
        this.c = l;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7142a = str;
    }

    public final void setSleepDataLastSyncTime(@Nullable Long l) {
        this.d = l;
    }

    public final void setSpO2DataLastSyncTime(@Nullable Long l) {
        this.g = l;
    }

    public final void setStepDataLastSyncTime(@Nullable Long l) {
        this.b = l;
    }

    public final void setTemperatureDataLastSyncTime(@Nullable Long l) {
        this.e = l;
    }

    @NotNull
    public String toString() {
        return "KhSmaDeviceInfo(macAddress='" + this.f7142a + "', stepDataLastSyncTime=" + this.b + ", hrDataLastSyncTime=" + this.c + ", sleepDataLastSyncTime=" + this.d + ", temperatureDataLastSyncTime=" + this.e + ", bpDataLastSyncTime=" + this.f + ",, spO2DataLastSyncTime=" + this.g + HexStringBuilder.COMMENT_END_CHAR;
    }
}
