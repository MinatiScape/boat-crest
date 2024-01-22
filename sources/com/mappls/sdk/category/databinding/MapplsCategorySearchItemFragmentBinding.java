package com.mappls.sdk.category.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.mappls.sdk.category.R;
/* loaded from: classes11.dex */
public abstract class MapplsCategorySearchItemFragmentBinding extends ViewDataBinding {
    @NonNull
    public final AppBarLayout mapplsCategoryAppbar;
    @NonNull
    public final ImageView mapplsCategoryBackIcon;
    @NonNull
    public final TextView mapplsCategoryButtonNext;
    @NonNull
    public final RecyclerView mapplsCategoryCategoryRecyclerView;
    @NonNull
    public final CoordinatorLayout mapplsCategoryLayoutBackground;
    @NonNull
    public final LinearLayout mapplsCategoryLinearLayout;
    @NonNull
    public final RelativeLayout mapplsCategoryProgressBar;
    @NonNull
    public final ImageButton mapplsCategorySearchClearBtn;
    @NonNull
    public final EditText mapplsCategorySearchInput;
    @NonNull
    public final LinearLayout mapplsCategorySearchLayoutParent;

    public MapplsCategorySearchItemFragmentBinding(Object obj, View view, int i, AppBarLayout appBarLayout, ImageView imageView, TextView textView, RecyclerView recyclerView, CoordinatorLayout coordinatorLayout, LinearLayout linearLayout, RelativeLayout relativeLayout, ImageButton imageButton, EditText editText, LinearLayout linearLayout2) {
        super(obj, view, i);
        this.mapplsCategoryAppbar = appBarLayout;
        this.mapplsCategoryBackIcon = imageView;
        this.mapplsCategoryButtonNext = textView;
        this.mapplsCategoryCategoryRecyclerView = recyclerView;
        this.mapplsCategoryLayoutBackground = coordinatorLayout;
        this.mapplsCategoryLinearLayout = linearLayout;
        this.mapplsCategoryProgressBar = relativeLayout;
        this.mapplsCategorySearchClearBtn = imageButton;
        this.mapplsCategorySearchInput = editText;
        this.mapplsCategorySearchLayoutParent = linearLayout2;
    }

    public static MapplsCategorySearchItemFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsCategorySearchItemFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsCategorySearchItemFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_category_search_item_fragment);
    }

    @NonNull
    public static MapplsCategorySearchItemFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsCategorySearchItemFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsCategorySearchItemFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsCategorySearchItemFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_category_search_item_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsCategorySearchItemFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsCategorySearchItemFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_category_search_item_fragment, null, false, obj);
    }
}
