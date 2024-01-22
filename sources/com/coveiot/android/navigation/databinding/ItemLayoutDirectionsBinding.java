package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ItemLayoutDirectionsBinding extends ViewDataBinding {
    @NonNull
    public final ImageView ivDirectionImage;
    @NonNull
    public final TextView tvDirectionAdvice;
    @NonNull
    public final TextView tvTurnDistance;
    @NonNull
    public final View vLine;

    public ItemLayoutDirectionsBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.ivDirectionImage = imageView;
        this.tvDirectionAdvice = textView;
        this.tvTurnDistance = textView2;
        this.vLine = view2;
    }

    public static ItemLayoutDirectionsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutDirectionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemLayoutDirectionsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ItemLayoutDirectionsBinding) ViewDataBinding.bind(obj, view, R.layout.item_layout_directions);
    }

    @NonNull
    @Deprecated
    public static ItemLayoutDirectionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ItemLayoutDirectionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_layout_directions, viewGroup, z, obj);
    }

    @NonNull
    public static ItemLayoutDirectionsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ItemLayoutDirectionsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ItemLayoutDirectionsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_layout_directions, null, false, obj);
    }
}
