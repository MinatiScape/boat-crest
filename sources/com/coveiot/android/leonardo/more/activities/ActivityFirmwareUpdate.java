package com.coveiot.android.leonardo.more.activities;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppSessionManager;
import com.coveiot.android.leonardo.utils.DFUUtils;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.android.leonardo.utils.FirmwareUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
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
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdate extends BaseActivity {
    public BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageDFUFailure;
    public BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessageError;
    public BluetoothLeScanner q;
    public boolean r;
    @Nullable
    public ProgressBar t;
    @Nullable
    public TextView u;
    public int v;
    public int w;
    public BottomSheetDialog x;
    public SoftwareUpdateRes.DataBean.FirmwareBean y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final long p = 30000;
    @NotNull
    public final String s = "ActivityFirmwareUpdate";
    @NotNull
    public final Handler z = new Handler();
    public final int A = 101;
    @NotNull
    public final ScanCallback B = new ScanCallback() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$leScanCallback$1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, @NotNull ScanResult result) {
            String y;
            Handler handler;
            String y2;
            Intrinsics.checkNotNullParameter(result, "result");
            super.onScanResult(i, result);
            String address = result.getDevice().getAddress();
            y = ActivityFirmwareUpdate.this.y();
            if (Intrinsics.areEqual(address, y)) {
                handler = ActivityFirmwareUpdate.this.z;
                handler.removeCallbacksAndMessages(null);
                FirmwareUtils firmwareUtils = FirmwareUtils.INSTANCE;
                y2 = ActivityFirmwareUpdate.this.y();
                firmwareUtils.initiateDFU(y2, "DfuTarg", ActivityFirmwareUpdate.this);
            }
        }
    };
    @NotNull
    public final ActivityFirmwareUpdate$mDfuProgressListener$1 C = new ActivityFirmwareUpdate$mDfuProgressListener$1(this);

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$deleteFiles$1", f = "ActivityFirmwareUpdate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                File filesDir = ActivityFirmwareUpdate.this.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdate.filesDir");
                fileUtils.deleteRecursive(fileUtils.getFirmwareFile(filesDir, AppConstants.LEONARDO_FIRMWARE_FILE_NAME.getValue()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$onUploadCanceled$1", f = "ActivityFirmwareUpdate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                Toast.makeText(ActivityFirmwareUpdate.this, "Upload canceled", 0).show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$showErrorBottomSheet$1", f = "ActivityFirmwareUpdate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ((TextView) ActivityFirmwareUpdate.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdate.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdate.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdate.this._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$showErrorBottomSheetDFUFailure$1", f = "ActivityFirmwareUpdate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isContainsOnlyMessages;
        public final /* synthetic */ String $message;
        public final /* synthetic */ String $title;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(String str, String str2, boolean z, Continuation<? super d> continuation) {
            super(2, continuation);
            this.$title = str;
            this.$message = str2;
            this.$isContainsOnlyMessages = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(ActivityFirmwareUpdate activityFirmwareUpdate, View view) {
            activityFirmwareUpdate.startActivity(new Intent(activityFirmwareUpdate, ActivityFirmwareRestore.class));
            activityFirmwareUpdate.getBottomSheetDialogImageTitleMessageDFUFailure().dismiss();
            activityFirmwareUpdate.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(ActivityFirmwareUpdate activityFirmwareUpdate, View view) {
            activityFirmwareUpdate.getBottomSheetDialogImageTitleMessageDFUFailure().dismiss();
            activityFirmwareUpdate.finish();
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.$title, this.$message, this.$isContainsOnlyMessages, continuation);
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
                BottomSheetDialog bottomSheetDialog = ActivityFirmwareUpdate.this.x;
                if (bottomSheetDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                    bottomSheetDialog = null;
                }
                bottomSheetDialog.dismiss();
                ActivityFirmwareUpdate activityFirmwareUpdate = ActivityFirmwareUpdate.this;
                if (activityFirmwareUpdate.bottomSheetDialogImageTitleMessageDFUFailure == null) {
                    Drawable drawable = activityFirmwareUpdate.getResources().getDrawable(R.drawable.failure_round_icon);
                    Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…wable.failure_round_icon)");
                    activityFirmwareUpdate.setBottomSheetDialogImageTitleMessageDFUFailure(new BottomSheetDialogImageTitleMessageTwoBtns(activityFirmwareUpdate, drawable, this.$title, this.$message, this.$isContainsOnlyMessages));
                    BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageDFUFailure = ActivityFirmwareUpdate.this.getBottomSheetDialogImageTitleMessageDFUFailure();
                    String string = ActivityFirmwareUpdate.this.getString(R.string.recover_now);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.recover_now)");
                    final ActivityFirmwareUpdate activityFirmwareUpdate2 = ActivityFirmwareUpdate.this;
                    bottomSheetDialogImageTitleMessageDFUFailure.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ea
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityFirmwareUpdate.d.invokeSuspend$lambda$0(ActivityFirmwareUpdate.this, view);
                        }
                    });
                    BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageDFUFailure2 = ActivityFirmwareUpdate.this.getBottomSheetDialogImageTitleMessageDFUFailure();
                    String string2 = ActivityFirmwareUpdate.this.getString(R.string.recover_later);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.recover_later)");
                    final ActivityFirmwareUpdate activityFirmwareUpdate3 = ActivityFirmwareUpdate.this;
                    bottomSheetDialogImageTitleMessageDFUFailure2.setNegativeButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.da
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityFirmwareUpdate.d.invokeSuspend$lambda$1(ActivityFirmwareUpdate.this, view);
                        }
                    });
                }
                if (!ActivityFirmwareUpdate.this.getBottomSheetDialogImageTitleMessageDFUFailure().isShowing()) {
                    ActivityFirmwareUpdate.this.getBottomSheetDialogImageTitleMessageDFUFailure().show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$showFWUpdateSuccessDialog$1", f = "ActivityFirmwareUpdate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ((TextView) ActivityFirmwareUpdate.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdate.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdate.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdate.this._$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivityFirmwareUpdate this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void B(ActivityFirmwareUpdate this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void C(ActivityFirmwareUpdate this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (!AppUtils.isGpsEnabled(this$0)) {
                Toast.makeText(this$0, this$0.getString(R.string.please_check_gps), 0).show();
                return;
            } else if (!AppUtils.isNetConnected(this$0)) {
                Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
                return;
            } else if (AppUtils.checkIfLocationPermissionExists(this$0)) {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this$0.y;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                this$0.w(firmwareBean);
                return;
            } else {
                this$0.G();
                return;
            }
        }
        Object systemService = this$0.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        if (((BluetoothManager) systemService).getAdapter().isEnabled()) {
            if (AppSessionManager.getInstance(this$0).isDfuFailed()) {
                this$0.H();
                return;
            }
            String string = this$0.getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
            String string2 = this$0.getString(R.string.connect_your_device_desc);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.connect_your_device_desc)");
            this$0.K(string, string2);
            return;
        }
        Toast.makeText(this$0, this$0.getString(R.string.bluetooth_off_message), 0).show();
    }

    public static final void D(ActivityFirmwareUpdate this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void I(ActivityFirmwareUpdate this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = false;
        BluetoothLeScanner bluetoothLeScanner = this$0.q;
        BottomSheetDialog bottomSheetDialog = null;
        if (bluetoothLeScanner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bluetoothLeScanner");
            bluetoothLeScanner = null;
        }
        bluetoothLeScanner.stopScan(this$0.B);
        int i = this$0.w;
        if (i < 3) {
            this$0.w = i + 1;
            this$0.H();
            return;
        }
        if (!this$0.isFinishing()) {
            if (this$0.x == null) {
                Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
            }
            BottomSheetDialog bottomSheetDialog2 = this$0.x;
            if (bottomSheetDialog2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                bottomSheetDialog2 = null;
            }
            if (bottomSheetDialog2.isShowing()) {
                BottomSheetDialog bottomSheetDialog3 = this$0.x;
                if (bottomSheetDialog3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                } else {
                    bottomSheetDialog = bottomSheetDialog3;
                }
                bottomSheetDialog.dismiss();
            }
        }
        Toast.makeText(this$0, (int) R.string.unable_to_find_your_device, 0).show();
    }

    public static final void z(ActivityFirmwareUpdate this$0, View view) {
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

    public final void E() {
        SessionManager.getInstance(this).setForceFirmwareUpdated(true);
        SessionManager.getInstance(this).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(this).deleteSoftwareUpdateResponseBeanString();
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, new ActivityFirmwareUpdate$onTransferCompleted$1(this));
        TextView textView = this.u;
        if (textView != null) {
            textView.setText(getString(R.string.upgrading_dot));
        }
        ((TextView) _$_findCachedViewById(R.id.fw_upgrading_header)).setVisibility(4);
        ((TextView) _$_findCachedViewById(R.id.tv_fw_upgrade_info)).setText(getString(R.string.please_wait_after_fw_update));
        ((ImageView) _$_findCachedViewById(R.id.fw_upgrading_image)).setImageResource(R.drawable.fw_upgrade_final_configurations);
    }

    public final void F() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
    }

    public final void G() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                ActivityFirmwareUpdate activityFirmwareUpdate = ActivityFirmwareUpdate.this;
                i = activityFirmwareUpdate.A;
                ActivityCompat.requestPermissions(activityFirmwareUpdate, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityFirmwareUpdate.this.M();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
                ActivityFirmwareUpdate activityFirmwareUpdate = ActivityFirmwareUpdate.this;
                firmwareBean = activityFirmwareUpdate.y;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                activityFirmwareUpdate.w(firmwareBean);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityFirmwareUpdate.this.M();
            }
        });
    }

    @SuppressLint({"MissingPermission"})
    public final void H() {
        BluetoothLeScanner bluetoothLeScanner = null;
        if (!this.r) {
            this.z.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.x9
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdate.I(ActivityFirmwareUpdate.this);
                }
            }, this.p);
            this.r = true;
            if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                return;
            }
            if (!isFinishing()) {
                if (this.x == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                }
                BottomSheetDialog bottomSheetDialog = this.x;
                if (bottomSheetDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                    bottomSheetDialog = null;
                }
                if (!bottomSheetDialog.isShowing()) {
                    BottomSheetDialog bottomSheetDialog2 = this.x;
                    if (bottomSheetDialog2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                        bottomSheetDialog2 = null;
                    }
                    bottomSheetDialog2.show();
                    TextView textView = this.u;
                    if (textView != null) {
                        textView.setText(getString(R.string.searching_for_device));
                    }
                }
            }
            BluetoothLeScanner bluetoothLeScanner2 = this.q;
            if (bluetoothLeScanner2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bluetoothLeScanner");
            } else {
                bluetoothLeScanner = bluetoothLeScanner2;
            }
            bluetoothLeScanner.startScan(this.B);
            return;
        }
        this.r = false;
        BluetoothLeScanner bluetoothLeScanner3 = this.q;
        if (bluetoothLeScanner3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bluetoothLeScanner");
        } else {
            bluetoothLeScanner = bluetoothLeScanner3;
        }
        bluetoothLeScanner.stopScan(this.B);
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
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$sendConnectedDeviceInfoToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ActivityFirmwareUpdate.this.dismissProgress();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                ActivityFirmwareUpdate.this.showFWUpdateSuccessDialog();
            }
        });
    }

    public final void K(String str, String str2) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
    }

    public final void L(String str, String str2, boolean z) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(str, str2, z, null), 2, null);
    }

    public final void M() {
        Intrinsics.checkNotNull(this);
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                ActivityFirmwareUpdate activityFirmwareUpdate = this;
                Intrinsics.checkNotNull(activityFirmwareUpdate);
                AppUtils.openAppSettings(activityFirmwareUpdate, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void N() {
        BleApiManager.getInstance(this).getBleApi().getData(new DeviceInfoRequest.Builder().setIsFwVersion(true).build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate$updateNewFwVersionToPref$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityFirmwareUpdate.this.getTAG(), "FW update: onDataError");
                ActivityFirmwareUpdate.this.showFWUpdateSuccessDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityFirmwareUpdate.this.getTAG(), "FW update: onDataResponse");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdate.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdate.this).saveDeviceVersionNumber(deviceInfo.getFwVersion());
                        AppUtils.isEmpty(deviceInfo.getFwVersion());
                        SessionManager.getInstance(ActivityFirmwareUpdate.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    if (bleDeviceInfo == null || bleDeviceInfo.getSerialNumber() == null || bleDeviceInfo.getmManufacturerName() == null || bleDeviceInfo.getmModelNumber() == null || bleDeviceInfo.getFirmwareRevision() == null || bleDeviceInfo.getHwRevision() == null) {
                        return;
                    }
                    ActivityFirmwareUpdate.this.J(bleDeviceInfo);
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
    public final BottomSheetDialogImageTitleMessageTwoBtns getBottomSheetDialogImageTitleMessageDFUFailure() {
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this.bottomSheetDialogImageTitleMessageDFUFailure;
        if (bottomSheetDialogImageTitleMessageTwoBtns != null) {
            return bottomSheetDialogImageTitleMessageTwoBtns;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogImageTitleMessageDFUFailure");
        return null;
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
    public final String getTAG() {
        return this.s;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.w9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdate.z(ActivityFirmwareUpdate.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdate.A(ActivityFirmwareUpdate.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.remindMeLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdate.B(ActivityFirmwareUpdate.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdate.C(ActivityFirmwareUpdate.this, view);
            }
        });
    }

    public final void initDialogs() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.DialogThemeBottomSheet);
        this.x = bottomSheetDialog;
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.requestFeature(1);
        BottomSheetDialog bottomSheetDialog2 = this.x;
        BottomSheetDialog bottomSheetDialog3 = null;
        if (bottomSheetDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
            bottomSheetDialog2 = null;
        }
        bottomSheetDialog2.setContentView(R.layout.dialog_updating_fw);
        BottomSheetDialog bottomSheetDialog4 = this.x;
        if (bottomSheetDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
        } else {
            bottomSheetDialog3 = bottomSheetDialog4;
        }
        bottomSheetDialog3.setCancelable(false);
        this.u = (TextView) findViewById(R.id.tv_progress_title);
        this.t = (ProgressBar) findViewById(R.id.progress_update);
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.firmware_update));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.s9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdate.D(ActivityFirmwareUpdate.this, view);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 121 && AppUtils.checkIfLocationPermissionExists(this)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this.y;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            w(firmwareBean);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_update_latest_fw);
        if (getIntent() != null) {
            Intent intent = getIntent();
            if ((intent != null ? intent.getSerializableExtra(AppConstants.FIRMWARE_BEAN.getValue()) : null) != null) {
                Intent intent2 = getIntent();
                Serializable serializableExtra = intent2 != null ? intent2.getSerializableExtra(AppConstants.FIRMWARE_BEAN.getValue()) : null;
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = serializableExtra instanceof SoftwareUpdateRes.DataBean.FirmwareBean ? (SoftwareUpdateRes.DataBean.FirmwareBean) serializableExtra : null;
                Intrinsics.checkNotNull(firmwareBean);
                this.y = firmwareBean;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                }
                TextView textView = (TextView) _$_findCachedViewById(R.id.tv_fw_version);
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this.y;
                if (firmwareBean2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean2 = null;
                }
                textView.setText(firmwareBean2.getUpdateVersion());
                ((TextView) _$_findCachedViewById(R.id.current_fw_version)).setText(((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision());
                Object systemService = getSystemService("bluetooth");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
                BluetoothAdapter adapter = ((BluetoothManager) systemService).getAdapter();
                BluetoothLeScanner bluetoothLeScanner = adapter != null ? adapter.getBluetoothLeScanner() : null;
                Intrinsics.checkNotNull(bluetoothLeScanner);
                this.q = bluetoothLeScanner;
                initToolbar();
                initClickListeners();
                initDialogs();
                DfuServiceListenerHelper.registerProgressListener(this, this.C);
                return;
            }
        }
        Toast.makeText(this, (int) R.string.some_thing_went_wrong, 0).show();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        DfuServiceListenerHelper.unregisterProgressListener(this, this.C);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.A && AppUtils.checkIfLocationPermissionExists(this)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this.y;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            w(firmwareBean);
        }
    }

    public final void setBottomSheetDialogImageTitleMessageDFUFailure(@NotNull BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessageTwoBtns, "<set-?>");
        this.bottomSheetDialogImageTitleMessageDFUFailure = bottomSheetDialogImageTitleMessageTwoBtns;
    }

    public final void setBottomSheetDialogImageTitleMessageError(@NotNull BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "<set-?>");
        this.bottomSheetDialogImageTitleMessageError = bottomSheetDialogImageTitleMessage;
    }

    public final void showFWUpdateSuccessDialog() {
        x();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(null), 2, null);
    }

    public final void w(SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        x();
        this.v = 0;
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdate$beginFirmwareDownload$1(this, firmwareBean, null), 2, null);
    }

    public final void x() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public final String y() {
        String generateDfuTargetMacAddress = DFUUtils.generateDfuTargetMacAddress(SessionManager.getInstance(this).getConnectedDeviceMacAddress());
        Intrinsics.checkNotNullExpressionValue(generateDfuTargetMacAddress, "generateDfuTargetMacAddress(address)");
        return generateDfuTargetMacAddress;
    }
}
