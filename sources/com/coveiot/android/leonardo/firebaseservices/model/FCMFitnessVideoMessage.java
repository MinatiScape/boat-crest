package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FCMFitnessVideoMessage {
    @SerializedName(Constants.KEY_MESSAGE)
    @Nullable
    private String message;
    @SerializedName("title")
    @Nullable
    private String title;

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }
}
