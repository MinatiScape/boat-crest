package com.mappls.sdk.navigation.ui;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.mappls.sdk.navigation.ui.databinding.InstructionContainerItemBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutAlertViewBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutBottomInfobarBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutBottomInfobarBindingLandImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutBottomSheetAdapterBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutDirectionAdapterHeaderBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutDirectionAdapterLightBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutDirectionListBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutInstructionContainerBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutInstructionContainerBindingLandImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutInstructionPipBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutJunctionViewBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutNavigationFinishedBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutNavigationViewBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutNavigationViewBindingLandImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutNextInstructionViewBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutRecenterBtnBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutSearchLongRouteBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutSettingsViewBindingImpl;
import com.mappls.sdk.navigation.ui.databinding.LayoutSoundViewBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes11.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_INSTRUCTIONCONTAINERITEM = 1;
    private static final int LAYOUT_LAYOUTALERTVIEW = 2;
    private static final int LAYOUT_LAYOUTBOTTOMINFOBAR = 3;
    private static final int LAYOUT_LAYOUTBOTTOMSHEETADAPTER = 4;
    private static final int LAYOUT_LAYOUTDIRECTIONADAPTERHEADER = 5;
    private static final int LAYOUT_LAYOUTDIRECTIONADAPTERLIGHT = 6;
    private static final int LAYOUT_LAYOUTDIRECTIONLIST = 7;
    private static final int LAYOUT_LAYOUTINSTRUCTIONCONTAINER = 8;
    private static final int LAYOUT_LAYOUTINSTRUCTIONPIP = 9;
    private static final int LAYOUT_LAYOUTJUNCTIONVIEW = 10;
    private static final int LAYOUT_LAYOUTNAVIGATIONFINISHED = 11;
    private static final int LAYOUT_LAYOUTNAVIGATIONVIEW = 12;
    private static final int LAYOUT_LAYOUTNEXTINSTRUCTIONVIEW = 13;
    private static final int LAYOUT_LAYOUTRECENTERBTN = 14;
    private static final int LAYOUT_LAYOUTSEARCHLONGROUTE = 15;
    private static final int LAYOUT_LAYOUTSETTINGSVIEW = 16;
    private static final int LAYOUT_LAYOUTSOUNDVIEW = 17;

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f12966a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(1);
            f12966a = sparseArray;
            sparseArray.put(0, "_all");
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f12967a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(20);
            f12967a = hashMap;
            hashMap.put("layout/instruction_container_item_0", Integer.valueOf(R.layout.instruction_container_item));
            hashMap.put("layout/layout_alert_view_0", Integer.valueOf(R.layout.layout_alert_view));
            int i = R.layout.layout_bottom_infobar;
            hashMap.put("layout-land/layout_bottom_infobar_0", Integer.valueOf(i));
            hashMap.put("layout/layout_bottom_infobar_0", Integer.valueOf(i));
            hashMap.put("layout/layout_bottom_sheet_adapter_0", Integer.valueOf(R.layout.layout_bottom_sheet_adapter));
            hashMap.put("layout/layout_direction_adapter_header_0", Integer.valueOf(R.layout.layout_direction_adapter_header));
            hashMap.put("layout/layout_direction_adapter_light_0", Integer.valueOf(R.layout.layout_direction_adapter_light));
            hashMap.put("layout/layout_direction_list_0", Integer.valueOf(R.layout.layout_direction_list));
            int i2 = R.layout.layout_instruction_container;
            hashMap.put("layout/layout_instruction_container_0", Integer.valueOf(i2));
            hashMap.put("layout-land/layout_instruction_container_0", Integer.valueOf(i2));
            hashMap.put("layout/layout_instruction_pip_0", Integer.valueOf(R.layout.layout_instruction_pip));
            hashMap.put("layout/layout_junction_view_0", Integer.valueOf(R.layout.layout_junction_view));
            hashMap.put("layout/layout_navigation_finished_0", Integer.valueOf(R.layout.layout_navigation_finished));
            int i3 = R.layout.layout_navigation_view;
            hashMap.put("layout-land/layout_navigation_view_0", Integer.valueOf(i3));
            hashMap.put("layout/layout_navigation_view_0", Integer.valueOf(i3));
            hashMap.put("layout/layout_next_instruction_view_0", Integer.valueOf(R.layout.layout_next_instruction_view));
            hashMap.put("layout/layout_recenter_btn_0", Integer.valueOf(R.layout.layout_recenter_btn));
            hashMap.put("layout/layout_search_long_route_0", Integer.valueOf(R.layout.layout_search_long_route));
            hashMap.put("layout/layout_settings_view_0", Integer.valueOf(R.layout.layout_settings_view));
            hashMap.put("layout/layout_sound_view_0", Integer.valueOf(R.layout.layout_sound_view));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(17);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.instruction_container_item, 1);
        sparseIntArray.put(R.layout.layout_alert_view, 2);
        sparseIntArray.put(R.layout.layout_bottom_infobar, 3);
        sparseIntArray.put(R.layout.layout_bottom_sheet_adapter, 4);
        sparseIntArray.put(R.layout.layout_direction_adapter_header, 5);
        sparseIntArray.put(R.layout.layout_direction_adapter_light, 6);
        sparseIntArray.put(R.layout.layout_direction_list, 7);
        sparseIntArray.put(R.layout.layout_instruction_container, 8);
        sparseIntArray.put(R.layout.layout_instruction_pip, 9);
        sparseIntArray.put(R.layout.layout_junction_view, 10);
        sparseIntArray.put(R.layout.layout_navigation_finished, 11);
        sparseIntArray.put(R.layout.layout_navigation_view, 12);
        sparseIntArray.put(R.layout.layout_next_instruction_view, 13);
        sparseIntArray.put(R.layout.layout_recenter_btn, 14);
        sparseIntArray.put(R.layout.layout_search_long_route, 15);
        sparseIntArray.put(R.layout.layout_settings_view, 16);
        sparseIntArray.put(R.layout.layout_sound_view, 17);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f12966a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/instruction_container_item_0".equals(tag)) {
                            return new InstructionContainerItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for instruction_container_item is invalid. Received: " + tag);
                    case 2:
                        if ("layout/layout_alert_view_0".equals(tag)) {
                            return new LayoutAlertViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_alert_view is invalid. Received: " + tag);
                    case 3:
                        if ("layout-land/layout_bottom_infobar_0".equals(tag)) {
                            return new LayoutBottomInfobarBindingLandImpl(dataBindingComponent, view);
                        }
                        if ("layout/layout_bottom_infobar_0".equals(tag)) {
                            return new LayoutBottomInfobarBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_bottom_infobar is invalid. Received: " + tag);
                    case 4:
                        if ("layout/layout_bottom_sheet_adapter_0".equals(tag)) {
                            return new LayoutBottomSheetAdapterBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_bottom_sheet_adapter is invalid. Received: " + tag);
                    case 5:
                        if ("layout/layout_direction_adapter_header_0".equals(tag)) {
                            return new LayoutDirectionAdapterHeaderBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_direction_adapter_header is invalid. Received: " + tag);
                    case 6:
                        if ("layout/layout_direction_adapter_light_0".equals(tag)) {
                            return new LayoutDirectionAdapterLightBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_direction_adapter_light is invalid. Received: " + tag);
                    case 7:
                        if ("layout/layout_direction_list_0".equals(tag)) {
                            return new LayoutDirectionListBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_direction_list is invalid. Received: " + tag);
                    case 8:
                        if ("layout/layout_instruction_container_0".equals(tag)) {
                            return new LayoutInstructionContainerBindingImpl(dataBindingComponent, view);
                        }
                        if ("layout-land/layout_instruction_container_0".equals(tag)) {
                            return new LayoutInstructionContainerBindingLandImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_instruction_container is invalid. Received: " + tag);
                    case 9:
                        if ("layout/layout_instruction_pip_0".equals(tag)) {
                            return new LayoutInstructionPipBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_instruction_pip is invalid. Received: " + tag);
                    case 10:
                        if ("layout/layout_junction_view_0".equals(tag)) {
                            return new LayoutJunctionViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_junction_view is invalid. Received: " + tag);
                    case 11:
                        if ("layout/layout_navigation_finished_0".equals(tag)) {
                            return new LayoutNavigationFinishedBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_navigation_finished is invalid. Received: " + tag);
                    case 12:
                        if ("layout-land/layout_navigation_view_0".equals(tag)) {
                            return new LayoutNavigationViewBindingLandImpl(dataBindingComponent, view);
                        }
                        if ("layout/layout_navigation_view_0".equals(tag)) {
                            return new LayoutNavigationViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_navigation_view is invalid. Received: " + tag);
                    case 13:
                        if ("layout/layout_next_instruction_view_0".equals(tag)) {
                            return new LayoutNextInstructionViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_next_instruction_view is invalid. Received: " + tag);
                    case 14:
                        if ("layout/layout_recenter_btn_0".equals(tag)) {
                            return new LayoutRecenterBtnBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_recenter_btn is invalid. Received: " + tag);
                    case 15:
                        if ("layout/layout_search_long_route_0".equals(tag)) {
                            return new LayoutSearchLongRouteBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_search_long_route is invalid. Received: " + tag);
                    case 16:
                        if ("layout/layout_settings_view_0".equals(tag)) {
                            return new LayoutSettingsViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_settings_view is invalid. Received: " + tag);
                    case 17:
                        if ("layout/layout_sound_view_0".equals(tag)) {
                            return new LayoutSoundViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for layout_sound_view is invalid. Received: " + tag);
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f12967a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }
}
