package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
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
public final class ActivityFirmwareUpdateMoyang$initiateDFU$1$1$onDataResponse$1 implements DataResultListener {
    public final /* synthetic */ ActivityFirmwareUpdateMoyang h;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$initiateDFU$1$1$onDataResponse$1$onProgressUpdate$1", f = "ActivityFirmwareUpdateMoyang.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProgressData $progress;
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateMoyang this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang, ProgressData progressData, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateMoyang;
            this.$progress = progressData;
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
                    progressBar2.setProgress(this.$progress.getProgress());
                }
                ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = this.this$0;
                int i = R.id.tv_progress_percentage;
                ((TextView) activityFirmwareUpdateMoyang._$_findCachedViewById(i)).setVisibility(0);
                ((TextView) this.this$0._$_findCachedViewById(i)).setText(this.this$0.getString(R.string.dfu_uploading_percentage, new Object[]{Boxing.boxInt(this.$progress.getProgress())}));
                textView = this.this$0.r;
                if (textView != null) {
                    textView.setText(this.this$0.getString(R.string.sending));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityFirmwareUpdateMoyang$initiateDFU$1$1$onDataResponse$1(ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang) {
        this.h = activityFirmwareUpdateMoyang;
    }

    public static final void b(ActivityFirmwareUpdateMoyang this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.C();
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataError(@NotNull BleBaseError error) {
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        Intrinsics.checkNotNullParameter(error, "error");
        ((TextView) this.h.findViewById(R.id.toolbar_title)).setVisibility(8);
        ((TextView) this.h.findViewById(R.id.toolbar_back_arrow)).setVisibility(8);
        ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = this.h;
        Object[] objArr = new Object[1];
        firmwareBean = activityFirmwareUpdateMoyang.u;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = activityFirmwareUpdateMoyang.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
        activityFirmwareUpdateMoyang.G(string, "");
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataResponse(@NotNull BleBaseResponse response) {
        Handler handler;
        Intrinsics.checkNotNullParameter(response, "response");
        handler = this.h.v;
        final ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = this.h;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.uc
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateMoyang$initiateDFU$1$1$onDataResponse$1.b(ActivityFirmwareUpdateMoyang.this);
            }
        }, 200L);
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onProgressUpdate(@NotNull ProgressData progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.h), Dispatchers.getMain(), null, new a(this.h, progress, null), 2, null);
    }
}
