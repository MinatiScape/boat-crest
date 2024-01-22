package com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class MatchProjection {
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)

    /* renamed from: a  reason: collision with root package name */
    private Integer f5988a;
    @SerializedName("injurytime")
    private Integer b;
    @SerializedName("value")
    private String c;

    public Integer getInjurytime() {
        return this.b;
    }

    public Integer getTime() {
        return this.f5988a;
    }

    public String getValue() {
        return this.c;
    }

    public void setInjurytime(Integer num) {
        this.b = num;
    }

    public void setTime(Integer num) {
        this.f5988a = num;
    }

    public void setValue(String str) {
        this.c = str;
    }
}
