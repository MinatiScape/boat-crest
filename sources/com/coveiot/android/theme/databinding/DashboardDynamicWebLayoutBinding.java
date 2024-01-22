package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class DashboardDynamicWebLayoutBinding extends ViewDataBinding {
    @NonNull
    public final WebView dynamicWebView;
    @NonNull
    public final LinearLayout webViewContainer;

    public DashboardDynamicWebLayoutBinding(Object obj, View view, int i, WebView webView, LinearLayout linearLayout) {
        super(obj, view, i);
        this.dynamicWebView = webView;
        this.webViewContainer = linearLayout;
    }

    public static DashboardDynamicWebLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DashboardDynamicWebLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DashboardDynamicWebLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DashboardDynamicWebLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.dashboard_dynamic_web_layout);
    }

    @NonNull
    @Deprecated
    public static DashboardDynamicWebLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (DashboardDynamicWebLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dashboard_dynamic_web_layout, viewGroup, z, obj);
    }

    @NonNull
    public static DashboardDynamicWebLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static DashboardDynamicWebLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (DashboardDynamicWebLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dashboard_dynamic_web_layout, null, false, obj);
    }
}
