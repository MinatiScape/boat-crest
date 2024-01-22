package com.coveiot.coveaccess.model.server;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class UserPlanInfoRes implements Serializable {
    @SerializedName("data")
    @Expose
    private UserFitnessPlanData data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public static class UserFitnessPlanData implements Serializable {
        @SerializedName("activationDate")
        @Expose
        private String activationDate;
        @SerializedName("activitiesMapped")
        @Expose
        private Boolean activitiesMapped;
        @SerializedName("categoryChosen")
        @Expose
        private String categoryChosen;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("planTemplateId")
        @Expose
        private String planTemplateId;
        @SerializedName("progress")
        @Expose
        private Integer progress;
        @SerializedName("schedule")
        @Expose
        private Schedule schedule;
        @SerializedName("totalCalories")
        @Expose
        private Double totalCalories;
        @SerializedName("totalDistance")
        @Expose
        private Double totalDistance;
        @SerializedName("totalSteps")
        @Expose
        private Integer totalSteps;
        @SerializedName("userPlanId")
        @Expose
        private String userPlanId;

        /* loaded from: classes8.dex */
        public static class Schedule implements Serializable {
            @SerializedName("weeks")
            @Expose
            private List<Week> weeks;

            /* loaded from: classes8.dex */
            public static class Week implements Serializable {
                @SerializedName("days")
                @Expose
                private List<Day> days;
                @SerializedName("weekNumber")
                @Expose
                private Integer weekNumber;

                /* loaded from: classes8.dex */
                public static class Day implements Serializable {
                    @SerializedName("activities")
                    @Expose
                    private List<Activity> activities;
                    @SerializedName("date")
                    @Expose
                    private String date;
                    @SerializedName("dayNumber")
                    @Expose
                    private Integer dayNumber;
                    @SerializedName("totalDistance")
                    @Expose
                    private Integer totalDistance;

                    /* loaded from: classes8.dex */
                    public static class Activity implements Serializable {
                        @SerializedName("activityBaseUnit")
                        @Expose
                        private String activityBaseUnit;
                        @SerializedName("activityCode")
                        @Expose
                        private String activityCode;
                        @SerializedName("activityType")
                        @Expose
                        private String activityType;
                        @SerializedName(TypedValues.AttributesType.S_TARGET)
                        @Expose
                        private String target;
                        @SerializedName("targetAchieved")
                        @Expose
                        private String targetAchieved;
                        @SerializedName("title")
                        @Expose
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

                    public List<Activity> getActivities() {
                        return this.activities;
                    }

                    public String getDate() {
                        return this.date;
                    }

                    public Integer getDayNumber() {
                        return this.dayNumber;
                    }

                    public Integer getTotalDistance() {
                        return this.totalDistance;
                    }

                    public void setActivities(List<Activity> list) {
                        this.activities = list;
                    }

                    public void setDate(String str) {
                        this.date = str;
                    }

                    public void setDayNumber(Integer num) {
                        this.dayNumber = num;
                    }

                    public void setTotalDistance(Integer num) {
                        this.totalDistance = num;
                    }
                }

                public List<Day> getDays() {
                    return this.days;
                }

                public Integer getWeekNumber() {
                    return this.weekNumber;
                }

                public void setDays(List<Day> list) {
                    this.days = list;
                }

                public void setWeekNumber(Integer num) {
                    this.weekNumber = num;
                }
            }

            public List<Week> getWeeks() {
                return this.weeks;
            }

            public void setWeeks(List<Week> list) {
                this.weeks = list;
            }
        }

        public String getActivationDate() {
            return this.activationDate;
        }

        public Boolean getActivitiesMapped() {
            return this.activitiesMapped;
        }

        public String getCategoryChosen() {
            return this.categoryChosen;
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public String getEndDate() {
            return this.endDate;
        }

        public String getPlanTemplateId() {
            return this.planTemplateId;
        }

        public Integer getProgress() {
            return this.progress;
        }

        public Schedule getSchedule() {
            return this.schedule;
        }

        public Double getTotalCalories() {
            return this.totalCalories;
        }

        public Double getTotalDistance() {
            return this.totalDistance;
        }

        public Integer getTotalSteps() {
            return this.totalSteps;
        }

        public String getUserPlanId() {
            return this.userPlanId;
        }

        public void setActivationDate(String str) {
            this.activationDate = str;
        }

        public void setActivitiesMapped(Boolean bool) {
            this.activitiesMapped = bool;
        }

        public void setCategoryChosen(String str) {
            this.categoryChosen = str;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setEndDate(String str) {
            this.endDate = str;
        }

        public void setPlanTemplateId(String str) {
            this.planTemplateId = str;
        }

        public void setProgress(Integer num) {
            this.progress = num;
        }

        public void setSchedule(Schedule schedule) {
            this.schedule = schedule;
        }

        public void setTotalCalories(Double d) {
            this.totalCalories = d;
        }

        public void setTotalDistance(Double d) {
            this.totalDistance = d;
        }

        public void setTotalSteps(Integer num) {
            this.totalSteps = num;
        }

        public void setUserPlanId(String str) {
            this.userPlanId = str;
        }
    }

    public UserFitnessPlanData getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(UserFitnessPlanData userFitnessPlanData) {
        this.data = userFitnessPlanData;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
