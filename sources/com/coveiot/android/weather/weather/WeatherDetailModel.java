package com.coveiot.android.weather.weather;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherDetailModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f6175a;
    public double b;
    public double c;
    @NotNull
    public String d;
    @NotNull
    public String e;

    public WeatherDetailModel(@NotNull String iconUrl, double d, double d2, @NotNull String weatherStatus, @NotNull String day) {
        Intrinsics.checkNotNullParameter(iconUrl, "iconUrl");
        Intrinsics.checkNotNullParameter(weatherStatus, "weatherStatus");
        Intrinsics.checkNotNullParameter(day, "day");
        this.f6175a = iconUrl;
        this.b = d;
        this.c = d2;
        this.d = weatherStatus;
        this.e = day;
    }

    public static /* synthetic */ WeatherDetailModel copy$default(WeatherDetailModel weatherDetailModel, String str, double d, double d2, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = weatherDetailModel.f6175a;
        }
        if ((i & 2) != 0) {
            d = weatherDetailModel.b;
        }
        double d3 = d;
        if ((i & 4) != 0) {
            d2 = weatherDetailModel.c;
        }
        double d4 = d2;
        if ((i & 8) != 0) {
            str2 = weatherDetailModel.d;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            str3 = weatherDetailModel.e;
        }
        return weatherDetailModel.copy(str, d3, d4, str4, str3);
    }

    @NotNull
    public final String component1() {
        return this.f6175a;
    }

    public final double component2() {
        return this.b;
    }

    public final double component3() {
        return this.c;
    }

    @NotNull
    public final String component4() {
        return this.d;
    }

    @NotNull
    public final String component5() {
        return this.e;
    }

    @NotNull
    public final WeatherDetailModel copy(@NotNull String iconUrl, double d, double d2, @NotNull String weatherStatus, @NotNull String day) {
        Intrinsics.checkNotNullParameter(iconUrl, "iconUrl");
        Intrinsics.checkNotNullParameter(weatherStatus, "weatherStatus");
        Intrinsics.checkNotNullParameter(day, "day");
        return new WeatherDetailModel(iconUrl, d, d2, weatherStatus, day);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeatherDetailModel) {
            WeatherDetailModel weatherDetailModel = (WeatherDetailModel) obj;
            return Intrinsics.areEqual(this.f6175a, weatherDetailModel.f6175a) && Double.compare(this.b, weatherDetailModel.b) == 0 && Double.compare(this.c, weatherDetailModel.c) == 0 && Intrinsics.areEqual(this.d, weatherDetailModel.d) && Intrinsics.areEqual(this.e, weatherDetailModel.e);
        }
        return false;
    }

    @NotNull
    public final String getDay() {
        return this.e;
    }

    @NotNull
    public final String getIconUrl() {
        return this.f6175a;
    }

    public final double getMaxTemp() {
        return this.c;
    }

    public final double getMinTemp() {
        return this.b;
    }

    @NotNull
    public final String getWeatherStatus() {
        return this.d;
    }

    public int hashCode() {
        return (((((((this.f6175a.hashCode() * 31) + Double.hashCode(this.b)) * 31) + Double.hashCode(this.c)) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }

    public final void setDay(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.e = str;
    }

    public final void setIconUrl(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f6175a = str;
    }

    public final void setMaxTemp(double d) {
        this.c = d;
    }

    public final void setMinTemp(double d) {
        this.b = d;
    }

    public final void setWeatherStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    @NotNull
    public String toString() {
        return "WeatherDetailModel(iconUrl=" + this.f6175a + ", minTemp=" + this.b + ", maxTemp=" + this.c + ", weatherStatus=" + this.d + ", day=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }
}
