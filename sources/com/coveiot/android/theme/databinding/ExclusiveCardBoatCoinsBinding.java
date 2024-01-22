package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ExclusiveCardBoatCoinsBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivIcon;

    public ExclusiveCardBoatCoinsBinding(Object obj, View view, int i, ImageView imageView) {
        super(obj, view, i);
        this.ivIcon = imageView;
    }

    public static ExclusiveCardBoatCoinsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ExclusiveCardBoatCoinsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ExclusiveCardBoatCoinsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ExclusiveCardBoatCoinsBinding) ViewDataBinding.bind(obj, view, R.layout.exclusive_card_boat_coins);
    }

    @NonNull
    @Deprecated
    public static ExclusiveCardBoatCoinsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ExclusiveCardBoatCoinsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.exclusive_card_boat_coins, viewGroup, z, obj);
    }

    @NonNull
    public static ExclusiveCardBoatCoinsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ExclusiveCardBoatCoinsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ExclusiveCardBoatCoinsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.exclusive_card_boat_coins, null, false, obj);
    }
}
