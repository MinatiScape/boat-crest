package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.weather.WeatherForecast;
import com.htsmart.wristband2.bean.weather.WeatherToday;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixWeatherReq extends MatrixBaseReq {
    @NotNull
    public final String e;
    @NotNull
    public final WeatherToday f;
    @NotNull
    public final List<WeatherForecast> g;

    /* JADX WARN: Multi-variable type inference failed */
    public MatrixWeatherReq(@NotNull String place, @NotNull WeatherToday weatherToday, @NotNull List<? extends WeatherForecast> weatherForecast) {
        Intrinsics.checkNotNullParameter(place, "place");
        Intrinsics.checkNotNullParameter(weatherToday, "weatherToday");
        Intrinsics.checkNotNullParameter(weatherForecast, "weatherForecast");
        this.e = place;
        this.f = weatherToday;
        this.g = weatherForecast;
    }

    @NotNull
    public final String getPlace() {
        return this.e;
    }

    @NotNull
    public final List<WeatherForecast> getWeatherForecast() {
        return this.g;
    }

    @NotNull
    public final WeatherToday getWeatherToday() {
        return this.f;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return true;
    }
}
