package com.mappls.sdk.category;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.category.databinding.MapplsCategoryResultFragmentBindingImpl;
import com.mappls.sdk.category.databinding.MapplsCategorySearchFragmentBindingImpl;
import com.mappls.sdk.category.databinding.MapplsCategorySearchItemFragmentBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes11.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_MAPPLSCATEGORYRESULTFRAGMENT = 1;
    private static final int LAYOUT_MAPPLSCATEGORYSEARCHFRAGMENT = 2;
    private static final int LAYOUT_MAPPLSCATEGORYSEARCHITEMFRAGMENT = 3;

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f12534a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(1);
            f12534a = sparseArray;
            sparseArray.put(0, "_all");
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f12535a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(3);
            f12535a = hashMap;
            hashMap.put("layout/mappls_category_result_fragment_0", Integer.valueOf(R.layout.mappls_category_result_fragment));
            hashMap.put("layout/mappls_category_search_fragment_0", Integer.valueOf(R.layout.mappls_category_search_fragment));
            hashMap.put("layout/mappls_category_search_item_fragment_0", Integer.valueOf(R.layout.mappls_category_search_item_fragment));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(3);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.mappls_category_result_fragment, 1);
        sparseIntArray.put(R.layout.mappls_category_search_fragment, 2);
        sparseIntArray.put(R.layout.mappls_category_search_item_fragment, 3);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.nearby.plugin.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f12534a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                if (i2 == 1) {
                    if ("layout/mappls_category_result_fragment_0".equals(tag)) {
                        return new MapplsCategoryResultFragmentBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for mappls_category_result_fragment is invalid. Received: " + tag);
                } else if (i2 == 2) {
                    if ("layout/mappls_category_search_fragment_0".equals(tag)) {
                        return new MapplsCategorySearchFragmentBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for mappls_category_search_fragment is invalid. Received: " + tag);
                } else if (i2 != 3) {
                    return null;
                } else {
                    if ("layout/mappls_category_search_item_fragment_0".equals(tag)) {
                        return new MapplsCategorySearchItemFragmentBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for mappls_category_search_item_fragment is invalid. Received: " + tag);
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
        if (str == null || (num = b.f12535a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }
}
