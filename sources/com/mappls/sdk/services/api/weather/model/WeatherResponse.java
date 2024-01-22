package com.mappls.sdk.services.api.weather.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class WeatherResponse {
    @SerializedName("data")
    @Expose
    private WeatherData data;

    public WeatherData getData() {
        return this.data;
    }

    public void setData(WeatherData weatherData) {
        this.data = weatherData;
    }
}
