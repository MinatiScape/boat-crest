package com.coveiot.android.navigation;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.navigation.databinding.ActivityAddOrEditFavouriteLocationBindingImpl;
import com.coveiot.android.navigation.databinding.ActivityDirectionsBindingImpl;
import com.coveiot.android.navigation.databinding.ActivityNavigationFtuBindingImpl;
import com.coveiot.android.navigation.databinding.ActivityNavigationMainBindingImpl;
import com.coveiot.android.navigation.databinding.ActivityNavigationRecentHistoryBindingImpl;
import com.coveiot.android.navigation.databinding.ActivityNavigationSearchPlacesBindingImpl;
import com.coveiot.android.navigation.databinding.ActivityNavigationSettingsBindingImpl;
import com.coveiot.android.navigation.databinding.ActivityNavigationShowRouteBindingImpl;
import com.coveiot.android.navigation.databinding.ActivityYourPlacesBindingImpl;
import com.coveiot.android.navigation.databinding.FragmentDisclaimerBindingImpl;
import com.coveiot.android.navigation.databinding.FragmentNavigatFtuBindingImpl;
import com.coveiot.android.navigation.databinding.ItemLayoutDirectionsBindingImpl;
import com.coveiot.android.navigation.databinding.ItemLayoutRecentHistoryBindingImpl;
import com.coveiot.android.navigation.databinding.ItemSearchPlacesBindingImpl;
import com.coveiot.android.navigation.databinding.ItemYourPlacesBindingImpl;
import com.coveiot.android.navigation.databinding.PlacesItemLayoutBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes5.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYADDOREDITFAVOURITELOCATION = 1;
    private static final int LAYOUT_ACTIVITYDIRECTIONS = 2;
    private static final int LAYOUT_ACTIVITYNAVIGATIONFTU = 3;
    private static final int LAYOUT_ACTIVITYNAVIGATIONMAIN = 4;
    private static final int LAYOUT_ACTIVITYNAVIGATIONRECENTHISTORY = 5;
    private static final int LAYOUT_ACTIVITYNAVIGATIONSEARCHPLACES = 6;
    private static final int LAYOUT_ACTIVITYNAVIGATIONSETTINGS = 7;
    private static final int LAYOUT_ACTIVITYNAVIGATIONSHOWROUTE = 8;
    private static final int LAYOUT_ACTIVITYYOURPLACES = 9;
    private static final int LAYOUT_FRAGMENTDISCLAIMER = 10;
    private static final int LAYOUT_FRAGMENTNAVIGATFTU = 11;
    private static final int LAYOUT_ITEMLAYOUTDIRECTIONS = 12;
    private static final int LAYOUT_ITEMLAYOUTRECENTHISTORY = 13;
    private static final int LAYOUT_ITEMSEARCHPLACES = 14;
    private static final int LAYOUT_ITEMYOURPLACES = 15;
    private static final int LAYOUT_PLACESITEMLAYOUT = 16;

    /* loaded from: classes5.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f5470a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(35);
            f5470a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "arrival");
            sparseArray.put(2, "bindingDataModel1");
            sparseArray.put(3, "destinationRouteLocation");
            sparseArray.put(4, "distance");
            sparseArray.put(5, "firstCardType");
            sparseArray.put(6, "fitnessType");
            sparseArray.put(7, "healthInfo");
            sparseArray.put(8, "isDataAvailable");
            sparseArray.put(9, "isFirstCardDataAvailable");
            sparseArray.put(10, "onClickGetRoute");
            sparseArray.put(11, "onClickSearchCategory");
            sparseArray.put(12, "onHandleBack");
            sparseArray.put(13, "onNextClick");
            sparseArray.put(14, "onPreviousClick");
            sparseArray.put(15, "onRouteReportClick");
            sparseArray.put(16, "onStartClick");
            sparseArray.put(17, "onclickHandleBack");
            sparseArray.put(18, "progress");
            sparseArray.put(19, "retryButtonClick");
            sparseArray.put(20, "routeTime");
            sparseArray.put(21, "showAlexaConnect");
            sparseArray.put(22, "showFitnessPlan");
            sparseArray.put(23, "showSOSSettings");
            sparseArray.put(24, "showSportScores");
            sparseArray.put(25, "showTapAndPay");
            sparseArray.put(26, "showWellnessCrew");
            sparseArray.put(27, "sourceRouteLocation");
            sparseArray.put(28, "startButtonClick");
            sparseArray.put(29, "stepsDataModel");
            sparseArray.put(30, "stepsGoal");
            sparseArray.put(31, "tipsData");
            sparseArray.put(32, "title");
            sparseArray.put(33, "watchName");
            sparseArray.put(34, "wayPoints");
        }
    }

    /* loaded from: classes5.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f5471a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(16);
            f5471a = hashMap;
            hashMap.put("layout/activity_add_or_edit_favourite_location_0", Integer.valueOf(R.layout.activity_add_or_edit_favourite_location));
            hashMap.put("layout/activity_directions_0", Integer.valueOf(R.layout.activity_directions));
            hashMap.put("layout/activity_navigation_ftu_0", Integer.valueOf(R.layout.activity_navigation_ftu));
            hashMap.put("layout/activity_navigation_main_0", Integer.valueOf(R.layout.activity_navigation_main));
            hashMap.put("layout/activity_navigation_recent_history_0", Integer.valueOf(R.layout.activity_navigation_recent_history));
            hashMap.put("layout/activity_navigation_search_places_0", Integer.valueOf(R.layout.activity_navigation_search_places));
            hashMap.put("layout/activity_navigation_settings_0", Integer.valueOf(R.layout.activity_navigation_settings));
            hashMap.put("layout/activity_navigation_show_route_0", Integer.valueOf(R.layout.activity_navigation_show_route));
            hashMap.put("layout/activity_your_places_0", Integer.valueOf(R.layout.activity_your_places));
            hashMap.put("layout/fragment_disclaimer_0", Integer.valueOf(R.layout.fragment_disclaimer));
            hashMap.put("layout/fragment_navigat_ftu_0", Integer.valueOf(R.layout.fragment_navigat_ftu));
            hashMap.put("layout/item_layout_directions_0", Integer.valueOf(R.layout.item_layout_directions));
            hashMap.put("layout/item_layout_recent_history_0", Integer.valueOf(R.layout.item_layout_recent_history));
            hashMap.put("layout/item_search_places_0", Integer.valueOf(R.layout.item_search_places));
            hashMap.put("layout/item_your_places_0", Integer.valueOf(R.layout.item_your_places));
            hashMap.put("layout/places_item_layout_0", Integer.valueOf(R.layout.places_item_layout));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(16);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_add_or_edit_favourite_location, 1);
        sparseIntArray.put(R.layout.activity_directions, 2);
        sparseIntArray.put(R.layout.activity_navigation_ftu, 3);
        sparseIntArray.put(R.layout.activity_navigation_main, 4);
        sparseIntArray.put(R.layout.activity_navigation_recent_history, 5);
        sparseIntArray.put(R.layout.activity_navigation_search_places, 6);
        sparseIntArray.put(R.layout.activity_navigation_settings, 7);
        sparseIntArray.put(R.layout.activity_navigation_show_route, 8);
        sparseIntArray.put(R.layout.activity_your_places, 9);
        sparseIntArray.put(R.layout.fragment_disclaimer, 10);
        sparseIntArray.put(R.layout.fragment_navigat_ftu, 11);
        sparseIntArray.put(R.layout.item_layout_directions, 12);
        sparseIntArray.put(R.layout.item_layout_recent_history, 13);
        sparseIntArray.put(R.layout.item_search_places, 14);
        sparseIntArray.put(R.layout.item_your_places, 15);
        sparseIntArray.put(R.layout.places_item_layout, 16);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(7);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.permissionsandsettings.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.category.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.direction.ui.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.navigation.ui.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.nearby.plugin.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f5470a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/activity_add_or_edit_favourite_location_0".equals(tag)) {
                            return new ActivityAddOrEditFavouriteLocationBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_add_or_edit_favourite_location is invalid. Received: " + tag);
                    case 2:
                        if ("layout/activity_directions_0".equals(tag)) {
                            return new ActivityDirectionsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_directions is invalid. Received: " + tag);
                    case 3:
                        if ("layout/activity_navigation_ftu_0".equals(tag)) {
                            return new ActivityNavigationFtuBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_navigation_ftu is invalid. Received: " + tag);
                    case 4:
                        if ("layout/activity_navigation_main_0".equals(tag)) {
                            return new ActivityNavigationMainBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_navigation_main is invalid. Received: " + tag);
                    case 5:
                        if ("layout/activity_navigation_recent_history_0".equals(tag)) {
                            return new ActivityNavigationRecentHistoryBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_navigation_recent_history is invalid. Received: " + tag);
                    case 6:
                        if ("layout/activity_navigation_search_places_0".equals(tag)) {
                            return new ActivityNavigationSearchPlacesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_navigation_search_places is invalid. Received: " + tag);
                    case 7:
                        if ("layout/activity_navigation_settings_0".equals(tag)) {
                            return new ActivityNavigationSettingsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_navigation_settings is invalid. Received: " + tag);
                    case 8:
                        if ("layout/activity_navigation_show_route_0".equals(tag)) {
                            return new ActivityNavigationShowRouteBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_navigation_show_route is invalid. Received: " + tag);
                    case 9:
                        if ("layout/activity_your_places_0".equals(tag)) {
                            return new ActivityYourPlacesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_your_places is invalid. Received: " + tag);
                    case 10:
                        if ("layout/fragment_disclaimer_0".equals(tag)) {
                            return new FragmentDisclaimerBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_disclaimer is invalid. Received: " + tag);
                    case 11:
                        if ("layout/fragment_navigat_ftu_0".equals(tag)) {
                            return new FragmentNavigatFtuBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_navigat_ftu is invalid. Received: " + tag);
                    case 12:
                        if ("layout/item_layout_directions_0".equals(tag)) {
                            return new ItemLayoutDirectionsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for item_layout_directions is invalid. Received: " + tag);
                    case 13:
                        if ("layout/item_layout_recent_history_0".equals(tag)) {
                            return new ItemLayoutRecentHistoryBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for item_layout_recent_history is invalid. Received: " + tag);
                    case 14:
                        if ("layout/item_search_places_0".equals(tag)) {
                            return new ItemSearchPlacesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for item_search_places is invalid. Received: " + tag);
                    case 15:
                        if ("layout/item_your_places_0".equals(tag)) {
                            return new ItemYourPlacesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for item_your_places is invalid. Received: " + tag);
                    case 16:
                        if ("layout/places_item_layout_0".equals(tag)) {
                            return new PlacesItemLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for places_item_layout is invalid. Received: " + tag);
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f5471a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
