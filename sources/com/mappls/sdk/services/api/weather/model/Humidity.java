package com.mappls.sdk.services.api.weather.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class Humidity {
    @SerializedName("indoorRelHumidity")
    @Expose
    private Integer indoorRelHumidity;
    @SerializedName("relHumidity")
    @Expose
    private Integer relHumidity;

    public Integer getIndoorRelHumidity() {
        return this.indoorRelHumidity;
    }

    public Integer getRelHumidity() {
        return this.relHumidity;
    }

    public void setIndoorRelHumidity(Integer num) {
        this.indoorRelHumidity = num;
    }

    public void setRelHumidity(Integer num) {
        this.relHumidity = num;
    }
}
