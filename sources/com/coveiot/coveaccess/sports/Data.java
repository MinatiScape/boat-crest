package com.coveiot.coveaccess.sports;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Data {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6742a;
    @SerializedName("response")
    @Expose
    private Response b;
    @SerializedName("api_version")
    @Expose
    private String c;

    public String getApiVersion() {
        return this.c;
    }

    public Response getResponse() {
        return this.b;
    }

    public String getStatus() {
        return this.f6742a;
    }

    public void setApiVersion(String str) {
        this.c = str;
    }

    public void setResponse(Response response) {
        this.b = response;
    }

    public void setStatus(String str) {
        this.f6742a = str;
    }
}
