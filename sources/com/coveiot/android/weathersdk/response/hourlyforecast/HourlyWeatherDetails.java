package com.coveiot.android.weathersdk.response.hourlyforecast;

import com.clevertap.android.sdk.Constants;
import com.coveiot.android.weathersdk.response.forecastmodel.Weather;
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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b6\b\u0086\b\u0018\u00002\u00020\u0001B©\u0001\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017¢\u0006\u0004\bb\u0010cJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\u0004J\u0012\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000eJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0010\u0010\u000eJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0011\u0010\u000eJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0012\u0010\u000eJ\u0012\u0010\u0013\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0013\u0010\u000eJ\u0012\u0010\u0014\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0014\u0010\u000eJ\u0012\u0010\u0015\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0015\u0010\u000eJ\u0012\u0010\u0016\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u0016\u0010\u000eJ\u0018\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ²\u0001\u0010(\u001a\u00020\u00002\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\f2\u0010\b\u0002\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017HÆ\u0001¢\u0006\u0004\b(\u0010)J\u0010\u0010+\u001a\u00020*HÖ\u0001¢\u0006\u0004\b+\u0010,J\u001a\u0010/\u001a\u00020.2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b/\u00100R$\u0010\u001b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u0010\u0007\"\u0004\b4\u00105R$\u0010\u001c\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u0010\n\"\u0004\b9\u0010:R$\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010\u0004\"\u0004\b>\u0010?R$\u0010\u001e\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010\u000e\"\u0004\bC\u0010DR$\u0010\u001f\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u0010A\u001a\u0004\bF\u0010\u000e\"\u0004\bG\u0010DR$\u0010 \u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bH\u0010A\u001a\u0004\bI\u0010\u000e\"\u0004\bJ\u0010DR$\u0010!\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u0010A\u001a\u0004\bL\u0010\u000e\"\u0004\bM\u0010DR$\u0010\"\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u0010A\u001a\u0004\bO\u0010\u000e\"\u0004\bP\u0010DR$\u0010#\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bQ\u0010A\u001a\u0004\bR\u0010\u000e\"\u0004\bS\u0010DR$\u0010$\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bT\u0010A\u001a\u0004\bU\u0010\u000e\"\u0004\bV\u0010DR$\u0010%\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bW\u0010A\u001a\u0004\bX\u0010\u000e\"\u0004\bY\u0010DR$\u0010&\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bZ\u0010A\u001a\u0004\b[\u0010\u000e\"\u0004\b\\\u0010DR*\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b]\u0010^\u001a\u0004\b_\u0010\u001a\"\u0004\b`\u0010a¨\u0006d"}, d2 = {"Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherDetails;", "", "", "toString", "()Ljava/lang/String;", "", "component1", "()Ljava/lang/Long;", "Ljava/util/Date;", "component2", "()Ljava/util/Date;", "component3", "", "component4", "()Ljava/lang/Double;", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "", "Lcom/coveiot/android/weathersdk/response/forecastmodel/Weather;", "component13", "()Ljava/util/List;", "DateTimeSatmp", "date", "dateTimeTxt", "temp", "minTemp", "maxTemp", "pressure", "humidity", "speed", "deg", "gust", "pop", "weather", Constants.COPY_TYPE, "(Ljava/lang/Long;Ljava/util/Date;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherDetails;", "", "hashCode", "()I", FitnessActivities.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/Long;", "getDateTimeSatmp", "setDateTimeSatmp", "(Ljava/lang/Long;)V", "b", "Ljava/util/Date;", "getDate", "setDate", "(Ljava/util/Date;)V", c.f10260a, "Ljava/lang/String;", "getDateTimeTxt", "setDateTimeTxt", "(Ljava/lang/String;)V", "d", "Ljava/lang/Double;", "getTemp", "setTemp", "(Ljava/lang/Double;)V", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getMinTemp", "setMinTemp", "f", "getMaxTemp", "setMaxTemp", "g", "getPressure", "setPressure", "h", "getHumidity", "setHumidity", "i", "getSpeed", "setSpeed", "j", "getDeg", "setDeg", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getGust", "setGust", "l", "getPop", "setPop", "m", "Ljava/util/List;", "getWeather", "setWeather", "(Ljava/util/List;)V", "<init>", "(Ljava/lang/Long;Ljava/util/Date;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class HourlyWeatherDetails {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Long f6217a;
    @Nullable
    public Date b;
    @Nullable
    public String c;
    @Nullable
    public Double d;
    @Nullable
    public Double e;
    @Nullable
    public Double f;
    @Nullable
    public Double g;
    @Nullable
    public Double h;
    @Nullable
    public Double i;
    @Nullable
    public Double j;
    @Nullable
    public Double k;
    @Nullable
    public Double l;
    @Nullable
    public List<Weather> m;

    public HourlyWeatherDetails() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public HourlyWeatherDetails(@Nullable Long l, @Nullable Date date, @Nullable String str, @Nullable Double d, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Double d9, @Nullable List<Weather> list) {
        this.f6217a = l;
        this.b = date;
        this.c = str;
        this.d = d;
        this.e = d2;
        this.f = d3;
        this.g = d4;
        this.h = d5;
        this.i = d6;
        this.j = d7;
        this.k = d8;
        this.l = d9;
        this.m = list;
    }

    public /* synthetic */ HourlyWeatherDetails(Long l, Date date, String str, Double d, Double d2, Double d3, Double d4, Double d5, Double d6, Double d7, Double d8, Double d9, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : date, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : d, (i & 16) != 0 ? null : d2, (i & 32) != 0 ? null : d3, (i & 64) != 0 ? null : d4, (i & 128) != 0 ? null : d5, (i & 256) != 0 ? null : d6, (i & 512) != 0 ? null : d7, (i & 1024) != 0 ? null : d8, (i & 2048) != 0 ? null : d9, (i & 4096) == 0 ? list : null);
    }

    @Nullable
    public final Long component1() {
        return this.f6217a;
    }

    @Nullable
    public final Double component10() {
        return this.j;
    }

    @Nullable
    public final Double component11() {
        return this.k;
    }

    @Nullable
    public final Double component12() {
        return this.l;
    }

    @Nullable
    public final List<Weather> component13() {
        return this.m;
    }

    @Nullable
    public final Date component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @Nullable
    public final Double component4() {
        return this.d;
    }

    @Nullable
    public final Double component5() {
        return this.e;
    }

    @Nullable
    public final Double component6() {
        return this.f;
    }

    @Nullable
    public final Double component7() {
        return this.g;
    }

    @Nullable
    public final Double component8() {
        return this.h;
    }

    @Nullable
    public final Double component9() {
        return this.i;
    }

    @NotNull
    public final HourlyWeatherDetails copy(@Nullable Long l, @Nullable Date date, @Nullable String str, @Nullable Double d, @Nullable Double d2, @Nullable Double d3, @Nullable Double d4, @Nullable Double d5, @Nullable Double d6, @Nullable Double d7, @Nullable Double d8, @Nullable Double d9, @Nullable List<Weather> list) {
        return new HourlyWeatherDetails(l, date, str, d, d2, d3, d4, d5, d6, d7, d8, d9, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof HourlyWeatherDetails) {
                HourlyWeatherDetails hourlyWeatherDetails = (HourlyWeatherDetails) obj;
                return Intrinsics.areEqual(this.f6217a, hourlyWeatherDetails.f6217a) && Intrinsics.areEqual(this.b, hourlyWeatherDetails.b) && Intrinsics.areEqual(this.c, hourlyWeatherDetails.c) && Intrinsics.areEqual((Object) this.d, (Object) hourlyWeatherDetails.d) && Intrinsics.areEqual((Object) this.e, (Object) hourlyWeatherDetails.e) && Intrinsics.areEqual((Object) this.f, (Object) hourlyWeatherDetails.f) && Intrinsics.areEqual((Object) this.g, (Object) hourlyWeatherDetails.g) && Intrinsics.areEqual((Object) this.h, (Object) hourlyWeatherDetails.h) && Intrinsics.areEqual((Object) this.i, (Object) hourlyWeatherDetails.i) && Intrinsics.areEqual((Object) this.j, (Object) hourlyWeatherDetails.j) && Intrinsics.areEqual((Object) this.k, (Object) hourlyWeatherDetails.k) && Intrinsics.areEqual((Object) this.l, (Object) hourlyWeatherDetails.l) && Intrinsics.areEqual(this.m, hourlyWeatherDetails.m);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final Date getDate() {
        return this.b;
    }

    @Nullable
    public final Long getDateTimeSatmp() {
        return this.f6217a;
    }

    @Nullable
    public final String getDateTimeTxt() {
        return this.c;
    }

    @Nullable
    public final Double getDeg() {
        return this.j;
    }

    @Nullable
    public final Double getGust() {
        return this.k;
    }

    @Nullable
    public final Double getHumidity() {
        return this.h;
    }

    @Nullable
    public final Double getMaxTemp() {
        return this.f;
    }

    @Nullable
    public final Double getMinTemp() {
        return this.e;
    }

    @Nullable
    public final Double getPop() {
        return this.l;
    }

    @Nullable
    public final Double getPressure() {
        return this.g;
    }

    @Nullable
    public final Double getSpeed() {
        return this.i;
    }

    @Nullable
    public final Double getTemp() {
        return this.d;
    }

    @Nullable
    public final List<Weather> getWeather() {
        return this.m;
    }

    public int hashCode() {
        Long l = this.f6217a;
        int hashCode = (l != null ? l.hashCode() : 0) * 31;
        Date date = this.b;
        int hashCode2 = (hashCode + (date != null ? date.hashCode() : 0)) * 31;
        String str = this.c;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        Double d = this.d;
        int hashCode4 = (hashCode3 + (d != null ? d.hashCode() : 0)) * 31;
        Double d2 = this.e;
        int hashCode5 = (hashCode4 + (d2 != null ? d2.hashCode() : 0)) * 31;
        Double d3 = this.f;
        int hashCode6 = (hashCode5 + (d3 != null ? d3.hashCode() : 0)) * 31;
        Double d4 = this.g;
        int hashCode7 = (hashCode6 + (d4 != null ? d4.hashCode() : 0)) * 31;
        Double d5 = this.h;
        int hashCode8 = (hashCode7 + (d5 != null ? d5.hashCode() : 0)) * 31;
        Double d6 = this.i;
        int hashCode9 = (hashCode8 + (d6 != null ? d6.hashCode() : 0)) * 31;
        Double d7 = this.j;
        int hashCode10 = (hashCode9 + (d7 != null ? d7.hashCode() : 0)) * 31;
        Double d8 = this.k;
        int hashCode11 = (hashCode10 + (d8 != null ? d8.hashCode() : 0)) * 31;
        Double d9 = this.l;
        int hashCode12 = (hashCode11 + (d9 != null ? d9.hashCode() : 0)) * 31;
        List<Weather> list = this.m;
        return hashCode12 + (list != null ? list.hashCode() : 0);
    }

    public final void setDate(@Nullable Date date) {
        this.b = date;
    }

    public final void setDateTimeSatmp(@Nullable Long l) {
        this.f6217a = l;
    }

    public final void setDateTimeTxt(@Nullable String str) {
        this.c = str;
    }

    public final void setDeg(@Nullable Double d) {
        this.j = d;
    }

    public final void setGust(@Nullable Double d) {
        this.k = d;
    }

    public final void setHumidity(@Nullable Double d) {
        this.h = d;
    }

    public final void setMaxTemp(@Nullable Double d) {
        this.f = d;
    }

    public final void setMinTemp(@Nullable Double d) {
        this.e = d;
    }

    public final void setPop(@Nullable Double d) {
        this.l = d;
    }

    public final void setPressure(@Nullable Double d) {
        this.g = d;
    }

    public final void setSpeed(@Nullable Double d) {
        this.i = d;
    }

    public final void setTemp(@Nullable Double d) {
        this.d = d;
    }

    public final void setWeather(@Nullable List<Weather> list) {
        this.m = list;
    }

    @NotNull
    public String toString() {
        return "HourlyWeatherDetails(DateTimeSatmp=" + this.f6217a + ", date=" + this.b + ", dateTimeTxt=" + this.c + ", temp=" + this.d + ", minTemp=" + this.e + ", maxTemp=" + this.f + ", pressure=" + this.g + ", humidity=" + this.h + ", speed=" + this.i + ", deg=" + this.j + ", gust=" + this.k + ", pop=" + this.l + ", weather=" + this.m + HexStringBuilder.COMMENT_END_CHAR;
    }
}
