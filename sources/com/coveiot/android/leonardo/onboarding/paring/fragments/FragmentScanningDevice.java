package com.coveiot.android.leonardo.onboarding.paring.fragments;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentScanningDeviceViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentScanningDevice extends Fragment implements QRCodeReaderView.OnQRCodeReadListener, ViewModelListener, ScanResultListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public OnPairScanningFragmentInteractionListener h;
    public LoadingDialog i;
    public FragmentScanningDeviceViewModel k;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage m;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String j = "FragmentScanningDevice";
    public final int l = 102;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentScanningDevice newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentScanningDevice fragmentScanningDevice = new FragmentScanningDevice();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            fragmentScanningDevice.setArguments(bundle);
            return fragmentScanningDevice;
        }
    }

    /* loaded from: classes5.dex */
    public interface OnPairScanningFragmentInteractionListener {
        void addManually(boolean z);
    }

    public static final void g(FragmentScanningDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnPairScanningFragmentInteractionListener onPairScanningFragmentInteractionListener = this$0.h;
        Intrinsics.checkNotNull(onPairScanningFragmentInteractionListener);
        onPairScanningFragmentInteractionListener.addManually(false);
    }

    public static final void i(FragmentScanningDevice this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openLocationSettings(this$0.requireActivity(), this$0.l);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void k(FragmentScanningDevice this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.m;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentScanningDevice newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final void d() {
        String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(requireContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
        if (!(unGrantedPermissions.length == 0)) {
            ((QRCodeReaderView) _$_findCachedViewById(R.id.qrScanView)).setVisibility(8);
            ((ImageView) _$_findCachedViewById(R.id.qrScanDiabled)).setVisibility(0);
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                String string = getResources().getString(R.string.need_location_permission);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…need_location_permission)");
                j(string);
                return;
            } else if (Build.VERSION.SDK_INT < 26) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, FragmentScanningDeviceKt.getLOCATION_PERMISSION_REQUEST_CODE());
                return;
            } else {
                return;
            }
        }
        f();
    }

    public final void e() {
        PermissionUtils.checkPermission(requireActivity(), "android.permission.CAMERA", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentScanningDevice$checkPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                String str;
                ((QRCodeReaderView) FragmentScanningDevice.this._$_findCachedViewById(R.id.qrScanView)).setVisibility(8);
                ((ImageView) FragmentScanningDevice.this._$_findCachedViewById(R.id.qrScanDiabled)).setVisibility(0);
                String[] strArr = {"android.permission.CAMERA", "android.permission.ACCESS_FINE_LOCATION"};
                if (Build.VERSION.SDK_INT >= 26) {
                    strArr = new String[]{"android.permission.CAMERA"};
                    str = FragmentScanningDevice.this.j;
                    LogHelper.d(str, "Asking for Camera Permission only its above Oreo");
                }
                FragmentScanningDevice.this.requestPermissions(strArr, 1);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ((QRCodeReaderView) FragmentScanningDevice.this._$_findCachedViewById(R.id.qrScanView)).setVisibility(8);
                ((ImageView) FragmentScanningDevice.this._$_findCachedViewById(R.id.qrScanDiabled)).setVisibility(0);
                FragmentScanningDevice fragmentScanningDevice = FragmentScanningDevice.this;
                String string = fragmentScanningDevice.getResources().getString(R.string.need_camera_permission);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…g.need_camera_permission)");
                fragmentScanningDevice.j(string);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                String str;
                String str2;
                String str3;
                str = FragmentScanningDevice.this.j;
                LogHelper.d(str, "camera onPermissionGranted: called");
                if (Build.VERSION.SDK_INT >= 26) {
                    FragmentScanningDevice.this.f();
                    str3 = FragmentScanningDevice.this.j;
                    LogHelper.d(str3, "camera onPermissionGranted: called, QR Code initialization");
                    return;
                }
                FragmentScanningDevice.this.d();
                str2 = FragmentScanningDevice.this.j;
                LogHelper.d(str2, "camera onPermissionGranted: Asking for location");
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ((QRCodeReaderView) FragmentScanningDevice.this._$_findCachedViewById(R.id.qrScanView)).setVisibility(8);
                ((ImageView) FragmentScanningDevice.this._$_findCachedViewById(R.id.qrScanDiabled)).setVisibility(0);
                FragmentScanningDevice fragmentScanningDevice = FragmentScanningDevice.this;
                String string = fragmentScanningDevice.getResources().getString(R.string.need_camera_permission);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…g.need_camera_permission)");
                fragmentScanningDevice.j(string);
            }
        });
    }

    public final void f() {
        if (!AppUtils.isGpsEnabled(requireContext())) {
            h();
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
        int i = R.id.qrScanView;
        if (((QRCodeReaderView) _$_findCachedViewById(i)) != null) {
            ((QRCodeReaderView) _$_findCachedViewById(i)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.qrScanDiabled)).setVisibility(8);
            ((QRCodeReaderView) _$_findCachedViewById(i)).setOnQRCodeReadListener(this);
            ((QRCodeReaderView) _$_findCachedViewById(i)).setQRDecodingEnabled(true);
            ((QRCodeReaderView) _$_findCachedViewById(i)).setAutofocusInterval(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
            ((QRCodeReaderView) _$_findCachedViewById(i)).setBackCamera();
            ((QRCodeReaderView) _$_findCachedViewById(i)).startCamera();
        }
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetDialogOneButtonTitleMessage() {
        return this.m;
    }

    public final void h() {
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
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentScanningDevice.i(FragmentScanningDevice.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void j(String str) {
        if (getActivity() != null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            String string = getString(R.string.permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
            this.m = bottomSheetDialogOneButtonTitleMessage;
            bottomSheetDialogOneButtonTitleMessage.setCancelable(true);
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.m;
            if (bottomSheetDialogOneButtonTitleMessage2 != null) {
                bottomSheetDialogOneButtonTitleMessage2.setCancelableOutside(true);
            }
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.m;
            if (bottomSheetDialogOneButtonTitleMessage3 != null) {
                String string2 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
                bottomSheetDialogOneButtonTitleMessage3.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.n0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentScanningDevice.k(FragmentScanningDevice.this, view);
                    }
                });
            }
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = this.m;
            if (bottomSheetDialogOneButtonTitleMessage4 != null) {
                bottomSheetDialogOneButtonTitleMessage4.show();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof OnPairScanningFragmentInteractionListener) {
            this.h = (OnPairScanningFragmentInteractionListener) context;
            return;
        }
        throw new RuntimeException(context + " must implement OnPairTShirtFragmentInteractionListener");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_pair_qrcode_scan, viewGroup, false);
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (getActivity() != null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            ((ActivityPairing) activity).dismissProgress();
            f();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.h = null;
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
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.m;
        boolean z = true;
        if (bottomSheetDialogOneButtonTitleMessage2 == null || !bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            z = false;
        }
        if (!z || (bottomSheetDialogOneButtonTitleMessage = this.m) == null) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @Override // com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener
    public void onQRCodeRead(@Nullable String str, @Nullable PointF[] pointFArr) {
        try {
            LogHelper.d(this.j, str);
            ((QRCodeReaderView) _$_findCachedViewById(R.id.qrScanView)).stopCamera();
            String str2 = this.j;
            LogHelper.d(str2, "Result of the QR Code is " + str);
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            String string = getResources().getString(R.string.connecting_to_band_name, str);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(\n   …iceName\n                )");
            ((ActivityPairing) activity).showProgresswithMsg(string);
            FragmentScanningDeviceViewModel fragmentScanningDeviceViewModel = this.k;
            if (fragmentScanningDeviceViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentScanningDeviceViewModel = null;
            }
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            fragmentScanningDeviceViewModel.startScan(str, requireActivity, ((ActivityPairing) activity2).getASSOCIATION_REQUEST_CODE(), true);
        } catch (Exception e) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string2 = getString(R.string.scan_error);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.scan_error)");
            ViewUtilsKt.toast(requireContext, string2);
            FragmentActivity activity3 = getActivity();
            Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            ((ActivityPairing) activity3).dismissProgress();
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == FragmentScanningDeviceKt.getLOCATION_PERMISSION_REQUEST_CODE()) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                f();
            }
        } else if (i == 1) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                if (Build.VERSION.SDK_INT >= 26) {
                    f();
                    LogHelper.d(this.j, "Initlize QR Code scanner");
                    return;
                }
                d();
                LogHelper.d(this.j, "Asking for location permission camera permission granted");
                return;
            }
            ((TextView) _$_findCachedViewById(R.id.addManually)).performClick();
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.ScanResultListener
    public void onResponse(@NotNull BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        LogHelper.d(this.j, getString(R.string.association_success));
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) activity).dismissProgress();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        e();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ((TextView) _$_findCachedViewById(R.id.addManually)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentScanningDevice.g(FragmentScanningDevice.this, view2);
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentScanningDeviceViewModel fragmentScanningDeviceViewModel = (FragmentScanningDeviceViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentScanningDeviceViewModel.class);
        this.k = fragmentScanningDeviceViewModel;
        LoadingDialog loadingDialog = null;
        if (fragmentScanningDeviceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentScanningDeviceViewModel = null;
        }
        fragmentScanningDeviceViewModel.setViewModelListener(this);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        LoadingDialog loadingDialog2 = new LoadingDialog(requireActivity);
        this.i = loadingDialog2;
        loadingDialog2.setCanceledOnTouchOutside(false);
        LoadingDialog loadingDialog3 = this.i;
        if (loadingDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgressDialog");
        } else {
            loadingDialog = loadingDialog3;
        }
        loadingDialog.setTitle("Connecting...");
    }

    public final void setBottomSheetDialogOneButtonTitleMessage(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.m = bottomSheetDialogOneButtonTitleMessage;
    }
}
