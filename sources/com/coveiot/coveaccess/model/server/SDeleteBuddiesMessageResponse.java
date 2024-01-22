package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SDeleteBuddiesMessageResponse {
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message = null;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public boolean status = false;
}
