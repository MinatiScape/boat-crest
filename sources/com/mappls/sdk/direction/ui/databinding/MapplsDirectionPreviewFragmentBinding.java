package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionPreviewFragmentBinding extends ViewDataBinding {
    @NonNull
    public final ImageButton btnNextUp;
    @NonNull
    public final ImageButton btnPrevDown;
    @NonNull
    public final MapplsDirectionCommonToolbarBinding layoutCommonToolbar;
    @Bindable
    public View.OnClickListener mOnNextClick;
    @Bindable
    public View.OnClickListener mOnPreviousClick;
    @NonNull
    public final ViewPager pagerDirectionPreview;
    @NonNull
    public final LinearLayout previewContainer;
    @NonNull
    public final View previewView;

    public MapplsDirectionPreviewFragmentBinding(Object obj, View view, int i, ImageButton imageButton, ImageButton imageButton2, MapplsDirectionCommonToolbarBinding mapplsDirectionCommonToolbarBinding, ViewPager viewPager, LinearLayout linearLayout, View view2) {
        super(obj, view, i);
        this.btnNextUp = imageButton;
        this.btnPrevDown = imageButton2;
        this.layoutCommonToolbar = mapplsDirectionCommonToolbarBinding;
        this.pagerDirectionPreview = viewPager;
        this.previewContainer = linearLayout;
        this.previewView = view2;
    }

    public static MapplsDirectionPreviewFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionPreviewFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionPreviewFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_preview_fragment);
    }

    @NonNull
    public static MapplsDirectionPreviewFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionPreviewFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionPreviewFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionPreviewFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_preview_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionPreviewFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionPreviewFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_preview_fragment, null, false, obj);
    }

    @Nullable
    public View.OnClickListener getOnNextClick() {
        return this.mOnNextClick;
    }

    @Nullable
    public View.OnClickListener getOnPreviousClick() {
        return this.mOnPreviousClick;
    }

    public abstract void setOnNextClick(@Nullable View.OnClickListener onClickListener);

    public abstract void setOnPreviousClick(@Nullable View.OnClickListener onClickListener);
}
