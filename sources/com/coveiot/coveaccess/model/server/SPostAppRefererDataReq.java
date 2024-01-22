package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPostAppRefererDataReq {
    @SerializedName("referrerData")
    private String referrerData;

    public String getReferrerData() {
        return this.referrerData;
    }

    public void setReferrerData(String str) {
        this.referrerData = str;
    }
}
