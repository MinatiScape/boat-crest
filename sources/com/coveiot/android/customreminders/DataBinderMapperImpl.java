package com.coveiot.android.customreminders;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.customreminders.databinding.FragmentHandWashNewBindingImpl;
import com.coveiot.android.customreminders.databinding.MedicineReminderItemBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes3.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_FRAGMENTHANDWASHNEW = 1;
    private static final int LAYOUT_MEDICINEREMINDERITEM = 2;

    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f4124a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(20);
            f4124a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "bindingDataModel1");
            sparseArray.put(2, "firstCardType");
            sparseArray.put(3, "fitnessType");
            sparseArray.put(4, "healthInfo");
            sparseArray.put(5, "isDataAvailable");
            sparseArray.put(6, "isFirstCardDataAvailable");
            sparseArray.put(7, "isInEditMode");
            sparseArray.put(8, "progress");
            sparseArray.put(9, "showAlexaConnect");
            sparseArray.put(10, "showFitnessPlan");
            sparseArray.put(11, "showSOSSettings");
            sparseArray.put(12, "showSportScores");
            sparseArray.put(13, "showTapAndPay");
            sparseArray.put(14, "showWellnessCrew");
            sparseArray.put(15, "stepsDataModel");
            sparseArray.put(16, "stepsGoal");
            sparseArray.put(17, "tipsData");
            sparseArray.put(18, "title");
            sparseArray.put(19, "watchName");
        }
    }

    /* loaded from: classes3.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f4125a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(2);
            f4125a = hashMap;
            hashMap.put("layout/fragment_hand_wash_new_0", Integer.valueOf(R.layout.fragment_hand_wash_new));
            hashMap.put("layout/medicine_reminder_item_0", Integer.valueOf(R.layout.medicine_reminder_item));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(2);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.fragment_hand_wash_new, 1);
        sparseIntArray.put(R.layout.medicine_reminder_item, 2);
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
        return a.f4124a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                if (i2 == 1) {
                    if ("layout/fragment_hand_wash_new_0".equals(tag)) {
                        return new FragmentHandWashNewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for fragment_hand_wash_new is invalid. Received: " + tag);
                } else if (i2 != 2) {
                    return null;
                } else {
                    if ("layout/medicine_reminder_item_0".equals(tag)) {
                        return new MedicineReminderItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for medicine_reminder_item is invalid. Received: " + tag);
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f4125a.get(str)) == null) {
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
