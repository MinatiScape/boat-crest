package com.coveiot.coveaccess.edgegateway.model;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class EGUpdateSessionResponse extends CoveApiResponseBaseModel {
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    public EGUpdateSessionResponse(int i) {
        super(i);
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
