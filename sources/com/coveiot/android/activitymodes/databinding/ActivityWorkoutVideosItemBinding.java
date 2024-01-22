package com.coveiot.android.activitymodes.databinding;

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
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public abstract class ActivityWorkoutVideosItemBinding extends ViewDataBinding {
    @NonNull
    public final CardView cvImage;
    @NonNull
    public final ConstraintLayout videoItemMainLayout;
    @NonNull
    public final TextView videoMinutes;
    @NonNull
    public final ImageView videoThumbnail;
    @NonNull
    public final TextView videoTitle;
    @NonNull
    public final View view;

    public ActivityWorkoutVideosItemBinding(Object obj, View view, int i, CardView cardView, ConstraintLayout constraintLayout, TextView textView, ImageView imageView, TextView textView2, View view2) {
        super(obj, view, i);
        this.cvImage = cardView;
        this.videoItemMainLayout = constraintLayout;
        this.videoMinutes = textView;
        this.videoThumbnail = imageView;
        this.videoTitle = textView2;
        this.view = view2;
    }

    public static ActivityWorkoutVideosItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityWorkoutVideosItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWorkoutVideosItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityWorkoutVideosItemBinding) ViewDataBinding.bind(obj, view, R.layout.activity_workout_videos_item);
    }

    @NonNull
    @Deprecated
    public static ActivityWorkoutVideosItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityWorkoutVideosItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_workout_videos_item, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityWorkoutVideosItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityWorkoutVideosItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityWorkoutVideosItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_workout_videos_item, null, false, obj);
    }
}
