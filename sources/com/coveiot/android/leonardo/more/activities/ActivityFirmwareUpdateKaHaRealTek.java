package com.coveiot.android.leonardo.more.activities;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
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
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.GetFirmwareCapabilityRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.bleabstract.response.GetFirmwareCapabilityResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
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
import com.coveiot.covepreferences.data.FirmwareCapabilityData;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.realsil.sdk.core.RtkConfigure;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.dfu.RtkDfu;
import com.realsil.sdk.dfu.utils.GattDfuAdapter;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateKaHaRealTek extends BaseActivity {
    @Nullable
    public String B;
    @Nullable
    public BottomSheetDialogImageTitleMessage D;
    @Nullable
    public BottomSheetDialogImageTitleMessage E;
    @Nullable
    public ProgressBar q;
    @Nullable
    public TextView r;
    public int s;
    public BottomSheetDialog t;
    public SoftwareUpdateRes.DataBean.FirmwareBean u;
    @Nullable
    public String y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityFirmwareUpdateKaHaRealTek";
    @NotNull
    public final Lazy v = LazyKt__LazyJVMKt.lazy(new b());
    @NotNull
    public final Handler w = new Handler();
    @NotNull
    public String x = "dfu";
    public final int z = 101;
    public final int A = 102;
    @NotNull
    public final ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1 C = new ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1(this);

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$deleteFiles$1", f = "ActivityFirmwareUpdateKaHaRealTek.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                File filesDir = ActivityFirmwareUpdateKaHaRealTek.this.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateKaHaRealTek.filesDir");
                fileUtils.deleteRecursive(fileUtils.getFirmwareFile(filesDir, AppConstants.LEONARDO_FIRMWARE_FILE_NAME.getValue()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function0<GattDfuAdapter> {
        public b() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GattDfuAdapter invoke() {
            return GattDfuAdapter.getInstance(ActivityFirmwareUpdateKaHaRealTek.this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$onUploadCanceled$1", f = "ActivityFirmwareUpdateKaHaRealTek.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                Toast.makeText(ActivityFirmwareUpdateKaHaRealTek.this, "Upload canceled", 0).show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$showErrorBottomSheet$1", f = "ActivityFirmwareUpdateKaHaRealTek.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ((TextView) ActivityFirmwareUpdateKaHaRealTek.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdateKaHaRealTek.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdateKaHaRealTek.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdateKaHaRealTek.this._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$showFWUpdateSuccessDialog$1", f = "ActivityFirmwareUpdateKaHaRealTek.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ((TextView) ActivityFirmwareUpdateKaHaRealTek.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdateKaHaRealTek.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdateKaHaRealTek.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdateKaHaRealTek.this._$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivityFirmwareUpdateKaHaRealTek this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void B(ActivityFirmwareUpdateKaHaRealTek this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isGpsEnabled(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.please_check_gps), 0).show();
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
        } else if (AppUtils.isBluetoothEnabled(this$0)) {
            BleApi bleApi = BleApiManager.getInstance(this$0).getBleApi();
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
            if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
                if (AppUtils.checkIfLocationPermissionExists(this$0)) {
                    SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this$0.u;
                    if (firmwareBean2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    } else {
                        firmwareBean = firmwareBean2;
                    }
                    this$0.v(firmwareBean);
                    return;
                }
                this$0.H();
                return;
            }
            Toast.makeText(this$0, this$0.getString(R.string.band_not_connected), 0).show();
        } else {
            Toast.makeText(this$0, this$0.getString(R.string.bluetooth_off_message), 0).show();
        }
    }

    public static final void C(ActivityFirmwareUpdateKaHaRealTek this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivityFirmwareUpdateKaHaRealTek this$0, View view) {
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

    public static final void z(ActivityFirmwareUpdateKaHaRealTek this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(-1);
        this$0.finish();
    }

    public final void D() {
        SessionManager.getInstance(this).setForceFirmwareUpdated(true);
        SessionManager.getInstance(this).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(this).deleteSoftwareUpdateResponseBeanString();
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, new ActivityFirmwareUpdateKaHaRealTek$onTransferCompleted$1(this));
        TextView textView = this.r;
        if (textView != null) {
            textView.setText(getString(R.string.upgrading_dot));
        }
        ((TextView) _$_findCachedViewById(R.id.fw_upgrading_header)).setVisibility(4);
        ((TextView) _$_findCachedViewById(R.id.tv_fw_upgrade_info)).setText(getString(R.string.please_wait_after_fw_update));
        ((ImageView) _$_findCachedViewById(R.id.fw_upgrading_image)).setImageResource(R.drawable.fw_upgrade_final_configurations);
        LogHelper.d(this.p, "onTransferCompleted ");
    }

    public final void E() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
        LogHelper.d(this.p, "onUploadCanceled");
    }

    public final String F(Context context, int i, int i2) {
        TypedArray obtainTypedArray;
        if (i == 0) {
            obtainTypedArray = context.getResources().obtainTypedArray(R.array.connection_error_code);
            Intrinsics.checkNotNullExpressionValue(obtainTypedArray, "context.resources.obtain…ay.connection_error_code)");
        } else {
            TypedArray obtainTypedArray2 = context.getResources().obtainTypedArray(R.array.error_code);
            Intrinsics.checkNotNullExpressionValue(obtainTypedArray2, "context.resources.obtain…Array(R.array.error_code)");
            String G = G(context, obtainTypedArray2, i, i2);
            obtainTypedArray2.recycle();
            if (G != null) {
                return G;
            }
            obtainTypedArray = context.getResources().obtainTypedArray(R.array.load_file_error_code);
            Intrinsics.checkNotNullExpressionValue(obtainTypedArray, "context.resources.obtain…ray.load_file_error_code)");
        }
        String G2 = G(context, obtainTypedArray, i, i2);
        obtainTypedArray.recycle();
        if (G2 == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.ENGLISH, "0x%04X(%d) %s, %s", Arrays.copyOf(new Object[]{Integer.valueOf(i2), Integer.valueOf(i2), "null", "null"}, 4));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            return format;
        }
        return G2;
    }

    public final String G(Context context, TypedArray typedArray, int i, int i2) {
        int length = typedArray.length();
        for (int i3 = 0; i3 < length; i3++) {
            String[] stringArray = context.getResources().getStringArray(typedArray.getResourceId(i3, -1));
            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ray.getResourceId(i, -1))");
            String str = stringArray[0];
            Intrinsics.checkNotNullExpressionValue(str, "errorInfo[0]");
            int parseInt = Integer.parseInt(str);
            String str2 = stringArray[1];
            String str3 = stringArray[2];
            if (parseInt == i2) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ENGLISH, "0x%04X(%d) %s, %s", Arrays.copyOf(new Object[]{Integer.valueOf(parseInt), Integer.valueOf(parseInt), str2, str3}, 4));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                return format;
            }
        }
        return null;
    }

    public final void H() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = ActivityFirmwareUpdateKaHaRealTek.this;
                i = activityFirmwareUpdateKaHaRealTek.z;
                ActivityCompat.requestPermissions(activityFirmwareUpdateKaHaRealTek, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityFirmwareUpdateKaHaRealTek.this.K();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
                ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = ActivityFirmwareUpdateKaHaRealTek.this;
                firmwareBean = activityFirmwareUpdateKaHaRealTek.u;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                activityFirmwareUpdateKaHaRealTek.v(firmwareBean);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityFirmwareUpdateKaHaRealTek.this.K();
            }
        });
    }

    public final void I(BleDeviceInfo bleDeviceInfo) {
        IOTUserDeviceReq iOTUserDeviceReq = new IOTUserDeviceReq(bleDeviceInfo);
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported()) {
            String str = bleDeviceInfo.getmDeviceName();
            Intrinsics.checkNotNullExpressionValue(str, "bleDeviceInfo.getmDeviceName()");
            String lowerCase = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            iOTUserDeviceReq.setAppTrackerId(lowerCase);
        }
        LogHelper.i(this.p, "sendConnectedDeviceInfoToServer");
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$sendConnectedDeviceInfoToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ActivityFirmwareUpdateKaHaRealTek.this.dismissProgress();
                LogHelper.i(ActivityFirmwareUpdateKaHaRealTek.this.getTAG(), "sendConnectedDeviceInfoToServer onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                LogHelper.i(ActivityFirmwareUpdateKaHaRealTek.this.getTAG(), "sendConnectedDeviceInfoToServer onSuccess");
                ActivityFirmwareUpdateKaHaRealTek.this.showFWUpdateSuccessDialog();
            }
        });
    }

    public final void J(String str, String str2) {
        w();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void K() {
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                AppUtils.openAppSettings(this, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void L() {
        BleApiManager.getInstance(this).getBleApi().getData(new DeviceInfoRequest.Builder().setIsFwVersion(true).build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$updateNewFwVersionToPref$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityFirmwareUpdateKaHaRealTek.this.getTAG(), "deviceInfoRequest: onDataError");
                ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = ActivityFirmwareUpdateKaHaRealTek.this;
                Object[] objArr = new Object[1];
                firmwareBean = activityFirmwareUpdateKaHaRealTek.u;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                objArr[0] = firmwareBean.getUpdateVersion();
                String string = activityFirmwareUpdateKaHaRealTek.getString(R.string.fw_update_failure, objArr);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
                activityFirmwareUpdateKaHaRealTek.J(string, "");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    final BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).saveDeviceVersionNumber(deviceInfo.getFwVersion());
                        SessionManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    LogHelper.d(ActivityFirmwareUpdateKaHaRealTek.this.getTAG(), "deviceInfoRequest: onDataResponse");
                    if (DeviceUtils.Companion.isCADevice(ActivityFirmwareUpdateKaHaRealTek.this)) {
                        BleApiManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleApi().clearCommandQueue();
                        BleApi bleApi = BleApiManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleApi();
                        GetFirmwareCapabilityRequest getFirmwareCapabilityRequest = new GetFirmwareCapabilityRequest();
                        final ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = ActivityFirmwareUpdateKaHaRealTek.this;
                        bleApi.getData(getFirmwareCapabilityRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$updateNewFwVersionToPref$1$onDataResponse$1
                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                LogHelper.d(ActivityFirmwareUpdateKaHaRealTek.this.getTAG(), error.getErrorMsg());
                                SessionManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleApi().getMacAddress(), new FirmwareCapabilityData());
                                PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleApi().getMacAddress(), new com.coveiot.android.bleabstract.models.FirmwareCapabilityData());
                                ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek2 = ActivityFirmwareUpdateKaHaRealTek.this;
                                BleDeviceInfo bleDeviceInfo2 = bleDeviceInfo;
                                Intrinsics.checkNotNullExpressionValue(bleDeviceInfo2, "bleDeviceInfo");
                                activityFirmwareUpdateKaHaRealTek2.I(bleDeviceInfo2);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataResponse(@NotNull BleBaseResponse response2) {
                                Intrinsics.checkNotNullParameter(response2, "response");
                                if (response2.getResponseData() != null && (response2.getResponseData() instanceof GetFirmwareCapabilityResponse)) {
                                    Object responseData2 = response2.getResponseData();
                                    Intrinsics.checkNotNull(responseData2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetFirmwareCapabilityResponse");
                                    GetFirmwareCapabilityResponse getFirmwareCapabilityResponse = (GetFirmwareCapabilityResponse) responseData2;
                                    FirmwareCapabilityData firmwareCapabilityData = new FirmwareCapabilityData();
                                    firmwareCapabilityData.setCapabilities(getFirmwareCapabilityResponse.getCapabilities());
                                    SessionManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleApi().getMacAddress(), firmwareCapabilityData);
                                    com.coveiot.android.bleabstract.models.FirmwareCapabilityData firmwareCapabilityData2 = new com.coveiot.android.bleabstract.models.FirmwareCapabilityData();
                                    firmwareCapabilityData2.setCapabilities(getFirmwareCapabilityResponse.getCapabilities());
                                    PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleApi().getMacAddress(), firmwareCapabilityData2);
                                } else {
                                    SessionManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleApi().getMacAddress(), new FirmwareCapabilityData());
                                    PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaRealTek.this).getBleApi().getMacAddress(), new com.coveiot.android.bleabstract.models.FirmwareCapabilityData());
                                }
                                ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek2 = ActivityFirmwareUpdateKaHaRealTek.this;
                                BleDeviceInfo bleDeviceInfo2 = bleDeviceInfo;
                                Intrinsics.checkNotNullExpressionValue(bleDeviceInfo2, "bleDeviceInfo");
                                activityFirmwareUpdateKaHaRealTek2.I(bleDeviceInfo2);
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onProgressUpdate(@NotNull ProgressData progress) {
                                Intrinsics.checkNotNullParameter(progress, "progress");
                            }
                        });
                        return;
                    }
                    ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek2 = ActivityFirmwareUpdateKaHaRealTek.this;
                    Intrinsics.checkNotNullExpressionValue(bleDeviceInfo, "bleDeviceInfo");
                    activityFirmwareUpdateKaHaRealTek2.I(bleDeviceInfo);
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
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessageError() {
        return this.E;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessageSuccess() {
        return this.D;
    }

    @NotNull
    public final String getDfuFilterName() {
        return this.x;
    }

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ob
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaRealTek.y(ActivityFirmwareUpdateKaHaRealTek.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.qb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaRealTek.z(ActivityFirmwareUpdateKaHaRealTek.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.remindMeLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.mb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaRealTek.A(ActivityFirmwareUpdateKaHaRealTek.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.nb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaRealTek.B(ActivityFirmwareUpdateKaHaRealTek.this, view);
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
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.pb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaRealTek.C(ActivityFirmwareUpdateKaHaRealTek.this, view);
            }
        });
    }

    public final void initiateDFU(String str, String str2) {
        RtkCore.initialize(this, new RtkConfigure.Builder().debugEnabled(false).printLog(false).build());
        RtkDfu.initialize(this, true);
        GattDfuAdapter x = x();
        Intrinsics.checkNotNull(x);
        x.initialize(this.C);
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
        ((BluetoothManager) systemService).getAdapter();
        this.y = SessionManager.getInstance(this).getConnectedDeviceMacAddress();
        ((TextView) _$_findCachedViewById(R.id.current_fw_version)).setText(((BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class)).getFirmwareRevision());
        initToolbar();
        initClickListeners();
        initDialogs();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        w();
        GattDfuAdapter x = x();
        Intrinsics.checkNotNull(x);
        x.abort();
        x.close();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
        if (i == this.z) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this.u;
            if (firmwareBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean2;
            }
            v(firmwareBean);
        } else if (i == this.A) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean3 = this.u;
            if (firmwareBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean3;
            }
            v(firmwareBean);
        }
    }

    public final void setBottomSheetDialogImageTitleMessageError(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.E = bottomSheetDialogImageTitleMessage;
    }

    public final void setBottomSheetDialogImageTitleMessageSuccess(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.D = bottomSheetDialogImageTitleMessage;
    }

    public final void setDfuFilterName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.x = str;
    }

    @SuppressLint({"StringFormatInvalid"})
    public final void showFWUpdateSuccessDialog() {
        w();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(null), 2, null);
    }

    public final void v(SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        String updateKey = firmwareBean.getUpdateKey();
        if (!(updateKey == null || updateKey.length() == 0)) {
            w();
            this.s = 0;
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdateKaHaRealTek$beginFirmwareDownload$1(this, firmwareBean, null), 2, null);
            return;
        }
        String string = getString(R.string.fw_update_key_not_found);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_update_key_not_found)");
        J(string, "");
    }

    public final void w() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public final GattDfuAdapter x() {
        return (GattDfuAdapter) this.v.getValue();
    }
}
