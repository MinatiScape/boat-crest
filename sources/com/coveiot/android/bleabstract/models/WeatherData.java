package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WeatherData {
    public static final int CLOUDY = 2;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int FOGGY = 9;
    public static final int HAZE = 11;
    public static final int HIGH_WINDY = 7;
    public static final int OTHER = 0;
    public static final int OVERCAST = 3;
    public static final int RAINY = 4;
    public static final int SAND_STORM = 10;
    public static final int SNOWY = 8;
    public static final int SUNNY = 1;
    public static final int THUNDER = 5;
    public static final int THUNDERSHOWER = 6;

    /* renamed from: a  reason: collision with root package name */
    public int f3458a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WeatherData() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 511, null);
    }

    public WeatherData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.f3458a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        this.h = i8;
        this.i = i9;
    }

    public final int component1() {
        return this.f3458a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    public final int component6() {
        return this.f;
    }

    public final int component7() {
        return this.g;
    }

    public final int component8() {
        return this.h;
    }

    public final int component9() {
        return this.i;
    }

    @NotNull
    public final WeatherData copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        return new WeatherData(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obj;
            return this.f3458a == weatherData.f3458a && this.b == weatherData.b && this.c == weatherData.c && this.d == weatherData.d && this.e == weatherData.e && this.f == weatherData.f && this.g == weatherData.g && this.h == weatherData.h && this.i == weatherData.i;
        }
        return false;
    }

    public final int getMCurrentTemperature() {
        return this.f3458a;
    }

    public final int getMHumidity() {
        return this.f;
    }

    public final int getMMaxTemperature() {
        return this.b;
    }

    public final int getMMinTemperature() {
        return this.c;
    }

    public final int getMPrecipitation() {
        return this.i;
    }

    public final int getMUltraVioletIntensity() {
        return this.h;
    }

    public final int getMVisibility() {
        return this.g;
    }

    public final int getMWeatherCode() {
        return this.d;
    }

    public final int getMWindSpeed() {
        return this.e;
    }

    public int hashCode() {
        return (((((((((((((((Integer.hashCode(this.f3458a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Integer.hashCode(this.g)) * 31) + Integer.hashCode(this.h)) * 31) + Integer.hashCode(this.i);
    }

    public final void setMCurrentTemperature(int i) {
        this.f3458a = i;
    }

    public final void setMHumidity(int i) {
        this.f = i;
    }

    public final void setMMaxTemperature(int i) {
        this.b = i;
    }

    public final void setMMinTemperature(int i) {
        this.c = i;
    }

    public final void setMPrecipitation(int i) {
        this.i = i;
    }

    public final void setMUltraVioletIntensity(int i) {
        this.h = i;
    }

    public final void setMVisibility(int i) {
        this.g = i;
    }

    public final void setMWeatherCode(int i) {
        this.d = i;
    }

    public final void setMWindSpeed(int i) {
        this.e = i;
    }

    @NotNull
    public String toString() {
        return "WeatherData(mCurrentTemperature=" + this.f3458a + ", mMaxTemperature=" + this.b + ", mMinTemperature=" + this.c + ", mWeatherCode=" + this.d + ", mWindSpeed=" + this.e + ", mHumidity=" + this.f + ", mVisibility=" + this.g + ", mUltraVioletIntensity=" + this.h + ", mPrecipitation=" + this.i + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WeatherData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? 0 : i, (i10 & 2) != 0 ? 0 : i2, (i10 & 4) != 0 ? 0 : i3, (i10 & 8) != 0 ? 0 : i4, (i10 & 16) != 0 ? 0 : i5, (i10 & 32) != 0 ? 0 : i6, (i10 & 64) != 0 ? 0 : i7, (i10 & 128) != 0 ? 0 : i8, (i10 & 256) == 0 ? i9 : 0);
    }
}
