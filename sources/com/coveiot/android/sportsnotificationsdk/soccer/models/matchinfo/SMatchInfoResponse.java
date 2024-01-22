package com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class SMatchInfoResponse {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)

    /* renamed from: a  reason: collision with root package name */
    private String f5990a;
    @SerializedName("response")
    private Response b;
    @SerializedName("etag")
    private String c;
    @SerializedName("modified")
    private String d;
    @SerializedName("datetime")
    private String e;
    @SerializedName("api_version")
    private String f;

    public String getApiVersion() {
        return this.f;
    }

    public String getDatetime() {
        return this.e;
    }

    public String getEtag() {
        return this.c;
    }

    public String getModified() {
        return this.d;
    }

    public Response getResponse() {
        return this.b;
    }

    public String getStatus() {
        return this.f5990a;
    }

    public void setApiVersion(String str) {
        this.f = str;
    }

    public void setDatetime(String str) {
        this.e = str;
    }

    public void setEtag(String str) {
        this.c = str;
    }

    public void setModified(String str) {
        this.d = str;
    }

    public void setResponse(Response response) {
        this.b = response;
    }

    public void setStatus(String str) {
        this.f5990a = str;
    }
}
