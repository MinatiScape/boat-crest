package com.coveiot.coveaccess.fitnessbuddies.model;

import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.fitnessbuddies.ReactionType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SendReactionRequest implements Serializable {
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName("type")
    @Expose
    public String type;

    public SendReactionRequest(ReactionType reactionType, String str) {
        this.type = reactionType.getAction();
        this.message = str;
    }
}
