package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessPlanHistoryRes implements Serializable {
    @SerializedName("data")
    @Expose
    private FitnessPlanHistoryData data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public static class FitnessPlanHistoryData implements Serializable {
        @SerializedName("plansHistory")
        @Expose
        private List<PlansHistory> plansHistory;

        /* loaded from: classes8.dex */
        public static class PlansHistory implements Serializable {
            @SerializedName("activationDate")
            @Expose
            private String activationDate;
            @SerializedName("categoryChosen")
            @Expose
            private String categoryChosen;
            @SerializedName("createdDate")
            @Expose
            private String createdDate;
            @SerializedName("deactivationDate")
            @Expose
            private String deactivationDate;
            @SerializedName("endDate")
            @Expose
            private String endDate;
            @SerializedName("lastModifiedDate")
            @Expose
            private String lastModifiedDate;
            @SerializedName("planTemplate")
            @Expose
            private PlanTemplate planTemplate;
            @SerializedName("progress")
            @Expose
            private Integer progress;
            @SerializedName("progressStatus")
            @Expose
            private String progressStatus;
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
            public static class PlanTemplate implements Serializable {
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
                @SerializedName("planTemplateId")
                @Expose
                private String planTemplateId;
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

                public String getPlanTemplateId() {
                    return this.planTemplateId;
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

                public void setPlanTemplateId(String str) {
                    this.planTemplateId = str;
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

            public String getActivationDate() {
                return this.activationDate;
            }

            public String getCategoryChosen() {
                return this.categoryChosen;
            }

            public String getCreatedDate() {
                return this.createdDate;
            }

            public String getDeactivationDate() {
                return this.deactivationDate;
            }

            public String getEndDate() {
                return this.endDate;
            }

            public String getLastModifiedDate() {
                return this.lastModifiedDate;
            }

            public PlanTemplate getPlanTemplate() {
                return this.planTemplate;
            }

            public Integer getProgress() {
                return this.progress;
            }

            public String getProgressStatus() {
                return this.progressStatus;
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

            public void setActivationDate(String str) {
                this.activationDate = str;
            }

            public void setCategoryChosen(String str) {
                this.categoryChosen = str;
            }

            public void setCreatedDate(String str) {
                this.createdDate = str;
            }

            public void setDeactivationDate(String str) {
                this.deactivationDate = str;
            }

            public void setEndDate(String str) {
                this.endDate = str;
            }

            public void setLastModifiedDate(String str) {
                this.lastModifiedDate = str;
            }

            public void setPlanTemplate(PlanTemplate planTemplate) {
                this.planTemplate = planTemplate;
            }

            public void setProgress(Integer num) {
                this.progress = num;
            }

            public void setProgressStatus(String str) {
                this.progressStatus = str;
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

        public List<PlansHistory> getPlansHistory() {
            return this.plansHistory;
        }

        public void setPlansHistory(List<PlansHistory> list) {
            this.plansHistory = list;
        }
    }

    public FitnessPlanHistoryData getFitnessPlanHistory() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(FitnessPlanHistoryData fitnessPlanHistoryData) {
        this.data = fitnessPlanHistoryData;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
