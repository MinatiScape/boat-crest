package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class TransactionDetailPopupBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clAmount;
    @NonNull
    public final ConstraintLayout clIndustryCategoryName;
    @NonNull
    public final ConstraintLayout clIndustryName;
    @NonNull
    public final ConstraintLayout clTransactionType;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final ImageButton imgvClose;
    @NonNull
    public final TextView tvIndustryCategoryName;
    @NonNull
    public final TextView tvIndustryName;
    @NonNull
    public final TextView tvMerchantCity;
    @NonNull
    public final TextView tvMerchantName;
    @NonNull
    public final TextView tvTransactionAmount;
    @NonNull
    public final TextView tvTransactionDt;
    @NonNull
    public final TextView tvTransactionStatus;
    @NonNull
    public final TextView tvTransactionType;

    public TransactionDetailPopupBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, Guideline guideline, ImageButton imageButton, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        super(obj, view, i);
        this.clAmount = constraintLayout;
        this.clIndustryCategoryName = constraintLayout2;
        this.clIndustryName = constraintLayout3;
        this.clTransactionType = constraintLayout4;
        this.guideline1 = guideline;
        this.imgvClose = imageButton;
        this.tvIndustryCategoryName = textView;
        this.tvIndustryName = textView2;
        this.tvMerchantCity = textView3;
        this.tvMerchantName = textView4;
        this.tvTransactionAmount = textView5;
        this.tvTransactionDt = textView6;
        this.tvTransactionStatus = textView7;
        this.tvTransactionType = textView8;
    }

    public static TransactionDetailPopupBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TransactionDetailPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TransactionDetailPopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TransactionDetailPopupBinding) ViewDataBinding.bind(obj, view, R.layout.transaction_detail_popup);
    }

    @NonNull
    @Deprecated
    public static TransactionDetailPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TransactionDetailPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.transaction_detail_popup, viewGroup, z, obj);
    }

    @NonNull
    public static TransactionDetailPopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TransactionDetailPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TransactionDetailPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.transaction_detail_popup, null, false, obj);
    }
}
