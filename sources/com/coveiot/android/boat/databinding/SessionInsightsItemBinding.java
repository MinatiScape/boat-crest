package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class SessionInsightsItemBinding extends ViewDataBinding {
    @NonNull
    public final CheckBox cbAddToCompare;
    @NonNull
    public final ConstraintLayout clAvgHandSpeed;
    @NonNull
    public final ConstraintLayout clData1;
    @NonNull
    public final ConstraintLayout clDuration;
    @NonNull
    public final ConstraintLayout clTotalShots;
    @NonNull
    public final CardView cvItem;
    @NonNull
    public final Guideline firstGuideline;
    @NonNull
    public final ImageView ivAward;
    @NonNull
    public final Guideline secondGuideline;
    @NonNull
    public final TextView tvAvgHandSpeed;
    @NonNull
    public final TextView tvAvgHandSpeedTxt;
    @NonNull
    public final TextView tvDuration;
    @NonNull
    public final TextView tvDurationTxt;
    @NonNull
    public final TextView tvTime;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final TextView tvTotalShots;
    @NonNull
    public final TextView tvTotalShotsTxt;
    @NonNull
    public final View view;

    public SessionInsightsItemBinding(Object obj, View view, int i, CheckBox checkBox, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, CardView cardView, Guideline guideline, ImageView imageView, Guideline guideline2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view2) {
        super(obj, view, i);
        this.cbAddToCompare = checkBox;
        this.clAvgHandSpeed = constraintLayout;
        this.clData1 = constraintLayout2;
        this.clDuration = constraintLayout3;
        this.clTotalShots = constraintLayout4;
        this.cvItem = cardView;
        this.firstGuideline = guideline;
        this.ivAward = imageView;
        this.secondGuideline = guideline2;
        this.tvAvgHandSpeed = textView;
        this.tvAvgHandSpeedTxt = textView2;
        this.tvDuration = textView3;
        this.tvDurationTxt = textView4;
        this.tvTime = textView5;
        this.tvTitle = textView6;
        this.tvTotalShots = textView7;
        this.tvTotalShotsTxt = textView8;
        this.view = view2;
    }

    public static SessionInsightsItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static SessionInsightsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SessionInsightsItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SessionInsightsItemBinding) ViewDataBinding.bind(obj, view, R.layout.session_insights_item);
    }

    @NonNull
    @Deprecated
    public static SessionInsightsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SessionInsightsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.session_insights_item, viewGroup, z, obj);
    }

    @NonNull
    public static SessionInsightsItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SessionInsightsItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SessionInsightsItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.session_insights_item, null, false, obj);
    }
}
