package com.coveiot.coveaccess.fitnessbuddies.model.buddydetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class BuddyLastEarnedBadge implements Serializable {
    @SerializedName("badgeImageUrl")
    @Expose
    public String badgeImageUrl;
    @SerializedName("badgeName")
    @Expose
    public String badgeName;

    public String getBadgeImageUrl() {
        return this.badgeImageUrl;
    }

    public String getBadgeName() {
        return this.badgeName;
    }

    public void setBadgeImageUrl(String str) {
        this.badgeImageUrl = str;
    }

    public void setBadgeName(String str) {
        this.badgeName = str;
    }
}
