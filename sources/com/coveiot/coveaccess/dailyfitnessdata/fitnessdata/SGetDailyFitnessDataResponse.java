package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SGetDailyFitnessDataResponse {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private DailyFitnessData f6466a = null;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;

    /* loaded from: classes8.dex */
    public class DailyFitnessData {
        @SerializedName("fitnessData")
        @Expose
        public List<FitnessDatum> requestData;

        public DailyFitnessData(SGetDailyFitnessDataResponse sGetDailyFitnessDataResponse) {
        }

        public List<FitnessDatum> getRequestData() {
            return this.requestData;
        }

        public void setRequestData(List<FitnessDatum> list) {
            this.requestData = list;
        }
    }

    public DailyFitnessData getFitnessResponse() {
        return this.f6466a;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setFitnessResponse(DailyFitnessData dailyFitnessData) {
        this.f6466a = dailyFitnessData;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
