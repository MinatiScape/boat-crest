package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class ActivityActivateCardBinding extends ViewDataBinding {
    @NonNull
    public final Button btnBackToDashBoard;
    @NonNull
    public final ConstraintLayout clOTPSuccess;
    @NonNull
    public final TextView congratulations;
    @NonNull
    public final FrameLayout fragmentContainer;
    @NonNull
    public final ImageView ivSuccess;
    @NonNull
    public final ConstraintLayout successMessage;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvYourProfile;

    public ActivityActivateCardBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, TextView textView, FrameLayout frameLayout, ImageView imageView, ConstraintLayout constraintLayout2, View view2, TextView textView2) {
        super(obj, view, i);
        this.btnBackToDashBoard = button;
        this.clOTPSuccess = constraintLayout;
        this.congratulations = textView;
        this.fragmentContainer = frameLayout;
        this.ivSuccess = imageView;
        this.successMessage = constraintLayout2;
        this.toolbar = view2;
        this.tvYourProfile = textView2;
    }

    public static ActivityActivateCardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityActivateCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityActivateCardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityActivateCardBinding) ViewDataBinding.bind(obj, view, R.layout.activity_activate_card);
    }

    @NonNull
    @Deprecated
    public static ActivityActivateCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityActivateCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_activate_card, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityActivateCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityActivateCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityActivateCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_activate_card, null, false, obj);
    }
}
