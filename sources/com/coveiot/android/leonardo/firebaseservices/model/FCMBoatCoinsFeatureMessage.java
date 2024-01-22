package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FCMBoatCoinsFeatureMessage {
    @SerializedName("bgImageUrl")
    @Nullable
    private String bgImageUrl;
    @SerializedName(Constants.KEY_MESSAGE)
    @Nullable
    private String message;
    @SerializedName("notifIdentifier")
    @Nullable
    private String notifIdentifier;
    @SerializedName("title")
    @Nullable
    private String title;
    @SerializedName("webViewUrl")
    @Nullable
    private String webViewUrl;

    @Nullable
    public final String getBgImageUrl() {
        return this.bgImageUrl;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getNotifIdentifier() {
        return this.notifIdentifier;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final String getWebViewUrl() {
        return this.webViewUrl;
    }

    public final void setBgImageUrl(@Nullable String str) {
        this.bgImageUrl = str;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }

    public final void setNotifIdentifier(@Nullable String str) {
        this.notifIdentifier = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final void setWebViewUrl(@Nullable String str) {
        this.webViewUrl = str;
    }
}
