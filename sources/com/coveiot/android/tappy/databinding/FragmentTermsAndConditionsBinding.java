package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentTermsAndConditionsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSelect;
    @NonNull
    public final TextView tvTermsAndConditions;
    @NonNull
    public final TextView tvTitle;

    public FragmentTermsAndConditionsBinding(Object obj, View view, int i, Button button, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnSelect = button;
        this.tvTermsAndConditions = textView;
        this.tvTitle = textView2;
    }

    public static FragmentTermsAndConditionsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentTermsAndConditionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentTermsAndConditionsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentTermsAndConditionsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_terms_and_conditions);
    }

    @NonNull
    @Deprecated
    public static FragmentTermsAndConditionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentTermsAndConditionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_terms_and_conditions, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentTermsAndConditionsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentTermsAndConditionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentTermsAndConditionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_terms_and_conditions, null, false, obj);
    }
}
