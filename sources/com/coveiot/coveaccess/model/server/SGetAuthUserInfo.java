package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SGetAuthUserInfo {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("createdDate")
        private String createdDate;
        @SerializedName("orgName")
        private String orgName;
        @SerializedName("username")
        private String username;

        public String getCreatedDate() {
            return this.createdDate;
        }

        public String getOrgName() {
            return this.orgName;
        }

        public String getUsername() {
            return this.username;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setOrgName(String str) {
            this.orgName = str;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public String toString() {
            return "DataBean{username=" + this.username + ", orgName='" + this.orgName + "', createdDate='" + this.createdDate + "'}";
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

    public String toString() {
        return "SGetAuthUserInfo{data=" + this.data + ", message='" + this.message + "', status='" + this.status + "'}";
    }
}
