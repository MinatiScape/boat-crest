package com.coveiot.android.weather.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import com.coveiot.android.weather.BR;
import com.coveiot.android.weather.R;
/* loaded from: classes8.dex */
public class FragmentWeatherSettingBindingImpl extends FragmentWeatherSettingBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.ll_enable_weather, 4);
        sparseIntArray.put(R.id.textViewEnable, 5);
        sparseIntArray.put(R.id.weather_info_txt, 6);
        sparseIntArray.put(R.id.ll_weather_unit, 7);
        sparseIntArray.put(R.id.textViewUnit, 8);
        sparseIntArray.put(R.id.rl_metric, 9);
        sparseIntArray.put(R.id.text_metric, 10);
        sparseIntArray.put(R.id.textView5, 11);
        sparseIntArray.put(R.id.rl_imperial, 12);
        sparseIntArray.put(R.id.text_imperial, 13);
        sparseIntArray.put(R.id.textView6, 14);
        sparseIntArray.put(R.id.tvLocationPermission, 15);
        sparseIntArray.put(R.id.btn_ok, 16);
    }

    public FragmentWeatherSettingBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 17, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        boolean z;
        boolean z2;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Boolean bool = this.mIsMetricUnitData;
        Boolean bool2 = this.mIsWeatherEnableData;
        int i = ((5 & j2) > 0L ? 1 : ((5 & j2) == 0L ? 0 : -1));
        if (i != 0) {
            z = ViewDataBinding.safeUnbox(bool);
            z2 = ViewDataBinding.safeUnbox(Boolean.valueOf(!z));
        } else {
            z = false;
            z2 = false;
        }
        int i2 = ((j2 & 6) > 0L ? 1 : ((j2 & 6) == 0L ? 0 : -1));
        boolean safeUnbox = i2 != 0 ? ViewDataBinding.safeUnbox(bool2) : false;
        if (i != 0) {
            CompoundButtonBindingAdapter.setChecked(this.rbImperial, z2);
            CompoundButtonBindingAdapter.setChecked(this.rbMetric, z);
        }
        if (i2 != 0) {
            CompoundButtonBindingAdapter.setChecked(this.switchWeather, safeUnbox);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.weather.databinding.FragmentWeatherSettingBinding
    public void setIsMetricUnitData(@Nullable Boolean bool) {
        this.mIsMetricUnitData = bool;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.isMetricUnitData);
        super.requestRebind();
    }

    @Override // com.coveiot.android.weather.databinding.FragmentWeatherSettingBinding
    public void setIsWeatherEnableData(@Nullable Boolean bool) {
        this.mIsWeatherEnableData = bool;
        synchronized (this) {
            this.i |= 2;
        }
        notifyPropertyChanged(BR.isWeatherEnableData);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.isMetricUnitData == i) {
            setIsMetricUnitData((Boolean) obj);
        } else if (BR.isWeatherEnableData != i) {
            return false;
        } else {
            setIsWeatherEnableData((Boolean) obj);
        }
        return true;
    }

    public FragmentWeatherSettingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[16], (RelativeLayout) objArr[4], (RelativeLayout) objArr[7], (RadioButton) objArr[3], (RadioButton) objArr[2], (RelativeLayout) objArr[12], (RelativeLayout) objArr[9], (SwitchCompat) objArr[1], (TextView) objArr[13], (TextView) objArr[10], (TextView) objArr[11], (TextView) objArr[14], (TextView) objArr[5], (TextView) objArr[8], (TextView) objArr[15], (TextView) objArr[6]);
        this.i = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        this.rbImperial.setTag(null);
        this.rbMetric.setTag(null);
        this.switchWeather.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
