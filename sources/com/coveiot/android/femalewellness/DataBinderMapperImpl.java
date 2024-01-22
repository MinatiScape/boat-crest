package com.coveiot.android.femalewellness;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.femalewellness.databinding.ActivityFemaleWellnessFtuBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes4.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYFEMALEWELLNESSFTU = 1;

    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f4367a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(21);
            f4367a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "bindingDataModel1");
            sparseArray.put(2, "currentSelection");
            sparseArray.put(3, "firstCardType");
            sparseArray.put(4, "fitnessType");
            sparseArray.put(5, "ftuItemCount");
            sparseArray.put(6, "healthInfo");
            sparseArray.put(7, "isDataAvailable");
            sparseArray.put(8, "isFirstCardDataAvailable");
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

    /* loaded from: classes4.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f4368a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(1);
            f4368a = hashMap;
            hashMap.put("layout/activity_female_wellness_ftu_0", Integer.valueOf(R.layout.activity_female_wellness_ftu));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(1);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_female_wellness_ftu, 1);
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
        return a.f4367a.get(i);
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
                if ("layout/activity_female_wellness_ftu_0".equals(tag)) {
                    return new ActivityFemaleWellnessFtuBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_female_wellness_ftu is invalid. Received: " + tag);
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f4368a.get(str)) == null) {
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
