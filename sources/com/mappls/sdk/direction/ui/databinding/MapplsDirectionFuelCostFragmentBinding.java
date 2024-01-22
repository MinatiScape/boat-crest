package com.mappls.sdk.direction.ui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.R;
/* loaded from: classes11.dex */
public abstract class MapplsDirectionFuelCostFragmentBinding extends ViewDataBinding {
    @NonNull
    public final EditText editTextAverage;
    @NonNull
    public final EditText editTextCost;
    @NonNull
    public final MapplsDirectionCommonToolbarBinding fuelCostHeader;
    @NonNull
    public final ImageView imageViewAutoGraph;
    @NonNull
    public final ImageView imageViewDistance;
    @NonNull
    public final ImageView imageViewDuration;
    @NonNull
    public final ImageView imageViewRupee;
    @NonNull
    public final ImageView imageViewRupeeSymbol;
    @NonNull
    public final ConstraintLayout layoutBeforeFuelCostCalculated;
    @NonNull
    public final ConstraintLayout layoutDistance;
    @NonNull
    public final ConstraintLayout layoutDuration;
    @NonNull
    public final ConstraintLayout layoutTollInfo;
    @NonNull
    public final ConstraintLayout layoutTollLayout;
    @NonNull
    public final View previewView;
    @NonNull
    public final RecyclerView recyclerViewFuelType;
    @NonNull
    public final TextView textViewCalculate;
    @NonNull
    public final TextView textViewDistance;
    @NonNull
    public final TextView textViewDistanceLebel;
    @NonNull
    public final TextView textViewDuration;
    @NonNull
    public final TextView textViewDurationLebel;
    @NonNull
    public final TextView textViewSelectFuelType;
    @NonNull
    public final TextView textViewTollFee;
    @NonNull
    public final TextView textViewTollLebel;
    @NonNull
    public final View view;
    @NonNull
    public final View view1;
    @NonNull
    public final View view2;

    public MapplsDirectionFuelCostFragmentBinding(Object obj, View view, int i, EditText editText, EditText editText2, MapplsDirectionCommonToolbarBinding mapplsDirectionCommonToolbarBinding, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, View view2, RecyclerView recyclerView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view3, View view4, View view5) {
        super(obj, view, i);
        this.editTextAverage = editText;
        this.editTextCost = editText2;
        this.fuelCostHeader = mapplsDirectionCommonToolbarBinding;
        this.imageViewAutoGraph = imageView;
        this.imageViewDistance = imageView2;
        this.imageViewDuration = imageView3;
        this.imageViewRupee = imageView4;
        this.imageViewRupeeSymbol = imageView5;
        this.layoutBeforeFuelCostCalculated = constraintLayout;
        this.layoutDistance = constraintLayout2;
        this.layoutDuration = constraintLayout3;
        this.layoutTollInfo = constraintLayout4;
        this.layoutTollLayout = constraintLayout5;
        this.previewView = view2;
        this.recyclerViewFuelType = recyclerView;
        this.textViewCalculate = textView;
        this.textViewDistance = textView2;
        this.textViewDistanceLebel = textView3;
        this.textViewDuration = textView4;
        this.textViewDurationLebel = textView5;
        this.textViewSelectFuelType = textView6;
        this.textViewTollFee = textView7;
        this.textViewTollLebel = textView8;
        this.view = view3;
        this.view1 = view4;
        this.view2 = view5;
    }

    public static MapplsDirectionFuelCostFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MapplsDirectionFuelCostFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MapplsDirectionFuelCostFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.mappls_direction_fuel_cost_fragment);
    }

    @NonNull
    public static MapplsDirectionFuelCostFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MapplsDirectionFuelCostFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionFuelCostFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MapplsDirectionFuelCostFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_fuel_cost_fragment, viewGroup, z, obj);
    }

    @NonNull
    @Deprecated
    public static MapplsDirectionFuelCostFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MapplsDirectionFuelCostFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.mappls_direction_fuel_cost_fragment, null, false, obj);
    }
}
