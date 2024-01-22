package com.coveiot.android.leonardo.more.activities;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.utils.utility.LogHelper;
import com.jieli.jl_bt_ota.interfaces.IUpgradeCallback;
import com.jieli.jl_bt_ota.model.base.BaseError;
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
public final class ActivityFirmwareUpdateSmaJL$startOTA$1 implements IUpgradeCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateSmaJL f4955a;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$startOTA$1$onProgress$1", f = "ActivityFirmwareUpdateSmaJL.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ float $progress;
        public final /* synthetic */ int $type;
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateSmaJL this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL, int i, float f, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateSmaJL;
            this.$type = i;
            this.$progress = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, this.$type, this.$progress, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ProgressBar progressBar;
            ProgressBar progressBar2;
            TextView textView;
            ProgressBar progressBar3;
            TextView textView2;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                String tag = this.this$0.getTAG();
                LogHelper.d(tag, "onProgress -> " + this.$type + ' ' + this.$progress);
                if (this.$type == 0) {
                    this.this$0.w = ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADE_CHECKING;
                    progressBar3 = this.this$0.q;
                    if (progressBar3 != null) {
                        progressBar3.setIndeterminate(true);
                    }
                    textView2 = this.this$0.r;
                    if (textView2 != null) {
                        textView2.setText(this.this$0.getString(R.string.verifying));
                    }
                    LogHelper.d(this.this$0.getTAG(), "UPGRADE_CHECKING");
                } else {
                    this.this$0.w = ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADEING;
                    progressBar = this.this$0.q;
                    if (progressBar != null) {
                        progressBar.setIndeterminate(false);
                    }
                    progressBar2 = this.this$0.q;
                    if (progressBar2 != null) {
                        progressBar2.setProgress(kotlin.math.c.roundToInt(this.$progress));
                    }
                    ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = this.this$0;
                    int i = R.id.tv_progress_percentage;
                    ((TextView) activityFirmwareUpdateSmaJL._$_findCachedViewById(i)).setVisibility(0);
                    ((TextView) this.this$0._$_findCachedViewById(i)).setText(this.this$0.getString(R.string.dfu_uploading_percentage, new Object[]{Boxing.boxInt(kotlin.math.c.roundToInt(this.$progress))}));
                    textView = this.this$0.r;
                    if (textView != null) {
                        textView.setText(this.this$0.getString(R.string.sending));
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityFirmwareUpdateSmaJL$startOTA$1(ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL) {
        this.f4955a = activityFirmwareUpdateSmaJL;
    }

    public static final void c(ActivityFirmwareUpdateSmaJL this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void d(ActivityFirmwareUpdateSmaJL this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.H();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onCancelOTA() {
        ProgressBar progressBar;
        TextView textView;
        this.f4955a.N(ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADE_FAILED);
        progressBar = this.f4955a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4955a.r;
        if (textView != null) {
            textView.setText(this.f4955a.getString(R.string.failed_message));
        }
        LogHelper.d(this.f4955a.getTAG(), "STATE_CONNECT_FAILED");
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    @SuppressLint({"StringFormatInvalid"})
    public void onError(@Nullable BaseError baseError) {
        ActivityFirmwareUpdateSmaJL.OTAStatus oTAStatus;
        Handler handler;
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        String tag = this.f4955a.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("onError -> status = ");
        oTAStatus = this.f4955a.w;
        sb.append(oTAStatus);
        sb.append(", error = ");
        sb.append(baseError);
        LogHelper.d(tag, sb.toString());
        this.f4955a.N(ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADE_FAILED);
        handler = this.f4955a.z;
        final ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = this.f4955a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.od
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateSmaJL$startOTA$1.c(ActivityFirmwareUpdateSmaJL.this);
            }
        }, 200L);
        ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL2 = this.f4955a;
        Object[] objArr = new Object[1];
        firmwareBean = activityFirmwareUpdateSmaJL2.u;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = activityFirmwareUpdateSmaJL2.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
        activityFirmwareUpdateSmaJL2.K(string, "");
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onNeedReconnect(@Nullable String str, boolean z) {
        String tag = this.f4955a.getTAG();
        LogHelper.d(tag, "onNeedReconnect : " + str + ", " + z);
        this.f4955a.v = true;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    @SuppressLint({"StringFormatInvalid"})
    public void onProgress(int i, float f) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4955a), Dispatchers.getMain(), null, new a(this.f4955a, i, f, null), 2, null);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onStartOTA() {
        ProgressBar progressBar;
        TextView textView;
        this.f4955a.N(ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADE_START);
        progressBar = this.f4955a.q;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        textView = this.f4955a.r;
        if (textView != null) {
            textView.setText(this.f4955a.getString(R.string.starting));
        }
        LogHelper.d(this.f4955a.getTAG(), "STATE_OTA_PROCESSING");
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeCallback
    public void onStopOTA() {
        Handler handler;
        LogHelper.d(this.f4955a.getTAG(), "onStopOTA() upgrade ok");
        this.f4955a.N(ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADE_STOP);
        this.f4955a.v = false;
        handler = this.f4955a.z;
        final ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = this.f4955a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.pd
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateSmaJL$startOTA$1.d(ActivityFirmwareUpdateSmaJL.this);
            }
        }, 200L);
    }
}
