package com.coveiot.android.weather.weather.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.android.theme.LocationPermissionActivity;
import com.coveiot.android.weather.R;
import com.coveiot.android.weather.databinding.FragmentWeatherSettingBinding;
import com.coveiot.android.weather.weather.WeatherActivity;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.android.weather.weather.WeatherUtils;
import com.coveiot.android.weather.weather.WeatherViewModel;
import com.coveiot.android.weather.weather.utils.DialogListener;
import com.coveiot.android.weather.weather.utils.ViewUtilsKt;
import com.coveiot.android.weather.weather.utils.WeatherSettingsListener;
import com.coveiot.android.weathersdk.WeatherApiCallsManager;
import com.coveiot.android.weathersdk.WeatherApiErrorModel;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.android.weathersdk.WeatherUnit;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentForecastModel;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherForecastModel;
import com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWeatherSettings extends BaseFragment implements DialogListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentWeatherSettingBinding m;
    public WeatherAppPreferenceManager n;
    public WeatherPreferenceManager o;
    public WeatherViewModel q;
    public boolean s;
    public boolean t;
    public boolean u;
    public Button v;
    public TextView w;
    public boolean x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 1001;
    @NotNull
    public final String r = "FragmentWeatherSettings";

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentWeatherSettings newInstance() {
            return new FragmentWeatherSettings();
        }
    }

    /* loaded from: classes8.dex */
    public static final class a extends Lambda implements Function1<Location, Unit> {
        public final /* synthetic */ String[] $latLng;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String[] strArr) {
            super(1);
            this.$latLng = strArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Location location) {
            invoke2(location);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Location location) {
            if (location != null) {
                WeatherPreferenceManager.Companion companion = WeatherPreferenceManager.Companion;
                Context requireContext = FragmentWeatherSettings.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                WeatherPreferenceManager companion2 = companion.getInstance(requireContext);
                Intrinsics.checkNotNull(companion2);
                companion2.saveLastLocationLatLng(location);
                String[] strArr = this.$latLng;
                StringBuilder sb = new StringBuilder();
                sb.append(location.getLatitude());
                sb.append(',');
                sb.append(location.getLongitude());
                strArr[0] = sb.toString();
                FragmentWeatherSettings.this.E(location.getLatitude(), location.getLongitude());
                return;
            }
            Toast.makeText(FragmentWeatherSettings.this.requireContext(), R.string.location_not_found, 0).show();
        }
    }

    /* loaded from: classes8.dex */
    public static final class b extends Lambda implements Function1<View, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if (FragmentWeatherSettings.this.F() && BleApiManager.getInstance(FragmentWeatherSettings.this.requireContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
                Toast.makeText(FragmentWeatherSettings.this.requireContext(), R.string.band_not_connected, 0).show();
                FragmentWeatherSettings.this.z().setIsWeatherEnableData(Boolean.valueOf(true ^ FragmentWeatherSettings.this.t));
                FragmentWeatherSettings.this.x = false;
                FragmentWeatherSettings.this.y();
            } else if (!AppUtils.isNetConnected(FragmentWeatherSettings.this.requireContext())) {
                FragmentWeatherSettings.this.z().setIsWeatherEnableData(Boolean.valueOf(!FragmentWeatherSettings.this.t));
                FragmentWeatherSettings.this.x = false;
                FragmentWeatherSettings.this.y();
                Toast.makeText(FragmentWeatherSettings.this.requireContext(), FragmentWeatherSettings.this.getResources().getString(R.string.please_check_network), 1).show();
            } else {
                FragmentWeatherSettings fragmentWeatherSettings = FragmentWeatherSettings.this;
                fragmentWeatherSettings.P(fragmentWeatherSettings.t);
                FragmentWeatherSettings.this.x();
            }
        }
    }

    public static final void B(FragmentWeatherSettings this$0, Exception e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "e");
        String str = this$0.r;
        LogHelper.e(str, "addOnFailureListener " + e.getMessage());
    }

    public static final void C(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void H(FragmentWeatherSettings this$0, FragmentWeatherSettingBinding this_apply, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        if (compoundButton.isPressed()) {
            this$0.t = z;
            this$0.x = !this$0.x;
            this$0.y();
        }
        if (z) {
            RelativeLayout llWeatherUnit = this_apply.llWeatherUnit;
            Intrinsics.checkNotNullExpressionValue(llWeatherUnit, "llWeatherUnit");
            this$0.visible(llWeatherUnit);
            return;
        }
        RelativeLayout llWeatherUnit2 = this_apply.llWeatherUnit;
        Intrinsics.checkNotNullExpressionValue(llWeatherUnit2, "llWeatherUnit");
        this$0.gone(llWeatherUnit2);
    }

    public static final void I(FragmentWeatherSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Button button = this$0.v;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBarSave");
            button = null;
        }
        button.performClick();
    }

    public static final void J(FragmentWeatherSettings this$0, FragmentWeatherSettingBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.u = true;
        this_apply.setIsMetricUnitData(true);
        this$0.y();
    }

    public static final void K(FragmentWeatherSettings this$0, FragmentWeatherSettingBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.u = false;
        this_apply.setIsMetricUnitData(false);
        this$0.y();
    }

    public static final void L(FragmentWeatherSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().startActivity(new Intent(this$0.requireContext(), LocationPermissionActivity.class));
    }

    public static final void M(final FragmentWeatherSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            Button button = this$0.v;
            if (button == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolBarSave");
                button = null;
            }
            if (button.isEnabled()) {
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string = this$0.getString(R.string.confirmation);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
                String string2 = this$0.getString(R.string.save_changes);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
                final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
                String string3 = this$0.getString(R.string.save_changes_btn);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
                bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.f
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FragmentWeatherSettings.N(BottomSheetDialogTwoButtons.this, this$0, view2);
                    }
                });
                String string4 = this$0.getString(R.string.discard);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
                bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.e
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FragmentWeatherSettings.O(BottomSheetDialogTwoButtons.this, this$0, view2);
                    }
                });
                bottomSheetDialogTwoButtons.show();
                return;
            }
            this$0.requireActivity().onBackPressed();
        }
    }

    public static final void N(BottomSheetDialogTwoButtons dialog, FragmentWeatherSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Button button = this$0.v;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBarSave");
            button = null;
        }
        button.performClick();
    }

    public static final void O(BottomSheetDialogTwoButtons dialog, FragmentWeatherSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.requireActivity().onBackPressed();
    }

    public static final void Q(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, FragmentWeatherSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.requireActivity().finish();
    }

    public static final void S(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, FragmentWeatherSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.requireActivity().onBackPressed();
    }

    public final void A() {
        if (!AppUtils.isNetConnected(requireContext())) {
            Toast.makeText(requireContext(), R.string.please_enable_internet, 0).show();
            return;
        }
        String[] strArr = new String[1];
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(requireContext())");
        if (ContextCompat.checkSelfPermission(requireContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
            final a aVar = new a(strArr);
            lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.weather.weather.fragments.c
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    FragmentWeatherSettings.C(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.weather.weather.fragments.b
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    FragmentWeatherSettings.B(FragmentWeatherSettings.this, exc);
                }
            });
            return;
        }
        requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, this.p);
    }

    public final boolean D() {
        WeatherAppPreferenceManager weatherAppPreferenceManager = this.n;
        if (weatherAppPreferenceManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
            weatherAppPreferenceManager = null;
        }
        Boolean isMetricUnitEnabled = weatherAppPreferenceManager.isMetricUnitEnabled();
        Intrinsics.checkNotNull(isMetricUnitEnabled);
        return isMetricUnitEnabled.booleanValue() != this.u;
    }

    public final void E(final double d, final double d2) {
        String weatherUnit;
        WeatherAppPreferenceManager weatherAppPreferenceManager = this.n;
        if (weatherAppPreferenceManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
            weatherAppPreferenceManager = null;
        }
        if (Intrinsics.areEqual(weatherAppPreferenceManager.isMetricUnitEnabled(), Boolean.FALSE)) {
            weatherUnit = WeatherUnit.IMPERIAL.weatherUnit();
            Intrinsics.checkNotNullExpressionValue(weatherUnit, "{\n            WeatherUni…L.weatherUnit()\n        }");
        } else {
            weatherUnit = WeatherUnit.METRIC.weatherUnit();
            Intrinsics.checkNotNullExpressionValue(weatherUnit, "{\n            WeatherUni…C.weatherUnit()\n        }");
        }
        LoadingDialog progressDialog = getProgressDialog();
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.show();
        WeatherApiCallsManager.Companion companion = WeatherApiCallsManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        WeatherApiCallsManager weatherApiCallsManager = companion.getInstance(requireContext).getWeatherApiCallsManager();
        Intrinsics.checkNotNull(weatherApiCallsManager);
        final String str = weatherUnit;
        weatherApiCallsManager.getOpenWeatherMapDailyForecastInfo(d, d2, 7, str, new WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.weather.weather.fragments.FragmentWeatherSettings$getWeatherForecastInfo$1
            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onError(@NotNull WeatherApiErrorModel obj) {
                WeatherPreferenceManager weatherPreferenceManager;
                WeatherAppPreferenceManager weatherAppPreferenceManager2;
                WeatherAppPreferenceManager weatherAppPreferenceManager3;
                Intrinsics.checkNotNullParameter(obj, "obj");
                if (FragmentWeatherSettings.this.isAdded() && FragmentWeatherSettings.this.getProgressDialog() != null) {
                    LoadingDialog progressDialog2 = FragmentWeatherSettings.this.getProgressDialog();
                    Intrinsics.checkNotNull(progressDialog2);
                    if (progressDialog2.isShowing()) {
                        LoadingDialog progressDialog3 = FragmentWeatherSettings.this.getProgressDialog();
                        Intrinsics.checkNotNull(progressDialog3);
                        progressDialog3.dismiss();
                    }
                }
                weatherPreferenceManager = FragmentWeatherSettings.this.o;
                WeatherAppPreferenceManager weatherAppPreferenceManager4 = null;
                if (weatherPreferenceManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("weatherPreferenceManager");
                    weatherPreferenceManager = null;
                }
                weatherPreferenceManager.saveWeatherForecastModel(null);
                weatherAppPreferenceManager2 = FragmentWeatherSettings.this.n;
                if (weatherAppPreferenceManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
                    weatherAppPreferenceManager2 = null;
                }
                weatherAppPreferenceManager2.saveIsMetric(FragmentWeatherSettings.this.t);
                weatherAppPreferenceManager3 = FragmentWeatherSettings.this.n;
                if (weatherAppPreferenceManager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
                } else {
                    weatherAppPreferenceManager4 = weatherAppPreferenceManager3;
                }
                weatherAppPreferenceManager4.saveWeatherEnable(false);
                if (!FragmentWeatherSettings.this.isAdded() || FragmentWeatherSettings.this.getView() == null) {
                    return;
                }
                Toast.makeText(FragmentWeatherSettings.this.getContext(), FragmentWeatherSettings.this.getString(R.string.api_error), 0).show();
            }

            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onSuccess(@Nullable WeatherForecastModel weatherForecastModel) {
                WeatherPreferenceManager weatherPreferenceManager;
                WeatherAppPreferenceManager weatherAppPreferenceManager2;
                weatherPreferenceManager = FragmentWeatherSettings.this.o;
                WeatherAppPreferenceManager weatherAppPreferenceManager3 = null;
                if (weatherPreferenceManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("weatherPreferenceManager");
                    weatherPreferenceManager = null;
                }
                weatherPreferenceManager.saveWeatherForecastModel(weatherForecastModel);
                weatherAppPreferenceManager2 = FragmentWeatherSettings.this.n;
                if (weatherAppPreferenceManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
                } else {
                    weatherAppPreferenceManager3 = weatherAppPreferenceManager2;
                }
                weatherAppPreferenceManager3.saveOpenWeatherLatTimeStamp(new Date().getTime());
                WeatherApiCallsManager.Companion companion2 = WeatherApiCallsManager.Companion;
                Context requireContext2 = FragmentWeatherSettings.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                WeatherApiCallsManager singletonHolder = companion2.getInstance(requireContext2);
                double d3 = d;
                double d4 = d2;
                final String str2 = str;
                final FragmentWeatherSettings fragmentWeatherSettings = FragmentWeatherSettings.this;
                singletonHolder.getOpenWeatherMapCurrentWeatherInfo(d3, d4, str2, new WeatherApiResponseListener<CurrentForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.weather.weather.fragments.FragmentWeatherSettings$getWeatherForecastInfo$1$onSuccess$1
                    @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                    public void onError(@NotNull WeatherApiErrorModel obj) {
                        Intrinsics.checkNotNullParameter(obj, "obj");
                        if (FragmentWeatherSettings.this.isAdded()) {
                            FragmentWeatherSettings.this.dismissProgress();
                            if (FragmentWeatherSettings.this.getProgressDialog() != null) {
                                LoadingDialog progressDialog2 = FragmentWeatherSettings.this.getProgressDialog();
                                Intrinsics.checkNotNull(progressDialog2);
                                if (progressDialog2.isShowing()) {
                                    LoadingDialog progressDialog3 = FragmentWeatherSettings.this.getProgressDialog();
                                    Intrinsics.checkNotNull(progressDialog3);
                                    progressDialog3.dismiss();
                                }
                            }
                        }
                    }

                    @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                    public void onSuccess(@Nullable CurrentForecastModel currentForecastModel) {
                        WeatherAppPreferenceManager weatherAppPreferenceManager4;
                        boolean z;
                        WeatherAppPreferenceManager weatherAppPreferenceManager5;
                        WeatherViewModel weatherViewModel;
                        if (FragmentWeatherSettings.this.isAdded()) {
                            FragmentWeatherSettings.this.dismissProgress();
                            if (FragmentWeatherSettings.this.getProgressDialog() != null) {
                                LoadingDialog progressDialog2 = FragmentWeatherSettings.this.getProgressDialog();
                                Intrinsics.checkNotNull(progressDialog2);
                                if (progressDialog2.isShowing()) {
                                    LoadingDialog progressDialog3 = FragmentWeatherSettings.this.getProgressDialog();
                                    Intrinsics.checkNotNull(progressDialog3);
                                    progressDialog3.dismiss();
                                }
                            }
                        }
                        weatherAppPreferenceManager4 = FragmentWeatherSettings.this.n;
                        WeatherViewModel weatherViewModel2 = null;
                        if (weatherAppPreferenceManager4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
                            weatherAppPreferenceManager4 = null;
                        }
                        z = FragmentWeatherSettings.this.u;
                        weatherAppPreferenceManager4.saveIsMetric(z);
                        weatherAppPreferenceManager5 = FragmentWeatherSettings.this.n;
                        if (weatherAppPreferenceManager5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
                            weatherAppPreferenceManager5 = null;
                        }
                        weatherAppPreferenceManager5.saveWeatherEnable(FragmentWeatherSettings.this.t);
                        weatherViewModel = FragmentWeatherSettings.this.q;
                        if (weatherViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                        } else {
                            weatherViewModel2 = weatherViewModel;
                        }
                        String str3 = str2;
                        Locale ROOT = Locale.ROOT;
                        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                        String upperCase = str3.toUpperCase(ROOT);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
                        Context requireContext3 = FragmentWeatherSettings.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        weatherViewModel2.saveWeatherSettings(true, upperCase, requireContext3);
                    }
                });
            }
        });
    }

    public final boolean F() {
        return (BleApiManager.getInstance(getActivity()) == null || BleApiManager.getInstance(getActivity()).getBleApi() == null || !BleApiManager.getInstance(getActivity()).getBleApi().getDeviceSupportedFeatures().isWeatherSupportedInBand()) ? false : true;
    }

    public final void G() {
        this.t = false;
        z().setIsWeatherEnableData(Boolean.valueOf(this.t));
        this.x = false;
        y();
        requireContext().startActivity(new Intent(requireContext(), LocationPermissionActivity.class));
    }

    public final void P(boolean z) {
        MeasurementUnitType measurementUnitType;
        if (F()) {
            WeatherAppPreferenceManager weatherAppPreferenceManager = this.n;
            WeatherViewModel weatherViewModel = null;
            if (weatherAppPreferenceManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
                weatherAppPreferenceManager = null;
            }
            weatherAppPreferenceManager.saveIsMetric(this.u);
            if (this.u) {
                measurementUnitType = MeasurementUnitType.METRIC;
            } else {
                measurementUnitType = MeasurementUnitType.IMPERIAL;
            }
            WeatherViewModel weatherViewModel2 = this.q;
            if (weatherViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            } else {
                weatherViewModel = weatherViewModel2;
            }
            weatherViewModel.sendWeatherConfigInfoToBand(measurementUnitType, z, new WeatherSettingsListener() { // from class: com.coveiot.android.weather.weather.fragments.FragmentWeatherSettings$sendConfigInfoToBand$1
                @Override // com.coveiot.android.weather.weather.utils.WeatherSettingsListener
                public void onFailure() {
                    if (FragmentWeatherSettings.this.isAdded() && FragmentWeatherSettings.this.isVisible()) {
                        Toast.makeText(FragmentWeatherSettings.this.requireContext(), R.string.something_went_wrong, 0).show();
                    }
                }

                @Override // com.coveiot.android.weather.weather.utils.WeatherSettingsListener
                public void onSuccess() {
                    if (FragmentWeatherSettings.this.isAdded() && FragmentWeatherSettings.this.isVisible()) {
                        Toast.makeText(FragmentWeatherSettings.this.requireContext(), R.string.weather_unit_setting_updated, 0).show();
                    }
                }
            });
        }
    }

    public final void R() {
        String[] unGrantedPermissions;
        if (F()) {
            if (Build.VERSION.SDK_INT < 29) {
                unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(getContext(), new String[]{"android.permission.ACCESS_FINE_LOCATION"});
            } else {
                unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(getContext(), new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"});
            }
            Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
            if (!(unGrantedPermissions.length == 0)) {
                G();
            } else {
                w();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final boolean isWeatherAPICalled() {
        return this.s;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentWeatherSettingBinding.inflate(inflater, viewGroup, false);
        View root = z().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.weather.weather.utils.DialogListener
    public void onDismiss() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        Integer firstOrNull = ArraysKt___ArraysKt.firstOrNull(grantResults);
        if (firstOrNull == null || firstOrNull.intValue() != 0) {
            Toast.makeText(requireContext(), R.string.please_enable_location_permission, 1).show();
            return;
        }
        P(this.t);
        x();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        String[] unGrantedPermissions;
        super.onResume();
        if (F()) {
            if (Build.VERSION.SDK_INT < 29) {
                unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(getContext(), new String[]{"android.permission.ACCESS_FINE_LOCATION"});
            } else {
                unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(getContext(), new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"});
            }
            Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
            if (!(unGrantedPermissions.length == 0)) {
                WeatherAppPreferenceManager weatherAppPreferenceManager = this.n;
                if (weatherAppPreferenceManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
                    weatherAppPreferenceManager = null;
                }
                weatherAppPreferenceManager.saveWeatherEnable(false);
            }
        }
    }

    @Override // com.coveiot.android.weather.weather.utils.DialogListener
    public void onShowProgressDialog() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.weather.weather.WeatherActivity");
        ((WeatherActivity) activity).setupToolbar(Companion.newInstance());
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.weather.weather.WeatherActivity");
        ViewModel viewModel = ViewModelProviders.of((WeatherActivity) requireActivity).get(WeatherViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(requireActivity() as …herViewModel::class.java)");
        WeatherViewModel weatherViewModel = (WeatherViewModel) viewModel;
        this.q = weatherViewModel;
        TextView textView = null;
        if (weatherViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            weatherViewModel = null;
        }
        weatherViewModel.setDialogListener(this);
        View findViewById = requireActivity().findViewById(R.id.btn_ok);
        Intrinsics.checkNotNullExpressionValue(findViewById, "requireActivity().findViewById(R.id.btn_ok)");
        this.v = (Button) findViewById;
        View findViewById2 = requireActivity().findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "requireActivity().findVi…(R.id.toolbar_back_arrow)");
        this.w = (TextView) findViewById2;
        WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        WeatherAppPreferenceManager companion2 = companion.getInstance(requireContext);
        Intrinsics.checkNotNull(companion2);
        this.n = companion2;
        WeatherPreferenceManager.Companion companion3 = WeatherPreferenceManager.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        WeatherPreferenceManager companion4 = companion3.getInstance(requireContext2);
        Intrinsics.checkNotNull(companion4);
        this.o = companion4;
        final FragmentWeatherSettingBinding z = z();
        if (Build.VERSION.SDK_INT < 29) {
            z.tvLocationPermission.setVisibility(8);
        }
        z.switchWeather.setOnCheckedChangeListener(null);
        WeatherAppPreferenceManager weatherAppPreferenceManager = this.n;
        if (weatherAppPreferenceManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
            weatherAppPreferenceManager = null;
        }
        Boolean isMetricUnitEnabled = weatherAppPreferenceManager.isMetricUnitEnabled();
        Intrinsics.checkNotNull(isMetricUnitEnabled);
        z.setIsMetricUnitData(isMetricUnitEnabled);
        WeatherAppPreferenceManager weatherAppPreferenceManager2 = this.n;
        if (weatherAppPreferenceManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
            weatherAppPreferenceManager2 = null;
        }
        Boolean isMetricUnitEnabled2 = weatherAppPreferenceManager2.isMetricUnitEnabled();
        Intrinsics.checkNotNull(isMetricUnitEnabled2);
        this.u = isMetricUnitEnabled2.booleanValue();
        WeatherAppPreferenceManager weatherAppPreferenceManager3 = this.n;
        if (weatherAppPreferenceManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
            weatherAppPreferenceManager3 = null;
        }
        Boolean isWeatherEnabled = weatherAppPreferenceManager3.isWeatherEnabled();
        Intrinsics.checkNotNull(isWeatherEnabled);
        z.setIsWeatherEnableData(isWeatherEnabled);
        WeatherAppPreferenceManager weatherAppPreferenceManager4 = this.n;
        if (weatherAppPreferenceManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
            weatherAppPreferenceManager4 = null;
        }
        Boolean isWeatherEnabled2 = weatherAppPreferenceManager4.isWeatherEnabled();
        Intrinsics.checkNotNull(isWeatherEnabled2);
        boolean booleanValue = isWeatherEnabled2.booleanValue();
        this.t = booleanValue;
        if (booleanValue) {
            RelativeLayout llWeatherUnit = z.llWeatherUnit;
            Intrinsics.checkNotNullExpressionValue(llWeatherUnit, "llWeatherUnit");
            visible(llWeatherUnit);
        } else {
            RelativeLayout llWeatherUnit2 = z.llWeatherUnit;
            Intrinsics.checkNotNullExpressionValue(llWeatherUnit2, "llWeatherUnit");
            gone(llWeatherUnit2);
        }
        z.switchWeather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.weather.weather.fragments.l
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                FragmentWeatherSettings.H(FragmentWeatherSettings.this, z, compoundButton, z2);
            }
        });
        z.btnOk.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWeatherSettings.I(FragmentWeatherSettings.this, view2);
            }
        });
        z.rlMetric.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWeatherSettings.J(FragmentWeatherSettings.this, z, view2);
            }
        });
        z.rlImperial.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWeatherSettings.K(FragmentWeatherSettings.this, z, view2);
            }
        });
        z.tvLocationPermission.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWeatherSettings.L(FragmentWeatherSettings.this, view2);
            }
        });
        Button button = this.v;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBarSave");
            button = null;
        }
        ViewUtilsKt.setSafeOnClickListener(button, new b());
        TextView textView2 = this.w;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backBtn");
        } else {
            textView = textView2;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWeatherSettings.M(FragmentWeatherSettings.this, view2);
            }
        });
    }

    public final void setWeatherAPICalled(boolean z) {
        this.s = z;
    }

    @Override // com.coveiot.android.weather.weather.utils.DialogListener
    public void showErrorDialog() {
        if (isAdded()) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.setting_couldnot_save);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
            final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentWeatherSettings.Q(BottomSheetDialogOneButtonOneTitle.this, this, view);
                }
            });
            if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
                return;
            }
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    @Override // com.coveiot.android.weather.weather.utils.DialogListener
    public void showSuccessDialog() {
        if (isAdded()) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = requireContext().getString(R.string.setting_success_message);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri….setting_success_message)");
            final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
            String string2 = requireContext().getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getStri…ring.ok\n                )");
            bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.weather.weather.fragments.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentWeatherSettings.S(BottomSheetDialogOneButtonOneTitle.this, this, view);
                }
            });
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    public final void w() {
        String upperCase;
        P(this.t);
        if (this.u) {
            String weatherUnit = WeatherUnit.METRIC.weatherUnit();
            Intrinsics.checkNotNullExpressionValue(weatherUnit, "METRIC.weatherUnit()");
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            upperCase = weatherUnit.toUpperCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        } else {
            String weatherUnit2 = WeatherUnit.IMPERIAL.weatherUnit();
            Intrinsics.checkNotNullExpressionValue(weatherUnit2, "IMPERIAL.weatherUnit()");
            Locale ROOT2 = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
            upperCase = weatherUnit2.toUpperCase(ROOT2);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        }
        WeatherViewModel weatherViewModel = this.q;
        WeatherAppPreferenceManager weatherAppPreferenceManager = null;
        if (weatherViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            weatherViewModel = null;
        }
        boolean z = this.t;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        weatherViewModel.saveWeatherSettings(z, upperCase, requireContext);
        WeatherAppPreferenceManager weatherAppPreferenceManager2 = this.n;
        if (weatherAppPreferenceManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
            weatherAppPreferenceManager2 = null;
        }
        weatherAppPreferenceManager2.saveWeatherEnable(this.t);
        WeatherPreferenceManager weatherPreferenceManager = this.o;
        if (weatherPreferenceManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weatherPreferenceManager");
            weatherPreferenceManager = null;
        }
        weatherPreferenceManager.saveWeatherFeatureEnabled(this.t);
        if (!this.t) {
            WeatherPreferenceManager weatherPreferenceManager2 = this.o;
            if (weatherPreferenceManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("weatherPreferenceManager");
                weatherPreferenceManager2 = null;
            }
            weatherPreferenceManager2.saveWeatherForecastModel(null);
            WeatherAppPreferenceManager weatherAppPreferenceManager3 = this.n;
            if (weatherAppPreferenceManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("weatherAppPreferenceManager");
            } else {
                weatherAppPreferenceManager = weatherAppPreferenceManager3;
            }
            weatherAppPreferenceManager.saveOpenWeatherLatTimeStamp(-1L);
        }
        if (this.t && !this.s) {
            long lastUpdateWeatherTimeStamp = UserDataManager.getInstance(requireContext()).getLastUpdateWeatherTimeStamp();
            int currentTimeMillis = (int) ((System.currentTimeMillis() - lastUpdateWeatherTimeStamp) / 60000);
            if (lastUpdateWeatherTimeStamp == 0 || currentTimeMillis > 120) {
                if (AppUtils.isNetConnected(requireContext())) {
                    this.s = true;
                    Context applicationContext = requireActivity().getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "requireActivity().applicationContext");
                    WeatherUtils weatherUtils = new WeatherUtils(applicationContext);
                    Context applicationContext2 = requireActivity().getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext2, "requireActivity().applicationContext");
                    weatherUtils.getLocationAndCallWeatherAPI(applicationContext2);
                } else {
                    Toast.makeText(requireContext(), getResources().getString(R.string.please_check_network), 1).show();
                }
            }
        }
        this.x = false;
        y();
    }

    public final void x() {
        if (F()) {
            R();
        } else {
            A();
        }
    }

    public final void y() {
        Button button = null;
        if (this.x | D()) {
            Button button2 = this.v;
            if (button2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolBarSave");
                button2 = null;
            }
            button2.setEnabled(true);
            Button button3 = this.v;
            if (button3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolBarSave");
            } else {
                button = button3;
            }
            button.setClickable(true);
            return;
        }
        Button button4 = this.v;
        if (button4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBarSave");
            button4 = null;
        }
        button4.setEnabled(false);
        Button button5 = this.v;
        if (button5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBarSave");
        } else {
            button = button5;
        }
        button.setClickable(false);
    }

    public final FragmentWeatherSettingBinding z() {
        FragmentWeatherSettingBinding fragmentWeatherSettingBinding = this.m;
        Intrinsics.checkNotNull(fragmentWeatherSettingBinding);
        return fragmentWeatherSettingBinding;
    }
}
