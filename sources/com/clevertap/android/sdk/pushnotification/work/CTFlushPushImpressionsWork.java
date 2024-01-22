package com.clevertap.android.sdk.pushnotification.work;

import android.content.Context;
import androidx.annotation.RequiresApi;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CTFlushPushImpressionsWork extends Worker {
    @NotNull
    public final String n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CTFlushPushImpressionsWork(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.n = "CTFlushPushImpressionsWork";
    }

    public final boolean a() {
        if (isStopped()) {
            Logger.d(this.n, "someone told me to stop flushing and go to sleep again! going to sleep now.ˁ(-.-)ˀzzZZ");
        }
        return isStopped();
    }

    @Override // androidx.work.Worker
    @RequiresApi(api = 21)
    @NotNull
    public ListenableWorker.Result doWork() {
        Logger.d(this.n, "hello, this is FlushPushImpressionsWork from CleverTap. I am awake now and ready to flush push impressions:-)");
        Logger.d(this.n, "initiating push impressions flush...");
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        ArrayList<CleverTapAPI> availableInstances = CleverTapAPI.getAvailableInstances(applicationContext);
        Intrinsics.checkNotNullExpressionValue(availableInstances, "getAvailableInstances(context)");
        List filterNotNull = CollectionsKt___CollectionsKt.filterNotNull(availableInstances);
        ArrayList<CleverTapAPI> arrayList = new ArrayList();
        for (Object obj : filterNotNull) {
            if (!((CleverTapAPI) obj).getCoreState().getConfig().isAnalyticsOnly()) {
                arrayList.add(obj);
            }
        }
        for (CleverTapAPI cleverTapAPI : arrayList) {
            if (a()) {
                ListenableWorker.Result success = ListenableWorker.Result.success();
                Intrinsics.checkNotNullExpressionValue(success, "success()");
                return success;
            }
            String str = this.n;
            Logger.d(str, "flushing queue for push impressions on CT instance = " + cleverTapAPI.getAccountId());
            CTXtensions.flushPushImpressionsOnPostAsyncSafely(cleverTapAPI, this.n, Constants.D_SRC_PI_WM, applicationContext);
        }
        Logger.d(this.n, "flush push impressions work is DONE! going to sleep now...ˁ(-.-)ˀzzZZ");
        ListenableWorker.Result success2 = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success2, "success()");
        return success2;
    }

    @NotNull
    public final String getTag() {
        return this.n;
    }
}
