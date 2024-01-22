package com.mappls.sdk.direction.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionTollCostFragmentBinding;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
/* loaded from: classes11.dex */
public final class n extends Fragment {
    public CostEstimationResponse h;
    public final DirectionsResponse i;
    public final int j;
    public MapplsDirectionTollCostFragmentBinding k;
    public d l;

    /* loaded from: classes11.dex */
    public class a extends OnBackPressedCallback {
        public a() {
            super(true);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            n.this.getParentFragmentManager().popBackStack(n.this.getClass().getName(), 1);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (n.this.l != null) {
                n.this.l.onUpdateFuelCost(n.this.h, n.this.i, n.this.j);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ImageView imageView;
            int i;
            if (n.this.k.recyclerViewToll.getVisibility() == 0) {
                n.this.k.recyclerViewToll.setVisibility(8);
                imageView = n.this.k.imageViewShowHideTollInfo;
                i = R.drawable.mappls_direction_baseline_arrow_down;
            } else {
                n.this.k.recyclerViewToll.setVisibility(0);
                imageView = n.this.k.imageViewShowHideTollInfo;
                i = R.drawable.mappls_direction_baseline_arrow_up;
            }
            imageView.setImageResource(i);
        }
    }

    /* loaded from: classes11.dex */
    public interface d {
        void onUpdateFuelCost(CostEstimationResponse costEstimationResponse, DirectionsResponse directionsResponse, int i);
    }

    public n(CostEstimationResponse costEstimationResponse, DirectionsResponse directionsResponse, int i) {
        this.h = costEstimationResponse;
        this.i = directionsResponse;
        this.j = i;
    }

    public final void a() {
        TextView textView;
        String str;
        TextView textView2;
        StringBuilder sb;
        Object totalFuelCost;
        TextView textView3;
        String str2;
        if (this.h.getHasTolls() == null || !this.h.getHasTolls().booleanValue()) {
            b(false);
        } else {
            b(true);
            this.k.textViewNumberOfToll.setText(this.h.getTotalTolls() + "");
            if (this.h.getTotalTollCost() != null) {
                textView3 = this.k.textViewTotalTollFee;
                str2 = "₹ " + this.h.getTotalTollCost();
            } else {
                textView3 = this.k.textViewTotalTollFee;
                str2 = "₹ NA";
            }
            textView3.setText(str2);
        }
        if (this.h.getTotalFuelCost() != null) {
            this.k.layoutFuelAndTotalCost.setVisibility(0);
            this.k.textViewTotalFuelCostDisplay.setText("₹ " + this.h.getTotalFuelCost());
            if (this.h.getTotalTripCostEstimate() != null) {
                textView2 = this.k.textViewGrandTotalFee;
                sb = new StringBuilder("₹ ");
                totalFuelCost = this.h.getTotalTripCostEstimate();
            } else {
                textView2 = this.k.textViewGrandTotalFee;
                sb = new StringBuilder("₹ ");
                totalFuelCost = this.h.getTotalFuelCost();
            }
            sb.append(totalFuelCost);
            textView2.setText(sb.toString());
            String b2 = com.mappls.sdk.direction.ui.common.d.b(this.h.getDistance().doubleValue());
            this.k.textViewFuelCostInfo.setText(b2 + " / " + this.h.getFuelEfficiency() + " * ₹ " + this.h.getFuelPrice());
            int intValue = this.h.getTotalTollCost() != null ? this.h.getTotalTollCost().intValue() : 0;
            int intValue2 = this.h.getTotalFuelCost() != null ? this.h.getTotalFuelCost().intValue() : 0;
            this.k.textViewGrandTotalInfo.setText("Toll Fee (₹ " + intValue + ") + Fuel Cost (₹ " + intValue2 + ")");
            textView = this.k.textViewAddUpdateFuelCost;
            str = "Update Fuel Cost";
        } else {
            this.k.layoutFuelAndTotalCost.setVisibility(8);
            textView = this.k.textViewAddUpdateFuelCost;
            str = "Add Fuel Cost";
        }
        textView.setText(str);
        this.k.recyclerViewToll.setLayoutManager(new LinearLayoutManager(requireContext()));
        this.k.recyclerViewToll.setHasFixedSize(true);
        this.k.recyclerViewToll.setAdapter(new com.mappls.sdk.direction.ui.adapters.k(this.h.getTolls()));
        this.k.imageViewShowHideTollInfo.setOnClickListener(new c());
    }

    public final void a(d dVar) {
        this.l = dVar;
    }

    public final void a(CostEstimationResponse costEstimationResponse) {
        this.h = costEstimationResponse;
        a();
    }

    public final void b(boolean z) {
        this.k.recyclerViewToll.setVisibility(z ? 0 : 8);
        this.k.imageViewShowHideTollInfo.setVisibility(z ? 0 : 8);
        this.k.textViewNumberOfToll.setVisibility(z ? 0 : 8);
        this.k.imageViewTotalTollCost.setVisibility(z ? 0 : 8);
        this.k.textViewTollRoute.setVisibility(z ? 0 : 8);
        this.k.textViewTotalTollFee.setVisibility(z ? 0 : 8);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MapplsDirectionTollCostFragmentBinding mapplsDirectionTollCostFragmentBinding = (MapplsDirectionTollCostFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.mappls_direction_toll_cost_fragment, viewGroup, false);
        this.k = mapplsDirectionTollCostFragmentBinding;
        return mapplsDirectionTollCostFragmentBinding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        this.k.layoutTollCost.toolbar.setTitle("Estimated Trip Cost");
        this.k.layoutTollCost.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.colorTextPrimary));
        this.k.layoutTollCost.toolbar.setNavigationOnClickListener(new e0(this));
        a();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new a());
        this.k.textViewAddUpdateFuelCost.setOnClickListener(new b());
    }
}
