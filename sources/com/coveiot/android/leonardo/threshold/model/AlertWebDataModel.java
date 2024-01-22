package com.coveiot.android.leonardo.threshold.model;

import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes5.dex */
public class AlertWebDataModel implements Serializable {
    @SerializedName("data")
    private DataBean data;

    /* loaded from: classes5.dex */
    public static class DataBean implements Serializable {
        @SerializedName("currentLocation")
        private List<Double> currentLocation;
        @SerializedName(DeviceKey.TempData)
        private TemperatureBean temperature;
        @SerializedName("triggerType")
        private String triggerType;

        /* loaded from: classes5.dex */
        public static class TemperatureBean implements Serializable {
            @SerializedName("baseUnit")
            private String baseUnit;
            @SerializedName("currentBaseUnit")
            private String currentBaseUnit;
            @SerializedName("recordedDate")
            private String time;
            @SerializedName("value")
            private float value;

            public String getBaseUnit() {
                return this.baseUnit;
            }

            public String getCurrentBaseUnit() {
                return this.currentBaseUnit;
            }

            public String getTime() {
                return this.time;
            }

            public float getValue() {
                return this.value;
            }

            public void setBaseUnit(String str) {
                this.baseUnit = str;
            }

            public void setCurrentBaseUnit(String str) {
                this.currentBaseUnit = str;
            }

            public void setTime(String str) {
                this.time = str;
            }

            public void setValue(float f) {
                this.value = f;
            }
        }

        public List<Double> getCurrentLocation() {
            return this.currentLocation;
        }

        public TemperatureBean getTemperature() {
            return this.temperature;
        }

        public String getTriggerType() {
            return this.triggerType;
        }

        public void setCurrentLocation(List<Double> list) {
            this.currentLocation = list;
        }

        public void setTemperature(TemperatureBean temperatureBean) {
            this.temperature = temperatureBean;
        }

        public void setTriggerType(String str) {
            this.triggerType = str;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }
}
