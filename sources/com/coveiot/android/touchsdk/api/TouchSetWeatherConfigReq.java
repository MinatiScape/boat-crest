package com.coveiot.android.touchsdk.api;

import com.touchgui.sdk.bean.TGWeather;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class TouchSetWeatherConfigReq extends TouchELXBaseReq {
    @NotNull
    public TGWeather e;
    @NotNull
    public String f;

    public TouchSetWeatherConfigReq(@NotNull TGWeather weather, @NotNull String cityName) {
        Intrinsics.checkNotNullParameter(weather, "weather");
        Intrinsics.checkNotNullParameter(cityName, "cityName");
        this.e = weather;
        this.f = cityName;
    }

    @NotNull
    public final String getCityName() {
        return this.f;
    }

    @NotNull
    public final TGWeather getWeather() {
        return this.e;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setCityName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setWeather(@NotNull TGWeather tGWeather) {
        Intrinsics.checkNotNullParameter(tGWeather, "<set-?>");
        this.e = tGWeather;
    }
}
