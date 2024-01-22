package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class TransactionDetailPopupnewBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivTransactionStatus;
    @NonNull
    public final TextView tvLast4;
    @NonNull
    public final TextView tvMerchantCity;
    @NonNull
    public final TextView tvMerchantName;
    @NonNull
    public final TextView tvPaidFromStrap;
    @NonNull
    public final TextView tvPaidFromTitle;
    @NonNull
    public final TextView tvPaidStatus;
    @NonNull
    public final TextView tvPaidTime;
    @NonNull
    public final TextView tvPaidToTitle;
    @NonNull
    public final TextView tvTransactionAmount;
    @NonNull
    public final TextView tvTransactionAmountInWords;
    @NonNull
    public final TextView tvTransactionDt;
    @NonNull
    public final TextView tvTransactionId;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;
    @NonNull
    public final View view3;
    @NonNull
    public final View view4;

    public TransactionDetailPopupnewBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, View view2, View view3, View view4, View view5) {
        super(obj, view, i);
        this.ivTransactionStatus = imageView;
        this.tvLast4 = textView;
        this.tvMerchantCity = textView2;
        this.tvMerchantName = textView3;
        this.tvPaidFromStrap = textView4;
        this.tvPaidFromTitle = textView5;
        this.tvPaidStatus = textView6;
        this.tvPaidTime = textView7;
        this.tvPaidToTitle = textView8;
        this.tvTransactionAmount = textView9;
        this.tvTransactionAmountInWords = textView10;
        this.tvTransactionDt = textView11;
        this.tvTransactionId = textView12;
        this.view1 = view2;
        this.view2 = view3;
        this.view3 = view4;
        this.view4 = view5;
    }

    public static TransactionDetailPopupnewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TransactionDetailPopupnewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TransactionDetailPopupnewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TransactionDetailPopupnewBinding) ViewDataBinding.bind(obj, view, R.layout.transaction_detail_popupnew);
    }

    @NonNull
    @Deprecated
    public static TransactionDetailPopupnewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TransactionDetailPopupnewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.transaction_detail_popupnew, viewGroup, z, obj);
    }

    @NonNull
    public static TransactionDetailPopupnewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TransactionDetailPopupnewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TransactionDetailPopupnewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.transaction_detail_popupnew, null, false, obj);
    }
}
