package com.coveiot.android.fitnessbuddies;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.fitnessbuddies.databinding.ActivityAddBuddiesBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.ActivityAddBuddiesNewBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.ActivityBuddyDetailsBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.ActivityManageRequestsBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.BuddiesListItemNewBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.BuddiesRequestItemBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.CheerBuddyDialogBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.ContactItemSelectBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.FragmentFitnessBuddiesBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.FragmentNotificationBuddiesBindingImpl;
import com.coveiot.android.fitnessbuddies.databinding.RemoveBuddiesDialogBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes4.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYADDBUDDIES = 1;
    private static final int LAYOUT_ACTIVITYADDBUDDIESNEW = 2;
    private static final int LAYOUT_ACTIVITYBUDDYDETAILS = 3;
    private static final int LAYOUT_ACTIVITYMANAGEREQUESTS = 4;
    private static final int LAYOUT_BUDDIESLISTITEMNEW = 5;
    private static final int LAYOUT_BUDDIESREQUESTITEM = 6;
    private static final int LAYOUT_CHEERBUDDYDIALOG = 7;
    private static final int LAYOUT_CONTACTITEMSELECT = 8;
    private static final int LAYOUT_FRAGMENTFITNESSBUDDIES = 9;
    private static final int LAYOUT_FRAGMENTNOTIFICATIONBUDDIES = 10;
    private static final int LAYOUT_REMOVEBUDDIESDIALOG = 11;

    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f4413a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(19);
            f4413a = sparseArray;
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

    /* loaded from: classes4.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f4414a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(11);
            f4414a = hashMap;
            hashMap.put("layout/activity_add_buddies_0", Integer.valueOf(R.layout.activity_add_buddies));
            hashMap.put("layout/activity_add_buddies_new_0", Integer.valueOf(R.layout.activity_add_buddies_new));
            hashMap.put("layout/activity_buddy_details_0", Integer.valueOf(R.layout.activity_buddy_details));
            hashMap.put("layout/activity_manage_requests_0", Integer.valueOf(R.layout.activity_manage_requests));
            hashMap.put("layout/buddies_list_item_new_0", Integer.valueOf(R.layout.buddies_list_item_new));
            hashMap.put("layout/buddies_request_item_0", Integer.valueOf(R.layout.buddies_request_item));
            hashMap.put("layout/cheer_buddy_dialog_0", Integer.valueOf(R.layout.cheer_buddy_dialog));
            hashMap.put("layout/contact_item_select_0", Integer.valueOf(R.layout.contact_item_select));
            hashMap.put("layout/fragment_fitness_buddies_0", Integer.valueOf(R.layout.fragment_fitness_buddies));
            hashMap.put("layout/fragment_notification_buddies_0", Integer.valueOf(R.layout.fragment_notification_buddies));
            hashMap.put("layout/remove_buddies_dialog_0", Integer.valueOf(R.layout.remove_buddies_dialog));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(11);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_add_buddies, 1);
        sparseIntArray.put(R.layout.activity_add_buddies_new, 2);
        sparseIntArray.put(R.layout.activity_buddy_details, 3);
        sparseIntArray.put(R.layout.activity_manage_requests, 4);
        sparseIntArray.put(R.layout.buddies_list_item_new, 5);
        sparseIntArray.put(R.layout.buddies_request_item, 6);
        sparseIntArray.put(R.layout.cheer_buddy_dialog, 7);
        sparseIntArray.put(R.layout.contact_item_select, 8);
        sparseIntArray.put(R.layout.fragment_fitness_buddies, 9);
        sparseIntArray.put(R.layout.fragment_notification_buddies, 10);
        sparseIntArray.put(R.layout.remove_buddies_dialog, 11);
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
        return a.f4413a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/activity_add_buddies_0".equals(tag)) {
                            return new ActivityAddBuddiesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_add_buddies is invalid. Received: " + tag);
                    case 2:
                        if ("layout/activity_add_buddies_new_0".equals(tag)) {
                            return new ActivityAddBuddiesNewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_add_buddies_new is invalid. Received: " + tag);
                    case 3:
                        if ("layout/activity_buddy_details_0".equals(tag)) {
                            return new ActivityBuddyDetailsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_buddy_details is invalid. Received: " + tag);
                    case 4:
                        if ("layout/activity_manage_requests_0".equals(tag)) {
                            return new ActivityManageRequestsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for activity_manage_requests is invalid. Received: " + tag);
                    case 5:
                        if ("layout/buddies_list_item_new_0".equals(tag)) {
                            return new BuddiesListItemNewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for buddies_list_item_new is invalid. Received: " + tag);
                    case 6:
                        if ("layout/buddies_request_item_0".equals(tag)) {
                            return new BuddiesRequestItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for buddies_request_item is invalid. Received: " + tag);
                    case 7:
                        if ("layout/cheer_buddy_dialog_0".equals(tag)) {
                            return new CheerBuddyDialogBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for cheer_buddy_dialog is invalid. Received: " + tag);
                    case 8:
                        if ("layout/contact_item_select_0".equals(tag)) {
                            return new ContactItemSelectBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for contact_item_select is invalid. Received: " + tag);
                    case 9:
                        if ("layout/fragment_fitness_buddies_0".equals(tag)) {
                            return new FragmentFitnessBuddiesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_fitness_buddies is invalid. Received: " + tag);
                    case 10:
                        if ("layout/fragment_notification_buddies_0".equals(tag)) {
                            return new FragmentNotificationBuddiesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for fragment_notification_buddies is invalid. Received: " + tag);
                    case 11:
                        if ("layout/remove_buddies_dialog_0".equals(tag)) {
                            return new RemoveBuddiesDialogBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for remove_buddies_dialog is invalid. Received: " + tag);
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
        if (str == null || (num = b.f4414a.get(str)) == null) {
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
