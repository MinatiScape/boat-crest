package com.coveiot.android.leonardo.googlefit;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.coveiot.utils.utility.LogHelper;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GoogleFitSyncWorkManager extends Worker {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static String n = GoogleFitSyncWorkManager.class.getSimpleName();
    @NotNull
    public static final String o = "GOOGLE_FIT_SYNC";
    @Nullable
    public static GoogleFitDataManager p;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void cancelAll(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                WorkManager.getInstance(context).cancelUniqueWork(GoogleFitSyncWorkManager.o);
                LogHelper.d(getTAG$app_prodRelease(), "Cleared all tasks");
            } catch (Exception e) {
                e.printStackTrace();
                LogHelper.d(getTAG$app_prodRelease(), "Exception while clearing tasks");
            }
        }

        @Nullable
        public final GoogleFitDataManager getDataManager$app_prodRelease() {
            return GoogleFitSyncWorkManager.p;
        }

        public final String getTAG$app_prodRelease() {
            return GoogleFitSyncWorkManager.n;
        }

        public final void scheduleRepeat(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresCharging(false).build();
                Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …esCharging(false).build()");
                PeriodicWorkRequest build2 = new PeriodicWorkRequest.Builder(GoogleFitSyncWorkManager.class, 1L, TimeUnit.HOURS).addTag(GoogleFitSyncWorkManager.o).setConstraints(build).build();
                Intrinsics.checkNotNullExpressionValue(build2, "PeriodicWorkRequestBuild…                 .build()");
                WorkManager.getInstance(context).enqueueUniquePeriodicWork(GoogleFitSyncWorkManager.o, ExistingPeriodicWorkPolicy.REPLACE, build2);
                setDataManager$app_prodRelease(new GoogleFitDataManager(context));
                Log.v(getTAG$app_prodRelease(), "repeating task scheduled");
            } catch (Exception e) {
                LogHelper.e(getTAG$app_prodRelease(), "scheduling failed");
                e.printStackTrace();
            }
        }

        public final void setDataManager$app_prodRelease(@Nullable GoogleFitDataManager googleFitDataManager) {
            GoogleFitSyncWorkManager.p = googleFitDataManager;
        }

        public final void setTAG$app_prodRelease(String str) {
            GoogleFitSyncWorkManager.n = str;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GoogleFitSyncWorkManager(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    public static final void b() {
        GoogleFitDataManager googleFitDataManager = p;
        if (googleFitDataManager != null) {
            Intrinsics.checkNotNull(googleFitDataManager);
            googleFitDataManager.insertAndReadData();
            return;
        }
        LogHelper.d(n, "data manager is null");
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        Handler handler = new Handler(Looper.getMainLooper());
        WorkManager workManager = WorkManager.getInstance(getApplicationContext());
        String str = o;
        if (workManager.getWorkInfosByTag(str).equals(str)) {
            handler.post(new Runnable() { // from class: com.coveiot.android.leonardo.googlefit.o
                @Override // java.lang.Runnable
                public final void run() {
                    GoogleFitSyncWorkManager.b();
                }
            });
        }
        ListenableWorker.Result success = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(success, "success()");
        return success;
    }
}
