package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class TransactionListItemNewBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clRoot;
    @NonNull
    public final TextView tvDt;
    @NonNull
    public final TextView tvPaidTo;
    @NonNull
    public final TextView tvTransactionAmount;
    @NonNull
    public final TextView tvTransactionStatus;

    public TransactionListItemNewBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.clRoot = constraintLayout;
        this.tvDt = textView;
        this.tvPaidTo = textView2;
        this.tvTransactionAmount = textView3;
        this.tvTransactionStatus = textView4;
    }

    public static TransactionListItemNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TransactionListItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static TransactionListItemNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TransactionListItemNewBinding) ViewDataBinding.bind(obj, view, R.layout.transaction_list_item_new);
    }

    @NonNull
    @Deprecated
    public static TransactionListItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TransactionListItemNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.transaction_list_item_new, viewGroup, z, obj);
    }

    @NonNull
    public static TransactionListItemNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TransactionListItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TransactionListItemNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.transaction_list_item_new, null, false, obj);
    }
}
