package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
public abstract class FragmentRegisteredProductItemNewBinding extends ViewDataBinding {
    @NonNull
    public final Button btnAddCard;
    @NonNull
    public final ConstraintLayout clCardNumber;
    @NonNull
    public final ConstraintLayout clFriendlyName;
    @NonNull
    public final ConstraintLayout clLast4Number;
    @NonNull
    public final ConstraintLayout clStrapImage;
    @NonNull
    public final ConstraintLayout cvCardDetail;
    @NonNull
    public final ConstraintLayout cvProductDetail;
    @NonNull
    public final CardView cvStrapDetail;
    @NonNull
    public final CardView cvVirtualCard;
    @NonNull
    public final Guideline guideline3;
    @NonNull
    public final Guideline guideline4;
    @NonNull
    public final ImageView imageViewHolder;
    @NonNull
    public final ImageButton imgvEdtFriendlyName;
    @NonNull
    public final ImageView imgvStrap;
    @NonNull
    public final ImageView imgvVirtualCard;
    @NonNull
    public final TextView tvDynamicAccountNumber;
    @NonNull
    public final TextView tvLast4DynamicNumber;
    @NonNull
    public final TextView tvLast4StaticNumber;
    @NonNull
    public final TextView tvStaticAccountNumber;
    @NonNull
    public final TextView tvStrapAddDetails;
    @NonNull
    public final TextView tvStrapName;
    @NonNull
    public final TextView tvVirtualCard;
    @NonNull
    public final TextView tvVirtualCardStatus;

    public FragmentRegisteredProductItemNewBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, CardView cardView, CardView cardView2, Guideline guideline, Guideline guideline2, ImageView imageView, ImageButton imageButton, ImageView imageView2, ImageView imageView3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        super(obj, view, i);
        this.btnAddCard = button;
        this.clCardNumber = constraintLayout;
        this.clFriendlyName = constraintLayout2;
        this.clLast4Number = constraintLayout3;
        this.clStrapImage = constraintLayout4;
        this.cvCardDetail = constraintLayout5;
        this.cvProductDetail = constraintLayout6;
        this.cvStrapDetail = cardView;
        this.cvVirtualCard = cardView2;
        this.guideline3 = guideline;
        this.guideline4 = guideline2;
        this.imageViewHolder = imageView;
        this.imgvEdtFriendlyName = imageButton;
        this.imgvStrap = imageView2;
        this.imgvVirtualCard = imageView3;
        this.tvDynamicAccountNumber = textView;
        this.tvLast4DynamicNumber = textView2;
        this.tvLast4StaticNumber = textView3;
        this.tvStaticAccountNumber = textView4;
        this.tvStrapAddDetails = textView5;
        this.tvStrapName = textView6;
        this.tvVirtualCard = textView7;
        this.tvVirtualCardStatus = textView8;
    }

    public static FragmentRegisteredProductItemNewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentRegisteredProductItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentRegisteredProductItemNewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentRegisteredProductItemNewBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_registered_product_item_new);
    }

    @NonNull
    @Deprecated
    public static FragmentRegisteredProductItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentRegisteredProductItemNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_registered_product_item_new, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentRegisteredProductItemNewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentRegisteredProductItemNewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentRegisteredProductItemNewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_registered_product_item_new, null, false, obj);
    }
}
