package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ComparePopupBinding extends ViewDataBinding {
    @NonNull
    public final CardView cvCompare;
    @NonNull
    public final TextView tvCompare;

    public ComparePopupBinding(Object obj, View view, int i, CardView cardView, TextView textView) {
        super(obj, view, i);
        this.cvCompare = cardView;
        this.tvCompare = textView;
    }

    public static ComparePopupBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ComparePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ComparePopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ComparePopupBinding) ViewDataBinding.bind(obj, view, R.layout.compare_popup);
    }

    @NonNull
    @Deprecated
    public static ComparePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ComparePopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.compare_popup, viewGroup, z, obj);
    }

    @NonNull
    public static ComparePopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ComparePopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ComparePopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.compare_popup, null, false, obj);
    }
}
