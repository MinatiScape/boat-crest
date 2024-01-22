package com.coveiot.android.leonardo.performance;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.leonardo.performance.custommessage.Nudge1NoActivityManager;
import com.coveiot.android.leonardo.performance.custommessage.Nudge2BestActivityManager;
import com.coveiot.android.leonardo.performance.custommessage.Nudge3SleepGoalManager;
import com.coveiot.android.leonardo.performance.custommessage.Nudge4SleepScoreManager;
import com.coveiot.android.leonardo.performance.custommessage.Nudge5EnergyScoreManager;
import com.coveiot.android.leonardo.performance.custommessage.Nudge6StressManager;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class PerformanceWorker extends Worker {
    @NotNull
    public final Context n;
    public final String o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PerformanceWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.n = context;
        this.o = PerformanceWorker.class.getSimpleName();
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        Boolean isNudge1Enabled = KHPerformancePreferenceManager.getInstance(this.n).isNudge1Enabled();
        Intrinsics.checkNotNullExpressionValue(isNudge1Enabled, "getInstance(context).isNudge1Enabled");
        if (isNudge1Enabled.booleanValue()) {
            Nudge1NoActivityManager.Companion.calculateNudge1AndSendToBLE(this.n);
        } else {
            LogHelper.i(this.o, "Nudge1 not enabled.");
        }
        Boolean isNudge2Enabled = KHPerformancePreferenceManager.getInstance(this.n).isNudge2Enabled();
        Intrinsics.checkNotNullExpressionValue(isNudge2Enabled, "getInstance(context).isNudge2Enabled");
        if (isNudge2Enabled.booleanValue()) {
            Nudge2BestActivityManager.Companion.calculateNudge2AndSendToBLE(this.n);
        } else {
            LogHelper.i(this.o, "Nudge2 not enabled.");
        }
        Boolean isNudge3Enabled = KHPerformancePreferenceManager.getInstance(this.n).isNudge3Enabled();
        Intrinsics.checkNotNullExpressionValue(isNudge3Enabled, "getInstance(context).isNudge3Enabled");
        if (isNudge3Enabled.booleanValue()) {
            Nudge3SleepGoalManager.Companion.calculateNudge3AndSendToBLE(this.n);
        } else {
            LogHelper.i(this.o, "Nudge3 not enabled.");
        }
        Boolean isSleepScoreNudgeEnabled = KHPerformancePreferenceManager.getInstance(this.n).isSleepScoreNudgeEnabled();
        Intrinsics.checkNotNullExpressionValue(isSleepScoreNudgeEnabled, "getInstance(context).isSleepScoreNudgeEnabled");
        if (isSleepScoreNudgeEnabled.booleanValue()) {
            Nudge4SleepScoreManager.Companion.calculateNudge4AndSendToBLE(this.n);
        } else {
            LogHelper.i(this.o, "Nudge4 not enabled.");
        }
        Boolean isEnergyScoreNudgeEnabled = KHPerformancePreferenceManager.getInstance(this.n).isEnergyScoreNudgeEnabled();
        Intrinsics.checkNotNullExpressionValue(isEnergyScoreNudgeEnabled, "getInstance(context).isEnergyScoreNudgeEnabled");
        if (isEnergyScoreNudgeEnabled.booleanValue()) {
            Nudge5EnergyScoreManager.Companion.calculateNudge5AndSendToBLE(this.n);
        } else {
            LogHelper.i(this.o, "Nudge5 not enabled.");
        }
        Boolean isStressNudgeEnabled = KHPerformancePreferenceManager.getInstance(this.n).isStressNudgeEnabled();
        Intrinsics.checkNotNullExpressionValue(isStressNudgeEnabled, "getInstance(context).isStressNudgeEnabled");
        if (isStressNudgeEnabled.booleanValue() && BleApiManager.getInstance(this.n).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
            Nudge6StressManager.Companion.calculateNudge6AndSendToBLE(this.n);
        } else {
            LogHelper.i(this.o, "Nudge6 not enabled.");
        }
        ListenableWorker.Result success = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success, "success()");
        return success;
    }

    @NotNull
    public final Context getContext() {
        return this.n;
    }
}
