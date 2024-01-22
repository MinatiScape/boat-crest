package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SApduCommand implements Serializable {
    @SerializedName("APDU")
    @Expose
    private String apdu;
    @SerializedName("IgnoreFailureResponse")
    @Expose
    private boolean ignoreFailureResponse;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SaveResponseData")
    @Expose
    private boolean saveResponseData;

    public String getApdu() {
        return this.apdu;
    }

    public String getName() {
        return this.name;
    }

    public boolean isIgnoreFailureResponse() {
        return this.ignoreFailureResponse;
    }

    public boolean isSaveResponseData() {
        return this.saveResponseData;
    }

    public void setApdu(String str) {
        this.apdu = str;
    }

    public void setIgnoreFailureResponse(boolean z) {
        this.ignoreFailureResponse = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSaveResponseData(boolean z) {
        this.saveResponseData = z;
    }
}
