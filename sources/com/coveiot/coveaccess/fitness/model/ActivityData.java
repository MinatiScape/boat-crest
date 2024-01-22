package com.coveiot.coveaccess.fitness.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class ActivityData implements Serializable {
    @SerializedName("data")
    @Expose
    public List<ActivityDataModel> data = null;
    @SerializedName("fitnessLogs")
    @Expose
    public List<ActivityDataModel> fitnessLogs;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;

    public ActivityData(List<ActivityDataModel> list) {
        this.fitnessLogs = null;
        this.fitnessLogs = list;
    }
}
