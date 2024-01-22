package com.coveiot.android.leonardo.onboarding.paring.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentDeviceListingFragmentBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.onboarding.paring.adapters.ScannedBleDeviceAdapter;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceListingViewModelCompanionDM;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentDeviceListing extends BaseFragment implements ScannedBleDeviceAdapter.SelectedBleDeviceListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentDeviceListingFragmentBinding m;
    public BleDevice q;
    public FragmentDeviceListingViewModelCompanionDM r;
    public ScannedBleDeviceAdapter t;
    public int u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int n = 202;
    public final int o = 102;
    public final int p = 1001;
    @NotNull
    public String s = "FragmentDeviceListing";
    public int v = R.raw.rect_blue_scan_start;
    public int w = R.raw.rect_blue_connecting;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentDeviceListing newInstance() {
            return new FragmentDeviceListing();
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<Boolean, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean isStillScanning) {
            Intrinsics.checkNotNullExpressionValue(isStillScanning, "isStillScanning");
            if (!isStillScanning.booleanValue()) {
                if (FragmentDeviceListing.this.u == 0) {
                    try {
                        FragmentDeviceListing.this.B().lottieView.pauseAnimation();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                FragmentDeviceListing fragmentDeviceListing = FragmentDeviceListing.this;
                ConstraintLayout constraintLayout = fragmentDeviceListing.B().clSearchDevice;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clSearchDevice");
                fragmentDeviceListing.visible(constraintLayout);
                FragmentDeviceListing fragmentDeviceListing2 = FragmentDeviceListing.this;
                ConstraintLayout constraintLayout2 = fragmentDeviceListing2.B().clNoDeviceFound;
                Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clNoDeviceFound");
                fragmentDeviceListing2.gone(constraintLayout2);
                return;
            }
            FragmentDeviceListing fragmentDeviceListing3 = FragmentDeviceListing.this;
            ConstraintLayout constraintLayout3 = fragmentDeviceListing3.B().clSearchDevice;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clSearchDevice");
            fragmentDeviceListing3.visible(constraintLayout3);
            FragmentDeviceListing fragmentDeviceListing4 = FragmentDeviceListing.this;
            ConstraintLayout constraintLayout4 = fragmentDeviceListing4.B().clNoDeviceFound;
            Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clNoDeviceFound");
            fragmentDeviceListing4.gone(constraintLayout4);
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<Boolean, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean noDeviceFound) {
            Intrinsics.checkNotNullExpressionValue(noDeviceFound, "noDeviceFound");
            if (noDeviceFound.booleanValue()) {
                FragmentDeviceListing fragmentDeviceListing = FragmentDeviceListing.this;
                ConstraintLayout constraintLayout = fragmentDeviceListing.B().clSearchDevice;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clSearchDevice");
                fragmentDeviceListing.gone(constraintLayout);
                FragmentDeviceListing fragmentDeviceListing2 = FragmentDeviceListing.this;
                ConstraintLayout constraintLayout2 = fragmentDeviceListing2.B().clNoDeviceFound;
                Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clNoDeviceFound");
                fragmentDeviceListing2.visible(constraintLayout2);
                return;
            }
            FragmentDeviceListing fragmentDeviceListing3 = FragmentDeviceListing.this;
            ConstraintLayout constraintLayout3 = fragmentDeviceListing3.B().clSearchDevice;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clSearchDevice");
            fragmentDeviceListing3.visible(constraintLayout3);
            FragmentDeviceListing fragmentDeviceListing4 = FragmentDeviceListing.this;
            ConstraintLayout constraintLayout4 = fragmentDeviceListing4.B().clNoDeviceFound;
            Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clNoDeviceFound");
            fragmentDeviceListing4.gone(constraintLayout4);
        }
    }

    /* loaded from: classes5.dex */
    public static final class c extends Lambda implements Function1<ArrayList<BleDevice>, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ArrayList<BleDevice> arrayList) {
            invoke2(arrayList);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(ArrayList<BleDevice> it) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (!it.isEmpty()) {
                FragmentDeviceListingFragmentBinding B = FragmentDeviceListing.this.B();
                FragmentDeviceListing fragmentDeviceListing = FragmentDeviceListing.this;
                ConstraintLayout clSearchDevice = B.clSearchDevice;
                Intrinsics.checkNotNullExpressionValue(clSearchDevice, "clSearchDevice");
                fragmentDeviceListing.visible(clSearchDevice);
                ConstraintLayout clNoDeviceFound = B.clNoDeviceFound;
                Intrinsics.checkNotNullExpressionValue(clNoDeviceFound, "clNoDeviceFound");
                fragmentDeviceListing.gone(clNoDeviceFound);
                TextView tvInfo = B.tvInfo;
                Intrinsics.checkNotNullExpressionValue(tvInfo, "tvInfo");
                fragmentDeviceListing.gone(tvInfo);
                TextView textView = B.tvSearching;
                textView.setText(it.size() + " device found");
                ConstraintLayout clQRCode = B.clQRCode;
                Intrinsics.checkNotNullExpressionValue(clQRCode, "clQRCode");
                fragmentDeviceListing.gone(clQRCode);
                ConstraintLayout clPairDevice = B.clPairDevice;
                Intrinsics.checkNotNullExpressionValue(clPairDevice, "clPairDevice");
                fragmentDeviceListing.visible(clPairDevice);
                B.ivConnectionLoader.setBackgroundResource(R.drawable.connection_loader);
                ScannedBleDeviceAdapter scannedBleDeviceAdapter = FragmentDeviceListing.this.t;
                if (scannedBleDeviceAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scannedBleDeviceAdapter");
                    scannedBleDeviceAdapter = null;
                }
                scannedBleDeviceAdapter.setScannedBleDevices(it);
                FragmentDeviceListing.this.u = it.size();
            }
        }
    }

    public static final void D(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public static final void E(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public static final void F(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) activity).loadQRScanningFragment();
    }

    public static final void G(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public static final void H(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public static final void I(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SessionManager.getInstance(this$0.requireContext()).setScanAllDevice(false);
        this$0.S();
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_NODEVICE_FOUND_SEARCH_AGAIN.getValue(), hashMap);
    }

    public static final void J(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (SessionManager.getInstance(this$0.requireContext()).getTroubleshootUrl() != null) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            String string = this$0.getString(R.string.troubleshoot);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.troubleshoot)");
            String troubleshootUrl = SessionManager.getInstance(this$0.requireContext()).getTroubleshootUrl();
            Intrinsics.checkNotNullExpressionValue(troubleshootUrl, "getInstance(requireContext()).troubleshootUrl");
            companion.navigateToWebViewer(requireActivity, string, troubleshootUrl);
        } else {
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            FragmentActivity requireActivity2 = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            String string2 = this$0.getString(R.string.troubleshoot);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.troubleshoot)");
            companion2.navigateToWebViewer(requireActivity2, string2, AppConstants.TROUBLESHOOT_URL.getValue());
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_PAIR_CONN_TROUBLESHOOT.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.PAIRING_FAILED_SCREEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.DEVICE_SELECTION_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public static final void K(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion companion = AppNavigator.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = this$0.getString(R.string.troubleshoot);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.troubleshoot)");
        String troubleshootUrl = SessionManager.getInstance(this$0.requireContext()).getTroubleshootUrl();
        Intrinsics.checkNotNullExpressionValue(troubleshootUrl, "getInstance(requireContext()).troubleshootUrl");
        companion.navigateToWebViewer(requireActivity, string, troubleshootUrl);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_PAIR_CONN_TROUBLESHOOT.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.PAIRING_FAILED_SCREEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.DEVICE_SELECTION_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public static final void L(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DEVICE_SCANNING_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_DEVICE_PAIRING_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.PAIR_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM = this$0.r;
        if (fragmentDeviceListingViewModelCompanionDM == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentDeviceListingViewModelCompanionDM = null;
        }
        fragmentDeviceListingViewModelCompanionDM.stopScan();
        BleDevice bleDevice = this$0.q;
        if (bleDevice == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedBleDevice");
            bleDevice = null;
        }
        BleDevice bleDevice2 = new BleDevice(bleDevice.getmDevice());
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ActivityPairing.connectTo$default((ActivityPairing) activity, bleDevice2, null, 2, null);
    }

    public static final void M(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) activity).loadDeviceScanningFragment();
    }

    public static final void N(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) activity).loadDeviceScanningFragment();
    }

    public static final void O(FragmentDeviceListing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            LottieAnimationView lottieAnimationView = this$0.B().lottieView;
            if (lottieAnimationView != null) {
                lottieAnimationView.removeAllAnimatorListeners();
            }
            LottieAnimationView lottieAnimationView2 = this$0.B().lottieView;
            if (lottieAnimationView2 != null) {
                lottieAnimationView2.cancelAnimation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SessionManager.getInstance(this$0.requireContext()).setScanAllDevice(true);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DEVICE_SCANNING_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.SCAN_ALL_DEVICES.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SCAN_ALL_DEVICE_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        SessionManager.getInstance(this$0.requireContext()).setScanAllDevice(true);
        LogHelper.d(this$0.s, "Scan All clicked");
        this$0.S();
    }

    public static final void P(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void Q(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void R(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void U(FragmentDeviceListing this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openLocationSettings(this$0.requireActivity(), this$0.o);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void W(FragmentDeviceListing this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public final FragmentDeviceListingFragmentBinding B() {
        FragmentDeviceListingFragmentBinding fragmentDeviceListingFragmentBinding = this.m;
        Intrinsics.checkNotNull(fragmentDeviceListingFragmentBinding);
        return fragmentDeviceListingFragmentBinding;
    }

    public final void C() {
        View findViewById = B().tvBack1.findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "binding.tvBack1.findView…View>(R.id.toolbar_title)");
        gone(findViewById);
        View findViewById2 = B().tvBack.findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "binding.tvBack.findViewB…View>(R.id.toolbar_title)");
        gone(findViewById2);
        View findViewById3 = B().tvBack.findViewById(R.id.ivButton);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "binding.tvBack.findViewB…ImageView>(R.id.ivButton)");
        gone(findViewById3);
        View findViewById4 = B().tvBack1.findViewById(R.id.ivButton);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "binding.tvBack1.findView…ImageView>(R.id.ivButton)");
        gone(findViewById4);
        ((TextView) B().tvBack.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentDeviceListing.D(FragmentDeviceListing.this, view);
            }
        });
        ((TextView) B().tvBack1.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentDeviceListing.E(FragmentDeviceListing.this, view);
            }
        });
    }

    public final void S() {
        FragmentDeviceListingFragmentBinding B = B();
        ConstraintLayout clSearchDevice = B.clSearchDevice;
        Intrinsics.checkNotNullExpressionValue(clSearchDevice, "clSearchDevice");
        visible(clSearchDevice);
        ConstraintLayout clNoDeviceFound = B.clNoDeviceFound;
        Intrinsics.checkNotNullExpressionValue(clNoDeviceFound, "clNoDeviceFound");
        gone(clNoDeviceFound);
        B.btnPairDevice.setAlpha(0.5f);
        B.btnPairDevice.setEnabled(false);
        B.btnPairDevice.setClickable(false);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DEVICE_SCANNING_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.REFRESH_DEVICE.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.REFRESH_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = requireContext().getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                ((ActivityPairing) activity).showOpenBluetoothSettingsDialog();
                return;
            }
            Y(this.v, false, new AnimatorListenerAdapter() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceListing$searchAgain$2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(@NotNull Animator animation, boolean z) {
                    FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM;
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    super.onAnimationEnd(animation, z);
                    fragmentDeviceListingViewModelCompanionDM = FragmentDeviceListing.this.r;
                    if (fragmentDeviceListingViewModelCompanionDM == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentDeviceListingViewModelCompanionDM = null;
                    }
                    if (Intrinsics.areEqual(fragmentDeviceListingViewModelCompanionDM.isScanning().getValue(), Boolean.TRUE)) {
                        FragmentDeviceListing fragmentDeviceListing = FragmentDeviceListing.this;
                        fragmentDeviceListing.Y(fragmentDeviceListing.getAnimFileConnecting(), true, null);
                    }
                }
            });
            FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM = this.r;
            if (fragmentDeviceListingViewModelCompanionDM == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentDeviceListingViewModelCompanionDM = null;
            }
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            int association_request_code = ((ActivityPairing) activity2).getASSOCIATION_REQUEST_CODE();
            Bundle arguments = getArguments();
            fragmentDeviceListingViewModelCompanionDM.startScan(requireActivity, association_request_code, false, arguments != null ? arguments.getStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue()) : null);
            return;
        }
        String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(requireContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
        if (!(unGrantedPermissions.length == 0)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                V();
            } else {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, this.p);
            }
        } else if (!AppUtils.isGpsEnabled(requireContext())) {
            T();
        } else {
            Object systemService2 = requireContext().getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (!((BluetoothManager) systemService2).getAdapter().isEnabled()) {
                FragmentActivity activity3 = getActivity();
                Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                ((ActivityPairing) activity3).showOpenBluetoothSettingsDialog();
                return;
            }
            Y(this.v, false, new AnimatorListenerAdapter() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceListing$searchAgain$3
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(@NotNull Animator animation, boolean z) {
                    FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM2;
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    super.onAnimationEnd(animation, z);
                    fragmentDeviceListingViewModelCompanionDM2 = FragmentDeviceListing.this.r;
                    if (fragmentDeviceListingViewModelCompanionDM2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentDeviceListingViewModelCompanionDM2 = null;
                    }
                    if (Intrinsics.areEqual(fragmentDeviceListingViewModelCompanionDM2.isScanning().getValue(), Boolean.TRUE)) {
                        FragmentDeviceListing fragmentDeviceListing = FragmentDeviceListing.this;
                        fragmentDeviceListing.Y(fragmentDeviceListing.getAnimFileConnecting(), true, null);
                    }
                }
            });
            FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM2 = this.r;
            if (fragmentDeviceListingViewModelCompanionDM2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentDeviceListingViewModelCompanionDM2 = null;
            }
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            FragmentActivity activity4 = getActivity();
            Intrinsics.checkNotNull(activity4, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            int association_request_code2 = ((ActivityPairing) activity4).getASSOCIATION_REQUEST_CODE();
            Bundle arguments2 = getArguments();
            fragmentDeviceListingViewModelCompanionDM2.startScan(requireActivity2, association_request_code2, false, arguments2 != null ? arguments2.getStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue()) : null);
        }
    }

    public final void T() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.enable_location_services);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enable_location_services)");
        String string2 = getString(R.string.enable_location_access_in_the_next_screen_for_the_app_to_find_your_band);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.enabl…he_app_to_find_your_band)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentDeviceListing.U(FragmentDeviceListing.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void V() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …on_required\n            )");
        String string2 = getString(R.string.enable_location_access_in_the_next_screen_for_the_app_to_find_your_band);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …d_your_band\n            )");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentDeviceListing.W(FragmentDeviceListing.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void X() {
        SessionManager.getInstance(requireContext()).setScanAllDevice(false);
        if (!AppUtils.isGpsEnabled(requireContext())) {
            T();
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = requireContext().getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                ((ActivityPairing) activity).showOpenBluetoothSettingsDialog();
                return;
            }
            Y(this.v, false, new AnimatorListenerAdapter() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceListing$starScan$1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(@NotNull Animator animation, boolean z) {
                    FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM;
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    super.onAnimationEnd(animation, z);
                    fragmentDeviceListingViewModelCompanionDM = FragmentDeviceListing.this.r;
                    if (fragmentDeviceListingViewModelCompanionDM == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentDeviceListingViewModelCompanionDM = null;
                    }
                    if (Intrinsics.areEqual(fragmentDeviceListingViewModelCompanionDM.isScanning().getValue(), Boolean.TRUE)) {
                        FragmentDeviceListing fragmentDeviceListing = FragmentDeviceListing.this;
                        fragmentDeviceListing.Y(fragmentDeviceListing.getAnimFileConnecting(), true, null);
                    }
                }
            });
            FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM = this.r;
            if (fragmentDeviceListingViewModelCompanionDM == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentDeviceListingViewModelCompanionDM = null;
            }
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            int association_request_code = ((ActivityPairing) activity2).getASSOCIATION_REQUEST_CODE();
            Bundle arguments = getArguments();
            fragmentDeviceListingViewModelCompanionDM.startScan(requireActivity, association_request_code, false, arguments != null ? arguments.getStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue()) : null);
            return;
        }
        Y(this.v, false, new AnimatorListenerAdapter() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceListing$starScan$2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NotNull Animator animation, boolean z) {
                FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM2;
                Intrinsics.checkNotNullParameter(animation, "animation");
                super.onAnimationEnd(animation, z);
                fragmentDeviceListingViewModelCompanionDM2 = FragmentDeviceListing.this.r;
                if (fragmentDeviceListingViewModelCompanionDM2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentDeviceListingViewModelCompanionDM2 = null;
                }
                if (Intrinsics.areEqual(fragmentDeviceListingViewModelCompanionDM2.isScanning().getValue(), Boolean.TRUE)) {
                    FragmentDeviceListing fragmentDeviceListing = FragmentDeviceListing.this;
                    fragmentDeviceListing.Y(fragmentDeviceListing.getAnimFileConnecting(), true, null);
                }
            }
        });
        FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM2 = this.r;
        if (fragmentDeviceListingViewModelCompanionDM2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentDeviceListingViewModelCompanionDM2 = null;
        }
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        int association_request_code2 = ((ActivityPairing) activity3).getASSOCIATION_REQUEST_CODE();
        Bundle arguments2 = getArguments();
        fragmentDeviceListingViewModelCompanionDM2.startScan(requireActivity2, association_request_code2, false, arguments2 != null ? arguments2.getStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue()) : null);
    }

    public final void Y(int i, boolean z, AnimatorListenerAdapter animatorListenerAdapter) {
        B().lottieView.removeAllAnimatorListeners();
        B().lottieView.cancelAnimation();
        B().lottieView.setAnimation(i);
        if (z) {
            B().lottieView.loop(true);
        } else {
            B().lottieView.loop(false);
        }
        B().lottieView.playAnimation();
        if (animatorListenerAdapter != null) {
            B().lottieView.addAnimatorListener(animatorListenerAdapter);
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

    @Override // com.coveiot.android.leonardo.onboarding.paring.adapters.ScannedBleDeviceAdapter.SelectedBleDeviceListener
    public void deviceSelector(@NotNull BleDevice device, int i) {
        Intrinsics.checkNotNullParameter(device, "device");
        this.q = device;
        ScannedBleDeviceAdapter scannedBleDeviceAdapter = this.t;
        if (scannedBleDeviceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scannedBleDeviceAdapter");
            scannedBleDeviceAdapter = null;
        }
        scannedBleDeviceAdapter.setSelectedDevicePosition(i);
        B().btnPairDevice.setAlpha(1.0f);
        B().btnPairDevice.setEnabled(true);
        B().btnPairDevice.setClickable(true);
    }

    public final int getAnimFileConnecting() {
        return this.w;
    }

    public final int getAnimFileStart() {
        return this.v;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.o) {
            if (AppUtils.isGpsEnabled(requireContext())) {
                Object systemService = requireContext().getSystemService("bluetooth");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
                if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
                    FragmentActivity activity = getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                    ((ActivityPairing) activity).showOpenBluetoothSettingsDialog();
                    return;
                }
                FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM = this.r;
                if (fragmentDeviceListingViewModelCompanionDM == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentDeviceListingViewModelCompanionDM = null;
                }
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                FragmentActivity activity2 = getActivity();
                Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                int association_request_code = ((ActivityPairing) activity2).getASSOCIATION_REQUEST_CODE();
                Bundle arguments = getArguments();
                fragmentDeviceListingViewModelCompanionDM.startScan(requireActivity, association_request_code, false, arguments != null ? arguments.getStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue()) : null);
            }
        } else if (i == this.n) {
            Object systemService2 = requireContext().getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (((BluetoothManager) systemService2).getAdapter().isEnabled()) {
                FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM2 = this.r;
                if (fragmentDeviceListingViewModelCompanionDM2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentDeviceListingViewModelCompanionDM2 = null;
                }
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                FragmentActivity activity3 = getActivity();
                Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                int association_request_code2 = ((ActivityPairing) activity3).getASSOCIATION_REQUEST_CODE();
                Bundle arguments2 = getArguments();
                fragmentDeviceListingViewModelCompanionDM2.startScan(requireActivity2, association_request_code2, false, arguments2 != null ? arguments2.getStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue()) : null);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentDeviceListingFragmentBinding.inflate(inflater, viewGroup, false);
        View root = B().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.p && grantResults.length > 0 && grantResults[0] == 0) {
            if (!AppUtils.isGpsEnabled(requireContext())) {
                T();
                return;
            }
            Object systemService = requireContext().getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                ((ActivityPairing) activity).showOpenBluetoothSettingsDialog();
                return;
            }
            FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM = this.r;
            if (fragmentDeviceListingViewModelCompanionDM == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentDeviceListingViewModelCompanionDM = null;
            }
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            int association_request_code = ((ActivityPairing) activity2).getASSOCIATION_REQUEST_CODE();
            Bundle arguments = getArguments();
            fragmentDeviceListingViewModelCompanionDM.startScan(requireActivity, association_request_code, false, arguments != null ? arguments.getStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue()) : null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"MissingPermission"})
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        C();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isRoundWatch(requireContext)) {
            this.v = R.raw.round_blue_scan_start;
            this.w = R.raw.round_blue_connecting;
        }
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (companion.isIDODevice(requireContext2)) {
            B().clPairViaQR1.setVisibility(0);
            B().clPairViaQR.setVisibility(0);
            B().tvOr.setVisibility(0);
            B().tvInfo1.setText(getString(R.string.please_search_again_via_qr_code));
        } else {
            B().clPairViaQR1.setVisibility(8);
            B().clPairViaQR.setVisibility(8);
            B().tvOr.setVisibility(8);
            B().tvInfo1.setText(getString(R.string.please_search_again));
        }
        FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM = null;
        UserDataManager.getInstance(requireContext()).saveLiveHealthData(null);
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        this.r = (FragmentDeviceListingViewModelCompanionDM) ViewModelProviders.of(this, new ViewModelFactory(requireContext3)).get(FragmentDeviceListingViewModelCompanionDM.class);
        this.t = new ScannedBleDeviceAdapter(this);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.DEVICE_SCANNING_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        X();
        TextView scanQRcode = (TextView) B().tvBack.findViewById(R.id.scanQR);
        scanQRcode.setText(getString(R.string.scanQRCode));
        scanQRcode.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_qr_scan, 0);
        scanQRcode.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.F(FragmentDeviceListing.this, view2);
            }
        });
        if (Intrinsics.areEqual(SessionManager.getInstance(requireContext()).getDeviceModelBean().getIsQRCodeOnboardingSupported(), Boolean.TRUE)) {
            Intrinsics.checkNotNullExpressionValue(scanQRcode, "scanQRcode");
            visible(scanQRcode);
        } else {
            Intrinsics.checkNotNullExpressionValue(scanQRcode, "scanQRcode");
            gone(scanQRcode);
        }
        FragmentDeviceListingFragmentBinding B = B();
        RecyclerView recyclerView = B.rvBleDevices;
        ScannedBleDeviceAdapter scannedBleDeviceAdapter = this.t;
        if (scannedBleDeviceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scannedBleDeviceAdapter");
            scannedBleDeviceAdapter = null;
        }
        recyclerView.setAdapter(scannedBleDeviceAdapter);
        B.rvBleDevices.setLayoutManager(new LinearLayoutManager(requireActivity()));
        TextView textView = B.tvDeviceName;
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        textView.setText(companion.getWatchName(requireContext4));
        B.tvDeviceName.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, B.tvDeviceName.getTextSize(), getResources().getColor(R.color.white), getResources().getColor(R.color.color_b3b3b3), Shader.TileMode.MIRROR));
        B.tvBack.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.G(FragmentDeviceListing.this, view2);
            }
        });
        B.tvBack1.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.H(FragmentDeviceListing.this, view2);
            }
        });
        B.tvSearchAgain.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.I(FragmentDeviceListing.this, view2);
            }
        });
        B.tvTrouble.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.J(FragmentDeviceListing.this, view2);
            }
        });
        B.tvUnableToFindDevice.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.K(FragmentDeviceListing.this, view2);
            }
        });
        B.btnPairDevice.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.L(FragmentDeviceListing.this, view2);
            }
        });
        B.clPairViaQR.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.M(FragmentDeviceListing.this, view2);
            }
        });
        B.clPairViaQR1.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.N(FragmentDeviceListing.this, view2);
            }
        });
        B.scanAllDevicesTv.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDeviceListing.O(FragmentDeviceListing.this, view2);
            }
        });
        if (kotlin.text.m.equals("prod", "prod", true)) {
            B.scanAllDevicesTv.setVisibility(8);
        }
        FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM2 = this.r;
        if (fragmentDeviceListingViewModelCompanionDM2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentDeviceListingViewModelCompanionDM2 = null;
        }
        MutableLiveData<Boolean> isScanning = fragmentDeviceListingViewModelCompanionDM2.isScanning();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        isScanning.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.m
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentDeviceListing.P(Function1.this, obj);
            }
        });
        FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM3 = this.r;
        if (fragmentDeviceListingViewModelCompanionDM3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentDeviceListingViewModelCompanionDM3 = null;
        }
        MutableLiveData<Boolean> noDeviceFound = fragmentDeviceListingViewModelCompanionDM3.getNoDeviceFound();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final b bVar = new b();
        noDeviceFound.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentDeviceListing.Q(Function1.this, obj);
            }
        });
        FragmentDeviceListingViewModelCompanionDM fragmentDeviceListingViewModelCompanionDM4 = this.r;
        if (fragmentDeviceListingViewModelCompanionDM4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentDeviceListingViewModelCompanionDM = fragmentDeviceListingViewModelCompanionDM4;
        }
        MutableLiveData<ArrayList<BleDevice>> bleDevices = fragmentDeviceListingViewModelCompanionDM.getBleDevices();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final c cVar = new c();
        bleDevices.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.l
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentDeviceListing.R(Function1.this, obj);
            }
        });
    }

    public final void setAnimFileConnecting(int i) {
        this.w = i;
    }

    public final void setAnimFileStart(int i) {
        this.v = i;
    }
}
