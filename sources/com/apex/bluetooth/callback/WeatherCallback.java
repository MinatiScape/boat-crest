package com.apex.bluetooth.callback;

import com.apex.bluetooth.model.EABleWeather;
/* loaded from: classes.dex */
public interface WeatherCallback extends EABleCallback {
    void weatherInfo(EABleWeather eABleWeather);
}
