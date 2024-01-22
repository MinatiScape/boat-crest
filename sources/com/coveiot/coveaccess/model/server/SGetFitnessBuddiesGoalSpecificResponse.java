package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoalsBean;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SGetFitnessBuddiesGoalSpecificResponse {
    @SerializedName("data")
    public DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("buddiesGoals")
        public List<BuddiesGoalsBean> buddiesGoals;

        public List<BuddiesGoalsBean> getBuddiesGoals() {
            return this.buddiesGoals;
        }

        public void setBuddiesGoals(List<BuddiesGoalsBean> list) {
            this.buddiesGoals = list;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
