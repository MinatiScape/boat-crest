package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SUpdateRegisteredProductFriendlyNameRequest implements Serializable {
    @SerializedName("FriendlyName")
    @Expose
    private String friendlyName;
    private transient long productRegistrationId;
    private transient long userId;

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public long getProductRegistrationId() {
        return this.productRegistrationId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }

    public void setProductRegistrationId(long j) {
        this.productRegistrationId = j;
    }

    public void setUserId(long j) {
        this.userId = j;
    }
}
