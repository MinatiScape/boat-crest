package com.coveiot.coveaccess.fitnessbuddies.model.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class ToUserDetailsRequests {
    @SerializedName("globalRank")
    @Expose
    public GlobalRankDetailsRequests globalRankData;
    @SerializedName("totalEarnedBadges")
    @Expose
    public Integer totalEarnedBadges;

    public GlobalRankDetailsRequests getGlobalRankData() {
        return this.globalRankData;
    }

    public int getTotalEarnedBadges() {
        return this.totalEarnedBadges.intValue();
    }

    public void setGlobalRankData(GlobalRankDetailsRequests globalRankDetailsRequests) {
        this.globalRankData = globalRankDetailsRequests;
    }

    public void setTotalEarnedBadges(int i) {
        this.totalEarnedBadges = Integer.valueOf(i);
    }
}
