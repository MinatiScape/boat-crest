package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class FragmentNavigatFtuBinding extends ViewDataBinding {
    @NonNull
    public final Button btnNext;
    @NonNull
    public final LinearLayout llNavigationFtuWebviewContainer;
    @NonNull
    public final ConstraintLayout navigationFtu;
    @NonNull
    public final TextView tvEffortlesslySend;
    @NonNull
    public final TextView tvIntroducingNavigation;
    @NonNull
    public final TextView tvOverview;
    @NonNull
    public final TextView tvSearchYourDesired;
    @NonNull
    public final TextView tvSlNoSearchYourDesired;
    @NonNull
    public final TextView tvSlNoTvEffortlesslySend;
    @NonNull
    public final TextView tvSlNoTvThenFollow;
    @NonNull
    public final TextView tvThenFollow;
    @NonNull
    public final WebView webviewTbtNavigation;

    public FragmentNavigatFtuBinding(Object obj, View view, int i, Button button, LinearLayout linearLayout, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, WebView webView) {
        super(obj, view, i);
        this.btnNext = button;
        this.llNavigationFtuWebviewContainer = linearLayout;
        this.navigationFtu = constraintLayout;
        this.tvEffortlesslySend = textView;
        this.tvIntroducingNavigation = textView2;
        this.tvOverview = textView3;
        this.tvSearchYourDesired = textView4;
        this.tvSlNoSearchYourDesired = textView5;
        this.tvSlNoTvEffortlesslySend = textView6;
        this.tvSlNoTvThenFollow = textView7;
        this.tvThenFollow = textView8;
        this.webviewTbtNavigation = webView;
    }

    public static FragmentNavigatFtuBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentNavigatFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentNavigatFtuBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentNavigatFtuBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_navigat_ftu);
    }

    @NonNull
    @Deprecated
    public static FragmentNavigatFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentNavigatFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_navigat_ftu, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentNavigatFtuBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentNavigatFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentNavigatFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_navigat_ftu, null, false, obj);
    }
}
