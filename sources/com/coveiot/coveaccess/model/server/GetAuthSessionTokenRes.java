package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GetAuthSessionTokenRes {
    @SerializedName("data")
    private Data data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("authSessionToken")
        private String authSessionToken;
        @SerializedName("ctx")
        private String ctx;

        public Data() {
        }

        public String getAuthSessionToken() {
            return this.authSessionToken;
        }

        public String getCtx() {
            return this.ctx;
        }

        public void setAuthSessionToken(String str) {
            this.authSessionToken = str;
        }

        public void setCtx(String str) {
            this.ctx = str;
        }
    }

    public Data getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
