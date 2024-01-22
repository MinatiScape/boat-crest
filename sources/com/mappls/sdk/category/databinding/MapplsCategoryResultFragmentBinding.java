package com.mappls.sdk.category.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.mappls.sdk.category.R;
/* loaded from: classes11.dex */
public abstract class MapplsCategoryResultFragmentBinding extends ViewDataBinding {
    @NonNull
    public final View mapplsCategoryLayoutEmptyErrorState;
    @NonNull
    public final RecyclerView mapplsCategoryPoiList;
    @NonNull
    public final RelativeLayout mapplsCategoryPoiListBottomSheetContainer;
    @NonNull
    public final View mapplsCategoryProgressLayout;
    @NonNull
    public final ImageView mapplsCategoryResultBackIcon;
    @NonNull
    public final TextView mapplsCategoryResultSearchInput;
    @NonNull
    public final LinearLayout mapplsCategoryResultSearchLayoutParent;
    @NonNull
    public final ImageButton mapplsCategorySearchClearBtn;
    @NonNull
    public final TextView mapplsCategoryTextViewResults;
    @NonNull
    public final AppBarLayout resultAppbar;
    @NonNull
    public final View view2;

    public MapplsCategoryResultFragmentBinding(Object obj, View view, int i, View view2, RecyclerView recyclerView, RelativeLayout relativeLayout, View view3, ImageView imageView, TextView textView, LinearLayout linearLayout, ImageButton imageButton, TextView textView2, AppBarLayout appBarLayout, View view4) {
        super(obj, view, i);
        this.mapplsCategoryLayoutEmptyErrorState = view2;
        this.mapplsCategoryPoiList = recyclerView;
        this.mapplsCategoryPoiListBottomSheetContainer = relativeLayout;
        this.mapplsCategoryProgressLayout = view3;
        this.mapplsCategoryResultBackIcon = imageView;
        this.mapplsCategoryResultSearchInput = textView;
        this.mapplsCategoryResultSearchLayoutParent = linearLayout;
        this.mapplsCategorySearchClearBtn = imageButton;
        this.mapplsCategoryTextViewResults = textView2;
        this.resultAppbar = appBarLayout;
        this.view2 = view4;
    }

    public static MapplsCategoryResultFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsCategoryResultFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsCategoryResultFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_category_result_fragment);
    }

    @NonNull
    public static MapplsCategoryResultFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsCategoryResultFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsCategoryResultFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsCategoryResultFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_category_result_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsCategoryResultFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsCategoryResultFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_category_result_fragment, null, false, obj);
    }
}
