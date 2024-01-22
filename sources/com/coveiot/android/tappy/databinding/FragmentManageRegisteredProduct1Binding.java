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
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentManageRegisteredProduct1Binding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clManageCard;
    @NonNull
    public final ConstraintLayout clManageStrap;
    @NonNull
    public final CardView cvManageCard;
    @NonNull
    public final CardView cvManageStrap;
    @NonNull
    public final ImageView imgvDownCard;
    @NonNull
    public final ImageView imgvDownStrap;
    @NonNull
    public final ImageView imgvUpCard;
    @NonNull
    public final ImageView imgvUpStrap;
    @NonNull
    public final TextView tvAddCard;
    @NonNull
    public final TextView tvCardManage;
    @NonNull
    public final TextView tvCardManageDetails;
    @NonNull
    public final TextView tvDeleteCard;
    @NonNull
    public final TextView tvDeregisterStrap;
    @NonNull
    public final TextView tvResumeCard;
    @NonNull
    public final TextView tvStrapManage;
    @NonNull
    public final TextView tvStrapManageDetails;
    @NonNull
    public final TextView tvSuspendCard;

    public FragmentManageRegisteredProduct1Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, CardView cardView, CardView cardView2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        super(obj, view, i);
        this.clManageCard = constraintLayout;
        this.clManageStrap = constraintLayout2;
        this.cvManageCard = cardView;
        this.cvManageStrap = cardView2;
        this.imgvDownCard = imageView;
        this.imgvDownStrap = imageView2;
        this.imgvUpCard = imageView3;
        this.imgvUpStrap = imageView4;
        this.tvAddCard = textView;
        this.tvCardManage = textView2;
        this.tvCardManageDetails = textView3;
        this.tvDeleteCard = textView4;
        this.tvDeregisterStrap = textView5;
        this.tvResumeCard = textView6;
        this.tvStrapManage = textView7;
        this.tvStrapManageDetails = textView8;
        this.tvSuspendCard = textView9;
    }

    public static FragmentManageRegisteredProduct1Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentManageRegisteredProduct1Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentManageRegisteredProduct1Binding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentManageRegisteredProduct1Binding) ViewDataBinding.bind(obj, view, R.layout.fragment_manage_registered_product1);
    }

    @NonNull
    @Deprecated
    public static FragmentManageRegisteredProduct1Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentManageRegisteredProduct1Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_manage_registered_product1, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentManageRegisteredProduct1Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentManageRegisteredProduct1Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentManageRegisteredProduct1Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_manage_registered_product1, null, false, obj);
    }
}
