package com.coveiot.coveaccess.sports;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SportsUserPrefResponse {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6753a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName("data")
    @Expose
    private SportsPrefResData c;

    public SportsPrefResData getData() {
        return this.c;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.f6753a;
    }

    public void setData(SportsPrefResData sportsPrefResData) {
        this.c = sportsPrefResData;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.f6753a = str;
    }
}
