package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SGetConsentData {
    @SerializedName("data")
    @Expose
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public class DataBean {
        @SerializedName("docOnlineConsent")
        @Expose
        private Boolean docOnlineConsent;
        @SerializedName("monitoringConsent")
        @Expose
        private Boolean monitoringConsent;
        @SerializedName("sportsConsent")
        @Expose
        private Boolean sportsConsent;
        @SerializedName("stravaConsent")
        @Expose
        private Boolean stravaConsent;

        public DataBean() {
        }

        public Boolean getDocOnlineConsent() {
            return this.docOnlineConsent;
        }

        public Boolean getMonitoringConsent() {
            return this.monitoringConsent;
        }

        public Boolean getSportsConsent() {
            return this.sportsConsent;
        }

        public Boolean getStravaConsent() {
            return this.stravaConsent;
        }

        public void setDocOnlineConsent(Boolean bool) {
            this.docOnlineConsent = bool;
        }

        public void setMonitoringConsent(Boolean bool) {
            this.monitoringConsent = bool;
        }

        public void setSportsConsent(Boolean bool) {
            this.sportsConsent = bool;
        }

        public void setStravaConsent(Boolean bool) {
            this.stravaConsent = bool;
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
