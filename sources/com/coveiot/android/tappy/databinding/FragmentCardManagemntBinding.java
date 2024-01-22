package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentCardManagemntBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView cardRv;
    @NonNull
    public final ImageView emptyCardView;

    public FragmentCardManagemntBinding(Object obj, View view, int i, RecyclerView recyclerView, ImageView imageView) {
        super(obj, view, i);
        this.cardRv = recyclerView;
        this.emptyCardView = imageView;
    }

    public static FragmentCardManagemntBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentCardManagemntBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCardManagemntBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentCardManagemntBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_card_managemnt);
    }

    @NonNull
    @Deprecated
    public static FragmentCardManagemntBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentCardManagemntBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_card_managemnt, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentCardManagemntBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentCardManagemntBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentCardManagemntBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_card_managemnt, null, false, obj);
    }
}
