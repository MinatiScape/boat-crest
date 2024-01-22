package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class FcmGaurdianDeleteMessage implements Serializable {
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public String message;
    @SerializedName("relType")
    @Expose
    public String relType;
    @SerializedName("title")
    @Expose
    public String title;

    public String getMessage() {
        return this.message;
    }

    public String getRelType() {
        return this.relType;
    }

    public String getTitle() {
        return this.title;
    }
}
