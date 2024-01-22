package com.coveiot.android.remotecommandframework.alexa.response.model;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public class RemoteBaseResponse {
    @SerializedName("data")
    @Nullable
    private Object data;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Nullable
    private String status;

    @Nullable
    public final Object getData() {
        return this.data;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    public final void setData(@Nullable Object obj) {
        this.data = obj;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }
}
