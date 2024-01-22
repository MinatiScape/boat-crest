package com.coveiot.android.qrtray;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.qrtray.databinding.ActivityQrtrayBindingImpl;
import com.coveiot.android.qrtray.databinding.FragmentAddedQrTrayBindingImpl;
import com.coveiot.android.qrtray.databinding.FragmentQrTrayIntroBindingImpl;
import com.coveiot.android.qrtray.databinding.FragmentQrTraySavingBindingImpl;
import com.coveiot.android.qrtray.databinding.FragmentQrTrayUploadBindingImpl;
import com.coveiot.android.qrtray.databinding.ListItemAddedQrTrayLayoutBindingImpl;
import com.coveiot.android.qrtray.databinding.ListItemQrTrayCategoryLayoutBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes5.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYQRTRAY = 1;
    private static final int LAYOUT_FRAGMENTADDEDQRTRAY = 2;
    private static final int LAYOUT_FRAGMENTQRTRAYINTRO = 3;
    private static final int LAYOUT_FRAGMENTQRTRAYSAVING = 4;
    private static final int LAYOUT_FRAGMENTQRTRAYUPLOAD = 5;
    private static final int LAYOUT_LISTITEMADDEDQRTRAYLAYOUT = 6;
    private static final int LAYOUT_LISTITEMQRTRAYCATEGORYLAYOUT = 7;

    /* loaded from: classes5.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f5563a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(22);
            f5563a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "bindingDataModel1");
            sparseArray.put(2, "firstCardType");
            sparseArray.put(3, "fitnessType");
            sparseArray.put(4, "healthInfo");
            sparseArray.put(5, "isDataAvailable");
            sparseArray.put(6, "isFirstCardDataAvailable");
            sparseArray.put(7, "progress");
            sparseArray.put(8, "qrCategoryData");
            sparseArray.put(9, "qrCodeData");
            sparseArray.put(10, "qrEditImage");
            sparseArray.put(11, "showAlexaConnect");
            sparseArray.put(12, "showFitnessPlan");
            sparseArray.put(13, "showSOSSettings");
            sparseArray.put(14, "showSportScores");
            sparseArray.put(15, "showTapAndPay");
            sparseArray.put(16, "showWellnessCrew");
            sparseArray.put(17, "stepsDataModel");
            sparseArray.put(18, "stepsGoal");
            sparseArray.put(19, "tipsData");
            sparseArray.put(20, "title");
            sparseArray.put(21, "watchName");
        }
    }

    /* loaded from: classes5.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f5564a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(7);
            f5564a = hashMap;
            hashMap.put("layout/activity_qrtray_0", Integer.valueOf(R.layout.activity_qrtray));
            hashMap.put("layout/fragment_added_qr_tray_0", Integer.valueOf(R.layout.fragment_added_qr_tray));
            hashMap.put("layout/fragment_qr_tray_intro_0", Integer.valueOf(R.layout.fragment_qr_tray_intro));
            hashMap.put("layout/fragment_qr_tray_saving_0", Integer.valueOf(R.layout.fragment_qr_tray_saving));
            hashMap.put("layout/fragment_qr_tray_upload_0", Integer.valueOf(R.layout.fragment_qr_tray_upload));
            hashMap.put("layout/list_item_added_qr_tray_layout_0", Integer.valueOf(R.layout.list_item_added_qr_tray_layout));
            hashMap.put("layout/list_item_qr_tray_category_layout_0", Integer.valueOf(R.layout.list_item_qr_tray_category_layout));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(7);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_qrtray, 1);
        sparseIntArray.put(R.layout.fragment_added_qr_tray, 2);
        sparseIntArray.put(R.layout.fragment_qr_tray_intro, 3);
        sparseIntArray.put(R.layout.fragment_qr_tray_saving, 4);
        sparseIntArray.put(R.layout.fragment_qr_tray_upload, 5);
        sparseIntArray.put(R.layout.list_item_added_qr_tray_layout, 6);
        sparseIntArray.put(R.layout.list_item_qr_tray_category_layout, 7);
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
        return a.f5563a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/activity_qrtray_0".equals(tag)) {
                            return new ActivityQrtrayBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_qrtray is invalid. Received: " + tag);
                    case 2:
                        if ("layout/fragment_added_qr_tray_0".equals(tag)) {
                            return new FragmentAddedQrTrayBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_added_qr_tray is invalid. Received: " + tag);
                    case 3:
                        if ("layout/fragment_qr_tray_intro_0".equals(tag)) {
                            return new FragmentQrTrayIntroBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_qr_tray_intro is invalid. Received: " + tag);
                    case 4:
                        if ("layout/fragment_qr_tray_saving_0".equals(tag)) {
                            return new FragmentQrTraySavingBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_qr_tray_saving is invalid. Received: " + tag);
                    case 5:
                        if ("layout/fragment_qr_tray_upload_0".equals(tag)) {
                            return new FragmentQrTrayUploadBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_qr_tray_upload is invalid. Received: " + tag);
                    case 6:
                        if ("layout/list_item_added_qr_tray_layout_0".equals(tag)) {
                            return new ListItemAddedQrTrayLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for list_item_added_qr_tray_layout is invalid. Received: " + tag);
                    case 7:
                        if ("layout/list_item_qr_tray_category_layout_0".equals(tag)) {
                            return new ListItemQrTrayCategoryLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for list_item_qr_tray_category_layout is invalid. Received: " + tag);
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
        if (str == null || (num = b.f5564a.get(str)) == null) {
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
