package com.coveiot.coveaccess.fitness.config.models.responsemodel;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class FitnessSummaryResponse implements Serializable {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private String createdDate;
        private List<FitnessStatsBean> fitnessStats;
        private String height;
        private String lastModifiedDate;
        private String runningStrideLength;
        private String skinType;
        private String walkingStrideLength;
        private String weight;

        /* loaded from: classes8.dex */
        public static class FitnessStatsBean {
            private String activityBaseUnit;
            private String activityType;
            private String calories;
            private String date;
            private String meters;
            private String statsType;
            private String value;

            public String getActivityBaseUnit() {
                return this.activityBaseUnit;
            }

            public String getActivityType() {
                return this.activityType;
            }

            public String getCalories() {
                return this.calories;
            }

            public String getDate() {
                return this.date;
            }

            public String getMeters() {
                return this.meters;
            }

            public String getStatsType() {
                return this.statsType;
            }

            public String getValue() {
                return this.value;
            }

            public void setActivityBaseUnit(String str) {
                this.activityBaseUnit = str;
            }

            public void setActivityType(String str) {
                this.activityType = str;
            }

            public void setCalories(String str) {
                this.calories = str;
            }

            public void setDate(String str) {
                this.date = str;
            }

            public void setMeters(String str) {
                this.meters = str;
            }

            public void setStatsType(String str) {
                this.statsType = str;
            }

            public void setValue(String str) {
                this.value = str;
            }
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public List<FitnessStatsBean> getFitnessStats() {
            return this.fitnessStats;
        }

        public String getHeight() {
            return this.height;
        }

        public String getLastModifiedDate() {
            return this.lastModifiedDate;
        }

        public String getRunningStrideLength() {
            return this.runningStrideLength;
        }

        public String getSkinType() {
            return this.skinType;
        }

        public String getWalkingStrideLength() {
            return this.walkingStrideLength;
        }

        public String getWeight() {
            return this.weight;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setFitnessStats(List<FitnessStatsBean> list) {
            this.fitnessStats = list;
        }

        public void setHeight(String str) {
            this.height = str;
        }

        public void setLastModifiedDate(String str) {
            this.lastModifiedDate = str;
        }

        public void setRunningStrideLength(String str) {
            this.runningStrideLength = str;
        }

        public void setSkinType(String str) {
            this.skinType = str;
        }

        public void setWalkingStrideLength(String str) {
            this.walkingStrideLength = str;
        }

        public void setWeight(String str) {
            this.weight = str;
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
