package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionFragmentAddWayPointDialogBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout addWaypointBottomSheet;
    @NonNull
    public final TextView buttonAddWaypoint;
    @NonNull
    public final TextView itemPlaceResultPlaceAddress;
    @NonNull
    public final RatingBar itemPlaceResultRatingBar;
    @NonNull
    public final ImageView placeResultImg;
    @NonNull
    public final TextView resultPlaceName;
    @NonNull
    public final ImageView selectedWaypointBackIcon;
    @NonNull
    public final ImageButton selectedWaypointClearBtn;
    @NonNull
    public final LinearLayout selectedWaypointLayoutParent;
    @NonNull
    public final TextView selectedWaypointSearchInput;
    @NonNull
    public final TextView textViewDistance;

    public MapplsDirectionFragmentAddWayPointDialogBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2, RatingBar ratingBar, ImageView imageView, TextView textView3, ImageView imageView2, ImageButton imageButton, LinearLayout linearLayout, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.addWaypointBottomSheet = constraintLayout;
        this.buttonAddWaypoint = textView;
        this.itemPlaceResultPlaceAddress = textView2;
        this.itemPlaceResultRatingBar = ratingBar;
        this.placeResultImg = imageView;
        this.resultPlaceName = textView3;
        this.selectedWaypointBackIcon = imageView2;
        this.selectedWaypointClearBtn = imageButton;
        this.selectedWaypointLayoutParent = linearLayout;
        this.selectedWaypointSearchInput = textView4;
        this.textViewDistance = textView5;
    }

    public static MapplsDirectionFragmentAddWayPointDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionFragmentAddWayPointDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionFragmentAddWayPointDialogBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_fragment_add_way_point_dialog);
    }

    @NonNull
    public static MapplsDirectionFragmentAddWayPointDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionFragmentAddWayPointDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionFragmentAddWayPointDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionFragmentAddWayPointDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_fragment_add_way_point_dialog, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionFragmentAddWayPointDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionFragmentAddWayPointDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_fragment_add_way_point_dialog, null, false, obj);
    }
}
