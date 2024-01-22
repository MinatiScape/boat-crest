package com.coveiot.android.weather.weather;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherAppPreferenceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String WeatherDetails = "WeatherDetails";
    @Nullable
    public static WeatherAppPreferenceManager d;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f6174a;
    @NotNull
    public SharedPreferences b;
    @NotNull
    public SharedPreferences.Editor c;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final WeatherAppPreferenceManager getInstance(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (WeatherAppPreferenceManager.d == null) {
                WeatherAppPreferenceManager.d = new WeatherAppPreferenceManager(context, null);
            }
            return WeatherAppPreferenceManager.d;
        }
    }

    public WeatherAppPreferenceManager(Context context) {
        this.f6174a = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("WeatherPreference", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "mContext.getSharedPrefer…ME, Context.MODE_PRIVATE)");
        this.b = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "pref.edit()");
        this.c = edit;
    }

    public /* synthetic */ WeatherAppPreferenceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void clearPreferences(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        for (String str : context.getSharedPreferences("WeatherPreference", 0).getAll().keySet()) {
            remove(context, str);
        }
    }

    @NotNull
    public final SharedPreferences.Editor getEditor() {
        return this.c;
    }

    @NotNull
    public final Context getMContext() {
        return this.f6174a;
    }

    @Nullable
    public final Long getOpenWeatherLatTimeStamp() {
        return Long.valueOf(this.b.getLong("weather_api_last_time_stamp", -1L));
    }

    @NotNull
    public final SharedPreferences getPref() {
        return this.b;
    }

    @Nullable
    public final Boolean isMetricUnitEnabled() {
        return Boolean.valueOf(this.b.getBoolean("is_metric_enabled", true));
    }

    @Nullable
    public final Boolean isWeatherEnabled() {
        return Boolean.valueOf(this.b.getBoolean("is_weather_enabled", false));
    }

    public final void remove(@NotNull Context context, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences("WeatherPreference", 0);
        if (sharedPreferences.contains(str)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(str);
            edit.commit();
        }
    }

    public final void saveIsMetric(boolean z) {
        this.c.putBoolean("is_metric_enabled", z);
        this.c.commit();
    }

    public final void saveOpenWeatherLatTimeStamp(long j) {
        this.c.putLong("weather_api_last_time_stamp", j);
        this.c.commit();
    }

    public final void saveWeatherEnable(boolean z) {
        this.c.putBoolean("is_weather_enabled", z);
        this.c.commit();
    }

    public final void setEditor(@NotNull SharedPreferences.Editor editor) {
        Intrinsics.checkNotNullParameter(editor, "<set-?>");
        this.c = editor;
    }

    public final void setMContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f6174a = context;
    }

    public final void setPref(@NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.b = sharedPreferences;
    }
}
