package com.mappls.sdk.services.api.weather;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.weather.model.WeatherResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes8.dex */
public class MapplsWeatherManager {
    private final MapplsWeather mapplsWeatherCondition;

    /* loaded from: classes8.dex */
    public class a implements Callback<WeatherResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnResponseCallback f13300a;

        public a(MapplsWeatherManager mapplsWeatherManager, OnResponseCallback onResponseCallback) {
            this.f13300a = onResponseCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<WeatherResponse> call, Throwable th) {
            if (call.isCanceled()) {
                this.f13300a.onError(0, th.getMessage());
            } else if (th instanceof UnknownHostException) {
                this.f13300a.onError(1, th.getMessage());
            } else {
                this.f13300a.onError(2, th.getMessage());
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
            if (response.code() == 200) {
                this.f13300a.onSuccess(response.body());
            } else if (response.headers().get(Constants.KEY_MESSAGE) != null) {
                this.f13300a.onError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
            } else if (response.errorBody() != null) {
                try {
                    Map map = (Map) new Gson().fromJson(response.errorBody().string(), (Class<Object>) Map.class);
                    if (map != null && map.containsKey("error")) {
                        this.f13300a.onError(response.code(), (String) map.get("error"));
                    } else {
                        this.f13300a.onError(response.code(), response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f13300a.onError(response.code(), response.message());
                }
            } else {
                this.f13300a.onError(response.code(), response.message());
            }
        }
    }

    private MapplsWeatherManager(MapplsWeather mapplsWeather) {
        this.mapplsWeatherCondition = mapplsWeather;
    }

    public static MapplsWeatherManager newInstance(MapplsWeather mapplsWeather) {
        return new MapplsWeatherManager(mapplsWeather);
    }

    public void call(OnResponseCallback<WeatherResponse> onResponseCallback) {
        this.mapplsWeatherCondition.enqueue(new a(this, onResponseCallback));
    }

    public void cancel() {
        this.mapplsWeatherCondition.cancel();
    }

    public WeatherResponse execute() throws IOException {
        return this.mapplsWeatherCondition.execute().body();
    }

    public boolean isExecuted() {
        return this.mapplsWeatherCondition.executed();
    }
}
