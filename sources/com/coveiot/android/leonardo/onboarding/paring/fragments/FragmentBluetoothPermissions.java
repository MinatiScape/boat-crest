package com.coveiot.android.leonardo.onboarding.paring.fragments;

import android.app.Activity;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.clevertap.android.sdk.PushPermissionManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentBluetoothPermissionsViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentBluetoothPermissions extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public OnPairBluetoothPermissionInteractionListener listener;
    @Nullable
    public BottomSheetDialogTwoButtons p;
    public boolean q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int m = 202;
    public final int n = 102;
    public final int o = 1001;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentBluetoothPermissions newInstance() {
            return new FragmentBluetoothPermissions();
        }
    }

    /* loaded from: classes5.dex */
    public interface OnPairBluetoothPermissionInteractionListener {
        void addManually(boolean z);
    }

    public static final void q(FragmentBluetoothPermissions this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        FirebaseEventParams.EventName eventName = FirebaseEventParams.EventName.KH_TAP;
        analyticsLog.setEventName(eventName.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GRANT_PERMISSION_DIALOG.getValue());
        analyticsLog.setAppPermissionId("Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,BLUETOOTH_SERVICE");
        CoveAnalyticsManager.Companion companion = CoveAnalyticsManager.Companion;
        companion.getInstance().logEvent(analyticsLog);
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            Toast.makeText(this$0.requireContext(), (int) R.string.please_enable_internet, 0).show();
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this$0.r();
            if (i >= 31 && i < 33) {
                this$0.p(CleverTapConstants.CustomEventValues.NA.getValue());
                return;
            } else if (i >= 33) {
                this$0.p(CleverTapConstants.CustomEventValues.YES.getValue());
                return;
            } else {
                this$0.p(CleverTapConstants.CustomEventValues.NA.getValue());
                return;
            }
        }
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
        Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
        if (!(unGrantedPermissions.length == 0)) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity);
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION")) {
                this$0.w();
                return;
            } else if (i < 26) {
                FragmentActivity activity2 = this$0.getActivity();
                Intrinsics.checkNotNull(activity2);
                ActivityCompat.requestPermissions(activity2, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, this$0.o);
                return;
            } else {
                return;
            }
        }
        AnalyticsLog analyticsLog2 = new AnalyticsLog();
        analyticsLog2.setEventName(eventName.getValue());
        analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.PAIRING_PERMISSION_INFO_SCREEN.getValue());
        analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_DEVICE_SELECTION_SCREEN.getValue());
        analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.NEXT_BUTTON.getValue());
        companion.getInstance().logEvent(analyticsLog2);
        Context context2 = this$0.getContext();
        Intrinsics.checkNotNull(context2);
        if (!AppUtils.isGpsEnabled(context2)) {
            this$0.s();
            return;
        }
        Context context3 = this$0.getContext();
        Intrinsics.checkNotNull(context3);
        Object systemService = context3.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
            this$0.showOpenBluetoothSettingsDialog();
            return;
        }
        this$0.r();
        this$0.p(CleverTapConstants.CustomEventValues.NO.getValue());
    }

    public static final void t(FragmentBluetoothPermissions this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        AppUtils.openLocationSettings(activity, this$0.n);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void u(FragmentBluetoothPermissions this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        AppUtils.openBluetoothSettings(activity);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void v(FragmentBluetoothPermissions this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void x(FragmentBluetoothPermissions this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        AppUtils.openAppSettings(activity, -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
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
    public final BottomSheetDialogTwoButtons getEnableBluetoothDialog() {
        return this.p;
    }

    @NotNull
    public final OnPairBluetoothPermissionInteractionListener getListener() {
        OnPairBluetoothPermissionInteractionListener onPairBluetoothPermissionInteractionListener = this.listener;
        if (onPairBluetoothPermissionInteractionListener != null) {
            return onPairBluetoothPermissionInteractionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
    }

    public final boolean isGranted() {
        return this.q;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentBluetoothPermissionsViewModel fragmentBluetoothPermissionsViewModel = (FragmentBluetoothPermissionsViewModel) ViewModelProviders.of(this).get(FragmentBluetoothPermissionsViewModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.n) {
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            if (AppUtils.isGpsEnabled(context)) {
                Context context2 = getContext();
                Intrinsics.checkNotNull(context2);
                Object systemService = context2.getSystemService("bluetooth");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
                if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
                    showOpenBluetoothSettingsDialog();
                } else {
                    r();
                }
            }
        } else if (i == this.m) {
            Context context3 = getContext();
            Intrinsics.checkNotNull(context3);
            Object systemService2 = context3.getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (((BluetoothManager) systemService2).getAdapter().isEnabled()) {
                r();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof OnPairBluetoothPermissionInteractionListener) {
            setListener((OnPairBluetoothPermissionInteractionListener) activity);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_bluetooth_permissions_fragment, viewGroup, false);
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
        if (i == this.o && grantResults.length > 0 && grantResults[0] == 0) {
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            if (!AppUtils.isGpsEnabled(context)) {
                s();
                return;
            }
            Context context2 = getContext();
            Intrinsics.checkNotNull(context2);
            Object systemService = context2.getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
                showOpenBluetoothSettingsDialog();
            } else {
                r();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        int i = Build.VERSION.SDK_INT;
        if (i >= 33) {
            ((LinearLayout) _$_findCachedViewById(R.id.cv_notification)).setVisibility(0);
        } else {
            ((LinearLayout) _$_findCachedViewById(R.id.cv_notification)).setVisibility(8);
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String[] unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
        if (i >= 31 && i < 33) {
            Context context2 = getContext();
            Intrinsics.checkNotNull(context2);
            unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(context2, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"});
        }
        if (i >= 33) {
            Context context3 = getContext();
            Intrinsics.checkNotNull(context3);
            unGrantedPermissions = PermissionUtils.checkPermissionsHasGranted(context3, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", PushPermissionManager.ANDROID_PERMISSION_STRING, "android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"});
        }
        Intrinsics.checkNotNullExpressionValue(unGrantedPermissions, "unGrantedPermissions");
        if (!(unGrantedPermissions.length == 0)) {
            ((Button) _$_findCachedViewById(R.id.btn_grant_permission)).setText(getString(R.string.grant_permission));
        } else {
            ((Button) _$_findCachedViewById(R.id.btn_grant_permission)).setText(getString(R.string.next));
        }
        ((Button) _$_findCachedViewById(R.id.btn_grant_permission)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentBluetoothPermissions.q(FragmentBluetoothPermissions.this, view2);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.PAIRING_PERMISSION_INFO_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void p(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String value = CleverTapConstants.CustomEventProperties.ONBOARD_PERMISSION_BLUETOOTH.getValue();
        CleverTapConstants.CustomEventValues customEventValues = CleverTapConstants.CustomEventValues.YES;
        hashMap.put(value, customEventValues.getValue());
        hashMap.put(CleverTapConstants.CustomEventProperties.ONBOARD_PERMISSION_LOCATION.getValue(), customEventValues.getValue());
        hashMap.put(CleverTapConstants.CustomEventProperties.ONBOARD_PERMISSION_NOTIFICATIONS.getValue(), str);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_APP_PERMISSION_GRANTED.getValue(), hashMap);
    }

    public final void r() {
        if (this.listener != null) {
            getListener().addManually(true);
        }
    }

    public final void s() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.enable_location_services);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enable_location_services)");
        String string2 = getString(R.string.enable_location_access_in_the_next_screen_for_the_app_to_find_your_band);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.enabl…he_app_to_find_your_band)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentBluetoothPermissions.t(FragmentBluetoothPermissions.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void setEnableBluetoothDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.p = bottomSheetDialogTwoButtons;
    }

    public final void setGranted(boolean z) {
        this.q = z;
    }

    public final void setListener(@NotNull OnPairBluetoothPermissionInteractionListener onPairBluetoothPermissionInteractionListener) {
        Intrinsics.checkNotNullParameter(onPairBluetoothPermissionInteractionListener, "<set-?>");
        this.listener = onPairBluetoothPermissionInteractionListener;
    }

    public final void showOpenBluetoothSettingsDialog() {
        if (this.p == null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            String string = getString(R.string.bluetooth_off);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
            String string2 = getString(R.string.bluetooth_off_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(activity, string, string2, false, 8, null);
            this.p = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.proceed);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.proceed)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentBluetoothPermissions.u(FragmentBluetoothPermissions.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.p;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentBluetoothPermissions.v(FragmentBluetoothPermissions.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
    }

    public final void w() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …on_required\n            )");
        String string2 = getString(R.string.enable_location_access_in_the_next_screen_for_the_app_to_find_your_band);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …d_your_band\n            )");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentBluetoothPermissions.x(FragmentBluetoothPermissions.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }
}
