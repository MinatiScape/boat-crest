package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FCMFitnessReportSubscribeMessage {
    @SerializedName("medium")
    @Nullable
    private String medium;
    @SerializedName(Constants.KEY_MESSAGE)
    @Nullable
    private String message;
    @SerializedName("title")
    @Nullable
    private String title;
    @SerializedName("topic")
    @Nullable
    private String topic;

    @Nullable
    public final String getMedium() {
        return this.medium;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final String getTopic() {
        return this.topic;
    }

    public final void setMedium(@Nullable String str) {
        this.medium = str;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final void setTopic(@Nullable String str) {
        this.topic = str;
    }
}
