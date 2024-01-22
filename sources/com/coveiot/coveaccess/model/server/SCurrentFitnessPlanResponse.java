package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class SCurrentFitnessPlanResponse implements Serializable {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean implements Serializable {
        private String activationDate;
        private String createdDate;
        @SerializedName("planTemplate")
        private PlanTemplatesBean planTemplate;
        private String planTemplateId;
        @SerializedName("previousPlansSubscribed")
        private int previousPlansSubscribed;
        private ScheduleBean schedule;
        private String userPlanId;

        /* loaded from: classes8.dex */
        public static class ScheduleBean implements Serializable {
            private List<WeeksBean> weeks;

            /* loaded from: classes8.dex */
            public static class WeeksBean implements Serializable {
                private List<DaysBean> days;
                private int weekNumber;

                /* loaded from: classes8.dex */
                public static class DaysBean implements Serializable {
                    private List<ActivitiesBean> activities;
                    private String date;
                    private int dayNumber;

                    /* loaded from: classes8.dex */
                    public static class ActivitiesBean implements Serializable {
                        private String activityBaseUnit;
                        private String activityType;
                        private String target;
                        private String targetAchieved;
                        private String title;

                        public String getActivityBaseUnit() {
                            return this.activityBaseUnit;
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
