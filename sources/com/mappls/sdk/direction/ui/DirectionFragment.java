package com.mappls.sdk.direction.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.google.gson.Gson;
import com.mappls.sdk.category.IMapplsCategoryCallback;
import com.mappls.sdk.category.IMapplsPOICallback;
import com.mappls.sdk.category.MapplsCategorySearchFragment;
import com.mappls.sdk.category.model.PoiResult;
import com.mappls.sdk.category.model.SearchCategoryOption;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionLayoutBinding;
import com.mappls.sdk.direction.ui.fragment.RouteSummaryBottomSheetFragment;
import com.mappls.sdk.direction.ui.fragment.a;
import com.mappls.sdk.direction.ui.fragment.d;
import com.mappls.sdk.direction.ui.fragment.g;
import com.mappls.sdk.direction.ui.fragment.h;
import com.mappls.sdk.direction.ui.fragment.i;
import com.mappls.sdk.direction.ui.fragment.l;
import com.mappls.sdk.direction.ui.fragment.n;
import com.mappls.sdk.direction.ui.model.DirectionOptions;
import com.mappls.sdk.direction.ui.model.DirectionPoint;
import com.mappls.sdk.direction.ui.plugin.d;
import com.mappls.sdk.direction.ui.plugin.j;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.MapplsMapOptions;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.services.api.costestimation.model.CostEstimationResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import com.mappls.sdk.services.api.event.route.model.RouteReport;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
@Keep
/* loaded from: classes11.dex */
public class DirectionFragment extends Fragment implements OnMapReadyCallback, h, n.d {
    private MapplsDirectionLayoutBinding binding;
    private List<CategoryCode> categoryCodes;
    private com.mappls.sdk.direction.ui.plugin.d directionPolylinePlugin;
    private boolean isMapInitialised;
    public DirectionCallback mCallback;
    private Context mContext;
    private POISearchCallback mPoiSearchCallback;
    private com.mappls.sdk.direction.ui.b mViewModel;
    private j mapEventsPlugin;
    private MapView mapView;
    private DirectionOptions options;

    /* loaded from: classes11.dex */
    public class a implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ MapplsMap f12556a;

        /* renamed from: com.mappls.sdk.direction.ui.DirectionFragment$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0612a implements d.k {
            public C0612a() {
            }

            @Override // com.mappls.sdk.direction.ui.plugin.d.k
            public final void a(int i, DirectionsRoute directionsRoute) {
                l lVar;
                if (!DirectionFragment.this.isAdded() || (lVar = (l) DirectionFragment.this.getChildFragmentManager().findFragmentByTag(l.class.getName())) == null) {
                    return;
                }
                lVar.a(i, directionsRoute);
            }
        }

        public a(MapplsMap mapplsMap) {
            this.f12556a = mapplsMap;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            DirectionFragment directionFragment = DirectionFragment.this;
            directionFragment.directionPolylinePlugin = new com.mappls.sdk.direction.ui.plugin.d(directionFragment.mapView, this.f12556a, DirectionFragment.this.options);
            DirectionFragment.this.directionPolylinePlugin.a(new C0612a());
            if (DirectionFragment.this.options.showRouteReportSummaryOnMap().booleanValue()) {
                DirectionFragment directionFragment2 = DirectionFragment.this;
                directionFragment2.mapEventsPlugin = new j(directionFragment2.mapView, this.f12556a);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements IMapplsPOICallback {
        public b() {
        }

        @Override // com.mappls.sdk.category.IMapplsPOICallback
        public final void getPOIResultListener(@NonNull List<PoiResult> list) {
            if (DirectionFragment.this.mPoiSearchCallback != null) {
                DirectionFragment.this.mPoiSearchCallback.getPoiSearchResults(list);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements IMapplsCategoryCallback {

        /* loaded from: classes11.dex */
        public class a implements a.InterfaceC0614a {
            public a() {
            }

