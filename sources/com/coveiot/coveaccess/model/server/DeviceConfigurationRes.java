package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public class DeviceConfigurationRes {
    @SerializedName("calibration")
    private CalibrationBean calibration;

    /* loaded from: classes8.dex */
    public static class CalibrationBean {
        @SerializedName(DeviceKey.TempData)
        private TemperatureBean temperature;

        /* loaded from: classes8.dex */
        public static class TemperatureBean {
            @SerializedName("baseUnit")
            private String baseUnit;
            @SerializedName("value")
            private double value;

            public String getBaseUnit() {
                return this.baseUnit;
            }

            public double getValue() {
                return this.value;
            }

            public void setBaseUnit(String str) {
                this.baseUnit = str;
            }

            public void setValue(double d) {
                this.value = d;
            }
        }

        public TemperatureBean getTemperature() {
            return this.temperature;
        }

        public void setTemperature(TemperatureBean temperatureBean) {
            this.temperature = temperatureBean;
        }
    }

    public CalibrationBean getCalibration() {
        return this.calibration;
    }

    public void setCalibration(CalibrationBean calibrationBean) {
        this.calibration = calibrationBean;
    }
}
