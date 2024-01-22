package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentAddNfcStrapBinding extends ViewDataBinding {
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final ImageView imageViewHolder;

    public FragmentAddNfcStrapBinding(Object obj, View view, int i, Guideline guideline, ImageView imageView) {
        super(obj, view, i);
        this.guideline1 = guideline;
        this.imageViewHolder = imageView;
    }

    public static FragmentAddNfcStrapBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentAddNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAddNfcStrapBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentAddNfcStrapBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_add_nfc_strap);
    }

    @NonNull
    @Deprecated
    public static FragmentAddNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentAddNfcStrapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_add_nfc_strap, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentAddNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentAddNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentAddNfcStrapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_add_nfc_strap, null, false, obj);
    }
}
