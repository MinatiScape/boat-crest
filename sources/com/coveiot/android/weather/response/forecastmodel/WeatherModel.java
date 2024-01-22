package com.coveiot.android.weather.response.forecastmodel;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherModel implements Serializable {
    @Nullable
    private Integer cityId;
    @Nullable
    private String cityName;
    @Nullable
    private Integer cnt;
    @Nullable
    private String cod;
    @Nullable
    private Double message;
    @Nullable
    private List<WeatherDetail> weatherDetailList;

    public WeatherModel() {
        this(null, null, null, null, null, null, 63, null);
    }

    public WeatherModel(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable Double d, @Nullable Integer num2, @Nullable List<WeatherDetail> list) {
        this.cityId = num;
        this.cityName = str;
        this.cod = str2;
        this.message = d;
        this.cnt = num2;
        this.weatherDetailList = list;
    }

    public static /* synthetic */ WeatherModel copy$default(WeatherModel weatherModel, Integer num, String str, String str2, Double d, Integer num2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            num = weatherModel.cityId;
        }
        if ((i & 2) != 0) {
            str = weatherModel.cityName;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = weatherModel.cod;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            d = weatherModel.message;
        }
        Double d2 = d;
        if ((i & 16) != 0) {
            num2 = weatherModel.cnt;
        }
        Integer num3 = num2;
        List<WeatherDetail> list2 = list;
        if ((i & 32) != 0) {
            list2 = weatherModel.weatherDetailList;
        }
        return weatherModel.copy(num, str3, str4, d2, num3, list2);
    }

    @Nullable
    public final Integer component1() {
        return this.cityId;
    }

    @Nullable
    public final String component2() {
        return this.cityName;
    }

    @Nullable
    public final String component3() {
        return this.cod;
    }

    @Nullable
    public final Double component4() {
        return this.message;
    }

    @Nullable
    public final Integer component5() {
        return this.cnt;
    }

    @Nullable
    public final List<WeatherDetail> component6() {
        return this.weatherDetailList;
    }

    @NotNull
    public final WeatherModel copy(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable Double d, @Nullable Integer num2, @Nullable List<WeatherDetail> list) {
        return new WeatherModel(num, str, str2, d, num2, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeatherModel) {
            WeatherModel weatherModel = (WeatherModel) obj;
            return Intrinsics.areEqual(this.cityId, weatherModel.cityId) && Intrinsics.areEqual(this.cityName, weatherModel.cityName) && Intrinsics.areEqual(this.cod, weatherModel.cod) && Intrinsics.areEqual((Object) this.message, (Object) weatherModel.message) && Intrinsics.areEqual(this.cnt, weatherModel.cnt) && Intrinsics.areEqual(this.weatherDetailList, weatherModel.weatherDetailList);
        }
        return false;
    }

    @Nullable
    public final Integer getCityId() {
        return this.cityId;
    }

    @Nullable
    public final String getCityName() {
        return this.cityName;
    }

    @Nullable
    public final Integer getCnt() {
        return this.cnt;
    }

    @Nullable
    public final String getCod() {
        return this.cod;
    }

    @Nullable
    public final Double getMessage() {
        return this.message;
    }

    @Nullable
    public final List<WeatherDetail> getWeatherDetailList() {
        return this.weatherDetailList;
    }

    public int hashCode() {
        Integer num = this.cityId;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.cityName;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.cod;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Double d = this.message;
        int hashCode4 = (hashCode3 + (d == null ? 0 : d.hashCode())) * 31;
        Integer num2 = this.cnt;
        int hashCode5 = (hashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        List<WeatherDetail> list = this.weatherDetailList;
        return hashCode5 + (list != null ? list.hashCode() : 0);
    }

    public final void setCityId(@Nullable Integer num) {
        this.cityId = num;
    }

    public final void setCityName(@Nullable String str) {
        this.cityName = str;
    }

    public final void setCnt(@Nullable Integer num) {
        this.cnt = num;
    }

    public final void setCod(@Nullable String str) {
        this.cod = str;
    }

    public final void setMessage(@Nullable Double d) {
        this.message = d;
    }

    public final void setWeatherDetailList(@Nullable List<WeatherDetail> list) {
        this.weatherDetailList = list;
    }

    @NotNull
    public String toString() {
        return "WeatherModel(cityId=" + this.cityId + ", cityName=" + this.cityName + ", cod=" + this.cod + ", message=" + this.message + ", cnt=" + this.cnt + ", weatherDetailList=" + this.weatherDetailList + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WeatherModel(Integer num, String str, String str2, Double d, Integer num2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : d, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : list);
    }
}
