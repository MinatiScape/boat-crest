package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class StrapListItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clItem;
    @NonNull
    public final ConstraintLayout clUpdateStrap;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final ImageView imgvExpand;
    @NonNull
    public final ImageView imgvStrap;
    @NonNull
    public final TextView tvDeRegister;
    @NonNull
    public final TextView tvFriendlyName;
    @NonNull
    public final TextView tvUpdateFriendlyName;
    @NonNull
    public final CardView vitalsCardview;

    public StrapListItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Guideline guideline, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, CardView cardView) {
        super(obj, view, i);
        this.clItem = constraintLayout;
        this.clUpdateStrap = constraintLayout2;
        this.guideline1 = guideline;
        this.imgvExpand = imageView;
        this.imgvStrap = imageView2;
        this.tvDeRegister = textView;
        this.tvFriendlyName = textView2;
        this.tvUpdateFriendlyName = textView3;
        this.vitalsCardview = cardView;
    }

    public static StrapListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static StrapListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static StrapListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (StrapListItemBinding) ViewDataBinding.bind(obj, view, R.layout.strap_list_item);
    }

    @NonNull
    @Deprecated
    public static StrapListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (StrapListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.strap_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static StrapListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static StrapListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (StrapListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.strap_list_item, null, false, obj);
    }
}
