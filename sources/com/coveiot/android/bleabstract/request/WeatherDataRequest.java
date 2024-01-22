package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.WeatherData;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WeatherDataRequest extends BleBaseRequest {
    @NotNull
    public final WeatherData f;
    @Nullable
    public final List<WeatherData> g;

    public WeatherDataRequest(@NotNull WeatherData currentWeather, @Nullable List<WeatherData> list) {
        Intrinsics.checkNotNullParameter(currentWeather, "currentWeather");
        this.f = currentWeather;
        this.g = list;
    }

    @NotNull
    public final WeatherData getCurrentWeather() {
        return this.f;
    }

    @Nullable
    public final List<WeatherData> getWeatherDataList() {
        return this.g;
    }

    public /* synthetic */ WeatherDataRequest(WeatherData weatherData, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(weatherData, (i & 2) != 0 ? null : list);
    }
}
