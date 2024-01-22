package com.coveiot.android.sos;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.sos.databinding.ActivitySosBindingImpl;
import com.coveiot.android.sos.databinding.ActivitySosSettingsBindingImpl;
import com.coveiot.android.sos.databinding.ItemSosBindingImpl;
import com.coveiot.android.sos.databinding.SosContactItemSelectBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes7.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYSOS = 1;
    private static final int LAYOUT_ACTIVITYSOSSETTINGS = 2;
    private static final int LAYOUT_ITEMSOS = 3;
    private static final int LAYOUT_SOSCONTACTITEMSELECT = 4;

    /* loaded from: classes7.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f5774a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(19);
            f5774a = sparseArray;
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
        public static final HashMap<String, Integer> f5775a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(4);
            f5775a = hashMap;
            hashMap.put("layout/activity_sos_0", Integer.valueOf(R.layout.activity_sos));
            hashMap.put("layout/activity_sos_settings_0", Integer.valueOf(R.layout.activity_sos_settings));
            hashMap.put("layout/item_sos_0", Integer.valueOf(R.layout.item_sos));
            hashMap.put("layout/sos_contact_item_select_0", Integer.valueOf(R.layout.sos_contact_item_select));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(4);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_sos, 1);
        sparseIntArray.put(R.layout.activity_sos_settings, 2);
        sparseIntArray.put(R.layout.item_sos, 3);
        sparseIntArray.put(R.layout.sos_contact_item_select, 4);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.fitnessbuddies.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f5774a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                if (i2 == 1) {
                    if ("layout/activity_sos_0".equals(tag)) {
                        return new ActivitySosBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_sos is invalid. Received: " + tag);
                } else if (i2 == 2) {
                    if ("layout/activity_sos_settings_0".equals(tag)) {
                        return new ActivitySosSettingsBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_sos_settings is invalid. Received: " + tag);
                } else if (i2 == 3) {
                    if ("layout/item_sos_0".equals(tag)) {
                        return new ItemSosBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for item_sos is invalid. Received: " + tag);
                } else if (i2 != 4) {
                    return null;
                } else {
                    if ("layout/sos_contact_item_select_0".equals(tag)) {
                        return new SosContactItemSelectBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for sos_contact_item_select is invalid. Received: " + tag);
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f5775a.get(str)) == null) {
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
