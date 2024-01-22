package com.coveiot.android.tappy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.tappy.R;
/* loaded from: classes7.dex */
public abstract class FragmentAddNfcStrapFtuBinding extends ViewDataBinding {
    @NonNull
    public final Button btnGetStarted;
    @NonNull
    public final ConstraintLayout clBottomViews;
    @NonNull
    public final ConstraintLayout clPageIndicator;
    @NonNull
    public final ImageView imageViewDot1;
    @NonNull
    public final ImageView imageViewDot2;
    @NonNull
    public final ImageView imageViewDot3;
    @NonNull
    public final ImageView imageViewDot4;
    @Bindable
    public Integer mFTUItemCount;
    @Bindable
    public Integer mSelectedItemPosition;
    @NonNull
    public final View toolbar;
    @NonNull
    public final TextView tvDesc;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final ViewPager viewPager;

    public FragmentAddNfcStrapFtuBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, View view2, TextView textView, TextView textView2, ViewPager viewPager) {
        super(obj, view, i);
        this.btnGetStarted = button;
        this.clBottomViews = constraintLayout;
        this.clPageIndicator = constraintLayout2;
        this.imageViewDot1 = imageView;
        this.imageViewDot2 = imageView2;
        this.imageViewDot3 = imageView3;
        this.imageViewDot4 = imageView4;
        this.toolbar = view2;
        this.tvDesc = textView;
        this.tvTitle = textView2;
        this.viewPager = viewPager;
    }

    public static FragmentAddNfcStrapFtuBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentAddNfcStrapFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Integer getFTUItemCount() {
        return this.mFTUItemCount;
    }

    @Nullable
    public Integer getSelectedItemPosition() {
        return this.mSelectedItemPosition;
    }

    public abstract void setFTUItemCount(@Nullable Integer num);

    public abstract void setSelectedItemPosition(@Nullable Integer num);

    @Deprecated
    public static FragmentAddNfcStrapFtuBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentAddNfcStrapFtuBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_add_nfc_strap_ftu);
    }

    @NonNull
    @Deprecated
    public static FragmentAddNfcStrapFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentAddNfcStrapFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_add_nfc_strap_ftu, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentAddNfcStrapFtuBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentAddNfcStrapFtuBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentAddNfcStrapFtuBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_add_nfc_strap_ftu, null, false, obj);
    }
}
