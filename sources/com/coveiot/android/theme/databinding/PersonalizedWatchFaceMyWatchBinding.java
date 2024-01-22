package com.coveiot.android.theme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public abstract class PersonalizedWatchFaceMyWatchBinding extends ViewDataBinding {
    @NonNull
    public final Guideline guideline3;
    @NonNull
    public final TextView tvChangeYourWatchFace;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvNewWatchFaces;
    @NonNull
    public final View view;

    public PersonalizedWatchFaceMyWatchBinding(Object obj, View view, int i, Guideline guideline, TextView textView, TextView textView2, TextView textView3, View view2) {
        super(obj, view, i);
        this.guideline3 = guideline;
        this.tvChangeYourWatchFace = textView;
        this.tvHeader = textView2;
        this.tvNewWatchFaces = textView3;
        this.view = view2;
    }

    public static PersonalizedWatchFaceMyWatchBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static PersonalizedWatchFaceMyWatchBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static PersonalizedWatchFaceMyWatchBinding bind(@NonNull View view, @Nullable Object obj) {
        return (PersonalizedWatchFaceMyWatchBinding) ViewDataBinding.bind(obj, view, R.layout.personalized_watch_face_my_watch);
    }

    @NonNull
    @Deprecated
    public static PersonalizedWatchFaceMyWatchBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (PersonalizedWatchFaceMyWatchBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.personalized_watch_face_my_watch, viewGroup, z, obj);
    }

    @NonNull
    public static PersonalizedWatchFaceMyWatchBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static PersonalizedWatchFaceMyWatchBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (PersonalizedWatchFaceMyWatchBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.personalized_watch_face_my_watch, null, false, obj);
    }
}
