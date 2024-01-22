package com.coveiot.android.watchfaceui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.watchfaceui.R;
/* loaded from: classes8.dex */
public abstract class ActivityBackgroundWatchFaceBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final View toolbar;
    @NonNull
    public final FrameLayout watchfaceBackground;

    public ActivityBackgroundWatchFaceBinding(Object obj, View view, int i, Button button, View view2, FrameLayout frameLayout) {
        super(obj, view, i);
        this.btnSave = button;
        this.toolbar = view2;
        this.watchfaceBackground = frameLayout;
    }

    public static ActivityBackgroundWatchFaceBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBackgroundWatchFaceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBackgroundWatchFaceBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityBackgroundWatchFaceBinding) ViewDataBinding.bind(obj, view, R.layout.activity_background_watch_face);
    }

    @NonNull
    @Deprecated
    public static ActivityBackgroundWatchFaceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityBackgroundWatchFaceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_background_watch_face, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityBackgroundWatchFaceBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityBackgroundWatchFaceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityBackgroundWatchFaceBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_background_watch_face, null, false, obj);
    }
}
