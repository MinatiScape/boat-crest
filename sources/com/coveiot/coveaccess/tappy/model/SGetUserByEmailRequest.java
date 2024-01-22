package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetUserByEmailRequest implements Serializable {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("includeAddress")
    @Expose
    private boolean includeAddress;
    @SerializedName("includeCustomFieldAnswers")
    @Expose
    private boolean includeCustomFieldAnswers;

    public String getEmail() {
        return this.email;
    }

    public boolean isIncludeAddress() {
        return this.includeAddress;
    }

    public boolean isIncludeCustomFieldAnswers() {
        return this.includeCustomFieldAnswers;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setIncludeAddress(boolean z) {
        this.includeAddress = z;
    }

    public void setIncludeCustomFieldAnswers(boolean z) {
        this.includeCustomFieldAnswers = z;
    }
}
