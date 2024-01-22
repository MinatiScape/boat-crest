package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentCardDetailsBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSaveCard;
    @NonNull
    public final ConstraintLayout clCardCvv;
    @NonNull
    public final ConstraintLayout clCardExpiry;
    @NonNull
    public final ConstraintLayout clCardName;
    @NonNull
    public final ConstraintLayout clCardNumber;
    @NonNull
    public final AppCompatEditText edtCardName;
    @NonNull
    public final AppCompatEditText edtCardNumber;
    @NonNull
    public final AppCompatEditText edtCvv;
    @NonNull
    public final AppCompatEditText edtExpiryMm;
    @NonNull
    public final AppCompatEditText edtExpiryYy;
    @NonNull
    public final ImageView imgCardTypeLogo;
    @NonNull
    public final TextView tvAddCardInstruction;
    @NonNull
    public final TextView tvCardCvv;
    @NonNull
    public final TextView tvCardExpiry;
    @NonNull
    public final TextView tvCardName;
    @NonNull
    public final TextView tvCardNumber;
    @NonNull
    public final View vMiddle;
    @NonNull
    public final View view1;
    @NonNull
    public final View viewCard;

    public FragmentCardDetailsBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, AppCompatEditText appCompatEditText, AppCompatEditText appCompatEditText2, AppCompatEditText appCompatEditText3, AppCompatEditText appCompatEditText4, AppCompatEditText appCompatEditText5, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, View view2, View view3, View view4) {
        super(obj, view, i);
        this.btnSaveCard = button;
        this.clCardCvv = constraintLayout;
        this.clCardExpiry = constraintLayout2;
        this.clCardName = constraintLayout3;
        this.clCardNumber = constraintLayout4;
        this.edtCardName = appCompatEditText;
        this.edtCardNumber = appCompatEditText2;
        this.edtCvv = appCompatEditText3;
        this.edtExpiryMm = appCompatEditText4;
        this.edtExpiryYy = appCompatEditText5;
        this.imgCardTypeLogo = imageView;
        this.tvAddCardInstruction = textView;
        this.tvCardCvv = textView2;
        this.tvCardExpiry = textView3;
        this.tvCardName = textView4;
        this.tvCardNumber = textView5;
        this.vMiddle = view2;
        this.view1 = view3;
        this.viewCard = view4;
    }

    public static FragmentCardDetailsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentCardDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCardDetailsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentCardDetailsBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_card_details);
    }

    @NonNull
    @Deprecated
    public static FragmentCardDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentCardDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_card_details, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentCardDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentCardDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentCardDetailsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_card_details, null, false, obj);
    }
}
