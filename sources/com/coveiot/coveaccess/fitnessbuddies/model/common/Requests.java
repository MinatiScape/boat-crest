package com.coveiot.coveaccess.fitnessbuddies.model.common;

import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Requests {
    @SerializedName("buddyId")
    @Expose
    public Integer buddyId;
    @SerializedName("createdDate")
    @Expose
    public String createdDate;
    @SerializedName("dpUrl")
    @Expose
    public String dpUrl;
    @SerializedName("fromUserDetails")
    @Expose
    public ToUserDetailsRequests fromUserDetails;
    @SerializedName("fromUserDpUrl")
    @Expose
    public String fromUserDpUrl;
    @SerializedName("fromUserId")
    @Expose
    public int fromUserId;
    @SerializedName("fromUserMobileNumber")
    @Expose
    public String fromUserMobileNumber;
    @SerializedName("fromUserName")
    @Expose
    public String fromUserName;
    @SerializedName("fromUserProfilePictureName")
    @Expose
    public String fromUserProfilePictureName;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("inviteLinkId")
    @Expose
    public String inviteLinkId;
    @SerializedName("inviteLocally")
    @Expose
    public boolean inviteLocally;
    @SerializedName("inviteText")
    @Expose
    public String inviteText;
    @SerializedName("lastInvitedDate")
    @Expose
    public String lastInvitedDate;
    @SerializedName("mobileNumber")
    @Expose
    public String mobileNumber;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    public String name;
    @SerializedName("profilePic")
    @Expose
    public String profilePic;
    @SerializedName("profilePictureName")
    @Expose
    public String profilePictureName;
    @SerializedName("requestId")
    @Expose
    public int requestId;
    @SerializedName("requestStatus")
    @Expose
    public String requestStatus;
    @SerializedName("toCloverName")
    @Expose
    public String toCloverName;
    @SerializedName("toUserDetails")
    @Expose
    public ToUserDetailsRequests toUserDetails;
    @SerializedName("toUserDpUrl")
    @Expose
    public String toUserDpUrl;
    @SerializedName("toUserId")
    @Expose
    public int toUserId;
    @SerializedName("toUserMobileNumber")
    @Expose
    public String toUserMobileNumber;
    @SerializedName("toUserName")
    @Expose
    public String toUserName;
    @SerializedName("toUserProfilePictureName")
    @Expose
    public String toUserProfilePictureName;
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    @Expose
    public int userId;
}
