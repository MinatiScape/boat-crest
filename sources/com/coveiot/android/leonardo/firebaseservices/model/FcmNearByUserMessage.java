package com.coveiot.android.leonardo.firebaseservices.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class FcmNearByUserMessage implements Serializable {
    @SerializedName("panicCode")
    @Expose
    public String panicCode;

    public String getPanicCode() {
        return this.panicCode;
    }

    public void setPanicCode(String str) {
        this.panicCode = str;
    }
}
