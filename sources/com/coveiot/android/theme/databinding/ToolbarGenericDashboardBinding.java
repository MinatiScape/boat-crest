package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ToolbarGenericDashboardBinding extends ViewDataBinding {
    @NonNull
    public final TextView boatCoinsValue;
    @NonNull
    public final ConstraintLayout clWeatherDataDash;
    @NonNull
    public final ConstraintLayout clboAtCoins;
    @NonNull
    public final ImageView imgVLogo;
    @NonNull
    public final ImageView imgVProfilePic;
    @NonNull
    public final ImageView imgWeatherDash;
    @NonNull
    public final Toolbar toolbarDashboard;
    @NonNull
    public final TextView txtWeatherDash;
    @NonNull
    public final TextView txtWeatherUnitDash;

    public ToolbarGenericDashboardBinding(Object obj, View view, int i, TextView textView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, ImageView imageView3, Toolbar toolbar, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.boatCoinsValue = textView;
        this.clWeatherDataDash = constraintLayout;
        this.clboAtCoins = constraintLayout2;
        this.imgVLogo = imageView;
        this.imgVProfilePic = imageView2;
        this.imgWeatherDash = imageView3;
        this.toolbarDashboard = toolbar;
        this.txtWeatherDash = textView2;
        this.txtWeatherUnitDash = textView3;
    }

    public static ToolbarGenericDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ToolbarGenericDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ToolbarGenericDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ToolbarGenericDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.toolbar_generic_dashboard);
    }

    @NonNull
    @Deprecated
    public static ToolbarGenericDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ToolbarGenericDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.toolbar_generic_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static ToolbarGenericDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ToolbarGenericDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ToolbarGenericDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.toolbar_generic_dashboard, null, false, obj);
    }
}
