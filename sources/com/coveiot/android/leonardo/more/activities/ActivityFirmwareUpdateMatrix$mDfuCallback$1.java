package com.coveiot.android.leonardo.more.activities;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.boat.R;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.htsmart.wristband2.dfu.DfuCallback;
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
public final class ActivityFirmwareUpdateMatrix$mDfuCallback$1 implements DfuCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateMatrix f4939a;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix$mDfuCallback$1$onProgressChanged$1", f = "ActivityFirmwareUpdateMatrix.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $progress;
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateMatrix this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix, int i, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateMatrix;
            this.$progress = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, this.$progress, continuation);
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                progressBar = this.this$0.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(false);
                }
                progressBar2 = this.this$0.q;
                if (progressBar2 != null) {
                    progressBar2.setProgress(this.$progress);
                }
                ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = this.this$0;
                int i = R.id.tv_progress_percentage;
                ((TextView) activityFirmwareUpdateMatrix._$_findCachedViewById(i)).setVisibility(0);
                ((TextView) this.this$0._$_findCachedViewById(i)).setText(this.this$0.getString(R.string.dfu_uploading_percentage, new Object[]{Boxing.boxInt(this.$progress)}));
                TextView textView = this.this$0.r;
                if (textView != null) {
                    textView.setText(this.this$0.getString(R.string.sending));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityFirmwareUpdateMatrix$mDfuCallback$1(ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix) {
        this.f4939a = activityFirmwareUpdateMatrix;
    }

    public static final void c(ActivityFirmwareUpdateMatrix this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    public static final void d(ActivityFirmwareUpdateMatrix this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.F();
        Object systemService = this$0.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(283);
    }

    @Override // com.htsmart.wristband2.dfu.DfuCallback
    public void onError(int i, int i2) {
        Handler handler;
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        handler = this.f4939a.u;
        final ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = this.f4939a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.jc
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateMatrix$mDfuCallback$1.c(ActivityFirmwareUpdateMatrix.this);
            }
        }, 200L);
        ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix2 = this.f4939a;
        Object[] objArr = new Object[1];
        firmwareBean = activityFirmwareUpdateMatrix2.t;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = activityFirmwareUpdateMatrix2.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_up…rmwareBean.updateVersion)");
        activityFirmwareUpdateMatrix2.K(string, "");
        this.f4939a.logError(i, i2);
    }

    @Override // com.htsmart.wristband2.dfu.DfuCallback
    @SuppressLint({"StringFormatMatches"})
    public void onProgressChanged(int i) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4939a), Dispatchers.getMain(), null, new a(this.f4939a, i, null), 2, null);
    }

    @Override // com.htsmart.wristband2.dfu.DfuCallback
    public void onStateChanged(int i, boolean z) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        ProgressBar progressBar3;
        ProgressBar progressBar4;
        if (i == 1) {
            progressBar = this.f4939a.q;
            if (progressBar != null) {
                progressBar.setIndeterminate(true);
            }
            TextView textView = this.f4939a.r;
            if (textView != null) {
                textView.setText(R.string.dfu_state_dfu_file);
            }
        } else if (i == 2) {
            progressBar2 = this.f4939a.q;
            if (progressBar2 != null) {
                progressBar2.setIndeterminate(true);
            }
            TextView textView2 = this.f4939a.r;
            if (textView2 != null) {
                textView2.setText(R.string.dfu_state_dfu_mode);
            }
        } else if (i == 3) {
            progressBar3 = this.f4939a.q;
            if (progressBar3 != null) {
                progressBar3.setIndeterminate(true);
            }
            TextView textView3 = this.f4939a.r;
            if (textView3 != null) {
                textView3.setText(R.string.dfu_state_dfu_device);
            }
        } else if (i != 4) {
        } else {
            progressBar4 = this.f4939a.q;
            if (progressBar4 != null) {
                progressBar4.setIndeterminate(true);
            }
            TextView textView4 = this.f4939a.r;
            if (textView4 != null) {
                textView4.setText(R.string.dfu_state_dfu_process);
            }
        }
    }

    @Override // com.htsmart.wristband2.dfu.DfuCallback
    public void onSuccess() {
        Handler handler;
        handler = this.f4939a.u;
        final ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = this.f4939a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.kc
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateMatrix$mDfuCallback$1.d(ActivityFirmwareUpdateMatrix.this);
            }
        }, 200L);
    }
}
