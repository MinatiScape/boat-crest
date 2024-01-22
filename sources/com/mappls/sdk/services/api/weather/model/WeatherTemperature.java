package com.mappls.sdk.services.api.weather.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
@Keep
/* loaded from: classes8.dex */
public class WeatherTemperature {
    @SerializedName("maxTemperature")
    @Expose
    private Double maxTemperature;
    @SerializedName("maxTemperatureUnit")
    @Expose
    private String maxTemperatureUnit;
    @SerializedName("minTemperature")
    @Expose
    private Double minTemperature;
    @SerializedName("minTemperatureUnit")
    @Expose
    private String minTemperatureUnit;
    @SerializedName(DeviceKey.TempUnit)
    @Expose
    private String unit;
    @SerializedName(DeviceKey.TempData)
    @Expose
    private Double value;

    public Double getMaxTemperature() {
        return this.maxTemperature;
    }

    public String getMaxTemperatureUnit() {
        return this.maxTemperatureUnit;
    }

    public Double getMinTemperature() {
        return this.minTemperature;
    }

    public String getMinTemperatureUnit() {
        return this.minTemperatureUnit;
    }

    public String getUnit() {
        return this.unit;
    }

    public Double getValue() {
        return this.value;
    }

    public void setMaxTemperature(Double d) {
        this.maxTemperature = d;
    }

    public void setMaxTemperatureUnit(String str) {
        this.maxTemperatureUnit = str;
    }

    public void setMinTemperature(Double d) {
        this.minTemperature = d;
    }

    public void setMinTemperatureUnit(String str) {
        this.minTemperatureUnit = str;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public void setValue(Double d) {
        this.value = d;
    }
}
