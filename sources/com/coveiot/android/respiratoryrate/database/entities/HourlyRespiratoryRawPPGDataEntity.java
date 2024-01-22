package com.coveiot.android.respiratoryrate.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(foreignKeys = {@ForeignKey(childColumns = {"date", DeviceKey.MacAddress}, entity = DailyRespiratoryRawPPGHistoryEntity.class, parentColumns = {"date", DeviceKey.MacAddress})}, primaryKeys = {"timestamp", DeviceKey.MacAddress}, tableName = "hourly_raw_ppg_history_table")
/* loaded from: classes6.dex */
public final class HourlyRespiratoryRawPPGDataEntity {
    @NonNull
    @SerializedName("date")
    public String date;
    @SerializedName("duration")
    private int duration;
    @SerializedName("interval")
    private int interval;
    @NonNull
    @SerializedName(DeviceKey.MacAddress)
    public String macAddress;
    @SerializedName("movementLevel")
    private int movementLevel;
    @SerializedName("ppgType")
    private int ppgType;
    @SerializedName("ppgValues")
    @Nullable
    private ArrayList<Integer> ppgValues;
    @SerializedName("recordNumber")
    private int recordNumber;
    @SerializedName("samplingRate")
    private int samplingRate;
    @NonNull
    @SerializedName("timestamp")
    private long timestamp;

    @NotNull
    public final String getDate() {
        String str = this.date;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("date");
        return null;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final int getInterval() {
        return this.interval;
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

    public final int getMovementLevel() {
        return this.movementLevel;
    }

    public final int getPpgType() {
        return this.ppgType;
    }

    @Nullable
    public final ArrayList<Integer> getPpgValues() {
        return this.ppgValues;
    }

    public final int getRecordNumber() {
        return this.recordNumber;
    }

    public final int getSamplingRate() {
        return this.samplingRate;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final void setInterval(int i) {
        this.interval = i;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.macAddress = str;
    }

    public final void setMovementLevel(int i) {
        this.movementLevel = i;
    }

    public final void setPpgType(int i) {
        this.ppgType = i;
    }

    public final void setPpgValues(@Nullable ArrayList<Integer> arrayList) {
        this.ppgValues = arrayList;
    }

    public final void setRecordNumber(int i) {
        this.recordNumber = i;
    }

    public final void setSamplingRate(int i) {
        this.samplingRate = i;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }
}
