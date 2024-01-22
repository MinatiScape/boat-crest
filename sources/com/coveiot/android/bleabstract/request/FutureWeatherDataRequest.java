package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.WeatherData;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FutureWeatherDataRequest extends BleBaseRequest {
    @Nullable
    public final List<WeatherData> f;

    public FutureWeatherDataRequest() {
        this(null, 1, null);
    }

    public FutureWeatherDataRequest(@Nullable List<WeatherData> list) {
        this.f = list;
    }

    @Nullable
    public final List<WeatherData> getWeatherDataList() {
        return this.f;
    }

    public /* synthetic */ FutureWeatherDataRequest(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }
}
