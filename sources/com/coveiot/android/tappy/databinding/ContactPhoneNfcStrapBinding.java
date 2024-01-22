package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class ContactPhoneNfcStrapBinding extends ViewDataBinding {
    @NonNull
    public final ImageView addStrapProgress;
    @NonNull
    public final ConstraintLayout addStrapProgressLayout;
    @NonNull
    public final ConstraintLayout addStrapStatusLayout;
    @NonNull
    public final Button btnTryAgain;
    @NonNull
    public final ConstraintLayout clDataTransferStatus;
    @NonNull
    public final ConstraintLayout clStrapStatus;
    @NonNull
    public final ConstraintLayout contactPhoneNfcStrap;
    @NonNull
    public final ImageView failureImage;
    @NonNull
    public final ImageView ivStrapNfcNearby;
    @NonNull
    public final TextView tvDataTransferStatusSucess;
    @NonNull
    public final TextView tvStrapAddStatus;
    @NonNull
    public final TextView tvStrapAddSuccess;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final TextView updateStatusTv;

    public ContactPhoneNfcStrapBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Button button, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.addStrapProgress = imageView;
        this.addStrapProgressLayout = constraintLayout;
        this.addStrapStatusLayout = constraintLayout2;
        this.btnTryAgain = button;
        this.clDataTransferStatus = constraintLayout3;
        this.clStrapStatus = constraintLayout4;
        this.contactPhoneNfcStrap = constraintLayout5;
        this.failureImage = imageView2;
        this.ivStrapNfcNearby = imageView3;
        this.tvDataTransferStatusSucess = textView;
        this.tvStrapAddStatus = textView2;
        this.tvStrapAddSuccess = textView3;
        this.tvTitle = textView4;
        this.updateStatusTv = textView5;
    }

    public static ContactPhoneNfcStrapBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ContactPhoneNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ContactPhoneNfcStrapBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ContactPhoneNfcStrapBinding) ViewDataBinding.bind(obj, view, R.layout.contact_phone_nfc_strap);
    }

    @NonNull
    @Deprecated
    public static ContactPhoneNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ContactPhoneNfcStrapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.contact_phone_nfc_strap, viewGroup, z, obj);
    }

    @NonNull
    public static ContactPhoneNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ContactPhoneNfcStrapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ContactPhoneNfcStrapBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.contact_phone_nfc_strap, null, false, obj);
    }
}
