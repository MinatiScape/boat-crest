package com.coveiot.android.navigation.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.navigation.R;
/* loaded from: classes5.dex */
public abstract class ActivityAddOrEditFavouriteLocationBinding extends ViewDataBinding {
    @NonNull
    public final Button btnSave;
    @NonNull
    public final ConstraintLayout clSearchLayout;
    @NonNull
    public final EditText etFavouritePlaceAddress;
    @NonNull
    public final ImageView ivClearIcon;
    @NonNull
    public final ImageView ivEditFavouritePlaceTitle;
    @NonNull
    public final ImageView ivSearchIcon;
    @NonNull
    public final RecyclerView rvFavouritePlaces;
    @NonNull
    public final View toolbar;
    @NonNull
    public final EditText tvFavouritePlaceTitle;

    public ActivityAddOrEditFavouriteLocationBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout, EditText editText, ImageView imageView, ImageView imageView2, ImageView imageView3, RecyclerView recyclerView, View view2, EditText editText2) {
        super(obj, view, i);
        this.btnSave = button;
        this.clSearchLayout = constraintLayout;
        this.etFavouritePlaceAddress = editText;
        this.ivClearIcon = imageView;
        this.ivEditFavouritePlaceTitle = imageView2;
        this.ivSearchIcon = imageView3;
        this.rvFavouritePlaces = recyclerView;
        this.toolbar = view2;
        this.tvFavouritePlaceTitle = editText2;
    }

    public static ActivityAddOrEditFavouriteLocationBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAddOrEditFavouriteLocationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAddOrEditFavouriteLocationBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ActivityAddOrEditFavouriteLocationBinding) ViewDataBinding.bind(obj, view, R.layout.activity_add_or_edit_favourite_location);
    }

    @NonNull
    @Deprecated
    public static ActivityAddOrEditFavouriteLocationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ActivityAddOrEditFavouriteLocationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_or_edit_favourite_location, viewGroup, z, obj);
    }

    @NonNull
    public static ActivityAddOrEditFavouriteLocationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ActivityAddOrEditFavouriteLocationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ActivityAddOrEditFavouriteLocationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_add_or_edit_favourite_location, null, false, obj);
    }
}
