package com.coveiot.coveaccess.fitnessbuddies.model.common;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class BuddyRequest {
    @SerializedName("data")
    @Expose
    public Data_ data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;

    /* loaded from: classes8.dex */
    public class Data_ {
        @SerializedName("inviteText")
        @Expose
        public String inviteText;
        @SerializedName("toUserId")
        @Expose
        public Integer toUserId;
        @SerializedName("toUserMobileNumber")
        @Expose
        public String toUserMobileNumber;
        @SerializedName("toUserName")
        @Expose
        public String toUserName;

        public Data_() {
        }
    }
}
