package com.coveiot.android.respiratoryrate;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.respiratoryrate.databinding.ActivityRespiratoryRateHistoryBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.ActivityRespiratoryRateInfoBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.ActivityRespiratoryRateSettingsBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.ActivityRespiratoryRateShareBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.CustomMarkerViewBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.FragmentRespiratoryRateBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.FragmentRespiratoryRateHistoryBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.FragmentRespiratoryRateShareBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.FragmentVitalRespiratoryRateBindingImpl;
import com.coveiot.android.respiratoryrate.databinding.RespiratoryRateHistoryListItemBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes6.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYRESPIRATORYRATEHISTORY = 1;
    private static final int LAYOUT_ACTIVITYRESPIRATORYRATEINFO = 2;
    private static final int LAYOUT_ACTIVITYRESPIRATORYRATESETTINGS = 3;
    private static final int LAYOUT_ACTIVITYRESPIRATORYRATESHARE = 4;
    private static final int LAYOUT_CUSTOMMARKERVIEW = 5;
    private static final int LAYOUT_FRAGMENTRESPIRATORYRATE = 6;
    private static final int LAYOUT_FRAGMENTRESPIRATORYRATEHISTORY = 7;
    private static final int LAYOUT_FRAGMENTRESPIRATORYRATESHARE = 8;
    private static final int LAYOUT_FRAGMENTVITALRESPIRATORYRATE = 9;
    private static final int LAYOUT_RESPIRATORYRATEHISTORYLISTITEM = 10;

    /* loaded from: classes6.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f5655a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(19);
            f5655a = sparseArray;
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

    /* loaded from: classes6.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f5656a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(10);
            f5656a = hashMap;
            hashMap.put("layout/activity_respiratory_rate_history_0", Integer.valueOf(R.layout.activity_respiratory_rate_history));
            hashMap.put("layout/activity_respiratory_rate_info_0", Integer.valueOf(R.layout.activity_respiratory_rate_info));
            hashMap.put("layout/activity_respiratory_rate_settings_0", Integer.valueOf(R.layout.activity_respiratory_rate_settings));
            hashMap.put("layout/activity_respiratory_rate_share_0", Integer.valueOf(R.layout.activity_respiratory_rate_share));
            hashMap.put("layout/custom_marker_view_0", Integer.valueOf(R.layout.custom_marker_view));
            hashMap.put("layout/fragment_respiratory_rate_0", Integer.valueOf(R.layout.fragment_respiratory_rate));
            hashMap.put("layout/fragment_respiratory_rate_history_0", Integer.valueOf(R.layout.fragment_respiratory_rate_history));
            hashMap.put("layout/fragment_respiratory_rate_share_0", Integer.valueOf(R.layout.fragment_respiratory_rate_share));
            hashMap.put("layout/fragment_vital_respiratory_rate_0", Integer.valueOf(R.layout.fragment_vital_respiratory_rate));
            hashMap.put("layout/respiratory_rate_history_list_item_0", Integer.valueOf(R.layout.respiratory_rate_history_list_item));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(10);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_respiratory_rate_history, 1);
        sparseIntArray.put(R.layout.activity_respiratory_rate_info, 2);
        sparseIntArray.put(R.layout.activity_respiratory_rate_settings, 3);
        sparseIntArray.put(R.layout.activity_respiratory_rate_share, 4);
        sparseIntArray.put(R.layout.custom_marker_view, 5);
        sparseIntArray.put(R.layout.fragment_respiratory_rate, 6);
        sparseIntArray.put(R.layout.fragment_respiratory_rate_history, 7);
        sparseIntArray.put(R.layout.fragment_respiratory_rate_share, 8);
        sparseIntArray.put(R.layout.fragment_vital_respiratory_rate, 9);
        sparseIntArray.put(R.layout.respiratory_rate_history_list_item, 10);
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
        return a.f5655a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/activity_respiratory_rate_history_0".equals(tag)) {
                            return new ActivityRespiratoryRateHistoryBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_respiratory_rate_history is invalid. Received: " + tag);
                    case 2:
                        if ("layout/activity_respiratory_rate_info_0".equals(tag)) {
                            return new ActivityRespiratoryRateInfoBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_respiratory_rate_info is invalid. Received: " + tag);
                    case 3:
                        if ("layout/activity_respiratory_rate_settings_0".equals(tag)) {
                            return new ActivityRespiratoryRateSettingsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_respiratory_rate_settings is invalid. Received: " + tag);
                    case 4:
                        if ("layout/activity_respiratory_rate_share_0".equals(tag)) {
                            return new ActivityRespiratoryRateShareBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_respiratory_rate_share is invalid. Received: " + tag);
                    case 5:
                        if ("layout/custom_marker_view_0".equals(tag)) {
                            return new CustomMarkerViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for custom_marker_view is invalid. Received: " + tag);
                    case 6:
                        if ("layout/fragment_respiratory_rate_0".equals(tag)) {
                            return new FragmentRespiratoryRateBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_respiratory_rate is invalid. Received: " + tag);
                    case 7:
                        if ("layout/fragment_respiratory_rate_history_0".equals(tag)) {
                            return new FragmentRespiratoryRateHistoryBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_respiratory_rate_history is invalid. Received: " + tag);
                    case 8:
                        if ("layout/fragment_respiratory_rate_share_0".equals(tag)) {
                            return new FragmentRespiratoryRateShareBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_respiratory_rate_share is invalid. Received: " + tag);
                    case 9:
                        if ("layout/fragment_vital_respiratory_rate_0".equals(tag)) {
                            return new FragmentVitalRespiratoryRateBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_vital_respiratory_rate is invalid. Received: " + tag);
                    case 10:
                        if ("layout/respiratory_rate_history_list_item_0".equals(tag)) {
                            return new RespiratoryRateHistoryListItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for respiratory_rate_history_list_item is invalid. Received: " + tag);
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
        if (str == null || (num = b.f5656a.get(str)) == null) {
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
