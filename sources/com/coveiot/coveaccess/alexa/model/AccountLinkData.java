package com.coveiot.coveaccess.alexa.model;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AccountLinkData {
    @SerializedName(NotificationCompat.CATEGORY_STATUS)

    /* renamed from: a  reason: collision with root package name */
    private String f6409a = null;

    public String getStatus() {
        return this.f6409a;
    }

    public void setStatus(String str) {
        this.f6409a = str;
    }
}
