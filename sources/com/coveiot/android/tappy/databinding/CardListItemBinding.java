package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class CardListItemBinding extends ViewDataBinding {
    @NonNull
    public final CardView cvCardItem;
    @NonNull
    public final ImageView imgvCardType;
    @NonNull
    public final ImageView imgvStrapImage;
    @NonNull
    public final TextView tvDynamicAccountNumber;
    @NonNull
    public final TextView tvStaticAccountNumber;
    @NonNull
    public final TextView tvStrapName;

    public CardListItemBinding(Object obj, View view, int i, CardView cardView, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.cvCardItem = cardView;
        this.imgvCardType = imageView;
        this.imgvStrapImage = imageView2;
        this.tvDynamicAccountNumber = textView;
        this.tvStaticAccountNumber = textView2;
        this.tvStrapName = textView3;
    }

    public static CardListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CardListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CardListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CardListItemBinding) ViewDataBinding.bind(obj, view, R.layout.card_list_item);
    }

    @NonNull
    @Deprecated
    public static CardListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CardListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.card_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static CardListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CardListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CardListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.card_list_item, null, false, obj);
    }
}
