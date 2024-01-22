package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityBoatCoinsWebViewerBinding extends ViewDataBinding {
    @NonNull
    public final WebView webViewBoatCoins;

    public ActivityBoatCoinsWebViewerBinding(Object obj, View view, int i, WebView webView) {
        super(obj, view, i);
        this.webViewBoatCoins = webView;
    }

    public static ActivityBoatCoinsWebViewerBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBoatCoinsWebViewerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBoatCoinsWebViewerBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityBoatCoinsWebViewerBinding) ViewDataBinding.bind(obj, view, R.layout.activity_boat_coins_web_viewer);
    }

    @NonNull
    @Deprecated
    public static ActivityBoatCoinsWebViewerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityBoatCoinsWebViewerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_boat_coins_web_viewer, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityBoatCoinsWebViewerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityBoatCoinsWebViewerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityBoatCoinsWebViewerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_boat_coins_web_viewer, null, false, obj);
    }
}
