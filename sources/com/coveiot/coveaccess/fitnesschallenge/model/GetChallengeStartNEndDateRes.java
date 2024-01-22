package com.coveiot.coveaccess.fitnesschallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class GetChallengeStartNEndDateRes implements Serializable {
    @SerializedName("activeCreatedBuddiesChallenges")
    @Expose
    private Integer activeCreatedBuddiesChallenges;
    @SerializedName("activeDateRange")
    @Expose
    private ActiveDateRange activeDateRange;
    @SerializedName("maxAllowedBuddiesChallenges")
    @Expose
    private Integer maxAllowedBuddiesChallenges;

    /* loaded from: classes8.dex */
    public static class ActiveDateRange implements Serializable {
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("startDate")
        @Expose
        private String startDate;

        public String getEndDate() {
            return this.endDate;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public void setEndDate(String str) {
            this.endDate = str;
        }

        public void setStartDate(String str) {
            this.startDate = str;
        }
    }

    public Integer getActiveCreatedBuddiesChallenges() {
        return this.activeCreatedBuddiesChallenges;
    }

    public ActiveDateRange getActiveDateRange() {
        return this.activeDateRange;
    }

    public Integer getMaxAllowedBuddiesChallenges() {
        return this.maxAllowedBuddiesChallenges;
    }

    public void setActiveCreatedBuddiesChallenges(Integer num) {
        this.activeCreatedBuddiesChallenges = num;
    }

    public void setActiveDateRange(ActiveDateRange activeDateRange) {
        this.activeDateRange = activeDateRange;
    }

    public void setMaxAllowedBuddiesChallenges(Integer num) {
        this.maxAllowedBuddiesChallenges = num;
    }
}
