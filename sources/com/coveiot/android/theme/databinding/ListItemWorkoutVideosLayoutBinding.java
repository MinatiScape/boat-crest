package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class ListItemWorkoutVideosLayoutBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout clMain;
    @NonNull
    public final ConstraintLayout clWorkoutVideo0;
    @NonNull
    public final CardView cvWorkoutImage;
    @NonNull
    public final CardView cvWorkoutImagePlay;
    @NonNull
    public final ImageView ivVideo;
    @NonNull
    public final ImageView ivVideo0;
    @NonNull
    public final TextView tvWorkoutDuration;
    @NonNull
    public final TextView tvWorkoutDuration0;
    @NonNull
    public final TextView tvWorkoutTitle;
    @NonNull
    public final TextView tvWorkoutTitle0;
    @NonNull
    public final View view;

    public ListItemWorkoutVideosLayoutBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, CardView cardView, CardView cardView2, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view2) {
        super(obj, view, i);
        this.clMain = constraintLayout;
        this.clWorkoutVideo0 = constraintLayout2;
        this.cvWorkoutImage = cardView;
        this.cvWorkoutImagePlay = cardView2;
        this.ivVideo = imageView;
        this.ivVideo0 = imageView2;
        this.tvWorkoutDuration = textView;
        this.tvWorkoutDuration0 = textView2;
        this.tvWorkoutTitle = textView3;
        this.tvWorkoutTitle0 = textView4;
        this.view = view2;
    }

    public static ListItemWorkoutVideosLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ListItemWorkoutVideosLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ListItemWorkoutVideosLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ListItemWorkoutVideosLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.list_item_workout_videos_layout);
    }

    @NonNull
    @Deprecated
    public static ListItemWorkoutVideosLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ListItemWorkoutVideosLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_workout_videos_layout, viewGroup, z, obj);
    }

    @NonNull
    public static ListItemWorkoutVideosLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ListItemWorkoutVideosLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ListItemWorkoutVideosLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.list_item_workout_videos_layout, null, false, obj);
    }
}
