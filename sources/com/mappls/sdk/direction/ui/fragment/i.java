package com.mappls.sdk.direction.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteSummaryAllEventFragmentBinding;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class i extends Fragment {
    public final String h;
    public final ArrayList<ReportDetails> i;
    public MapplsDirectionRouteSummaryAllEventFragmentBinding j;

    /* loaded from: classes11.dex */
    public class a extends OnBackPressedCallback {
        public a() {
            super(true);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            i.this.getParentFragmentManager().popBackStack(i.this.getClass().getName(), 1);
        }
    }

    public i(String str, ArrayList<ReportDetails> arrayList) {
        this.h = str;
        this.i = arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MapplsDirectionRouteSummaryAllEventFragmentBinding mapplsDirectionRouteSummaryAllEventFragmentBinding = (MapplsDirectionRouteSummaryAllEventFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.mappls_direction_route_summary_all_event_fragment, viewGroup, false);
        this.j = mapplsDirectionRouteSummaryAllEventFragmentBinding;
        return mapplsDirectionRouteSummaryAllEventFragmentBinding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.j.layoutSummaryCommonToolbar.toolbar.setTitle(this.h);
        this.j.layoutSummaryCommonToolbar.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.colorTextPrimary));
        this.j.layoutSummaryCommonToolbar.toolbar.setNavigationOnClickListener(new r(this));
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new a());
        this.j.routeAllSummaryRecyclerView.setHasFixedSize(true);
        this.j.routeAllSummaryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        this.j.routeAllSummaryRecyclerView.setAdapter(new com.mappls.sdk.direction.ui.adapters.g(this.i));
    }
}
