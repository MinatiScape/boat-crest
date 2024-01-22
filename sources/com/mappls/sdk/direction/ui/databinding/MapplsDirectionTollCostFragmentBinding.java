package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionTollCostFragmentBinding extends ViewDataBinding {
    @NonNull
    public final ImageView imageViewFuelCost;
    @NonNull
    public final ImageView imageViewGrandTotalCost;
    @NonNull
    public final ImageView imageViewShowHideTollInfo;
    @NonNull
    public final ImageView imageViewTotalTollCost;
    @NonNull
    public final ConstraintLayout layoutAfterFuelCostCalculated;
    @NonNull
    public final ConstraintLayout layoutFuelAndTotalCost;
    @NonNull
    public final MapplsDirectionCommonToolbarBinding layoutTollCost;
    @NonNull
    public final NestedScrollView nestedScrollView;
    @NonNull
    public final View previewView;
    @NonNull
    public final RecyclerView recyclerViewToll;
    @NonNull
    public final TextView textViewAddUpdateFuelCost;
    @NonNull
    public final TextView textViewFuelCostInfo;
    @NonNull
    public final TextView textViewGrandTotal;
    @NonNull
    public final TextView textViewGrandTotalFee;
    @NonNull
    public final TextView textViewGrandTotalInfo;
    @NonNull
    public final TextView textViewNumberOfToll;
    @NonNull
    public final TextView textViewTollRoute;
    @NonNull
    public final TextView textViewTotalFuelCost;
    @NonNull
    public final TextView textViewTotalFuelCostDisplay;
    @NonNull
    public final TextView textViewTotalTollFee;
    @NonNull
    public final View view1;

    public MapplsDirectionTollCostFragmentBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, MapplsDirectionCommonToolbarBinding mapplsDirectionCommonToolbarBinding, NestedScrollView nestedScrollView, View view2, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, View view3) {
        super(obj, view, i);
        this.imageViewFuelCost = imageView;
        this.imageViewGrandTotalCost = imageView2;
        this.imageViewShowHideTollInfo = imageView3;
        this.imageViewTotalTollCost = imageView4;
        this.layoutAfterFuelCostCalculated = constraintLayout;
        this.layoutFuelAndTotalCost = constraintLayout2;
        this.layoutTollCost = mapplsDirectionCommonToolbarBinding;
        this.nestedScrollView = nestedScrollView;
        this.previewView = view2;
        this.recyclerViewToll = recyclerView;
        this.textViewAddUpdateFuelCost = textView;
        this.textViewFuelCostInfo = textView2;
        this.textViewGrandTotal = textView3;
        this.textViewGrandTotalFee = textView4;
        this.textViewGrandTotalInfo = textView5;
        this.textViewNumberOfToll = textView6;
        this.textViewTollRoute = textView7;
        this.textViewTotalFuelCost = textView8;
        this.textViewTotalFuelCostDisplay = textView9;
        this.textViewTotalTollFee = textView10;
        this.view1 = view3;
    }

    public static MapplsDirectionTollCostFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionTollCostFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionTollCostFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_toll_cost_fragment);
    }

    @NonNull
    public static MapplsDirectionTollCostFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionTollCostFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionTollCostFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionTollCostFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_toll_cost_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionTollCostFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionTollCostFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_toll_cost_fragment, null, false, obj);
    }
}
