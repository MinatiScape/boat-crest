package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ListItemDeviceFeatureMyWatchLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clEven;
    @NonNull
    public final ConstraintLayout clOdd;
    @NonNull
    public final View evenLayout1;
    @NonNull
    public final View evenLayout2;
    @NonNull
    public final LayoutDashboardDoMoreWithYourWatchGridItemBinding oddLayout;

    public ListItemDeviceFeatureMyWatchLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, View view2, View view3, LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding) {
        super(obj, view, i);
        this.clEven = constraintLayout;
        this.clOdd = constraintLayout2;
        this.evenLayout1 = view2;
        this.evenLayout2 = view3;
        this.oddLayout = layoutDashboardDoMoreWithYourWatchGridItemBinding;
    }

    public static ListItemDeviceFeatureMyWatchLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemDeviceFeatureMyWatchLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemDeviceFeatureMyWatchLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemDeviceFeatureMyWatchLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_device_feature_my_watch_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemDeviceFeatureMyWatchLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemDeviceFeatureMyWatchLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_device_feature_my_watch_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemDeviceFeatureMyWatchLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemDeviceFeatureMyWatchLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemDeviceFeatureMyWatchLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_device_feature_my_watch_layout, null, false, obj);
    }
}
