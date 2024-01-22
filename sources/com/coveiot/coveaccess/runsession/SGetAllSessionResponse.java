package com.coveiot.coveaccess.runsession;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.runsession.common.GetAllSessionData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetAllSessionResponse implements Serializable {
    @SerializedName("data")
    @Expose
    public GetAllSessionData data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    public String status;
}
