package com.coveiot.coveaccess.healthbuddies;

import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class HealthBuddy {
    @SerializedName("createdDate")
    @Expose
    public String createdDate;
    @SerializedName("dpUrl")
    @Expose
    public String dpUrl;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("inviteText")
    @Expose
    public String inviteMessage;
    @SerializedName("lastInvitedDate")
    @Expose
    public String lastInvitedDate;
    @SerializedName("mobileNumber")
    @Expose
    public String mobileNumber;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    public String name;
    @SerializedName("relType")
    @Expose
    public String relType;
    @SerializedName("requestId")
    @Expose
    public Integer requestId;
    @SerializedName("requestSatus")
    @Expose
    public String requestStatus;
    @SerializedName("toContactName")
    @Expose
    public String toContactName;
    @SerializedName("toUserDpUrl")
    @Expose
    public String toUserDpUrl;
    @SerializedName("toUserId")
    @Expose
    public Integer toUserId;
    @SerializedName("toUserMobileNumber")
    @Expose
    public String toUserMobileNumber;
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    @Expose
    public Integer userId;
}
