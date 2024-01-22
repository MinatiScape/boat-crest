package com.mappls.sdk.direction.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.adapters.e;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteEventFragmentBinding;
import com.mappls.sdk.services.api.event.route.ReportCriteria;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import com.mappls.sdk.services.api.event.route.model.RouteReport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes11.dex */
public final class k extends Fragment {
    public MapplsDirectionRouteEventFragmentBinding h;
    public RouteReport i;
    public b j;

    /* loaded from: classes11.dex */
    public class a implements e.b {
        public a() {
        }

        @Override // com.mappls.sdk.direction.ui.adapters.e.b
        public final void a(String str, ArrayList<ReportDetails> arrayList) {
            if (k.this.j != null) {
                k.this.j.a(str, arrayList);
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a(String str, ArrayList<ReportDetails> arrayList);
    }

    public k(RouteReport routeReport) {
        this.i = routeReport;
    }

    public final void a(b bVar) {
        this.j = bVar;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        MapplsDirectionRouteEventFragmentBinding mapplsDirectionRouteEventFragmentBinding = (MapplsDirectionRouteEventFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.mappls_direction_route_event_fragment, viewGroup, false);
        this.h = mapplsDirectionRouteEventFragmentBinding;
        return mapplsDirectionRouteEventFragmentBinding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ArrayList arrayList = new ArrayList();
        RouteReport routeReport = this.i;
        if (routeReport != null && routeReport.getReports() != null) {
            HashMap hashMap = new HashMap();
            for (ReportDetails reportDetails : this.i.getReports()) {
                String parentCategory = reportDetails.getParentCategory();
                if (hashMap.containsKey(parentCategory)) {
                    ArrayList arrayList2 = (ArrayList) hashMap.get(parentCategory);
                    if (arrayList2 != null) {
                        arrayList2.add(reportDetails);
                    }
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(reportDetails);
                    hashMap.put(parentCategory, arrayList3);
                }
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                String str = (String) entry.getKey();
                ArrayList arrayList4 = (ArrayList) entry.getValue();
                if (arrayList4.size() > 2) {
                    arrayList.add(new com.mappls.sdk.direction.ui.event.c(str, arrayList4, ((ReportDetails) arrayList4.get(0)).getParentReportIcon(ReportCriteria.ICON_48_PX), arrayList4.size(), true));
                    arrayList.add(new com.mappls.sdk.direction.ui.event.b((ReportDetails) arrayList4.get(0)));
                    arrayList.add(new com.mappls.sdk.direction.ui.event.b((ReportDetails) arrayList4.get(1)));
                } else {
                    arrayList.add(new com.mappls.sdk.direction.ui.event.c(str, arrayList4, ((ReportDetails) arrayList4.get(0)).getParentReportIcon(ReportCriteria.ICON_48_PX), arrayList4.size(), false));
                    Iterator it = arrayList4.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new com.mappls.sdk.direction.ui.event.b((ReportDetails) it.next()));
                    }
                }
            }
        }
        com.mappls.sdk.direction.ui.adapters.e eVar = new com.mappls.sdk.direction.ui.adapters.e(arrayList);
        eVar.a(new a());
        this.h.routeEventRecyclerView.setHasFixedSize(true);
        this.h.routeEventRecyclerView.setAdapter(eVar);
        this.h.routeEventRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
