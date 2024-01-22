package com.coveiot.android.weathersdk.response.hourlyforecast;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b;\b\u0086\b\u0018\u00002\u00020\u0001Bµ\u0001\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010+\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a¢\u0006\u0004\bj\u0010kJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\u0004J\u0012\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\f\u0010\u000bJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\u0004J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000bJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0007J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0004J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0013J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0007J\u0018\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ¾\u0001\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010+\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aHÆ\u0001¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b.\u0010/J\u001a\u00102\u001a\u0002012\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b2\u00103R$\u0010\u001e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u0010\u0007\"\u0004\b7\u00108R$\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b9\u0010:\u001a\u0004\b;\u0010\u0004\"\u0004\b<\u0010=R$\u0010 \u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010\u000b\"\u0004\bA\u0010BR$\u0010!\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bC\u0010?\u001a\u0004\bD\u0010\u000b\"\u0004\bE\u0010BR$\u0010\"\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u0010:\u001a\u0004\bG\u0010\u0004\"\u0004\bH\u0010=R$\u0010#\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bI\u0010?\u001a\u0004\bJ\u0010\u000b\"\u0004\bK\u0010BR$\u0010$\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bL\u00105\u001a\u0004\bM\u0010\u0007\"\u0004\bN\u00108R$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bO\u0010:\u001a\u0004\bP\u0010\u0004\"\u0004\bQ\u0010=R$\u0010&\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010\u0013\"\u0004\bU\u0010VR$\u0010'\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bW\u0010X\u001a\u0004\bY\u0010\u0016\"\u0004\bZ\u0010[R$\u0010(\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\\\u0010S\u001a\u0004\b]\u0010\u0013\"\u0004\b^\u0010VR$\u0010)\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b_\u0010X\u001a\u0004\b`\u0010\u0016\"\u0004\ba\u0010[R$\u0010*\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bb\u00105\u001a\u0004\bc\u0010\u0007\"\u0004\bd\u00108R*\u0010+\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\be\u0010f\u001a\u0004\bg\u0010\u001d\"\u0004\bh\u0010i¨\u0006l"}, d2 = {"Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherForecastModel;", "", "", "toString", "()Ljava/lang/String;", "", "component1", "()Ljava/lang/Integer;", "component2", "", "component3", "()Ljava/lang/Double;", "component4", "component5", "component6", "component7", "component8", "", "component9", "()Ljava/lang/Long;", "Ljava/util/Date;", "component10", "()Ljava/util/Date;", "component11", "component12", "component13", "", "Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherDetails;", "component14", "()Ljava/util/List;", "cityId", "cityName", "citylon", "cityLat", "country", "population", "timezone", "cod", "sunrise", "sunriseDate", "sunset", "sunsetDate", "cnt", "weatherDetailList", Constants.COPY_TYPE, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Integer;Ljava/util/List;)Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherForecastModel;", "hashCode", "()I", FitnessActivities.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/Integer;", "getCityId", "setCityId", "(Ljava/lang/Integer;)V", "b", "Ljava/lang/String;", "getCityName", "setCityName", "(Ljava/lang/String;)V", c.f10260a, "Ljava/lang/Double;", "getCitylon", "setCitylon", "(Ljava/lang/Double;)V", "d", "getCityLat", "setCityLat", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getCountry", "setCountry", "f", "getPopulation", "setPopulation", "g", "getTimezone", "setTimezone", "h", "getCod", "setCod", "i", "Ljava/lang/Long;", "getSunrise", "setSunrise", "(Ljava/lang/Long;)V", "j", "Ljava/util/Date;", "getSunriseDate", "setSunriseDate", "(Ljava/util/Date;)V", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getSunset", "setSunset", "l", "getSunsetDate", "setSunsetDate", "m", "getCnt", "setCnt", "n", "Ljava/util/List;", "getWeatherDetailList", "setWeatherDetailList", "(Ljava/util/List;)V", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Integer;Ljava/util/List;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class HourlyWeatherForecastModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f6218a;
    @Nullable
    public String b;
    @Nullable
    public Double c;
    @Nullable
    public Double d;
    @Nullable
    public String e;
    @Nullable
    public Double f;
    @Nullable
    public Integer g;
    @Nullable
    public String h;
    @Nullable
    public Long i;
    @Nullable
    public Date j;
    @Nullable
    public Long k;
    @Nullable
    public Date l;
    @Nullable
    public Integer m;
    @Nullable
    public List<HourlyWeatherDetails> n;

    public HourlyWeatherForecastModel() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    public HourlyWeatherForecastModel(@Nullable Integer num, @Nullable String str, @Nullable Double d, @Nullable Double d2, @Nullable String str2, @Nullable Double d3, @Nullable Integer num2, @Nullable String str3, @Nullable Long l, @Nullable Date date, @Nullable Long l2, @Nullable Date date2, @Nullable Integer num3, @Nullable List<HourlyWeatherDetails> list) {
        this.f6218a = num;
        this.b = str;
        this.c = d;
        this.d = d2;
        this.e = str2;
        this.f = d3;
        this.g = num2;
        this.h = str3;
        this.i = l;
        this.j = date;
        this.k = l2;
        this.l = date2;
        this.m = num3;
        this.n = list;
    }

    public /* synthetic */ HourlyWeatherForecastModel(Integer num, String str, Double d, Double d2, String str2, Double d3, Integer num2, String str3, Long l, Date date, Long l2, Date date2, Integer num3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : d, (i & 8) != 0 ? null : d2, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : d3, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? null : l, (i & 512) != 0 ? null : date, (i & 1024) != 0 ? null : l2, (i & 2048) != 0 ? null : date2, (i & 4096) != 0 ? null : num3, (i & 8192) == 0 ? list : null);
    }

    @Nullable
    public final Integer component1() {
        return this.f6218a;
    }

    @Nullable
    public final Date component10() {
        return this.j;
    }

    @Nullable
    public final Long component11() {
        return this.k;
    }

    @Nullable
    public final Date component12() {
        return this.l;
    }

    @Nullable
    public final Integer component13() {
        return this.m;
    }

    @Nullable
    public final List<HourlyWeatherDetails> component14() {
        return this.n;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final Double component3() {
        return this.c;
    }

    @Nullable
    public final Double component4() {
        return this.d;
    }

    @Nullable
    public final String component5() {
        return this.e;
    }

    @Nullable
    public final Double component6() {
        return this.f;
    }

    @Nullable
    public final Integer component7() {
        return this.g;
    }

    @Nullable
    public final String component8() {
        return this.h;
    }

    @Nullable
    public final Long component9() {
        return this.i;
    }

    @NotNull
    public final HourlyWeatherForecastModel copy(@Nullable Integer num, @Nullable String str, @Nullable Double d, @Nullable Double d2, @Nullable String str2, @Nullable Double d3, @Nullable Integer num2, @Nullable String str3, @Nullable Long l, @Nullable Date date, @Nullable Long l2, @Nullable Date date2, @Nullable Integer num3, @Nullable List<HourlyWeatherDetails> list) {
        return new HourlyWeatherForecastModel(num, str, d, d2, str2, d3, num2, str3, l, date, l2, date2, num3, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof HourlyWeatherForecastModel) {
                HourlyWeatherForecastModel hourlyWeatherForecastModel = (HourlyWeatherForecastModel) obj;
                return Intrinsics.areEqual(this.f6218a, hourlyWeatherForecastModel.f6218a) && Intrinsics.areEqual(this.b, hourlyWeatherForecastModel.b) && Intrinsics.areEqual((Object) this.c, (Object) hourlyWeatherForecastModel.c) && Intrinsics.areEqual((Object) this.d, (Object) hourlyWeatherForecastModel.d) && Intrinsics.areEqual(this.e, hourlyWeatherForecastModel.e) && Intrinsics.areEqual((Object) this.f, (Object) hourlyWeatherForecastModel.f) && Intrinsics.areEqual(this.g, hourlyWeatherForecastModel.g) && Intrinsics.areEqual(this.h, hourlyWeatherForecastModel.h) && Intrinsics.areEqual(this.i, hourlyWeatherForecastModel.i) && Intrinsics.areEqual(this.j, hourlyWeatherForecastModel.j) && Intrinsics.areEqual(this.k, hourlyWeatherForecastModel.k) && Intrinsics.areEqual(this.l, hourlyWeatherForecastModel.l) && Intrinsics.areEqual(this.m, hourlyWeatherForecastModel.m) && Intrinsics.areEqual(this.n, hourlyWeatherForecastModel.n);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final Integer getCityId() {
        return this.f6218a;
    }

    @Nullable
    public final Double getCityLat() {
        return this.d;
    }

    @Nullable
    public final String getCityName() {
        return this.b;
    }

    @Nullable
    public final Double getCitylon() {
        return this.c;
    }

    @Nullable
    public final Integer getCnt() {
        return this.m;
    }

    @Nullable
    public final String getCod() {
        return this.h;
    }

    @Nullable
    public final String getCountry() {
        return this.e;
    }

    @Nullable
    public final Double getPopulation() {
        return this.f;
    }

    @Nullable
    public final Long getSunrise() {
        return this.i;
    }

    @Nullable
    public final Date getSunriseDate() {
        return this.j;
    }

    @Nullable
    public final Long getSunset() {
        return this.k;
    }

    @Nullable
    public final Date getSunsetDate() {
        return this.l;
    }

    @Nullable
    public final Integer getTimezone() {
        return this.g;
    }

    @Nullable
    public final List<HourlyWeatherDetails> getWeatherDetailList() {
        return this.n;
    }

    public int hashCode() {
        Integer num = this.f6218a;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        Double d = this.c;
        int hashCode3 = (hashCode2 + (d != null ? d.hashCode() : 0)) * 31;
        Double d2 = this.d;
        int hashCode4 = (hashCode3 + (d2 != null ? d2.hashCode() : 0)) * 31;
        String str2 = this.e;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Double d3 = this.f;
        int hashCode6 = (hashCode5 + (d3 != null ? d3.hashCode() : 0)) * 31;
        Integer num2 = this.g;
        int hashCode7 = (hashCode6 + (num2 != null ? num2.hashCode() : 0)) * 31;
        String str3 = this.h;
        int hashCode8 = (hashCode7 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Long l = this.i;
        int hashCode9 = (hashCode8 + (l != null ? l.hashCode() : 0)) * 31;
        Date date = this.j;
        int hashCode10 = (hashCode9 + (date != null ? date.hashCode() : 0)) * 31;
        Long l2 = this.k;
        int hashCode11 = (hashCode10 + (l2 != null ? l2.hashCode() : 0)) * 31;
        Date date2 = this.l;
        int hashCode12 = (hashCode11 + (date2 != null ? date2.hashCode() : 0)) * 31;
        Integer num3 = this.m;
        int hashCode13 = (hashCode12 + (num3 != null ? num3.hashCode() : 0)) * 31;
        List<HourlyWeatherDetails> list = this.n;
        return hashCode13 + (list != null ? list.hashCode() : 0);
    }

    public final void setCityId(@Nullable Integer num) {
        this.f6218a = num;
    }

    public final void setCityLat(@Nullable Double d) {
        this.d = d;
    }

    public final void setCityName(@Nullable String str) {
        this.b = str;
    }

    public final void setCitylon(@Nullable Double d) {
        this.c = d;
    }

    public final void setCnt(@Nullable Integer num) {
        this.m = num;
    }

    public final void setCod(@Nullable String str) {
        this.h = str;
    }

    public final void setCountry(@Nullable String str) {
        this.e = str;
    }

    public final void setPopulation(@Nullable Double d) {
        this.f = d;
    }

    public final void setSunrise(@Nullable Long l) {
        this.i = l;
    }

    public final void setSunriseDate(@Nullable Date date) {
        this.j = date;
    }

    public final void setSunset(@Nullable Long l) {
        this.k = l;
    }

    public final void setSunsetDate(@Nullable Date date) {
        this.l = date;
    }

    public final void setTimezone(@Nullable Integer num) {
        this.g = num;
    }

    public final void setWeatherDetailList(@Nullable List<HourlyWeatherDetails> list) {
        this.n = list;
    }

    @NotNull
    public String toString() {
        return "HourlyWeatherForecastModel(cityId=" + this.f6218a + ", cityName=" + this.b + ", citylon=" + this.c + ", cityLat=" + this.d + ", country=" + this.e + ", population=" + this.f + ", timezone=" + this.g + ", cod=" + this.h + ",sunrise=" + this.i + ", sunriseDate=" + this.j + ", sunset=" + this.k + ", sunsetDate=" + this.l + ", cnt=" + this.m + ", weatherDetailList=" + this.n + HexStringBuilder.COMMENT_END_CHAR;
    }
}
