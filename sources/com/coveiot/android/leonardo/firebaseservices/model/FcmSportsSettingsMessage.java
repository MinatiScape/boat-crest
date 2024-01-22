package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class FcmSportsSettingsMessage implements Serializable {
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName("title")
    @Expose
    public String title;

    public String getMessage() {
        return this.message;
    }

    public String getTitle() {
        return this.title;
    }
}
