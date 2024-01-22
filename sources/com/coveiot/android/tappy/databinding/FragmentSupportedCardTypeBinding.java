package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentSupportedCardTypeBinding extends ViewDataBinding {
    @NonNull
    public final ImageView icMasterCard;
    @NonNull
    public final ImageView icRuPayCard;
    @NonNull
    public final ImageView icVisaCard;

    public FragmentSupportedCardTypeBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3) {
        super(obj, view, i);
        this.icMasterCard = imageView;
        this.icRuPayCard = imageView2;
        this.icVisaCard = imageView3;
    }

    public static FragmentSupportedCardTypeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSupportedCardTypeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSupportedCardTypeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSupportedCardTypeBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_supported_card_type);
    }

    @NonNull
    @Deprecated
    public static FragmentSupportedCardTypeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSupportedCardTypeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_supported_card_type, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSupportedCardTypeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSupportedCardTypeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSupportedCardTypeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_supported_card_type, null, false, obj);
    }
}
