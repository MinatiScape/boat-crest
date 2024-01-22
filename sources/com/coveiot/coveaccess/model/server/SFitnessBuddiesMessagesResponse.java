package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Messages;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SFitnessBuddiesMessagesResponse {
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        @Expose
        public List<Messages> items;
        @SerializedName("itemsPerPage")
        @Expose
        public int itemsPerPage;
        @SerializedName("pageIndex")
        @Expose
        public int pageIndex;

        public Data() {
        }
    }
}
