package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityBatterySaverModeNewBinding extends ViewDataBinding {
    @NonNull
    public final LinearLayout advModeDesc1;
    @NonNull
    public final LinearLayout advModeDesc2;
    @NonNull
    public final TextView batterySaverModeInformation;
    @NonNull
    public final ConstraintLayout clBatterySaverModeStandard;
    @NonNull
    public final ConstraintLayout clBsmAdvanced;
    @NonNull
    public final LinearLayout stdModeDesc1;
    @NonNull
    public final LinearLayout stdModeDesc2;
    @NonNull
    public final LinearLayout stdModeDesc3;
    @NonNull
    public final LinearLayout stdModeDesc4;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvBsmAdvanced;
    @NonNull
    public final TextView tvBsmStandard;
    @NonNull
    public final View view;
    @NonNull
    public final View view2;

    public ActivityBatterySaverModeNewBinding(Object obj, View view, int i, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, View view2, TextView textView2, TextView textView3, View view3, View view4) {
        super(obj, view, i);
        this.advModeDesc1 = linearLayout;
        this.advModeDesc2 = linearLayout2;
        this.batterySaverModeInformation = textView;
        this.clBatterySaverModeStandard = constraintLayout;
        this.clBsmAdvanced = constraintLayout2;
        this.stdModeDesc1 = linearLayout3;
        this.stdModeDesc2 = linearLayout4;
        this.stdModeDesc3 = linearLayout5;
        this.stdModeDesc4 = linearLayout6;
        this.toolbar = view2;
        this.tvBsmAdvanced = textView2;
        this.tvBsmStandard = textView3;
        this.view = view3;
        this.view2 = view4;
    }

    public static ActivityBatterySaverModeNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBatterySaverModeNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBatterySaverModeNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityBatterySaverModeNewBinding) ViewDataBinding.bind(obj, view, R.layout.activity_battery_saver_mode_new);
    }

    @NonNull
    @Deprecated
    public static ActivityBatterySaverModeNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityBatterySaverModeNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_battery_saver_mode_new, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityBatterySaverModeNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityBatterySaverModeNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityBatterySaverModeNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_battery_saver_mode_new, null, false, obj);
    }
}
