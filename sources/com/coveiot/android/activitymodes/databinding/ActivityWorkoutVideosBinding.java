package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public abstract class ActivityWorkoutVideosBinding extends ViewDataBinding {
    @NonNull
    public final RecyclerView categoryRecyclerView;
    @NonNull
    public final EditText etSearchVideo;
    @NonNull
    public final FrameLayout fragmentContainerCultFitLastVideo;
    @NonNull
    public final TextView header;
    @NonNull
    public final View toolBar;
    @NonNull
    public final TextView tvNoData;
    @NonNull
    public final RecyclerView videosRecyclerView;

    public ActivityWorkoutVideosBinding(Object obj, View view, int i, RecyclerView recyclerView, EditText editText, FrameLayout frameLayout, TextView textView, View view2, TextView textView2, RecyclerView recyclerView2) {
        super(obj, view, i);
        this.categoryRecyclerView = recyclerView;
        this.etSearchVideo = editText;
        this.fragmentContainerCultFitLastVideo = frameLayout;
        this.header = textView;
        this.toolBar = view2;
        this.tvNoData = textView2;
        this.videosRecyclerView = recyclerView2;
    }

    public static ActivityWorkoutVideosBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityWorkoutVideosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWorkoutVideosBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityWorkoutVideosBinding) ViewDataBinding.bind(obj, view, R.layout.activity_workout_videos);
    }

    @NonNull
    @Deprecated
    public static ActivityWorkoutVideosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityWorkoutVideosBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_workout_videos, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityWorkoutVideosBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityWorkoutVideosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityWorkoutVideosBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_workout_videos, null, false, obj);
    }
}
