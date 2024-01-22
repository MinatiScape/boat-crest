package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentSelectBankBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView rvBank;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvCreateAccount;
    @NonNull
    public final TextView tvNoAccountInfo;
    @NonNull
    public final TextView tvNote;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final View view;

    public FragmentSelectBankBinding(Object obj, View view, int i, RecyclerView recyclerView, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view3) {
        super(obj, view, i);
        this.rvBank = recyclerView;
        this.toolbar = view2;
        this.tvCreateAccount = textView;
        this.tvNoAccountInfo = textView2;
        this.tvNote = textView3;
        this.tvTitle = textView4;
        this.view = view3;
    }

    public static FragmentSelectBankBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSelectBankBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSelectBankBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSelectBankBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_select_bank);
    }

    @NonNull
    @Deprecated
    public static FragmentSelectBankBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSelectBankBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_select_bank, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSelectBankBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSelectBankBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSelectBankBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_select_bank, null, false, obj);
    }
}
