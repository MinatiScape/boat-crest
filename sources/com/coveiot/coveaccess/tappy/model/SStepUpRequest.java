package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SStepUpRequest implements Serializable {
    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("Method")
    @Expose
    private String method;
    @SerializedName("MethodDescription")
    @Expose
    private String methodDescription;
    @SerializedName("MethodValue")
    @Expose
    private String methodValue;

    public String getId() {
        return this.id;
    }

    public String getMethod() {
        return this.method;
    }

    public String getMethodDescription() {
        return this.methodDescription;
    }

    public String getMethodValue() {
        return this.methodValue;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setMethodDescription(String str) {
        this.methodDescription = str;
    }

    public void setMethodValue(String str) {
        this.methodValue = str;
    }
}
