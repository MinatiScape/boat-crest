package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SensAiFeedbackItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clFeedback;
    @NonNull
    public final ImageView ivFeedback;
    @NonNull
    public final TextView tvFeedback;

    public SensAiFeedbackItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.clFeedback = constraintLayout;
        this.ivFeedback = imageView;
        this.tvFeedback = textView;
    }

    public static SensAiFeedbackItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SensAiFeedbackItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SensAiFeedbackItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SensAiFeedbackItemBinding) ViewDataBinding.bind(obj, view, R.layout.sens_ai_feedback_item);
    }

    @NonNull
    @Deprecated
    public static SensAiFeedbackItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SensAiFeedbackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_feedback_item, viewGroup, z, obj);
    }

    @NonNull
    public static SensAiFeedbackItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SensAiFeedbackItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SensAiFeedbackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sens_ai_feedback_item, null, false, obj);
    }
}
