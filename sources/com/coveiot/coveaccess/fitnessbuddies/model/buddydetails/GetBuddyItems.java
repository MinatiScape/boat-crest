package com.coveiot.coveaccess.fitnessbuddies.model.buddydetails;

import com.coveiot.coveaccess.fitnessbuddies.model.common.GlobalRankDetailsRequests;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class GetBuddyItems implements Serializable {
    @SerializedName("buddy")
    @Expose
    private BuddyDetails buddyDetails;
    @SerializedName("globalRank")
    @Expose
    private GlobalRankDetailsRequests globalRank;
    @SerializedName("goals")
    @Expose
    private List<BuddyGoalsDetails> goalsList;
    @SerializedName("lastEarnedBadge")
    @Expose
    private BuddyLastEarnedBadge lastEarnedBadge;
    @SerializedName("recentActivity")
    @Expose
    private BuddyRecentActivityDetails recentActivity;
    @SerializedName("totalEarnedBadges")
    @Expose
    private Integer totalEarnedBadges;
    @SerializedName("walkData")
    @Expose
    private List<BuddyWalkDetails> walkDataList;

    public BuddyDetails getBuddyDetails() {
        return this.buddyDetails;
    }

    public GlobalRankDetailsRequests getGlobalRank() {
        return this.globalRank;
    }

    public List<BuddyGoalsDetails> getGoalsList() {
        return this.goalsList;
    }

    public BuddyLastEarnedBadge getLastEarnedBadge() {
        return this.lastEarnedBadge;
    }

    public BuddyRecentActivityDetails getRecentActivity() {
        return this.recentActivity;
    }

    public Integer getTotalEarnedBadges() {
        return this.totalEarnedBadges;
    }

    public List<BuddyWalkDetails> getWalkDataList() {
        return this.walkDataList;
    }

    public void setBuddyDetails(BuddyDetails buddyDetails) {
        this.buddyDetails = buddyDetails;
    }

    public void setGlobalRank(GlobalRankDetailsRequests globalRankDetailsRequests) {
        this.globalRank = globalRankDetailsRequests;
    }

    public void setGoalsList(List<BuddyGoalsDetails> list) {
        this.goalsList = list;
    }

    public void setLastEarnedBadge(BuddyLastEarnedBadge buddyLastEarnedBadge) {
        this.lastEarnedBadge = buddyLastEarnedBadge;
    }

    public void setRecentActivity(BuddyRecentActivityDetails buddyRecentActivityDetails) {
        this.recentActivity = buddyRecentActivityDetails;
    }

    public void setTotalEarnedBadges(Integer num) {
        this.totalEarnedBadges = num;
    }

    public void setWalkDataList(List<BuddyWalkDetails> list) {
        this.walkDataList = list;
    }
}
