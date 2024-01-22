package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class FragmentSensaiShareBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clProfile;
    @NonNull
    public final RecyclerView contentRecyclerView;
    @NonNull
    public final TextView dateTimeText;
    @NonNull
    public final TextView disclaimerInfo;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final Guideline guideline3;
    @NonNull
    public final LinearLayout imageLayout;
    @NonNull
    public final ImageView imgActivity;
    @NonNull
    public final ImageView imgProfile;
    @NonNull
    public final ImageView ivAppLogo;
    @NonNull
    public final ImageView ivPoweredCove;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final TextView titleText;
    @NonNull
    public final TextView tvName;
    @NonNull
    public final TextView typeText;

    public FragmentSensaiShareBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, RecyclerView recyclerView, TextView textView, TextView textView2, Guideline guideline, Guideline guideline2, LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ConstraintLayout constraintLayout2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.clProfile = constraintLayout;
        this.contentRecyclerView = recyclerView;
        this.dateTimeText = textView;
        this.disclaimerInfo = textView2;
        this.guideline1 = guideline;
        this.guideline3 = guideline2;
        this.imageLayout = linearLayout;
        this.imgActivity = imageView;
        this.imgProfile = imageView2;
        this.ivAppLogo = imageView3;
        this.ivPoweredCove = imageView4;
        this.rootLayout = constraintLayout2;
        this.titleText = textView3;
        this.tvName = textView4;
        this.typeText = textView5;
    }

    public static FragmentSensaiShareBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentSensaiShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSensaiShareBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentSensaiShareBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_sensai_share);
    }

    @NonNull
    @Deprecated
    public static FragmentSensaiShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentSensaiShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sensai_share, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentSensaiShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentSensaiShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentSensaiShareBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_sensai_share, null, false, obj);
    }
}
