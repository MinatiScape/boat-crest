package com.coveiot.coveaccess.fitness.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AllFitnessRecordsResponse {
    @SerializedName("data")
    @Expose
    private AllFitnessRecordData allFitnessRecordData;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public AllFitnessRecordData getAllFitnessRecordModel() {
        return this.allFitnessRecordData;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setAllFitnessRecordModel(AllFitnessRecordData allFitnessRecordData) {
        this.allFitnessRecordData = allFitnessRecordData;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
