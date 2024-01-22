package com.coveiot.coveaccess.model.server;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessPlanProgressReq implements Serializable {
    @SerializedName("progress")
    @Expose
    private Integer progress;
    @SerializedName("progressStatus")
    @Expose
    private String progressStatus;
    @SerializedName("schedule")
    @Expose
    private Schedule schedule;
    @SerializedName("totalCalories")
    @Expose
    private Integer totalCalories;
    @SerializedName("totalDistance")
    @Expose
    private Integer totalDistance;
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

                    public Activity(String str, String str2, String str3, String str4, String str5) {
                        this.activityType = str;
                        this.activityCode = str2;
                        this.target = str3;
                        this.targetAchieved = str4;
                        this.activityBaseUnit = str5;
                    }

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
                }

                public Day(Integer num, String str, List<Activity> list) {
                    this.dayNumber = num;
                    this.date = str;
                    this.activities = list;
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

            public Week(Integer num, List<Day> list) {
                this.weekNumber = num;
                this.days = list;
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

        public Schedule(List<Week> list) {
            this.weeks = list;
        }

        public List<Week> getWeeks() {
            return this.weeks;
        }

        public void setWeeks(List<Week> list) {
            this.weeks = list;
        }
    }

    public FitnessPlanProgressReq(String str, Integer num, String str2, Integer num2, Integer num3, Integer num4, Schedule schedule) {
        this.userPlanId = str;
        this.progress = num;
        this.progressStatus = str2;
        this.totalCalories = num2;
        this.totalDistance = num3;
        this.totalSteps = num4;
        this.schedule = schedule;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public String getProgressStatus() {
        return this.progressStatus;
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    public Integer getTotalCalories() {
        return this.totalCalories;
    }

    public Integer getTotalDistance() {
        return this.totalDistance;
    }

    public Integer getTotalSteps() {
        return this.totalSteps;
    }

    public String getUserPlanId() {
        return this.userPlanId;
    }

    public void setProgress(Integer num) {
        this.progress = num;
    }

    public void setProgressStatus(String str) {
        this.progressStatus = str;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setTotalCalories(Integer num) {
        this.totalCalories = num;
    }

    public void setTotalDistance(Integer num) {
        this.totalDistance = num;
    }

    public void setTotalSteps(Integer num) {
        this.totalSteps = num;
    }

    public void setUserPlanId(String str) {
        this.userPlanId = str;
    }
}
