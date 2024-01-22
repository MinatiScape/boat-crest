package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SValidateOTPResponse implements Serializable {
    @SerializedName("Result")
    @Expose
    private String result;

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }
}
