package com.coveiot.android.leonardo.more.activities;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.boat.BuildConfig;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.DFUUtils;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.utils.utility.LogHelper;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.image.FirmwareLoaderX;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.utils.ConnectParams;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.realsil.sdk.dfu.utils.GattDfuAdapter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1 extends DfuAdapter.DfuHelperCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateKaHaRealTek f4933a;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1$onProcessStateChanged$1$1", f = "ActivityFirmwareUpdateKaHaRealTek.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateKaHaRealTek this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateKaHaRealTek;
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
            TextView textView;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                textView = this.this$0.r;
                if (textView != null) {
                    textView.setText(this.this$0.getString(R.string.upgrading));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1$onProgressChanged$1", f = "ActivityFirmwareUpdateKaHaRealTek.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DfuProgressInfo $progressInfo;
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateKaHaRealTek this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek, DfuProgressInfo dfuProgressInfo, Continuation<? super b> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateKaHaRealTek;
            this.$progressInfo = dfuProgressInfo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.this$0, this.$progressInfo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ProgressBar progressBar;
            ProgressBar progressBar2;
            TextView textView;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                progressBar = this.this$0.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(false);
                }
                progressBar2 = this.this$0.q;
                if (progressBar2 != null) {
                    progressBar2.setProgress(this.$progressInfo.getProgress());
                }
                ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = this.this$0;
                int i = R.id.tv_progress_percentage;
                ((TextView) activityFirmwareUpdateKaHaRealTek._$_findCachedViewById(i)).setVisibility(0);
                ((TextView) this.this$0._$_findCachedViewById(i)).setText(this.this$0.getString(R.string.dfu_uploading_percentage, new Object[]{Boxing.boxInt(this.$progressInfo.getProgress())}));
                textView = this.this$0.r;
                if (textView != null) {
                    textView.setText(this.this$0.getString(R.string.sending));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1$onStateChanged$1", f = "ActivityFirmwareUpdateKaHaRealTek.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateKaHaRealTek this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek, Continuation<? super c> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateKaHaRealTek;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ProgressBar progressBar;
            TextView textView;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                progressBar = this.this$0.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(true);
                }
                textView = this.this$0.r;
                if (textView != null) {
                    textView.setText(R.string.dfu_status_starting);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1$onStateChanged$2", f = "ActivityFirmwareUpdateKaHaRealTek.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateKaHaRealTek this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek, Continuation<? super d> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateKaHaRealTek;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ProgressBar progressBar;
            TextView textView;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                progressBar = this.this$0.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(true);
                }
                textView = this.this$0.r;
                if (textView != null) {
                    textView.setText(R.string.dfu_state_dfu_preparing);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1(ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek) {
        this.f4933a = activityFirmwareUpdateKaHaRealTek;
    }

    public static final void f(ActivityFirmwareUpdateKaHaRealTek this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void g(ActivityFirmwareUpdateKaHaRealTek this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new a(this$0, null), 2, null);
        this$0.D();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void h(ActivityFirmwareUpdateKaHaRealTek this$0) {
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object[] objArr = new Object[1];
        firmwareBean = this$0.u;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = this$0.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
        this$0.J(string, "");
    }

    public static final void i(ActivityFirmwareUpdateKaHaRealTek this$0) {
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object[] objArr = new Object[1];
        firmwareBean = this$0.u;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = this$0.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
        this$0.J(string, "");
    }

    public static final void j(ActivityFirmwareUpdateKaHaRealTek this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
    @SuppressLint({"StringFormatInvalid"})
    public void onError(int i, int i2) {
        Handler handler;
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        String F;
        handler = this.f4933a.w;
        final ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = this.f4933a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.tb
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1.f(ActivityFirmwareUpdateKaHaRealTek.this);
            }
        }, 200L);
        ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek2 = this.f4933a;
        Object[] objArr = new Object[1];
        firmwareBean = activityFirmwareUpdateKaHaRealTek2.u;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = activityFirmwareUpdateKaHaRealTek2.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
        activityFirmwareUpdateKaHaRealTek2.J(string, "");
        String tag = this.f4933a.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("onError message ");
        ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek3 = this.f4933a;
        F = activityFirmwareUpdateKaHaRealTek3.F(activityFirmwareUpdateKaHaRealTek3, i, i2);
        sb.append(F);
        LogHelper.d(tag, sb.toString());
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
    public void onProcessStateChanged(int i, @Nullable Throughput throughput) {
        Handler handler;
        if (i == 258) {
            handler = this.f4933a.w;
            final ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = this.f4933a;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.sb
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1.g(ActivityFirmwareUpdateKaHaRealTek.this);
                }
            }, 200L);
            LogHelper.d(this.f4933a.getTAG(), "onDfuCompleted");
        }
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
    @SuppressLint({"StringFormatInvalid"})
    public void onProgressChanged(@NotNull DfuProgressInfo progressInfo) {
        Intrinsics.checkNotNullParameter(progressInfo, "progressInfo");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4933a), Dispatchers.getMain(), null, new b(this.f4933a, progressInfo, null), 2, null);
        LogHelper.d(this.f4933a.getTAG(), "onProgressChanged");
    }

    @Override // com.realsil.sdk.dfu.utils.DfuAdapter.DfuHelperCallback
    @SuppressLint({"StringFormatInvalid"})
    public void onStateChanged(int i) {
        String str;
        GattDfuAdapter x;
        String str2;
        String str3;
        GattDfuAdapter x2;
        GattDfuAdapter x3;
        GattDfuAdapter x4;
        GattDfuAdapter x5;
        GattDfuAdapter x6;
        GattDfuAdapter x7;
        String str4;
        GattDfuAdapter x8;
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        TextView textView;
        Handler handler;
        GattDfuAdapter x9;
        GattDfuAdapter x10;
        GattDfuAdapter x11;
        GattDfuAdapter x12;
        if (i == 258) {
            LogHelper.d(this.f4933a.getTAG(), "STATE_INIT_OK");
            ConnectParams.Builder builder = new ConnectParams.Builder();
            str = this.f4933a.y;
            ConnectParams.Builder batteryValueFormat = builder.address(str).reconnectTimes(3).batteryValueFormat(1);
            x = this.f4933a.x();
            Intrinsics.checkNotNull(x);
            x.connectDevice(batteryValueFormat.build());
            return;
        }
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = null;
        if (i != 527) {
            if (i == 1025) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4933a), Dispatchers.getMain(), null, new d(this.f4933a, null), 2, null);
                LogHelper.d(this.f4933a.getTAG(), "STATE_OTA_PROCESSING");
                return;
            } else if (i == 8193) {
                textView = this.f4933a.r;
                if (textView != null) {
                    textView.setText(R.string.dfu_status_aborted);
                }
                handler = this.f4933a.w;
                final ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = this.f4933a;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.wb
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1.j(ActivityFirmwareUpdateKaHaRealTek.this);
                    }
                }, 200L);
                LogHelper.d(this.f4933a.getTAG(), "onDfuAborted");
                return;
            } else if (i == 4097) {
                LogHelper.d(this.f4933a.getTAG(), "STATE_DISCONNECTED");
                final ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek2 = this.f4933a;
                activityFirmwareUpdateKaHaRealTek2.runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ub
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1.h(ActivityFirmwareUpdateKaHaRealTek.this);
                    }
                });
                x9 = this.f4933a.x();
                x9.abort();
                x10 = this.f4933a.x();
                x10.close();
                return;
            } else if (i != 4098) {
                return;
            } else {
                LogHelper.d(this.f4933a.getTAG(), "STATE_CONNECT_FAILED");
                final ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek3 = this.f4933a;
                activityFirmwareUpdateKaHaRealTek3.runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.vb
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFirmwareUpdateKaHaRealTek$mDfuHelperCallback$1.i(ActivityFirmwareUpdateKaHaRealTek.this);
                    }
                });
                x11 = this.f4933a.x();
                x11.abort();
                x12 = this.f4933a.x();
                x12.close();
                return;
            }
        }
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4933a), Dispatchers.getMain(), null, new c(this.f4933a, null), 2, null);
        DfuConfig dfuConfig = new DfuConfig();
        dfuConfig.setChannelType(0);
        str2 = this.f4933a.y;
        dfuConfig.setAddress(str2);
        dfuConfig.setFileLocation(0);
        str3 = this.f4933a.B;
        dfuConfig.setFilePath(str3);
        dfuConfig.setFileSuffix("bin");
        try {
            firmwareBean = this.f4933a.u;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            } else {
                firmwareBean2 = firmwareBean;
            }
            dfuConfig.setSecretKey(DataConverter.hex2Bytes(DFUUtils.decryptMsg(firmwareBean2.getUpdateKey(), BuildConfig.COVE_REALTEK_FW_UPDATE_PUBLIC_KEY)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        x2 = this.f4933a.x();
        Intrinsics.checkNotNull(x2);
        dfuConfig.setOtaWorkMode(x2.getPriorityWorkMode(16).getWorkmode());
        dfuConfig.setBatteryCheckEnabled(false);
        dfuConfig.setBatteryLevelFormat(0);
        dfuConfig.setLowBatteryThreshold(20);
        dfuConfig.setIcCheckEnabled(true);
        x3 = this.f4933a.x();
        Intrinsics.checkNotNull(x3);
        dfuConfig.setPrimaryIcType(x3.getOtaDeviceInfo().icType);
        dfuConfig.setSectionSizeCheckEnabled(true);
        dfuConfig.setBreakpointResumeEnabled(false);
        dfuConfig.setVersionCheckEnabled(false);
        dfuConfig.setVersionCheckMode(0);
        x4 = this.f4933a.x();
        Intrinsics.checkNotNull(x4);
        dfuConfig.setProtocolType(x4.getOtaDeviceInfo().getProtocolType());
        dfuConfig.setAutomaticActiveEnabled(true);
        dfuConfig.setThroughputEnabled(false);
        dfuConfig.setMtuUpdateEnabled(false);
        dfuConfig.setWaitActiveCmdAckEnabled(false);
        dfuConfig.setWaitDisconnectWhenEnterOtaMode(true);
        dfuConfig.setFlowControlEnabled(true);
        dfuConfig.setFlowControlInterval(1);
        dfuConfig.setPhy(0);
        dfuConfig.setConParamUpdateLatencyEnabled(true);
        dfuConfig.setLatencyTimeout(10);
        dfuConfig.setHandoverTimeout(6);
        dfuConfig.addErrorAction(1);
        dfuConfig.addErrorAction(2);
        dfuConfig.addErrorAction(4);
        dfuConfig.removeCompleteAction(1);
        try {
            LoadParams.Builder with = new LoadParams.Builder().with(this.f4933a);
            str4 = this.f4933a.B;
            LoadParams.Builder fileSuffix = with.setFilePath(str4).setFileSuffix("bin");
            x8 = this.f4933a.x();
            Intrinsics.checkNotNull(x8);
            BinInfo loadImageBinInfo = FirmwareLoaderX.loadImageBinInfo(fileSuffix.setOtaDeviceInfo(x8.getOtaDeviceInfo()).setIcCheckEnabled(true).setSectionSizeCheckEnabled(false).build());
            String tag = this.f4933a.getTAG();
            LogHelper.i(tag, "OTA BIN info (icType: %d, version: %d, fileType: %d)" + loadImageBinInfo.icType + "" + loadImageBinInfo.version + "" + loadImageBinInfo.fileType);
        } catch (Exception e2) {
            LogHelper.e(this.f4933a.getTAG(), "=== Loading bin info failed===");
            LogHelper.e(this.f4933a.getTAG(), "Error: %s", e2.getMessage());
            x5 = this.f4933a.x();
            Intrinsics.checkNotNull(x5);
            x5.abort();
            x6 = this.f4933a.x();
            Intrinsics.checkNotNull(x6);
            x6.close();
            this.f4933a.finish();
        }
        x7 = this.f4933a.x();
        Intrinsics.checkNotNull(x7);
        x7.startOtaProcedure(dfuConfig);
    }
}
