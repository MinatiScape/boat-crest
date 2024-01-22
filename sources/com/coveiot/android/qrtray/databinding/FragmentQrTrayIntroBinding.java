package com.coveiot.android.qrtray.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.coveiot.android.qrtray.R;
/* loaded from: classes5.dex */
public abstract class FragmentQrTrayIntroBinding extends ViewDataBinding {
    @NonNull
    public final Button btnGetStarted;
    @NonNull
    public final LinearLayout dotsLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final ViewPager2 viewPager;

    public FragmentQrTrayIntroBinding(Object obj, View view, int i, Button button, LinearLayout linearLayout, View view2, TextView textView, TextView textView2, ViewPager2 viewPager2) {
        super(obj, view, i);
        this.btnGetStarted = button;
        this.dotsLayout = linearLayout;
        this.toolbar = view2;
        this.tvHeader = textView;
        this.tvInfo = textView2;
        this.viewPager = viewPager2;
    }

    public static FragmentQrTrayIntroBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentQrTrayIntroBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentQrTrayIntroBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentQrTrayIntroBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_qr_tray_intro);
    }

    @NonNull
    @Deprecated
    public static FragmentQrTrayIntroBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentQrTrayIntroBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_qr_tray_intro, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentQrTrayIntroBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentQrTrayIntroBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentQrTrayIntroBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_qr_tray_intro, null, false, obj);
    }
}
