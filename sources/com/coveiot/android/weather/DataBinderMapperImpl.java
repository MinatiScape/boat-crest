package com.coveiot.android.weather;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.weather.databinding.FragmentWeatherSettingBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes8.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_FRAGMENTWEATHERSETTING = 1;

    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f6171a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(21);
            f6171a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "bindingDataModel1");
            sparseArray.put(2, "firstCardType");
            sparseArray.put(3, "fitnessType");
            sparseArray.put(4, "healthInfo");
            sparseArray.put(5, "isDataAvailable");
            sparseArray.put(6, "isFirstCardDataAvailable");
            sparseArray.put(7, "isMetricUnitData");
            sparseArray.put(8, "isWeatherEnableData");
            sparseArray.put(9, "progress");
            sparseArray.put(10, "showAlexaConnect");
            sparseArray.put(11, "showFitnessPlan");
            sparseArray.put(12, "showSOSSettings");
            sparseArray.put(13, "showSportScores");
            sparseArray.put(14, "showTapAndPay");
            sparseArray.put(15, "showWellnessCrew");
            sparseArray.put(16, "stepsDataModel");
            sparseArray.put(17, "stepsGoal");
            sparseArray.put(18, "tipsData");
            sparseArray.put(19, "title");
            sparseArray.put(20, "watchName");
        }
    }

    /* loaded from: classes8.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f6172a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(1);
            f6172a = hashMap;
            hashMap.put("layout/fragment_weather_setting_0", Integer.valueOf(R.layout.fragment_weather_setting));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(1);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.fragment_weather_setting, 1);
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
        return a.f6171a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                if (i2 != 1) {
                    return null;
                }
                if ("layout/fragment_weather_setting_0".equals(tag)) {
                    return new FragmentWeatherSettingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_weather_setting is invalid. Received: " + tag);
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f6172a.get(str)) == null) {
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
