package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class UserRemoteConfigResponse implements Serializable {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("dataPull")
        private DataPull dataPull;
        @SerializedName("session")
        private SessionBean session;

        /* loaded from: classes8.dex */
        public static class DataPull {
            @SerializedName("fromDate")
            private String fromDate;
            @SerializedName("versionTag")
            private String versionTag;

            public String getFromDate() {
                return this.fromDate;
            }

            public String getVersionTag() {
                return this.versionTag;
            }

            public void setFromDate(String str) {
                this.fromDate = str;
            }

            public void setVersionTag(String str) {
                this.versionTag = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class SessionBean {
            @SerializedName("isExist")
            private boolean isExist;
            @SerializedName("requireRefresh")
            private boolean requireRefresh;

            public boolean isIsExist() {
                return this.isExist;
            }

            public boolean isRequireRefresh() {
                return this.requireRefresh;
            }

            public void setIsExist(boolean z) {
                this.isExist = z;
            }

            public void setRequireRefresh(boolean z) {
                this.requireRefresh = z;
            }
        }

        public DataPull getDataPull() {
            return this.dataPull;
        }

        public SessionBean getSession() {
            return this.session;
        }

        public void setDataPull(DataPull dataPull) {
            this.dataPull = dataPull;
        }

        public void setSession(SessionBean sessionBean) {
            this.session = sessionBean;
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
