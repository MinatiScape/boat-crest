package com.coveiot.android.leonardo.more.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.android.leonardo.utils.JL_LogUtils;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.PreferenceNames;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.DeviceInfoManager;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.device.model.IOTUserDeviceReq;
import com.coveiot.coveaccess.device.model.IOTUserDeviceRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.jieli.jl_bt_ota.interfaces.BtEventCallback;
import com.jieli.jl_bt_ota.model.BluetoothOTAConfigure;
import com.jieli.watchtesttool.tool.bluetooth.BluetoothEventListener;
import com.jieli.watchtesttool.tool.bluetooth.BluetoothHelper;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
import com.szabh.smable3.component.BleConnector;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateSmaJL extends BaseActivity {
    @Nullable
    public String B;
    public BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessageError;
    public BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessageSucess;
    @Nullable
    public ProgressBar q;
    @Nullable
    public TextView r;
    public int s;
    public BottomSheetDialog t;
    public SoftwareUpdateRes.DataBean.FirmwareBean u;
    public boolean v;
    @Nullable
    public OTAManager x;
    @Nullable
    public BluetoothHelper y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityFirmwareUpdateSmaJL";
    @NotNull
    public OTAStatus w = OTAStatus.NONE;
    @NotNull
    public final Handler z = new Handler();
    @NotNull
    public String A = "dfu";
    public final int C = 101;
    public final int D = 102;
    @NotNull
    public final ActivityFirmwareUpdateSmaJL$mBtEventListener$1 E = new BluetoothEventListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$mBtEventListener$1
        @Override // com.jieli.watchtesttool.tool.bluetooth.BluetoothEventListener
        public void onAdapterStatus(boolean z) {
            String tag = ActivityFirmwareUpdateSmaJL.this.getTAG();
            LogHelper.d(tag, "bt onAdapterStatus " + z);
        }

        @Override // com.jieli.watchtesttool.tool.bluetooth.BluetoothEventListener
        public void onConnection(@NotNull BluetoothDevice device, int i) {
            Intrinsics.checkNotNullParameter(device, "device");
            String tag = ActivityFirmwareUpdateSmaJL.this.getTAG();
            LogHelper.d(tag, "bt onConnection " + device + ' ' + i);
        }
    };
    @NotNull
    public final BtEventCallback F = new BtEventCallback() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$mOTAEventCallback$1
        @Override // com.jieli.jl_bt_ota.interfaces.BtEventCallback, com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
        public void onConnection(@NotNull BluetoothDevice device, int i) {
            ActivityFirmwareUpdateSmaJL.OTAStatus oTAStatus;
            OTAManager oTAManager;
            OTAManager oTAManager2;
            ActivityFirmwareUpdateSmaJL.OTAStatus oTAStatus2;
            ActivityFirmwareUpdateSmaJL.OTAStatus oTAStatus3;
            boolean w;
            Intrinsics.checkNotNullParameter(device, "device");
            String tag = ActivityFirmwareUpdateSmaJL.this.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("ota onConnection -> mJLUpgradeStatus = ");
            oTAStatus = ActivityFirmwareUpdateSmaJL.this.w;
            sb.append(oTAStatus);
            sb.append(", ");
            sb.append(device);
            sb.append(" ,status = ");
            sb.append(i);
            sb.append(" ,isOTA = ");
            oTAManager = ActivityFirmwareUpdateSmaJL.this.x;
            Intrinsics.checkNotNull(oTAManager);
            sb.append(oTAManager.isOTA());
            LogHelper.d(tag, sb.toString());
            oTAManager2 = ActivityFirmwareUpdateSmaJL.this.x;
            Intrinsics.checkNotNull(oTAManager2);
            if (oTAManager2.isOTA()) {
                return;
            }
            if (i == 0) {
                LogHelper.d(ActivityFirmwareUpdateSmaJL.this.getTAG(), "ota onConnection -> device disconnected");
                oTAStatus2 = ActivityFirmwareUpdateSmaJL.this.w;
                if (oTAStatus2 == ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADE_PREPARE) {
                    ActivityFirmwareUpdateSmaJL.this.N(ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADE_PREPARE_FAILED);
                }
            } else if (i != 1) {
            } else {
                ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = ActivityFirmwareUpdateSmaJL.this;
                oTAStatus3 = activityFirmwareUpdateSmaJL.w;
                w = activityFirmwareUpdateSmaJL.w(oTAStatus3);
                if (w) {
                    ActivityFirmwareUpdateSmaJL.this.startOTA();
                }
            }
        }
    };

    /* loaded from: classes5.dex */
    public enum OTAStatus {
        NONE,
        UPGRADE_SCANNING_START,
        UPGRADE_SCANNING_TIMEOUT,
        UPGRADE_SCANNING_STOP,
        UPGRADE_PREPARE,
        UPGRADE_PREPARE_FAILED,
        UPGRADE_START,
        UPGRADE_CHECKING,
        UPGRADEING,
        UPGRADE_STOP,
        UPGRADE_FAILED
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OTAStatus.values().length];
            try {
                iArr[OTAStatus.UPGRADE_SCANNING_TIMEOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OTAStatus.UPGRADE_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[OTAStatus.UPGRADE_PREPARE_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$deleteFiles$1", f = "ActivityFirmwareUpdateSmaJL.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FileUtils fileUtils = FileUtils.INSTANCE;
                File filesDir = ActivityFirmwareUpdateSmaJL.this.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateSmaJL.filesDir");
                fileUtils.deleteRecursive(fileUtils.getFirmwareFile(filesDir, AppConstants.SMA_FIRMWARE_FILE_NAME.getValue()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$showErrorBottomSheet$1", f = "ActivityFirmwareUpdateSmaJL.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((TextView) ActivityFirmwareUpdateSmaJL.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdateSmaJL.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdateSmaJL.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdateSmaJL.this._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$showFWUpdateSuccessDialog$1", f = "ActivityFirmwareUpdateSmaJL.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((TextView) ActivityFirmwareUpdateSmaJL.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdateSmaJL.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdateSmaJL.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdateSmaJL.this._$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivityFirmwareUpdateSmaJL this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isBluetoothEnabled(this$0)) {
            Toast.makeText(this$0, (int) R.string.bluetooth_off_message, 0).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0, (int) R.string.band_not_connected, 0).show();
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
        } else {
            ((TextView) this$0.findViewById(R.id.toolbar_title)).setVisibility(0);
            ConstraintLayout fw_update_failed_layout = (ConstraintLayout) this$0._$_findCachedViewById(R.id.fw_update_failed_layout);
            Intrinsics.checkNotNullExpressionValue(fw_update_failed_layout, "fw_update_failed_layout");
            this$0.gone(fw_update_failed_layout);
            ((Button) this$0._$_findCachedViewById(R.id.btn_update)).performClick();
        }
    }

    public static final void B(ActivityFirmwareUpdateSmaJL this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(-1);
        this$0.finish();
    }

    public static final void C(ActivityFirmwareUpdateSmaJL this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void D(ActivityFirmwareUpdateSmaJL this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isGpsEnabled(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.please_check_gps), 0).show();
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
        } else if (AppUtils.checkIfLocationPermissionExists(this$0)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this$0.u;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            this$0.v(firmwareBean);
        } else {
            this$0.I();
        }
    }

    public static final void F(ActivityFirmwareUpdateSmaJL this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void E() {
        OTAManager oTAManager = new OTAManager(this);
        this.x = oTAManager;
        oTAManager.registerBluetoothCallback(this.F);
    }

    public final boolean G(OTAStatus oTAStatus) {
        int i = WhenMappings.$EnumSwitchMapping$0[oTAStatus.ordinal()];
        return i == 1 || i == 2 || i == 3;
    }

    public final void H() {
        SessionManager.getInstance(this).setForceFirmwareUpdated(true);
        SessionManager.getInstance(this).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(this).deleteSoftwareUpdateResponseBeanString();
        BleConnector bleConnector = BleConnector.INSTANCE;
        String str = this.B;
        Intrinsics.checkNotNull(str);
        bleConnector.setAddress(str).connect(true);
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, new ActivityFirmwareUpdateSmaJL$onTransferCompleted$1(this));
        TextView textView = this.r;
        if (textView != null) {
            textView.setText(getString(R.string.upgrading_dot));
        }
        ((TextView) _$_findCachedViewById(R.id.fw_upgrading_header)).setVisibility(4);
        ((TextView) _$_findCachedViewById(R.id.tv_fw_upgrade_info)).setText(getString(R.string.please_wait_after_fw_update));
        ((ImageView) _$_findCachedViewById(R.id.fw_upgrading_image)).setImageResource(R.drawable.fw_upgrade_final_configurations);
        LogHelper.d(this.p, "onTransferCompleted ");
    }

    public final void I() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = ActivityFirmwareUpdateSmaJL.this;
                i = activityFirmwareUpdateSmaJL.C;
                ActivityCompat.requestPermissions(activityFirmwareUpdateSmaJL, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityFirmwareUpdateSmaJL.this.L();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
                ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = ActivityFirmwareUpdateSmaJL.this;
                firmwareBean = activityFirmwareUpdateSmaJL.u;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                activityFirmwareUpdateSmaJL.v(firmwareBean);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityFirmwareUpdateSmaJL.this.L();
            }
        });
    }

    public final void J(BleDeviceInfo bleDeviceInfo) {
        IOTUserDeviceReq iOTUserDeviceReq = new IOTUserDeviceReq(bleDeviceInfo);
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported()) {
            String str = bleDeviceInfo.getmDeviceName();
            Intrinsics.checkNotNullExpressionValue(str, "bleDeviceInfo.getmDeviceName()");
            String lowerCase = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            iOTUserDeviceReq.setAppTrackerId(lowerCase);
        }
        LogHelper.i(this.p, "sendConnectedDeviceInfoToServer");
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$sendConnectedDeviceInfoToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ActivityFirmwareUpdateSmaJL.this.dismissProgress();
                LogHelper.i(ActivityFirmwareUpdateSmaJL.this.getTAG(), "sendConnectedDeviceInfoToServer onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                LogHelper.i(ActivityFirmwareUpdateSmaJL.this.getTAG(), "sendConnectedDeviceInfoToServer onSuccess");
                ActivityFirmwareUpdateSmaJL.this.showFWUpdateSuccessDialog();
            }
        });
    }

    public final void K(String str, String str2) {
        y();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
    }

    public final void L() {
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                AppUtils.openAppSettings(this, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void M() {
        BleApiManager.getInstance(this).getBleApi().getData(new DeviceInfoRequest.Builder().setIsFwVersion(true).build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$updateNewFwVersionToPref$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityFirmwareUpdateSmaJL.this.getTAG(), "deviceInfoRequest: onDataError");
                ActivityFirmwareUpdateSmaJL.this.showFWUpdateSuccessDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdateSmaJL.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        SessionManager.getInstance(ActivityFirmwareUpdateSmaJL.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    LogHelper.d(ActivityFirmwareUpdateSmaJL.this.getTAG(), "deviceInfoRequest: onDataResponse");
                    PreferenceManager.savePreference(ActivityFirmwareUpdateSmaJL.this, PreferenceNames.TEMPERATURE_CALIBRATION_VAL, Double.valueOf(0.0d));
                    ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = ActivityFirmwareUpdateSmaJL.this;
                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo, "bleDeviceInfo");
                    activityFirmwareUpdateSmaJL.J(bleDeviceInfo);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void N(OTAStatus oTAStatus) {
        String str = this.p;
        LogHelper.d(str, "updateUpgradeStatus status = " + oTAStatus);
        this.w = oTAStatus;
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

    @NotNull
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessageError() {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.bottomSheetDialogImageTitleMessageError;
        if (bottomSheetDialogImageTitleMessage != null) {
            return bottomSheetDialogImageTitleMessage;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogImageTitleMessageError");
        return null;
    }

    @NotNull
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessageSucess() {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.bottomSheetDialogImageTitleMessageSucess;
        if (bottomSheetDialogImageTitleMessage != null) {
            return bottomSheetDialogImageTitleMessage;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogImageTitleMessageSucess");
        return null;
    }

    @NotNull
    public final String getDfuFilterName() {
        return this.A;
    }

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ld
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateSmaJL.A(ActivityFirmwareUpdateSmaJL.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.id
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateSmaJL.B(ActivityFirmwareUpdateSmaJL.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.remindMeLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.kd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateSmaJL.C(ActivityFirmwareUpdateSmaJL.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.hd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateSmaJL.D(ActivityFirmwareUpdateSmaJL.this, view);
            }
        });
    }

    public final void initDialogs() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.DialogThemeBottomSheet);
        this.t = bottomSheetDialog;
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.requestFeature(1);
        BottomSheetDialog bottomSheetDialog2 = this.t;
        BottomSheetDialog bottomSheetDialog3 = null;
        if (bottomSheetDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
            bottomSheetDialog2 = null;
        }
        bottomSheetDialog2.setContentView(R.layout.dialog_updating_fw);
        BottomSheetDialog bottomSheetDialog4 = this.t;
        if (bottomSheetDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
        } else {
            bottomSheetDialog3 = bottomSheetDialog4;
        }
        bottomSheetDialog3.setCancelable(false);
        this.r = (TextView) findViewById(R.id.tv_progress_title);
        this.q = (ProgressBar) findViewById(R.id.progress_update);
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.firmware_update));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.jd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateSmaJL.F(ActivityFirmwareUpdateSmaJL.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
        if (i != 121) {
            if (i != 122) {
                return;
            }
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this.u;
            if (firmwareBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean2;
            }
            v(firmwareBean);
        } else if (AppUtils.checkIfLocationPermissionExists(this)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean3 = this.u;
            if (firmwareBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean3;
            }
            v(firmwareBean);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.y = BluetoothHelper.getInstance(getApplication());
        setContentView(R.layout.activity_update_latest_fw);
        Serializable serializableExtra = getIntent().getSerializableExtra(AppConstants.FIRMWARE_BEAN.getValue());
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes.DataBean.FirmwareBean");
        this.u = (SoftwareUpdateRes.DataBean.FirmwareBean) serializableExtra;
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_fw_version);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this.u;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        textView.setText(firmwareBean.getUpdateVersion());
        this.B = SessionManager.getInstance(this).getConnectedDeviceMacAddress();
        ((TextView) _$_findCachedViewById(R.id.current_fw_version)).setText(((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision());
        initToolbar();
        initClickListeners();
        initDialogs();
        JL_LogUtils.enableLog(this);
        BluetoothHelper bluetoothHelper = this.y;
        if (bluetoothHelper != null) {
            bluetoothHelper.addBluetoothEventListener(this.E);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        y();
        BluetoothHelper bluetoothHelper = this.y;
        if (bluetoothHelper != null) {
            bluetoothHelper.removeBluetoothEventListener(this.E);
        }
        BluetoothHelper bluetoothHelper2 = this.y;
        if (bluetoothHelper2 != null) {
            bluetoothHelper2.destroy();
        }
        z();
        BleConnector.INSTANCE.launch();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
        if (i == this.C) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this.u;
            if (firmwareBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean2;
            }
            v(firmwareBean);
        } else if (i == this.D) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean3 = this.u;
            if (firmwareBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean3;
            }
            v(firmwareBean);
        }
    }

    public final void setBottomSheetDialogImageTitleMessageError(@NotNull BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "<set-?>");
        this.bottomSheetDialogImageTitleMessageError = bottomSheetDialogImageTitleMessage;
    }

    public final void setBottomSheetDialogImageTitleMessageSucess(@NotNull BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "<set-?>");
        this.bottomSheetDialogImageTitleMessageSucess = bottomSheetDialogImageTitleMessage;
    }

    public final void setDfuFilterName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.A = str;
    }

    public final void showFWUpdateSuccessDialog() {
        y();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
    }

    public final void startOTA() {
        File externalCacheDir = getExternalCacheDir();
        String absolutePath = new File(externalCacheDir, AppConstants.SMA_FIRMWARE_FILE_NAME.getValue() + OTAManager.OTA_FILE_SUFFIX).getAbsolutePath();
        String str = this.p;
        LogHelper.d(str, "startOTA :: " + absolutePath);
        OTAManager oTAManager = this.x;
        BluetoothOTAConfigure bluetoothOption = oTAManager != null ? oTAManager.getBluetoothOption() : null;
        if (bluetoothOption != null) {
            bluetoothOption.setFirmwareFilePath(absolutePath);
        }
        OTAManager oTAManager2 = this.x;
        if (oTAManager2 != null) {
            oTAManager2.startOTA(new ActivityFirmwareUpdateSmaJL$startOTA$1(this));
        }
    }

    public final void v(SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        y();
        this.s = 0;
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1(this, firmwareBean, null), 2, null);
    }

    public final boolean w(OTAStatus oTAStatus) {
        return (G(oTAStatus) || oTAStatus == OTAStatus.UPGRADE_STOP) ? false : true;
    }

    public final void x(String str) {
        String str2 = this.p;
        LogHelper.d(str2, "connectDevice: " + str);
        BluetoothHelper bluetoothHelper = this.y;
        if (bluetoothHelper != null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            bluetoothHelper.connectDevice(defaultAdapter != null ? defaultAdapter.getRemoteDevice(str) : null);
        }
    }

    public final void y() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public final void z() {
        OTAManager oTAManager = this.x;
        if (oTAManager != null) {
            oTAManager.unregisterBluetoothCallback(this.F);
        }
        OTAManager oTAManager2 = this.x;
        if (oTAManager2 != null) {
            oTAManager2.release();
        }
        this.x = null;
    }
}
