package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentSensAiFtu1Binding extends ViewDataBinding {
    @NonNull
    public final Button btnNext;
    @NonNull
    public final ImageView ivFtu;
    @NonNull
    public final TextView tvFtu1;
    @NonNull
    public final TextView tvFtu2;
    @NonNull
    public final TextView tvSkip;

    public FragmentSensAiFtu1Binding(Object obj, View view, int i, Button button, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnNext = button;
        this.ivFtu = imageView;
        this.tvFtu1 = textView;
        this.tvFtu2 = textView2;
        this.tvSkip = textView3;
    }

    public static FragmentSensAiFtu1Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSensAiFtu1Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSensAiFtu1Binding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSensAiFtu1Binding) ViewDataBinding.bind(obj, view, R.layout.fragment_sens_ai_ftu_1);
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiFtu1Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSensAiFtu1Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_ftu_1, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSensAiFtu1Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiFtu1Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSensAiFtu1Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_ftu_1, null, false, obj);
    }
}
