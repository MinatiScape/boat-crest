package com.mappls.sdk.services.api.weather;

import com.mappls.sdk.services.api.weather.model.WeatherResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
/* loaded from: classes8.dex */
public interface WeatherService {
    @GET("apis/bridge/currentWeatherCondition")
    Call<WeatherResponse> getCall(@QueryMap Map<String, Object> map);
}
