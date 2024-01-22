package com.coveiot.android.weathersdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentForecastModel;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherForecastModel;
import com.coveiot.android.weathersdk.response.hourlyforecast.HourlyWeatherForecastModel;
import com.google.android.material.color.c;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 H2\u00020\u0001:\u0001HB\u0011\b\u0002\u0012\u0006\u0010D\u001a\u00020=¢\u0006\u0004\bG\u0010CJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0013¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0019¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0019¢\u0006\u0004\b \u0010\u001cJ\r\u0010!\u001a\u00020\u0019¢\u0006\u0004\b!\u0010\u001eJ\u0015\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\"¢\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010(¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u0004\u0018\u00010(¢\u0006\u0004\b+\u0010,R\"\u00104\u001a\u00020-8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\"\u0010<\u001a\u0002058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\"\u0010D\u001a\u00020=8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u0015\u0010F\u001a\u0004\u0018\u00010\u00138F@\u0006¢\u0006\u0006\u001a\u0004\bE\u0010\u0018¨\u0006I"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherPreferenceManager;", "", "Landroid/location/Location;", FirebaseAnalytics.Param.LOCATION, "", "saveLastLocationLatLng", "(Landroid/location/Location;)V", "Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherForecastModel;", "weatherForecastModel", "saveWeatherForecastModel", "(Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherForecastModel;)V", "getWeatherForecastModel", "()Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherForecastModel;", "Lcom/coveiot/android/weathersdk/response/currentweathermodel/CurrentForecastModel;", "currentWeather", "saveCurrentWeatherModel", "(Lcom/coveiot/android/weathersdk/response/currentweathermodel/CurrentForecastModel;)V", "getCurrentWeatherModel", "()Lcom/coveiot/android/weathersdk/response/currentweathermodel/CurrentForecastModel;", "", "weatherUnit", "saveWeatherUnit", "(Ljava/lang/String;)V", "getWeatherUnit", "()Ljava/lang/String;", "", "weatherInterval", "saveWeatherInterval", "(I)V", "getWeatherInterval", "()I", "weatherDays", "saveWeatherInDays", "getWeatherInDays", "", "isEnabled", "saveWeatherFeatureEnabled", "(Z)V", "isWeatherEnabled", "()Z", "Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherForecastModel;", "saveHourlyWeatherForecastModel", "(Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherForecastModel;)V", "getHourlyWeatherForecastModel", "()Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherForecastModel;", "Landroid/content/SharedPreferences;", "a", "Landroid/content/SharedPreferences;", "getPref", "()Landroid/content/SharedPreferences;", "setPref", "(Landroid/content/SharedPreferences;)V", "pref", "Landroid/content/SharedPreferences$Editor;", "b", "Landroid/content/SharedPreferences$Editor;", "getEditor", "()Landroid/content/SharedPreferences$Editor;", "setEditor", "(Landroid/content/SharedPreferences$Editor;)V", "editor", "Landroid/content/Context;", c.f10260a, "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mContext", "getLastLocationLatLng", "lastLocationLatLng", "<init>", "Companion", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherPreferenceManager {
    public static final Companion Companion = new Companion(null);
    public static WeatherPreferenceManager d;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f6205a;
    @NotNull
    public SharedPreferences.Editor b;
    @NotNull
    public Context c;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\t8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0016\u0010\r\u001a\u00020\t8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000bR\u0016\u0010\u000e\u001a\u00020\t8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000bR\u0016\u0010\u000f\u001a\u00020\t8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\u000bR\u0016\u0010\u0010\u001a\u00020\t8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherPreferenceManager$Companion;", "", "Landroid/content/Context;", "context", "Lcom/coveiot/android/weathersdk/WeatherPreferenceManager;", "getInstance", "(Landroid/content/Context;)Lcom/coveiot/android/weathersdk/WeatherPreferenceManager;", "INSTANCE", "Lcom/coveiot/android/weathersdk/WeatherPreferenceManager;", "", "KEY_PREF_NAME", "Ljava/lang/String;", "LAST_LOCATION_LAT_LONG", "WEATHER_DAYS", "WEATHER_ENABLED", "WEATHER_INTERVAL", "WEATHER_UNIT", "<init>", "()V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final WeatherPreferenceManager getInstance(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (WeatherPreferenceManager.d == null) {
                WeatherPreferenceManager.d = new WeatherPreferenceManager(context, null);
            }
            return WeatherPreferenceManager.d;
        }
    }

    public WeatherPreferenceManager(Context context) {
        this.c = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("WeatherPreference", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "mContext.getSharedPrefer…ME, Context.MODE_PRIVATE)");
        this.f6205a = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "pref.edit()");
        this.b = edit;
    }

    public /* synthetic */ WeatherPreferenceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final CurrentForecastModel getCurrentWeatherModel() {
        return (CurrentForecastModel) new Gson().fromJson(this.f6205a.getString(WeatherConstants.INSTANCE.getCURRENT_WEATHER(), null), (Class<Object>) CurrentForecastModel.class);
    }

    @NotNull
    public final SharedPreferences.Editor getEditor() {
        return this.b;
    }

    @Nullable
    public final HourlyWeatherForecastModel getHourlyWeatherForecastModel() {
        return (HourlyWeatherForecastModel) new Gson().fromJson(this.f6205a.getString(WeatherConstants.INSTANCE.getWEATHER_FORECAST_HOURLY(), null), (Class<Object>) HourlyWeatherForecastModel.class);
    }

    @Nullable
    public final String getLastLocationLatLng() {
        return this.f6205a.getString("last_location", "");
    }

    @NotNull
    public final Context getMContext() {
        return this.c;
    }

    @NotNull
    public final SharedPreferences getPref() {
        return this.f6205a;
    }

    @Nullable
    public final WeatherForecastModel getWeatherForecastModel() {
        return (WeatherForecastModel) new Gson().fromJson(this.f6205a.getString(WeatherConstants.INSTANCE.getWEATHER_FORECAST(), null), (Class<Object>) WeatherForecastModel.class);
    }

    public final int getWeatherInDays() {
        return this.f6205a.getInt("weather_days", 7);
    }

    public final int getWeatherInterval() {
        return this.f6205a.getInt("weather_interval", 30);
    }

    @NotNull
    public final String getWeatherUnit() {
        String string = this.f6205a.getString("weather_unit", WeatherConstants.INSTANCE.getUNIT_METRIC());
        Intrinsics.checkNotNull(string);
        return string;
    }

    public final boolean isWeatherEnabled() {
        return this.f6205a.getBoolean("weather_enabled", false);
    }

    public final void saveCurrentWeatherModel(@Nullable CurrentForecastModel currentForecastModel) {
        this.b.putString(WeatherConstants.INSTANCE.getCURRENT_WEATHER(), new Gson().toJson(currentForecastModel));
        this.b.commit();
    }

    public final void saveHourlyWeatherForecastModel(@Nullable HourlyWeatherForecastModel hourlyWeatherForecastModel) {
        this.b.putString(WeatherConstants.INSTANCE.getWEATHER_FORECAST_HOURLY(), new Gson().toJson(hourlyWeatherForecastModel));
        this.b.commit();
    }

    public final void saveLastLocationLatLng(@NotNull Location location) {
        Intrinsics.checkNotNullParameter(location, "location");
        SharedPreferences.Editor editor = this.b;
        editor.putString("last_location", String.valueOf(location.getLatitude()) + Constants.SEPARATOR_COMMA + location.getLongitude());
        this.b.commit();
    }

    public final void saveWeatherFeatureEnabled(boolean z) {
        this.b.putBoolean("weather_enabled", z);
        this.b.commit();
    }

    public final void saveWeatherForecastModel(@Nullable WeatherForecastModel weatherForecastModel) {
        this.b.putString(WeatherConstants.INSTANCE.getWEATHER_FORECAST(), new Gson().toJson(weatherForecastModel));
        this.b.commit();
    }

    public final void saveWeatherInDays(int i) {
        this.b.putInt("weather_days", i);
        this.b.commit();
    }

    public final void saveWeatherInterval(int i) {
        this.b.putInt("weather_interval", i);
        this.b.commit();
    }

    public final void saveWeatherUnit(@NotNull String weatherUnit) {
        Intrinsics.checkNotNullParameter(weatherUnit, "weatherUnit");
        this.b.putString("weather_unit", weatherUnit);
        this.b.commit();
    }

    public final void setEditor(@NotNull SharedPreferences.Editor editor) {
        Intrinsics.checkNotNullParameter(editor, "<set-?>");
        this.b = editor;
    }

    public final void setMContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.c = context;
    }

    public final void setPref(@NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.f6205a = sharedPreferences;
    }
}
