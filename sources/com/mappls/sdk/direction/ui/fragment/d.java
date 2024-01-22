package com.mappls.sdk.direction.ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.adapters.d;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionFuelCostFragmentBinding;
import com.mappls.sdk.services.api.costestimation.CostEstimationCriteria;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class d extends Fragment {
    public final CostEstimationResponse h;
    public final DirectionsResponse i;
    public final int j;
    public MapplsDirectionFuelCostFragmentBinding k;
    public String l = CostEstimationCriteria.VEHICLE_FUEL_TYPE_PETROL;
    public InterfaceC0616d m;
    public com.mappls.sdk.direction.ui.c n;

    /* loaded from: classes11.dex */
    public class a extends OnBackPressedCallback {
        public a() {
            super(true);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            d.this.getParentFragmentManager().popBackStack(d.this.getClass().getName(), 1);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements d.b {
        public b() {
        }

        @Override // com.mappls.sdk.direction.ui.adapters.d.b
        public final void a(String str) {
            EditText editText;
            int i;
            str.getClass();
            char c = 65535;
            switch (str.hashCode()) {
                case -1331959846:
                    if (str.equals(CostEstimationCriteria.VEHICLE_FUEL_TYPE_DIESEL)) {
                        c = 0;
                        break;
                    }
                    break;
                case -991657904:
                    if (str.equals(CostEstimationCriteria.VEHICLE_FUEL_TYPE_PETROL)) {
                        c = 1;
                        break;
                    }
                    break;
                case -17124067:
                    if (str.equals(CostEstimationCriteria.VEHICLE_FUEL_TYPE_ELECTRIC)) {
                        c = 2;
                        break;
                    }
                    break;
                case 66876:
                    if (str.equals(CostEstimationCriteria.VEHICLE_FUEL_TYPE_CNG)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    editText = d.this.k.editTextAverage;
                    i = R.string.mappls_direction_fuel_efficiency_ltr;
                    break;
                case 2:
                    editText = d.this.k.editTextAverage;
                    i = R.string.mappls_direction_fuel_efficiency_in_km;
                    break;
                case 3:
                    editText = d.this.k.editTextAverage;
                    i = R.string.mappls_direction_fuel_efficiency_kg;
                    break;
                default:
                    d.this.l = str;
            }
            editText.setHint(i);
            d.this.l = str;
        }
    }

    /* loaded from: classes11.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            d.e(d.this);
        }
    }

    /* renamed from: com.mappls.sdk.direction.ui.fragment.d$d  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0616d {
        void a(CostEstimationResponse costEstimationResponse);
    }

    public d(CostEstimationResponse costEstimationResponse, DirectionsResponse directionsResponse, int i) {
        this.h = costEstimationResponse;
        this.i = directionsResponse;
        this.j = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(com.mappls.sdk.direction.ui.common.a aVar) {
        if (aVar != null) {
            int i = aVar.f12583a;
            if (i != 1 || aVar.c == 0) {
                if (i == 2) {
                    Toast.makeText(requireContext(), aVar.b, 0).show();
                    return;
                }
                return;
            }
            getParentFragmentManager().popBackStack(d.class.getName(), 1);
            InterfaceC0616d interfaceC0616d = this.m;
            if (interfaceC0616d != null) {
                interfaceC0616d.a((CostEstimationResponse) aVar.c);
            }
        }
    }

    public static void e(d dVar) {
        Context requireContext;
        String str;
        com.mappls.sdk.direction.ui.utils.a.a(dVar.k.getRoot());
        if (TextUtils.isEmpty(dVar.k.editTextCost.getText().toString())) {
            requireContext = dVar.requireContext();
            str = "Please enter fuel price";
        } else if (!TextUtils.isEmpty(dVar.k.editTextAverage.getText().toString())) {
            int parseInt = Integer.parseInt(dVar.k.editTextAverage.getText().toString());
            double parseDouble = Double.parseDouble(dVar.k.editTextCost.getText().toString());
            DirectionsResponse directionsResponse = dVar.i;
            dVar.n.a(dVar.i.sessionId(), dVar.j, dVar.l, Integer.valueOf(parseInt), Double.valueOf(parseDouble), (directionsResponse == null || directionsResponse.routes().size() <= 0 || dVar.i.routes().get(0).routeOptions() == null) ? "driving" : dVar.i.routes().get(0).routeOptions().profile());
            return;
        } else {
            requireContext = dVar.requireContext();
            str = "Please enter fuel efficiency";
        }
        Toast.makeText(requireContext, str, 0).show();
    }

    public final void a() {
        this.n.f12581a.observe(getViewLifecycleOwner(), new Observer() { // from class: com.mappls.sdk.direction.ui.fragment.p
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                d.this.c((com.mappls.sdk.direction.ui.common.a) obj);
            }
        });
    }

    public final void a(InterfaceC0616d interfaceC0616d) {
        this.m = interfaceC0616d;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        MapplsDirectionFuelCostFragmentBinding mapplsDirectionFuelCostFragmentBinding = (MapplsDirectionFuelCostFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.mappls_direction_fuel_cost_fragment, viewGroup, false);
        this.k = mapplsDirectionFuelCostFragmentBinding;
        return mapplsDirectionFuelCostFragmentBinding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        Toolbar toolbar;
        String str;
        TextView textView;
        String str2;
        super.onViewCreated(view, bundle);
        this.n = (com.mappls.sdk.direction.ui.c) new ViewModelProvider(this).get(com.mappls.sdk.direction.ui.c.class);
        a();
        CostEstimationResponse costEstimationResponse = this.h;
        if (costEstimationResponse == null || costEstimationResponse.getVehicleFuelType() == null) {
            toolbar = this.k.fuelCostHeader.toolbar;
            str = "Add Fuel Cost";
        } else {
            toolbar = this.k.fuelCostHeader.toolbar;
            str = "Update Fuel Cost";
        }
        toolbar.setTitle(str);
        this.k.fuelCostHeader.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.colorTextPrimary));
        this.k.fuelCostHeader.toolbar.setNavigationOnClickListener(new q(this));
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new a());
        CostEstimationResponse costEstimationResponse2 = this.h;
        if (costEstimationResponse2 == null || costEstimationResponse2.getHasTolls() == null || !this.h.getHasTolls().booleanValue()) {
            textView = this.k.textViewTollFee;
            str2 = "₹ 0";
        } else if (this.h.getTotalTollCost() != null) {
            textView = this.k.textViewTollFee;
            str2 = "₹ " + this.h.getTotalTollCost();
        } else {
            textView = this.k.textViewTollFee;
            str2 = "₹ NA";
        }
        textView.setText(str2);
        if (this.i.routes().get(this.j) != null) {
            this.k.textViewDistance.setText(com.mappls.sdk.direction.ui.common.d.b(this.i.routes().get(this.j).distance().doubleValue()));
            this.k.textViewDuration.setText(com.mappls.sdk.direction.ui.common.d.c(this.i.routes().get(this.j).duration().doubleValue()));
        }
        this.k.recyclerViewFuelType.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        CostEstimationResponse costEstimationResponse3 = this.h;
        if (costEstimationResponse3 != null && costEstimationResponse3.getVehicleFuelType() != null) {
            this.l = this.h.getVehicleFuelType();
        }
        CostEstimationResponse costEstimationResponse4 = this.h;
        if (costEstimationResponse4 != null && costEstimationResponse4.getVehicleFuelType() != null) {
            this.l = this.h.getVehicleFuelType();
        }
        CostEstimationResponse costEstimationResponse5 = this.h;
        if (costEstimationResponse5 != null && costEstimationResponse5.getFuelPrice() != null) {
            this.k.editTextCost.setText(this.h.getFuelPrice() + "");
        }
        CostEstimationResponse costEstimationResponse6 = this.h;
        if (costEstimationResponse6 != null && costEstimationResponse6.getFuelEfficiency() != null) {
            this.k.editTextAverage.setText(this.h.getFuelEfficiency().replaceAll("\\D", ""));
        }
        ArrayList arrayList = new ArrayList();
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.mappls_direction_petrol_station_grey);
        Drawable drawable2 = ContextCompat.getDrawable(requireContext(), R.drawable.mappls_direction_petrol_station_white);
        int i = R.drawable.mappls_direction_fuel_type_bg;
        Integer valueOf = Integer.valueOf(i);
        int i2 = R.drawable.mappls_direction_fuel_type_bg_selected;
        arrayList.add(new com.mappls.sdk.direction.ui.model.d("Petrol", CostEstimationCriteria.VEHICLE_FUEL_TYPE_PETROL, drawable, drawable2, valueOf, Integer.valueOf(i2), Boolean.valueOf(this.l.equalsIgnoreCase(CostEstimationCriteria.VEHICLE_FUEL_TYPE_PETROL))));
        arrayList.add(new com.mappls.sdk.direction.ui.model.d("Diesel", CostEstimationCriteria.VEHICLE_FUEL_TYPE_DIESEL, ContextCompat.getDrawable(requireContext(), R.drawable.mappls_direction_diesel_station_grey), ContextCompat.getDrawable(requireContext(), R.drawable.mappls_direction_diesel_station_white), Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(this.l.equalsIgnoreCase(CostEstimationCriteria.VEHICLE_FUEL_TYPE_DIESEL))));
        arrayList.add(new com.mappls.sdk.direction.ui.model.d(CostEstimationCriteria.VEHICLE_FUEL_TYPE_CNG, CostEstimationCriteria.VEHICLE_FUEL_TYPE_CNG, ContextCompat.getDrawable(requireContext(), R.drawable.mappls_direction_cng_station_grey), ContextCompat.getDrawable(requireContext(), R.drawable.mappls_direction_cng_station_white), Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(this.l.equalsIgnoreCase(CostEstimationCriteria.VEHICLE_FUEL_TYPE_CNG))));
        arrayList.add(new com.mappls.sdk.direction.ui.model.d("Electric", CostEstimationCriteria.VEHICLE_FUEL_TYPE_ELECTRIC, ContextCompat.getDrawable(requireContext(), R.drawable.mappls_direction_ev_station_grey), ContextCompat.getDrawable(requireContext(), R.drawable.mappls_direction_ev_station_white), Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(this.l.equalsIgnoreCase(CostEstimationCriteria.VEHICLE_FUEL_TYPE_ELECTRIC))));
        com.mappls.sdk.direction.ui.adapters.d dVar = new com.mappls.sdk.direction.ui.adapters.d(arrayList);
        dVar.a(new b());
        this.k.recyclerViewFuelType.setAdapter(dVar);
        this.k.textViewCalculate.setOnClickListener(new c());
    }
}
