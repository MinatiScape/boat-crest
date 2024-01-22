package com.coveiot.android.weather.response.forecastmodel;

import com.coveiot.android.weathersdk.WeatherConditionType;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherDesc implements Serializable {
    @Nullable
    private String description;
    @Nullable
    private String icon;
    @Nullable
    private WeatherConditionType.WeatherConditionEnum iconType;
    @Nullable
    private Integer id;
    @Nullable
    private String main;

    public WeatherDesc() {
        this(null, null, null, null, null, 31, null);
    }

    public WeatherDesc(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable WeatherConditionType.WeatherConditionEnum weatherConditionEnum) {
        this.id = num;
        this.main = str;
        this.description = str2;
        this.icon = str3;
        this.iconType = weatherConditionEnum;
    }

    public static /* synthetic */ WeatherDesc copy$default(WeatherDesc weatherDesc, Integer num, String str, String str2, String str3, WeatherConditionType.WeatherConditionEnum weatherConditionEnum, int i, Object obj) {
        if ((i & 1) != 0) {
            num = weatherDesc.id;
        }
        if ((i & 2) != 0) {
            str = weatherDesc.main;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = weatherDesc.description;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = weatherDesc.icon;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            weatherConditionEnum = weatherDesc.iconType;
        }
        return weatherDesc.copy(num, str4, str5, str6, weatherConditionEnum);
    }

    @Nullable
    public final Integer component1() {
        return this.id;
    }

    @Nullable
    public final String component2() {
        return this.main;
    }

    @Nullable
    public final String component3() {
        return this.description;
    }

    @Nullable
    public final String component4() {
        return this.icon;
    }

    @Nullable
    public final WeatherConditionType.WeatherConditionEnum component5() {
        return this.iconType;
    }

    @NotNull
    public final WeatherDesc copy(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable WeatherConditionType.WeatherConditionEnum weatherConditionEnum) {
        return new WeatherDesc(num, str, str2, str3, weatherConditionEnum);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeatherDesc) {
            WeatherDesc weatherDesc = (WeatherDesc) obj;
            return Intrinsics.areEqual(this.id, weatherDesc.id) && Intrinsics.areEqual(this.main, weatherDesc.main) && Intrinsics.areEqual(this.description, weatherDesc.description) && Intrinsics.areEqual(this.icon, weatherDesc.icon) && this.iconType == weatherDesc.iconType;
        }
        return false;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final String getIcon() {
        return this.icon;
    }

    @Nullable
    public final WeatherConditionType.WeatherConditionEnum getIconType() {
        return this.iconType;
    }

    @Nullable
    public final Integer getId() {
        return this.id;
    }

    @Nullable
    public final String getMain() {
        return this.main;
    }

    public int hashCode() {
        Integer num = this.id;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.main;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.description;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.icon;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        WeatherConditionType.WeatherConditionEnum weatherConditionEnum = this.iconType;
        return hashCode4 + (weatherConditionEnum != null ? weatherConditionEnum.hashCode() : 0);
    }

    public final void setDescription(@Nullable String str) {
        this.description = str;
    }

    public final void setIcon(@Nullable String str) {
        this.icon = str;
    }

    public final void setIconType(@Nullable WeatherConditionType.WeatherConditionEnum weatherConditionEnum) {
        this.iconType = weatherConditionEnum;
    }

    public final void setId(@Nullable Integer num) {
        this.id = num;
    }

    public final void setMain(@Nullable String str) {
        this.main = str;
    }

    @NotNull
    public String toString() {
        return "WeatherDesc(id=" + this.id + ", main=" + this.main + ", description=" + this.description + ", icon=" + this.icon + ", iconType=" + this.iconType + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WeatherDesc(Integer num, String str, String str2, String str3, WeatherConditionType.WeatherConditionEnum weatherConditionEnum, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : weatherConditionEnum);
    }
}
