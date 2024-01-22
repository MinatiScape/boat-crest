package com.coveiot.coveaccess.fitnessbuddies.model.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class BuddiesGoalsBean {
    @SerializedName("buddy")
    private BuddyBean buddy;
    @SerializedName("goals")
    private List<GoalsBean> goals;

    /* loaded from: classes8.dex */
    public class BuddyBean {
        @SerializedName("dpUrl")
        private String dpUrl;
        @SerializedName("mobileNumber")
        private String mobileNumber;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String name;
        @SerializedName(Constants.END_USER_GLOBAL_ID)
        private String userId;

        public BuddyBean() {
        }

        public String getDpUrl() {
            return this.dpUrl;
        }

        public String getMobileNumber() {
            return this.mobileNumber;
        }

        public String getName() {
            return this.name;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setDpUrl(String str) {
            this.dpUrl = str;
        }

        public void setMobileNumber(String str) {
            this.mobileNumber = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setUserId(String str) {
            this.userId = str;
        }
    }

    /* loaded from: classes8.dex */
    public class GoalsBean {
        @SerializedName("activityBaseUnit")
        private String activityBaseUnit;
        @SerializedName("activityType")
        private String activityType;
        @SerializedName("goalId")
        private String goalId;
        @SerializedName("modifiedDate")
        private String modifiedDate;
        @SerializedName("startDate")
        private String startDate;
        @SerializedName(TypedValues.AttributesType.S_TARGET)
        private String target;
        @SerializedName("targetAchieved")
        private String targetAchieved;
        @SerializedName("targetedDays")
        private int targetedDays;

        public GoalsBean() {
        }

        public String getActivityBaseUnit() {
            return this.activityBaseUnit;
        }

        public String getActivityType() {
            return this.activityType;
        }

        public int getGoalId() {
            return Integer.parseInt(this.goalId);
        }

        public String getModifiedDate() {
            return this.modifiedDate;
        }

        public String getStartDate() {
            return this.startDate;
        }

        public int getTarget() {
            return Integer.parseInt(this.target);
        }

        public int getTargetAchieved() {
            return Integer.parseInt(this.targetAchieved);
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

        public void setModifiedDate(String str) {
            this.modifiedDate = str;
        }

        public void setStartDate(String str) {
            this.startDate = str;
        }

        public void setTarget(String str) {
            this.target = str;
        }

        public void setTargetAchieved(String str) {
            this.targetAchieved = str;
        }

        public void setTargetedDays(int i) {
            this.targetedDays = i;
        }
    }

    public BuddyBean getBuddy() {
        return this.buddy;
    }

    public GoalsBean getGoalInfoByActivityType(String str) {
        if (getGoals() != null && getGoals().size() != 0) {
            for (GoalsBean goalsBean : getGoals()) {
                if (goalsBean.activityType.equalsIgnoreCase(str)) {
                    return goalsBean;
                }
            }
        }
        return null;
    }

    public List<GoalsBean> getGoals() {
        return this.goals;
    }

    public void setBuddy(BuddyBean buddyBean) {
        this.buddy = buddyBean;
    }

    public void setGoals(List<GoalsBean> list) {
        this.goals = list;
    }
}
