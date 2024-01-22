package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetTAndCResponse implements Serializable {
    @SerializedName("TermsAndConditionsFileUrl")
    @Expose
    private String termsAndConditionsFileUrl;
    @SerializedName("TermsAndConditionsID")
    @Expose
    private String termsAndConditionsID;

    public String getTermsAndConditionsFileUrl() {
        return this.termsAndConditionsFileUrl;
    }

    public String getTermsAndConditionsID() {
        return this.termsAndConditionsID;
    }

    public void setTermsAndConditionsFileUrl(String str) {
        this.termsAndConditionsFileUrl = str;
    }

    public void setTermsAndConditionsID(String str) {
        this.termsAndConditionsID = str;
    }
}
