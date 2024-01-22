package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SActivatePlanResponse implements Serializable {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean implements Serializable {
        @SerializedName("activationDate")
        private String activationDate;
        @SerializedName("createdDate")
        private String createdDate;
        @SerializedName("planTemplateId")
        private String planTemplateId;
        @SerializedName("userPlanId")
        private String userPlanId;

        public String getActivationDate() {
            return this.activationDate;
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public String getPlanTemplateId() {
            return this.planTemplateId;
        }

        public String getUserPlanId() {
            return this.userPlanId;
        }

        public void setActivationDate(String str) {
            this.activationDate = str;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setPlanTemplateId(String str) {
            this.planTemplateId = str;
        }

        public void setUserPlanId(String str) {
            this.userPlanId = str;
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
