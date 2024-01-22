package com.coveiot.android.weather.weather;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.request.SetWeatherConfigInfoRequest;
import com.coveiot.android.bleabstract.request.SetWeatherUnitRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.weather.R;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.android.weather.weather.utils.DialogListener;
import com.coveiot.android.weather.weather.utils.WeatherSettingsListener;
import com.coveiot.android.weathersdk.WeatherApiCallsManager;
import com.coveiot.android.weathersdk.WeatherApiErrorModel;
import com.coveiot.android.weathersdk.WeatherConditionType;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.android.weathersdk.WeatherUnit;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherForecastModel;
import com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.WeatherForecastBean;
import com.coveiot.coveaccess.userappsetting.SaveWeatherSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveWeatherSettingsRes;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherViewModel extends AndroidViewModel {
    @NotNull
    public Context d;
    public DialogListener dialogListener;
    public WeatherForecastUpdate weatherForecastUpdate;

    /* loaded from: classes8.dex */
    public interface WeatherForecastUpdate {
        void onWeatherUpdate(boolean z);
    }

    /* loaded from: classes8.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WeatherConditionType.WeatherConditionEnum.values().length];
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Thunderstorm.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Clouds.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Clear_Sunny.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Drizzle.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Mist.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Rain.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Snow.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WeatherViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = application;
    }

    public static final void c(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public final void b(double d, double d2) {
        String weatherUnit = WeatherUnit.METRIC.weatherUnit();
        WeatherAppPreferenceManager companion = WeatherAppPreferenceManager.Companion.getInstance(this.d);
        Intrinsics.checkNotNull(companion);
        if (Intrinsics.areEqual(companion.isMetricUnitEnabled(), Boolean.FALSE)) {
            weatherUnit = WeatherUnit.IMPERIAL.weatherUnit();
        }
        String weatherUnit2 = weatherUnit;
        WeatherApiCallsManager weatherApiCallsManager = WeatherApiCallsManager.Companion.getInstance(this.d).getWeatherApiCallsManager();
        if (weatherApiCallsManager != null) {
            Intrinsics.checkNotNullExpressionValue(weatherUnit2, "weatherUnit");
            weatherApiCallsManager.getOpenWeatherMapDailyForecastInfo(d, d2, 7, weatherUnit2, new WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.weather.weather.WeatherViewModel$getWeatherForecastInfo$1
                @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                public void onError(@NotNull WeatherApiErrorModel obj) {
                    Context context;
                    Intrinsics.checkNotNullParameter(obj, "obj");
                    LogHelper.d("getWeatherForecastInfo", obj.toString());
                    WeatherPreferenceManager.Companion companion2 = WeatherPreferenceManager.Companion;
                    context = WeatherViewModel.this.d;
                    WeatherPreferenceManager companion3 = companion2.getInstance(context);
                    Intrinsics.checkNotNull(companion3);
                    companion3.saveWeatherForecastModel(new WeatherForecastModel(null, null, null, null, null, null, null, null, null, null, null, 2047, null));
                    WeatherViewModel.this.getWeatherForecastUpdate().onWeatherUpdate(false);
                }

                @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                public void onSuccess(@Nullable WeatherForecastModel weatherForecastModel) {
                    Context context;
                    Context context2;
                    LogHelper.d("getWeatherForecastInfo", FirebaseAnalytics.Param.SUCCESS);
                    WeatherPreferenceManager.Companion companion2 = WeatherPreferenceManager.Companion;
                    context = WeatherViewModel.this.d;
                    WeatherPreferenceManager companion3 = companion2.getInstance(context);
                    Intrinsics.checkNotNull(companion3);
                    companion3.saveWeatherForecastModel(weatherForecastModel);
                    WeatherAppPreferenceManager.Companion companion4 = WeatherAppPreferenceManager.Companion;
                    context2 = WeatherViewModel.this.d;
                    WeatherAppPreferenceManager companion5 = companion4.getInstance(context2);
                    if (companion5 != null) {
                        companion5.saveOpenWeatherLatTimeStamp(System.currentTimeMillis());
                    }
                    WeatherViewModel.this.getWeatherForecastUpdate().onWeatherUpdate(true);
                }
            });
        }
    }

    public final void getCurrentLocation() {
        final String[] strArr = new String[1];
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.d);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(mContext)");
        if (ContextCompat.checkSelfPermission(this.d, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.coveiot.android.weather.weather.WeatherViewModel$getCurrentLocation$1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(@Nullable Location location) {
                    Context context;
                    if (location != null) {
                        WeatherPreferenceManager.Companion companion = WeatherPreferenceManager.Companion;
                        context = WeatherViewModel.this.d;
                        WeatherPreferenceManager companion2 = companion.getInstance(context);
                        Intrinsics.checkNotNull(companion2);
                        companion2.saveLastLocationLatLng(location);
                        String[] strArr2 = strArr;
                        StringBuilder sb = new StringBuilder();
                        sb.append(location.getLatitude());
                        sb.append(',');
                        sb.append(location.getLongitude());
                        strArr2[0] = sb.toString();
                        LogHelper.d("getCurrentLocation", "Last Location ==== " + location.getLatitude() + ", " + location.getLongitude());
                        WeatherViewModel.this.b(location.getLatitude(), location.getLongitude());
                        return;
                    }
                    WeatherViewModel.this.getWeatherForecastUpdate().onWeatherUpdate(false);
                    LogHelper.d("getCurrentLocation", "getLastLocationLatLng onSuccess() location is NULL====");
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.weather.weather.WeatherViewModel$getCurrentLocation$2
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(@NotNull Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    WeatherViewModel.this.getWeatherForecastUpdate().onWeatherUpdate(false);
                    LogHelper.d("getCurrentLocation", "onFailure() in getLastLocation() ====");
                }
            });
        } else {
            getWeatherForecastUpdate().onWeatherUpdate(false);
        }
    }

    public final int getDashboardWeatherDrawable(@NotNull WeatherConditionType.WeatherConditionEnum weatherConditionType) {
        Intrinsics.checkNotNullParameter(weatherConditionType, "weatherConditionType");
        switch (WhenMappings.$EnumSwitchMapping$0[weatherConditionType.ordinal()]) {
            case 1:
                return R.drawable.thunderstorm_small;
            case 2:
                return R.drawable.cloud_small;
            case 3:
                return R.drawable.clear_small;
            case 4:
                return R.drawable.drizzle_small;
            case 5:
                return R.drawable.mist_small;
            case 6:
                return R.drawable.rain_small;
            case 7:
                return R.drawable.snow_small;
            default:
                return R.drawable.mist_small;
        }
    }

    @NotNull
    public final DialogListener getDialogListener() {
        DialogListener dialogListener = this.dialogListener;
        if (dialogListener != null) {
            return dialogListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        return null;
    }

    @NotNull
    public final String getTemmpratueUnit() {
        WeatherAppPreferenceManager companion = WeatherAppPreferenceManager.Companion.getInstance(this.d);
        Intrinsics.checkNotNull(companion);
        return Intrinsics.areEqual(companion.isMetricUnitEnabled(), Boolean.TRUE) ? "˚C" : "˚F";
    }

    public final int getWeatherDetailDrawable(@NotNull WeatherConditionType.WeatherConditionEnum weatherConditionType) {
        Intrinsics.checkNotNullParameter(weatherConditionType, "weatherConditionType");
        switch (WhenMappings.$EnumSwitchMapping$0[weatherConditionType.ordinal()]) {
            case 1:
                return R.drawable.thunderstorm_large;
            case 2:
                return R.drawable.cloud_large;
            case 3:
                return R.drawable.clear_large;
            case 4:
                return R.drawable.drizzle_large;
            case 5:
                return R.drawable.mist_larg;
            case 6:
                return R.drawable.rain_large;
            case 7:
                return R.drawable.snow_larg;
            default:
                return R.drawable.mist_larg;
        }
    }

    public final int getWeatherForecastDrawable(@NotNull WeatherConditionType.WeatherConditionEnum weatherConditionType) {
        Intrinsics.checkNotNullParameter(weatherConditionType, "weatherConditionType");
        switch (WhenMappings.$EnumSwitchMapping$0[weatherConditionType.ordinal()]) {
            case 1:
                return R.drawable.thunderstorm;
            case 2:
                return R.drawable.cloud;
            case 3:
                return R.drawable.clear;
            case 4:
                return R.drawable.drizzle;
            case 5:
                return R.drawable.mist;
            case 6:
                return R.drawable.rain;
            case 7:
                return R.drawable.snow;
            default:
                return R.drawable.ic_weather;
        }
    }

    @NotNull
    public final WeatherForecastUpdate getWeatherForecastUpdate() {
        WeatherForecastUpdate weatherForecastUpdate = this.weatherForecastUpdate;
        if (weatherForecastUpdate != null) {
            return weatherForecastUpdate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("weatherForecastUpdate");
        return null;
    }

    @NotNull
    public final String getWindSpeedUnit() {
        WeatherAppPreferenceManager companion = WeatherAppPreferenceManager.Companion.getInstance(this.d);
        Intrinsics.checkNotNull(companion);
        return Intrinsics.areEqual(companion.isMetricUnitEnabled(), Boolean.TRUE) ? "Kmph" : "mph";
    }

    public final void saveWeatherSettings(boolean z, @NotNull String unitSystem, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(unitSystem, "unitSystem");
        Intrinsics.checkNotNullParameter(context, "context");
        WeatherForecastBean weatherForecastBean = new WeatherForecastBean();
        weatherForecastBean.setActive(Boolean.valueOf(z));
        weatherForecastBean.setUnitSystem(unitSystem);
        SaveWeatherSettingsReq saveWeatherSettingsReq = new SaveWeatherSettingsReq(weatherForecastBean);
        LogHelper.d("saveWeatherSettings", saveWeatherSettingsReq.toString());
        CoveUserAppSettings.saveWeatherSettings(saveWeatherSettingsReq, new CoveApiListener<SaveWeatherSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.weather.weather.WeatherViewModel$saveWeatherSettings$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                WeatherViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SaveWeatherSettingsRes saveWeatherSettingsRes) {
                WeatherViewModel.this.getDialogListener().showSuccessDialog();
            }
        });
    }

    public final void sendWeatherConfigInfoToBand(@NotNull MeasurementUnitType measurementUnitType, boolean z, @NotNull final WeatherSettingsListener configInfoListener) {
        Intrinsics.checkNotNullParameter(measurementUnitType, "measurementUnitType");
        Intrinsics.checkNotNullParameter(configInfoListener, "configInfoListener");
        if (BleApiManager.getInstance(this.d).getBleApi().getDeviceSupportedFeatures().isWeatherEnableCommandSupported()) {
            BleApiManager.getInstance(this.d).getBleApi().setUserSettings(new SetWeatherConfigInfoRequest(measurementUnitType, z), new SettingsResultListener() { // from class: com.coveiot.android.weather.weather.WeatherViewModel$sendWeatherConfigInfoToBand$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                }
            });
        } else if (z) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isEastApexDevice(this.d) && !companion.isSmaDevice(this.d)) {
                BleApiManager.getInstance(this.d).getBleApi().setUserSettings(new SetWeatherUnitRequest(measurementUnitType), new SettingsResultListener() { // from class: com.coveiot.android.weather.weather.WeatherViewModel$sendWeatherConfigInfoToBand$2
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        WeatherSettingsListener.this.onFailure();
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        WeatherSettingsListener.this.onSuccess();
                    }
                });
            } else {
                new WeatherUtils(this.d).sendWeatherToBle(this.d);
            }
        }
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setWeatherForecastUpdate(@NotNull WeatherForecastUpdate weatherForecastUpdate) {
        Intrinsics.checkNotNullParameter(weatherForecastUpdate, "<set-?>");
        this.weatherForecastUpdate = weatherForecastUpdate;
    }

    public final void showSettingSuccessFailureDialog(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.setting_success_message);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri….setting_success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(context, string);
        String string2 = context.getString(com.coveiot.android.theme.R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(\n     …R.string.ok\n            )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeatherViewModel.c(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
