package com.coveiot.android.sleepenergyscore.energymeter.database.entities;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.room.Entity;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {DeviceKey.MacAddress, "mDate"}, tableName = "energy_score_table")
/* loaded from: classes6.dex */
public final class EnergyScoreDbData {
    @SerializedName("data")
    @JvmField
    @Nullable
    public ArrayList<EnergyData> data;
    @SerializedName("questionAnswerFeedback")
    @JvmField
    @Nullable
    public ArrayList<QuestionAnswerData> feedbackList;
    @NonNull
    public String mDate;
    @NonNull
    @SerializedName(DeviceKey.MacAddress)
    public String macAddress;
    @SerializedName(Constants.KEY_MESSAGE)
    @JvmField
    @Nullable
    public String message;
    @SerializedName("questionnaireId")
    @JvmField
    @Nullable
    public String questionnaireId;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @JvmField
    @Nullable
    public String status;
    @NonNull
    @SerializedName("lastSyncedDate")
    @JvmField
    @Nullable
    public Long lastSyncedDate = 0L;
    @NonNull
    @SerializedName("isSyncedWithServer")
    @JvmField
    @Nullable
    public Integer isSyncedWithServer = 0;

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
