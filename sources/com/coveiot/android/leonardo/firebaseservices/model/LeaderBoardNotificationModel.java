package com.coveiot.android.leonardo.firebaseservices.model;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class LeaderBoardNotificationModel implements Serializable {
    @SerializedName("badgeName")
    @Expose
    private String badgeName;
    @SerializedName("levelDescription")
    @Expose
    private String levelDescription;
    @SerializedName("levelImageUrl")
    @Expose
    private String levelImageUrl;
    @SerializedName("levelName")
    @Expose
    private String levelName;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName("noOfBadges")
    @Expose
    private int noOfBadges;
    @SerializedName("title")
    @Expose
    private String title;

    public String getBadgeName() {
        return this.badgeName;
    }

    public String getLevelDescription() {
        return this.levelDescription;
    }

    public String getLevelImageUrl() {
        return this.levelImageUrl;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public String getMessage() {
        return this.message;
    }

    public int getNoOfBadges() {
        return this.noOfBadges;
    }

    public String getTitle() {
        return this.title;
    }

    public void setBadgeName(String str) {
        this.badgeName = str;
    }

    public void setLevelDescription(String str) {
        this.levelDescription = str;
    }

    public void setLevelImageUrl(String str) {
        this.levelImageUrl = str;
    }

    public void setLevelName(String str) {
        this.levelName = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setNoOfBadges(int i) {
        this.noOfBadges = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
