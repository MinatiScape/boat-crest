package com.coveiot.android.activitymodes.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.activitymodes.R;
/* loaded from: classes2.dex */
public abstract class CustomTabWorkoutVideosBinding extends ViewDataBinding {
    @NonNull
    public final TextView tabTitle;

    public CustomTabWorkoutVideosBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tabTitle = textView;
    }

    public static CustomTabWorkoutVideosBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static CustomTabWorkoutVideosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static CustomTabWorkoutVideosBinding bind(@NonNull View view, @Nullable Object obj) {
        return (CustomTabWorkoutVideosBinding) ViewDataBinding.bind(obj, view, R.layout.custom_tab_workout_videos);
    }

    @NonNull
    @Deprecated
    public static CustomTabWorkoutVideosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (CustomTabWorkoutVideosBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.custom_tab_workout_videos, viewGroup, z, obj);
    }

    @NonNull
    public static CustomTabWorkoutVideosBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static CustomTabWorkoutVideosBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (CustomTabWorkoutVideosBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.custom_tab_workout_videos, null, false, obj);
    }
}
