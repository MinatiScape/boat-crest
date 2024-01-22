package com.coveiot.android.boat.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.R;
/* loaded from: classes3.dex */
public abstract class ActivitySensaiShareScreenBinding extends ViewDataBinding {
    @NonNull
    public final RelativeLayout containerLayout;
    @NonNull
    public final TextView shareButton;
    @NonNull
    public final ImageView shareCloseImage;
    @NonNull
    public final LinearLayout shareCloseLayout;
    @NonNull
    public final LinearLayout shareLayout;

    public ActivitySensaiShareScreenBinding(Object obj, View view, int i, RelativeLayout relativeLayout, TextView textView, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.containerLayout = relativeLayout;
        this.shareButton = textView;
        this.shareCloseImage = imageView;
        this.shareCloseLayout = linearLayout;
        this.shareLayout = linearLayout2;
    }

    public static ActivitySensaiShareScreenBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySensaiShareScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySensaiShareScreenBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivitySensaiShareScreenBinding) ViewDataBinding.bind(obj, view, R.layout.activity_sensai_share_screen);
    }

    @NonNull
    @Deprecated
    public static ActivitySensaiShareScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivitySensaiShareScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sensai_share_screen, viewGroup, z, obj);
    }

    @NonNull
    public static ActivitySensaiShareScreenBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivitySensaiShareScreenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivitySensaiShareScreenBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_sensai_share_screen, null, false, obj);
    }
}
