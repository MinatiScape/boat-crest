package com.coveiot.android.leonardo.onboarding.paring.fragments;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentDeviceSelectionFragmentBinding;
import com.coveiot.android.devicemodels.DeviceConstants;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DeviceRemoteConfigFetchListener;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.onboarding.paring.adapters.DeviceModelsAdapter;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceSelectionViewModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.StringExtensionsKt;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentDeviceSelection extends BaseFragment implements DeviceModelsAdapter.DeviceItemListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentDeviceSelectionFragmentBinding m;
    @Nullable
    public BluetoothAdapter o;
    public boolean p;
    public DeviceModelsAdapter q;
    public int t;
    @Nullable
    public DeviceRemoteConfig.DeviceModelsBean v;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String n = "FragmentDeviceSelection";
    @NotNull
    public List<DeviceRemoteConfig.DeviceModelsBean> r = new ArrayList();
    @NotNull
    public List<DeviceRemoteConfig.DeviceModelsBean> s = new ArrayList();
    public int u = -1;
    @NotNull
    public ArrayList<DeviceRemoteConfig.DeviceCategories> w = new ArrayList<>();

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentDeviceSelection newInstance(boolean z) {
            FragmentDeviceSelection fragmentDeviceSelection = new FragmentDeviceSelection();
            fragmentDeviceSelection.setProgressShow(z);
            return fragmentDeviceSelection;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            List<String> scanFilter;
            Intrinsics.checkNotNullParameter(it, "it");
            if (AppUtils.isGpsEnabled(FragmentDeviceSelection.this.requireContext())) {
                if (AppUtils.isBluetoothEnabled(FragmentDeviceSelection.this.requireContext())) {
                    BluetoothAdapter bluetoothAdapter = FragmentDeviceSelection.this.getBluetoothAdapter();
                    Intrinsics.checkNotNull(bluetoothAdapter);
                    if (!bluetoothAdapter.isEnabled()) {
                        FragmentActivity activity = FragmentDeviceSelection.this.getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                        ((ActivityPairing) activity).showOpenBluetoothSettingsDialog();
                        return;
                    }
                    if (BleApiManager.getInstance(FragmentDeviceSelection.this.getContext()).getBleApi() != null) {
                        DeviceRemoteConfig.DeviceModelsBean deviceModelsBean = FragmentDeviceSelection.this.v;
                        String type = deviceModelsBean != null ? deviceModelsBean.getType() : null;
                        DeviceConstants.Companion companion = DeviceConstants.Companion;
                        if (!Intrinsics.areEqual(type, companion.getIDO_SELECT())) {
                            DeviceRemoteConfig.DeviceModelsBean deviceModelsBean2 = FragmentDeviceSelection.this.v;
                            if (!Intrinsics.areEqual(deviceModelsBean2 != null ? deviceModelsBean2.getType() : null, companion.getIDO_CONNECT())) {
                                BleApiManager.getInstance(FragmentDeviceSelection.this.getContext()).getBleApi().stopService();
                            }
                        }
                    }
                    DeviceModelBean deviceModelBean = new DeviceModelBean();
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean3 = FragmentDeviceSelection.this.v;
                    deviceModelBean.setName(deviceModelsBean3 != null ? deviceModelsBean3.getName() : null);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean4 = FragmentDeviceSelection.this.v;
                    deviceModelBean.setType(deviceModelsBean4 != null ? deviceModelsBean4.getType() : null);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean5 = FragmentDeviceSelection.this.v;
                    deviceModelBean.setIcon(deviceModelsBean5 != null ? deviceModelsBean5.getIcon() : null);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean6 = FragmentDeviceSelection.this.v;
                    deviceModelBean.setRemoteFrameworkSupported(deviceModelsBean6 != null ? deviceModelsBean6.getRemoteFrameworkSupported() : null);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean7 = FragmentDeviceSelection.this.v;
                    deviceModelBean.setTapAndPaySupported(deviceModelsBean7 != null ? deviceModelsBean7.getTapAndPaySupported() : null);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean8 = FragmentDeviceSelection.this.v;
                    deviceModelBean.setScanFilter(deviceModelsBean8 != null ? deviceModelsBean8.getScanFilter() : null);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean9 = FragmentDeviceSelection.this.v;
                    deviceModelBean.setIs1kActivitySupported(deviceModelsBean9 != null ? deviceModelsBean9.getIs1kActivitySupported() : null);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean10 = FragmentDeviceSelection.this.v;
                    deviceModelBean.setSleepAndEnergyScoreSupported(deviceModelsBean10 != null ? deviceModelsBean10.getSleepAndEnergyScoreSupported() : null);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean11 = FragmentDeviceSelection.this.v;
                    if ((deviceModelsBean11 != null ? deviceModelsBean11.getLogoType() : null) != null) {
                        DeviceRemoteConfig.DeviceModelsBean deviceModelsBean12 = FragmentDeviceSelection.this.v;
                        deviceModelBean.setLogoType(deviceModelsBean12 != null ? deviceModelsBean12.getLogoType() : null);
                    }
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean13 = FragmentDeviceSelection.this.v;
                    if ((deviceModelsBean13 != null ? deviceModelsBean13.getIsQRCodeOnboardingSupported() : null) != null) {
                        DeviceRemoteConfig.DeviceModelsBean deviceModelsBean14 = FragmentDeviceSelection.this.v;
                        Intrinsics.checkNotNull(deviceModelsBean14);
                        if (Intrinsics.areEqual(deviceModelsBean14.getIsQRCodeOnboardingSupported(), Boolean.TRUE)) {
                            DeviceRemoteConfig.DeviceModelsBean deviceModelsBean15 = FragmentDeviceSelection.this.v;
                            deviceModelBean.setIsQRCodeOnboardingSupported(deviceModelsBean15 != null ? deviceModelsBean15.getIsQRCodeOnboardingSupported() : null);
                        }
                    }
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean16 = FragmentDeviceSelection.this.v;
                    if ((deviceModelsBean16 != null ? deviceModelsBean16.getMinBatteryPerForWatchFaceUpload() : null) != null) {
                        DeviceRemoteConfig.DeviceModelsBean deviceModelsBean17 = FragmentDeviceSelection.this.v;
                        deviceModelBean.setMinBatteryPerForWatchFaceUpload(deviceModelsBean17 != null ? deviceModelsBean17.getMinBatteryPerForWatchFaceUpload() : null);
                    }
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean18 = FragmentDeviceSelection.this.v;
                    if ((deviceModelsBean18 != null ? deviceModelsBean18.getDisableAutoHRFor() : null) != null) {
                        DeviceRemoteConfig.DeviceModelsBean deviceModelsBean19 = FragmentDeviceSelection.this.v;
                        deviceModelBean.setDisableAutoHRFor(deviceModelsBean19 != null ? deviceModelsBean19.getDisableAutoHRFor() : null);
                    }
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean20 = FragmentDeviceSelection.this.v;
                    if ((deviceModelsBean20 != null ? deviceModelsBean20.getMinTimeGapForShowingNextAutoHRPopup() : null) != null) {
                        DeviceRemoteConfig.DeviceModelsBean deviceModelsBean21 = FragmentDeviceSelection.this.v;
                        deviceModelBean.setMinTimeGapForShowingNextAutoHRPopup(deviceModelsBean21 != null ? deviceModelsBean21.getMinTimeGapForShowingNextAutoHRPopup() : null);
                    }
                    SessionManager.getInstance(FragmentDeviceSelection.this.requireContext()).saveDeviceModelBean(deviceModelBean);
                    FragmentDeviceSelection.this.k();
                    String str = FragmentDeviceSelection.this.n;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Selected device type -> ");
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean22 = FragmentDeviceSelection.this.v;
                    sb.append(deviceModelsBean22 != null ? deviceModelsBean22.getType() : null);
                    LogHelper.d(str, sb.toString());
                    SessionManager sessionManager = SessionManager.getInstance(FragmentDeviceSelection.this.requireContext());
                    DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                    Context requireContext = FragmentDeviceSelection.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean23 = FragmentDeviceSelection.this.v;
                    sessionManager.saveDeviceType(companion2.getDeviceModelNameFromType(requireContext, deviceModelsBean23 != null ? deviceModelsBean23.getType() : null));
                    SessionManager.getInstance(FragmentDeviceSelection.this.requireContext()).setSelectedDeviceTypePhoneOnly(false);
                    BleApiManager bleApiManager = BleApiManager.getInstance(FragmentDeviceSelection.this.getContext());
                    Context requireContext2 = FragmentDeviceSelection.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    bleApiManager.init(companion2.getBleDeviceType(requireContext2));
                    BleApiManager.getInstance(FragmentDeviceSelection.this.getContext()).getBleApi();
                    Context requireContext3 = FragmentDeviceSelection.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    if (companion2.isKaHaDevice(requireContext3)) {
                        BleApi bleApi = BleApiManager.getInstance(FragmentDeviceSelection.this.getContext()).getBleApi();
                        Intrinsics.checkNotNull(bleApi, "null cannot be cast to non-null type com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl");
                        ((LeonardoBleApiImpl) bleApi).setPairingSupported(true);
                    } else {
                        Context requireContext4 = FragmentDeviceSelection.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                        if (companion2.isSmaDevice(requireContext4)) {
                            SessionManager.getInstance(FragmentDeviceSelection.this.requireContext()).setSmaIsMigrationRequired(false);
                            if (BleApiManager.getInstance(FragmentDeviceSelection.this.getContext()).getBleApi() != null) {
                                BleApiManager.getInstance(FragmentDeviceSelection.this.getContext()).getBleApi().stopService();
                            }
                        }
                    }
                    SessionManager.getInstance(FragmentDeviceSelection.this.requireContext()).setScanAllDevice(false);
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean24 = FragmentDeviceSelection.this.v;
                    if ((deviceModelsBean24 != null ? deviceModelsBean24.getIsQRCodeOnboardingSupported() : null) != null) {
                        DeviceRemoteConfig.DeviceModelsBean deviceModelsBean25 = FragmentDeviceSelection.this.v;
                        Intrinsics.checkNotNull(deviceModelsBean25);
                        if (Intrinsics.areEqual(deviceModelsBean25.getIsQRCodeOnboardingSupported(), Boolean.TRUE)) {
                            FragmentActivity activity2 = FragmentDeviceSelection.this.getActivity();
                            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                            ((ActivityPairing) activity2).loadQRScanningFragment();
                            HashMap<String, Object> hashMap = new HashMap<>();
                            Context requireContext5 = FragmentDeviceSelection.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                            hashMap.putAll(companion2.getDeviceId(requireContext5));
                            companion2.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_WATCH_MODEL_SELECTED.getValue(), hashMap);
                            return;
                        }
                    }
                    DeviceRemoteConfig.DeviceModelsBean deviceModelsBean26 = FragmentDeviceSelection.this.v;
                    if (deviceModelsBean26 != null && (scanFilter = deviceModelsBean26.getScanFilter()) != null) {
                        FragmentActivity activity3 = FragmentDeviceSelection.this.getActivity();
                        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                        ((ActivityPairing) activity3).loadDeviceListingFragment(scanFilter);
                    }
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    Context requireContext52 = FragmentDeviceSelection.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext52, "requireContext()");
                    hashMap2.putAll(companion2.getDeviceId(requireContext52));
                    companion2.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_WATCH_MODEL_SELECTED.getValue(), hashMap2);
                    return;
                }
                FragmentDeviceSelection fragmentDeviceSelection = FragmentDeviceSelection.this;
                Context requireContext6 = fragmentDeviceSelection.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                String string = FragmentDeviceSelection.this.getString(R.string.bluetooth_off_message);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off_message)");
                fragmentDeviceSelection.toast(requireContext6, string);
                return;
            }
            FragmentDeviceSelection fragmentDeviceSelection2 = FragmentDeviceSelection.this;
            Context requireContext7 = fragmentDeviceSelection2.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
            String string2 = FragmentDeviceSelection.this.getString(R.string.please_check_gps);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_check_gps)");
            fragmentDeviceSelection2.toast(requireContext7, string2);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.coveiot.android.leonardo.onboarding.paring.adapters.DeviceModelsAdapter.DeviceItemListener
    public void deviceCount(int i) {
        if (i == 0) {
            TextView textView = l().tvNoDataFound;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
            visible(textView);
            RecyclerView recyclerView = l().rvDevices;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvDevices");
            gone(recyclerView);
            return;
        }
        TextView textView2 = l().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvNoDataFound");
        gone(textView2);
        RecyclerView recyclerView2 = l().rvDevices;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvDevices");
        visible(recyclerView2);
    }

    @Override // com.coveiot.android.leonardo.onboarding.paring.adapters.DeviceModelsAdapter.DeviceItemListener
    public void deviceSelected(@NotNull DeviceRemoteConfig.DeviceModelsBean device, int i) {
        Intrinsics.checkNotNullParameter(device, "device");
        Button button = l().btnConfirm;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnConfirm");
        visible(button);
        this.u = i;
        DeviceModelsAdapter deviceModelsAdapter = this.q;
        if (deviceModelsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceModelsAdapter");
            deviceModelsAdapter = null;
        }
        deviceModelsAdapter.setSelectionPosition(this.u);
        this.v = device;
        SessionManager.getInstance(requireContext()).saveBleDeviceName(device.getName());
    }

    @Nullable
    public final BluetoothAdapter getBluetoothAdapter() {
        return this.o;
    }

    public final boolean isProgressShow() {
        return this.p;
    }

    public final void k() {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DEVICE_SELECTION_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_DEVICE_SCANNING_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SELECT_DEVICE.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final FragmentDeviceSelectionFragmentBinding l() {
        FragmentDeviceSelectionFragmentBinding fragmentDeviceSelectionFragmentBinding = this.m;
        Intrinsics.checkNotNull(fragmentDeviceSelectionFragmentBinding);
        return fragmentDeviceSelectionFragmentBinding;
    }

    public final void m() {
        Button button = l().btnConfirm;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnConfirm");
        gone(button);
        this.u = -1;
        DeviceModelsAdapter deviceModelsAdapter = this.q;
        DeviceModelsAdapter deviceModelsAdapter2 = null;
        if (deviceModelsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceModelsAdapter");
            deviceModelsAdapter = null;
        }
        deviceModelsAdapter.setSelectionPosition(this.u);
        this.s.clear();
        for (DeviceRemoteConfig.DeviceModelsBean deviceModelsBean : this.r) {
            if (this.w.get(this.t).getList_filter() != null) {
                String name = deviceModelsBean.getName();
                Intrinsics.checkNotNullExpressionValue(name, "deviceModel.name");
                String lowerCase = name.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String str = lowerCase.toString();
                PayUtils payUtils = PayUtils.INSTANCE;
                List<String> list_filter = this.w.get(this.t).getList_filter();
                Intrinsics.checkNotNullExpressionValue(list_filter, "deviceCategoriesList.get…lectedNumber).list_filter");
                if (StringExtensionsKt.containsList(str, payUtils.toLowerCaseList(list_filter))) {
                    this.s.add(deviceModelsBean);
                }
            } else {
                this.s.add(deviceModelsBean);
            }
        }
        DeviceModelsAdapter deviceModelsAdapter3 = this.q;
        if (deviceModelsAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceModelsAdapter");
        } else {
            deviceModelsAdapter2 = deviceModelsAdapter3;
        }
        deviceModelsAdapter2.setDeviceList(this.s);
    }

    public final void n() {
        if (this.p) {
            BaseFragment.showProgress$default(this, false, 1, null);
        }
        PayUtils payUtils = PayUtils.INSTANCE;
        String value = ThemeConstants.REMOTE_CONFIG_DEVICE_LIST_NEW.getValue();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        payUtils.fetchDeviceRemoteConfig(value, requireActivity, new DeviceRemoteConfigFetchListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceSelection$handleDeviceVisibility$1
            @Override // com.coveiot.android.leonardo.listener.DeviceRemoteConfigFetchListener
            public void onFailure(@Nullable String str) {
                if (FragmentDeviceSelection.this.isAdded()) {
                    FragmentDeviceSelection.this.dismissProgress();
                    FragmentDeviceSelection fragmentDeviceSelection = FragmentDeviceSelection.this;
                    Context requireContext = fragmentDeviceSelection.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    String string = FragmentDeviceSelection.this.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                    fragmentDeviceSelection.toast(requireContext, string);
                }
            }

            @Override // com.coveiot.android.leonardo.listener.DeviceRemoteConfigFetchListener
            public void onSuccess(@Nullable DeviceRemoteConfig deviceRemoteConfig) {
                List list;
                DeviceModelsAdapter deviceModelsAdapter;
                List<? extends DeviceRemoteConfig.DeviceModelsBean> list2;
                ArrayList arrayList;
                ArrayList<DeviceRemoteConfig.DeviceCategories> arrayList2;
                FragmentDeviceSelectionFragmentBinding l;
                if (FragmentDeviceSelection.this.isAdded()) {
                    FragmentDeviceSelection.this.dismissProgress();
                    if (deviceRemoteConfig != null) {
                        FragmentDeviceSelection fragmentDeviceSelection = FragmentDeviceSelection.this;
                        if (deviceRemoteConfig.getDeviceList() != null) {
                            list = fragmentDeviceSelection.r;
                            List<DeviceRemoteConfig.DeviceModelsBean> deviceList = deviceRemoteConfig.getDeviceList();
                            Intrinsics.checkNotNullExpressionValue(deviceList, "it.deviceList");
                            list.addAll(deviceList);
                            deviceModelsAdapter = fragmentDeviceSelection.q;
                            if (deviceModelsAdapter == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("deviceModelsAdapter");
                                deviceModelsAdapter = null;
                            }
                            list2 = fragmentDeviceSelection.r;
                            deviceModelsAdapter.setDeviceList(list2);
                            if (deviceRemoteConfig.getDeviceCategories() != null) {
                                arrayList = fragmentDeviceSelection.w;
                                arrayList.addAll(deviceRemoteConfig.getDeviceCategories());
                                arrayList2 = fragmentDeviceSelection.w;
                                for (DeviceRemoteConfig.DeviceCategories deviceCategories : arrayList2) {
                                    l = fragmentDeviceSelection.l();
                                    l.tlDeviceType.addTab(((TabLayout) fragmentDeviceSelection._$_findCachedViewById(R.id.tlDeviceType)).newTab().setText(deviceCategories.getName()));
                                }
                            }
                        }
                    }
                }
                FragmentDeviceSelection.this.setProgressShow(false);
            }
        });
    }

    public final void o(TabLayout.Tab tab, int i) {
        View childAt = l().tlDeviceType.getChildAt(0);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type android.view.ViewGroup");
        View childAt2 = ((ViewGroup) childAt).getChildAt(tab.getPosition());
        Intrinsics.checkNotNull(childAt2, "null cannot be cast to non-null type android.widget.LinearLayout");
        View childAt3 = ((LinearLayout) childAt2).getChildAt(1);
        Intrinsics.checkNotNull(childAt3, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) childAt3).setTextAppearance(i);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentDeviceSelectionViewModel fragmentDeviceSelectionViewModel = (FragmentDeviceSelectionViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentDeviceSelectionViewModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Object systemService = requireContext().getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        this.o = ((BluetoothManager) systemService).getAdapter();
        this.m = FragmentDeviceSelectionFragmentBinding.inflate(inflater, viewGroup, false);
        View root = l().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LoadingDialog progressDialog = getProgressDialog();
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        progressDialog.dismiss();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        n();
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.DEVICE_SCANNING_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        this.q = new DeviceModelsAdapter(this);
        final FragmentDeviceSelectionFragmentBinding l = l();
        RecyclerView recyclerView = l.rvDevices;
        DeviceModelsAdapter deviceModelsAdapter = this.q;
        if (deviceModelsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceModelsAdapter");
            deviceModelsAdapter = null;
        }
        recyclerView.setAdapter(deviceModelsAdapter);
        l.rvDevices.setLayoutManager(new LinearLayoutManager(requireActivity()));
        int i = 0;
        int tabCount = l.tlDeviceType.getTabCount();
        if (tabCount >= 0) {
            while (true) {
                TabLayout.Tab tabAt = l.tlDeviceType.getTabAt(i);
                if (tabAt != null && i == 0) {
                    o(tabAt, R.style.selectedTabFont);
                    break;
                } else if (i == tabCount) {
                    break;
                } else {
                    i++;
                }
            }
        }
        l.tlDeviceType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceSelection$onViewCreated$1$1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                FragmentDeviceSelection.this.t = tab.getPosition();
                FragmentDeviceSelection.this.o(tab, R.style.selectedTabFont);
                FragmentDeviceSelection.this.m();
                FragmentDeviceSelection fragmentDeviceSelection = FragmentDeviceSelection.this;
                TextView tvNoDataFound = l.tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(tvNoDataFound, "tvNoDataFound");
                fragmentDeviceSelection.gone(tvNoDataFound);
                l.etSearchDevice.setText("");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                FragmentDeviceSelection.this.o(tab, R.style.unSelectedTabFontOnBoarding);
            }
        });
        l.etSearchDevice.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceSelection$onViewCreated$1$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@NotNull Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@NotNull CharSequence s, int i2, int i3, int i4) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@NotNull CharSequence searchDevice, int i2, int i3, int i4) {
                DeviceModelsAdapter deviceModelsAdapter2;
                DeviceModelsAdapter deviceModelsAdapter3;
                Intrinsics.checkNotNullParameter(searchDevice, "searchDevice");
                deviceModelsAdapter2 = FragmentDeviceSelection.this.q;
                DeviceModelsAdapter deviceModelsAdapter4 = null;
                if (deviceModelsAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceModelsAdapter");
                    deviceModelsAdapter2 = null;
                }
                deviceModelsAdapter2.getFilter().filter(searchDevice);
                deviceModelsAdapter3 = FragmentDeviceSelection.this.q;
                if (deviceModelsAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceModelsAdapter");
                } else {
                    deviceModelsAdapter4 = deviceModelsAdapter3;
                }
                deviceModelsAdapter4.notifyDataSetChanged();
            }
        });
        Button btnConfirm = l.btnConfirm;
        Intrinsics.checkNotNullExpressionValue(btnConfirm, "btnConfirm");
        ViewUtilsKt.setSafeOnClickListener(btnConfirm, new a());
    }

    public final void setBluetoothAdapter(@Nullable BluetoothAdapter bluetoothAdapter) {
        this.o = bluetoothAdapter;
    }

    public final void setProgressShow(boolean z) {
        this.p = z;
    }
}
