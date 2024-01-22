package com.mappls.sdk.direction.ui;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionCommonToolbarBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionFragmentAddWayPointDialogBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionFuelCostFragmentBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionFuelTypeAdapterBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionItemStopBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionItemTollRowLayoutBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionLayoutBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionLayoutCollapsedRouteViewBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionListHeaderBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionPreviewFragmentBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionPreviewPagerBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteEventFragmentBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteSummaryAllEventFragmentBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteSummaryFragmentBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteSummaryHeaderBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteSummaryItemBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteViewBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionStepAdapterBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionTollCostFragmentBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionWidgetBindingImpl;
import com.mappls.sdk.direction.ui.databinding.MapplsRoutingLayoutBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes11.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_MAPPLSDIRECTIONCOMMONTOOLBAR = 1;
    private static final int LAYOUT_MAPPLSDIRECTIONFRAGMENTADDWAYPOINTDIALOG = 2;
    private static final int LAYOUT_MAPPLSDIRECTIONFUELCOSTFRAGMENT = 3;
    private static final int LAYOUT_MAPPLSDIRECTIONFUELTYPEADAPTER = 4;
    private static final int LAYOUT_MAPPLSDIRECTIONITEMSTOP = 5;
    private static final int LAYOUT_MAPPLSDIRECTIONITEMTOLLROWLAYOUT = 6;
    private static final int LAYOUT_MAPPLSDIRECTIONLAYOUT = 7;
    private static final int LAYOUT_MAPPLSDIRECTIONLAYOUTCOLLAPSEDROUTEVIEW = 8;
    private static final int LAYOUT_MAPPLSDIRECTIONLISTHEADER = 9;
    private static final int LAYOUT_MAPPLSDIRECTIONPREVIEWFRAGMENT = 10;
    private static final int LAYOUT_MAPPLSDIRECTIONPREVIEWPAGER = 11;
    private static final int LAYOUT_MAPPLSDIRECTIONROUTEEVENTFRAGMENT = 12;
    private static final int LAYOUT_MAPPLSDIRECTIONROUTESUMMARYALLEVENTFRAGMENT = 13;
    private static final int LAYOUT_MAPPLSDIRECTIONROUTESUMMARYFRAGMENT = 14;
    private static final int LAYOUT_MAPPLSDIRECTIONROUTESUMMARYHEADER = 15;
    private static final int LAYOUT_MAPPLSDIRECTIONROUTESUMMARYITEM = 16;
    private static final int LAYOUT_MAPPLSDIRECTIONROUTEVIEW = 17;
    private static final int LAYOUT_MAPPLSDIRECTIONSTEPADAPTER = 18;
    private static final int LAYOUT_MAPPLSDIRECTIONTOLLCOSTFRAGMENT = 19;
    private static final int LAYOUT_MAPPLSDIRECTIONWIDGET = 20;
    private static final int LAYOUT_MAPPLSROUTINGLAYOUT = 21;

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f12554a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(17);
            f12554a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "arrival");
            sparseArray.put(2, "destinationRouteLocation");
            sparseArray.put(3, "distance");
            sparseArray.put(4, "onClickGetRoute");
            sparseArray.put(5, "onClickSearchCategory");
            sparseArray.put(6, "onHandleBack");
            sparseArray.put(7, "onNextClick");
            sparseArray.put(8, "onPreviousClick");
            sparseArray.put(9, "onRouteReportClick");
            sparseArray.put(10, "onStartClick");
            sparseArray.put(11, "onclickHandleBack");
            sparseArray.put(12, "retryButtonClick");
            sparseArray.put(13, "routeTime");
            sparseArray.put(14, "sourceRouteLocation");
            sparseArray.put(15, "startButtonClick");
            sparseArray.put(16, "wayPoints");
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f12555a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(21);
            f12555a = hashMap;
            hashMap.put("layout/mappls_direction_common_toolbar_0", Integer.valueOf(R.layout.mappls_direction_common_toolbar));
            hashMap.put("layout/mappls_direction_fragment_add_way_point_dialog_0", Integer.valueOf(R.layout.mappls_direction_fragment_add_way_point_dialog));
            hashMap.put("layout/mappls_direction_fuel_cost_fragment_0", Integer.valueOf(R.layout.mappls_direction_fuel_cost_fragment));
            hashMap.put("layout/mappls_direction_fuel_type_adapter_0", Integer.valueOf(R.layout.mappls_direction_fuel_type_adapter));
            hashMap.put("layout/mappls_direction_item_stop_0", Integer.valueOf(R.layout.mappls_direction_item_stop));
            hashMap.put("layout/mappls_direction_item_toll_row_layout_0", Integer.valueOf(R.layout.mappls_direction_item_toll_row_layout));
            hashMap.put("layout/mappls_direction_layout_0", Integer.valueOf(R.layout.mappls_direction_layout));
            hashMap.put("layout/mappls_direction_layout_collapsed_route_view_0", Integer.valueOf(R.layout.mappls_direction_layout_collapsed_route_view));
            hashMap.put("layout/mappls_direction_list_header_0", Integer.valueOf(R.layout.mappls_direction_list_header));
            hashMap.put("layout/mappls_direction_preview_fragment_0", Integer.valueOf(R.layout.mappls_direction_preview_fragment));
            hashMap.put("layout/mappls_direction_preview_pager_0", Integer.valueOf(R.layout.mappls_direction_preview_pager));
            hashMap.put("layout/mappls_direction_route_event_fragment_0", Integer.valueOf(R.layout.mappls_direction_route_event_fragment));
            hashMap.put("layout/mappls_direction_route_summary_all_event_fragment_0", Integer.valueOf(R.layout.mappls_direction_route_summary_all_event_fragment));
            hashMap.put("layout/mappls_direction_route_summary_fragment_0", Integer.valueOf(R.layout.mappls_direction_route_summary_fragment));
            hashMap.put("layout/mappls_direction_route_summary_header_0", Integer.valueOf(R.layout.mappls_direction_route_summary_header));
            hashMap.put("layout/mappls_direction_route_summary_item_0", Integer.valueOf(R.layout.mappls_direction_route_summary_item));
            hashMap.put("layout/mappls_direction_route_view_0", Integer.valueOf(R.layout.mappls_direction_route_view));
            hashMap.put("layout/mappls_direction_step_adapter_0", Integer.valueOf(R.layout.mappls_direction_step_adapter));
            hashMap.put("layout/mappls_direction_toll_cost_fragment_0", Integer.valueOf(R.layout.mappls_direction_toll_cost_fragment));
            hashMap.put("layout/mappls_direction_widget_0", Integer.valueOf(R.layout.mappls_direction_widget));
            hashMap.put("layout/mappls_routing_layout_0", Integer.valueOf(R.layout.mappls_routing_layout));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(21);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.mappls_direction_common_toolbar, 1);
        sparseIntArray.put(R.layout.mappls_direction_fragment_add_way_point_dialog, 2);
        sparseIntArray.put(R.layout.mappls_direction_fuel_cost_fragment, 3);
        sparseIntArray.put(R.layout.mappls_direction_fuel_type_adapter, 4);
        sparseIntArray.put(R.layout.mappls_direction_item_stop, 5);
        sparseIntArray.put(R.layout.mappls_direction_item_toll_row_layout, 6);
        sparseIntArray.put(R.layout.mappls_direction_layout, 7);
        sparseIntArray.put(R.layout.mappls_direction_layout_collapsed_route_view, 8);
        sparseIntArray.put(R.layout.mappls_direction_list_header, 9);
        sparseIntArray.put(R.layout.mappls_direction_preview_fragment, 10);
        sparseIntArray.put(R.layout.mappls_direction_preview_pager, 11);
        sparseIntArray.put(R.layout.mappls_direction_route_event_fragment, 12);
        sparseIntArray.put(R.layout.mappls_direction_route_summary_all_event_fragment, 13);
        sparseIntArray.put(R.layout.mappls_direction_route_summary_fragment, 14);
        sparseIntArray.put(R.layout.mappls_direction_route_summary_header, 15);
        sparseIntArray.put(R.layout.mappls_direction_route_summary_item, 16);
        sparseIntArray.put(R.layout.mappls_direction_route_view, 17);
        sparseIntArray.put(R.layout.mappls_direction_step_adapter, 18);
        sparseIntArray.put(R.layout.mappls_direction_toll_cost_fragment, 19);
        sparseIntArray.put(R.layout.mappls_direction_widget, 20);
        sparseIntArray.put(R.layout.mappls_routing_layout, 21);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.category.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.nearby.plugin.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f12554a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/mappls_direction_common_toolbar_0".equals(tag)) {
                            return new MapplsDirectionCommonToolbarBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_common_toolbar is invalid. Received: ", tag));
                    case 2:
                        if ("layout/mappls_direction_fragment_add_way_point_dialog_0".equals(tag)) {
                            return new MapplsDirectionFragmentAddWayPointDialogBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_fragment_add_way_point_dialog is invalid. Received: ", tag));
                    case 3:
                        if ("layout/mappls_direction_fuel_cost_fragment_0".equals(tag)) {
                            return new MapplsDirectionFuelCostFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_fuel_cost_fragment is invalid. Received: ", tag));
                    case 4:
                        if ("layout/mappls_direction_fuel_type_adapter_0".equals(tag)) {
                            return new MapplsDirectionFuelTypeAdapterBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_fuel_type_adapter is invalid. Received: ", tag));
                    case 5:
                        if ("layout/mappls_direction_item_stop_0".equals(tag)) {
                            return new MapplsDirectionItemStopBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_item_stop is invalid. Received: ", tag));
                    case 6:
                        if ("layout/mappls_direction_item_toll_row_layout_0".equals(tag)) {
                            return new MapplsDirectionItemTollRowLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_item_toll_row_layout is invalid. Received: ", tag));
                    case 7:
                        if ("layout/mappls_direction_layout_0".equals(tag)) {
                            return new MapplsDirectionLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_layout is invalid. Received: ", tag));
                    case 8:
                        if ("layout/mappls_direction_layout_collapsed_route_view_0".equals(tag)) {
                            return new MapplsDirectionLayoutCollapsedRouteViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_layout_collapsed_route_view is invalid. Received: ", tag));
                    case 9:
                        if ("layout/mappls_direction_list_header_0".equals(tag)) {
                            return new MapplsDirectionListHeaderBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_list_header is invalid. Received: ", tag));
                    case 10:
                        if ("layout/mappls_direction_preview_fragment_0".equals(tag)) {
                            return new MapplsDirectionPreviewFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_preview_fragment is invalid. Received: ", tag));
                    case 11:
                        if ("layout/mappls_direction_preview_pager_0".equals(tag)) {
                            return new MapplsDirectionPreviewPagerBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_preview_pager is invalid. Received: ", tag));
                    case 12:
                        if ("layout/mappls_direction_route_event_fragment_0".equals(tag)) {
                            return new MapplsDirectionRouteEventFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_route_event_fragment is invalid. Received: ", tag));
                    case 13:
                        if ("layout/mappls_direction_route_summary_all_event_fragment_0".equals(tag)) {
                            return new MapplsDirectionRouteSummaryAllEventFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_route_summary_all_event_fragment is invalid. Received: ", tag));
                    case 14:
                        if ("layout/mappls_direction_route_summary_fragment_0".equals(tag)) {
                            return new MapplsDirectionRouteSummaryFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_route_summary_fragment is invalid. Received: ", tag));
                    case 15:
                        if ("layout/mappls_direction_route_summary_header_0".equals(tag)) {
                            return new MapplsDirectionRouteSummaryHeaderBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_route_summary_header is invalid. Received: ", tag));
                    case 16:
                        if ("layout/mappls_direction_route_summary_item_0".equals(tag)) {
                            return new MapplsDirectionRouteSummaryItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_route_summary_item is invalid. Received: ", tag));
                    case 17:
                        if ("layout/mappls_direction_route_view_0".equals(tag)) {
                            return new MapplsDirectionRouteViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_route_view is invalid. Received: ", tag));
                    case 18:
                        if ("layout/mappls_direction_step_adapter_0".equals(tag)) {
                            return new MapplsDirectionStepAdapterBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_step_adapter is invalid. Received: ", tag));
                    case 19:
                        if ("layout/mappls_direction_toll_cost_fragment_0".equals(tag)) {
                            return new MapplsDirectionTollCostFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_toll_cost_fragment is invalid. Received: ", tag));
                    case 20:
                        if ("layout/mappls_direction_widget_0".equals(tag)) {
                            return new MapplsDirectionWidgetBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_direction_widget is invalid. Received: ", tag));
                    case 21:
                        if ("layout/mappls_routing_layout_0".equals(tag)) {
                            return new MapplsRoutingLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(com.mappls.sdk.direction.ui.a.a("The tag for mappls_routing_layout is invalid. Received: ", tag));
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f12555a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }
}
