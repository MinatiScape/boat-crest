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
public abstract class FragmentSensAiFtu2Binding extends ViewDataBinding {
    @NonNull
    public final Button btnNext;
    @NonNull
    public final ImageView ivFtu;
    @NonNull
    public final TextView tvFtu1;
    @NonNull
    public final TextView tvFtu2;

    public FragmentSensAiFtu2Binding(Object obj, View view, int i, Button button, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnNext = button;
        this.ivFtu = imageView;
        this.tvFtu1 = textView;
        this.tvFtu2 = textView2;
    }

    public static FragmentSensAiFtu2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSensAiFtu2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSensAiFtu2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSensAiFtu2Binding) ViewDataBinding.bind(obj, view, R.layout.fragment_sens_ai_ftu_2);
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiFtu2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSensAiFtu2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_ftu_2, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSensAiFtu2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSensAiFtu2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSensAiFtu2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sens_ai_ftu_2, null, false, obj);
    }
}
