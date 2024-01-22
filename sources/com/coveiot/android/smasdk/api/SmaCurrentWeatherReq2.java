package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleWeatherForecast2;
import com.szabh.smable3.entity.BleWeatherRealtime2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SmaCurrentWeatherReq2 extends SmaBaseReq {
    public int d;
    @NotNull
    public final BleWeatherRealtime2 e;
    @NotNull
    public final BleWeatherForecast2 f;

    public SmaCurrentWeatherReq2(int i, @NotNull BleWeatherRealtime2 weather, @NotNull BleWeatherForecast2 forecastWeather) {
        Intrinsics.checkNotNullParameter(weather, "weather");
        Intrinsics.checkNotNullParameter(forecastWeather, "forecastWeather");
        this.d = i;
        this.e = weather;
        this.f = forecastWeather;
    }

    @NotNull
    public final BleWeatherForecast2 getForecastWeather() {
        return this.f;
    }

    public final int getTemperatureUnitType() {
        return this.d;
    }

    @NotNull
    public final BleWeatherRealtime2 getWeather() {
        return this.e;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setTemperatureUnitType(int i) {
        this.d = i;
    }

    public /* synthetic */ SmaCurrentWeatherReq2(int i, BleWeatherRealtime2 bleWeatherRealtime2, BleWeatherForecast2 bleWeatherForecast2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, bleWeatherRealtime2, bleWeatherForecast2);
    }
}
