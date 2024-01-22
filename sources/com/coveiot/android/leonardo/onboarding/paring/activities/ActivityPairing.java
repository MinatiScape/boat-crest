package com.coveiot.android.leonardo.onboarding.paring.activities;

import android.annotation.SuppressLint;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.bestmafen.baseble.connector.AbsBleConnector;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.PushPermissionManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.PairingFlowRequest;
import com.coveiot.android.bleabstract.request.ProbeDataRequest;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.request.SetDeviceParametersRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetTemperatureUnitRequest;
import com.coveiot.android.bleabstract.request.SetWearingHandRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.more.AppListHelper;
import com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentBluetoothPermissions;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDevciePairing;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceListing;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDevicePaired;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDevicePairingS2Style;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDeviceSelection;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentPairingFailed;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentQRScanDevice;
import com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentScanningDevice;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.AppSessionManager;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.coveaccess.device.DeviceInfoManager;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.device.model.IOTUserDeviceReq;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FirmwareCapabilityData;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.repository.datasync.domainlogic.SyncSessionManager;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.sdk.ble.model.FirmwareCapabilityInfo;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.szabh.smable3.component.BleConnector;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityPairing extends BaseActivity implements ConnectionResultListener, ViewModelListener, FragmentBluetoothPermissions.OnPairBluetoothPermissionInteractionListener, FragmentScanningDevice.OnPairScanningFragmentInteractionListener {
    @Nullable
    public BottomSheetDialogTwoButtons C;
    public final long F;
    public boolean G;
    @Nullable
    public List<String> H;
    public ActivityPairingViewModel activityPairingViewModel;
    public BT3CallViewModel bt3CallViewModel;
    public boolean p;
    public FragmentStepsGoalViewModel q;
    @Nullable
    public String r;
    public boolean s;
    public BleDevice t;
    public long y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final long u = 3000;
    public final int v = 1003;
    public final int w = 1004;
    public final int x = 1005;
    public final int z = 202;
    public final int A = 113;
    public final int B = 12345;
    @NotNull
    public final String D = "ActivityPairing";
    public int E = 1;

    @DebugMetadata(c = "com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectedDeviceInfoToServer$1", f = "ActivityPairing.kt", i = {}, l = {2207}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (WorkoutSessionRepository.Companion.getInstance(ActivityPairing.this).deleteWorkoutSession(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public ActivityPairing() {
        ParcelUuid.fromString("00000001-0000-1000-8000-00805f9b34fb");
        this.F = Constants.ONE_MIN_IN_MILLIS;
    }

    public static final void F(ActivityPairing this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleDevice bleDevice = this$0.t;
        if (bleDevice == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSelectedBleDevice");
            bleDevice = null;
        }
        this$0.A(bleDevice);
    }

    public static final void G(ActivityPairing this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.toolbar;
        View findViewById = this$0._$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById, "toolbar.findViewById<Tex…(R.id.toolbar_back_arrow)");
        boolean z = true;
        findViewById.setVisibility((this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FragmentBluetoothPermissions) ^ true ? 0 : 8);
        View toolbar = this$0._$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(toolbar, "toolbar");
        if ((this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FragmentDevicePaired) || (this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof FragmentPairingFailed)) {
            z = false;
        }
        toolbar.setVisibility(z ? 0 : 8);
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.g
            @Override // java.lang.Runnable
            public final void run() {
                ActivityPairing.H();
            }
        }, 500L);
    }

    public static final void H() {
    }

    public static final void R(ActivityPairing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.loadDeviceScanningFragment();
    }

    public static final void S(BottomSheetDialogOneButtonTitleMessage dialog, ActivityPairing this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.startActivityForResult(intent, this$0.x);
    }

    public static final void T(ActivityPairing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openBluetoothSettings(this$0);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.C;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void U(ActivityPairing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.C;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static /* synthetic */ void connectTo$default(ActivityPairing activityPairing, BleDevice bleDevice, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        activityPairing.connectTo(bleDevice, str);
    }

    public static /* synthetic */ void sendNotificationConfigurationToBand$default(ActivityPairing activityPairing, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        activityPairing.sendNotificationConfigurationToBand(z);
    }

    public final void A(BleDevice bleDevice) {
        new PreferenceManager(this).setConnectedDeviceMacAddress(BleApiManager.getInstance(this).getBleApi().getMacAddress());
        BleApiManager.getInstance(this).getBleApi().clearCommandQueue();
        SessionManager sessionManager = SessionManager.getInstance(this);
        String address = bleDevice.getmDevice().getAddress();
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            String name = bleDevice.getmDevice().getName();
            String name2 = name == null || name.length() == 0 ? this.r : bleDevice.getmDevice().getName();
            sessionManager.saveConnectedDeviceMAcAddress(address);
            sessionManager.saveConnectedDeviceName(name2);
            this.s = false;
            DeviceInfoRequest deviceInfoRequest = new DeviceInfoRequest();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isJstyleDevice(this)) {
                deviceInfoRequest = new DeviceInfoRequest.Builder().setIsModelNo(false).setIsFwVersion(true).setIsSerialNo(false).setIsHwVersion(false).setIsManufacturer(false).setIsDeviceName(false).setIsMacAddress(true).build();
            } else if (companion.isSmaDevice(this)) {
                deviceInfoRequest = new DeviceInfoRequest.Builder().setIsFwVersion(true).setIsSerialNo(true).setIsModelNo(true).setIsMacAddress(true).build();
            } else if (companion.isMoyangDevice(this)) {
                deviceInfoRequest = new DeviceInfoRequest.Builder().setIsModelNo(false).setIsFwVersion(true).setIsSerialNo(false).setIsHwVersion(false).setIsManufacturer(false).setIsDeviceName(false).setIsMacAddress(true).build();
            } else if (companion.isMatrixDevice(this)) {
                deviceInfoRequest = new DeviceInfoRequest.Builder().setIsModelNo(false).setIsFwVersion(false).setIsSerialNo(false).setIsHwVersion(false).setIsManufacturer(false).setIsDeviceName(false).setIsMacAddress(true).build();
            } else if (companion.isIDODevice(this)) {
                deviceInfoRequest = new DeviceInfoRequest.Builder().setIsModelNo(true).setIsFwVersion(true).setIsSerialNo(true).setIsHwVersion(true).setIsManufacturer(true).setIsDeviceName(true).setIsMacAddress(true).build();
            } else if (!kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.v2_device), false) && !SessionManager.getInstance(this).getDeviceType().equals(getString(R.string.v7_device)) && !companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this) && !kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smart_t), false)) {
                if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.C11G), false)) {
                    deviceInfoRequest = new DeviceInfoRequest.Builder().setIsModelNo(false).setIsFwVersion(true).setIsSerialNo(false).setIsHwVersion(false).setIsManufacturer(false).setIsDeviceName(false).setIsMacAddress(true).build();
                } else if (companion.isTouchELXDevice(this)) {
                    deviceInfoRequest = new DeviceInfoRequest.Builder().setIsModelNo(false).setIsFwVersion(true).setIsSerialNo(true).setIsHwVersion(true).setIsManufacturer(false).setIsDeviceName(false).setIsMacAddress(true).build();
                } else if (companion.isEastApexDevice(this)) {
                    deviceInfoRequest = new DeviceInfoRequest.Builder().setIsModelNo(false).setIsFwVersion(true).setIsSerialNo(true).setIsHwVersion(true).setIsManufacturer(false).setIsDeviceName(false).setIsMacAddress(true).build();
                }
            } else {
                deviceInfoRequest = new DeviceInfoRequest.Builder().setIsModelNo(true).setIsFwVersion(true).setIsSerialNo(true).setIsHwVersion(true).setIsManufacturer(true).setIsDeviceName(true).setIsMacAddress(true).build();
            }
            BleApiManager.getInstance(this).getBleApi().getData(deviceInfoRequest, new ActivityPairing$getDeviceInfo$1(this, address, name2));
        }
    }

    public final void B() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$getLocationPermissions$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityPairing activityPairing = ActivityPairing.this;
                ActivityCompat.requestPermissions(activityPairing, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, activityPairing.getLOCATION_PERMISSION_REQUEST_CODE());
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityPairing activityPairing = ActivityPairing.this;
                String string = activityPairing.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityPairing.V(string);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityPairing.this.loadDeviceSelectionFragment();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityPairing activityPairing = ActivityPairing.this;
                String string = activityPairing.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityPairing.V(string);
            }
        });
    }

    public final void C() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        ConstraintLayout clTop = (ConstraintLayout) _$_findCachedViewById(R.id.clTop);
        Intrinsics.checkNotNullExpressionValue(clTop, "clTop");
        gone(clTop);
        ConstraintLayout clProgressBar = (ConstraintLayout) _$_findCachedViewById(R.id.clProgressBar);
        Intrinsics.checkNotNullExpressionValue(clProgressBar, "clProgressBar");
        gone(clProgressBar);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentDevicePaired.Companion companion = FragmentDevicePaired.Companion;
        beginTransaction.replace(R.id.fragment_container, companion.newInstance("", "")).addToBackStack(companion.toString()).commitAllowingStateLoss();
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.coveiot.android.boat.GenericBandApplication");
        ((GenericBandApplication) application).registerConnectionListener();
        _$_findCachedViewById(R.id.toolbar).setVisibility(8);
    }

    public final void D() {
        if (isFinishing() || isDestroyed() || this.G) {
            return;
        }
        ConstraintLayout clTop = (ConstraintLayout) _$_findCachedViewById(R.id.clTop);
        Intrinsics.checkNotNullExpressionValue(clTop, "clTop");
        gone(clTop);
        ConstraintLayout clProgressBar = (ConstraintLayout) _$_findCachedViewById(R.id.clProgressBar);
        Intrinsics.checkNotNullExpressionValue(clProgressBar, "clProgressBar");
        gone(clProgressBar);
        this.G = true;
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (findFragmentById == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            FragmentPairingFailed.Companion companion = FragmentPairingFailed.Companion;
            beginTransaction.replace(R.id.fragment_container, companion.newInstance("", "")).addToBackStack(companion.toString()).commitAllowingStateLoss();
        } else if (!(findFragmentById instanceof FragmentPairingFailed)) {
            FragmentTransaction beginTransaction2 = getSupportFragmentManager().beginTransaction();
            FragmentPairingFailed.Companion companion2 = FragmentPairingFailed.Companion;
            beginTransaction2.replace(R.id.fragment_container, companion2.newInstance("", "")).addToBackStack(companion2.toString()).commitAllowingStateLoss();
        }
        _$_findCachedViewById(R.id.toolbar).setVisibility(8);
    }

    public final void E() {
        Fragment newInstance;
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (DeviceUtils.Companion.isSmaDevice(this)) {
            newInstance = FragmentDevicePairingS2Style.Companion.newInstance("", "");
        } else {
            newInstance = FragmentDevciePairing.Companion.newInstance("", "");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance).addToBackStack(FragmentDevciePairing.Companion.toString()).commitAllowingStateLoss();
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.onboarding_progressBar);
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        String string = getString(R.string.connecting);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connecting)");
        Q(string);
    }

    public final void I(boolean z) {
        if (!z && DeviceUtils.Companion.isIDODevice(this)) {
            if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.ido_select), false)) {
                SessionManager.getInstance(this).saveDeviceType(getResources().getString(R.string.ido_select));
                SessionManager.getInstance(this).setSelectedDeviceTypePhoneOnly(false);
                BleApiManager.getInstance(this).init(DeviceType.IDO_SELECT);
                SessionManager.getInstance(this).setScanAllDevice(false);
                ArrayList arrayList = new ArrayList();
                arrayList.add(AppConstants.SCAN_FILTER_DEVICE_IDO_WAVE_SELECT.getValue());
                arrayList.add(AppConstants.SCAN_FILTER_DEVICE_IDO_SELECT.getValue());
                loadDeviceListingFragment(arrayList);
                return;
            } else if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.ido_connect), false)) {
                SessionManager.getInstance(this).saveDeviceType(getResources().getString(R.string.ido_connect));
                SessionManager.getInstance(this).setSelectedDeviceTypePhoneOnly(false);
                BleApiManager.getInstance(this).init(DeviceType.IDO_CONNECT);
                SessionManager.getInstance(this).setScanAllDevice(false);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(AppConstants.SCAN_FILTER_DEVICE_IDO_WAVE_CONNECT.getValue());
                arrayList2.add(AppConstants.SCAN_FILTER_DEVICE_IDO_CONNECT.getValue());
                loadDeviceListingFragment(arrayList2);
                return;
            } else {
                return;
            }
        }
        loadDeviceSelectionFragment();
    }

    public final void J() {
        SessionManager.getInstance(this).saveFirmwareCapability(BleApiManager.getInstance(this).getBleApi().getMacAddress(), new FirmwareCapabilityData());
        PreferenceManagerAbstract.getInstance(this).saveFirmwareCapability(BleApiManager.getInstance(this).getBleApi().getMacAddress(), new com.coveiot.android.bleabstract.models.FirmwareCapabilityData());
        FirmwareCapabilityInfo firmwareCapabilityInfo = new FirmwareCapabilityInfo();
        StringBuilder sb = new StringBuilder();
        String lowerCase = CommonPreference.FIRMWARE_CAPABILITY.name().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        sb.append(lowerCase);
        sb.append(BleApiManager.getInstance(this).getBleApi().getMacAddress());
        BlePreferenceManager.saveString(this, sb.toString(), new Gson().toJson(firmwareCapabilityInfo));
    }

    public final void K(BleDeviceInfo bleDeviceInfo) {
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isSportsModeHistorySupported()) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isIDODevice(this) || !companion.isTouchELXDevice(this) || !companion.isEastApexDevice(this)) {
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
            }
        }
        IOTUserDeviceReq iOTUserDeviceReq = new IOTUserDeviceReq(bleDeviceInfo);
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported()) {
            String str = bleDeviceInfo.getmDeviceName();
            Intrinsics.checkNotNullExpressionValue(str, "bleDeviceInfo.getmDeviceName()");
            String lowerCase = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            iOTUserDeviceReq.setAppTrackerId(lowerCase);
        } else {
            BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isAppSocialDistanceFeatureSupported();
        }
        iOTUserDeviceReq.setRegisterInDvcMgmt(Boolean.TRUE);
        String str2 = this.D;
        LogHelper.d(str2, "Sending device info to server :" + new Gson().toJson(iOTUserDeviceReq));
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new ActivityPairing$sendConnectedDeviceInfoToServer$2(iOTUserDeviceReq, this, bleDeviceInfo));
    }

    public final void L() {
        if (BleApiManager.getInstance(this).getDeviceType() == DeviceType.CZ0) {
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new ActivityPairing$sendConnectionSuccessVibration$1(this, null), 2, null);
        } else if (BleApiManager.getInstance(this).getDeviceType() != DeviceType.CZ3 && BleApiManager.getInstance(this).getDeviceType() != DeviceType.wavePrime && BleApiManager.getInstance(this).getDeviceType() != DeviceType.WAVE_ELITE) {
            if (BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA3 && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA0 && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA3_BT_CALL) {
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                if (!companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this) && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA2 && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA3_WAVE_COSMOS && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA3_BT_STORM_PRO_CALL && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA3_BT_WAVE_COSMOS_PRO && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA5_WAVE_STYLE && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA5_WAVE_PLAY && BleApiManager.getInstance(this).getDeviceType() != DeviceType.CA5_WAVE_BEAT && BleApiManager.getInstance(this).getDeviceType() != DeviceType.ULC3_WAVE_SMART && BleApiManager.getInstance(this).getDeviceType() != DeviceType.ULC2_WAVE_BEAT_PLUS && BleApiManager.getInstance(this).getDeviceType() != DeviceType.ULC2_WAVE_STYLE_PLUS && BleApiManager.getInstance(this).getDeviceType() != DeviceType.ULC2_WAVE_SMART_PLUS && BleApiManager.getInstance(this).getDeviceType() != DeviceType.ULC2_WAVE_LYNC && BleApiManager.getInstance(this).getDeviceType() != DeviceType.ULC5_ULTIMA_CALL && BleApiManager.getInstance(this).getDeviceType() != DeviceType.ULTIMA_RISE && BleApiManager.getInstance(this).getDeviceType() != DeviceType.ULC5_ULTIMA_CONNECT && BleApiManager.getInstance(this).getDeviceType() != DeviceType.WAVE_REGAL && BleApiManager.getInstance(this).getDeviceType() != DeviceType.WAVE_CHASE && !BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isPairingFlowTypeCommandSupported()) {
                    return;
                }
            }
            BleApiManager.getInstance(this).getBleApi().setUserSettings(new PairingFlowRequest(1), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectionSuccessVibration$3
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                }
            });
        } else {
            BleApiManager.getInstance(this).getBleApi().setUserSettings(new PairingFlowRequest(1), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendConnectionSuccessVibration$2
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                }
            });
        }
    }

    public final void M() {
        BleApiManager.getInstance(this).getBleApi().setUserSettings(new ProbeDataRequest.Builder().setInterval(UserDataManager.getInstance(this).getProbeInterval()).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendProbeCommand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                ActivityPairing.sendNotificationConfigurationToBand$default(ActivityPairing.this, false, 1, null);
            }
        });
    }

    public final void N(final BleDeviceInfo bleDeviceInfo) {
        BleApiManager.getInstance(this).getBleApi().setUserSettings(new TemperatureTimeIntervalRequest.Builder(UserDataManager.getInstance(this).getAutoTemperatureInterval()).setOpen(UserDataManager.getInstance(this).getAutoTemperatureEnabled() ? 1 : 0).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$setAutoTemperature$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ActivityPairing.this.sendCommandNextToUserSetting(bleDeviceInfo);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                ActivityPairing.this.sendCommandNextToUserSetting(bleDeviceInfo);
            }
        });
    }

    public final void O(int i, final BleDeviceInfo bleDeviceInfo) {
        BleApiManager.getInstance(this).getBleApi().setUserSettings(new TemperatureTimeIntervalRequest.Builder(i).setOpen(1).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$setAutoTemperatureIntervalToKaHaDevice$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.e(ActivityPairing.this.getTAG(), "Auto Temperature setting failed");
                ActivityPairing.this.sendCommandNextToUserSetting(bleDeviceInfo);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                ActivityPairing.this.sendCommandNextToUserSetting(bleDeviceInfo);
            }
        });
    }

    public final void P(final BleDeviceInfo bleDeviceInfo) {
        SetLiftWristRequest.Builder builder;
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            final Boolean isUnitInMile = UserDataManager.getInstance(this).isDistanceUnitInMile();
            final Boolean isRightHandSelected = UserDataManager.getInstance(this).isRightHandSelected();
            final Boolean hourPref = UserDataManager.getInstance(this).isTimeIn12HourFormat();
            final Boolean isLiftWristEnabled = UserDataManager.getInstance(this).isLiftWristToViewEnable();
            final Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this).isTemperatureUnitInFahrenheit();
            SetDeviceParametersRequest.Builder handState = new SetDeviceParametersRequest.Builder().setHandState(!isRightHandSelected.booleanValue());
            Intrinsics.checkNotNullExpressionValue(hourPref, "hourPref");
            SetDeviceParametersRequest.Builder hourFormat = handState.setHourFormat(hourPref.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isUnitInMile, "isUnitInMile");
            SetDeviceParametersRequest.Builder temperatureUnitInCelsius = hourFormat.setDistanceUnit(isUnitInMile.booleanValue()).setTemperatureUnitInCelsius(!isTemperatureUnitInFahrenheit.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            SetDeviceParametersRequest build = temperatureUnitInCelsius.setLiftWristEnable(isLiftWristEnabled.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…LiftWristEnabled).build()");
            BleApiManager.getInstance(this).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$setDeviceParameters$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    ActivityPairing.this.K(bleDeviceInfo);
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    UserDataManager.getInstance(ActivityPairing.this).saveDistanceUnitPref(isUnitInMile);
                    UserDataManager.getInstance(ActivityPairing.this).saveHourFormatPref(hourPref);
                    UserDataManager.getInstance(ActivityPairing.this).saveHandPref(isRightHandSelected);
                    UserDataManager.getInstance(ActivityPairing.this).saveLiftWristPref(isLiftWristEnabled);
                    UserDataManager.getInstance(ActivityPairing.this).saveTemperatureUnitPref(isTemperatureUnitInFahrenheit);
                    ActivityPairing.this.K(bleDeviceInfo);
                }
            });
            return;
        }
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isHandPreferenceSettingsSupported()) {
            Boolean handPref = UserDataManager.getInstance(this).isRightHandSelected();
            Intrinsics.checkNotNullExpressionValue(handPref, "handPref");
            SetWearingHandRequest build2 = new SetWearingHandRequest.Builder(handPref.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder(handPref).build()");
            BleApiManager.getInstance(this).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$setDeviceParameters$2
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairing.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairing.this.getTAG(), response.toString());
                }
            });
        }
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isDistanceUnitSettingsSupported()) {
            Boolean isUnitInMile2 = UserDataManager.getInstance(this).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isUnitInMile2, "isUnitInMile");
            SetDistanceUnitRequest build3 = new SetDistanceUnitRequest.Builder(isUnitInMile2.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build3, "Builder(isUnitInMile).build()");
            BleApiManager.getInstance(this).getBleApi().setUserSettings(build3, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$setDeviceParameters$3
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairing.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairing.this.getTAG(), response.toString());
                }
            });
        }
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isTemperatureUnitSettingsSupported()) {
            SetTemperatureUnitRequest build4 = new SetTemperatureUnitRequest.Builder(!UserDataManager.getInstance(this).isTemperatureUnitInFahrenheit().booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build4, "Builder(!isTemperatureinFahrenheit).build()");
            BleApiManager.getInstance(this).getBleApi().setUserSettings(build4, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$setDeviceParameters$4
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairing.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairing.this.getTAG(), response.toString());
                }
            });
        }
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isLiftWristToViewSettingsSupported()) {
            Boolean isLiftWristToViewOn = UserDataManager.getInstance(this).isLiftWristToViewEnable();
            if (BleApiManager.getInstance(this).getBleApi() != null && BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isScheduledLiftWristViewSettingsSupported()) {
                Intrinsics.checkNotNullExpressionValue(isLiftWristToViewOn, "isLiftWristToViewOn");
                builder = new SetLiftWristRequest.Builder(isLiftWristToViewOn.booleanValue());
                builder.setStartHour(UserDataManager.getInstance(this).getLiftWristToViewStartHour());
                builder.setStartMinute(UserDataManager.getInstance(this).getLiftWristToViewStartMin());
                builder.setEndHour(UserDataManager.getInstance(this).getLiftWristToViewEndHour());
                builder.setEndMinute(UserDataManager.getInstance(this).getLiftWristToViewEndMin());
            } else {
                Intrinsics.checkNotNullExpressionValue(isLiftWristToViewOn, "isLiftWristToViewOn");
                builder = new SetLiftWristRequest.Builder(isLiftWristToViewOn.booleanValue());
            }
            SetLiftWristRequest build5 = builder.build();
            Intrinsics.checkNotNullExpressionValue(build5, "builder.build()");
            BleApiManager.getInstance(this).getBleApi().setUserSettings(build5, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$setDeviceParameters$5
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairing.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairing.this.getTAG(), response.toString());
                }
            });
        }
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isTimeFormatSettingsSupported()) {
            Boolean isTimeIn12HourFormat = UserDataManager.getInstance(this).isTimeIn12HourFormat();
            Intrinsics.checkNotNullExpressionValue(isTimeIn12HourFormat, "isTimeIn12HourFormat");
            SetHourFormatRequest build6 = new SetHourFormatRequest.Builder(isTimeIn12HourFormat.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build6, "Builder(isTimeIn12HourFormat).build()");
            BleApiManager.getInstance(this).getBleApi().setUserSettings(build6, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$setDeviceParameters$6
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d(ActivityPairing.this.getTAG(), error.toString());
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d(ActivityPairing.this.getTAG(), response.toString());
                }
            });
        }
        K(bleDeviceInfo);
    }

    public final void Q(String str) {
        int i = R.id.toolbar;
        _$_findCachedViewById(i).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(str);
        if (str.equals(getResources().getString(R.string.select_device)) && DeviceUtils.Companion.isIDODevice(this)) {
            ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setImageDrawable(getResources().getDrawable(R.drawable.ic_qrcode_image));
            ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityPairing.R(ActivityPairing.this, view);
                }
            });
            return;
        }
        ((ImageView) _$_findCachedViewById(i).findViewById(R.id.share_iv)).setVisibility(4);
    }

    public final void V(String str) {
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, str);
        String string = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$showPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                AppUtils.openAppSettings(this, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void W() {
        ProfileData userDetails = SessionManager.getInstance(this).getUserDetails();
        String height = userDetails.getHeight();
        Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
        int parseInt = Integer.parseInt(height);
        String weight = userDetails.getWeight();
        Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
        SetFitnessInfoRequest.Builder builder = new SetFitnessInfoRequest.Builder(parseInt, Double.parseDouble(weight));
        builder.setStride(ProfileRepository.getInstance().getLatestProfileData(this).walkStrideLength);
        builder.setRunStride(ProfileRepository.getInstance().getLatestProfileData(this).runStrideLength);
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(this).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
        builder.setUnitType(isDistanceUnitInMile.booleanValue() ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC);
        String dob = userDetails.getDob();
        boolean z = false;
        if (!(dob == null || dob.length() == 0)) {
            builder.setAge(AppUtils.getAge(userDetails.getDob()));
        }
        String gender = userDetails.getGender();
        if (gender == null || gender.length() == 0) {
            z = true;
        }
        if (!z) {
            builder.setMale(kotlin.text.m.equals(userDetails.getGender(), AppConstants.MALE.getValue(), true));
        }
        SetFitnessInfoRequest builder2 = builder.builder();
        Intrinsics.checkNotNullExpressionValue(builder2, "fitnessInfoRequestBuilder.builder()");
        BleApiManager.getInstance(this).getBleApi().setUserSettings(builder2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$updateHeightAndWeightToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityPairing.this.getTAG(), "setFitnessInfoRequest Success");
                SessionManager.getInstance(ActivityPairing.this).setIsPairDeviceAfterLogin(false);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentBluetoothPermissions.OnPairBluetoothPermissionInteractionListener, com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentScanningDevice.OnPairScanningFragmentInteractionListener
    public void addManually(boolean z) {
        if (AppUtils.isBluetoothEnabled(this)) {
            if (AppUtils.isGpsEnabled(this)) {
                if (PayUtils.INSTANCE.checkIfScanOnDisConnectIsNeeded(this)) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        z();
                        return;
                    } else {
                        B();
                        return;
                    }
                }
                I(z);
                return;
            }
            Toast.makeText(this, getString(R.string.please_check_gps), 1).show();
            return;
        }
        Toast.makeText(this, getString(R.string.bluetooth_off_message), 1).show();
    }

    public final void checkBluetoothConnectPermission(@Nullable List<String> list) {
        this.H = list;
        if (Build.VERSION.SDK_INT < 31) {
            if (list != null) {
                loadDeviceListingFragment(list);
                return;
            }
            return;
        }
        String[] permissions = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"});
        Intrinsics.checkNotNullExpressionValue(permissions, "permissions");
        if (!(permissions.length == 0)) {
            Intrinsics.checkNotNull(this);
            ActivityCompat.requestPermissions(this, permissions, this.w);
            return;
        }
        List<String> list2 = this.H;
        if (list2 != null) {
            loadDeviceListingFragment(list2);
        }
    }

    @SuppressLint({"MissingPermission"})
    public final void connectTo(@NotNull BleDevice selectedBleDevice, @Nullable String str) {
        Intrinsics.checkNotNullParameter(selectedBleDevice, "selectedBleDevice");
        Object systemService = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        if (!((BluetoothManager) systemService).getAdapter().isEnabled()) {
            showOpenBluetoothSettingsDialog();
            return;
        }
        if (DeviceUtils.Companion.isSmaDevice(this)) {
            BleConnector bleConnector = BleConnector.INSTANCE;
            BluetoothDevice bluetoothDevice = selectedBleDevice.getmDevice();
            Intrinsics.checkNotNullExpressionValue(bluetoothDevice, "selectedBleDevice.getmDevice()");
            AbsBleConnector.setBleDevice$default(bleConnector, new com.bestmafen.baseble.scanner.BleDevice(bluetoothDevice, selectedBleDevice.getRssi(), null, "", selectedBleDevice.getmDevice().getType()), 0, 2, null);
        }
        E();
        String address = selectedBleDevice.getmDevice().getAddress();
        Intrinsics.checkNotNullExpressionValue(address, "selectedBleDevice.getmDevice().address");
        ConnectRequest connectRequest = new ConnectRequest(address);
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        if (BleApiManager.getInstance(this).getBleApi().getMacAddress() != null && Intrinsics.areEqual(BleApiManager.getInstance(this).getBleApi().getMacAddress(), selectedBleDevice.getmDevice().getAddress())) {
            booleanRef.element = true;
        }
        String str2 = this.D;
        LogHelper.d(str2, "Connect initiated for device name " + selectedBleDevice.getmDevice().getName() + ", mac Address:" + selectedBleDevice.getmDevice().getAddress());
        BleApiManager.getInstance(this).getBleApi().connect(connectRequest, new ActivityPairing$connectTo$1(str, this, selectedBleDevice, booleanRef, BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures(), connectRequest));
    }

    public final int getASSOCIATION_REQUEST_CODE() {
        return this.A;
    }

    @NotNull
    public final ActivityPairingViewModel getActivityPairingViewModel() {
        ActivityPairingViewModel activityPairingViewModel = this.activityPairingViewModel;
        if (activityPairingViewModel != null) {
            return activityPairingViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("activityPairingViewModel");
        return null;
    }

    public final int getBLUETOOTH_CONNECT_PERMISSION_REQUEST_CODE() {
        return this.w;
    }

    public final int getBackPressedCounter() {
        return this.E;
    }

    @NotNull
    public final BT3CallViewModel getBt3CallViewModel() {
        BT3CallViewModel bT3CallViewModel = this.bt3CallViewModel;
        if (bT3CallViewModel != null) {
            return bT3CallViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bt3CallViewModel");
        return null;
    }

    public final int getCONFIGURATION_REQUESTCODE() {
        return this.B;
    }

    @Nullable
    public final String getConnectedDeviceName() {
        return this.r;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getEnableBluetoothDialog() {
        return this.C;
    }

    public final int getLOCATION_PERMISSION_REQUEST_CODE() {
        return this.v;
    }

    public final int getPOST_NOTIFICATION_PERMISSION_REQUEST_CODE() {
        return this.x;
    }

    public final long getScanDuration() {
        return this.F;
    }

    @NotNull
    public final String getTAG() {
        return this.D;
    }

    public final void handleConnected(boolean z, @NotNull BleDevice selectedBleDevice) {
        Intrinsics.checkNotNullParameter(selectedBleDevice, "selectedBleDevice");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setCommandName(FirebaseEventParams.Description.CONNECT.getValue());
        analyticsLog.setCommandStatus(FirebaseEventParams.Description.OK.getValue());
        analyticsLog.setDeviceConnectionStatus(FirebaseEventParams.Description.CONNECTED.getValue());
        analyticsLog.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this.t = selectedBleDevice;
        C();
        L();
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            String name = selectedBleDevice.getmDevice().getName();
            if (z) {
                SyncSessionManager.getInstance(this).setExistingUserFirstTime(false);
            }
            SessionManager.getInstance(this).saveBleDeviceName(name);
            LogHelper.d(this.D, "Device Connected ");
        }
    }

    public final void handleDisconnected() {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setCommandName(FirebaseEventParams.Description.DISCONNECT.getValue());
        analyticsLog.setCommandStatus(FirebaseEventParams.Description.ERROR.getValue());
        analyticsLog.setDeviceConnectionStatus(FirebaseEventParams.Description.DISCONNECTED.getValue());
        analyticsLog.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this.G = false;
        D();
        LogHelper.i(this.D, "handleDisconnected Device disconnected ");
    }

    public final void handleOnError(@NotNull Error error, boolean z, @NotNull BleDevice selectedBleDevice, @NotNull ConnectRequest connectRequest) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(selectedBleDevice, "selectedBleDevice");
        Intrinsics.checkNotNullParameter(connectRequest, "connectRequest");
        try {
            String str = this.D;
            LogHelper.d(str, "Connection error" + error.getMessage());
            if (Intrinsics.areEqual(error.getMessage(), getString(R.string.service_not_running))) {
                retryConnect(z, selectedBleDevice, connectRequest);
            } else {
                logErrorToAnalytics(error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean isLoadGoalScreen() {
        return this.p;
    }

    public final boolean isPairingFailedFragmentShown() {
        return this.G;
    }

    public final void loadDeviceListingFragment(@NotNull List<String> scanFilter) {
        Intrinsics.checkNotNullParameter(scanFilter, "scanFilter");
        ConstraintLayout clTop = (ConstraintLayout) _$_findCachedViewById(R.id.clTop);
        Intrinsics.checkNotNullExpressionValue(clTop, "clTop");
        gone(clTop);
        ConstraintLayout clProgressBar = (ConstraintLayout) _$_findCachedViewById(R.id.clProgressBar);
        Intrinsics.checkNotNullExpressionValue(clProgressBar, "clProgressBar");
        gone(clProgressBar);
        if (Build.VERSION.SDK_INT >= 31) {
            if (PayUtils.INSTANCE.checkIfBluetoothPermissionExists(this)) {
                this.G = false;
                FragmentDeviceListing.Companion companion = FragmentDeviceListing.Companion;
                FragmentDeviceListing newInstance = companion.newInstance();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue(), (ArrayList) scanFilter);
                newInstance.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance).addToBackStack(companion.toString()).commitAllowingStateLoss();
                String string = getString(R.string.select_device);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_device)");
                Q(string);
                return;
            }
            z();
        } else if (AppUtils.checkIfLocationPermissionExists(this)) {
            this.G = false;
            FragmentDeviceListing.Companion companion2 = FragmentDeviceListing.Companion;
            FragmentDeviceListing newInstance2 = companion2.newInstance();
            Bundle bundle2 = new Bundle();
            bundle2.putStringArrayList(AppConstants.SCAN_FILTER_ARGUMENT_KEY.getValue(), (ArrayList) scanFilter);
            newInstance2.setArguments(bundle2);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newInstance2).addToBackStack(companion2.toString()).commitAllowingStateLoss();
            String string2 = getString(R.string.select_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.select_device)");
            Q(string2);
        } else {
            B();
        }
    }

    public final void loadDeviceScanningFragment() {
        this.G = false;
        if (isFinishing() || isDestroyed()) {
            return;
        }
        String string = getResources().getString(R.string.qr_scan);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.qr_scan)");
        Q(string);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentScanningDevice.Companion companion = FragmentScanningDevice.Companion;
        beginTransaction.replace(R.id.fragment_container, companion.newInstance("", "")).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void loadDeviceSelectionFragment() {
        if (Build.VERSION.SDK_INT >= 33) {
            PayUtils payUtils = PayUtils.INSTANCE;
            if (payUtils.checkIfBluetoothPermissionExists(this) && AppUtils.checkIfLocationPermissionExists(this)) {
                PermissionUtils.checkPermission(this, PushPermissionManager.ANDROID_PERMISSION_STRING, new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$loadDeviceSelectionFragment$1
                    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
                    public void onPermissionAsk() {
                        ActivityPairing activityPairing = ActivityPairing.this;
                        ActivityCompat.requestPermissions(activityPairing, new String[]{PushPermissionManager.ANDROID_PERMISSION_STRING}, activityPairing.getPOST_NOTIFICATION_PERMISSION_REQUEST_CODE());
                    }

                    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
                    public void onPermissionDisabled() {
                        ActivityPairing.this.showNotificationPermissionDialog();
                    }

                    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
                    public void onPermissionGranted() {
                        ActivityPairing.this.y();
                    }

                    @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
                    public void onPermissionPreviouslyDenied() {
                        ActivityPairing.this.showNotificationPermissionDialog();
                    }
                });
            } else if (!payUtils.checkIfBluetoothPermissionExists(this)) {
                z();
            } else if (!AppUtils.checkIfLocationPermissionExists(this)) {
                B();
            } else {
                y();
            }
        } else if (AppUtils.checkIfLocationPermissionExists(this)) {
            y();
        } else {
            B();
        }
    }

    public final void loadPermissionsFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentBluetoothPermissions.Companion companion = FragmentBluetoothPermissions.Companion;
        beginTransaction.replace(R.id.fragment_container, companion.newInstance()).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void loadQRScanningFragment() {
        this.G = false;
        if (isFinishing() || isDestroyed()) {
            return;
        }
        String string = getResources().getString(R.string.scan_qr_code_text);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.scan_qr_code_text)");
        Q(string);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentQRScanDevice.Companion.newInstance()).addToBackStack(FragmentScanningDevice.Companion.toString()).commitAllowingStateLoss();
    }

    public final void logErrorToAnalytics(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        try {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setCommandName(FirebaseEventParams.Description.DISCONNECT.getValue());
            analyticsLog.setCommandStatus(FirebaseEventParams.Description.ERROR.getValue());
            analyticsLog.setDeviceConnectionStatus(FirebaseEventParams.Description.DISCONNECTED.getValue());
            analyticsLog.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        D();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        BluetoothDevice remoteDevice;
        super.onActivityResult(i, i2, intent);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        Intrinsics.checkNotNull(findFragmentById);
        findFragmentById.onActivityResult(i, i2, intent);
        String str = this.D;
        LogHelper.d(str, "onActivityResult: " + i);
        if (i == this.z) {
            Object systemService = getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            if (((BluetoothManager) systemService).getAdapter().isEnabled()) {
                return;
            }
            Fragment findFragmentById2 = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if ((findFragmentById2 instanceof FragmentDevciePairing) || (findFragmentById2 instanceof FragmentDeviceListing)) {
                showOpenBluetoothSettingsDialog();
            }
        } else if (i != this.A) {
            if (i2 == this.x) {
                if (Build.VERSION.SDK_INT < 33 || PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT", PushPermissionManager.ANDROID_PERMISSION_STRING}).length != 0) {
                    return;
                }
                y();
            } else if (i == this.B) {
                AppSessionManager.getInstance(this).setPermissionSettings(Boolean.TRUE);
                AppNavigator.Companion.navigateToDashBoard(this, true);
                SessionManager.getInstance(this).setIsFromOnboarding(true);
            }
        } else if (i2 == -1) {
            LoadingDialog progressDialog = getProgressDialog();
            Intrinsics.checkNotNull(progressDialog);
            if (progressDialog.isShowing()) {
                dismissProgress();
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            Intrinsics.checkNotNullExpressionValue(defaultAdapter, "getDefaultAdapter()");
            Intrinsics.checkNotNull(intent);
            Parcelable parcelableExtra = intent.getParcelableExtra("android.companion.extra.DEVICE");
            if (parcelableExtra != null) {
                if (parcelableExtra instanceof ScanResult) {
                    remoteDevice = defaultAdapter.getRemoteDevice(((ScanResult) parcelableExtra).getDevice().getAddress());
                } else {
                    remoteDevice = defaultAdapter.getRemoteDevice(parcelableExtra.toString());
                }
                connectTo$default(this, new BleDevice(remoteDevice), null, 2, null);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            if (this.y + this.u > System.currentTimeMillis()) {
                finishAffinity();
            }
            Toast.makeText(this, getString(R.string.quit_app), 0).show();
            this.y = System.currentTimeMillis();
        } else {
            onTryAgainClicked();
        }
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (findFragmentById instanceof FragmentDeviceSelection) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DEVICE_SELECTION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_PERMISSION_INFO_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        }
        if (findFragmentById instanceof FragmentBluetoothPermissions) {
            String string = getString(R.string.connect);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connect)");
            Q(string);
        }
        if (findFragmentById instanceof FragmentDeviceListing) {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.DEVICE_SCANNING_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_PERMISSION_INFO_SCREEN.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            String string2 = getString(R.string.select_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.select_device)");
            Q(string2);
        }
        if (findFragmentById instanceof FragmentDevciePairing) {
            AnalyticsLog analyticsLog3 = new AnalyticsLog();
            analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.DEVICE_PAIRING_SCREEN.getValue());
            analyticsLog3.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_PERMISSION_INFO_SCREEN.getValue());
            analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
            String string3 = getString(R.string.connecting);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.connecting)");
            Q(string3);
        }
        if (findFragmentById instanceof FragmentDevicePaired) {
            AnalyticsLog analyticsLog4 = new AnalyticsLog();
            analyticsLog4.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog4.setScreenName(FirebaseEventParams.ScreenName.PAIRING_SUCCESS_SCREEN.getValue());
            analyticsLog4.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_PERMISSION_INFO_SCREEN.getValue());
            analyticsLog4.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog4);
        }
        if (findFragmentById instanceof FragmentPairingFailed) {
            AnalyticsLog analyticsLog5 = new AnalyticsLog();
            analyticsLog5.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog5.setScreenName(FirebaseEventParams.ScreenName.PAIRING_FAILED_SCREEN.getValue());
            analyticsLog5.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_PERMISSION_INFO_SCREEN.getValue());
            analyticsLog5.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog5);
        }
        if (findFragmentById instanceof FragmentScanningDevice) {
            String string4 = getString(R.string.qr_scan);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qr_scan)");
            Q(string4);
        }
    }

    public final void onBluetoothPairedNextClicked() {
        BleApiManager.getInstance(this).getBleApi().setConnectionChangeListener(this);
        String string = getString(R.string.configuring_your_band);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.configuring_your_band)");
        showProgresswithMsg(string);
        if (!AppUtils.isNetConnected(this)) {
            dismissProgress();
            showNoInternetMessage();
            return;
        }
        this.p = true;
        BaseActivity.showProgressWithTitle$default(this, null, 1, null);
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.f
            @Override // java.lang.Runnable
            public final void run() {
                ActivityPairing.F(ActivityPairing.this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
    public void onConnectionResponse(@NotNull ConnectionStatus bleState) {
        Intrinsics.checkNotNullParameter(bleState, "bleState");
        if (bleState == ConnectionStatus.CONNECTED) {
            C();
        } else if (bleState == ConnectionStatus.DISCONNECTED) {
            D();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pairing);
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.onboarding_progressBar);
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        setActivityPairingViewModel((ActivityPairingViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityPairingViewModel.class));
        getActivityPairingViewModel().setViewmodelListener(this);
        setBt3CallViewModel((BT3CallViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(BT3CallViewModel.class));
        loadPermissionsFragment();
        ((ConstraintLayout) _$_findCachedViewById(R.id.clTop)).setVisibility(8);
        AppListHelper.Companion.saveAppListFromSystem$default(AppListHelper.Companion, this, false, 2, null);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.e
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public final void onBackStackChanged() {
                ActivityPairing.G(ActivityPairing.this);
            }
        });
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        dismissProgress();
        Toast.makeText(this, "Unable to proceed. try again", 1).show();
    }

    @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        List<String> list;
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        boolean z = false;
        if (i == this.v) {
            if (!(grantResults.length == 0)) {
                int length = grantResults.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = true;
                        break;
                    } else if (grantResults[i2] != 0) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    loadDeviceSelectionFragment();
                    return;
                }
                String string = getString(R.string.bluetooth_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_permission_required)");
                V(string);
            }
        } else if (i == this.w) {
            if (!(grantResults.length == 0)) {
                int length2 = grantResults.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        z = true;
                        break;
                    } else if (grantResults[i3] != 0) {
                        break;
                    } else {
                        i3++;
                    }
                }
                if (!z || (list = this.H) == null) {
                    return;
                }
                loadDeviceListingFragment(list);
            }
        } else if (i == this.x) {
            if (!(grantResults.length == 0)) {
                int length3 = grantResults.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length3) {
                        z = true;
                        break;
                    } else if (grantResults[i4] != 0) {
                        break;
                    } else {
                        i4++;
                    }
                }
                if (z) {
                    y();
                }
            }
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        if (this.p) {
            this.p = false;
            FragmentStepsGoalViewModel fragmentStepsGoalViewModel = (FragmentStepsGoalViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(FragmentStepsGoalViewModel.class);
            this.q = fragmentStepsGoalViewModel;
            FragmentStepsGoalViewModel fragmentStepsGoalViewModel2 = null;
            if (fragmentStepsGoalViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stepsGoalViewModel");
                fragmentStepsGoalViewModel = null;
            }
            fragmentStepsGoalViewModel.setMListener(new ViewModelListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$onSuccess$1
                @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
                public void onDataFailure(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    ActivityPairing.this.dismissProgress();
                    Toast.makeText(ActivityPairing.this, (int) R.string.somethings_went_wrong, 1).show();
                }

                @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
                public void onSuccess() {
                    ActivityPairing.this.dismissProgress();
                    if (!SessionManager.getInstance(ActivityPairing.this).getIsBatteryOptimisationDone().booleanValue()) {
                        AppNavigator.Companion companion = AppNavigator.Companion;
                        ActivityPairing activityPairing = ActivityPairing.this;
                        companion.navigateToConfigurationSettings(activityPairing, activityPairing.getCONFIGURATION_REQUESTCODE());
                        return;
                    }
                    AppNavigator.Companion.navigateToDashBoard(ActivityPairing.this, true);
                    SessionManager.getInstance(ActivityPairing.this).setIsFromOnboarding(true);
                }
            });
            SessionManager.getInstance(this).setIsDevicePaired(true);
            FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(this).getFitnessGoalStepsData();
            if ((fitnessGoalStepsData != null ? fitnessGoalStepsData.target : null) != null) {
                Integer num = fitnessGoalStepsData.target;
                Intrinsics.checkNotNullExpressionValue(num, "fitnessGoalSteps.target");
                if (num.intValue() > 0) {
                    FragmentStepsGoalViewModel fragmentStepsGoalViewModel3 = this.q;
                    if (fragmentStepsGoalViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("stepsGoalViewModel");
                    } else {
                        fragmentStepsGoalViewModel2 = fragmentStepsGoalViewModel3;
                    }
                    Integer num2 = fitnessGoalStepsData.target;
                    Intrinsics.checkNotNull(num2);
                    String valueOf = String.valueOf(num2.intValue());
                    Integer num3 = fitnessGoalStepsData.goalId;
                    fragmentStepsGoalViewModel2.onGoalSelected(valueOf, Integer.valueOf(num3 != null ? num3.intValue() : 0));
                    return;
                }
            }
            FragmentStepsGoalViewModel fragmentStepsGoalViewModel4 = this.q;
            if (fragmentStepsGoalViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stepsGoalViewModel");
            } else {
                fragmentStepsGoalViewModel2 = fragmentStepsGoalViewModel4;
            }
            fragmentStepsGoalViewModel2.onGoalSelected("10000", 0);
        }
    }

    public final void onTryAgainClicked() {
        while (getSupportFragmentManager().getBackStackEntryCount() != 1) {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    public final void retryConnect(final boolean z, @NotNull final BleDevice selectedBleDevice, @NotNull ConnectRequest connectRequest) {
        Intrinsics.checkNotNullParameter(selectedBleDevice, "selectedBleDevice");
        Intrinsics.checkNotNullParameter(connectRequest, "connectRequest");
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            String str = this.D;
            LogHelper.d(str, "Retry Connect initiated for device name " + selectedBleDevice.getmDevice().getName() + ", mac Address:" + selectedBleDevice.getmDevice().getAddress());
            BleApiManager.getInstance(this).getBleApi().connect(connectRequest, new ConnectionResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$retryConnect$1
                @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
                public void onConnectionResponse(@NotNull ConnectionStatus bleState) {
                    DeviceSupportedFeatures deviceSupportedFeatures;
                    DeviceSupportedFeatures deviceSupportedFeatures2;
                    Intrinsics.checkNotNullParameter(bleState, "bleState");
                    if (bleState == ConnectionStatus.CONNECTED) {
                        ActivityPairing.this.handleConnected(z, selectedBleDevice);
                        BleApi bleApi = BleApiManager.getInstance(ActivityPairing.this).getBleApi();
                        boolean z2 = true;
                        if ((bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isKahaBTCallSupported()) ? false : true) {
                            BT3CallViewModel.connectToBT3Watch$default(ActivityPairing.this.getBt3CallViewModel(), true, false, false, 6, null);
                            return;
                        }
                        BleApi bleApi2 = BleApiManager.getInstance(ActivityPairing.this).getBleApi();
                        if (bleApi2 == null || (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isBTCallingSupported()) {
                            z2 = false;
                        }
                        if (z2 && PayUtils.isIDODeviceSupportsBTCall(ActivityPairing.this)) {
                            BT3CallViewModel.connectToBT3Watch$default(ActivityPairing.this.getBt3CallViewModel(), true, false, false, 6, null);
                        }
                    } else if (bleState == ConnectionStatus.DISCONNECTED) {
                        ActivityPairing.this.handleDisconnected();
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
                public void onError(@NotNull Error error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = ActivityPairing.this.getTAG();
                    LogHelper.d(tag, "Connection error" + error.getMessage());
                    ActivityPairing.this.logErrorToAnalytics(error);
                }
            });
        }
    }

    public final void sendCommandNextToUserSetting(@NotNull BleDeviceInfo bleDeviceInfo) {
        Intrinsics.checkNotNullParameter(bleDeviceInfo, "bleDeviceInfo");
        if (!kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.v2_device), false)) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this) && !SessionManager.getInstance(this).getDeviceType().equals(getString(R.string.v7_device))) {
                if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.j1790_device), false)) {
                    P(bleDeviceInfo);
                    return;
                } else if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.j1810g_device), false)) {
                    M();
                    P(bleDeviceInfo);
                    return;
                } else if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.j1963d_device), false)) {
                    P(bleDeviceInfo);
                    return;
                } else if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.j1963yh_device), false)) {
                    P(bleDeviceInfo);
                    return;
                } else if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.j1860_device), false)) {
                    P(bleDeviceInfo);
                    return;
                } else if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.smart_t), false)) {
                    K(bleDeviceInfo);
                    return;
                } else if (kotlin.text.m.equals(SessionManager.getInstance(this).getDeviceType(), getResources().getString(R.string.C11G), false)) {
                    K(bleDeviceInfo);
                    return;
                } else if (companion.isSmaDevice(this)) {
                    K(bleDeviceInfo);
                    return;
                } else if (companion.isMoyangDevice(this)) {
                    K(bleDeviceInfo);
                    return;
                } else if (companion.isIDODevice(this)) {
                    K(bleDeviceInfo);
                    return;
                } else if (companion.isMatrixDevice(this)) {
                    sendNotificationConfigurationToBand$default(this, false, 1, null);
                    K(bleDeviceInfo);
                    return;
                } else if (companion.isTouchELXDevice(this)) {
                    K(bleDeviceInfo);
                    return;
                } else if (companion.isEastApexDevice(this)) {
                    K(bleDeviceInfo);
                    return;
                } else {
                    return;
                }
            }
        }
        M();
        sendNotificationConfigurationToBand$default(this, false, 1, null);
        P(bleDeviceInfo);
    }

    public final void sendNotificationConfigurationToBand(boolean z) {
        SetCallSmsSocialNotificationRequest callSmsSocialNotificationRequest = new SetCallSmsSocialNotificationRequest.Builder().setAppAlerts(z, true, true, true, true, true, true, true, true, true, true, true, true, true, true).setTelegramEnabled(true).setLineEnabled(true).setOtherAppEnabled(true).setWhatsAppBusinessEnabled(true).setCustomEventEnabled(true).setGmailEnabled(true).setNewsEnabled(true).setKaKaoTalkEnabled(true).setOutLookEnabled(true).setYahooEmailEnabled(true).setYoutubeEnabled(true).setLinkedInEnabled(true).build();
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        Intrinsics.checkNotNullExpressionValue(callSmsSocialNotificationRequest, "callSmsSocialNotificationRequest");
        bleApi.setUserSettings(callSmsSocialNotificationRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$sendNotificationConfigurationToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
    }

    public final void setActivityPairingViewModel(@NotNull ActivityPairingViewModel activityPairingViewModel) {
        Intrinsics.checkNotNullParameter(activityPairingViewModel, "<set-?>");
        this.activityPairingViewModel = activityPairingViewModel;
    }

    public final void setBackPressedCounter(int i) {
        this.E = i;
    }

    public final void setBt3CallViewModel(@NotNull BT3CallViewModel bT3CallViewModel) {
        Intrinsics.checkNotNullParameter(bT3CallViewModel, "<set-?>");
        this.bt3CallViewModel = bT3CallViewModel;
    }

    public final void setConnectedDeviceName(@Nullable String str) {
        this.r = str;
    }

    public final void setEnableBluetoothDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.C = bottomSheetDialogTwoButtons;
    }

    public final void setLoadGoalScreen(boolean z) {
        this.p = z;
    }

    public final void setPairingFailedFragmentShown(boolean z) {
        this.G = z;
    }

    public final void showNotificationPermissionDialog() {
        String string = getString(R.string.notification_permission_dialog_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.notif…_permission_dialog_title)");
        String string2 = getString(R.string.notification_permission_dialog_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.notif…ermission_dialog_message)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
        String string3 = getString(R.string.grant_permissions);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.grant_permissions)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPairing.S(BottomSheetDialogOneButtonTitleMessage.this, this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void showOpenBluetoothSettingsDialog() {
        if (this.C == null) {
            String string = getString(R.string.bluetooth_off);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
            String string2 = getString(R.string.bluetooth_off_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.C = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.proceed);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.proceed)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityPairing.T(ActivityPairing.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.C;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityPairing.U(ActivityPairing.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.C;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.C;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
    }

    public final void x(final BleDeviceInfo bleDeviceInfo) {
        int autoHrInterval = UserDataManager.getInstance(this).getAutoHrInterval();
        if (UserDataManager.getInstance(this).isAutoHrEnabled()) {
            BleApiManager.getInstance(this).getBleApi().setUserSettings(new HeartRateTimeIntervalRequest.Builder(autoHrInterval).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$checkAndEnableAutoHRSettings$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.e(ActivityPairing.this.getTAG(), "Auto HR setting failed");
                    if (BleApiManager.getInstance(ActivityPairing.this).getDeviceType() != DeviceType.v7) {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (!companion.isCZDevice(ActivityPairing.this) && !companion.isCADevice(ActivityPairing.this) && (BleApiManager.getInstance(ActivityPairing.this).getBleApi() == null || !BleApiManager.getInstance(ActivityPairing.this).getBleApi().getDeviceSupportedFeatures().getAutoTemperatureSettingsSupported())) {
                            ActivityPairing.this.sendCommandNextToUserSetting(bleDeviceInfo);
                            return;
                        }
                    }
                    ActivityPairing.this.O(60, bleDeviceInfo);
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.e(ActivityPairing.this.getTAG(), "Auto Hr setting pass");
                    if (BleApiManager.getInstance(ActivityPairing.this).getDeviceType() != DeviceType.v7) {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if (!companion.isCZDevice(ActivityPairing.this) && !companion.isCADevice(ActivityPairing.this) && (BleApiManager.getInstance(ActivityPairing.this).getBleApi() == null || !BleApiManager.getInstance(ActivityPairing.this).getBleApi().getDeviceSupportedFeatures().getAutoTemperatureSettingsSupported())) {
                            if (BleApiManager.getInstance(ActivityPairing.this).getDeviceType() == DeviceType.smaF2) {
                                ActivityPairing.this.N(bleDeviceInfo);
                                return;
                            } else {
                                ActivityPairing.this.sendCommandNextToUserSetting(bleDeviceInfo);
                                return;
                            }
                        }
                    }
                    ActivityPairing.this.O(60, bleDeviceInfo);
                }
            });
            return;
        }
        if (BleApiManager.getInstance(this).getDeviceType() != DeviceType.v7) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCZDevice(this) && !companion.isCADevice(this) && (BleApiManager.getInstance(this).getBleApi() == null || !BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().getAutoTemperatureSettingsSupported())) {
                sendCommandNextToUserSetting(bleDeviceInfo);
                return;
            }
        }
        O(60, bleDeviceInfo);
    }

    public final void y() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.clTop)).setVisibility(8);
        this.G = false;
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentDeviceSelection.Companion companion = FragmentDeviceSelection.Companion;
        beginTransaction.replace(R.id.fragment_container, companion.newInstance(true)).addToBackStack(companion.toString()).commitAllowingStateLoss();
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.onboarding_progressBar);
        if (progressBar == null) {
            return;
        }
        progressBar.setVisibility(8);
    }

    @RequiresApi(31)
    public final void z() {
        PermissionUtils.checkPermission(this, "android.permission.BLUETOOTH_SCAN", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing$getBluetoothPermissions$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityPairing activityPairing = ActivityPairing.this;
                ActivityCompat.requestPermissions(activityPairing, new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.ACCESS_FINE_LOCATION", "android.permission.BLUETOOTH_CONNECT"}, activityPairing.getLOCATION_PERMISSION_REQUEST_CODE());
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityPairing activityPairing = ActivityPairing.this;
                String string = activityPairing.getString(R.string.bluetooth_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_permission_required)");
                activityPairing.V(string);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityPairing.this.loadDeviceSelectionFragment();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityPairing activityPairing = ActivityPairing.this;
                String string = activityPairing.getString(R.string.bluetooth_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_permission_required)");
                activityPairing.V(string);
            }
        });
    }
}
