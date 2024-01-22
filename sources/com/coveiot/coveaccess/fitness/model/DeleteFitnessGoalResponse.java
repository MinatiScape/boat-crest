package com.coveiot.coveaccess.fitness.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class DeleteFitnessGoalResponse {
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public boolean status;

    public DeleteFitnessGoalResponse(String str, boolean z) {
        this.message = null;
        this.status = false;
        this.message = str;
        this.status = z;
    }
}
