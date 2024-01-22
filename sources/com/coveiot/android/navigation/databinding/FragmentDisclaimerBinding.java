package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class FragmentDisclaimerBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAccept;
    @NonNull
    public final WebView webviewTbtNavigation;

    public FragmentDisclaimerBinding(Object obj, View view, int i, Button button, WebView webView) {
        super(obj, view, i);
        this.btnAccept = button;
        this.webviewTbtNavigation = webView;
    }

    public static FragmentDisclaimerBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentDisclaimerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDisclaimerBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentDisclaimerBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_disclaimer);
    }

    @NonNull
    @Deprecated
    public static FragmentDisclaimerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentDisclaimerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_disclaimer, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentDisclaimerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentDisclaimerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentDisclaimerBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_disclaimer, null, false, obj);
    }
}
