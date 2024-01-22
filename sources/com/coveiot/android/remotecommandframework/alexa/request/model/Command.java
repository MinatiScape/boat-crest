package com.coveiot.android.remotecommandframework.alexa.request.model;

import com.clevertap.android.sdk.Constants;
import com.coveiot.android.remotecommandframework.alexa.response.model.RemoteBaseResponse;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public class Command {
    @SerializedName("da")
    @Nullable
    private Object data;
    @SerializedName("id")
    @Nullable
    private String id;
    @SerializedName("mg")
    @Nullable
    private String message;
    @SerializedName("ty")
    @Nullable
    private String name;
    @SerializedName(Constants.NOTIF_PRIORITY)
    private int priority;
    @SerializedName("response")
    @Nullable
    private RemoteBaseResponse remoteBaseResponse;
    @SerializedName("st")
    @Nullable
    private String status;

    @Nullable
    public final Object getData() {
        return this.data;
    }

    @Nullable
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    public final int getPriority() {
        return this.priority;
    }

    @Nullable
    public final RemoteBaseResponse getRemoteBaseResponse() {
        return this.remoteBaseResponse;
    }

    @Nullable
    public final String getStatus() {
        return this.status;
    }

    public final void setData(@Nullable Object obj) {
        this.data = obj;
    }

    public final void setId(@Nullable String str) {
        this.id = str;
    }

    public final void setMessage(@Nullable String str) {
        this.message = str;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setPriority(int i) {
        this.priority = i;
    }

    public final void setRemoteBaseResponse(@Nullable RemoteBaseResponse remoteBaseResponse) {
        this.remoteBaseResponse = remoteBaseResponse;
    }

    public final void setStatus(@Nullable String str) {
        this.status = str;
    }
}
