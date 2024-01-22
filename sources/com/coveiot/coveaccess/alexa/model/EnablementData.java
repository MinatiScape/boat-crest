package com.coveiot.coveaccess.alexa.model;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class EnablementData {
    @SerializedName("accountLink")

    /* renamed from: a  reason: collision with root package name */
    private AccountLinkData f6410a = null;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String b = null;
    @SerializedName("userDeviceId")
    private Long c;
    @SerializedName("skillEnabledFrom")
    private String d;

    public AccountLinkData getAccountLink() {
        return this.f6410a;
    }

    public String getSkillEnabledFrom() {
        return this.d;
    }

    public String getStatus() {
        return this.b;
    }

    public Long getUserDeviceId() {
        return this.c;
    }

    public void setAccountLink(AccountLinkData accountLinkData) {
        this.f6410a = accountLinkData;
    }

    public void setSkillEnabledFrom(String str) {
        this.d = str;
    }

    public void setStatus(String str) {
        this.b = str;
    }

    public void setUserDeviceId(Long l) {
        this.c = l;
    }
}
