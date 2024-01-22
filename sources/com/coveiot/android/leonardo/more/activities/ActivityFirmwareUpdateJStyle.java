package com.coveiot.android.leonardo.more.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
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
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle;
import com.coveiot.android.leonardo.service.DfuService;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
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
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateJStyle extends BaseActivity {
    @Nullable
    public BottomSheetDialogImageTitleMessage C;
    @Nullable
    public ProgressBar q;
    @Nullable
    public TextView r;
    public int s;
    public BottomSheetDialog t;
    public SoftwareUpdateRes.DataBean.FirmwareBean u;
    @Nullable
    public BluetoothAdapter w;
    public boolean x;
    @Nullable
    public String z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityFirmwareUpdateJStyle";
    @NotNull
    public final Handler v = new Handler();
    @NotNull
    public String y = "dfu";
    public final int A = 101;
    @NotNull
    public final ActivityFirmwareUpdateJStyle$mDfuProgressListener$1 B = new ActivityFirmwareUpdateJStyle$mDfuProgressListener$1(this);
    @NotNull
    public ScanCallback D = new ActivityFirmwareUpdateJStyle$scanCallback$1(this);
    @NotNull
    public final Handler E = new Handler() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$loadHandler$1
        @Override // android.os.Handler
        public void handleMessage(@NotNull Message msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            super.handleMessage(msg);
            if (msg.what == 1) {
                ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = ActivityFirmwareUpdateJStyle.this;
                String string = activityFirmwareUpdateJStyle.getString(R.string.ota_mode_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ota_mode_failed)");
                activityFirmwareUpdateJStyle.B(string, "", false);
            }
        }
    };

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$deleteFiles$1", f = "ActivityFirmwareUpdateJStyle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                File filesDir = ActivityFirmwareUpdateJStyle.this.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateJStyle.filesDir");
                fileUtils.deleteRecursive(fileUtils.getFirmwareFile(filesDir, AppConstants.JSTYLE_FIRMWARE_FILE_NAME.getValue()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$onUploadCanceled$1", f = "ActivityFirmwareUpdateJStyle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                Toast.makeText(ActivityFirmwareUpdateJStyle.this, "Upload canceled", 0).show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$showErrorBottomSheet$1", f = "ActivityFirmwareUpdateJStyle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isContainMessagesOnly;
        public final /* synthetic */ String $message;
        public final /* synthetic */ String $title;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String str, String str2, boolean z, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$title = str;
            this.$message = str2;
            this.$isContainMessagesOnly = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$title, this.$message, this.$isContainMessagesOnly, continuation);
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
                BottomSheetDialog bottomSheetDialog = ActivityFirmwareUpdateJStyle.this.t;
                if (bottomSheetDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                    bottomSheetDialog = null;
                }
                bottomSheetDialog.dismiss();
                ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = ActivityFirmwareUpdateJStyle.this;
                Drawable drawable = activityFirmwareUpdateJStyle.getResources().getDrawable(R.drawable.failure_round_icon);
                Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…wable.failure_round_icon)");
                final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(activityFirmwareUpdateJStyle, drawable, this.$title, this.$message, this.$isContainMessagesOnly);
                String string = ActivityFirmwareUpdateJStyle.this.getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
                bottomSheetDialogImageTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.db
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BottomSheetDialogImageTitleMessage.this.dismiss();
                    }
                });
                bottomSheetDialogImageTitleMessage.show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$showFWUpdateSuccessDialog$1", f = "ActivityFirmwareUpdateJStyle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle, View view) {
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = activityFirmwareUpdateJStyle.getBottomSheetDialogImageTitleMessage();
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            bottomSheetDialogImageTitleMessage.dismiss();
            activityFirmwareUpdateJStyle.finish();
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
                BottomSheetDialog bottomSheetDialog = ActivityFirmwareUpdateJStyle.this.t;
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
                if (bottomSheetDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                    bottomSheetDialog = null;
                }
                bottomSheetDialog.dismiss();
                if (ActivityFirmwareUpdateJStyle.this.getBottomSheetDialogImageTitleMessage() == null) {
                    ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = ActivityFirmwareUpdateJStyle.this;
                    Drawable drawable = activityFirmwareUpdateJStyle.getResources().getDrawable(R.drawable.success_fw_icon);
                    Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.success_fw_icon)");
                    ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle2 = ActivityFirmwareUpdateJStyle.this;
                    Object[] objArr = new Object[1];
                    SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = activityFirmwareUpdateJStyle2.u;
                    if (firmwareBean2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    } else {
                        firmwareBean = firmwareBean2;
                    }
                    objArr[0] = firmwareBean.getUpdateVersion();
                    String string = activityFirmwareUpdateJStyle2.getString(R.string.fw_update_success, objArr);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
                    activityFirmwareUpdateJStyle.setBottomSheetDialogImageTitleMessage(new BottomSheetDialogImageTitleMessage(activityFirmwareUpdateJStyle, drawable, string, "", true));
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = ActivityFirmwareUpdateJStyle.this.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                bottomSheetDialogImageTitleMessage.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = ActivityFirmwareUpdateJStyle.this.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                String string2 = ActivityFirmwareUpdateJStyle.this.getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.leaderboard.R.string.ok)");
                final ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle3 = ActivityFirmwareUpdateJStyle.this;
                bottomSheetDialogImageTitleMessage2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.eb
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityFirmwareUpdateJStyle.d.invokeSuspend$lambda$0(ActivityFirmwareUpdateJStyle.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = ActivityFirmwareUpdateJStyle.this.getBottomSheetDialogImageTitleMessage();
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                if (!bottomSheetDialogImageTitleMessage3.isShowing()) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = ActivityFirmwareUpdateJStyle.this.getBottomSheetDialogImageTitleMessage();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                    bottomSheetDialogImageTitleMessage4.show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void D(ActivityFirmwareUpdateJStyle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BluetoothAdapter bluetoothAdapter = this$0.w;
        BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter != null ? bluetoothAdapter.getBluetoothLeScanner() : null;
        Intrinsics.checkNotNull(bluetoothLeScanner);
        bluetoothLeScanner.stopScan(this$0.D);
        LogHelper.i(this$0.p, "startScan: stop timeout");
    }

    public static final void v(ActivityFirmwareUpdateJStyle this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (!AppUtils.isGpsEnabled(this$0)) {
                Toast.makeText(this$0, this$0.getString(R.string.please_check_gps), 0).show();
                return;
            } else if (!AppUtils.isNetConnected(this$0)) {
                Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
                return;
            } else if (AppUtils.checkIfLocationPermissionExists(this$0)) {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this$0.u;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                this$0.t(firmwareBean);
                return;
            } else {
                this$0.z();
                return;
            }
        }
        String string = this$0.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
        String string2 = this$0.getString(R.string.connect_your_device_desc);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.connect_your_device_desc)");
        this$0.B(string, string2, false);
    }

    public static final void w(ActivityFirmwareUpdateJStyle this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void A(BleDeviceInfo bleDeviceInfo) {
        IOTUserDeviceReq iOTUserDeviceReq = new IOTUserDeviceReq(bleDeviceInfo);
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported()) {
            String str = bleDeviceInfo.getmDeviceName();
            Intrinsics.checkNotNullExpressionValue(str, "bleDeviceInfo.getmDeviceName()");
            String lowerCase = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            iOTUserDeviceReq.setAppTrackerId(lowerCase);
        }
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$sendConnectedDeviceInfoToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ActivityFirmwareUpdateJStyle.this.dismissProgress();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                ActivityFirmwareUpdateJStyle.this.showFWUpdateSuccessDialog();
            }
        });
    }

    public final void B(String str, String str2, boolean z) {
        u();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(str, str2, z, null), 2, null);
    }

    public final void C() {
        Intrinsics.checkNotNull(this);
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this;
                Intrinsics.checkNotNull(activityFirmwareUpdateJStyle);
                AppUtils.openAppSettings(activityFirmwareUpdateJStyle, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void E() {
        BleApiManager.getInstance(this).getBleApi().getData(new DeviceInfoRequest.Builder().setIsFwVersion(true).build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$updateNewFwVersionToPref$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityFirmwareUpdateJStyle.this.getTAG(), "deviceInfoRequest: onDataError");
                ActivityFirmwareUpdateJStyle.this.showFWUpdateSuccessDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdateJStyle.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        SessionManager.getInstance(ActivityFirmwareUpdateJStyle.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    LogHelper.d(ActivityFirmwareUpdateJStyle.this.getTAG(), "deviceInfoRequest: onDataResponse");
                    PreferenceManager.savePreference(ActivityFirmwareUpdateJStyle.this, PreferenceNames.TEMPERATURE_CALIBRATION_VAL, Double.valueOf(0.0d));
                    ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = ActivityFirmwareUpdateJStyle.this;
                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo, "bleDeviceInfo");
                    activityFirmwareUpdateJStyle.A(bleDeviceInfo);
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

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessage() {
        return this.C;
    }

    @NotNull
    public final String getDfuFilterName() {
        return this.y;
    }

    @NotNull
    public final ScanCallback getScanCallback() {
        return this.D;
    }

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ua
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateJStyle.v(ActivityFirmwareUpdateJStyle.this, view);
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
            bottomSheetDialog4 = null;
        }
        bottomSheetDialog4.setCancelable(false);
        BottomSheetDialog bottomSheetDialog5 = this.t;
        if (bottomSheetDialog5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
            bottomSheetDialog5 = null;
        }
        this.r = (TextView) bottomSheetDialog5.findViewById(R.id.tv_progress_title);
        BottomSheetDialog bottomSheetDialog6 = this.t;
        if (bottomSheetDialog6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
        } else {
            bottomSheetDialog3 = bottomSheetDialog6;
        }
        this.q = (ProgressBar) bottomSheetDialog3.findViewById(R.id.progress_update);
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.firmware_update));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.va
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateJStyle.w(ActivityFirmwareUpdateJStyle.this, view);
            }
        });
    }

    public final void initiateDFU(@NotNull String name, @NotNull String address) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        if (Build.VERSION.SDK_INT >= 26) {
            DfuServiceInitiator.createDfuNotificationChannel(this);
        }
        DfuServiceInitiator unsafeExperimentalButtonlessServiceInSecureDfuEnabled = new DfuServiceInitiator(address).setDeviceName(name).setKeepBond(false).setForceDfu(false).setPacketsReceiptNotificationsEnabled(true).setPacketsReceiptNotificationsValue(12).setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(true);
        FileUtils fileUtils = FileUtils.INSTANCE;
        File filesDir = getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateJStyle.filesDir");
        File firmwareFile = fileUtils.getFirmwareFile(filesDir, AppConstants.JSTYLE_FIRMWARE_FILE_NAME.getValue());
        unsafeExperimentalButtonlessServiceInSecureDfuEnabled.setZip(null, firmwareFile != null ? firmwareFile.getAbsolutePath() : null);
        unsafeExperimentalButtonlessServiceInSecureDfuEnabled.start(this, DfuService.class);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 121 && AppUtils.checkIfLocationPermissionExists(this)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this.u;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            t(firmwareBean);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
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
        Object systemService = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        this.w = ((BluetoothManager) systemService).getAdapter();
        this.z = SessionManager.getInstance(this).getConnectedDeviceMacAddress();
        initToolbar();
        initClickListeners();
        initDialogs();
        DfuServiceListenerHelper.registerProgressListener(this, this.B);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        u();
        DfuServiceListenerHelper.unregisterProgressListener(this, this.B);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.A && AppUtils.checkIfLocationPermissionExists(this)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this.u;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            t(firmwareBean);
        }
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.C = bottomSheetDialogImageTitleMessage;
    }

    public final void setDfuFilterName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.y = str;
    }

    public final void setScanCallback(@NotNull ScanCallback scanCallback) {
        Intrinsics.checkNotNullParameter(scanCallback, "<set-?>");
        this.D = scanCallback;
    }

    public final void showFWUpdateSuccessDialog() {
        u();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void startScan(boolean z) {
        if (z) {
            this.v.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.wa
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateJStyle.D(ActivityFirmwareUpdateJStyle.this);
                }
            }, 20000L);
            LogHelper.i(this.p, "startScan: start");
            BluetoothAdapter bluetoothAdapter = this.w;
            BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter != null ? bluetoothAdapter.getBluetoothLeScanner() : null;
            Intrinsics.checkNotNull(bluetoothLeScanner);
            bluetoothLeScanner.startScan(this.D);
            return;
        }
        LogHelper.i(this.p, "startScan: stop");
        BluetoothAdapter bluetoothAdapter2 = this.w;
        BluetoothLeScanner bluetoothLeScanner2 = bluetoothAdapter2 != null ? bluetoothAdapter2.getBluetoothLeScanner() : null;
        Intrinsics.checkNotNull(bluetoothLeScanner2);
        bluetoothLeScanner2.stopScan(this.D);
        this.v.removeCallbacksAndMessages(null);
    }

    public final void t(SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        u();
        this.s = 0;
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1(this, firmwareBean, null), 2, null);
    }

    public final void u() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public final void x() {
        SessionManager.getInstance(this).setForceFirmwareUpdated(true);
        SessionManager.getInstance(this).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(this).deleteSoftwareUpdateResponseBeanString();
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, new ActivityFirmwareUpdateJStyle$onTransferCompleted$1(this));
        LogHelper.d(this.p, "onTransferCompleted ");
    }

    public final void y() {
        LogHelper.d(this.p, "onUploadCanceled");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
    }

    public final void z() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = ActivityFirmwareUpdateJStyle.this;
                i = activityFirmwareUpdateJStyle.A;
                ActivityCompat.requestPermissions(activityFirmwareUpdateJStyle, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityFirmwareUpdateJStyle.this.C();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = ActivityFirmwareUpdateJStyle.this;
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = activityFirmwareUpdateJStyle.u;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                activityFirmwareUpdateJStyle.t(firmwareBean);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityFirmwareUpdateJStyle.this.C();
            }
        });
    }
}
