package com.coveiot.android.fitnessbuddies.models;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes4.dex */
public class FitnessAcceptedCloversGoalData implements Serializable {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes4.dex */
    public static class DataBean implements Serializable {
        @SerializedName("buddiesGoals")
        private List<BuddiesGoalsBean> buddiesGoals;

        /* loaded from: classes4.dex */
        public static class BuddiesGoalsBean implements Serializable {
            @SerializedName("buddy")
            private BuddyBean buddy;
            @SerializedName("goals")
            private List<GoalsBean> goals;

            /* loaded from: classes4.dex */
            public static class BuddyBean implements Serializable {
                @SerializedName("dpUrl")
                private String dpUrl;
                @SerializedName("mobileNumber")
                private String mobileNumber;
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
                private String name;
                @SerializedName(com.coveiot.android.tappy.utils.Constants.END_USER_GLOBAL_ID)
                private String userId;

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

            /* loaded from: classes4.dex */
            public static class GoalsBean implements Serializable {
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
                if (AppUtils.isEmpty(getGoals())) {
                    return null;
                }
                for (GoalsBean goalsBean : getGoals()) {
                    if (goalsBean.activityType.equalsIgnoreCase(str)) {
                        return goalsBean;
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

        public List<BuddiesGoalsBean> getBuddiesGoals() {
            return this.buddiesGoals;
        }

        public void setBuddiesGoals(List<BuddiesGoalsBean> list) {
            this.buddiesGoals = list;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
