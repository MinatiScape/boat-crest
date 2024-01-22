package com.coveiot.android.sleepenergyscore.sleepscore.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {DeviceKey.MacAddress, "date"}, tableName = "sleep_score_table")
/* loaded from: classes6.dex */
public final class SleepScoreData {
    @SerializedName("sleepScore")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private Integer f5745a;
    @SerializedName("totalSleep")
    @Nullable
    private String b;
    @SerializedName("algoIdentifier")
    @Nullable
    private String c;
    @SerializedName("computedDate")
    @Nullable
    private String d;
    @NonNull
    public String date;
    @SerializedName("timeToDeepSleep")
    @Nullable
    private Integer e;
    @SerializedName("wascoCount")
    @Nullable
    private Integer f;
    @SerializedName("sleepConsistency")
    @Nullable
    private String g;
    @NonNull
    @SerializedName("lastSyncedDate")
    @Nullable
    private Long h = 0L;
    @SerializedName("questionnaireId")
    @Nullable
    private String i;
    @SerializedName("questionAnswerFeedback")
    @Nullable
    private ArrayList<QuestionAnswerSleepData> j;
    @NonNull
    @SerializedName(DeviceKey.MacAddress)
    public String macAddress;

    @Nullable
    public final String getAlgoIdentifier() {
        return this.c;
    }

    @Nullable
    public final String getComputedDate() {
        return this.d;
    }

    @NotNull
    public final String getDate() {
        String str = this.date;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("date");
        return null;
    }

    @Nullable
    public final ArrayList<QuestionAnswerSleepData> getFeedbackList() {
        return this.j;
    }

    @Nullable
    public final Long getLastSyncedDate() {
        return this.h;
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

    @Nullable
    public final String getQuestionnaireId() {
        return this.i;
    }

    @Nullable
    public final String getSleepConsistency() {
        return this.g;
    }

    @Nullable
    public final Integer getSleepScore() {
        return this.f5745a;
    }

    @Nullable
    public final Integer getTimeToDeepSleep() {
        return this.e;
    }

    @Nullable
    public final String getTotalSleep() {
        return this.b;
    }

    @Nullable
    public final Integer getWascoCount() {
        return this.f;
    }

    public final void setAlgoIdentifier(@Nullable String str) {
        this.c = str;
    }

    public final void setComputedDate(@Nullable String str) {
        this.d = str;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final void setFeedbackList(@Nullable ArrayList<QuestionAnswerSleepData> arrayList) {
        this.j = arrayList;
    }

    public final void setLastSyncedDate(@Nullable Long l) {
        this.h = l;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.macAddress = str;
    }

    public final void setQuestionnaireId(@Nullable String str) {
        this.i = str;
    }

    public final void setSleepConsistency(@Nullable String str) {
        this.g = str;
    }

    public final void setSleepScore(@Nullable Integer num) {
        this.f5745a = num;
    }

    public final void setTimeToDeepSleep(@Nullable Integer num) {
        this.e = num;
    }

    public final void setTotalSleep(@Nullable String str) {
        this.b = str;
    }

    public final void setWascoCount(@Nullable Integer num) {
        this.f = num;
    }
}
