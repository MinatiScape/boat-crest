package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SEGLoginResponse {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("access_token")
        private String accessToken;
        @SerializedName("error")
        private String error;
        @SerializedName("expires_in")
        private long expiresIn;
        @SerializedName("refresh_token")
        private String refreshToken;
        @SerializedName("token_type")
        private String tokenType;

        public String getAccessToken() {
            return this.accessToken;
        }

        public String getError() {
            return this.error;
        }

        public long getExpiresIn() {
            return this.expiresIn;
        }

        public String getRefreshToken() {
            return this.refreshToken;
        }

        public String getTokenType() {
            return this.tokenType;
        }

        public void setAccessToken(String str) {
            this.accessToken = str;
        }

        public void setError(String str) {
            this.error = str;
        }

        public void setExpiresIn(long j) {
            this.expiresIn = j;
        }

        public void setRefreshToken(String str) {
            this.refreshToken = str;
        }

        public void setTokenType(String str) {
            this.tokenType = str;
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
