package com.coveiot.android.respiratoryrate.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {DeviceKey.MacAddress, "mDate"}, tableName = "daily_respiratory_rate")
/* loaded from: classes6.dex */
public final class DailyRespiratoryRateEntity {
    @SerializedName("data")
    @Embedded
    @JvmField
    @Nullable
    public RespiratoryRateData data;
    @NonNull
    @SerializedName("isSyncedWithServer")
    @JvmField
    @Nullable
    public Integer isSyncedWithServer = 0;
    @NonNull
    public String mDate;
    @NonNull
    @SerializedName(DeviceKey.MacAddress)
    public String macAddress;

    @NotNull
    public final String getMDate() {
        String str = this.mDate;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mDate");
        return null;
    }

    @NotNull
    public final String getMacAddress() {
        String str = this.macAddress;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DeviceKey.MacAddress);
        return null;
    }

    public final void setMDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mDate = str;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.macAddress = str;
    }
}
