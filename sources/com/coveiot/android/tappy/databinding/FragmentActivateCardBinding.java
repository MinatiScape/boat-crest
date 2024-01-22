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
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentActivateCardBinding extends ViewDataBinding {
    @NonNull
    public final Button btnActivateCard;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final ImageView imageViewHolder;
    @NonNull
    public final ImageView imageViewHolder1;
    @NonNull
    public final ConstraintLayout imageViewHolderLayout;
    @NonNull
    public final TextView tvDynamicAccountNumber;
    @NonNull
    public final TextView tvYouNeedToActivate;

    public FragmentActivateCardBinding(Object obj, View view, int i, Button button, Guideline guideline, ImageView imageView, ImageView imageView2, ConstraintLayout constraintLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.btnActivateCard = button;
        this.guideline1 = guideline;
        this.imageViewHolder = imageView;
        this.imageViewHolder1 = imageView2;
        this.imageViewHolderLayout = constraintLayout;
        this.tvDynamicAccountNumber = textView;
        this.tvYouNeedToActivate = textView2;
    }

    public static FragmentActivateCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentActivateCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentActivateCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentActivateCardBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_activate_card);
    }

    @NonNull
    @Deprecated
    public static FragmentActivateCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentActivateCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_activate_card, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentActivateCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentActivateCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentActivateCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_activate_card, null, false, obj);
    }
}
