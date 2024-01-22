package com.coveiot.android.sleepenergyscore;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sleepenergyscore.databinding.DrainGainTableRowLegendBindingImpl;
import com.coveiot.android.sleepenergyscore.databinding.FragmentVitalEnergyMeterBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes6.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_DRAINGAINTABLEROWLEGEND = 1;
    private static final int LAYOUT_FRAGMENTVITALENERGYMETER = 2;

    /* loaded from: classes6.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f5689a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(42);
            f5689a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "activitiesCompleted");
            sparseArray.put(2, "bindingDataModel1");
            sparseArray.put(3, "completedCalories");
            sparseArray.put(4, "completedDistance");
            sparseArray.put(5, "dayCount");
            sparseArray.put(6, "dayProgress");
            sparseArray.put(7, "dayProgressMax");
            sparseArray.put(8, "distanceCovered");
            sparseArray.put(9, "firstCardType");
            sparseArray.put(10, "fitnessType");
            sparseArray.put(11, "futureDayInfo");
            sparseArray.put(12, "goalCategory");
            sparseArray.put(13, "healthInfo");
            sparseArray.put(14, "isDataAvailable");
            sparseArray.put(15, "isFirstCardDataAvailable");
            sparseArray.put(16, "isFutureDay");
            sparseArray.put(17, "isHistoryPlan");
            sparseArray.put(18, "isPlanCompleted");
            sparseArray.put(19, "isPlanStartsTomorrow");
            sparseArray.put(20, "isProgressFull");
            sparseArray.put(21, "isRestDay");
            sparseArray.put(22, "planBg");
            sparseArray.put(23, "planHeader");
            sparseArray.put(24, "planHistoryData");
            sparseArray.put(25, "planTitle");
            sparseArray.put(26, "progress");
            sparseArray.put(27, "progressText");
            sparseArray.put(28, "showAlexaConnect");
            sparseArray.put(29, "showFitnessPlan");
            sparseArray.put(30, "showSOSSettings");
            sparseArray.put(31, "showSportScores");
            sparseArray.put(32, "showTapAndPay");
            sparseArray.put(33, "showWellnessCrew");
            sparseArray.put(34, "stepsDataModel");
            sparseArray.put(35, "stepsGoal");
            sparseArray.put(36, "tipsData");
            sparseArray.put(37, "title");
            sparseArray.put(38, "totalActivities");
            sparseArray.put(39, "totalActivitiesAndDistance");
            sparseArray.put(40, "watchName");
            sparseArray.put(41, "weekCount");
        }
    }

    /* loaded from: classes6.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f5690a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(2);
            f5690a = hashMap;
            hashMap.put("layout/drain_gain_table_row_legend_0", Integer.valueOf(R.layout.drain_gain_table_row_legend));
            hashMap.put("layout/fragment_vital_energy_meter_0", Integer.valueOf(R.layout.fragment_vital_energy_meter));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(2);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.drain_gain_table_row_legend, 1);
        sparseIntArray.put(R.layout.fragment_vital_energy_meter, 2);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.activitymodes.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.permissionsandsettings.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f5689a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                if (i2 == 1) {
                    if ("layout/drain_gain_table_row_legend_0".equals(tag)) {
                        return new DrainGainTableRowLegendBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for drain_gain_table_row_legend is invalid. Received: " + tag);
                } else if (i2 != 2) {
                    return null;
                } else {
                    if ("layout/fragment_vital_energy_meter_0".equals(tag)) {
                        return new FragmentVitalEnergyMeterBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_vital_energy_meter is invalid. Received: " + tag);
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f5690a.get(str)) == null) {
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
