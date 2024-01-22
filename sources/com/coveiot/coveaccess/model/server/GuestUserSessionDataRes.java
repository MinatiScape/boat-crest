package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class GuestUserSessionDataRes implements Serializable {
    @SerializedName("data")
    @Expose
    private SessionData data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public static class SessionData implements Serializable {
        @SerializedName("axTrackerId")
        @Expose
        private String axTrackerId;
        @SerializedName("guestUserSessionId")
        @Expose
        private String guestUserSessionId;

        public String getAxTrackerId() {
            return this.axTrackerId;
        }

        public String getGuestUserSessionId() {
            return this.guestUserSessionId;
        }

        public void setAxTrackerId(String str) {
            this.axTrackerId = str;
        }

        public void setGuestUserSessionId(String str) {
            this.guestUserSessionId = str;
        }
    }

    public SessionData getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(SessionData sessionData) {
        this.data = sessionData;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
