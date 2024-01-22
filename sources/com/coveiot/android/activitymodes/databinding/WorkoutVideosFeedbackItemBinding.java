package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public abstract class WorkoutVideosFeedbackItemBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageFeedback;
    @NonNull
    public final LinearLayout linearLay;
    @NonNull
    public final TextView textFeedback;

    public WorkoutVideosFeedbackItemBinding(Object obj, View view, int i, ImageView imageView, LinearLayout linearLayout, TextView textView) {
        super(obj, view, i);
        this.imageFeedback = imageView;
        this.linearLay = linearLayout;
        this.textFeedback = textView;
    }

    public static WorkoutVideosFeedbackItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static WorkoutVideosFeedbackItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WorkoutVideosFeedbackItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (WorkoutVideosFeedbackItemBinding) ViewDataBinding.bind(obj, view, R.layout.workout_videos_feedback_item);
    }

    @NonNull
    @Deprecated
    public static WorkoutVideosFeedbackItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (WorkoutVideosFeedbackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.workout_videos_feedback_item, viewGroup, z, obj);
    }

    @NonNull
    public static WorkoutVideosFeedbackItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static WorkoutVideosFeedbackItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (WorkoutVideosFeedbackItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.workout_videos_feedback_item, null, false, obj);
    }
}
