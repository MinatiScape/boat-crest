package com.coveiot.coveaccess.healthbuddies;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class HealthBuddyLogResponse extends CoveApiResponseBaseModel {
    @SerializedName("cancelledHealthBuddyRequests")
    @Expose
    public List<HealthBuddy> cancelledHealthBuddyRequests;
    @SerializedName("expiredHealthBuddyRequests")
    @Expose
    public List<HealthBuddy> expiredHealthBuddyRequests;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName("pendingHealthBuddyRequests")
    @Expose
    public List<HealthBuddy> pendingHealthBuddyRequests;
    @SerializedName("rejectedHealthBuddyRequests")
    @Expose
    public List<HealthBuddy> rejectedHealthBuddyRequests;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;

    public HealthBuddyLogResponse(int i, String str, String str2, List<HealthBuddy> list, List<HealthBuddy> list2, List<HealthBuddy> list3, List<HealthBuddy> list4) {
        super(i);
        this.pendingHealthBuddyRequests = null;
        this.expiredHealthBuddyRequests = null;
        this.rejectedHealthBuddyRequests = null;
        this.cancelledHealthBuddyRequests = null;
        this.status = str;
        this.message = str2;
        this.pendingHealthBuddyRequests = list;
        this.expiredHealthBuddyRequests = list2;
        this.rejectedHealthBuddyRequests = list3;
        this.cancelledHealthBuddyRequests = list4;
    }
}