            @Override // com.mappls.sdk.direction.ui.fragment.a.InterfaceC0614a
            public final void a(@NonNull PoiResult poiResult) {
                l lVar;
                DirectionFragment.this.getChildFragmentManager().popBackStack(com.mappls.sdk.direction.ui.fragment.a.class.getName(), 1);
                if (!DirectionFragment.this.isAdded() || (lVar = (l) DirectionFragment.this.getChildFragmentManager().findFragmentByTag(l.class.getName())) == null) {
                    return;
                }
                lVar.a(poiResult);
            }

            @Override // com.mappls.sdk.direction.ui.fragment.a.InterfaceC0614a
            public final void onCancel() {
                DirectionFragment.this.getChildFragmentManager().popBackStack(com.mappls.sdk.direction.ui.fragment.a.class.getName(), 1);
            }
        }

        public c() {
        }

        @Override // com.mappls.sdk.category.IMapplsCategoryCallback
        public final void onCancel() {
            DirectionFragment.this.getChildFragmentManager().popBackStack(MapplsCategorySearchFragment.class.getName(), 1);
        }

        @Override // com.mappls.sdk.category.IMapplsCategoryCallback
        public final void onCategorySelected(@NonNull PoiResult data) {
            DirectionFragment.this.getChildFragmentManager().popBackStack(MapplsCategorySearchFragment.class.getName(), 1);
            int i = com.mappls.sdk.direction.ui.fragment.a.g;
            Intrinsics.checkNotNullParameter(data, "data");
            com.mappls.sdk.direction.ui.fragment.a aVar = new com.mappls.sdk.direction.ui.fragment.a();
            Bundle bundle = new Bundle();
            bundle.putString("arg_search_data", new Gson().toJson(data));
            aVar.setArguments(bundle);
            aVar.a(new a());
            DirectionFragment.this.replaceFragment(aVar, true);
            aVar.a(DirectionFragment.this.mapView);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ DirectionsResponse f12561a;

        public d(DirectionsResponse directionsResponse) {
            this.f12561a = directionsResponse;
        }

        @Override // com.mappls.sdk.direction.ui.fragment.g
        public final void a(int i) {
            l lVar;
            if (DirectionFragment.this.isAdded() && (lVar = (l) DirectionFragment.this.getChildFragmentManager().findFragmentByTag(l.class.getName())) != null) {
                lVar.a(i, this.f12561a.routes().get(i));
            }
            if (DirectionFragment.this.directionPolylinePlugin != null) {
                DirectionFragment.this.directionPolylinePlugin.a(i);
            }
        }

        @Override // com.mappls.sdk.direction.ui.fragment.g
        public final void a(String str, ArrayList<ReportDetails> arrayList) {
            DirectionFragment.this.replaceFragment(new i(str, arrayList), true);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements d.InterfaceC0616d {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ DirectionsResponse f12562a;
        public final /* synthetic */ int b;

        public e(DirectionsResponse directionsResponse, int i) {
            this.f12562a = directionsResponse;
            this.b = i;
        }

        @Override // com.mappls.sdk.direction.ui.fragment.d.InterfaceC0616d
        public final void a(CostEstimationResponse costEstimationResponse) {
            if (DirectionFragment.this.isAdded()) {
                l lVar = (l) DirectionFragment.this.getChildFragmentManager().findFragmentByTag(l.class.getName());
                if (lVar != null) {
                    lVar.a(costEstimationResponse);
                }
                n nVar = (n) DirectionFragment.this.getChildFragmentManager().findFragmentByTag(n.class.getName());
                if (nVar != null) {
                    nVar.a(costEstimationResponse);
                    return;
                }
                n nVar2 = new n(costEstimationResponse, this.f12562a, this.b);
                nVar2.a(DirectionFragment.this);
                DirectionFragment.this.replaceFragment(nVar2, true);
            }
        }
    }

    private void callFuelCostFragment(CostEstimationResponse costEstimationResponse, DirectionsResponse directionsResponse, int i) {
        com.mappls.sdk.direction.ui.fragment.d dVar = new com.mappls.sdk.direction.ui.fragment.d(costEstimationResponse, directionsResponse, i);
        dVar.a(new e(directionsResponse, i));
        replaceFragment(dVar, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0052, code lost:
        if (r3.theme().intValue() == 2) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
        r3 = com.mappls.sdk.category.model.SearchCategoryUIOption.builder();
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (r0 != 32) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r0 = requireContext();
        r3 = r3.alongRouteDarkTheme();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.mappls.sdk.category.model.SearchCategoryUIOption getUiOption(com.mappls.sdk.direction.ui.model.DirectionOptions r3) {
        /*
            r2 = this;
            java.lang.Integer r0 = r3.theme()
            int r0 = r0.intValue()
            if (r0 != 0) goto L3d
            android.content.res.Resources r0 = r2.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.uiMode
            r0 = r0 & 48
            r1 = 16
            if (r0 == r1) goto L34
            r1 = 32
            if (r0 == r1) goto L1f
            goto L55
        L1f:
            android.content.Context r0 = r2.requireContext()
            java.lang.Integer r3 = r3.alongRouteDarkTheme()
        L27:
            int r3 = r3.intValue()
            com.mappls.sdk.category.model.SearchCategoryUIOption$Builder r3 = com.mappls.sdk.category.model.SearchCategoryUIOption.createFromAttributes(r0, r3)
        L2f:
            com.mappls.sdk.category.model.SearchCategoryUIOption r3 = r3.build()
            return r3
        L34:
            android.content.Context r0 = r2.requireContext()
            java.lang.Integer r3 = r3.alongRouteDayTheme()
            goto L27
        L3d:
            java.lang.Integer r0 = r3.theme()
            int r0 = r0.intValue()
            r1 = 1
            if (r0 != r1) goto L49
            goto L34
        L49:
            java.lang.Integer r0 = r3.theme()
            int r0 = r0.intValue()
            r1 = 2
            if (r0 != r1) goto L55
            goto L1f
        L55:
            com.mappls.sdk.category.model.SearchCategoryUIOption$Builder r3 = com.mappls.sdk.category.model.SearchCategoryUIOption.builder()
            goto L2f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.direction.ui.DirectionFragment.getUiOption(com.mappls.sdk.direction.ui.model.DirectionOptions):com.mappls.sdk.category.model.SearchCategoryUIOption");
    }

    public static DirectionFragment newInstance() {
        return newInstance(DirectionOptions.builder().build());
    }

    public static DirectionFragment newInstance(@NonNull DirectionOptions directionOptions) {
        DirectionFragment directionFragment = new DirectionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.mappls.sdk.direction.ui.DirectionModel", directionOptions);
        directionFragment.setArguments(bundle);
        return directionFragment;
    }

    public void addFragment(Fragment fragment, boolean z) {
        Fragment findFragmentByTag = getChildFragmentManager() != null ? getChildFragmentManager().findFragmentByTag(fragment.getClass().getName()) : null;
        if (fragment == null || findFragmentByTag != null) {
            return;
        }
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.add(this.binding.fragmentContainer.getId(), fragment, fragment.getClass().getName());
        if (z) {
            beginTransaction.addToBackStack(fragment.getClass().getName());
        }
        try {
            beginTransaction.commit();
        } catch (IllegalStateException unused) {
            beginTransaction.commitAllowingStateLoss();
        }
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void clearRoute() {
        com.mappls.sdk.direction.ui.plugin.d dVar = this.directionPolylinePlugin;
        if (dVar != null) {
            dVar.b();
        }
        j jVar = this.mapEventsPlugin;
        if (jVar != null) {
            jVar.b();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void onCancel() {
        DirectionCallback directionCallback = this.mCallback;
        if (directionCallback != null) {
            directionCallback.onCancel();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.binding = (MapplsDirectionLayoutBinding) DataBindingUtil.inflate(layoutInflater, R.layout.mappls_direction_layout, viewGroup, false);
        this.mViewModel = (com.mappls.sdk.direction.ui.b) new ViewModelProvider(this).get(com.mappls.sdk.direction.ui.b.class);
        Bundle arguments = getArguments();
        DirectionOptions directionOptions = this.mViewModel.f12580a;
        if (directionOptions == null) {
            if (bundle != null) {
                this.options = (DirectionOptions) bundle.getParcelable("com.mappls.sdk.direction.ui.DirectionModel");
                bundle.clear();
            } else if (arguments != null) {
                this.options = (DirectionOptions) arguments.getParcelable("com.mappls.sdk.direction.ui.DirectionModel");
                arguments.clear();
            }
            if (this.options == null) {
                this.options = DirectionOptions.builder().build();
            }
            this.mViewModel.f12580a = this.options;
        } else {
            this.options = directionOptions;
        }
        if (this.options.showDefaultMap().booleanValue() && this.mapView == null) {
            MapView mapView = new MapView(this.mContext, MapplsMapOptions.createFromAttributes(requireContext()).textureMode(true));
            this.mapView = mapView;
            this.binding.mapContainer.addView(mapView);
            this.mapView.onCreate(bundle);
        }
        MapView mapView2 = this.mapView;
        if (mapView2 != null) {
            mapView2.getMapAsync(this);
        }
        if (bundle == null) {
            DirectionOptions directionOptions2 = this.options;
            l lVar = new l();
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("com.mappls.sdk.direction.ui.DirectionModel", directionOptions2);
            lVar.setArguments(bundle2);
            addFragment(lVar, false);
            MapView mapView3 = this.mapView;
            if (mapView3 != null) {
                lVar.a(mapView3);
            }
            lVar.a(this);
        } else {
            l lVar2 = (l) getChildFragmentManager().findFragmentByTag(l.class.getName());
            if (lVar2 != null) {
                MapView mapView4 = this.mapView;
                if (mapView4 != null) {
                    lVar2.a(mapView4);
                }
                lVar2.a(this);
            }
        }
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        com.mappls.sdk.direction.ui.plugin.d dVar = this.directionPolylinePlugin;
        if (dVar != null) {
            dVar.b();
            this.directionPolylinePlugin.a();
        }
        j jVar = this.mapEventsPlugin;
        if (jVar != null) {
            jVar.b();
            this.mapEventsPlugin.a();
        }
        if (this.options.showDefaultMap().booleanValue()) {
            this.mapView.onDestroy();
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        if (this.options.showDefaultMap().booleanValue()) {
            this.mapView.onLowMemory();
        }
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapError(int i, String str) {
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapReady(MapplsMap mapplsMap) {
        this.isMapInitialised = true;
        mapplsMap.getStyle(new a(mapplsMap));
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.options.showDefaultMap().booleanValue()) {
            this.mapView.onPause();
        }
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void onPreviewClick(List<LegStep> list, int i, int i2) {
        replaceFragment(new com.mappls.sdk.direction.ui.fragment.b(list, this.mapView, i, i2), true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.options.showDefaultMap().booleanValue()) {
            this.mapView.onResume();
        }
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void onRouteReportSummaryClick(RouteReportSummaryResponse routeReportSummaryResponse, Integer num, DirectionsResponse directionsResponse) {
        RouteSummaryBottomSheetFragment routeSummaryBottomSheetFragment = new RouteSummaryBottomSheetFragment(routeReportSummaryResponse, num, directionsResponse);
        routeSummaryBottomSheetFragment.setRouteSummaryCallback(new d(directionsResponse));
        routeSummaryBottomSheetFragment.show(getChildFragmentManager(), "RouteSummaryBottomSheetFragment");
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.options.showDefaultMap().booleanValue()) {
            this.mapView.onSaveInstanceState(bundle);
        }
        bundle.putParcelable("com.mappls.sdk.direction.ui.DirectionModel", this.options);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.options.showDefaultMap().booleanValue()) {
            this.mapView.onStart();
        }
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void onStartNavigation(DirectionPoint directionPoint, DirectionPoint directionPoint2, List<DirectionPoint> list, DirectionsResponse directionsResponse, int i) {
        DirectionCallback directionCallback = this.mCallback;
        if (directionCallback != null) {
            directionCallback.onStartNavigation(directionPoint, directionPoint2, list, directionsResponse, i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.options.showDefaultMap().booleanValue()) {
            this.mapView.onStop();
        }
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void onTripEstimation(CostEstimationResponse costEstimationResponse, DirectionsResponse directionsResponse, int i) {
        if (costEstimationResponse == null || costEstimationResponse.getTotalTolls() == null || costEstimationResponse.getTotalTolls().intValue() <= 0) {
            callFuelCostFragment(costEstimationResponse, directionsResponse, i);
            return;
        }
        n nVar = new n(costEstimationResponse, directionsResponse, i);
        nVar.a(this);
        replaceFragment(nVar, true);
    }

    @Override // com.mappls.sdk.direction.ui.fragment.n.d
    public void onUpdateFuelCost(CostEstimationResponse costEstimationResponse, DirectionsResponse directionsResponse, int i) {
        callFuelCostFragment(costEstimationResponse, directionsResponse, i);
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void onUpdateRoute(LatLng latLng, LatLng latLng2, List<LatLng> list, List<DirectionsRoute> list2, int i) {
        com.mappls.sdk.direction.ui.plugin.d dVar = this.directionPolylinePlugin;
        if (dVar != null) {
            dVar.a(latLng, latLng2, list, list2, i);
        }
        j jVar = this.mapEventsPlugin;
        if (jVar != null) {
            jVar.b();
        }
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void onUpdateRouteReport(List<RouteReport> list, int i) {
        j jVar = this.mapEventsPlugin;
        if (jVar != null) {
            jVar.a(list.get(i).getReports());
        }
    }

    public void provideMapView(MapView mapView) {
        l lVar;
        this.mapView = mapView;
        if (this.mContext != null && !this.isMapInitialised) {
            mapView.getMapAsync(this);
        }
        if (!isAdded() || (lVar = (l) getChildFragmentManager().findFragmentByTag(l.class.getName())) == null) {
            return;
        }
        lVar.a(mapView);
    }

    public void replaceFragment(Fragment fragment, boolean z) {
        Fragment findFragmentByTag = getChildFragmentManager() != null ? getChildFragmentManager().findFragmentByTag(fragment.getClass().getName()) : null;
        if (fragment == null || findFragmentByTag != null) {
            return;
        }
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(this.binding.fragmentContainer.getId(), fragment, fragment.getClass().getName());
        if (z) {
            beginTransaction.addToBackStack(fragment.getClass().getName());
        }
        try {
            beginTransaction.commit();
        } catch (IllegalStateException unused) {
            beginTransaction.commitAllowingStateLoss();
        }
    }

    @Override // com.mappls.sdk.direction.ui.fragment.h
    public void searchCategory(String str, DirectionOptions directionOptions) {
        SearchCategoryOption.Builder builder = SearchCategoryOption.builder();
        Boolean bool = Boolean.FALSE;
        MapplsCategorySearchFragment newInstance = MapplsCategorySearchFragment.newInstance(builder.isUsingInternalMap(bool).showRequestOnMap(bool).path(str).isSort(Boolean.TRUE).buffer(directionOptions.alongRouteBuffer()).build(), getUiOption(directionOptions));
        replaceFragment(newInstance, true);
        newInstance.provideMapView(this.mapView);
        List<CategoryCode> list = this.categoryCodes;
        if (list != null) {
            newInstance.setCategoryCodes(list);
        }
        newInstance.setPoiResultCallback(new b());
        newInstance.setCategoryCallback(new c());
    }

    public void setCategoryCodes(List<CategoryCode> list) {
        this.categoryCodes = list;
    }

    public void setDirectionCallback(DirectionCallback directionCallback) {
        this.mCallback = directionCallback;
    }

    public void setPoiSearchCallback(POISearchCallback pOISearchCallback) {
        this.mPoiSearchCallback = pOISearchCallback;
    }
}
