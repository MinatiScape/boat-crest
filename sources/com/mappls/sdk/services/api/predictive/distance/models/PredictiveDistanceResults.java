package com.mappls.sdk.services.api.predictive.distance.models;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDistanceResults {
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("from_index")
    @Expose
    private Integer fromIndex;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    @Expose
    private Integer time;
    @SerializedName("to_index")
    @Expose
    private Integer toIndex;

    public Double getDistance() {
        return this.distance;
    }

    public Integer getFromIndex() {
        return this.fromIndex;
    }

    public Integer getTime() {
        return this.time;
    }

    public Integer getToIndex() {
        return this.toIndex;
    }

    public void setDistance(Double d) {
        this.distance = d;
    }

    public void setFromIndex(Integer num) {
        this.fromIndex = num;
    }

    public void setTime(Integer num) {
        this.time = num;
    }

    public void setToIndex(Integer num) {
        this.toIndex = num;
    }
}
