package com.coveiot.android.leonardo.more.activities;

import android.app.Dialog;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
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
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.PreferenceNames;
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
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.htsmart.wristband2.dfu.DfuCallback;
import com.htsmart.wristband2.dfu.DfuManager;
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
public final class ActivityFirmwareUpdateMatrix extends BaseActivity {
    @Nullable
    public DfuManager B;
    public boolean D;
    @Nullable
    public FirmwareSuccessDialog G;
    @Nullable
    public FirmwareFailureDialog H;
    @Nullable
    public ProgressBar q;
    @Nullable
    public TextView r;
    public int s;
    public SoftwareUpdateRes.DataBean.FirmwareBean t;
    @Nullable
    public BluetoothAdapter v;
    public boolean w;
    @Nullable
    public String y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityFirmwareUpdateMatrix";
    @NotNull
    public final Handler u = new Handler();
    @NotNull
    public String x = "dfu";
    public final int z = 101;
    public final int A = 102;
    @NotNull
    public final DfuCallback C = new ActivityFirmwareUpdateMatrix$mDfuCallback$1(this);
    @NotNull
    public ScanCallback E = new ActivityFirmwareUpdateMatrix$scanCallback$1(this);
    @NotNull
    public final Handler F = new Handler() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$loadHandler$1
        @Override // android.os.Handler
        public void handleMessage(@NotNull Message msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            super.handleMessage(msg);
            if (msg.what == 1) {
                ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = ActivityFirmwareUpdateMatrix.this;
                String string = activityFirmwareUpdateMatrix.getString(R.string.ota_mode_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ota_mode_failed)");
                activityFirmwareUpdateMatrix.K(string, "");
            }
        }
    };
    @NotNull
    public final BroadcastReceiver I = new BroadcastReceiver() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$mBluetoothStatusReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (Intrinsics.areEqual(intent.getAction(), "android.bluetooth.adapter.action.STATE_CHANGED") && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) == 10) {
                LogHelper.d(ActivityFirmwareUpdateMatrix.this.getTAG(), "Bluetooth state BluetoothAdapter.STATE_OFF", ModuleNames.BLEABSTRACT.getModuleName());
                ActivityFirmwareUpdateMatrix.this.K("", "");
            }
        }
    };

    /* loaded from: classes5.dex */
    public static final class FirmwareFailureDialog extends DialogFragment {
        @NotNull
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

        public static final void c(FirmwareFailureDialog this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.dismiss();
            FragmentActivity activity = this$0.getActivity();
            if (activity != null) {
                activity.finish();
            }
        }

        public static final void d(FirmwareFailureDialog this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.dismiss();
            FragmentActivity activity = this$0.getActivity();
            if (activity != null) {
                activity.finish();
            }
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

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(@NotNull DialogInterface dialog) {
            Intrinsics.checkNotNullParameter(dialog, "dialog");
            super.onCancel(dialog);
            setStyle(1, R.style.DialogTheme);
        }

        @Override // androidx.fragment.app.Fragment
        @Nullable
        public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(inflater, "inflater");
            return inflater.inflate(R.layout.firmware_failure_popup, viewGroup, false);
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onStart() {
            Window window;
            super.onStart();
            Dialog dialog = getDialog();
            if (dialog == null || (window = dialog.getWindow()) == null) {
                return;
            }
            window.setLayout(-1, -1);
        }

        @Override // androidx.fragment.app.Fragment
        public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(view, "view");
            super.onViewCreated(view, bundle);
            ((Button) _$_findCachedViewById(R.id.btnTryAgain)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.fc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityFirmwareUpdateMatrix.FirmwareFailureDialog.c(ActivityFirmwareUpdateMatrix.FirmwareFailureDialog.this, view2);
                }
            });
            ((TextView) _$_findCachedViewById(R.id.cl_remind_me_later)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.gc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityFirmwareUpdateMatrix.FirmwareFailureDialog.d(ActivityFirmwareUpdateMatrix.FirmwareFailureDialog.this, view2);
                }
            });
        }
    }

    /* loaded from: classes5.dex */
    public static final class FirmwareSuccessDialog extends DialogFragment {
        @NotNull
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

        public static final void b(FirmwareSuccessDialog this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.dismiss();
            FragmentActivity activity = this$0.getActivity();
            if (activity != null) {
                activity.finish();
            }
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

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(@NotNull DialogInterface dialog) {
            Intrinsics.checkNotNullParameter(dialog, "dialog");
            super.onCancel(dialog);
            setStyle(1, R.style.DialogTheme);
        }

        @Override // androidx.fragment.app.Fragment
        @Nullable
        public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(inflater, "inflater");
            return inflater.inflate(R.layout.firmware_success_popup, viewGroup, false);
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
        public void onStart() {
            Window window;
            super.onStart();
            Dialog dialog = getDialog();
            if (dialog == null || (window = dialog.getWindow()) == null) {
                return;
            }
            window.setLayout(-1, -1);
        }

        @Override // androidx.fragment.app.Fragment
        public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(view, "view");
            super.onViewCreated(view, bundle);
            ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.hc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ActivityFirmwareUpdateMatrix.FirmwareSuccessDialog.b(ActivityFirmwareUpdateMatrix.FirmwareSuccessDialog.this, view2);
                }
            });
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$deleteFiles$1", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                File filesDir = ActivityFirmwareUpdateMatrix.this.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateMatrix.filesDir");
                fileUtils.deleteRecursive(fileUtils.getFirmwareFile(filesDir, AppConstants.MATRIX_FIRMWARE_FILE_NAME.getValue()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$onTransferCompleted$2", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                TextView textView = ActivityFirmwareUpdateMatrix.this.r;
                if (textView != null) {
                    textView.setText(ActivityFirmwareUpdateMatrix.this.getString(R.string.upgrading_dot));
                }
                ((TextView) ActivityFirmwareUpdateMatrix.this._$_findCachedViewById(R.id.fw_upgrading_header)).setVisibility(4);
                ((TextView) ActivityFirmwareUpdateMatrix.this._$_findCachedViewById(R.id.tv_fw_upgrade_info)).setText(ActivityFirmwareUpdateMatrix.this.getString(R.string.please_wait_after_fw_update));
                ((ImageView) ActivityFirmwareUpdateMatrix.this._$_findCachedViewById(R.id.fw_upgrading_image)).setImageResource(R.drawable.fw_upgrade_final_configurations);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$onUploadCanceled$1", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ActivityFirmwareUpdateMatrix.this.K("", "");
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$showErrorBottomSheet$1", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ActivityFirmwareUpdateMatrix.this.showFirmwareFailureDialog();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$showFWUpdateSuccessDialog$1", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ActivityFirmwareUpdateMatrix.this.showFirmwareSuccessDialog();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivityFirmwareUpdateMatrix this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void B(ActivityFirmwareUpdateMatrix this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void C(ActivityFirmwareUpdateMatrix this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isGpsEnabled(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.please_check_gps), 0).show();
        } else if (!AppUtils.isBluetoothEnabled(this$0)) {
            Toast.makeText(this$0, (int) R.string.bluetooth_off_message, 0).show();
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
        } else if (AppUtils.checkIfLocationPermissionExists(this$0)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this$0.t;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            this$0.x(firmwareBean);
        } else {
            this$0.I();
        }
    }

    public static final void D(ActivityFirmwareUpdateMatrix this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void E(ActivityFirmwareUpdateMatrix this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.G();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void M(ActivityFirmwareUpdateMatrix this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BluetoothAdapter bluetoothAdapter = this$0.v;
        BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter != null ? bluetoothAdapter.getBluetoothLeScanner() : null;
        Intrinsics.checkNotNull(bluetoothLeScanner);
        bluetoothLeScanner.stopScan(this$0.E);
        LogHelper.i(this$0.p, "startScan: stop timeout");
    }

    public static final void z(ActivityFirmwareUpdateMatrix this$0, View view) {
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

    public final void F() {
        SessionManager.getInstance(this).setForceFirmwareUpdated(true);
        SessionManager.getInstance(this).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(this).deleteSoftwareUpdateResponseBeanString();
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, new ActivityFirmwareUpdateMatrix$onTransferCompleted$1(this));
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
        LogHelper.d(this.p, "onTransferCompleted ");
    }

    public final void G() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
        LogHelper.d(this.p, "onUploadCanceled");
    }

    public final void H() {
        try {
            getApplicationContext().registerReceiver(this.I, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void I() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = ActivityFirmwareUpdateMatrix.this;
                i = activityFirmwareUpdateMatrix.z;
                ActivityCompat.requestPermissions(activityFirmwareUpdateMatrix, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityFirmwareUpdateMatrix.this.L();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
                ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = ActivityFirmwareUpdateMatrix.this;
                firmwareBean = activityFirmwareUpdateMatrix.t;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                activityFirmwareUpdateMatrix.x(firmwareBean);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityFirmwareUpdateMatrix.this.L();
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
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$sendConnectedDeviceInfoToServer$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$sendConnectedDeviceInfoToServer$1$onError$1", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityFirmwareUpdateMatrix this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityFirmwareUpdateMatrix;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
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
                        this.this$0.dismissProgress();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$sendConnectedDeviceInfoToServer$1$onSuccess$1", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityFirmwareUpdateMatrix this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = activityFirmwareUpdateMatrix;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new b(this.this$0, continuation);
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
                        this.this$0.showFWUpdateSuccessDialog();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityFirmwareUpdateMatrix.this), Dispatchers.getMain(), null, new a(ActivityFirmwareUpdateMatrix.this, null), 2, null);
                LogHelper.i(ActivityFirmwareUpdateMatrix.this.getTAG(), "sendConnectedDeviceInfoToServer onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                LogHelper.i(ActivityFirmwareUpdateMatrix.this.getTAG(), "sendConnectedDeviceInfoToServer onSuccess");
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityFirmwareUpdateMatrix.this), Dispatchers.getMain(), null, new b(ActivityFirmwareUpdateMatrix.this, null), 2, null);
            }
        });
    }

    public final void K(String str, String str2) {
        y();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void L() {
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                AppUtils.openAppSettings(this, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void N() {
        try {
            getApplicationContext().unregisterReceiver(this.I);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }

    public final void O() {
        BleApiManager.getInstance(this).getBleApi().getData(new DeviceInfoRequest.Builder().setIsFwVersion(true).build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$updateNewFwVersionToPref$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$updateNewFwVersionToPref$1$onDataError$1", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityFirmwareUpdateMatrix this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityFirmwareUpdateMatrix;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
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
                        this.this$0.showFWUpdateSuccessDialog();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityFirmwareUpdateMatrix.this.getTAG(), "deviceInfoRequest: onDataError");
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityFirmwareUpdateMatrix.this), Dispatchers.getMain(), null, new a(ActivityFirmwareUpdateMatrix.this, null), 2, null);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdateMatrix.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        SessionManager.getInstance(ActivityFirmwareUpdateMatrix.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    LogHelper.d(ActivityFirmwareUpdateMatrix.this.getTAG(), "deviceInfoRequest: onDataResponse");
                    PreferenceManager.savePreference(ActivityFirmwareUpdateMatrix.this, PreferenceNames.TEMPERATURE_CALIBRATION_VAL, Double.valueOf(0.0d));
                    ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = ActivityFirmwareUpdateMatrix.this;
                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo, "bleDeviceInfo");
                    activityFirmwareUpdateMatrix.J(bleDeviceInfo);
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

    @NotNull
    public final String getDfuFilterName() {
        return this.x;
    }

    @Nullable
    public final FirmwareFailureDialog getFirmwareFailureDialog() {
        return this.H;
    }

    @Nullable
    public final FirmwareSuccessDialog getFirmwareSuccessDialog() {
        return this.G;
    }

    @NotNull
    public final ScanCallback getScanCallback() {
        return this.E;
    }

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.bc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMatrix.z(ActivityFirmwareUpdateMatrix.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.zb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMatrix.A(ActivityFirmwareUpdateMatrix.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.remindMeLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.yb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMatrix.B(ActivityFirmwareUpdateMatrix.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ac
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMatrix.C(ActivityFirmwareUpdateMatrix.this, view);
            }
        });
    }

    public final void initDialogs() {
        this.r = (TextView) findViewById(R.id.tv_progress_title);
        this.q = (ProgressBar) findViewById(R.id.progress_update);
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.firmware_update));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.cc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMatrix.D(ActivityFirmwareUpdateMatrix.this, view);
            }
        });
    }

    public final void initiateDFU() {
        File externalCacheDir = getExternalCacheDir();
        String absolutePath = new File(externalCacheDir, AppConstants.MATRIX_FIRMWARE_FILE_NAME.getValue() + ".bin").getAbsolutePath();
        DfuManager dfuManager = this.B;
        Intrinsics.checkNotNull(dfuManager);
        dfuManager.upgradeFirmware(absolutePath);
    }

    public final boolean isConnectedEventReceivedAfterReboot() {
        return this.D;
    }

    public final void logError(int i, int i2) {
        String str = "";
        if (i != 0) {
            if (i == 1) {
                if (i2 != 0) {
                    if (i2 == 1) {
                        str = getResources().getString(R.string.dfu_error_file_not_exist);
                        Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.st…dfu_error_file_not_exist)");
                    } else if (i2 != 3) {
                        if (i2 == 4) {
                            str = getResources().getString(R.string.dfu_error_file_download);
                            Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.st….dfu_error_file_download)");
                        }
                    }
                }
                str = getResources().getString(R.string.dfu_error_file_format);
                Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.st…ng.dfu_error_file_format)");
            } else if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        switch (i2) {
                            case DfuManager.ERROR_CODE_DFU_PROCESS_STARTUP_FAILED /* 2147483645 */:
                            case 2147483646:
                                str = getResources().getString(R.string.dfu_error_service_not_ready);
                                Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.st…_error_service_not_ready)");
                                break;
                        }
                    }
                } else if (i2 == 0) {
                    str = getResources().getString(R.string.dfu_error_scan_failed);
                    Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.st…ng.dfu_error_scan_failed)");
                } else if (i2 == 1) {
                    str = getResources().getString(R.string.dfu_error_device_not_found);
                    Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.st…u_error_device_not_found)");
                }
            } else if (i2 != 0) {
                if (i2 == 2) {
                    str = getResources().getString(R.string.dfu_error_low_battery);
                    Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.st…ng.dfu_error_low_battery)");
                } else if (i2 != 3) {
                    str = getResources().getString(R.string.dfu_error_enter_dfu_failed);
                    Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.st…u_error_enter_dfu_failed)");
                } else {
                    str = getResources().getString(R.string.device_disconnected);
                    Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.string.device_disconnected)");
                }
            }
        } else if (i2 == 0) {
            str = getResources().getString(R.string.ble_not_support);
            Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.string.ble_not_support)");
        } else if (i2 == 1) {
            str = getResources().getString(R.string.bt_not_open);
            Intrinsics.checkNotNullExpressionValue(str, "resources.getString(R.string.bt_not_open)");
        }
        if (str.length() > 0) {
            String str2 = this.p;
            LogHelper.d(str2, "onError message  " + str);
        } else {
            String str3 = this.p;
            LogHelper.d(str3, "onError message" + getString(R.string.fw_update_failure));
        }
        this.u.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ec
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateMatrix.E(ActivityFirmwareUpdateMatrix.this);
            }
        }, 200L);
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
            x(firmwareBean);
        } else if (AppUtils.checkIfLocationPermissionExists(this)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean3 = this.t;
            if (firmwareBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean3;
            }
            x(firmwareBean);
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
        this.v = ((BluetoothManager) systemService).getAdapter();
        this.y = SessionManager.getInstance(this).getConnectedDeviceMacAddress();
        ((TextView) _$_findCachedViewById(R.id.current_fw_version)).setText(((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision());
        initToolbar();
        initClickListeners();
        initDialogs();
        H();
        DfuManager dfuManager = new DfuManager(this);
        this.B = dfuManager;
        Intrinsics.checkNotNull(dfuManager);
        dfuManager.setDfuCallback(this.C);
        DfuManager dfuManager2 = this.B;
        Intrinsics.checkNotNull(dfuManager2);
        dfuManager2.init();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        y();
        DfuManager dfuManager = this.B;
        Intrinsics.checkNotNull(dfuManager);
        dfuManager.release();
        N();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
        if (i == this.z) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this.t;
            if (firmwareBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean2;
            }
            x(firmwareBean);
        } else if (i == this.A) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean3 = this.t;
            if (firmwareBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean3;
            }
            x(firmwareBean);
        }
    }

    public final void setConnectedEventReceivedAfterReboot(boolean z) {
        this.D = z;
    }

    public final void setDfuFilterName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.x = str;
    }

    public final void setFirmwareFailureDialog(@Nullable FirmwareFailureDialog firmwareFailureDialog) {
        this.H = firmwareFailureDialog;
    }

    public final void setFirmwareSuccessDialog(@Nullable FirmwareSuccessDialog firmwareSuccessDialog) {
        this.G = firmwareSuccessDialog;
    }

    public final void setScanCallback(@NotNull ScanCallback scanCallback) {
        Intrinsics.checkNotNullParameter(scanCallback, "<set-?>");
        this.E = scanCallback;
    }

    public final void showFWUpdateSuccessDialog() {
        y();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(null), 2, null);
    }

    public final void showFirmwareFailureDialog() {
        try {
            ((TextView) findViewById(R.id.toolbar_title)).setVisibility(8);
            ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
            ((LinearLayout) _$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
            ((ConstraintLayout) _$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void showFirmwareSuccessDialog() {
        try {
            ((TextView) findViewById(R.id.toolbar_title)).setVisibility(8);
            ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
            ((ConstraintLayout) _$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void startScan(boolean z) {
        if (z) {
            this.u.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.dc
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateMatrix.M(ActivityFirmwareUpdateMatrix.this);
                }
            }, 20000L);
            LogHelper.i(this.p, "startScan: start");
            BluetoothAdapter bluetoothAdapter = this.v;
            BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter != null ? bluetoothAdapter.getBluetoothLeScanner() : null;
            Intrinsics.checkNotNull(bluetoothLeScanner);
            bluetoothLeScanner.startScan(this.E);
            return;
        }
        LogHelper.i(this.p, "startScan: stop");
        BluetoothAdapter bluetoothAdapter2 = this.v;
        BluetoothLeScanner bluetoothLeScanner2 = bluetoothAdapter2 != null ? bluetoothAdapter2.getBluetoothLeScanner() : null;
        Intrinsics.checkNotNull(bluetoothLeScanner2);
        bluetoothLeScanner2.stopScan(this.E);
        this.u.removeCallbacksAndMessages(null);
    }

    public final void x(SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        y();
        this.s = 0;
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdateMatrix$beginFirmwareDownload$1(this, firmwareBean, null), 2, null);
    }

    public final void y() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }
}
