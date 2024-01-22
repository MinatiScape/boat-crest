package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SGetDailyFitnessAggregateResponse {
    @SerializedName("data")
    @Expose
    public FitnessAggregateData aggregateData = null;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;

    /* loaded from: classes8.dex */
    public class FitnessAggregateData {
        @SerializedName("fitnessDataAggregates")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<FitnessDataAggregate> f6456a;

        public FitnessAggregateData(SGetDailyFitnessAggregateResponse sGetDailyFitnessAggregateResponse) {
        }

        public List<FitnessDataAggregate> getFitnessDataAggregates() {
            return this.f6456a;
        }

        public void setFitnessDataAggregates(List<FitnessDataAggregate> list) {
            this.f6456a = list;
        }
    }

    public FitnessAggregateData getAggregateData() {
        return this.aggregateData;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setAggregateData(FitnessAggregateData fitnessAggregateData) {
        this.aggregateData = fitnessAggregateData;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
