package com.coveiot.coveaccess.fitnesschallenge.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class BuddiesChallengeDetail implements Serializable {
    @SerializedName("bannerImageId")
    @Expose
    private Integer bannerImageId;
    @SerializedName("bannerImageUrl")
    @Expose
    private String bannerImageUrl;
    @SerializedName("challengeId")
    @Expose
    private Object challengeId;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
    @Expose
    private String description;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("creator")
    @Expose
    private Boolean isCreator;
    @SerializedName("leftDate")
    @Expose
    private String leftDate;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("shareable")
    @Expose
    private Boolean shareable;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    private Integer target;
    @SerializedName("targetAchieved")
    @Expose
    private Integer targetAchieved;
    @SerializedName("targetBaseUnits")
    @Expose
    private String targetBaseUnits;
    @SerializedName("topParticipants")
    @Expose
    private List<BuddiesChallengeRes.Item.TopParticipant> topParticipants;
    @SerializedName("totalBuddies")
    @Expose
    private Integer totalBuddies;
    @SerializedName("totalParticipants")
    @Expose
    private Integer totalParticipants;
    @SerializedName("transitive")
    @Expose
    private Boolean transitive;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getBannerImageId() {
        return this.bannerImageId;
    }

    public String getBannerImageUrl() {
        return this.bannerImageUrl;
    }

    public Object getChallengeId() {
        return this.challengeId;
    }

    public String getChallengeType() {
        return this.type;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Boolean getCreator() {
        return this.isCreator;
    }

    public String getDescription() {
        return this.description;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getLeftDate() {
        return this.leftDate;
    }

    public String getName() {
        return this.name;
    }

    public Integer getRank() {
        return this.rank;
    }

    public Boolean getShareable() {
        return this.shareable;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getStatus() {
        return this.status;
    }

    public Integer getTarget() {
        return this.target;
    }

    public Integer getTargetAchieved() {
        return this.targetAchieved;
    }

    public String getTargetBaseUnits() {
        return this.targetBaseUnits;
    }

    public List<BuddiesChallengeRes.Item.TopParticipant> getTopParticipants() {
        return this.topParticipants;
    }

    public Integer getTotalBuddies() {
        return this.totalBuddies;
    }

    public Integer getTotalParticipants() {
        return this.totalParticipants;
    }

    public Boolean getTransitive() {
        return this.transitive;
    }

    public String getType() {
        return this.type;
    }

    public void setBannerImageId(Integer num) {
        this.bannerImageId = num;
    }

    public void setBannerImageUrl(String str) {
        this.bannerImageUrl = str;
    }

    public void setChallengeId(Object obj) {
        this.challengeId = obj;
    }

    public void setChallengeType(String str) {
        this.type = str;
    }

    public void setCreatedBy(String str) {
        this.createdBy = str;
    }

    public void setCreator(Boolean bool) {
        this.isCreator = bool;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public void setLeftDate(String str) {
        this.leftDate = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRank(Integer num) {
        this.rank = num;
    }

    public void setShareable(Boolean bool) {
        this.shareable = bool;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTarget(Integer num) {
        this.target = num;
    }

    public void setTargetAchieved(Integer num) {
        this.targetAchieved = num;
    }

    public void setTargetBaseUnits(String str) {
        this.targetBaseUnits = str;
    }

    public void setTopParticipants(List<BuddiesChallengeRes.Item.TopParticipant> list) {
        this.topParticipants = list;
    }

    public void setTotalBuddies(Integer num) {
        this.totalBuddies = num;
    }

    public void setTotalParticipants(Integer num) {
        this.totalParticipants = num;
    }

    public void setTransitive(Boolean bool) {
        this.transitive = bool;
    }

    public void setType(String str) {
        this.type = str;
    }
}
