package com.coveiot.coveaccess.onekactivity;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPhysicalActivityCategoriesRes {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6678a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName("data")
    @Expose
    private ActivityCategoryData c;

    public ActivityCategoryData getData() {
        return this.c;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.f6678a;
    }

    public void setData(ActivityCategoryData activityCategoryData) {
        this.c = activityCategoryData;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.f6678a = str;
    }
}
