package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleWeatherForecast;
import com.szabh.smable3.entity.BleWeatherRealtime;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SmaCurrentWeatherReq extends SmaBaseReq {
    public int d;
    @NotNull
    public final BleWeatherRealtime e;
    @NotNull
    public final BleWeatherForecast f;

    public /* synthetic */ SmaCurrentWeatherReq(int i, BleWeatherRealtime bleWeatherRealtime, BleWeatherForecast bleWeatherForecast, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, bleWeatherRealtime, bleWeatherForecast);
    }

    @NotNull
    public final BleWeatherForecast getForecastWeather() {
        return this.f;
    }

    public final int getTemperatureUnitType() {
        return this.d;
    }

    @NotNull
    public final BleWeatherRealtime getWeather() {
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

    public SmaCurrentWeatherReq(int i, @NotNull BleWeatherRealtime weather, @NotNull BleWeatherForecast forecastWeather) {
        Intrinsics.checkNotNullParameter(weather, "weather");
        Intrinsics.checkNotNullParameter(forecastWeather, "forecastWeather");
        this.d = i;
        this.e = weather;
        this.f = forecastWeather;
    }
}
