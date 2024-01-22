package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessPlanTemplateRes implements Serializable {
    @SerializedName("data")
    @Expose
    private FitnessPlanTemplateData data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public static class FitnessPlanTemplateData implements Serializable {
        @SerializedName("activityMappings")
        @Expose
        private List<ActivityMapping> activityMappings;
        @SerializedName("categoryId")
        @Expose
        private String categoryId;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;
        @SerializedName("fullTitle")
        @Expose
        private String fullTitle;
        @SerializedName("images")
        @Expose
        private Images images;
        @SerializedName("introText")
        @Expose
        private List<String> introText;
        @SerializedName("planTemplateId")
        @Expose
        private String planTemplateId;
        @SerializedName("schedule")
        @Expose
        private Schedule schedule;
        @SerializedName("shortDesc")
        @Expose
        private String shortDesc;
        @SerializedName("shortTitle")
        @Expose
        private String shortTitle;
        @SerializedName("sortIndex")
        @Expose
        private Integer sortIndex;
        @SerializedName("subTitle")
        @Expose
        private String subTitle;

        /* loaded from: classes8.dex */
        public static class ActivityMapping implements Serializable {
            @SerializedName("activityType")
            @Expose
            private String activityType;
            @SerializedName("physicalActivityCodes")
            @Expose
            private List<String> physicalActivityCodes;
            @SerializedName("sessionTypes")
            @Expose
            private List<String> sessionTypes;

            public String getActivityType() {
                return this.activityType;
            }

            public List<String> getPhysicalActivityCodes() {
                return this.physicalActivityCodes;
            }

            public List<String> getSessionTypes() {
                return this.sessionTypes;
            }

            public void setActivityType(String str) {
                this.activityType = str;
            }

            public void setPhysicalActivityCodes(List<String> list) {
                this.physicalActivityCodes = list;
            }

            public void setSessionTypes(List<String> list) {
                this.sessionTypes = list;
            }
        }

        /* loaded from: classes8.dex */
        public static class Images implements Serializable {
            @SerializedName("background1")
            @Expose
            private String background1;
            @SerializedName("thumbnail1")
            @Expose
            private String thumbnail1;

            public String getBackground1() {
                return this.background1;
            }

            public String getThumbnail1() {
                return this.thumbnail1;
            }

            public void setBackground1(String str) {
                this.background1 = str;
            }

            public void setThumbnail1(String str) {
                this.thumbnail1 = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class Schedule implements Serializable {
            @SerializedName("overview")
            @Expose
            private List<String> overview;
            @SerializedName("weeks")
            @Expose
            private List<Week> weeks;

            /* loaded from: classes8.dex */
            public static class Week implements Serializable {
                @SerializedName("days")
                @Expose
                private List<Day> days;
                @SerializedName("introText")
                @Expose
                private List<String> introText;
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
                @Expose
                private String name;
                @SerializedName("shortDesc")
                @Expose
                private String shortDesc;
                @SerializedName("weekNumber")
                @Expose
                private Integer weekNumber;

                /* loaded from: classes8.dex */
                public static class Day implements Serializable {
                    @SerializedName("activities")
                    @Expose
                    private Object activities;
                    @SerializedName("dayNumber")
                    @Expose
                    private Integer dayNumber;
                    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
                    @Expose
                    private Object name;
                    @SerializedName("totalDistance")
                    @Expose
                    private Integer totalDistance;

                    public Object getActivities() {
                        return this.activities;
                    }

                    public Integer getDayNumber() {
                        return this.dayNumber;
                    }

                    public Object getName() {
                        return this.name;
                    }

                    public Integer getTotalDistance() {
                        return this.totalDistance;
                    }

                    public void setActivities(Object obj) {
                        this.activities = obj;
                    }

                    public void setDayNumber(Integer num) {
                        this.dayNumber = num;
                    }

                    public void setName(Object obj) {
                        this.name = obj;
                    }

                    public void setTotalDistance(Integer num) {
                        this.totalDistance = num;
                    }
                }

                public List<Day> getDays() {
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

                public Integer getWeekNumber() {
                    return this.weekNumber;
                }

                public void setDays(List<Day> list) {
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

                public void setWeekNumber(Integer num) {
                    this.weekNumber = num;
                }
            }

            public List<String> getOverview() {
                return this.overview;
            }

            public List<Week> getWeeks() {
                return this.weeks;
            }

            public void setOverview(List<String> list) {
                this.overview = list;
            }

            public void setWeeks(List<Week> list) {
                this.weeks = list;
            }
        }

        public List<ActivityMapping> getActivityMappings() {
            return this.activityMappings;
        }

        public String getCategoryId() {
            return this.categoryId;
        }

        public String getCategoryName() {
            return this.categoryName;
        }

        public String getFullTitle() {
            return this.fullTitle;
        }

        public Images getImages() {
            return this.images;
        }

        public List<String> getIntroText() {
            return this.introText;
        }

        public String getPlanTemplateId() {
            return this.planTemplateId;
        }

        public Schedule getSchedule() {
            return this.schedule;
        }

        public String getShortDesc() {
            return this.shortDesc;
        }

        public String getShortTitle() {
            return this.shortTitle;
        }

        public Integer getSortIndex() {
            return this.sortIndex;
        }

        public String getSubTitle() {
            return this.subTitle;
        }

        public void setActivityMappings(List<ActivityMapping> list) {
            this.activityMappings = list;
        }

        public void setCategoryId(String str) {
            this.categoryId = str;
        }

        public void setCategoryName(String str) {
            this.categoryName = str;
        }

        public void setFullTitle(String str) {
            this.fullTitle = str;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        public void setIntroText(List<String> list) {
            this.introText = list;
        }

        public void setPlanTemplateId(String str) {
            this.planTemplateId = str;
        }

        public void setSchedule(Schedule schedule) {
            this.schedule = schedule;
        }

        public void setShortDesc(String str) {
            this.shortDesc = str;
        }

        public void setShortTitle(String str) {
            this.shortTitle = str;
        }

        public void setSortIndex(Integer num) {
            this.sortIndex = num;
        }

        public void setSubTitle(String str) {
            this.subTitle = str;
        }
    }

    public FitnessPlanTemplateData getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(FitnessPlanTemplateData fitnessPlanTemplateData) {
        this.data = fitnessPlanTemplateData;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
