package com.coveiot.coveaccess.fitnesschallenge.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class GetAllParticipantsFitnessChallengeRes implements Serializable {
    @SerializedName("challengeName")
    @Expose
    private String challengeName;
    @SerializedName("currentUser")
    @Expose
    private ParticipantsDetails currentUser;
    @SerializedName("itemsPerPage")
    @Expose
    private Integer itemsPerPage;
    @SerializedName("pageIndex")
    @Expose
    private Integer pageIndex;
    @SerializedName("participants")
    @Expose
    private List<ParticipantsDetails> participants;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    private Integer target;
    @SerializedName("targetBaseUnit")
    @Expose
    private String targetBaseUnit;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    /* loaded from: classes8.dex */
    public static class ParticipantsDetails implements Serializable {
        @SerializedName("dpUrl")
        @Expose
        private String dpUrl;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        private String name;
        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("targetAchieved")
        @Expose
        private Integer targetAchieved;
        @SerializedName("targetAchievedDate")
        @Expose
        private String targetAchievedDate;
        @SerializedName(Constants.END_USER_GLOBAL_ID)
        @Expose
        private String userId;

        public String getDpUrl() {
            return this.dpUrl;
        }

        public String getName() {
            return this.name;
        }

        public Integer getRank() {
            return this.rank;
        }

        public Integer getTargetAchieved() {
            return this.targetAchieved;
        }

        public String getTargetAchievedDate() {
            return this.targetAchievedDate;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setDpUrl(String str) {
            this.dpUrl = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setRank(Integer num) {
            this.rank = num;
        }

        public void setTargetAchieved(Integer num) {
            this.targetAchieved = num;
        }

        public void setTargetAchievedDate(String str) {
            this.targetAchievedDate = str;
        }

        public void setUserId(String str) {
            this.userId = str;
        }
    }

    public String getChallengeName() {
        return this.challengeName;
    }

    public ParticipantsDetails getCurrentUser() {
        return this.currentUser;
    }

    public Integer getItemsPerPage() {
        return this.itemsPerPage;
    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public List<ParticipantsDetails> getParticipants() {
        return this.participants;
    }

    public Integer getTarget() {
        return this.target;
    }

    public String getTargetBaseUnit() {
        return this.targetBaseUnit;
    }

    public Integer getTotalItems() {
        return this.totalItems;
    }

    public void setChallengeName(String str) {
        this.challengeName = str;
    }

    public void setCurrentUser(ParticipantsDetails participantsDetails) {
        this.currentUser = participantsDetails;
    }

    public void setItemsPerPage(Integer num) {
        this.itemsPerPage = num;
    }

    public void setPageIndex(Integer num) {
        this.pageIndex = num;
    }

    public void setParticipants(List<ParticipantsDetails> list) {
        this.participants = list;
    }

    public void setTarget(Integer num) {
        this.target = num;
    }

    public void setTargetBaseUnit(String str) {
        this.targetBaseUnit = str;
    }

    public void setTotalItems(Integer num) {
        this.totalItems = num;
    }
}
