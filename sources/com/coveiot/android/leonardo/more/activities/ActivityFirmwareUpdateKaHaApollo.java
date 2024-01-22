package com.coveiot.android.leonardo.more.activities;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
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
import com.coveiot.mki.ota.OTACallback;
import com.coveiot.mki.ota.OTAManager;
import com.coveiot.mki.ota.OTAPlatform;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.realsil.sdk.dfu.utils.GattDfuAdapter;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateKaHaApollo extends BaseActivity implements OTACallback {
    @Nullable
    public String C;
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
    public BluetoothAdapter x;
    @Nullable
    public String z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityFirmwareUpdateKaHaRealTek";
    @NotNull
    public final Lazy v = LazyKt__LazyJVMKt.lazy(new b());
    @NotNull
    public final Handler w = new Handler();
    @NotNull
    public String y = "dfu";
    public final int A = 101;
    public final int B = 102;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$deleteFiles$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                File filesDir = ActivityFirmwareUpdateKaHaApollo.this.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateKaHaApollo.filesDir");
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
            return GattDfuAdapter.getInstance(ActivityFirmwareUpdateKaHaApollo.this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onAborted$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ActivityFirmwareUpdateKaHaApollo.this.E();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onCompleted$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ActivityFirmwareUpdateKaHaApollo.this.D();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onError$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = ActivityFirmwareUpdateKaHaApollo.this;
                Object[] objArr = new Object[1];
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = activityFirmwareUpdateKaHaApollo.u;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                objArr[0] = firmwareBean.getUpdateVersion();
                String string = activityFirmwareUpdateKaHaApollo.getString(R.string.fw_update_failure, objArr);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
                activityFirmwareUpdateKaHaApollo.H(string, "");
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onProgress$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ float $p1;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(float f, Continuation<? super f> continuation) {
            super(2, continuation);
            this.$p1 = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(this.$p1, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ProgressBar progressBar = ActivityFirmwareUpdateKaHaApollo.this.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(false);
                }
                int i = (int) (this.$p1 * 100);
                ProgressBar progressBar2 = ActivityFirmwareUpdateKaHaApollo.this.q;
                if (progressBar2 != null) {
                    progressBar2.setProgress(i);
                }
                ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = ActivityFirmwareUpdateKaHaApollo.this;
                int i2 = R.id.tv_progress_percentage;
                ((TextView) activityFirmwareUpdateKaHaApollo._$_findCachedViewById(i2)).setVisibility(0);
                ((TextView) ActivityFirmwareUpdateKaHaApollo.this._$_findCachedViewById(i2)).setText(ActivityFirmwareUpdateKaHaApollo.this.getString(R.string.dfu_uploading_percentage, new Object[]{Boxing.boxInt(i)}));
                TextView textView = ActivityFirmwareUpdateKaHaApollo.this.r;
                if (textView != null) {
                    textView.setText(ActivityFirmwareUpdateKaHaApollo.this.getString(R.string.sending));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onStarted$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public g(Continuation<? super g> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ProgressBar progressBar = ActivityFirmwareUpdateKaHaApollo.this.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(true);
                }
                TextView textView = ActivityFirmwareUpdateKaHaApollo.this.r;
                if (textView != null) {
                    textView.setText(R.string.dfu_status_starting);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onUploadCanceled$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public h(Continuation<? super h> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Toast.makeText(ActivityFirmwareUpdateKaHaApollo.this, "Upload canceled", 0).show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$showErrorBottomSheet$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public i(Continuation<? super i> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((i) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((TextView) ActivityFirmwareUpdateKaHaApollo.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdateKaHaApollo.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdateKaHaApollo.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdateKaHaApollo.this._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$showFWUpdateSuccessDialog$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class j extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public j(Continuation<? super j> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new j(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((j) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((TextView) ActivityFirmwareUpdateKaHaApollo.this.findViewById(R.id.toolbar_title)).setVisibility(8);
                ((TextView) ActivityFirmwareUpdateKaHaApollo.this.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
                ((LinearLayout) ActivityFirmwareUpdateKaHaApollo.this._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(8);
                ((ConstraintLayout) ActivityFirmwareUpdateKaHaApollo.this._$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(ActivityFirmwareUpdateKaHaApollo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void B(ActivityFirmwareUpdateKaHaApollo this$0, View view) {
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
                this$0.F();
                return;
            }
            Toast.makeText(this$0, this$0.getString(R.string.band_not_connected), 0).show();
        } else {
            Toast.makeText(this$0, this$0.getString(R.string.bluetooth_off_message), 0).show();
        }
    }

    public static final void C(ActivityFirmwareUpdateKaHaApollo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivityFirmwareUpdateKaHaApollo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isBluetoothEnabled(this$0)) {
            Toast.makeText(this$0, (int) R.string.bluetooth_off_message, 0).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0, (int) R.string.band_not_connected, 0).show();
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
        } else {
            ConstraintLayout fw_update_failed_layout = (ConstraintLayout) this$0._$_findCachedViewById(R.id.fw_update_failed_layout);
            Intrinsics.checkNotNullExpressionValue(fw_update_failed_layout, "fw_update_failed_layout");
            this$0.gone(fw_update_failed_layout);
            ((TextView) this$0.findViewById(R.id.toolbar_title)).setVisibility(0);
            ((Button) this$0._$_findCachedViewById(R.id.btn_update)).performClick();
        }
    }

    public static final void z(ActivityFirmwareUpdateKaHaApollo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public final void D() {
        SessionManager.getInstance(this).setForceFirmwareUpdated(true);
        SessionManager.getInstance(this).deleteOptionalFirmwareBeanString();
        SessionManager.getInstance(this).deleteSoftwareUpdateResponseBeanString();
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1(this, null), 2, null);
        BlePreferenceManager.savePreference(getApplicationContext(), CommonPreference.BLE_DEVICE_ADDRESS, this.z);
        BlePreferenceManager.savePreference(getApplicationContext(), CommonPreference.BLE_CONNECTION_TYPE, ConnectionType.RECONNECT_ON_DISCONNECT.name());
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        if (bleApi != null) {
            bleApi.restartService();
        }
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
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new h(null), 2, null);
        LogHelper.d(this.p, "onUploadCanceled");
    }

    public final void F() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i2;
                ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = ActivityFirmwareUpdateKaHaApollo.this;
                i2 = activityFirmwareUpdateKaHaApollo.A;
                ActivityCompat.requestPermissions(activityFirmwareUpdateKaHaApollo, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i2);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityFirmwareUpdateKaHaApollo.this.I();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = ActivityFirmwareUpdateKaHaApollo.this;
                SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = activityFirmwareUpdateKaHaApollo.u;
                if (firmwareBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                    firmwareBean = null;
                }
                activityFirmwareUpdateKaHaApollo.v(firmwareBean);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityFirmwareUpdateKaHaApollo.this.I();
            }
        });
    }

    public final void G(BleDeviceInfo bleDeviceInfo) {
        IOTUserDeviceReq iOTUserDeviceReq = new IOTUserDeviceReq(bleDeviceInfo);
        if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isBandSocialDistanceFeatureSupported()) {
            String str = bleDeviceInfo.getmDeviceName();
            Intrinsics.checkNotNullExpressionValue(str, "bleDeviceInfo.getmDeviceName()");
            String lowerCase = str.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            iOTUserDeviceReq.setAppTrackerId(lowerCase);
        }
        LogHelper.i(this.p, "sendConnectedDeviceInfoToServer");
        DeviceInfoManager.saveConnectedDeviceInfoOnServer(iOTUserDeviceReq, new CoveApiListener<IOTUserDeviceRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$sendConnectedDeviceInfoToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                ActivityFirmwareUpdateKaHaApollo.this.dismissProgress();
                LogHelper.i(ActivityFirmwareUpdateKaHaApollo.this.getTAG(), "sendConnectedDeviceInfoToServer onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable IOTUserDeviceRes iOTUserDeviceRes) {
                LogHelper.i(ActivityFirmwareUpdateKaHaApollo.this.getTAG(), "sendConnectedDeviceInfoToServer onSuccess");
                ActivityFirmwareUpdateKaHaApollo.this.showFWUpdateSuccessDialog();
            }
        });
    }

    public final void H(String str, String str2) {
        w();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new i(null), 2, null);
    }

    public final void I() {
        String string = getString(R.string.need_location_permission);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …_permission\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                AppUtils.openAppSettings(this, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.setCancelable(true);
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void J() {
        BleApiManager.getInstance(this).getBleApi().getData(new DeviceInfoRequest.Builder().setIsFwVersion(true).build(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$updateNewFwVersionToPref$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityFirmwareUpdateKaHaApollo.this.getTAG(), "deviceInfoRequest: onDataError");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof DeviceInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                    DeviceInfoData deviceInfo = ((DeviceInfoResponse) responseData).getDeviceInfo();
                    final BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
                    if (bleDeviceInfo != null && deviceInfo != null) {
                        bleDeviceInfo.setFirmwareRevision(deviceInfo.getFwVersion());
                        PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdateKaHaApollo.this).saveDeviceVersionNumber(deviceInfo.getFwVersion());
                        SessionManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).saveBleDeviceInfo(new Gson().toJson(bleDeviceInfo));
                    }
                    LogHelper.d(ActivityFirmwareUpdateKaHaApollo.this.getTAG(), "deviceInfoRequest: onDataResponse");
                    BleApiManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleApi().clearCommandQueue();
                    BleApi bleApi = BleApiManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleApi();
                    GetFirmwareCapabilityRequest getFirmwareCapabilityRequest = new GetFirmwareCapabilityRequest();
                    final ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = ActivityFirmwareUpdateKaHaApollo.this;
                    bleApi.getData(getFirmwareCapabilityRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$updateNewFwVersionToPref$1$onDataResponse$1
                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onDataError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            LogHelper.d(ActivityFirmwareUpdateKaHaApollo.this.getTAG(), error.getErrorMsg());
                            SessionManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleApi().getMacAddress(), new FirmwareCapabilityData());
                            PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdateKaHaApollo.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleApi().getMacAddress(), new com.coveiot.android.bleabstract.models.FirmwareCapabilityData());
                            ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo2 = ActivityFirmwareUpdateKaHaApollo.this;
                            BleDeviceInfo bleDeviceInfo2 = bleDeviceInfo;
                            Intrinsics.checkNotNullExpressionValue(bleDeviceInfo2, "bleDeviceInfo");
                            activityFirmwareUpdateKaHaApollo2.G(bleDeviceInfo2);
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
                                SessionManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleApi().getMacAddress(), firmwareCapabilityData);
                                com.coveiot.android.bleabstract.models.FirmwareCapabilityData firmwareCapabilityData2 = new com.coveiot.android.bleabstract.models.FirmwareCapabilityData();
                                firmwareCapabilityData2.setCapabilities(getFirmwareCapabilityResponse.getCapabilities());
                                PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdateKaHaApollo.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleApi().getMacAddress(), firmwareCapabilityData2);
                            } else {
                                SessionManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleApi().getMacAddress(), new FirmwareCapabilityData());
                                PreferenceManagerAbstract.getInstance(ActivityFirmwareUpdateKaHaApollo.this).saveFirmwareCapability(BleApiManager.getInstance(ActivityFirmwareUpdateKaHaApollo.this).getBleApi().getMacAddress(), new com.coveiot.android.bleabstract.models.FirmwareCapabilityData());
                            }
                            ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo2 = ActivityFirmwareUpdateKaHaApollo.this;
                            BleDeviceInfo bleDeviceInfo2 = bleDeviceInfo;
                            Intrinsics.checkNotNullExpressionValue(bleDeviceInfo2, "bleDeviceInfo");
                            activityFirmwareUpdateKaHaApollo2.G(bleDeviceInfo2);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onProgressUpdate(@NotNull ProgressData progress) {
                            Intrinsics.checkNotNullParameter(progress, "progress");
                        }
                    });
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
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
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
        return this.y;
    }

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    public final void initClickListeners() {
        ((Button) _$_findCachedViewById(R.id.btn_try_again)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.gb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaApollo.y(ActivityFirmwareUpdateKaHaApollo.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.fb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaApollo.z(ActivityFirmwareUpdateKaHaApollo.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.remindMeLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.hb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaApollo.A(ActivityFirmwareUpdateKaHaApollo.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ib
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaApollo.B(ActivityFirmwareUpdateKaHaApollo.this, view);
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
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.jb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityFirmwareUpdateKaHaApollo.C(ActivityFirmwareUpdateKaHaApollo.this, view);
            }
        });
    }

    public final void initiateDFU(String str, String str2) {
        BleApiManager.getInstance(this).getBleApi().stopService();
        OTAManager.Builder builder = new OTAManager.Builder(this);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isCYDevice(this)) {
            builder.setOtaPlatform(OTAPlatform.Apollo);
        } else if (companion.isJeiLiChipSet(this)) {
            builder.setOtaPlatform(OTAPlatform.JieLi);
        }
        BluetoothAdapter bluetoothAdapter = this.x;
        builder.setOtaDevice(bluetoothAdapter != null ? bluetoothAdapter.getRemoteDevice(str2) : null);
        builder.setFirmware(kotlin.io.c.readBytes(new File(this.C)));
        builder.build().ota(this);
    }

    @Override // com.coveiot.mki.ota.OTACallback
    public void onAborted(@Nullable OTAManager oTAManager) {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new c(null), 2, null);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
        if (i2 != 121) {
            if (i2 != 122) {
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

    @Override // com.coveiot.mki.ota.OTACallback
    public void onCompleted(@Nullable OTAManager oTAManager) {
        onProgress(oTAManager, 1.0f);
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new d(null), 2, null);
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
        this.x = ((BluetoothManager) systemService).getAdapter();
        this.z = SessionManager.getInstance(this).getConnectedDeviceMacAddress();
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

    @Override // com.coveiot.mki.ota.OTACallback
    public void onError(@Nullable OTAManager oTAManager, @Nullable String str) {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new e(null), 2, null);
    }

    @Override // com.coveiot.mki.ota.OTACallback
    public void onProgress(@Nullable OTAManager oTAManager, float f2) {
        String str = this.p;
        LogHelper.d(str, "Firmware update progress: " + f2);
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new f(f2, null), 2, null);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i2, permissions, grantResults);
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = null;
        if (i2 == this.A) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = this.u;
            if (firmwareBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean2;
            }
            v(firmwareBean);
        } else if (i2 == this.B) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean3 = this.u;
            if (firmwareBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean = firmwareBean3;
            }
            v(firmwareBean);
        }
    }

    @Override // com.coveiot.mki.ota.OTACallback
    public void onStarted(@Nullable OTAManager oTAManager) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new g(null), 2, null);
    }

    public final void setBottomSheetDialogImageTitleMessageError(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.E = bottomSheetDialogImageTitleMessage;
    }

    public final void setBottomSheetDialogImageTitleMessageSuccess(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.D = bottomSheetDialogImageTitleMessage;
    }

    public final void setDfuFilterName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.y = str;
    }

    public final void showFWUpdateSuccessDialog() {
        w();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new j(null), 2, null);
    }

    public final void v(SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        String updateKey = firmwareBean.getUpdateKey();
        if (!(updateKey == null || updateKey.length() == 0)) {
            w();
            this.s = 0;
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1(this, firmwareBean, null), 2, null);
            return;
        }
        String string = getString(R.string.fw_update_key_not_found);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_update_key_not_found)");
        H(string, "");
    }

    public final void w() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public final GattDfuAdapter x() {
        return (GattDfuAdapter) this.v.getValue();
    }
}
