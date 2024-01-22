package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class WatchFaceStudioBigCardDashboardBinding extends ViewDataBinding {
    @NonNull
    public final TextView tvChangeYourWatchFace;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final View view;

    public WatchFaceStudioBigCardDashboardBinding(Object obj, View view, int i, TextView textView, TextView textView2, View view2) {
        super(obj, view, i);
        this.tvChangeYourWatchFace = textView;
        this.tvHeader = textView2;
        this.view = view2;
    }

    public static WatchFaceStudioBigCardDashboardBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static WatchFaceStudioBigCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static WatchFaceStudioBigCardDashboardBinding bind(@NonNull View view, @Nullable Object obj) {
        return (WatchFaceStudioBigCardDashboardBinding) ViewDataBinding.bind(obj, view, R.layout.watch_face_studio_big_card_dashboard);
    }

    @NonNull
    @Deprecated
    public static WatchFaceStudioBigCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (WatchFaceStudioBigCardDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.watch_face_studio_big_card_dashboard, viewGroup, z, obj);
    }

    @NonNull
    public static WatchFaceStudioBigCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static WatchFaceStudioBigCardDashboardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (WatchFaceStudioBigCardDashboardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.watch_face_studio_big_card_dashboard, null, false, obj);
    }
}
