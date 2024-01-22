package com.coveiot.coveaccess.model.server;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class PlanTemplatesBean implements Serializable {
    @SerializedName("categoryName")
    private String categoryName;
    @SerializedName("fullTitle")
    private String fullTitle;
    @SerializedName("images")
    private ImagesBean images;
    @SerializedName("introText")
    private List<String> introText;
    @SerializedName("planTemplateId")
    private String planTemplateId;
    @SerializedName("schedule")
    private ScheduleBean schedule;
    @SerializedName("shortDesc")
    private String shortDesc;
    @SerializedName("shortTitle")
    private String shortTitle;
    @SerializedName("sortIndex")
    private int sortIndex;
    @SerializedName("subTitle")
    private String subTitle;

    /* loaded from: classes8.dex */
    public static class ImagesBean implements Serializable {
        @SerializedName("thumbnail1")
        private String thumbnail1;

        public String getThumbnail1() {
            return this.thumbnail1;
        }

        public void setThumbnail1(String str) {
            this.thumbnail1 = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class ScheduleBean implements Serializable {
        @SerializedName("overview")
        private List<String> overview;
        @SerializedName("weeks")
        private List<WeeksBean> weeks;

        /* loaded from: classes8.dex */
        public static class WeeksBean implements Serializable {
            @SerializedName("days")
            private List<DaysBean> days;
            @SerializedName("introText")
            private List<String> introText;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            private String name;
            @SerializedName("shortDesc")
            private String shortDesc;
            @SerializedName("weekNumber")
            private int weekNumber;

            /* loaded from: classes8.dex */
            public static class DaysBean implements Serializable {
                @SerializedName("activities")
                private List<ActivitiesBean> activities;
                @SerializedName("dayNumber")
                private int dayNumber;
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
                private String name;

                /* loaded from: classes8.dex */
                public static class ActivitiesBean implements Serializable {
                    @SerializedName("activityBaseUnit")
                    private String activityBaseUnit;
                    @SerializedName("activityType")
                    private String activityType;
                    @SerializedName("hrZoneId")
                    private String hrZoneId;
                    @SerializedName(TypedValues.AttributesType.S_TARGET)
                    private String target;
                    @SerializedName("title")
                    private String title;

                    public String getActivityBaseUnit() {
                        return this.activityBaseUnit;
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

                    public String getTitle() {
                        return this.title;
                    }

                    public void setActivityBaseUnit(String str) {
                        this.activityBaseUnit = str;
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

                    public void setTitle(String str) {
                        this.title = str;
                    }
                }

                public List<ActivitiesBean> getActivities() {
                    return this.activities;
                }

                public int getDayNumber() {
                    return this.dayNumber;
                }

                public String getName() {
                    return this.name;
                }

                public void setActivities(List<ActivitiesBean> list) {
                    this.activities = list;
                }

                public void setDayNumber(int i) {
                    this.dayNumber = i;
                }

                public void setName(String str) {
                    this.name = str;
                }
            }

            public List<DaysBean> getDays() {
                return this.days;
            }

            public List<String> getIntroText() {
                return this.introText;
            }

            public String getName() {
                return this.name;
            }

            public String getShortDesc() {
                return this.shortDesc;
            }

            public int getWeekNumber() {
                return this.weekNumber;
            }

            public void setDays(List<DaysBean> list) {
                this.days = list;
            }

            public void setIntroText(List<String> list) {
                this.introText = list;
            }

            public void setName(String str) {
                this.name = str;
            }

            public void setShortDesc(String str) {
                this.shortDesc = str;
            }

            public void setWeekNumber(int i) {
                this.weekNumber = i;
            }
        }

        public List<String> getOverview() {
            return this.overview;
        }

        public List<WeeksBean> getWeeks() {
            return this.weeks;
        }

        public void setOverview(List<String> list) {
            this.overview = list;
        }

        public void setWeeks(List<WeeksBean> list) {
            this.weeks = list;
        }
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getFullTitle() {
        return this.fullTitle;
    }

    public ImagesBean getImages() {
        return this.images;
    }

    public List<String> getIntroText() {
        return this.introText;
    }

    public String getPlanTemplateId() {
        return this.planTemplateId;
    }

    public ScheduleBean getSchedule() {
        return this.schedule;
    }

    public String getShortDesc() {
        return this.shortDesc;
    }

    public String getShortTitle() {
        return this.shortTitle;
    }

    public int getSortIndex() {
        return this.sortIndex;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public void setFullTitle(String str) {
        this.fullTitle = str;
    }

    public void setImages(ImagesBean imagesBean) {
        this.images = imagesBean;
    }

    public void setIntroText(List<String> list) {
        this.introText = list;
    }

    public void setPlanTemplateId(String str) {
        this.planTemplateId = str;
    }

    public void setSchedule(ScheduleBean scheduleBean) {
        this.schedule = scheduleBean;
    }

    public void setShortDesc(String str) {
        this.shortDesc = str;
    }

    public void setShortTitle(String str) {
        this.shortTitle = str;
    }

    public void setSortIndex(int i) {
        this.sortIndex = i;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }
}
