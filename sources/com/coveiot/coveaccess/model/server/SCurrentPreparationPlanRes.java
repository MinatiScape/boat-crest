package com.coveiot.coveaccess.model.server;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class SCurrentPreparationPlanRes implements Serializable {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean implements Serializable {
        @SerializedName("activationDate")
        private String activationDate;
        @SerializedName("categoryChosen")
        private String categoryChosen;
        @SerializedName("createdDate")
        private String createdDate;
        @SerializedName("planTemplate")
        private PlanTemplatesBean planTemplate;
        @SerializedName("planTemplateId")
        private String planTemplateId;
        @SerializedName("previousPlansSubscribed")
        private int previousPlansSubscribed;
        @SerializedName("schedule")
        private ScheduleBean schedule;
        @SerializedName("userPlanId")
        private String userPlanId;

        /* loaded from: classes8.dex */
        public static class ScheduleBean implements Serializable {
            @SerializedName("weeks")
            private List<WeeksBean> weeks;

            /* loaded from: classes8.dex */
            public static class WeeksBean implements Serializable {
                @SerializedName("days")
                private List<DaysBean> days;
                @SerializedName("weekNumber")
                private int weekNumber;

                /* loaded from: classes8.dex */
                public static class DaysBean implements Serializable {
                    @SerializedName("activities")
                    private List<ActivitiesBean> activities;
                    @SerializedName("date")
                    private String date;
                    @SerializedName("dayNumber")
                    private int dayNumber;

                    /* loaded from: classes8.dex */
                    public static class ActivitiesBean implements Serializable {
                        @SerializedName("activityBaseUnit")
                        private String activityBaseUnit;
                        @SerializedName("activityCode")
                        private String activityCode;
                        @SerializedName("activityType")
                        private String activityType;
                        @SerializedName("hrZoneId")
                        private String hrZoneId;
                        @SerializedName(TypedValues.AttributesType.S_TARGET)
                        private String target;
                        @SerializedName("targetAchieved")
                        private String targetAchieved;
                        @SerializedName("title")
                        private String title;

                        public String getActivityBaseUnit() {
                            return this.activityBaseUnit;
                        }

                        public String getActivityCode() {
                            return this.activityCode;
                        }

                        public String getActivityType() {
                            return this.activityType;
                        }

                        public String getHrZoneId() {
                            return this.hrZoneId;
                        }

                        public String getTarget() {
                            return this.target;
                        }

                        public String getTargetAchieved() {
                            return this.targetAchieved;
                        }

                        public String getTitle() {
                            return this.title;
                        }

                        public void setActivityBaseUnit(String str) {
                            this.activityBaseUnit = str;
                        }

                        public void setActivityCode(String str) {
                            this.activityCode = str;
                        }

                        public void setActivityType(String str) {
                            this.activityType = str;
                        }

                        public void setHrZoneId(String str) {
                            this.hrZoneId = str;
                        }

                        public void setTarget(String str) {
                            this.target = str;
                        }

                        public void setTargetAchieved(String str) {
                            this.targetAchieved = str;
                        }

                        public void setTitle(String str) {
                            this.title = str;
                        }
                    }

                    public List<ActivitiesBean> getActivities() {
                        return this.activities;
                    }

                    public String getDate() {
                        return this.date;
                    }

                    public int getDayNumber() {
                        return this.dayNumber;
                    }

                    public void setActivities(List<ActivitiesBean> list) {
                        this.activities = list;
                    }

                    public void setDate(String str) {
                        this.date = str;
                    }

                    public void setDayNumber(int i) {
                        this.dayNumber = i;
                    }
                }

                public List<DaysBean> getDays() {
                    return this.days;
                }

                public int getWeekNumber() {
                    return this.weekNumber;
                }

                public void setDays(List<DaysBean> list) {
                    this.days = list;
                }

                public void setWeekNumber(int i) {
                    this.weekNumber = i;
                }
            }

            public List<WeeksBean> getWeeks() {
                return this.weeks;
            }

            public void setWeeks(List<WeeksBean> list) {
                this.weeks = list;
            }
        }

        public String getActivationDate() {
            return this.activationDate;
        }

        public String getCategoryChosen() {
            return this.categoryChosen;
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public PlanTemplatesBean getPlanTemplate() {
            return this.planTemplate;
        }

        public String getPlanTemplateId() {
            return this.planTemplateId;
        }

        public int getPreviousPlansSubscribed() {
            return this.previousPlansSubscribed;
        }

        public ScheduleBean getSchedule() {
            return this.schedule;
        }

        public String getUserPlanId() {
            return this.userPlanId;
        }

        public void setActivationDate(String str) {
            this.activationDate = str;
        }

        public void setCategoryChosen(String str) {
            this.categoryChosen = str;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setPlanTemplate(PlanTemplatesBean planTemplatesBean) {
            this.planTemplate = planTemplatesBean;
        }

        public void setPlanTemplateId(String str) {
            this.planTemplateId = str;
        }

        public void setPreviousPlansSubscribed(int i) {
            this.previousPlansSubscribed = i;
        }

        public void setSchedule(ScheduleBean scheduleBean) {
            this.schedule = scheduleBean;
        }

        public void setUserPlanId(String str) {
            this.userPlanId = str;
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
