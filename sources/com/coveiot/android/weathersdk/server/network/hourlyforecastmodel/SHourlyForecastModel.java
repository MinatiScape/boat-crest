package com.coveiot.android.weathersdk.server.network.hourlyforecastmodel;

import com.coveiot.android.femalewellness.wellnesscalendar.Constants;
import com.coveiot.android.weathersdk.server.network.forecastmodel.SCity;
import com.coveiot.android.weathersdk.server.network.model.SMain;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.List;
/* loaded from: classes8.dex */
public class SHourlyForecastModel {
    @SerializedName(Constants.MAIN_TAG)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public SMain f6227a;
    @SerializedName(GeoCodingCriteria.POD_CITY)
    @Expose
    public SCity b;
    @SerializedName("cod")
    @Expose
    public String c;
    @SerializedName(com.clevertap.android.sdk.Constants.KEY_MESSAGE)
    @Expose
    public Double d;
    @SerializedName("cnt")
    @Expose
    public Integer e;
    @SerializedName("list")
    @Expose
    public List<SHourlyWeatherDetailList> f = null;

    public SCity getCity() {
        return this.b;
    }

    public Integer getCnt() {
        return this.e;
    }

    public String getCod() {
        return this.c;
    }

    public List<SHourlyWeatherDetailList> getHourlyWeatherDetailList() {
        return this.f;
    }

    public SMain getMain() {
        return this.f6227a;
    }

    public Double getMessage() {
        return this.d;
    }

    public void setCity(SCity sCity) {
        this.b = sCity;
    }

    public void setCnt(Integer num) {
        this.e = num;
    }

    public void setCod(String str) {
        this.c = str;
    }

    public void setHourlyWeatherDetailList(List<SHourlyWeatherDetailList> list) {
        this.f = list;
    }

    public void setMain(SMain sMain) {
        this.f6227a = sMain;
    }

    public void setMessage(Double d) {
        this.d = d;
    }
}
