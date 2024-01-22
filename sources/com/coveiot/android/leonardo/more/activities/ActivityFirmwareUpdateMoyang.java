package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateMoyang extends BaseActivity {
    @Nullable
    public ProgressBar q;
    @Nullable
    public TextView r;
    public int s;
    public BottomSheetDialog t;
    public SoftwareUpdateRes.DataBean.FirmwareBean u;
    @Nullable
    public BottomSheetDialogImageTitleMessage y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityFirmwareUpdateMoyang";
    @NotNull
    public final Handler v = new Handler(Looper.getMainLooper());
    public final int w = 101;
    @NotNull
    public final CoroutineScope x = CoroutineScopeKt.MainScope();

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$deleteFiles$1", f = "ActivityFirmwareUpdateMoyang.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                File filesDir = ActivityFirmwareUpdateMoyang.this.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateMoyang.filesDir");
                fileUtils.deleteRecursive(fileUtils.getFirmwareFile(filesDir, AppConstants.MOYANG_FIRMWARE_FILE_NAME.getValue()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$onUploadCanceled$1", f = "ActivityFirmwareUpdateMoyang.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                Toast.makeText(ActivityFirmwareUpdateMoyang.this, "Upload canceled", 0).show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$showErrorBottomSheet$1", f = "ActivityFirmwareUpdateMoyang.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ((TextView) ActivityFirmwareUpdateMoyang.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdateMoyang.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdateMoyang.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdateMoyang.this._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$showFWUpdateSuccessDialog$1", f = "ActivityFirmwareUpdateMoyang.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ((TextView) ActivityFirmwareUpdateMoyang.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdateMoyang.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdateMoyang.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdateMoyang.this._$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivityFirmwareUpdateMoyang this$0, View view) {
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
                this$0.v(firmwareBean);
                return;
            } else {
                this$0.E();
                return;
            }
        }
        String string = this$0.getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
        String string2 = this$0.getString(R.string.connect_your_device_desc);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.connect_your_device_desc)");
        this$0.G(string, string2);
    }

    public static final void B(ActivityFirmwareUpdateMoyang this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void x(ActivityFirmwareUpdateMoyang this$0, View view) {
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

    public static final void y(ActivityFirmwareUpdateMoyang this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void z(ActivityFirmwareUpdateMoyang this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public final void C() {
        SessionManager.getInstance(this).setForceFirmwareUpdated(true);
        SessionManager.getInstance(this).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(this).deleteSoftwareUpdateResponseBeanString();
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, new ActivityFirmwareUpdateMoyang$onTransferCompleted$1(this));
        TextView textView = this.r;
        if (textView != null) {
            textView.setText(getString(R.string.upgrading_dot));
        }
        ((TextView) _$_findCachedViewById(R.id.fw_upgrading_header)).setVisibility(4);
        ((TextView) _$_findCachedViewById(R.id.tv_fw_upgrade_info)).setText(getString(R.string.please_wait_after_fw_update));
        ((ImageView) _$_findCachedViewById(R.id.fw_upgrading_image)).setImageResource(R.drawable.fw_upgrade_final_configurations);
    }

    public final void D() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
    }

    public final void E() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = ActivityFirmwareUpdateMoyang.this;
                i = activityFirmwareUpdateMoyang.w;
                ActivityCompat.requestPermissions(activityFirmwareUpdateMoyang, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityFirmwareUpdateMoyang.this.H();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
                ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = ActivityFirmwareUpdateMoyang.this;
                firmwareBean = activityFirmwareUpdateMoyang.u;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                activityFirmwareUpdateMoyang.v(firmwareBean);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityFirmwareUpdateMoyang.this.H();
            }
        });
    }

    public final void F(BleDeviceInfo bleDeviceInfo) {
        IOTUserDeviceReq iOTUserDeviceReq = new IOTUserDeviceReq(bleDeviceInfo);
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported()) {
            String str = bleDeviceInfo.getmDeviceName();
            Intrinsics.checkNotNullExpressionValue(str, "bleDeviceInfo.getmDeviceName()");
            String lowerCase = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            iOTUserDeviceReq.setAppTrackerId(lowerCase);
        }
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$sendConnectedDeviceInfoToServer$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$sendConnectedDeviceInfoToServer$1$onError$1", f = "ActivityFirmwareUpdateMoyang.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityFirmwareUpdateMoyang this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityFirmwareUpdateMoyang;
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

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityFirmwareUpdateMoyang.this), Dispatchers.getMain(), null, new a(ActivityFirmwareUpdateMoyang.this, null), 2, null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                ActivityFirmwareUpdateMoyang.this.showFWUpdateSuccessDialog();
            }
        });
    }

    public final void G(String str, String str2) {
        w();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
    }

    public final void H() {
        Intrinsics.checkNotNull(this);
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = this;
                Intrinsics.checkNotNull(activityFirmwareUpdateMoyang);
                AppUtils.openAppSettings(activityFirmwareUpdateMoyang, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void I() {
        BleApiManager.getInstance(this).getBleApi().getData(new DeviceInfoRequest.Builder().setIsModelNo(false).setIsFwVersion(true).setIsSerialNo(false).setIsHwVersion(false).build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$updateNewFwVersionToPref$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String tag = ActivityFirmwareUpdateMoyang.this.getTAG();
                LogHelper.d(tag, "onDataError->" + error.getErrorMsg());
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdateMoyang.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        AppUtils.isEmpty(deviceInfo.getFwVersion());
                        SessionManager.getInstance(ActivityFirmwareUpdateMoyang.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    if (bleDeviceInfo == null || bleDeviceInfo.getSerialNumber() == null || bleDeviceInfo.getmManufacturerName() == null || bleDeviceInfo.getmModelNumber() == null || bleDeviceInfo.getFirmwareRevision() == null || bleDeviceInfo.getHwRevision() == null) {
                        return;
                    }
                    ActivityFirmwareUpdateMoyang.this.F(bleDeviceInfo);
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
        return this.y;
    }

    @NotNull
    public final CoroutineScope getScope() {
        return this.x;
    }

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.nc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMoyang.x(ActivityFirmwareUpdateMoyang.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.qc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMoyang.y(ActivityFirmwareUpdateMoyang.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.remindMeLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.rc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMoyang.z(ActivityFirmwareUpdateMoyang.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.oc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMoyang.A(ActivityFirmwareUpdateMoyang.this, view);
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
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.pc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateMoyang.B(ActivityFirmwareUpdateMoyang.this, view);
            }
        });
    }

    public final void initiateDFU() {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdateMoyang$initiateDFU$1(this, null), 2, null);
        }
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
            v(firmwareBean);
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
        ((TextView) _$_findCachedViewById(R.id.current_fw_version)).setText(((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision());
        initToolbar();
        initClickListeners();
        initDialogs();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        w();
        CoroutineScope coroutineScope = this.x;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.w && AppUtils.checkIfLocationPermissionExists(this)) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this.u;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            v(firmwareBean);
        }
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.y = bottomSheetDialogImageTitleMessage;
    }

    public final void showFWUpdateSuccessDialog() {
        w();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void v(SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        w();
        this.s = 0;
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdateMoyang$beginFirmwareDownload$1(this, firmwareBean, null), 2, null);
    }

    public final void w() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }
}
