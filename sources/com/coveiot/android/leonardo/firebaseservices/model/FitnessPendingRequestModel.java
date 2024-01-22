package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class FitnessPendingRequestModel implements Serializable {
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName("title")
    @Expose
    private String title;

    public String getMessage() {
        return this.message;
    }

    public String getTitle() {
        return this.title;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
