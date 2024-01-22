package com.coveiot.coveaccess.fitnesschallenge.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class CreateFitnessChallengeReq implements Serializable {
    @SerializedName("bannerImageId")
    @Expose
    private Integer bannerImageId;
    @SerializedName(SavingTrackHelper.POINT_COL_DESCRIPTION)
    @Expose
    private String description;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("participants")
    @Expose
    private List<Participant> participants;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    private Integer target;
    @SerializedName("targetBaseUnits")
    @Expose
    private String targetBaseUnits;
    @SerializedName("transitive")
    @Expose
    private Boolean transitive;

    /* loaded from: classes8.dex */
    public static class Participant implements Serializable {
        @SerializedName("mobileNumber")
        @Expose
        private String mobileNumber;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        private String name;

        public Participant(String str, String str2) {
            this.name = str;
            this.mobileNumber = str2;
        }

        public String getMobileNumber() {
            return this.mobileNumber;
        }

        public String getName() {
            return this.name;
        }

        public void setMobileNumber(String str) {
            this.mobileNumber = str;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    public Integer getBannerImageId() {
        return this.bannerImageId;
    }

    public String getDescription() {
        return this.description;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getName() {
        return this.name;
    }

    public List<Participant> getParticipants() {
        return this.participants;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public Integer getTarget() {
        return this.target;
    }

    public String getTargetBaseUnits() {
        return this.targetBaseUnits;
    }

    public Boolean getTransitive() {
        return this.transitive;
    }

    public void setBannerImageId(Integer num) {
        this.bannerImageId = num;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setParticipants(List<Participant> list) {
        this.participants = list;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public void setTarget(Integer num) {
        this.target = num;
    }

    public void setTargetBaseUnits(String str) {
        this.targetBaseUnits = str;
    }

    public void setTransitive(Boolean bool) {
        this.transitive = bool;
    }
}
