package com.coveiot.android.leonardo.onboarding.paring.fragments;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentQRScanDeviceBinding;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentQRScanDeviceViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentQRScanDevice extends BaseFragment implements ViewModelListener, ScanResultListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentQRScanDeviceBinding m;
    public LoadingDialog n;
    public FragmentQRScanDeviceViewModel p;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String o = "FragmentQRScanDevice";
    public final int q = 102;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentQRScanDevice newInstance() {
            return new FragmentQRScanDevice();
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<ModuleInstallResponse, Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ModuleInstallResponse moduleInstallResponse) {
            invoke2(moduleInstallResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(ModuleInstallResponse moduleInstallResponse) {
            if (moduleInstallResponse.areModulesAlreadyInstalled()) {
                LogHelper.i("checkScanModuleInstall", "Modules are already installed");
            }
            LogHelper.i("checkScanModuleInstall", "Modules successfully installed");
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<Barcode, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Barcode barcode) {
            invoke2(barcode);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Barcode barcode) {
            LogHelper.i("GmsBarcodeScanning", "Value: displayValue - " + barcode.getDisplayValue());
            LogHelper.i("GmsBarcodeScanning", "Value: rawValue - " + barcode.getRawValue());
            String displayValue = barcode.getDisplayValue();
            try {
                FragmentQRScanDevice.this.B().btnScan.setEnabled(false);
                LogHelper.i(FragmentQRScanDevice.this.o, displayValue);
                FragmentActivity activity = FragmentQRScanDevice.this.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                String string = FragmentQRScanDevice.this.getResources().getString(R.string.connecting_to_band_name, displayValue);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(\n   …                        )");
                ((ActivityPairing) activity).showProgresswithMsg(string);
                FragmentQRScanDeviceViewModel fragmentQRScanDeviceViewModel = FragmentQRScanDevice.this.p;
                if (fragmentQRScanDeviceViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentQRScanDeviceViewModel = null;
                }
                FragmentActivity requireActivity = FragmentQRScanDevice.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                FragmentActivity activity2 = FragmentQRScanDevice.this.getActivity();
                Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                fragmentQRScanDeviceViewModel.startQRScan(displayValue, requireActivity, ((ActivityPairing) activity2).getASSOCIATION_REQUEST_CODE(), true);
            } catch (Exception e) {
                FragmentQRScanDevice fragmentQRScanDevice = FragmentQRScanDevice.this;
                Context requireContext = fragmentQRScanDevice.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string2 = FragmentQRScanDevice.this.getString(R.string.scan_error);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.scan_error)");
                fragmentQRScanDevice.toast(requireContext, string2);
                FragmentActivity activity3 = FragmentQRScanDevice.this.getActivity();
                Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
                ((ActivityPairing) activity3).dismissProgress();
                e.printStackTrace();
                FragmentQRScanDevice.this.B().ivQRScanbg.setVisibility(0);
            }
        }
    }

    public static final void A(Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
        LogHelper.i("checkScanModuleInstall", "Error installing modules - - " + it);
    }

    public static final void D(FragmentQRScanDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public static final void E(FragmentQRScanDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceModelBean deviceModelBean = SessionManager.getInstance(this$0.requireContext()).getDeviceModelBean();
        deviceModelBean.getScanFilter();
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        List<String> scanFilter = deviceModelBean.getScanFilter();
        Intrinsics.checkNotNullExpressionValue(scanFilter, "deviceIndo.scanFilter");
        ((ActivityPairing) activity).loadDeviceListingFragment(scanFilter);
    }

    public static final void G(FragmentQRScanDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.navigateToActivityQROnboardingFTU(requireContext);
    }

    public static final void H(FragmentQRScanDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B().btnScan.setEnabled(false);
        this$0.F();
    }

    public static final void J(FragmentQRScanDevice this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openLocationSettings(this$0.requireActivity(), this$0.q);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void L(FragmentQRScanDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.r;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
    }

    public static final void N(final FragmentQRScanDevice this$0, GmsBarcodeScanner scanner, Exception e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(scanner, "$scanner");
        Intrinsics.checkNotNullParameter(e, "e");
        LogHelper.i("GmsBarcodeScanning", "addOnFailureListener: " + e.getMessage());
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = this$0.getString(R.string.scan_error);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.scan_error)");
        this$0.toast(requireContext, string);
        this$0.y(scanner);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.d0
            @Override // java.lang.Runnable
            public final void run() {
                FragmentQRScanDevice.O(FragmentQRScanDevice.this);
            }
        }, 300L);
    }

    public static final void O(FragmentQRScanDevice this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            ((ActivityPairing) activity).dismissProgress();
            this$0.B().btnScan.setEnabled(true);
        }
    }

    public static final void P(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void Q(FragmentQRScanDevice this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i("GmsBarcodeScanning", "addOnCanceledListener ");
        this$0.B().btnScan.setEnabled(true);
    }

    @JvmStatic
    @NotNull
    public static final FragmentQRScanDevice newInstance() {
        return Companion.newInstance();
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final FragmentQRScanDeviceBinding B() {
        FragmentQRScanDeviceBinding fragmentQRScanDeviceBinding = this.m;
        Intrinsics.checkNotNull(fragmentQRScanDeviceBinding);
        return fragmentQRScanDeviceBinding;
    }

    public final void C() {
        ((TextView) B().toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.scan_qr_code_text));
        ((TextView) B().toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentQRScanDevice.D(FragmentQRScanDevice.this, view);
            }
        });
        TextView textView = (TextView) B().toolbar.findViewById(R.id.scanQR);
        Intrinsics.checkNotNullExpressionValue(textView, "textView");
        visible(textView);
        textView.setText(getString(R.string.pair_with_bt));
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_bt_blue_icon, 0);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentQRScanDevice.E(FragmentQRScanDevice.this, view);
            }
        });
    }

    public final void F() {
        if (!AppUtils.isGpsEnabled(requireContext())) {
            I();
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
        M();
    }

    public final void I() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.enable_location_services);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enable_location_services)");
        String string2 = getString(R.string.enable_location_access_in_the_next_screen_for_the_app_to_find_your_band);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.enabl…he_app_to_find_your_band)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, string, string2);
        bottomSheetDialogOneButtonTitleMessage.setCancelable(true);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(true);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentQRScanDevice.J(FragmentQRScanDevice.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void K(String str) {
        if (getActivity() != null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            String string = getString(R.string.permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
            this.r = bottomSheetDialogOneButtonTitleMessage;
            bottomSheetDialogOneButtonTitleMessage.setCancelable(true);
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.r;
            if (bottomSheetDialogOneButtonTitleMessage2 != null) {
                bottomSheetDialogOneButtonTitleMessage2.setCancelableOutside(true);
            }
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.r;
            if (bottomSheetDialogOneButtonTitleMessage3 != null) {
                String string2 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleMessage3.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.h0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQRScanDevice.L(FragmentQRScanDevice.this, view);
                    }
                });
            }
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = this.r;
            if (bottomSheetDialogOneButtonTitleMessage4 != null) {
                bottomSheetDialogOneButtonTitleMessage4.show();
            }
        }
    }

    public final void M() {
        GmsBarcodeScannerOptions build = new GmsBarcodeScannerOptions.Builder().setBarcodeFormats(256, 0).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…om()\n            .build()");
        final GmsBarcodeScanner client = GmsBarcodeScanning.getClient(requireContext(), build);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(requireContext(), options)");
        Task<Barcode> startScan = client.startScan();
        final b bVar = new b();
        startScan.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.c0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FragmentQRScanDevice.P(Function1.this, obj);
            }
        }).addOnCanceledListener(new OnCanceledListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.j0
            @Override // com.google.android.gms.tasks.OnCanceledListener
            public final void onCanceled() {
                FragmentQRScanDevice.Q(FragmentQRScanDevice.this);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.k0
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FragmentQRScanDevice.N(FragmentQRScanDevice.this, client, exc);
            }
        });
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

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonTitleMessage() {
        return this.r;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentQRScanDeviceBinding.inflate(inflater, viewGroup, false);
        return B().getRoot();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (getActivity() != null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            ((ActivityPairing) activity).dismissProgress();
            if (message.length() == 0) {
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string = getString(R.string.invalidQRTitle);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.invalidQRTitle)");
                String string2 = getString(R.string.invalidQRText);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.invalidQRText)");
                final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, string, string2);
                bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(true);
                String string3 = getString(R.string.try_again);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.try_again)");
                bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentQRScanDevice$onDataFailure$1
                    @Override // android.view.View.OnClickListener
                    public void onClick(@Nullable View view) {
                        BottomSheetDialogOneButtonTitleMessage.this.dismiss();
                    }
                });
                bottomSheetDialogOneButtonTitleMessage.show();
                B().btnScan.setEnabled(true);
                return;
            }
            Toast.makeText(getContext(), message, 0).show();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
    public void onError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        AppUtils.showToast(getContext(), getString(R.string.association_failed));
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) activity).dismissProgress();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage;
        super.onPause();
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.r;
        boolean z = true;
        if (bottomSheetDialogOneButtonTitleMessage2 == null || !bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            z = false;
        }
        if (!z || (bottomSheetDialogOneButtonTitleMessage = this.r) == null) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == FragmentQRScanDeviceKt.getLOCATION_PERMISSION_CODE()) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                B().ivQRScanbg.setVisibility(0);
            }
        } else if (i == 1) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                if (Build.VERSION.SDK_INT >= 26) {
                    B().ivQRScanbg.setVisibility(0);
                    LogHelper.d(this.o, "Initlize QR Code scanner");
                    return;
                }
                w();
                LogHelper.d(this.o, "Asking for location permission camera permission granted");
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
    public void onResponse(@NotNull BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        LogHelper.d(this.o, getString(R.string.association_success));
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) activity).dismissProgress();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        x();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        C();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentQRScanDeviceViewModel fragmentQRScanDeviceViewModel = (FragmentQRScanDeviceViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentQRScanDeviceViewModel.class);
        this.p = fragmentQRScanDeviceViewModel;
        LoadingDialog loadingDialog = null;
        if (fragmentQRScanDeviceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentQRScanDeviceViewModel = null;
        }
        fragmentQRScanDeviceViewModel.setViewModelListener(this);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        LoadingDialog loadingDialog2 = new LoadingDialog(requireActivity);
        this.n = loadingDialog2;
        loadingDialog2.setCanceledOnTouchOutside(false);
        LoadingDialog loadingDialog3 = this.n;
        if (loadingDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialog");
        } else {
            loadingDialog = loadingDialog3;
        }
        loadingDialog.setTitle("Connecting...");
        B().tvHowToScan.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentQRScanDevice.G(FragmentQRScanDevice.this, view2);
            }
        });
        B().btnScan.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentQRScanDevice.H(FragmentQRScanDevice.this, view2);
            }
        });
    }

    public final void setBottomSheetDialogOneButtonTitleMessage(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.r = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void w() {
        String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(requireContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
        if (!(unGrantedPermissions.length == 0)) {
            B().ivQRScanbg.setVisibility(0);
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                String string = getResources().getString(R.string.need_location_permission);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…need_location_permission)");
                K(string);
                return;
            } else if (Build.VERSION.SDK_INT < 26) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, FragmentQRScanDeviceKt.getLOCATION_PERMISSION_CODE());
                return;
            } else {
                return;
            }
        }
        B().ivQRScanbg.setVisibility(0);
    }

    public final void x() {
        PermissionUtils.checkPermission(requireActivity(), "android.permission.CAMERA", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentQRScanDevice$checkPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                String[] strArr = {"android.permission.CAMERA", "android.permission.ACCESS_FINE_LOCATION"};
                if (Build.VERSION.SDK_INT >= 26) {
                    strArr = new String[]{"android.permission.CAMERA"};
                    LogHelper.d(FragmentQRScanDevice.this.o, "Asking for Camera Permission only its above Oreo");
                }
                FragmentQRScanDevice.this.requestPermissions(strArr, 1);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                FragmentQRScanDevice.this.B().ivQRScanbg.setVisibility(0);
                FragmentQRScanDevice fragmentQRScanDevice = FragmentQRScanDevice.this;
                String string = fragmentQRScanDevice.getResources().getString(R.string.need_camera_permission);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…g.need_camera_permission)");
                fragmentQRScanDevice.K(string);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                LogHelper.d(FragmentQRScanDevice.this.o, "camera onPermissionGranted: called");
                if (Build.VERSION.SDK_INT >= 26) {
                    FragmentQRScanDevice.this.B().ivQRScanbg.setVisibility(0);
                    LogHelper.d(FragmentQRScanDevice.this.o, "camera onPermissionGranted: called, QR Code initialization");
                    return;
                }
                FragmentQRScanDevice.this.w();
                LogHelper.d(FragmentQRScanDevice.this.o, "camera onPermissionGranted: Asking for location");
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                FragmentQRScanDevice.this.B().ivQRScanbg.setVisibility(0);
                FragmentQRScanDevice fragmentQRScanDevice = FragmentQRScanDevice.this;
                String string = fragmentQRScanDevice.getResources().getString(R.string.need_camera_permission);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…g.need_camera_permission)");
                fragmentQRScanDevice.K(string);
            }
        });
    }

    public final void y(GmsBarcodeScanner gmsBarcodeScanner) {
        ModuleInstallRequest build = ModuleInstallRequest.newBuilder().addApi(gmsBarcodeScanner).build();
        Intrinsics.checkNotNullExpressionValue(build, "newBuilder()\n           …\n                .build()");
        ModuleInstallClient client = ModuleInstall.getClient(requireContext());
        Intrinsics.checkNotNullExpressionValue(client, "getClient(requireContext())");
        Task<ModuleInstallResponse> installModules = client.installModules(build);
        final a aVar = a.INSTANCE;
        installModules.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.m0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FragmentQRScanDevice.z(Function1.this, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.l0
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FragmentQRScanDevice.A(exc);
            }
        });
    }
}
