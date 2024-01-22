package com.coveiot.android.weathersdk.server.network.forecastmodel;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.List;
/* loaded from: classes8.dex */
public class SWeatherForecastModel {
    @SerializedName(GeoCodingCriteria.POD_CITY)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public SCity f6226a;
    @SerializedName("cod")
    @Expose
    public String b;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    public Double c;
    @SerializedName("cnt")
    @Expose
    public Integer d;
    @SerializedName("list")
    @Expose
    public List<SWeatherDetailList> e = null;

    public SCity getCity() {
        return this.f6226a;
    }

    public Integer getCnt() {
        return this.d;
    }

    public String getCod() {
        return this.b;
    }

    public Double getMessage() {
        return this.c;
    }

    public List<SWeatherDetailList> getWeatherDetilList() {
        return this.e;
    }

    public void setCity(SCity sCity) {
        this.f6226a = sCity;
    }

    public void setCnt(Integer num) {
        this.d = num;
    }

    public void setCod(String str) {
        this.b = str;
    }

    public void setMessage(Double d) {
        this.c = d;
    }

    public void setWeatherDetilList(List<SWeatherDetailList> list) {
        this.e = list;
    }
}
