package com.coveiot.android.weather.response.forecastmodel;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherDetail implements Serializable {
    @Nullable
    private Date date;
    @Nullable
    private Double dayTemp;
    @Nullable
    private Double eveTemp;
    @Nullable
    private Double humidity;
    @Nullable
    private Double maxTemp;
    @Nullable
    private Double minTemp;
    @Nullable
    private Double morningTemp;
    @Nullable
    private Double nightTemp;
    @Nullable
    private Double pressure;
    @Nullable
    private Double speed;
    @Nullable
    private Date sunriseDate;
    @Nullable
    private Date sunsetDate;
    @Nullable
    private List<WeatherDesc> weather;

    public WeatherDetail() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public WeatherDetail(@Nullable Date date, @Nullable Date date2, @Nullable Date date3, @Nullable Double d, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Double d9, @Nullable List<WeatherDesc> list) {
        this.date = date;
        this.sunriseDate = date2;
        this.sunsetDate = date3;
        this.dayTemp = d;
        this.nightTemp = d2;
        this.morningTemp = d3;
        this.eveTemp = d4;
        this.minTemp = d5;
        this.maxTemp = d6;
        this.pressure = d7;
        this.humidity = d8;
        this.speed = d9;
        this.weather = list;
    }

    @Nullable
    public final Date component1() {
        return this.date;
    }

    @Nullable
    public final Double component10() {
        return this.pressure;
    }

    @Nullable
    public final Double component11() {
        return this.humidity;
    }

    @Nullable
    public final Double component12() {
        return this.speed;
    }

    @Nullable
    public final List<WeatherDesc> component13() {
        return this.weather;
    }

    @Nullable
    public final Date component2() {
        return this.sunriseDate;
    }

    @Nullable
    public final Date component3() {
        return this.sunsetDate;
    }

    @Nullable
    public final Double component4() {
        return this.dayTemp;
    }

    @Nullable
    public final Double component5() {
        return this.nightTemp;
    }

    @Nullable
    public final Double component6() {
        return this.morningTemp;
    }

    @Nullable
    public final Double component7() {
        return this.eveTemp;
    }

    @Nullable
    public final Double component8() {
        return this.minTemp;
    }

    @Nullable
    public final Double component9() {
        return this.maxTemp;
    }

    @NotNull
    public final WeatherDetail copy(@Nullable Date date, @Nullable Date date2, @Nullable Date date3, @Nullable Double d, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Double d9, @Nullable List<WeatherDesc> list) {
        return new WeatherDetail(date, date2, date3, d, d2, d3, d4, d5, d6, d7, d8, d9, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeatherDetail) {
            WeatherDetail weatherDetail = (WeatherDetail) obj;
            return Intrinsics.areEqual(this.date, weatherDetail.date) && Intrinsics.areEqual(this.sunriseDate, weatherDetail.sunriseDate) && Intrinsics.areEqual(this.sunsetDate, weatherDetail.sunsetDate) && Intrinsics.areEqual((Object) this.dayTemp, (Object) weatherDetail.dayTemp) && Intrinsics.areEqual((Object) this.nightTemp, (Object) weatherDetail.nightTemp) && Intrinsics.areEqual((Object) this.morningTemp, (Object) weatherDetail.morningTemp) && Intrinsics.areEqual((Object) this.eveTemp, (Object) weatherDetail.eveTemp) && Intrinsics.areEqual((Object) this.minTemp, (Object) weatherDetail.minTemp) && Intrinsics.areEqual((Object) this.maxTemp, (Object) weatherDetail.maxTemp) && Intrinsics.areEqual((Object) this.pressure, (Object) weatherDetail.pressure) && Intrinsics.areEqual((Object) this.humidity, (Object) weatherDetail.humidity) && Intrinsics.areEqual((Object) this.speed, (Object) weatherDetail.speed) && Intrinsics.areEqual(this.weather, weatherDetail.weather);
        }
        return false;
    }

    @Nullable
    public final Date getDate() {
        return this.date;
    }

    @Nullable
    public final Double getDayTemp() {
        return this.dayTemp;
    }

    @Nullable
    public final Double getEveTemp() {
        return this.eveTemp;
    }

    @Nullable
    public final Double getHumidity() {
        return this.humidity;
    }

    @Nullable
    public final Double getMaxTemp() {
        return this.maxTemp;
    }

    @Nullable
    public final Double getMinTemp() {
        return this.minTemp;
    }

    @Nullable
    public final Double getMorningTemp() {
        return this.morningTemp;
    }

    @Nullable
    public final Double getNightTemp() {
        return this.nightTemp;
    }

    @Nullable
    public final Double getPressure() {
        return this.pressure;
    }

    @Nullable
    public final Double getSpeed() {
        return this.speed;
    }

    @Nullable
    public final Date getSunriseDate() {
        return this.sunriseDate;
    }

    @Nullable
    public final Date getSunsetDate() {
        return this.sunsetDate;
    }

    @Nullable
    public final List<WeatherDesc> getWeather() {
        return this.weather;
    }

    public int hashCode() {
        Date date = this.date;
        int hashCode = (date == null ? 0 : date.hashCode()) * 31;
        Date date2 = this.sunriseDate;
        int hashCode2 = (hashCode + (date2 == null ? 0 : date2.hashCode())) * 31;
        Date date3 = this.sunsetDate;
        int hashCode3 = (hashCode2 + (date3 == null ? 0 : date3.hashCode())) * 31;
        Double d = this.dayTemp;
        int hashCode4 = (hashCode3 + (d == null ? 0 : d.hashCode())) * 31;
        Double d2 = this.nightTemp;
        int hashCode5 = (hashCode4 + (d2 == null ? 0 : d2.hashCode())) * 31;
        Double d3 = this.morningTemp;
        int hashCode6 = (hashCode5 + (d3 == null ? 0 : d3.hashCode())) * 31;
        Double d4 = this.eveTemp;
        int hashCode7 = (hashCode6 + (d4 == null ? 0 : d4.hashCode())) * 31;
        Double d5 = this.minTemp;
        int hashCode8 = (hashCode7 + (d5 == null ? 0 : d5.hashCode())) * 31;
        Double d6 = this.maxTemp;
        int hashCode9 = (hashCode8 + (d6 == null ? 0 : d6.hashCode())) * 31;
        Double d7 = this.pressure;
        int hashCode10 = (hashCode9 + (d7 == null ? 0 : d7.hashCode())) * 31;
        Double d8 = this.humidity;
        int hashCode11 = (hashCode10 + (d8 == null ? 0 : d8.hashCode())) * 31;
        Double d9 = this.speed;
        int hashCode12 = (hashCode11 + (d9 == null ? 0 : d9.hashCode())) * 31;
        List<WeatherDesc> list = this.weather;
        return hashCode12 + (list != null ? list.hashCode() : 0);
    }

    public final void setDate(@Nullable Date date) {
        this.date = date;
    }

    public final void setDayTemp(@Nullable Double d) {
        this.dayTemp = d;
    }

    public final void setEveTemp(@Nullable Double d) {
        this.eveTemp = d;
    }

    public final void setHumidity(@Nullable Double d) {
        this.humidity = d;
    }

    public final void setMaxTemp(@Nullable Double d) {
        this.maxTemp = d;
    }

    public final void setMinTemp(@Nullable Double d) {
        this.minTemp = d;
    }

    public final void setMorningTemp(@Nullable Double d) {
        this.morningTemp = d;
    }

    public final void setNightTemp(@Nullable Double d) {
        this.nightTemp = d;
    }

    public final void setPressure(@Nullable Double d) {
        this.pressure = d;
    }

    public final void setSpeed(@Nullable Double d) {
        this.speed = d;
    }

    public final void setSunriseDate(@Nullable Date date) {
        this.sunriseDate = date;
    }

    public final void setSunsetDate(@Nullable Date date) {
        this.sunsetDate = date;
    }

    public final void setWeather(@Nullable List<WeatherDesc> list) {
        this.weather = list;
    }

    @NotNull
    public String toString() {
        return "WeatherDetail(date=" + this.date + ", sunriseDate=" + this.sunriseDate + ", sunsetDate=" + this.sunsetDate + ", dayTemp=" + this.dayTemp + ", nightTemp=" + this.nightTemp + ", morningTemp=" + this.morningTemp + ", eveTemp=" + this.eveTemp + ", minTemp=" + this.minTemp + ", maxTemp=" + this.maxTemp + ", pressure=" + this.pressure + ", humidity=" + this.humidity + ", speed=" + this.speed + ", weather=" + this.weather + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WeatherDetail(Date date, Date date2, Date date3, Double d, Double d2, Double d3, Double d4, Double d5, Double d6, Double d7, Double d8, Double d9, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : date, (i & 2) != 0 ? null : date2, (i & 4) != 0 ? null : date3, (i & 8) != 0 ? null : d, (i & 16) != 0 ? null : d2, (i & 32) != 0 ? null : d3, (i & 64) != 0 ? null : d4, (i & 128) != 0 ? null : d5, (i & 256) != 0 ? null : d6, (i & 512) != 0 ? null : d7, (i & 1024) != 0 ? null : d8, (i & 2048) != 0 ? null : d9, (i & 4096) == 0 ? list : null);
    }
}
