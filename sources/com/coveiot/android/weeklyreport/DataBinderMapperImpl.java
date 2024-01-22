package com.coveiot.android.weeklyreport;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.weeklyreport.databinding.ActivityWeeklyReportCommonBindingImpl;
import com.coveiot.android.weeklyreport.databinding.ActivityWeeklyReportHistoryBindingImpl;
import com.coveiot.android.weeklyreport.databinding.FragmentFitnessReportHistoryBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes8.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYWEEKLYREPORTCOMMON = 1;
    private static final int LAYOUT_ACTIVITYWEEKLYREPORTHISTORY = 2;
    private static final int LAYOUT_FRAGMENTFITNESSREPORTHISTORY = 3;

    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f6236a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(19);
            f6236a = sparseArray;
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

    /* loaded from: classes8.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f6237a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(3);
            f6237a = hashMap;
            hashMap.put("layout/activity_weekly_report_common_0", Integer.valueOf(R.layout.activity_weekly_report_common));
            hashMap.put("layout/activity_weekly_report_history_0", Integer.valueOf(R.layout.activity_weekly_report_history));
            hashMap.put("layout/fragment_fitness_report_history_0", Integer.valueOf(R.layout.fragment_fitness_report_history));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(3);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_weekly_report_common, 1);
        sparseIntArray.put(R.layout.activity_weekly_report_history, 2);
        sparseIntArray.put(R.layout.fragment_fitness_report_history, 3);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.permissionsandsettings.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f6236a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                if (i2 == 1) {
                    if ("layout/activity_weekly_report_common_0".equals(tag)) {
                        return new ActivityWeeklyReportCommonBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_weekly_report_common is invalid. Received: " + tag);
                } else if (i2 == 2) {
                    if ("layout/activity_weekly_report_history_0".equals(tag)) {
                        return new ActivityWeeklyReportHistoryBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_weekly_report_history is invalid. Received: " + tag);
                } else if (i2 != 3) {
                    return null;
                } else {
                    if ("layout/fragment_fitness_report_history_0".equals(tag)) {
                        return new FragmentFitnessReportHistoryBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_fitness_report_history is invalid. Received: " + tag);
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f6237a.get(str)) == null) {
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
