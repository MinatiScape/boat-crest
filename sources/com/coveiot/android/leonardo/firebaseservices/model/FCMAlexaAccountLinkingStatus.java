package com.coveiot.android.leonardo.firebaseservices.model;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FCMAlexaAccountLinkingStatus {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Nullable
    private String status;
    @SerializedName("userDeviceId")
    @Nullable
    private Long userDeviceId;

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    @Nullable
    public final Long getUserDeviceId() {
        return this.userDeviceId;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }

    public final void setUserDeviceId(@Nullable Long l) {
        this.userDeviceId = l;
    }
}
