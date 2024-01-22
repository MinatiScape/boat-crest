package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivityRemoteCameraBinding extends ViewDataBinding {
    @NonNull
    public final ImageButton capturePhoto;
    @NonNull
    public final ImageButton captureVideo;
    @NonNull
    public final NestedScrollView controls;
    @NonNull
    public final ImageButton edit;
    @NonNull
    public final ImageButton flash;
    @Bindable
    public Boolean mIsFlashSupported;
    @NonNull
    public final ImageButton openGallery;
    @NonNull
    public final PreviewView previewView;
    @NonNull
    public final CoordinatorLayout root;
    @NonNull
    public final ImageButton toggleCamera;

    public ActivityRemoteCameraBinding(Object obj, View view, int i, ImageButton imageButton, ImageButton imageButton2, NestedScrollView nestedScrollView, ImageButton imageButton3, ImageButton imageButton4, ImageButton imageButton5, PreviewView previewView, CoordinatorLayout coordinatorLayout, ImageButton imageButton6) {
        super(obj, view, i);
        this.capturePhoto = imageButton;
        this.captureVideo = imageButton2;
        this.controls = nestedScrollView;
        this.edit = imageButton3;
        this.flash = imageButton4;
        this.openGallery = imageButton5;
        this.previewView = previewView;
        this.root = coordinatorLayout;
        this.toggleCamera = imageButton6;
    }

    public static ActivityRemoteCameraBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRemoteCameraBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Boolean getIsFlashSupported() {
        return this.mIsFlashSupported;
    }

    public abstract void setIsFlashSupported(@Nullable Boolean bool);

    @Deprecated
    public static ActivityRemoteCameraBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityRemoteCameraBinding) ViewDataBinding.bind(obj, view, R.layout.activity_remote_camera);
    }

    @NonNull
    @Deprecated
    public static ActivityRemoteCameraBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityRemoteCameraBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_remote_camera, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityRemoteCameraBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityRemoteCameraBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityRemoteCameraBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_remote_camera, null, false, obj);
    }
}
