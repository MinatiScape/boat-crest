package com.coveiot.android.dashboard2.databinding;

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
import com.coveiot.android.dashboard2.R;
/* loaded from: classes4.dex */
public abstract class FragmentFitnessGuestBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clGetStarted;
    @NonNull
    public final ConstraintLayout clMiddle;
    @NonNull
    public final ConstraintLayout clTopSelector;
    @NonNull
    public final ImageView ivTopImg;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvMessages;
    @NonNull
    public final TextView tvMyBuddies;

    public FragmentFitnessGuestBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clGetStarted = constraintLayout;
        this.clMiddle = constraintLayout2;
        this.clTopSelector = constraintLayout3;
        this.ivTopImg = imageView;
        this.tvInfo = textView;
        this.tvMessages = textView2;
        this.tvMyBuddies = textView3;
    }

    public static FragmentFitnessGuestBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentFitnessGuestBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentFitnessGuestBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentFitnessGuestBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_fitness_guest);
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessGuestBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentFitnessGuestBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_guest, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentFitnessGuestBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentFitnessGuestBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentFitnessGuestBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_fitness_guest, null, false, obj);
    }
}
