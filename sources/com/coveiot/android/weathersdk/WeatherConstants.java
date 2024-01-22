package com.coveiot.android.weathersdk;

import com.coveiot.coveaccess.constants.ErrorConstants;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b#\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b#\u0010$R\u001c\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u001c\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u001c\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u001c\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\u0006R\u001c\u0010\u000f\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u000f\u0010\u0004\u001a\u0004\b\u0010\u0010\u0006R\u001c\u0010\u0011\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006R\u001c\u0010\u0013\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u0013\u0010\u0004\u001a\u0004\b\u0014\u0010\u0006R\u001c\u0010\u0015\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u0015\u0010\u0004\u001a\u0004\b\u0016\u0010\u0006R\u001c\u0010\u0017\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0018\u0010\u0006R\u001c\u0010\u0019\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006R\u001c\u0010\u001b\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u0006R\u001c\u0010\u001d\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u001d\u0010\u0004\u001a\u0004\b\u001e\u0010\u0006R\u001c\u0010\u001f\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b\u001f\u0010\u0004\u001a\u0004\b \u0010\u0006R\u001c\u0010!\u001a\u00020\u00028\u0006@\u0006X\u0086D¢\u0006\f\n\u0004\b!\u0010\u0004\u001a\u0004\b\"\u0010\u0006¨\u0006%"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherConstants;", "", "", "X_RapidAPI_Key", "Ljava/lang/String;", "getX_RapidAPI_Key", "()Ljava/lang/String;", "SETUP_ERR_NOT_INIT", "getSETUP_ERR_NOT_INIT", "SETUP_ERR_WEATHER_APICALLS_MANAGER_NOT_INIT", "getSETUP_ERR_WEATHER_APICALLS_MANAGER_NOT_INIT", "UNIT_METRIC", "getUNIT_METRIC", "UNIT_IMPERIAL", "getUNIT_IMPERIAL", "DAYS_COUNT", "getDAYS_COUNT", "WEATHER_FORECAST", "getWEATHER_FORECAST", "CURRENT_WEATHER", "getCURRENT_WEATHER", "WEATHER_UNIT", "getWEATHER_UNIT", "WEATHER_INTERVAL", "getWEATHER_INTERVAL", "WEATHER_FORECAST_HOURLY", "getWEATHER_FORECAST_HOURLY", "OPEN_WEATHER_MAP_BASE_URL", "getOPEN_WEATHER_MAP_BASE_URL", "OPEN_WEATHER_MAP_CURRENT_BASE_URL", "getOPEN_WEATHER_MAP_CURRENT_BASE_URL", "OPEN_WEATHER_MAP_DAILY_BASE_URL", "getOPEN_WEATHER_MAP_DAILY_BASE_URL", "OPEN_WEATHER_MAP_HOURLY_BASE_URL", "getOPEN_WEATHER_MAP_HOURLY_BASE_URL", "<init>", "()V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherConstants {
    public static final WeatherConstants INSTANCE = new WeatherConstants();

    @NotNull
    public final String getCURRENT_WEATHER() {
        return "current_weather";
    }

    @NotNull
    public final String getDAYS_COUNT() {
        return "days_count";
    }

    @NotNull
    public final String getOPEN_WEATHER_MAP_BASE_URL() {
        return "https://api.openweathermap.org/data/2.5/";
    }

    @NotNull
    public final String getOPEN_WEATHER_MAP_CURRENT_BASE_URL() {
        return "https://api.openweathermap.org/data/2.5/weather";
    }

    @NotNull
    public final String getOPEN_WEATHER_MAP_DAILY_BASE_URL() {
        return "https://api.openweathermap.org/data/2.5/forecast/daily";
    }

    @NotNull
    public final String getOPEN_WEATHER_MAP_HOURLY_BASE_URL() {
        return "https://pro.openweathermap.org/data/2.5/forecast/hourly";
    }

    @NotNull
    public final String getSETUP_ERR_NOT_INIT() {
        return ErrorConstants.SETUP_ERR_NOT_INIT;
    }

    @NotNull
    public final String getSETUP_ERR_WEATHER_APICALLS_MANAGER_NOT_INIT() {
        return "Initialize the WeatherApiCallsManager from your application class before use.";
    }

    @NotNull
    public final String getUNIT_IMPERIAL() {
        return DirectionsCriteria.IMPERIAL;
    }

    @NotNull
    public final String getUNIT_METRIC() {
        return DirectionsCriteria.METRIC;
    }

    @NotNull
    public final String getWEATHER_FORECAST() {
        return "weather_forecast";
    }

    @NotNull
    public final String getWEATHER_FORECAST_HOURLY() {
        return "weather_forecast_hourly";
    }

    @NotNull
    public final String getWEATHER_INTERVAL() {
        return "weather_interval";
    }

    @NotNull
    public final String getWEATHER_UNIT() {
        return "weather_unit";
    }

    @NotNull
    public final String getX_RapidAPI_Key() {
        return "X-RapidAPI-Key";
    }
}
