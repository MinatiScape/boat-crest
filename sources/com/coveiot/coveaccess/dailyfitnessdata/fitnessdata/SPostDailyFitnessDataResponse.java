package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPostDailyFitnessDataResponse {
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6467a;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String b;

    public String getMessage() {
        return this.f6467a;
    }

    public String getStatus() {
        return this.b;
    }

    public void setMessage(String str) {
        this.f6467a = str;
    }

    public void setStatus(String str) {
        this.b = str;
    }
}
