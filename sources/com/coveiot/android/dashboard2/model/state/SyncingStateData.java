package com.coveiot.android.dashboard2.model.state;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SyncingStateData {
    private int batteryPercentage;
    @Nullable
    private String lastSyncTime;
    @Nullable
    private String name;
    @Nullable
    private String status;
    @Nullable
    private String title;
    @Nullable
    private Integer watchDrawableId;
    @Nullable
    private String watchName;

    public SyncingStateData() {
        this(null, null, null, 0, null, null, null, 127, null);
    }

    public SyncingStateData(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, @Nullable String str4, @Nullable String str5, @Nullable Integer num) {
        this.title = str;
        this.name = str2;
        this.status = str3;
        this.batteryPercentage = i;
        this.lastSyncTime = str4;
        this.watchName = str5;
        this.watchDrawableId = num;
    }

    public static /* synthetic */ SyncingStateData copy$default(SyncingStateData syncingStateData, String str, String str2, String str3, int i, String str4, String str5, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = syncingStateData.title;
        }
        if ((i2 & 2) != 0) {
            str2 = syncingStateData.name;
        }
        String str6 = str2;
        if ((i2 & 4) != 0) {
            str3 = syncingStateData.status;
        }
        String str7 = str3;
        if ((i2 & 8) != 0) {
            i = syncingStateData.batteryPercentage;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            str4 = syncingStateData.lastSyncTime;
        }
        String str8 = str4;
        if ((i2 & 32) != 0) {
            str5 = syncingStateData.watchName;
        }
        String str9 = str5;
        if ((i2 & 64) != 0) {
            num = syncingStateData.watchDrawableId;
        }
        return syncingStateData.copy(str, str6, str7, i3, str8, str9, num);
    }

    @Nullable
    public final String component1() {
        return this.title;
    }

    @Nullable
    public final String component2() {
        return this.name;
    }

    @Nullable
    public final String component3() {
        return this.status;
    }

    public final int component4() {
        return this.batteryPercentage;
    }

    @Nullable
    public final String component5() {
        return this.lastSyncTime;
    }

    @Nullable
    public final String component6() {
        return this.watchName;
    }

    @Nullable
    public final Integer component7() {
        return this.watchDrawableId;
    }

    @NotNull
    public final SyncingStateData copy(@Nullable String str, @Nullable String str2, @Nullable String str3, int i, @Nullable String str4, @Nullable String str5, @Nullable Integer num) {
        return new SyncingStateData(str, str2, str3, i, str4, str5, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SyncingStateData) {
            SyncingStateData syncingStateData = (SyncingStateData) obj;
            return Intrinsics.areEqual(this.title, syncingStateData.title) && Intrinsics.areEqual(this.name, syncingStateData.name) && Intrinsics.areEqual(this.status, syncingStateData.status) && this.batteryPercentage == syncingStateData.batteryPercentage && Intrinsics.areEqual(this.lastSyncTime, syncingStateData.lastSyncTime) && Intrinsics.areEqual(this.watchName, syncingStateData.watchName) && Intrinsics.areEqual(this.watchDrawableId, syncingStateData.watchDrawableId);
        }
        return false;
    }

    public final int getBatteryPercentage() {
        return this.batteryPercentage;
    }

    @Nullable
    public final String getLastSyncTime() {
        return this.lastSyncTime;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final Integer getWatchDrawableId() {
        return this.watchDrawableId;
    }

    @Nullable
    public final String getWatchName() {
        return this.watchName;
    }

    public int hashCode() {
        String str = this.title;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.status;
        int hashCode3 = (((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Integer.hashCode(this.batteryPercentage)) * 31;
        String str4 = this.lastSyncTime;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.watchName;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Integer num = this.watchDrawableId;
        return hashCode5 + (num != null ? num.hashCode() : 0);
    }

    public final void setBatteryPercentage(int i) {
        this.batteryPercentage = i;
    }

    public final void setLastSyncTime(@Nullable String str) {
        this.lastSyncTime = str;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final void setWatchDrawableId(@Nullable Integer num) {
        this.watchDrawableId = num;
    }

    public final void setWatchName(@Nullable String str) {
        this.watchName = str;
    }

    @NotNull
    public String toString() {
        return "SyncingStateData(title=" + this.title + ", name=" + this.name + ", status=" + this.status + ", batteryPercentage=" + this.batteryPercentage + ", lastSyncTime=" + this.lastSyncTime + ", watchName=" + this.watchName + ", watchDrawableId=" + this.watchDrawableId + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ SyncingStateData(String str, String str2, String str3, int i, String str4, String str5, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) != 0 ? null : num);
    }
}
