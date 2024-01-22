package com.coveiot.android.weathersdk.response.forecastmodel;

import com.coveiot.android.femalewellness.wellnesscalendar.Constants;
import com.coveiot.android.weathersdk.WeatherConditionType;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b0\u00101J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\b\u0010\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\t\u0010\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJL\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0007J\u0010\u0010\u0015\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u0004\"\u0004\b\u001e\u0010\u001fR$\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u0007\"\u0004\b#\u0010$R$\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010!\u001a\u0004\b&\u0010\u0007\"\u0004\b'\u0010$R$\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010!\u001a\u0004\b)\u0010\u0007\"\u0004\b*\u0010$R$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010\f\"\u0004\b.\u0010/¨\u00062"}, d2 = {"Lcom/coveiot/android/weathersdk/response/forecastmodel/Weather;", "", "", "component1", "()Ljava/lang/Integer;", "", "component2", "()Ljava/lang/String;", "component3", "component4", "Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;", "component5", "()Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;", "id", Constants.MAIN_TAG, SavingTrackHelper.POINT_COL_DESCRIPTION, com.clevertap.android.sdk.Constants.KEY_ICON, "iconType", com.clevertap.android.sdk.Constants.COPY_TYPE, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;)Lcom/coveiot/android/weathersdk/response/forecastmodel/Weather;", "toString", "hashCode", "()I", FitnessActivities.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/Integer;", "getId", "setId", "(Ljava/lang/Integer;)V", "b", "Ljava/lang/String;", "getMain", "setMain", "(Ljava/lang/String;)V", c.f10260a, "getDescription", "setDescription", "d", "getIcon", "setIcon", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;", "getIconType", "setIconType", "(Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;)V", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class Weather {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f6214a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public WeatherConditionType.WeatherConditionEnum e;

    public Weather() {
        this(null, null, null, null, null, 31, null);
    }

    public Weather(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable WeatherConditionType.WeatherConditionEnum weatherConditionEnum) {
        this.f6214a = num;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = weatherConditionEnum;
    }

    public /* synthetic */ Weather(Integer num, String str, String str2, String str3, WeatherConditionType.WeatherConditionEnum weatherConditionEnum, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : weatherConditionEnum);
    }

    public static /* synthetic */ Weather copy$default(Weather weather, Integer num, String str, String str2, String str3, WeatherConditionType.WeatherConditionEnum weatherConditionEnum, int i, Object obj) {
        if ((i & 1) != 0) {
            num = weather.f6214a;
        }
        if ((i & 2) != 0) {
            str = weather.b;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = weather.c;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = weather.d;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            weatherConditionEnum = weather.e;
        }
        return weather.copy(num, str4, str5, str6, weatherConditionEnum);
    }

    @Nullable
    public final Integer component1() {
        return this.f6214a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @Nullable
    public final WeatherConditionType.WeatherConditionEnum component5() {
        return this.e;
    }

    @NotNull
    public final Weather copy(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable WeatherConditionType.WeatherConditionEnum weatherConditionEnum) {
        return new Weather(num, str, str2, str3, weatherConditionEnum);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Weather) {
                Weather weather = (Weather) obj;
                return Intrinsics.areEqual(this.f6214a, weather.f6214a) && Intrinsics.areEqual(this.b, weather.b) && Intrinsics.areEqual(this.c, weather.c) && Intrinsics.areEqual(this.d, weather.d) && Intrinsics.areEqual(this.e, weather.e);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getDescription() {
        return this.c;
    }

    @Nullable
    public final String getIcon() {
        return this.d;
    }

    @Nullable
    public final WeatherConditionType.WeatherConditionEnum getIconType() {
        return this.e;
    }

    @Nullable
    public final Integer getId() {
        return this.f6214a;
    }

    @Nullable
    public final String getMain() {
        return this.b;
    }

    public int hashCode() {
        Integer num = this.f6214a;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.c;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.d;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        WeatherConditionType.WeatherConditionEnum weatherConditionEnum = this.e;
        return hashCode4 + (weatherConditionEnum != null ? weatherConditionEnum.hashCode() : 0);
    }

    public final void setDescription(@Nullable String str) {
        this.c = str;
    }

    public final void setIcon(@Nullable String str) {
        this.d = str;
    }

    public final void setIconType(@Nullable WeatherConditionType.WeatherConditionEnum weatherConditionEnum) {
        this.e = weatherConditionEnum;
    }

    public final void setId(@Nullable Integer num) {
        this.f6214a = num;
    }

    public final void setMain(@Nullable String str) {
        this.b = str;
    }

    @NotNull
    public String toString() {
        return "Weather(id=" + this.f6214a + ", main=" + this.b + ", description=" + this.c + ", icon=" + this.d + ", iconType=" + this.e + ")";
    }
}
