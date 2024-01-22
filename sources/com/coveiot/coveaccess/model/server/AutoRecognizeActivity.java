package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AutoRecognizeActivity {
    @SerializedName("code")
    private String code;
    @SerializedName("type")
    private String type;

    public String getCode() {
        return this.code;
    }

    public String getType() {
        return this.type;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
