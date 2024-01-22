package com.coveiot.android.respiratoryrate.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Entity(primaryKeys = {"date", DeviceKey.MacAddress}, tableName = "daily_raw_ppg_history_table")
/* loaded from: classes6.dex */
public final class DailyRespiratoryRawPPGHistoryEntity {
    @NonNull
    @SerializedName("date")
    public String date;
    @NonNull
    @SerializedName(DeviceKey.MacAddress)
    public String macAddress;
    @SerializedName("syncedToServer")
    private int syncedToServer;

    @NotNull
    public final String getDate() {
        String str = this.date;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("date");
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

    public final int getSyncedToServer() {
        return this.syncedToServer;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.macAddress = str;
    }

    public final void setSyncedToServer(int i) {
        this.syncedToServer = i;
    }
}
