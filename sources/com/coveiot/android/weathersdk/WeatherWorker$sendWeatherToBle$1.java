package com.coveiot.android.weathersdk;

import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"com/coveiot/android/weathersdk/WeatherWorker$sendWeatherToBle$1", "Lcom/coveiot/android/bleabstract/listeners/SettingsResultListener;", "Lcom/coveiot/android/bleabstract/response/BleBaseResponse;", "response", "", "onSettingsResponse", "(Lcom/coveiot/android/bleabstract/response/BleBaseResponse;)V", "Lcom/coveiot/android/bleabstract/response/BleBaseError;", "error", "onSettingsError", "(Lcom/coveiot/android/bleabstract/response/BleBaseError;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherWorker$sendWeatherToBle$1 implements SettingsResultListener {
    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsError(@NotNull BleBaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsResponse(@NotNull BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
    }
}
