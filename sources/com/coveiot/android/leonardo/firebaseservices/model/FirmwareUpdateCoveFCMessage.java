package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FirmwareUpdateCoveFCMessage {
    @SerializedName("customerId")
    @Nullable
    private Integer customerId;
    @SerializedName(Constants.KEY_MESSAGE)
    @Nullable
    private String message;
    @SerializedName("modelNumber")
    @Nullable
    private String modelNumber;
    @SerializedName("title")
    @Nullable
    private String title;
    @SerializedName("updateVersion")
    @Nullable
    private String updateVersion;

    @Nullable
    public final Integer getCustomerId() {
        return this.customerId;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getModelNumber() {
        return this.modelNumber;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final String getUpdateVersion() {
        return this.updateVersion;
    }

    public final void setCustomerId(@Nullable Integer num) {
        this.customerId = num;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }

    public final void setModelNumber(@Nullable String str) {
        this.modelNumber = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final void setUpdateVersion(@Nullable String str) {
        this.updateVersion = str;
    }
}
