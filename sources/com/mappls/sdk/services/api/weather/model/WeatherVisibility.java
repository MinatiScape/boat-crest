package com.mappls.sdk.services.api.weather.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class WeatherVisibility {
    @SerializedName("visibilityUnit")
    @Expose
    private String unit;
    @SerializedName("visibility")
    @Expose
    private Integer value;

    public String getUnit() {
        return this.unit;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public void setValue(Integer num) {
        this.value = num;
    }
}
