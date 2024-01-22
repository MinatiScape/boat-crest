package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionItemTollRowLayoutBinding extends ViewDataBinding {
    @NonNull
    public final Barrier barrier;
    @NonNull
    public final ConstraintLayout layoutMoreDetailInfo;
    @NonNull
    public final TextView textViewAmenities;
    @NonNull
    public final TextView textViewAmenitiesLabel;
    @NonNull
    public final TextView textViewCost;
    @NonNull
    public final TextView textViewCostLabel;
    @NonNull
    public final TextView textViewDistance;
    @NonNull
    public final TextView textViewDistanceLabel;
    @NonNull
    public final TextView textViewDuration;
    @NonNull
    public final TextView textViewDurationLabel;
    @NonNull
    public final TextView textViewEmergency;
    @NonNull
    public final TextView textViewEmergencyLabel;
    @NonNull
    public final TextView textViewTollInfo;
    @NonNull
    public final TextView textViewTollName;

    public MapplsDirectionItemTollRowLayoutBinding(Object obj, View view, int i, Barrier barrier, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12) {
        super(obj, view, i);
        this.barrier = barrier;
        this.layoutMoreDetailInfo = constraintLayout;
        this.textViewAmenities = textView;
        this.textViewAmenitiesLabel = textView2;
        this.textViewCost = textView3;
        this.textViewCostLabel = textView4;
        this.textViewDistance = textView5;
        this.textViewDistanceLabel = textView6;
        this.textViewDuration = textView7;
        this.textViewDurationLabel = textView8;
        this.textViewEmergency = textView9;
        this.textViewEmergencyLabel = textView10;
        this.textViewTollInfo = textView11;
        this.textViewTollName = textView12;
    }

    public static MapplsDirectionItemTollRowLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionItemTollRowLayoutBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionItemTollRowLayoutBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_item_toll_row_layout);
    }

    @NonNull
    public static MapplsDirectionItemTollRowLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionItemTollRowLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionItemTollRowLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionItemTollRowLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_item_toll_row_layout, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionItemTollRowLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionItemTollRowLayoutBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_item_toll_row_layout, null, false, obj);
    }
}
