package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class ActivityAddNfcStrapBinding extends ViewDataBinding {
    @NonNull
    public final TextView addLater;
    @NonNull
    public final Button btnProceedAdd;
    @NonNull
    public final ImageView failureImage;
    @NonNull
    public final FrameLayout fragmentContainer;
    @NonNull
    public final ConstraintLayout strapAddSuccessStatusLayout;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView updateStatusTv;

    public ActivityAddNfcStrapBinding(Object obj, View view, int i, TextView textView, Button button, ImageView imageView, FrameLayout frameLayout, ConstraintLayout constraintLayout, View view2, TextView textView2) {
        super(obj, view, i);
        this.addLater = textView;
        this.btnProceedAdd = button;
        this.failureImage = imageView;
        this.fragmentContainer = frameLayout;
        this.strapAddSuccessStatusLayout = constraintLayout;
        this.toolbar = view2;
        this.updateStatusTv = textView2;
    }

    public static ActivityAddNfcStrapBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAddNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddNfcStrapBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAddNfcStrapBinding) ViewDataBinding.bind(obj, view, R.layout.activity_add_nfc_strap);
    }

    @NonNull
    @Deprecated
    public static ActivityAddNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAddNfcStrapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_nfc_strap, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAddNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAddNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAddNfcStrapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_nfc_strap, null, false, obj);
    }
}
