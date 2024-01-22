package com.coveiot.coveaccess.fitnessbuddies.model.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class GlobalRankDetailsRequests implements Serializable {
    @SerializedName("placeType")
    @Expose
    public String placeType;
    @SerializedName("previousRank")
    @Expose
    public Integer previousRank;
    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("rankDate")
    @Expose
    public String rankDate;
    @SerializedName("rankType")
    @Expose
    public String rankType;
    @SerializedName("steps")
    @Expose
    public Integer steps;
    @SerializedName("totalUsers")
    @Expose
    public Integer totalUsers;

    public String getPlaceType() {
        return this.placeType;
    }

    public Integer getPreviousRank() {
        return this.previousRank;
    }

    public Integer getRank() {
        return this.rank;
    }

    public String getRankDate() {
        return this.rankDate;
    }

    public String getRankType() {
        return this.rankType;
    }

    public Integer getSteps() {
        return this.steps;
    }

    public Integer getTotalUsers() {
        return this.totalUsers;
    }

    public void setPlaceType(String str) {
        this.placeType = str;
    }

    public void setPreviousRank(Integer num) {
        this.previousRank = num;
    }

    public void setRank(Integer num) {
        this.rank = num;
    }

    public void setRankDate(String str) {
        this.rankDate = str;
    }

    public void setRankType(String str) {
        this.rankType = str;
    }

    public void setSteps(Integer num) {
        this.steps = num;
    }

    public void setTotalUsers(Integer num) {
        this.totalUsers = num;
    }
}
