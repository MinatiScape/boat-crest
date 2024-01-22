package com.coveiot.coveaccess.fitnessbuddies.model.common;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Messages {
    @SerializedName("createdDate")
    @Expose
    public String createdDate;
    @SerializedName("fitnessGoal")
    @Expose
    public BuddiesGoal fitnessGoal;
    @SerializedName("fitnessMessageId")
    @Expose
    public String fitnessMessageId;
    @SerializedName("fromUserDpUrl")
    @Expose
    public String fromUserDpUrl;
    @SerializedName("fromUserId")
    @Expose
    public int fromUserId;
    @SerializedName("fromUserName")
    @Expose
    public String fromUserName;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName("toUserDpUrl")
    @Expose
    public String toUserDpUrl;
    @SerializedName("toUserId")
    @Expose
    public int toUserId;
    @SerializedName("toUserName")
    @Expose
    public String toUserName;
    @SerializedName("type")
    @Expose
    public String type;
}
