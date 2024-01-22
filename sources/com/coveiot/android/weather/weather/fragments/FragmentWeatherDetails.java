package com.coveiot.android.weather.weather.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.weather.R;
import com.coveiot.android.weather.response.forecastmodel.WeatherDetail;
import com.coveiot.android.weather.response.forecastmodel.WeatherModel;
import com.coveiot.android.weather.weather.WeatherActivity;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.android.weather.weather.WeatherDetailsAdapter;
import com.coveiot.android.weather.weather.WeatherUtils;
import com.coveiot.android.weather.weather.WeatherViewModel;
import com.coveiot.android.weathersdk.WeatherApiCallsManager;
import com.coveiot.android.weathersdk.WeatherApiErrorModel;
import com.coveiot.android.weathersdk.WeatherConditionType;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.android.weathersdk.WeatherUnit;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentForecastModel;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentWeather;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherDetails;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherForecastModel;
import com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener;
import com.coveiot.utils.utility.AppUtils;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWeatherDetails extends BaseFragment implements WeatherViewModel.WeatherForecastUpdate {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public WeatherDetailsAdapter m;
    public WeatherViewModel n;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentWeatherDetails newInstance() {
            return new FragmentWeatherDetails();
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

    public final void k(CurrentForecastModel currentForecastModel) {
        CurrentWeather currentWeather;
        CurrentWeather currentWeather2;
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_weather);
        Context requireContext = requireContext();
        WeatherViewModel weatherViewModel = this.n;
        WeatherViewModel weatherViewModel2 = null;
        if (weatherViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            weatherViewModel = null;
        }
        List<CurrentWeather> currentWeather3 = currentForecastModel.getCurrentWeather();
        WeatherConditionType.WeatherConditionEnum iconType = (currentWeather3 == null || (currentWeather2 = currentWeather3.get(0)) == null) ? null : currentWeather2.getIconType();
        Intrinsics.checkNotNull(iconType);
        imageView.setImageDrawable(requireContext.getDrawable(weatherViewModel.getWeatherDetailDrawable(iconType)));
        TextView textView = (TextView) _$_findCachedViewById(R.id.txt_temp);
        StringBuilder sb = new StringBuilder();
        Double temp = currentForecastModel.getTemp();
        sb.append(temp != null ? Integer.valueOf(kotlin.math.c.roundToInt(temp.doubleValue())) : null);
        WeatherViewModel weatherViewModel3 = this.n;
        if (weatherViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            weatherViewModel3 = null;
        }
        sb.append(weatherViewModel3.getTemmpratueUnit());
        textView.setText(sb.toString());
        ((TextView) _$_findCachedViewById(R.id.txt_loc)).setText(currentForecastModel.getName());
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.txt_status);
        List<CurrentWeather> currentWeather4 = currentForecastModel.getCurrentWeather();
        textView2.setText((currentWeather4 == null || (currentWeather = currentWeather4.get(0)) == null) ? null : currentWeather.getMain());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(currentForecastModel.getHumidity());
        sb2.append('%');
        ((TextView) _$_findCachedViewById(R.id.txt_humidity)).setText(sb2.toString());
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.txt_wind_speed);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(currentForecastModel.getWindSpeed());
        WeatherViewModel weatherViewModel4 = this.n;
        if (weatherViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
        } else {
            weatherViewModel2 = weatherViewModel4;
        }
        sb3.append(weatherViewModel2.getWindSpeedUnit());
        textView3.setText(sb3.toString());
        ((TextView) _$_findCachedViewById(R.id.txt_pressure)).setText(currentForecastModel.getPressure() + "hPa");
        Calendar calendar = Calendar.getInstance();
        WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        WeatherAppPreferenceManager companion2 = companion.getInstance(requireContext2);
        Intrinsics.checkNotNull(companion2);
        Long openWeatherLatTimeStamp = companion2.getOpenWeatherLatTimeStamp();
        Intrinsics.checkNotNull(openWeatherLatTimeStamp);
        calendar.setTimeInMillis(openWeatherLatTimeStamp.longValue());
        DecimalFormat decimalFormat = new DecimalFormat("00");
        Intrinsics.checkNotNull(calendar);
        String format = decimalFormat.format(Integer.valueOf(calendar.get(2) + 1));
        ((TextView) _$_findCachedViewById(R.id.txt_date)).setText(decimalFormat.format(Integer.valueOf(calendar.get(11))) + '/' + format + Soundex.SILENT_MARKER + decimalFormat.format(Integer.valueOf(calendar.get(10))) + ':' + decimalFormat.format(Integer.valueOf(calendar.get(12))));
        calendar.setTime(currentForecastModel.getSunriseDate());
        ((TextView) _$_findCachedViewById(R.id.txt_sunrise)).setText(decimalFormat.format(Integer.valueOf(calendar.get(10))) + ':' + decimalFormat.format(Integer.valueOf(calendar.get(12))) + ' ' + (Intrinsics.areEqual(String.valueOf(calendar.get(9)), "1") ? "PM" : "AM"));
        calendar.setTime(currentForecastModel.getSunsetDate());
        ((TextView) _$_findCachedViewById(R.id.txt_sunset)).setText(decimalFormat.format(Integer.valueOf(calendar.get(10))) + ':' + decimalFormat.format(Integer.valueOf(calendar.get(12))) + ' ' + (Intrinsics.areEqual(String.valueOf(calendar.get(9)), "1") ? "PM" : "AM"));
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_weather, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        String str;
        String str2;
        String lastLocationLatLng;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.weather.weather.WeatherActivity");
        ((WeatherActivity) activity).setupToolbar(Companion.newInstance());
        WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        WeatherAppPreferenceManager companion2 = companion.getInstance(requireContext);
        Intrinsics.checkNotNull(companion2);
        Boolean isWeatherEnabled = companion2.isWeatherEnabled();
        Boolean bool = Boolean.FALSE;
        if (Intrinsics.areEqual(isWeatherEnabled, bool)) {
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.weather.weather.WeatherActivity");
            ((WeatherActivity) activity2).replaceFragment(FragmentWeatherDisabled.Companion.newInstance());
            return;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.weather.weather.WeatherActivity");
        ViewModel viewModel = ViewModelProviders.of((WeatherActivity) requireActivity).get(WeatherViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(requireActivity() as …herViewModel::class.java)");
        WeatherViewModel weatherViewModel = (WeatherViewModel) viewModel;
        this.n = weatherViewModel;
        List list = null;
        if (weatherViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            weatherViewModel = null;
        }
        weatherViewModel.setWeatherForecastUpdate(this);
        WeatherPreferenceManager.Companion companion3 = WeatherPreferenceManager.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "this.requireContext()");
        WeatherPreferenceManager companion4 = companion3.getInstance(requireContext2);
        Intrinsics.checkNotNull(companion4);
        WeatherForecastModel weatherForecastModel = companion4.getWeatherForecastModel();
        if (weatherForecastModel != null) {
            updateUI(weatherForecastModel);
        } else if (AppUtils.isNetConnected(requireContext())) {
            BaseFragment.showProgress$default(this, false, 1, null);
            WeatherViewModel weatherViewModel2 = this.n;
            if (weatherViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                weatherViewModel2 = null;
            }
            weatherViewModel2.getCurrentLocation();
        }
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        WeatherPreferenceManager companion5 = companion3.getInstance(requireContext3);
        Intrinsics.checkNotNull(companion5);
        final CurrentForecastModel currentWeatherModel = companion5.getCurrentWeatherModel();
        if (!AppUtils.isNetConnected(requireContext())) {
            if (currentWeatherModel != null) {
                k(currentWeatherModel);
            }
        } else if (currentWeatherModel == null) {
            String weatherUnit = WeatherUnit.METRIC.weatherUnit();
            Context requireContext4 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
            WeatherAppPreferenceManager companion6 = companion.getInstance(requireContext4);
            Intrinsics.checkNotNull(companion6);
            if (Intrinsics.areEqual(companion6.isMetricUnitEnabled(), bool)) {
                weatherUnit = WeatherUnit.IMPERIAL.weatherUnit();
            }
            String str3 = weatherUnit;
            Context requireContext5 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
            WeatherPreferenceManager companion7 = companion3.getInstance(requireContext5);
            if (companion7 != null && (lastLocationLatLng = companion7.getLastLocationLatLng()) != null) {
                list = StringsKt__StringsKt.split$default((CharSequence) lastLocationLatLng, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null);
            }
            if (list == null || list.size() <= 1 || (str = (String) list.get(0)) == null || (str2 = (String) list.get(1)) == null) {
                return;
            }
            WeatherApiCallsManager.Companion companion8 = WeatherApiCallsManager.Companion;
            Context requireContext6 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
            companion8.getInstance(requireContext6).getOpenWeatherMapCurrentWeatherInfo(Double.parseDouble(str), Double.parseDouble(str2), str3, new WeatherApiResponseListener<CurrentForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.weather.weather.fragments.FragmentWeatherDetails$onViewCreated$1$1$1
                @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                public void onError(@NotNull WeatherApiErrorModel obj) {
                    CurrentForecastModel currentForecastModel;
                    Intrinsics.checkNotNullParameter(obj, "obj");
                    if (!FragmentWeatherDetails.this.isAdded() || (currentForecastModel = currentWeatherModel) == null) {
                        return;
                    }
                    FragmentWeatherDetails.this.k(currentForecastModel);
                }

                @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                public void onSuccess(@Nullable CurrentForecastModel currentForecastModel) {
                    if (currentForecastModel == null || !FragmentWeatherDetails.this.isAdded()) {
                        return;
                    }
                    FragmentWeatherDetails.this.k(currentForecastModel);
                }
            });
        } else {
            k(currentWeatherModel);
        }
    }

    @Override // com.coveiot.android.weather.weather.WeatherViewModel.WeatherForecastUpdate
    public void onWeatherUpdate(boolean z) {
        dismissProgress();
        if (z) {
            WeatherPreferenceManager.Companion companion = WeatherPreferenceManager.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "this.requireContext()");
            WeatherPreferenceManager companion2 = companion.getInstance(requireContext);
            Intrinsics.checkNotNull(companion2);
            WeatherForecastModel weatherForecastModel = companion2.getWeatherForecastModel();
            Intrinsics.checkNotNull(weatherForecastModel);
            updateUI(weatherForecastModel);
            return;
        }
        Toast.makeText(getContext(), R.string.location_not_found, 0).show();
    }

    public final void updateUI(@NotNull WeatherForecastModel weatherForecastFromPref) {
        Intrinsics.checkNotNullParameter(weatherForecastFromPref, "weatherForecastFromPref");
        int i = R.id.weather_recycler_view;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(getContext()));
        List<WeatherDetails> weatherDetailList = weatherForecastFromPref.getWeatherDetailList();
        if (weatherDetailList != null) {
            boolean z = true;
            if (weatherDetailList.isEmpty()) {
                return;
            }
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            WeatherModel weatherForecastModel = new WeatherUtils(requireContext).getWeatherForecastModel(weatherForecastFromPref);
            List<WeatherDetail> weatherDetailList2 = weatherForecastModel.getWeatherDetailList();
            if (weatherDetailList2 != null && !weatherDetailList2.isEmpty()) {
                z = false;
            }
            if (z) {
                return;
            }
            List<WeatherDetail> weatherDetailList3 = weatherForecastModel.getWeatherDetailList();
            Intrinsics.checkNotNull(weatherDetailList3);
            List<WeatherDetail> weatherDetailList4 = weatherForecastModel.getWeatherDetailList();
            Intrinsics.checkNotNull(weatherDetailList4);
            weatherDetailList3.remove(weatherDetailList4.get(0));
            Context context = getContext();
            List<WeatherDetail> weatherDetailList5 = weatherForecastModel.getWeatherDetailList();
            WeatherViewModel weatherViewModel = this.n;
            WeatherDetailsAdapter weatherDetailsAdapter = null;
            if (weatherViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                weatherViewModel = null;
            }
            this.m = new WeatherDetailsAdapter(context, weatherDetailList5, weatherViewModel);
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
            WeatherDetailsAdapter weatherDetailsAdapter2 = this.m;
            if (weatherDetailsAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
            } else {
                weatherDetailsAdapter = weatherDetailsAdapter2;
            }
            recyclerView.setAdapter(weatherDetailsAdapter);
        }
    }
}
