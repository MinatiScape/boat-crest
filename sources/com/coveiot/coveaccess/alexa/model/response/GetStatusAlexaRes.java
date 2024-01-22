package com.coveiot.coveaccess.alexa.model.response;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.alexa.model.EnablementData;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GetStatusAlexaRes {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private EnablementData f6414a = null;
    @SerializedName(Constants.KEY_MESSAGE)
    private String b = null;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String c = null;

    public EnablementData getData() {
        return this.f6414a;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.c;
    }

    public void setData(EnablementData enablementData) {
        this.f6414a = enablementData;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.c = str;
    }
}
