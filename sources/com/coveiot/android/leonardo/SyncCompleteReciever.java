package com.coveiot.android.leonardo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.googlefit.GoogleFitDataManager;
import com.coveiot.android.leonardo.performance.PerformanceWorker;
import com.coveiot.android.respiratoryrate.RespiratoryRateManager;
import com.coveiot.android.respiratoryrate.RespiratoryRatePreferenceManager;
import com.coveiot.covepreferences.UserDataManager;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
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
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SyncCompleteReciever extends BroadcastReceiver {

    @DebugMetadata(c = "com.coveiot.android.leonardo.SyncCompleteReciever$onReceive$1", f = "SyncCompleteReciever.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                RespiratoryRateManager.Companion companion = RespiratoryRateManager.Companion;
                Context context = this.$context;
                this.label = 1;
                if (companion.syncData(context, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        boolean z = true;
        if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isRespiratoryRateByPPGSupported()) {
            z = false;
        }
        if (z && UserDataManager.getInstance(context).isRespiratoryRateFeatureEnabled(context)) {
            if (System.currentTimeMillis() > RespiratoryRatePreferenceManager.getInstance(context).getLastRunTimestamp() + 60000) {
                RespiratoryRatePreferenceManager.getInstance(context).saveLastRunTimestamp(System.currentTimeMillis());
                e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(context, null), 2, null);
            }
        }
        if (UserDataManager.getInstance(context).isGoogleFitConnected(context)) {
            new GoogleFitDataManager(context).insertAndReadData();
        }
        Boolean isPerformanceBasedNotificationEnabled = KHPerformancePreferenceManager.getInstance(context).isPerformanceBasedNotificationEnabled();
        Intrinsics.checkNotNullExpressionValue(isPerformanceBasedNotificationEnabled, "getInstance(context).isP…eBasedNotificationEnabled");
        if (isPerformanceBasedNotificationEnabled.booleanValue()) {
            Calendar calendar = Calendar.getInstance();
            if (calendar.get(11) > 19 || calendar.get(11) < 8) {
                return;
            }
            if (System.currentTimeMillis() > KHPerformancePreferenceManager.getInstance(context).getLastPerformanceWorkerRunTimestamp() + 60000) {
                KHPerformancePreferenceManager.getInstance(context).saveLastPerformanceWorkerRunTimestamp(System.currentTimeMillis());
                OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(PerformanceWorker.class).setInitialDelay(15L, TimeUnit.SECONDS).build();
                Intrinsics.checkNotNullExpressionValue(build, "OneTimeWorkRequestBuilde…                 .build()");
                WorkManager.getInstance().enqueue(build);
            }
        }
    }
}
