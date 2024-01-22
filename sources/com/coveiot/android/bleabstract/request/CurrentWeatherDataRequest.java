package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.WeatherData;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CurrentWeatherDataRequest extends BleBaseRequest {
    @NotNull
    public final WeatherData f;

    public CurrentWeatherDataRequest(@NotNull WeatherData currentWeather) {
        Intrinsics.checkNotNullParameter(currentWeather, "currentWeather");
        this.f = currentWeather;
    }

    @NotNull
    public final WeatherData getCurrentWeather() {
        return this.f;
    }
}
