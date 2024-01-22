package com.coveiot.android.leonardo.more.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.sportsnotification.SoccerSportsServiceNew;
import com.coveiot.android.sportsnotification.SportsService;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.datasync.domainlogic.SyncSessionManager;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentWatchStatus extends BaseFragment implements IBT3ConnectionChangeListener, Observer<ConnectionStatus> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public BT3CallViewModel bT3CallViewModel;
    @Nullable
    public BottomSheetDialogTwoButtons m;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public Boolean n = Boolean.FALSE;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentWatchStatus newInstance$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.newInstance(z);
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchStatus newInstance(boolean z) {
            FragmentWatchStatus fragmentWatchStatus = new FragmentWatchStatus();
            Bundle bundle = new Bundle();
            bundle.putBoolean("shouldHideTitle", z);
            fragmentWatchStatus.setArguments(bundle);
            return fragmentWatchStatus;
        }
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionStatus.values().length];
            try {
                iArr[ConnectionStatus.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConnectionStatus.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConnectionStatus.SCANNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ConnectionStatus.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchStatus newInstance(boolean z) {
        return Companion.newInstance(z);
    }

    public static final void q(final FragmentWatchStatus this$0, View view) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this$0.m;
        if (bottomSheetDialogTwoButtons2 != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons2 == null || !bottomSheetDialogTwoButtons2.isShowing()) {
                z = false;
            }
            if (z && (bottomSheetDialogTwoButtons = this$0.m) != null) {
                bottomSheetDialogTwoButtons.dismiss();
            }
            this$0.m = null;
        }
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = this$0.getString(R.string.disconnect_band);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.disconnect_band)");
        String string2 = this$0.getString(R.string.disconnect_band_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.disconnect_band_msg)");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        this$0.m = bottomSheetDialogTwoButtons3;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        String string3 = this$0.getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
        bottomSheetDialogTwoButtons3.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.r0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWatchStatus.r(FragmentWatchStatus.this, view2);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this$0.m;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        String string4 = this$0.getString(R.string.f4085no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogTwoButtons4.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.q0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWatchStatus.s(FragmentWatchStatus.this, view2);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this$0.m;
        if (bottomSheetDialogTwoButtons5 != null) {
            bottomSheetDialogTwoButtons5.show();
        }
    }

    public static final void r(FragmentWatchStatus this$0, View view) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isIDODevice(requireContext)) {
            this$0.t();
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.m;
            if (bottomSheetDialogTwoButtons != null) {
                bottomSheetDialogTwoButtons.dismiss();
                return;
            }
            return;
        }
        BleApi bleApi = BleApiManager.getInstance(this$0.requireContext()).getBleApi();
        boolean z = true;
        if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isKahaBTCallSupported()) {
            z = false;
        }
        if (!z) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!companion.isTouchELXDevice(requireContext2)) {
                this$0.p();
                return;
            }
        }
        this$0.getBT3CallViewModel().disconnectToBT3Watch();
    }

    public static final void s(FragmentWatchStatus this$0, View view) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DEVICE_DISCONNECT_BAND_POPUP.getValue());
        analyticsLog.setParentScreenName(FirebaseEventParams.ScreenName.SETTINGS_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_SETTINGS_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NO_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this$0.m;
        if (bottomSheetDialogTwoButtons2 != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons2 == null || !bottomSheetDialogTwoButtons2.isShowing()) {
                z = false;
            }
            if (!z || (bottomSheetDialogTwoButtons = this$0.m) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public static final void u(FragmentWatchStatus this$0, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        this$0.p();
        dialog.dismiss();
    }

    public static final void v(Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
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

    @NotNull
    public final BT3CallViewModel getBT3CallViewModel() {
        BT3CallViewModel bT3CallViewModel = this.bT3CallViewModel;
        if (bT3CallViewModel != null) {
            return bT3CallViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bT3CallViewModel");
        return null;
    }

    @Nullable
    public final Boolean getShouldHideTitle() {
        return this.n;
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Connecting() {
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3ConnectionFailure(@Nullable String str) {
        if (requireActivity().isFinishing()) {
            return;
        }
        p();
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onBT3Disconnected() {
        if (requireActivity().isFinishing()) {
            return;
        }
        p();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.n = arguments != null ? Boolean.valueOf(arguments.getBoolean("shouldHideTitle")) : null;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_status, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener
    public void onInitialCheckFailed(@Nullable String str) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Boolean bool = this.n;
        Intrinsics.checkNotNull(bool);
        bool.booleanValue();
        ((TextView) _$_findCachedViewById(R.id.your_device_tv)).setVisibility(8);
        DeviceModelBean deviceModelBean = SessionManager.getInstance(requireContext()).getDeviceModelBean();
        if (deviceModelBean != null) {
            ((TextView) _$_findCachedViewById(R.id.device_name)).setText(deviceModelBean.getName());
            ((ImageView) _$_findCachedViewById(R.id.watch_icon)).setImageResource(DeviceUtils.Companion.getWatchModelImagePref(deviceModelBean));
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        setBT3CallViewModel((BT3CallViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(BT3CallViewModel.class));
        getBT3CallViewModel().setBT3ConnectionChangeListener(this);
        BleApiManager.getInstance(requireContext()).getBleApi().registerConnectionStatus().observe(getViewLifecycleOwner(), this);
        ((TextView) _$_findCachedViewById(R.id.disconnect)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWatchStatus.q(FragmentWatchStatus.this, view2);
            }
        });
    }

    public final void p() {
        SyncManager.getInstance().clearBleSyncUtilsInstance();
        FitnessChallengeSessionManager.getInstance(requireContext()).saveFitnessChallengeLastSyncDate(null);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DEVICE_DISCONNECT_BAND_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.YES_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        SessionManager.getInstance(requireContext()).saveSoftwareUpdateResponseString("");
        UserDataManager.getInstance(requireContext()).saveLastUpdateWeatherTimeStamp(0L);
        UserDataManager.getInstance(getContext()).saveSyncedContacts(null);
        if (BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    DeviceScanManager.getInstance(requireContext()).deassociateWithBle(requireActivity(), BleApiManager.getInstance(requireContext()).getBleApi().getMacAddress());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            showProgress(true);
            BleApiManager.getInstance(requireContext()).getBleApi().disconnect(new ConnectionResultListener() { // from class: com.coveiot.android.leonardo.more.fragments.FragmentWatchStatus$disconnect$1
                /* JADX WARN: Code restructure failed: missing block: B:18:0x0081, code lost:
                    r6 = r5.h.m;
                 */
                @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onConnectionResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.models.ConnectionStatus r6) {
                    /*
                        Method dump skipped, instructions count: 349
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.fragments.FragmentWatchStatus$disconnect$1.onConnectionResponse(com.coveiot.android.bleabstract.models.ConnectionStatus):void");
                }

                @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
                public void onError(@NotNull Error error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    AnalyticsLog analyticsLog2 = new AnalyticsLog();
                    analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                    analyticsLog2.setCommandName(FirebaseEventParams.Description.CONNECT.getValue());
                    analyticsLog2.setCommandStatus(FirebaseEventParams.Description.ERROR.getValue());
                    analyticsLog2.setDeviceConnectionStatus(FirebaseEventParams.Description.CONNECTED.getValue());
                    analyticsLog2.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                    FragmentWatchStatus.this.dismissProgress();
                }
            });
        } else {
            SessionManager sessionManager = SessionManager.getInstance(requireContext());
            sessionManager.setIsDevicePaired(false);
            BleApiManager.getInstance(requireContext()).getBleApi().stopService();
            sessionManager.setOnBoardingComplete(false);
            UserDataManager.getInstance(requireContext()).remove(requireContext(), UserDataManager.IS_ONK_SUPPORTED);
            Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.getInstance(requireContext).clearPreferences();
            sessionManager.setExistingUserFirstTime(true);
            SyncSessionManager.getInstance(requireContext()).setExistingUserFirstTime(true);
            requireActivity().finish();
            PreferenceManagerAbstract.getInstance(requireContext()).saveDeviceVersionNumber(null);
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            companion2.navigateToBluetoothScanActivity(requireContext2);
            SessionManager.getInstance(requireContext()).setAnalyticsUserPropertiesUpdated(false);
        }
        requireContext().stopService(new Intent(requireContext(), SportsService.class));
        requireContext().stopService(new Intent(requireContext(), SoccerSportsServiceNew.class));
    }

    public final void setBT3CallViewModel(@NotNull BT3CallViewModel bT3CallViewModel) {
        Intrinsics.checkNotNullParameter(bT3CallViewModel, "<set-?>");
        this.bT3CallViewModel = bT3CallViewModel;
    }

    public final void setShouldHideTitle(@Nullable Boolean bool) {
        this.n = bool;
    }

    public final void t() {
        final Dialog dialog = new Dialog(requireContext(), R.style.DialogTheme);
        dialog.setContentView(R.layout.pair_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ((Button) dialog.findViewById(R.id.btn_okay)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.s0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchStatus.u(FragmentWatchStatus.this, dialog, view);
            }
        });
        ((ImageView) dialog.findViewById(R.id.close_icon)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchStatus.v(dialog, view);
            }
        });
        dialog.show();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@NotNull ConnectionStatus t) {
        Intrinsics.checkNotNullParameter(t, "t");
        int i = WhenMappings.$EnumSwitchMapping$0[t.ordinal()];
        if (i == 1) {
            int i2 = R.id.tv_connection_status;
            ((TextView) _$_findCachedViewById(i2)).setText(R.string.connected);
            ((TextView) _$_findCachedViewById(i2)).setTextColor(requireContext().getColor(R.color.color_67c465));
        } else if (i == 2) {
            int i3 = R.id.tv_connection_status;
            ((TextView) _$_findCachedViewById(i3)).setText(R.string.connecting);
            ((TextView) _$_findCachedViewById(i3)).setTextColor(requireContext().getColor(R.color.color_67c465));
        } else if (i == 3) {
            int i4 = R.id.tv_connection_status;
            ((TextView) _$_findCachedViewById(i4)).setText(R.string.scanning);
            ((TextView) _$_findCachedViewById(i4)).setTextColor(requireContext().getColor(R.color.color_ccededed));
        } else if (i != 4) {
        } else {
            int i5 = R.id.tv_connection_status;
            ((TextView) _$_findCachedViewById(i5)).setText(R.string.disconnected);
            ((TextView) _$_findCachedViewById(i5)).setTextColor(requireContext().getColor(R.color.color_ccededed));
        }
    }
}
