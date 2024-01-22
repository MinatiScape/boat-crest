package com.coveiot.android.leonardo.firebaseservices.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class FitnessCMCheerCloverMessage implements Serializable {
    @SerializedName("fitnessGoal")
    @Expose
    private FitnessGoalBean fitnessGoal;
    @SerializedName("fitnessMessageId")
    @Expose
    private String fitnessMessageId;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName("senderUserDpUrl")
    @Expose
    private String senderUserDpUrl;
    @SerializedName("senderUserId")
    @Expose
    private String senderUserId;
    @SerializedName("senderUserName")
    @Expose
    private String senderUserName;
    @SerializedName("sentDate")
    @Expose
    private String sentDate;
    @SerializedName("type")
    @Expose
    private String type;

    /* loaded from: classes2.dex */
    public static class FitnessGoalBean implements Serializable {
        @SerializedName("activityBaseUnit")
        @Expose
        private String activityBaseUnit;
        @SerializedName("activityType")
        @Expose
        private String activityType;
        @SerializedName("goalId")
        @Expose
        private String goalId;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName(TypedValues.AttributesType.S_TARGET)
        @Expose
        private int target;
        @SerializedName("targetAchieved")
        @Expose
        private int targetAchieved;
        @SerializedName("targetedDays")
        @Expose
        private int targetedDays;

        public String getActivityBaseUnit() {
            return this.activityBaseUnit;
        }

        public String getActivityType() {
            return this.activityType;
        }

        public String getGoalId() {
            return this.goalId;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public int getTarget() {
            return this.target;
        }

        public int getTargetAchieved() {
            return this.targetAchieved;
        }

        public int getTargetedDays() {
            return this.targetedDays;
        }

        public void setActivityBaseUnit(String str) {
            this.activityBaseUnit = str;
        }

        public void setActivityType(String str) {
            this.activityType = str;
        }

        public void setGoalId(String str) {
            this.goalId = str;
        }

        public void setStartDate(String str) {
            this.startDate = str;
        }

        public void setTarget(int i) {
            this.target = i;
        }

        public void setTargetAchieved(int i) {
            this.targetAchieved = i;
        }

        public void setTargetedDays(int i) {
            this.targetedDays = i;
        }
    }

    public FitnessGoalBean getFitnessGoal() {
        return this.fitnessGoal;
    }

    public String getFitnessMessageId() {
        return this.fitnessMessageId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSenderUserDpUrl() {
        return this.senderUserDpUrl;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public String getSenderUserName() {
        return this.senderUserName;
    }

    public String getSentDate() {
        return this.sentDate;
    }

    public String getType() {
        return this.type;
    }

    public void setFitnessGoal(FitnessGoalBean fitnessGoalBean) {
        this.fitnessGoal = fitnessGoalBean;
    }

    public void setFitnessMessageId(String str) {
        this.fitnessMessageId = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setSenderUserDpUrl(String str) {
        this.senderUserDpUrl = str;
    }

    public void setSenderUserId(String str) {
        this.senderUserId = str;
    }

    public void setSenderUserName(String str) {
        this.senderUserName = str;
    }

    public void setSentDate(String str) {
        this.sentDate = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
