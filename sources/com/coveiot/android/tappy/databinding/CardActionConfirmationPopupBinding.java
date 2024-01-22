package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class CardActionConfirmationPopupBinding extends ViewDataBinding {
    @NonNull
    public final Button btnNo;
    @NonNull
    public final Button btnYes;
    @NonNull
    public final TextView tvAreYouSure;
    @NonNull
    public final TextView tvByProceeding;
    @NonNull
    public final TextView tvDeregisterStrap;

    public CardActionConfirmationPopupBinding(Object obj, View view, int i, Button button, Button button2, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.btnNo = button;
        this.btnYes = button2;
        this.tvAreYouSure = textView;
        this.tvByProceeding = textView2;
        this.tvDeregisterStrap = textView3;
    }

    public static CardActionConfirmationPopupBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CardActionConfirmationPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CardActionConfirmationPopupBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CardActionConfirmationPopupBinding) ViewDataBinding.bind(obj, view, R.layout.card_action_confirmation_popup);
    }

    @NonNull
    @Deprecated
    public static CardActionConfirmationPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CardActionConfirmationPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.card_action_confirmation_popup, viewGroup, z, obj);
    }

    @NonNull
    public static CardActionConfirmationPopupBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CardActionConfirmationPopupBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CardActionConfirmationPopupBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.card_action_confirmation_popup, null, false, obj);
    }
}
