package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import com.ido.ble.event.stat.one.d;
/* loaded from: classes8.dex */
public class SPostReportIssue {
    @SerializedName("emailId")
    private String emailId;
    @SerializedName(d.O)
    private String feedback;
    @SerializedName("phoneNo")
    private String phoneNo;

    public String getEmailId() {
        return this.emailId;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setEmailId(String str) {
        this.emailId = str;
    }

    public void setFeedback(String str) {
        this.feedback = str;
    }

    public void setPhoneNo(String str) {
        this.phoneNo = str;
    }
}
