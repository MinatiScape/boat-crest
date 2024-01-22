package com.coveiot.coveaccess.model.server;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveLocationBreachInfo {
    @SerializedName("data")
    private List<DataBean> data;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("breachLocations")
        private List<BreachLocationsBean> breachLocations;
        @SerializedName("currentLocation")
        private List<Double> currentLocation;
        @SerializedName("endTime")
        private String endTime;
        @SerializedName("isLocationBreached")
        private boolean isLocationBreached;
        @SerializedName("locationBreachConfig")
        private LocationBreachConfigBean locationBreachConfig;
        @SerializedName("startTime")
        private String startTime;

        /* loaded from: classes8.dex */
        public static class BreachLocationsBean {
            @SerializedName("arrivalTime")
            private String arrivalTime;
            @SerializedName(FirebaseAnalytics.Param.LOCATION)
            private List<Double> location;

            public String getArrivalTime() {
                return this.arrivalTime;
            }

            public List<Double> getLocation() {
                return this.location;
            }

            public void setArrivalTime(String str) {
                this.arrivalTime = str;
            }

            public void setLocation(List<Double> list) {
                this.location = list;
            }
        }

        /* loaded from: classes8.dex */
        public static class LocationBreachConfigBean {
            @SerializedName("distanceThresholdGte")
            private int distanceThresholdGte;
            @SerializedName("maxBucketSize")
            private int maxBucketSize;
            @SerializedName("maxTimeSpan")
            private int maxTimeSpan;

            public int getDistanceThresholdGte() {
                return this.distanceThresholdGte;
            }

            public int getMaxBucketSize() {
                return this.maxBucketSize;
            }

            public int getMaxTimeSpan() {
                return this.maxTimeSpan;
            }

            public void setDistanceThresholdGte(int i) {
                this.distanceThresholdGte = i;
            }

            public void setMaxBucketSize(int i) {
                this.maxBucketSize = i;
            }

            public void setMaxTimeSpan(int i) {
                this.maxTimeSpan = i;
            }
        }

        public List<BreachLocationsBean> getBreachLocations() {
            return this.breachLocations;
        }

        public List<Double> getCurrentLocation() {
            return this.currentLocation;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public LocationBreachConfigBean getLocationBreachConfig() {
            return this.locationBreachConfig;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public boolean isIsLocationBreached() {
            return this.isLocationBreached;
        }

        public void setBreachLocations(List<BreachLocationsBean> list) {
            this.breachLocations = list;
        }

        public void setCurrentLocation(List<Double> list) {
            this.currentLocation = list;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public void setIsLocationBreached(boolean z) {
            this.isLocationBreached = z;
        }

        public void setLocationBreachConfig(LocationBreachConfigBean locationBreachConfigBean) {
            this.locationBreachConfig = locationBreachConfigBean;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }
}
