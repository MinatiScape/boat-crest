package com.coveiot.android.leonardo.more.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.android.theme.BaseActivity;
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
import com.google.gson.Gson;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
import com.sifli.siflidfu.DFUImagePath;
import com.sifli.siflidfu.FileProcess;
import com.sifli.siflidfu.SifliDFUService;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdatePS1 extends BaseActivity {
    @Nullable
    public ProgressBar q;
    @Nullable
    public TextView r;
    public int s;
    public SoftwareUpdateRes.DataBean.FirmwareBean t;
    public boolean x;
    public boolean y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String p = ActivityFirmwareUpdatePS1.class.getSimpleName();
    @NotNull
    public final Handler u = new Handler();
    public final int v = 101;
    public final int w = 102;
    @NotNull
    public final ActivityFirmwareUpdatePS1$otaBroadCastReceiver$1 z = new ActivityFirmwareUpdatePS1$otaBroadCastReceiver$1(this);

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$deleteFiles$1", f = "ActivityFirmwareUpdatePS1.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                File filesDir = ActivityFirmwareUpdatePS1.this.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdatePS1.filesDir");
                fileUtils.deleteRecursive(fileUtils.getFirmwareFile(filesDir, AppConstants.PS1_FIRMWARE_FILE_NAME.getValue()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<ConnectionStatus, Unit> {
        public b() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(ActivityFirmwareUpdatePS1 this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.U();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ConnectionStatus connectionStatus) {
            invoke2(connectionStatus);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(ConnectionStatus connectionStatus) {
            if (connectionStatus == ConnectionStatus.CONNECTED) {
                Handler handler = ActivityFirmwareUpdatePS1.this.u;
                final ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS1 = ActivityFirmwareUpdatePS1.this;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.fd
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFirmwareUpdatePS1.b.invoke$lambda$0(ActivityFirmwareUpdatePS1.this);
                    }
                }, 3000L);
            }
            String tag = ActivityFirmwareUpdatePS1.this.getTAG();
            LogHelper.d(tag, "value " + connectionStatus);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$showErrorBottomSheet$1", f = "ActivityFirmwareUpdatePS1.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ((TextView) ActivityFirmwareUpdatePS1.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdatePS1.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdatePS1.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdatePS1.this._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$showFWUpdateSuccessDialog$1", f = "ActivityFirmwareUpdatePS1.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((TextView) ActivityFirmwareUpdatePS1.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdatePS1.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdatePS1.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdatePS1.this._$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$updateFirmwareToDevice$1", f = "ActivityFirmwareUpdatePS1.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ProgressBar progressBar = ActivityFirmwareUpdatePS1.this.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(false);
                }
                ProgressBar progressBar2 = ActivityFirmwareUpdatePS1.this.q;
                if (progressBar2 != null) {
                    progressBar2.setProgress(0);
                }
                TextView textView = ActivityFirmwareUpdatePS1.this.r;
                if (textView != null) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = ActivityFirmwareUpdatePS1.this.getResources().getString(R.string.dfu_uploading_percentage);
                    Intrinsics.checkNotNullExpressionValue(string, "this@ActivityFirmwareUpd…dfu_uploading_percentage)");
                    String format = String.format(string, Arrays.copyOf(new Object[]{Boxing.boxInt(0)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    textView.setText(format);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivityFirmwareUpdatePS1 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0, "Failed to load file", 1).show();
    }

    public static final void B(ActivityFirmwareUpdatePS1 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0, "Failed to load file", 1).show();
    }

    public static final void F(ActivityFirmwareUpdatePS1 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isBluetoothEnabled(this$0)) {
            Toast.makeText(this$0, (int) R.string.bluetooth_off_message, 0).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0, (int) R.string.band_not_connected, 0).show();
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
        } else {
            ((ConstraintLayout) this$0._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(8);
            ((TextView) this$0.findViewById(R.id.toolbar_title)).setVisibility(0);
            ((Button) this$0._$_findCachedViewById(R.id.btn_update)).performClick();
        }
    }

    public static final void G(ActivityFirmwareUpdatePS1 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void H(ActivityFirmwareUpdatePS1 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void I(ActivityFirmwareUpdatePS1 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isGpsEnabled(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.please_check_gps), 0).show();
        } else if (!AppUtils.isBluetoothEnabled(this$0)) {
            Toast.makeText(this$0, (int) R.string.bluetooth_off_message, 0).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0, (int) R.string.band_not_connected, 0).show();
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
        } else if (AppUtils.checkIfLocationPermissionExists(this$0)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this$0.t;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            this$0.y(firmwareBean);
        } else {
            this$0.O();
        }
    }

    public static final void J(ActivityFirmwareUpdatePS1 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void L(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void C() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public final List<File> D(File file) {
        ArrayList arrayList = new ArrayList();
        E(file, TypeIntrinsics.asMutableList(arrayList));
        return arrayList;
    }

    public final List<File> E(File file, List<File> list) {
        if (!file.isDirectory()) {
            list.add(file);
            return list;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                Intrinsics.checkNotNullExpressionValue(file2, "file");
                E(file2, list);
            }
        }
        return list;
    }

    public final void K() {
        LogHelper.d(this.p, "entered onTransferCompleted");
        SessionManager.getInstance(this).setForceFirmwareUpdated(true);
        SessionManager.getInstance(this).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(this).deleteSoftwareUpdateResponseBeanString();
        M(SessionManager.getInstance(this).getConnectedDeviceMacAddress());
        LiveData<ConnectionStatus> registerConnectionStatus = BleApiManager.getInstance(this).getBleApi().registerConnectionStatus();
        final b bVar = new b();
        registerConnectionStatus.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.bd
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityFirmwareUpdatePS1.L(Function1.this, obj);
            }
        });
        TextView textView = this.r;
        if (textView != null) {
            textView.setText(getString(R.string.upgrading));
        }
        ((TextView) _$_findCachedViewById(R.id.tv_fw_upgrade_info)).setText(getString(R.string.please_wait_after_fw_update));
        ((ImageView) _$_findCachedViewById(R.id.fw_upgrading_image)).setImageResource(R.drawable.fw_upgrade_final_configurations);
        LogHelper.d(this.p, "onTransferCompleted ");
    }

    public final void M(String str) {
        try {
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
            if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return;
            }
            BluetoothGatt connectGatt = remoteDevice.connectGatt(this, false, new BluetoothGattCallback() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$refreshService$gatt$1
            });
            Method method = connectGatt.getClass().getMethod("refresh", new Class[0]);
            Intrinsics.checkNotNullExpressionValue(method, "gatt.javaClass.getMethod(\"refresh\")");
            method.invoke(connectGatt, new Object[0]);
            connectGatt.disconnect();
            connectGatt.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void N() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SifliDFUService.BROADCAST_DFU_LOG);
        intentFilter.addAction(SifliDFUService.BROADCAST_DFU_STATE);
        intentFilter.addAction(SifliDFUService.BROADCAST_DFU_PROGRESS);
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(this.z, intentFilter);
    }

    public final void O() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS1 = ActivityFirmwareUpdatePS1.this;
                i = activityFirmwareUpdatePS1.v;
                ActivityCompat.requestPermissions(activityFirmwareUpdatePS1, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityFirmwareUpdatePS1.this.R();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
                ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS1 = ActivityFirmwareUpdatePS1.this;
                firmwareBean = activityFirmwareUpdatePS1.t;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                activityFirmwareUpdatePS1.y(firmwareBean);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityFirmwareUpdatePS1.this.R();
            }
        });
    }

    public final void P(BleDeviceInfo bleDeviceInfo) {
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(new IOTUserDeviceReq(bleDeviceInfo), new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$sendConnectedDeviceInfoToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ActivityFirmwareUpdatePS1.this.dismissProgress();
                LogHelper.i(ActivityFirmwareUpdatePS1.this.getTAG(), "sendConnectedDeviceInfoToServer onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                LogHelper.i(ActivityFirmwareUpdatePS1.this.getTAG(), "sendConnectedDeviceInfoToServer onSuccess");
                ActivityFirmwareUpdatePS1.this.showFWUpdateSuccessDialog();
            }
        });
    }

    public final void Q(String str, String str2) {
        C();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
    }

    public final void R() {
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                AppUtils.openAppSettings(this, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void S() {
        stopService(new Intent(this, SifliDFUService.class));
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.z);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void T() {
        DFUImagePath dFUImagePath;
        File externalFilesDir = getExternalFilesDir(null);
        AppConstants appConstants = AppConstants.PS1_FIRMWARE_FILE_NAME;
        File file = new File(externalFilesDir, appConstants.getValue());
        C();
        if (!file.exists()) {
            file.mkdirs();
        }
        File externalCacheDir = getExternalCacheDir();
        FileProcess.unzipFolder(z(Uri.fromFile(new File(externalCacheDir, appConstants.getValue() + OTAManager.OTA_ZIP_SUFFIX)), "ota.zip"), file.toString());
        List<File> D = D(file);
        ArrayList arrayList = new ArrayList();
        if (D != null) {
            for (File file2 : D) {
                String name = file2.getName();
                Intrinsics.checkNotNullExpressionValue(name, "file.name");
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                String lowerCase = name.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (kotlin.text.m.startsWith$default(lowerCase, "ctrl_packet", false, 2, null)) {
                    dFUImagePath = new DFUImagePath(file2.toString(), null, -1);
                } else if (kotlin.text.m.startsWith$default(lowerCase, "outapp", false, 2, null)) {
                    dFUImagePath = new DFUImagePath(file2.toString(), null, 0);
                } else if (kotlin.text.m.startsWith$default(lowerCase, "outres", false, 2, null)) {
                    dFUImagePath = new DFUImagePath(file2.toString(), null, 3);
                } else if (kotlin.text.m.startsWith$default(lowerCase, "outfont", false, 2, null)) {
                    dFUImagePath = new DFUImagePath(file2.toString(), null, 4);
                } else if (kotlin.text.m.startsWith$default(lowerCase, "outlcpu", false, 2, null)) {
                    dFUImagePath = new DFUImagePath(file2.toString(), null, 1);
                } else if (kotlin.text.m.startsWith$default(lowerCase, "outcom_patch", false, 2, null)) {
                    dFUImagePath = new DFUImagePath(file2.toString(), null, 2);
                } else {
                    LogHelper.w(this.p, "Unknown partition \"%s\"", file2.getName());
                }
                LogHelper.d(this.p, "path: %s", dFUImagePath.getImagePath());
                arrayList.add(dFUImagePath);
            }
            if (arrayList.size() > 0) {
                LogHelper.i(this.p, "Starting Sifli OTA service");
                if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    this.y = true;
                    N();
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(null), 2, null);
                    SifliDFUService.startActionDFUNorExt(this, BleApiManager.getInstance(this).getBleApi().getMacAddress(), arrayList, 1, 0);
                }
            }
        }
    }

    public final void U() {
        DeviceInfoRequest build = new DeviceInfoRequest.Builder().setIsFwVersion(true).build();
        LogHelper.d(this.p, "updateNewFwVersionToPref ");
        BleApiManager.getInstance(this).getBleApi().getData(build, new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$updateNewFwVersionToPref$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityFirmwareUpdatePS1.this.getTAG(), "deviceInfoRequest: onDataError");
                BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdatePS1.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                if (bleDeviceInfo != null) {
                    firmwareBean = ActivityFirmwareUpdatePS1.this.t;
                    if (firmwareBean == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                        firmwareBean = null;
                    }
                    bleDeviceInfo.setFirmwareRevision(firmwareBean.getUpdateVersion());
                    SessionManager.getInstance(ActivityFirmwareUpdatePS1.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                }
                ActivityFirmwareUpdatePS1.this.showFWUpdateSuccessDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityFirmwareUpdatePS1.this.getTAG(), "deviceInfoRequest: onDataResponse");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdatePS1.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        SessionManager.getInstance(ActivityFirmwareUpdatePS1.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    LogHelper.d(ActivityFirmwareUpdatePS1.this.getTAG(), "deviceInfoRequest: onDataResponse");
                    ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS1 = ActivityFirmwareUpdatePS1.this;
                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo, "bleDeviceInfo");
                    activityFirmwareUpdatePS1.P(bleDeviceInfo);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
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

    public final String getTAG() {
        return this.p;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.wc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdatePS1.F(ActivityFirmwareUpdatePS1.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ad
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdatePS1.G(ActivityFirmwareUpdatePS1.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.remindMeLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.yc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdatePS1.H(ActivityFirmwareUpdatePS1.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.zc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdatePS1.I(ActivityFirmwareUpdatePS1.this, view);
            }
        });
    }

    public final void initDialogs() {
        this.r = (TextView) findViewById(R.id.tv_progress_title);
        this.q = (ProgressBar) findViewById(R.id.progress_update);
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.firmware_update));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.xc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdatePS1.J(ActivityFirmwareUpdatePS1.this, view);
            }
        });
    }

    public final void initiateDFU() {
        ProgressBar progressBar = this.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        TextView textView = this.r;
        if (textView != null) {
            textView.setText(R.string.dfu_state_dfu_preparing);
        }
        T();
    }

    public final boolean isUpdateIntitiated() {
        return this.y;
    }

    public final boolean isUpdatedSuccessfully() {
        return this.x;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
        if (i != 121) {
            if (i != 122) {
                return;
            }
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this.t;
            if (firmwareBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean2;
            }
            y(firmwareBean);
        } else if (AppUtils.checkIfLocationPermissionExists(this)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean3 = this.t;
            if (firmwareBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean3;
            }
            y(firmwareBean);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_update_latest_fw);
        Serializable serializableExtra = getIntent().getSerializableExtra(AppConstants.FIRMWARE_BEAN.getValue());
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes.DataBean.FirmwareBean");
        this.t = (SoftwareUpdateRes.DataBean.FirmwareBean) serializableExtra;
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_fw_version);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this.t;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        textView.setText(firmwareBean.getUpdateVersion());
        Object systemService = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        ((BluetoothManager) systemService).getAdapter();
        SessionManager.getInstance(this).getConnectedDeviceMacAddress();
        ((TextView) _$_findCachedViewById(R.id.current_fw_version)).setText(((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision());
        initToolbar();
        initClickListeners();
        initDialogs();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        C();
        if (this.y) {
            S();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
        if (i == this.v) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this.t;
            if (firmwareBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean2;
            }
            y(firmwareBean);
        } else if (i == this.w) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean3 = this.t;
            if (firmwareBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean3;
            }
            y(firmwareBean);
        }
    }

    public final void setUpdateIntitiated(boolean z) {
        this.y = z;
    }

    public final void setUpdatedSuccessfully(boolean z) {
        this.x = z;
    }

    public final void showFWUpdateSuccessDialog() {
        LogHelper.d(this.p, "showFWUpdateSuccessDialog");
        C();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void y(SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        C();
        this.s = 0;
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdatePS1$beginFirmwareDownload$1(this, firmwareBean, null), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0029 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String z(android.net.Uri r7, java.lang.String r8) {
        /*
            r6 = this;
            r0 = 0
            if (r7 == 0) goto Lf
            android.content.ContentResolver r1 = r6.getContentResolver()     // Catch: java.lang.Exception -> Lc
            java.io.InputStream r1 = r1.openInputStream(r7)     // Catch: java.lang.Exception -> Lc
            goto L10
        Lc:
            r1 = move-exception
            r2 = r0
            goto L1c
        Lf:
            r1 = r0
        L10:
            java.lang.String r2 = r6.p     // Catch: java.lang.Exception -> L18
            java.lang.String r3 = "Loaded file using content resolver"
            com.coveiot.utils.utility.LogHelper.i(r2, r3)     // Catch: java.lang.Exception -> L18
            goto L4a
        L18:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L1c:
            java.lang.String r3 = r6.p
            java.lang.String r1 = r1.getMessage()
            java.lang.String r4 = "Failed to open input stream using content resolver, error: %s"
            com.coveiot.utils.utility.LogHelper.e(r3, r4, r1)
            if (r7 == 0) goto L31
            java.lang.String r7 = r7.getPath()     // Catch: java.lang.Exception -> L2e
            goto L32
        L2e:
            r7 = move-exception
            r1 = r2
            goto L3f
        L31:
            r7 = r0
        L32:
            java.io.FileInputStream r1 = r6.openFileInput(r7)     // Catch: java.lang.Exception -> L2e
            java.lang.String r7 = r6.p     // Catch: java.lang.Exception -> L3e
            java.lang.String r2 = "Loaded file using open file input"
            com.coveiot.utils.utility.LogHelper.i(r7, r2)     // Catch: java.lang.Exception -> L3e
            goto L4a
        L3e:
            r7 = move-exception
        L3f:
            java.lang.String r2 = r6.p
            java.lang.String r7 = r7.getMessage()
            java.lang.String r3 = "Failed to open input stream using open file, error: %s"
            com.coveiot.utils.utility.LogHelper.e(r2, r3, r7)
        L4a:
            if (r1 != 0) goto L55
            com.coveiot.android.leonardo.more.activities.dd r7 = new com.coveiot.android.leonardo.more.activities.dd
            r7.<init>()
            r6.runOnUiThread(r7)
            return r0
        L55:
            java.io.File r7 = new java.io.File
            java.io.File r2 = r6.getExternalFilesDir(r0)
            r7.<init>(r2, r8)
            android.content.ContentResolver r8 = r6.getContentResolver()     // Catch: java.lang.Exception -> L80
            android.net.Uri r2 = android.net.Uri.fromFile(r7)     // Catch: java.lang.Exception -> L80
            java.lang.String r3 = "w"
            java.io.OutputStream r8 = r8.openOutputStream(r2, r3)     // Catch: java.lang.Exception -> L80
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L7e
        L70:
            int r3 = r1.read(r2)     // Catch: java.lang.Exception -> L7e
            if (r3 <= 0) goto L8d
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch: java.lang.Exception -> L7e
            r4 = 0
            r8.write(r2, r4, r3)     // Catch: java.lang.Exception -> L7e
            goto L70
        L7e:
            r2 = move-exception
            goto L82
        L80:
            r2 = move-exception
            r8 = r0
        L82:
            java.lang.String r3 = r6.p
            java.lang.String r2 = r2.getMessage()
            java.lang.String r4 = "Failed to open output stream using content resolver, error: %s"
            com.coveiot.utils.utility.LogHelper.e(r3, r4, r2)
        L8d:
            r1.close()     // Catch: java.lang.Exception -> L90
        L90:
            if (r8 != 0) goto L9b
            com.coveiot.android.leonardo.more.activities.cd r7 = new com.coveiot.android.leonardo.more.activities.cd
            r7.<init>()
            r6.runOnUiThread(r7)
            return r0
        L9b:
            r8.close()     // Catch: java.lang.Exception -> L9e
        L9e:
            java.lang.String r7 = r7.getAbsolutePath()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1.z(android.net.Uri, java.lang.String):java.lang.String");
    }
}
