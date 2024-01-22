package com.coveiot.android.watchfaceui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.watchfaceui.R;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes8.dex */
public abstract class ActivityWatchFaceCanewBinding extends ViewDataBinding {
    @NonNull
    public final Button applyToWatch;
    @NonNull
    public final ConstraintLayout clSelectedWatchFace;
    @NonNull
    public final ConstraintLayout customiseWatchFace;
    @NonNull
    public final TextView editBtn;
    @NonNull
    public final LinearLayout llTabLayout;
    @NonNull
    public final ConstraintLayout rootLayout;
    @NonNull
    public final RoundedImageView selectedRoundedWatchFace;
    @NonNull
    public final ImageView selectedWatchFace;
    @NonNull
    public final RoundedImageView selectedWatchFaceBg;
    @NonNull
    public final TabLayout tabs;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvToolTip;
    @NonNull
    public final ViewPager viewPager;
    @NonNull
    public final ImageView watchFacePlaceholder;

    public ActivityWatchFaceCanewBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, TextView textView, LinearLayout linearLayout, ConstraintLayout constraintLayout3, RoundedImageView roundedImageView, ImageView imageView, RoundedImageView roundedImageView2, TabLayout tabLayout, View view2, TextView textView2, ViewPager viewPager, ImageView imageView2) {
        super(obj, view, i);
        this.applyToWatch = button;
        this.clSelectedWatchFace = constraintLayout;
        this.customiseWatchFace = constraintLayout2;
        this.editBtn = textView;
        this.llTabLayout = linearLayout;
        this.rootLayout = constraintLayout3;
        this.selectedRoundedWatchFace = roundedImageView;
        this.selectedWatchFace = imageView;
        this.selectedWatchFaceBg = roundedImageView2;
        this.tabs = tabLayout;
        this.toolbar = view2;
        this.tvToolTip = textView2;
        this.viewPager = viewPager;
        this.watchFacePlaceholder = imageView2;
    }

    public static ActivityWatchFaceCanewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityWatchFaceCanewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWatchFaceCanewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityWatchFaceCanewBinding) ViewDataBinding.bind(obj, view, R.layout.activity_watch_face_canew);
    }

    @NonNull
    @Deprecated
    public static ActivityWatchFaceCanewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityWatchFaceCanewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_watch_face_canew, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityWatchFaceCanewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityWatchFaceCanewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityWatchFaceCanewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_watch_face_canew, null, false, obj);
    }
}
