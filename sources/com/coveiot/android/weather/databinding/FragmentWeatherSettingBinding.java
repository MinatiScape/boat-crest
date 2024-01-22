package com.coveiot.android.weather.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.weather.R;
/* loaded from: classes8.dex */
public abstract class FragmentWeatherSettingBinding extends ViewDataBinding {
    @NonNull
    public final Button btnOk;
    @NonNull
    public final RelativeLayout llEnableWeather;
    @NonNull
    public final RelativeLayout llWeatherUnit;
    @Bindable
    public Boolean mIsMetricUnitData;
    @Bindable
    public Boolean mIsWeatherEnableData;
    @NonNull
    public final RadioButton rbImperial;
    @NonNull
    public final RadioButton rbMetric;
    @NonNull
    public final RelativeLayout rlImperial;
    @NonNull
    public final RelativeLayout rlMetric;
    @NonNull
    public final SwitchCompat switchWeather;
    @NonNull
    public final TextView textImperial;
    @NonNull
    public final TextView textMetric;
    @NonNull
    public final TextView textView5;
    @NonNull
    public final TextView textView6;
    @NonNull
    public final TextView textViewEnable;
    @NonNull
    public final TextView textViewUnit;
    @NonNull
    public final TextView tvLocationPermission;
    @NonNull
    public final TextView weatherInfoTxt;

    public FragmentWeatherSettingBinding(Object obj, View view, int i, Button button, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RadioButton radioButton, RadioButton radioButton2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, SwitchCompat switchCompat, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        super(obj, view, i);
        this.btnOk = button;
        this.llEnableWeather = relativeLayout;
        this.llWeatherUnit = relativeLayout2;
        this.rbImperial = radioButton;
        this.rbMetric = radioButton2;
        this.rlImperial = relativeLayout3;
        this.rlMetric = relativeLayout4;
        this.switchWeather = switchCompat;
        this.textImperial = textView;
        this.textMetric = textView2;
        this.textView5 = textView3;
        this.textView6 = textView4;
        this.textViewEnable = textView5;
        this.textViewUnit = textView6;
        this.tvLocationPermission = textView7;
        this.weatherInfoTxt = textView8;
    }

    public static FragmentWeatherSettingBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWeatherSettingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Boolean getIsMetricUnitData() {
        return this.mIsMetricUnitData;
    }

    @Nullable
    public Boolean getIsWeatherEnableData() {
        return this.mIsWeatherEnableData;
    }

    public abstract void setIsMetricUnitData(@Nullable Boolean bool);

    public abstract void setIsWeatherEnableData(@Nullable Boolean bool);

    @Deprecated
    public static FragmentWeatherSettingBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentWeatherSettingBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_weather_setting);
    }

    @NonNull
    @Deprecated
    public static FragmentWeatherSettingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentWeatherSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_weather_setting, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentWeatherSettingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentWeatherSettingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentWeatherSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_weather_setting, null, false, obj);
    }
}
