package com.mappls.sdk.navigation.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.R;
/* loaded from: classes11.dex */
public abstract class LayoutAlertViewBinding extends ViewDataBinding {
    @NonNull
    public final ImageView reportIcon;
    @NonNull
    public final LinearLayout reportLayout;
    @NonNull
    public final TextView textReportCategoryName;
    @NonNull
    public final TextView textReportDistanceLeft;

    public LayoutAlertViewBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.reportIcon = imageView;
        this.reportLayout = linearLayout;
        this.textReportCategoryName = textView;
        this.textReportDistanceLeft = textView2;
    }

    public static LayoutAlertViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutAlertViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (LayoutAlertViewBinding) ViewDataBinding.bind(obj, view, R.layout.layout_alert_view);
    }

    @NonNull
    public static LayoutAlertViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutAlertViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static LayoutAlertViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (LayoutAlertViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_alert_view, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static LayoutAlertViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (LayoutAlertViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.layout_alert_view, null, false, obj);
    }
}
