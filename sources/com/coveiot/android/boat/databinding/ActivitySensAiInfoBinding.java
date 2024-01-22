package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivitySensAiInfoBinding extends ViewDataBinding {
    @NonNull
    public final TextView bullet1;
    @NonNull
    public final TextView bullet2;
    @NonNull
    public final TextView bullet3;
    @NonNull
    public final ConstraintLayout clInfo1;
    @NonNull
    public final ConstraintLayout clInfo2;
    @NonNull
    public final ConstraintLayout clInfo3;
    @NonNull
    public final LinearLayout cvInfo1;
    @NonNull
    public final LinearLayout cvInfo2;
    @NonNull
    public final LinearLayout cvInfo3;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvInfo1;
    @NonNull
    public final TextView tvInfo2;
    @NonNull
    public final TextView tvInfo3;

    public ActivitySensAiInfoBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, View view2, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.bullet1 = textView;
        this.bullet2 = textView2;
        this.bullet3 = textView3;
        this.clInfo1 = constraintLayout;
        this.clInfo2 = constraintLayout2;
        this.clInfo3 = constraintLayout3;
        this.cvInfo1 = linearLayout;
        this.cvInfo2 = linearLayout2;
        this.cvInfo3 = linearLayout3;
        this.toolbar = view2;
        this.tvInfo1 = textView4;
        this.tvInfo2 = textView5;
        this.tvInfo3 = textView6;
    }

    public static ActivitySensAiInfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySensAiInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySensAiInfoBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySensAiInfoBinding) ViewDataBinding.bind(obj, view, R.layout.activity_sens_ai_info);
    }

    @NonNull
    @Deprecated
    public static ActivitySensAiInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySensAiInfoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sens_ai_info, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySensAiInfoBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySensAiInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySensAiInfoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sens_ai_info, null, false, obj);
    }
}
