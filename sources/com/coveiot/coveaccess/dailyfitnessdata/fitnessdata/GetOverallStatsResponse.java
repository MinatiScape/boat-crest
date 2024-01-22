package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GetOverallStatsResponse {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private OverallStatsData f6461a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String c;

    public String getMessage() {
        return this.b;
    }

    public OverallStatsData getOverallStatsData() {
        return this.f6461a;
    }

    public String getStatus() {
        return this.c;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setOverallStatsData(OverallStatsData overallStatsData) {
        this.f6461a = overallStatsData;
    }

    public void setStatus(String str) {
        this.c = str;
    }

    public String toString() {
        return "GetOverallStatsResponse{overallStatsData=" + this.f6461a + ", message='" + this.b + "', status='" + this.c + "'}";
    }
}
