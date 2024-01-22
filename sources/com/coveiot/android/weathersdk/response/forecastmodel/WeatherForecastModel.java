package com.coveiot.android.weathersdk.response.forecastmodel;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b.\b\u0086\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\u0004\bS\u0010TJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\u0004J\u0012\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\f\u0010\u000bJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\u0004J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000bJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0007J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0004J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u0011\u0010\u000bJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0007J\u0018\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u009a\u0001\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013HÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010(\u001a\u00020'2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b(\u0010)R$\u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010.R$\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u0010\u0004\"\u0004\b2\u00103R$\u0010\u0019\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u0010\u000b\"\u0004\b7\u00108R$\u0010\u001a\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b9\u00105\u001a\u0004\b:\u0010\u000b\"\u0004\b;\u00108R$\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u00100\u001a\u0004\b=\u0010\u0004\"\u0004\b>\u00103R$\u0010\u001c\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b?\u00105\u001a\u0004\b@\u0010\u000b\"\u0004\bA\u00108R$\u0010\u001d\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bB\u0010+\u001a\u0004\bC\u0010\u0007\"\u0004\bD\u0010.R$\u0010\u001e\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u00100\u001a\u0004\bF\u0010\u0004\"\u0004\bG\u00103R$\u0010\u001f\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bH\u00105\u001a\u0004\bI\u0010\u000b\"\u0004\bJ\u00108R$\u0010 \u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u0010+\u001a\u0004\bL\u0010\u0007\"\u0004\bM\u0010.R*\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010\u0016\"\u0004\bQ\u0010R¨\u0006U"}, d2 = {"Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherForecastModel;", "", "", "toString", "()Ljava/lang/String;", "", "component1", "()Ljava/lang/Integer;", "component2", "", "component3", "()Ljava/lang/Double;", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "", "Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherDetails;", "component11", "()Ljava/util/List;", "cityId", "cityName", "citylon", "cityLat", "country", "population", "timezone", "cod", Constants.KEY_MESSAGE, "cnt", "weatherDetailList", Constants.COPY_TYPE, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/util/List;)Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherForecastModel;", "hashCode", "()I", FitnessActivities.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/Integer;", "getCityId", "setCityId", "(Ljava/lang/Integer;)V", "b", "Ljava/lang/String;", "getCityName", "setCityName", "(Ljava/lang/String;)V", c.f10260a, "Ljava/lang/Double;", "getCitylon", "setCitylon", "(Ljava/lang/Double;)V", "d", "getCityLat", "setCityLat", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getCountry", "setCountry", "f", "getPopulation", "setPopulation", "g", "getTimezone", "setTimezone", "h", "getCod", "setCod", "i", "getMessage", "setMessage", "j", "getCnt", "setCnt", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Ljava/util/List;", "getWeatherDetailList", "setWeatherDetailList", "(Ljava/util/List;)V", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/util/List;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherForecastModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f6216a;
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
    public Double i;
    @Nullable
    public Integer j;
    @Nullable
    public List<WeatherDetails> k;

    public WeatherForecastModel() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    public WeatherForecastModel(@Nullable Integer num, @Nullable String str, @Nullable Double d, @Nullable Double d2, @Nullable String str2, @Nullable Double d3, @Nullable Integer num2, @Nullable String str3, @Nullable Double d4, @Nullable Integer num3, @Nullable List<WeatherDetails> list) {
        this.f6216a = num;
        this.b = str;
        this.c = d;
        this.d = d2;
        this.e = str2;
        this.f = d3;
        this.g = num2;
        this.h = str3;
        this.i = d4;
        this.j = num3;
        this.k = list;
    }

    public /* synthetic */ WeatherForecastModel(Integer num, String str, Double d, Double d2, String str2, Double d3, Integer num2, String str3, Double d4, Integer num3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : d, (i & 8) != 0 ? null : d2, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : d3, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : str3, (i & 256) != 0 ? null : d4, (i & 512) != 0 ? null : num3, (i & 1024) == 0 ? list : null);
    }

    @Nullable
    public final Integer component1() {
        return this.f6216a;
    }

    @Nullable
    public final Integer component10() {
        return this.j;
    }

    @Nullable
    public final List<WeatherDetails> component11() {
        return this.k;
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
    public final Double component9() {
        return this.i;
    }

    @NotNull
    public final WeatherForecastModel copy(@Nullable Integer num, @Nullable String str, @Nullable Double d, @Nullable Double d2, @Nullable String str2, @Nullable Double d3, @Nullable Integer num2, @Nullable String str3, @Nullable Double d4, @Nullable Integer num3, @Nullable List<WeatherDetails> list) {
        return new WeatherForecastModel(num, str, d, d2, str2, d3, num2, str3, d4, num3, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof WeatherForecastModel) {
                WeatherForecastModel weatherForecastModel = (WeatherForecastModel) obj;
                return Intrinsics.areEqual(this.f6216a, weatherForecastModel.f6216a) && Intrinsics.areEqual(this.b, weatherForecastModel.b) && Intrinsics.areEqual((Object) this.c, (Object) weatherForecastModel.c) && Intrinsics.areEqual((Object) this.d, (Object) weatherForecastModel.d) && Intrinsics.areEqual(this.e, weatherForecastModel.e) && Intrinsics.areEqual((Object) this.f, (Object) weatherForecastModel.f) && Intrinsics.areEqual(this.g, weatherForecastModel.g) && Intrinsics.areEqual(this.h, weatherForecastModel.h) && Intrinsics.areEqual((Object) this.i, (Object) weatherForecastModel.i) && Intrinsics.areEqual(this.j, weatherForecastModel.j) && Intrinsics.areEqual(this.k, weatherForecastModel.k);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final Integer getCityId() {
        return this.f6216a;
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
        return this.j;
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
    public final Double getMessage() {
        return this.i;
    }

    @Nullable
    public final Double getPopulation() {
        return this.f;
    }

    @Nullable
    public final Integer getTimezone() {
        return this.g;
    }

    @Nullable
    public final List<WeatherDetails> getWeatherDetailList() {
        return this.k;
    }

    public int hashCode() {
        Integer num = this.f6216a;
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
        Double d4 = this.i;
        int hashCode9 = (hashCode8 + (d4 != null ? d4.hashCode() : 0)) * 31;
        Integer num3 = this.j;
        int hashCode10 = (hashCode9 + (num3 != null ? num3.hashCode() : 0)) * 31;
        List<WeatherDetails> list = this.k;
        return hashCode10 + (list != null ? list.hashCode() : 0);
    }

    public final void setCityId(@Nullable Integer num) {
        this.f6216a = num;
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
        this.j = num;
    }

    public final void setCod(@Nullable String str) {
        this.h = str;
    }

    public final void setCountry(@Nullable String str) {
        this.e = str;
    }

    public final void setMessage(@Nullable Double d) {
        this.i = d;
    }

    public final void setPopulation(@Nullable Double d) {
        this.f = d;
    }

    public final void setTimezone(@Nullable Integer num) {
        this.g = num;
    }

    public final void setWeatherDetailList(@Nullable List<WeatherDetails> list) {
        this.k = list;
    }

    @NotNull
    public String toString() {
        return "WeatherForecastModel(cityId=" + this.f6216a + ", cityName=" + this.b + ", citylon=" + this.c + ", cityLat=" + this.d + ", country=" + this.e + ", population=" + this.f + ", timezone=" + this.g + ", cod=" + this.h + ", message=" + this.i + ", cnt=" + this.j + ", weatherDetailList=" + this.k + HexStringBuilder.COMMENT_END_CHAR;
    }
}
