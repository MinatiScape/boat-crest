package com.coveiot.coveaccess.activitysession;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SActivitySessionDataResponse {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private PostActivitySessionDataRequest f6392a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String c;

    public String getMessage() {
        return this.b;
    }

    public PostActivitySessionDataRequest getRequestData() {
        return this.f6392a;
    }

    public String getStatus() {
        return this.c;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setRequestData(PostActivitySessionDataRequest postActivitySessionDataRequest) {
        this.f6392a = postActivitySessionDataRequest;
    }

    public void setStatus(String str) {
        this.c = str;
    }
}
