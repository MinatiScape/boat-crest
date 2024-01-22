package com.coveiot.android.sportsnotification;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sportsnotification.databinding.ActivitySportsFilterBindingImpl;
import com.coveiot.android.sportsnotification.databinding.FilterOptionListItemBindingImpl;
import com.coveiot.android.sportsnotification.databinding.FragmentFilterOptionsBindingImpl;
import com.coveiot.android.sportsnotification.databinding.SoccerFilterItemBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes7.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYSPORTSFILTER = 1;
    private static final int LAYOUT_FILTEROPTIONLISTITEM = 2;
    private static final int LAYOUT_FRAGMENTFILTEROPTIONS = 3;
    private static final int LAYOUT_SOCCERFILTERITEM = 4;

    /* loaded from: classes7.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f5797a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(19);
            f5797a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "bindingDataModel1");
            sparseArray.put(2, "firstCardType");
            sparseArray.put(3, "fitnessType");
            sparseArray.put(4, "healthInfo");
            sparseArray.put(5, "isDataAvailable");
            sparseArray.put(6, "isFirstCardDataAvailable");
            sparseArray.put(7, "progress");
            sparseArray.put(8, "showAlexaConnect");
            sparseArray.put(9, "showFitnessPlan");
            sparseArray.put(10, "showSOSSettings");
            sparseArray.put(11, "showSportScores");
            sparseArray.put(12, "showTapAndPay");
            sparseArray.put(13, "showWellnessCrew");
            sparseArray.put(14, "stepsDataModel");
            sparseArray.put(15, "stepsGoal");
            sparseArray.put(16, "tipsData");
            sparseArray.put(17, "title");
            sparseArray.put(18, "watchName");
        }
    }

    /* loaded from: classes7.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f5798a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(4);
            f5798a = hashMap;
            hashMap.put("layout/activity_sports_filter_0", Integer.valueOf(R.layout.activity_sports_filter));
            hashMap.put("layout/filter_option_list_item_0", Integer.valueOf(R.layout.filter_option_list_item));
            hashMap.put("layout/fragment_filter_options_0", Integer.valueOf(R.layout.fragment_filter_options));
            hashMap.put("layout/soccer_filter_item_0", Integer.valueOf(R.layout.soccer_filter_item));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(4);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_sports_filter, 1);
        sparseIntArray.put(R.layout.filter_option_list_item, 2);
        sparseIntArray.put(R.layout.fragment_filter_options, 3);
        sparseIntArray.put(R.layout.soccer_filter_item, 4);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f5797a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                if (i2 == 1) {
                    if ("layout/activity_sports_filter_0".equals(tag)) {
                        return new ActivitySportsFilterBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_sports_filter is invalid. Received: " + tag);
                } else if (i2 == 2) {
                    if ("layout/filter_option_list_item_0".equals(tag)) {
                        return new FilterOptionListItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for filter_option_list_item is invalid. Received: " + tag);
                } else if (i2 == 3) {
                    if ("layout/fragment_filter_options_0".equals(tag)) {
                        return new FragmentFilterOptionsBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_filter_options is invalid. Received: " + tag);
                } else if (i2 != 4) {
                    return null;
                } else {
                    if ("layout/soccer_filter_item_0".equals(tag)) {
                        return new SoccerFilterItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for soccer_filter_item is invalid. Received: " + tag);
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f5798a.get(str)) == null) {
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
