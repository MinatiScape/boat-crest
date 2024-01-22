package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public class SGetHealthStatusRes {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName(DeviceKey.TempData)
        private TemperatureBean temperature;

        /* loaded from: classes8.dex */
        public static class TemperatureBean {
            @SerializedName("flaggedDate")
            private String flaggedDate;
            @SerializedName("isFlagged")
            private boolean isFlagged;
            @SerializedName("locationBreachConfig")
            private LocationBreachConfigBean locationBreachConfig;
            @SerializedName("pauseTriggerForHours")
            private int pauseTriggerForHours;

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

            public String getFlaggedDate() {
                return this.flaggedDate;
            }

            public LocationBreachConfigBean getLocationBreachConfig() {
                return this.locationBreachConfig;
            }

            public int getPauseTriggerForHours() {
                return this.pauseTriggerForHours;
            }

            public boolean isIsFlagged() {
                return this.isFlagged;
            }

            public void setFlaggedDate(String str) {
                this.flaggedDate = str;
            }

            public void setIsFlagged(boolean z) {
                this.isFlagged = z;
            }

            public void setLocationBreachConfig(LocationBreachConfigBean locationBreachConfigBean) {
                this.locationBreachConfig = locationBreachConfigBean;
            }

            public void setPauseTriggerForHours(int i) {
                this.pauseTriggerForHours = i;
            }
        }

        public TemperatureBean getTemperature() {
            return this.temperature;
        }

        public void setTemperature(TemperatureBean temperatureBean) {
            this.temperature = temperatureBean;
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
