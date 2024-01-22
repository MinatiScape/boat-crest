package com.mappls.sdk.nearby.plugin;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyActivityBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyBaseFragmentBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyCategoryAdapterBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyFragmentBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyFragmentMapBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyFragmentResultListBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultCategoryAdapterBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultFragmentBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultListAdapterBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultViewBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultViewBindingLandImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyViewBindingImpl;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyViewBindingLandImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes10.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_MAPPLSNEARBYACTIVITY = 1;
    private static final int LAYOUT_MAPPLSNEARBYBASEFRAGMENT = 2;
    private static final int LAYOUT_MAPPLSNEARBYCATEGORYADAPTER = 3;
    private static final int LAYOUT_MAPPLSNEARBYFRAGMENT = 4;
    private static final int LAYOUT_MAPPLSNEARBYFRAGMENTMAP = 5;
    private static final int LAYOUT_MAPPLSNEARBYFRAGMENTRESULTLIST = 6;
    private static final int LAYOUT_MAPPLSNEARBYRESULTCATEGORYADAPTER = 7;
    private static final int LAYOUT_MAPPLSNEARBYRESULTFRAGMENT = 8;
    private static final int LAYOUT_MAPPLSNEARBYRESULTLISTADAPTER = 9;
    private static final int LAYOUT_MAPPLSNEARBYRESULTVIEW = 10;
    private static final int LAYOUT_MAPPLSNEARBYVIEW = 11;

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f13054a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(1);
            f13054a = sparseArray;
            sparseArray.put(0, "_all");
        }
    }

    /* loaded from: classes10.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f13055a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(13);
            f13055a = hashMap;
            hashMap.put("layout/mappls_nearby_activity_0", Integer.valueOf(R.layout.mappls_nearby_activity));
            hashMap.put("layout/mappls_nearby_base_fragment_0", Integer.valueOf(R.layout.mappls_nearby_base_fragment));
            hashMap.put("layout/mappls_nearby_category_adapter_0", Integer.valueOf(R.layout.mappls_nearby_category_adapter));
            hashMap.put("layout/mappls_nearby_fragment_0", Integer.valueOf(R.layout.mappls_nearby_fragment));
            hashMap.put("layout/mappls_nearby_fragment_map_0", Integer.valueOf(R.layout.mappls_nearby_fragment_map));
            hashMap.put("layout/mappls_nearby_fragment_result_list_0", Integer.valueOf(R.layout.mappls_nearby_fragment_result_list));
            hashMap.put("layout/mappls_nearby_result_category_adapter_0", Integer.valueOf(R.layout.mappls_nearby_result_category_adapter));
            hashMap.put("layout/mappls_nearby_result_fragment_0", Integer.valueOf(R.layout.mappls_nearby_result_fragment));
            hashMap.put("layout/mappls_nearby_result_list_adapter_0", Integer.valueOf(R.layout.mappls_nearby_result_list_adapter));
            int i = R.layout.mappls_nearby_result_view;
            hashMap.put("layout-land/mappls_nearby_result_view_0", Integer.valueOf(i));
            hashMap.put("layout/mappls_nearby_result_view_0", Integer.valueOf(i));
            int i2 = R.layout.mappls_nearby_view;
            hashMap.put("layout-land/mappls_nearby_view_0", Integer.valueOf(i2));
            hashMap.put("layout/mappls_nearby_view_0", Integer.valueOf(i2));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(11);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.mappls_nearby_activity, 1);
        sparseIntArray.put(R.layout.mappls_nearby_base_fragment, 2);
        sparseIntArray.put(R.layout.mappls_nearby_category_adapter, 3);
        sparseIntArray.put(R.layout.mappls_nearby_fragment, 4);
        sparseIntArray.put(R.layout.mappls_nearby_fragment_map, 5);
        sparseIntArray.put(R.layout.mappls_nearby_fragment_result_list, 6);
        sparseIntArray.put(R.layout.mappls_nearby_result_category_adapter, 7);
        sparseIntArray.put(R.layout.mappls_nearby_result_fragment, 8);
        sparseIntArray.put(R.layout.mappls_nearby_result_list_adapter, 9);
        sparseIntArray.put(R.layout.mappls_nearby_result_view, 10);
        sparseIntArray.put(R.layout.mappls_nearby_view, 11);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f13054a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/mappls_nearby_activity_0".equals(tag)) {
                            return new MapplsNearbyActivityBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_activity is invalid. Received: " + tag);
                    case 2:
                        if ("layout/mappls_nearby_base_fragment_0".equals(tag)) {
                            return new MapplsNearbyBaseFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_base_fragment is invalid. Received: " + tag);
                    case 3:
                        if ("layout/mappls_nearby_category_adapter_0".equals(tag)) {
                            return new MapplsNearbyCategoryAdapterBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_category_adapter is invalid. Received: " + tag);
                    case 4:
                        if ("layout/mappls_nearby_fragment_0".equals(tag)) {
                            return new MapplsNearbyFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_fragment is invalid. Received: " + tag);
                    case 5:
                        if ("layout/mappls_nearby_fragment_map_0".equals(tag)) {
                            return new MapplsNearbyFragmentMapBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_fragment_map is invalid. Received: " + tag);
                    case 6:
                        if ("layout/mappls_nearby_fragment_result_list_0".equals(tag)) {
                            return new MapplsNearbyFragmentResultListBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_fragment_result_list is invalid. Received: " + tag);
                    case 7:
                        if ("layout/mappls_nearby_result_category_adapter_0".equals(tag)) {
                            return new MapplsNearbyResultCategoryAdapterBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_result_category_adapter is invalid. Received: " + tag);
                    case 8:
                        if ("layout/mappls_nearby_result_fragment_0".equals(tag)) {
                            return new MapplsNearbyResultFragmentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_result_fragment is invalid. Received: " + tag);
                    case 9:
                        if ("layout/mappls_nearby_result_list_adapter_0".equals(tag)) {
                            return new MapplsNearbyResultListAdapterBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_result_list_adapter is invalid. Received: " + tag);
                    case 10:
                        if ("layout-land/mappls_nearby_result_view_0".equals(tag)) {
                            return new MapplsNearbyResultViewBindingLandImpl(dataBindingComponent, view);
                        }
                        if ("layout/mappls_nearby_result_view_0".equals(tag)) {
                            return new MapplsNearbyResultViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_result_view is invalid. Received: " + tag);
                    case 11:
                        if ("layout-land/mappls_nearby_view_0".equals(tag)) {
                            return new MapplsNearbyViewBindingLandImpl(dataBindingComponent, view);
                        }
                        if ("layout/mappls_nearby_view_0".equals(tag)) {
                            return new MapplsNearbyViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for mappls_nearby_view is invalid. Received: " + tag);
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
        if (str == null || (num = b.f13055a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }
}
