package com.coveiot.coveaccess.alexa.model.response;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class DeactivateAlexaRes {
    @SerializedName(Constants.KEY_MESSAGE)

    /* renamed from: a  reason: collision with root package name */
    private String f6413a = null;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String b = null;

    public String getMessage() {
        return this.f6413a;
    }

    public String getStatus() {
        return this.b;
    }

    public void setMessage(String str) {
        this.f6413a = str;
    }

    public void setStatus(String str) {
        this.b = str;
    }
}
