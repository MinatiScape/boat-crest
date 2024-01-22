package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.fitness.common.FitnessGoal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SCreateFitnessGoalResponse {
    @SerializedName("data")
    @Expose
    public FitnessGoal data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;
}