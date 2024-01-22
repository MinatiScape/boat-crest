package com.coveiot.android.weathersdk;

import com.mappls.sdk.services.api.directions.DirectionsCriteria;
/* loaded from: classes8.dex */
public enum WeatherUnit {
    METRIC(DirectionsCriteria.METRIC),
    IMPERIAL(DirectionsCriteria.IMPERIAL);
    

    /* renamed from: a  reason: collision with root package name */
    public String f6206a;

    WeatherUnit(String str) {
        this.f6206a = str;
    }

    public String weatherUnit() {
        return this.f6206a;
    }
}
