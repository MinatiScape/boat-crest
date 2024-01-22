package com.coveiot.coveaccess.activityfilter;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class ActivityFilter {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6377a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName("data")
    @Expose
    private Data c;

    public Data getData() {
        return this.c;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.f6377a;
    }

    public void setData(Data data) {
        this.c = data;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.f6377a = str;
    }
}
