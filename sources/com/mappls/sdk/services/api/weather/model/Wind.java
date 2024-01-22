package com.mappls.sdk.services.api.weather.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class Wind {
    @SerializedName("windAngle")
    @Expose
    private Integer windAngle;
    @SerializedName("windAngleUnit")
    @Expose
    private String windAngleUnit;
    @SerializedName("windSpeed")
    @Expose
    private Double windSpeed;
    @SerializedName("windSpeedUnit")
    @Expose
    private String windSpeedUnit;

    public Integer getWindAngle() {
        return this.windAngle;
    }

    public String getWindAngleUnit() {
        return this.windAngleUnit;
    }

    public Double getWindSpeed() {
        return this.windSpeed;
    }

    public String getWindSpeedUnit() {
        return this.windSpeedUnit;
    }

    public void setWindAngle(Integer num) {
        this.windAngle = num;
    }

    public void setWindAngleUnit(String str) {
        this.windAngleUnit = str;
    }

    public void setWindSpeed(Double d) {
        this.windSpeed = d;
    }

    public void setWindSpeedUnit(String str) {
        this.windSpeedUnit = str;
    }
}
