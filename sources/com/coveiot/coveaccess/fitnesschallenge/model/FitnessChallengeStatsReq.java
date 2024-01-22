package com.coveiot.coveaccess.fitnesschallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessChallengeStatsReq implements Serializable {
    @SerializedName("challengeStats")
    @Expose
    private List<ChallengeStat> challengeStatsList;

    /* loaded from: classes8.dex */
    public static class ChallengeStat implements Serializable {
        @SerializedName("baseUnits")
        @Expose
        private BaseUnitsData baseUnits;
        @SerializedName("calories")
        @Expose
        private Integer calories;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("distance")
        @Expose
        private Integer distance;
        @SerializedName("steps")
        @Expose
        private Integer steps;

        /* loaded from: classes8.dex */
        public static class BaseUnitsData implements Serializable {
            @SerializedName("distance")
            @Expose
            private String distance;

            public BaseUnitsData(String str) {
                this.distance = str;
            }

            public String getDistance() {
                return this.distance;
            }

            public void setDistance(String str) {
                this.distance = str;
            }
        }

        public ChallengeStat() {
        }

        public BaseUnitsData getBaseUnits() {
            return this.baseUnits;
        }

        public Integer getCalories() {
            return this.calories;
        }

        public String getDate() {
            return this.date;
        }

        public Integer getDistance() {
            return this.distance;
        }

        public Integer getSteps() {
            return this.steps;
        }

        public void setBaseUnits(BaseUnitsData baseUnitsData) {
            this.baseUnits = baseUnitsData;
        }

        public void setCalories(Integer num) {
            this.calories = num;
        }

        public void setDate(String str) {
            this.date = str;
        }

        public void setDistance(Integer num) {
            this.distance = num;
        }

        public void setSteps(Integer num) {
            this.steps = num;
        }

        public ChallengeStat(String str, Integer num, Integer num2, Integer num3, BaseUnitsData baseUnitsData) {
            this.date = str;
            this.steps = num;
            this.distance = num2;
            this.calories = num3;
            this.baseUnits = baseUnitsData;
        }
    }

    public List<ChallengeStat> getChallengeStatsList() {
        return this.challengeStatsList;
    }

    public void setChallengeStatsList(List<ChallengeStat> list) {
        this.challengeStatsList = list;
    }
}
