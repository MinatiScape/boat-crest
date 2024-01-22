package com.coveiot.android.leonardo.more.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.boat.R;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.sifli.siflidfu.SifliDFUService;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdatePS1$otaBroadCastReceiver$1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdatePS1 f4947a;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1$otaBroadCastReceiver$1$onReceive$1", f = "ActivityFirmwareUpdatePS1.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $progress;
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdatePS1 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS1, int i, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdatePS1;
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ProgressBar progressBar = this.this$0.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(false);
                }
                ProgressBar progressBar2 = this.this$0.q;
                if (progressBar2 != null) {
                    progressBar2.setProgress(this.$progress);
                }
                TextView textView = this.this$0.r;
                if (textView != null) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.this$0.getResources().getString(R.string.dfu_uploading_percentage);
                    Intrinsics.checkNotNullExpressionValue(string, "this@ActivityFirmwareUpd…dfu_uploading_percentage)");
                    String format = String.format(string, Arrays.copyOf(new Object[]{Boxing.boxInt(this.$progress)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    textView.setText(format);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityFirmwareUpdatePS1$otaBroadCastReceiver$1(ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS1) {
        this.f4947a = activityFirmwareUpdatePS1;
    }

    public static final void b(ActivityFirmwareUpdatePS1 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.K();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        Intrinsics.checkNotNull(intent);
        String action = intent.getAction();
        if (action != null) {
            int hashCode = action.hashCode();
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean2 = null;
            if (hashCode == -1936294892) {
                if (action.equals(SifliDFUService.BROADCAST_DFU_PROGRESS)) {
                    int intExtra = intent.getIntExtra(SifliDFUService.EXTRA_DFU_PROGRESS, 0);
                    intent.getIntExtra(SifliDFUService.EXTRA_DFU_PROGRESS_TYPE, 0);
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4947a), Dispatchers.getMain(), null, new a(this.f4947a, intExtra, null), 2, null);
                }
            } else if (hashCode != 888644493) {
                if (hashCode == 1761809728 && action.equals(SifliDFUService.BROADCAST_DFU_LOG)) {
                    String stringExtra = intent.getStringExtra(SifliDFUService.EXTRA_LOG_MESSAGE);
                    String tag = this.f4947a.getTAG();
                    LogHelper.d(tag, "otaUpdateLog " + stringExtra);
                }
            } else if (action.equals(SifliDFUService.BROADCAST_DFU_STATE)) {
                int intExtra2 = intent.getIntExtra(SifliDFUService.EXTRA_DFU_STATE, 0);
                int intExtra3 = intent.getIntExtra(SifliDFUService.EXTRA_DFU_STATE_RESULT, 0);
                if (intExtra2 == 100) {
                    if (intExtra3 == 0) {
                        String tag2 = this.f4947a.getTAG();
                        LogHelper.d(tag2, "dfuStateResult " + intExtra3 + " OTA COMPLETED");
                        this.f4947a.setUpdatedSuccessfully(true);
                        Handler handler = this.f4947a.u;
                        final ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS1 = this.f4947a;
                        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.gd
                            @Override // java.lang.Runnable
                            public final void run() {
                                ActivityFirmwareUpdatePS1$otaBroadCastReceiver$1.b(ActivityFirmwareUpdatePS1.this);
                            }
                        }, 200L);
                    } else {
                        String tag3 = this.f4947a.getTAG();
                        LogHelper.d(tag3, "dfuStateResult OTA UPDATE FAILED " + intExtra3);
                        if (!this.f4947a.isUpdatedSuccessfully()) {
                            ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS12 = this.f4947a;
                            activityFirmwareUpdatePS12.M(SessionManager.getInstance(activityFirmwareUpdatePS12).getConnectedDeviceMacAddress());
                            ActivityFirmwareUpdatePS1 activityFirmwareUpdatePS13 = this.f4947a;
                            Resources resources = activityFirmwareUpdatePS13.getResources();
                            Object[] objArr = new Object[1];
                            firmwareBean = this.f4947a.t;
                            if (firmwareBean == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                            } else {
                                firmwareBean2 = firmwareBean;
                            }
                            objArr[0] = firmwareBean2.getUpdateVersion();
                            String string = resources.getString(R.string.fw_update_failure, objArr);
                            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…rmwareBean.updateVersion)");
                            activityFirmwareUpdatePS13.Q(string, "");
                        }
                    }
                }
                String tag4 = this.f4947a.getTAG();
                LogHelper.d(tag4, "dfuStateResult OTA UPDATE Unknown state " + intExtra2);
            }
        }
    }
}
