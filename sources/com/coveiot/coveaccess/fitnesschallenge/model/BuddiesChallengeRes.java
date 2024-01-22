package com.coveiot.coveaccess.fitnesschallenge.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.io.Serializable;
import java.util.List;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes8.dex */
public class BuddiesChallengeRes implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<Item> items;
    @SerializedName("itemsPerPage")
    @Expose
    private Integer itemsPerPage;
    @SerializedName("pageIndex")
    @Expose
    private Integer pageIndex;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    /* loaded from: classes8.dex */
    public static class Item implements Serializable {
        @SerializedName("bannerImageUrl")
        @Expose
        private String bannerImageUrl;
        @SerializedName("challengeId")
        @Expose
        private Object challengeId;
        @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
        @Expose
        private String description;
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("joined")
        @Expose
        private Boolean joined = Boolean.FALSE;
        @SerializedName("leftDate")
        @Expose
        private String leftDate;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        private String name;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName(NotificationCompat.CATEGORY_STATUS)
        @Expose
        private String status;
        @SerializedName(HeaderParameterNames.AUTHENTICATION_TAG)
        @Expose
        private String tag;
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
        private List<TopParticipant> topParticipants;
        @SerializedName("totalParticipants")
        @Expose
        private Integer totalParticipants;
        @SerializedName("type")
        @Expose
        private String type;

        /* loaded from: classes8.dex */
        public static class TopParticipant implements Serializable {
            @SerializedName("dpUrl")
            @Expose
            private String dpUrl;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            @Expose
            private String name;

            public String getDpUrl() {
                return this.dpUrl;
            }

            public String getName() {
                return this.name;
            }

            public void setDpUrl(String str) {
                this.dpUrl = str;
            }

            public void setName(String str) {
                this.name = str;
            }
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

        public String getDescription() {
            return this.description;
        }

        public String getEndDate() {
            return this.endDate;
        }

        public Boolean getJoined() {
            return this.joined;
        }

        public String getLeftDate() {
            return this.leftDate;
        }

        public String getName() {
            return this.name;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public String getStatus() {
            return this.status;
        }

        public String getTag() {
            return this.tag;
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

        public List<TopParticipant> getTopParticipants() {
            return this.topParticipants;
        }

        public Integer getTotalParticipants() {
            return this.totalParticipants;
        }

        public String getType() {
            return this.type;
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

        public void setDescription(String str) {
            this.description = str;
        }

        public void setEndDate(String str) {
            this.endDate = str;
        }

        public void setJoined(Boolean bool) {
            this.joined = bool;
        }

        public void setLeftDate(String str) {
            this.leftDate = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setStartDate(String str) {
            this.startDate = str;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public void setTag(String str) {
            this.tag = str;
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

        public void setTopParticipants(List<TopParticipant> list) {
            this.topParticipants = list;
        }

        public void setTotalParticipants(Integer num) {
            this.totalParticipants = num;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Integer getItemsPerPage() {
        return this.itemsPerPage;
    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public Integer getTotalItems() {
        return this.totalItems;
    }

    public void setItems(List<Item> list) {
        this.items = list;
    }

    public void setItemsPerPage(Integer num) {
        this.itemsPerPage = num;
    }

    public void setPageIndex(Integer num) {
        this.pageIndex = num;
    }

    public void setTotalItems(Integer num) {
        this.totalItems = num;
    }
}
