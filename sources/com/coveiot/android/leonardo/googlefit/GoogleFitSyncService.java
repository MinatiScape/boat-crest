package com.coveiot.android.leonardo.googlefit;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.TaskParams;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GoogleFitSyncService extends GcmTaskService {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String o = "GOOGLE_FIT_SYNC";
    public static final int p = 60;
    public static String q = GoogleFitSyncService.class.getSimpleName();
    public static final int r = 60 * 60;
    @Nullable
    public static GoogleFitDataManager s;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void cancelAll(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            GcmNetworkManager.getInstance(context).cancelAllTasks(GoogleFitSyncService.class);
            LogHelper.d(getTAG$app_prodRelease(), "Cleared all tasks");
        }

        @Nullable
        public final GoogleFitDataManager getDataManager$app_prodRelease() {
            return GoogleFitSyncService.s;
        }

        public final String getTAG$app_prodRelease() {
            return GoogleFitSyncService.q;
        }

        public final void scheduleRepeat(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                GcmNetworkManager.getInstance(context).schedule(new PeriodicTask.Builder().setService(GoogleFitSyncService.class).setPeriod(GoogleFitSyncService.r).setTag(GoogleFitSyncService.o).setPersisted(true).setUpdateCurrent(true).setRequiredNetwork(0).setRequiresCharging(false).build());
                setDataManager$app_prodRelease(new GoogleFitDataManager(context));
                Log.v(getTAG$app_prodRelease(), "repeating task scheduled");
            } catch (Exception e) {
                LogHelper.e(getTAG$app_prodRelease(), "scheduling failed");
                e.printStackTrace();
            }
        }

        public final void setDataManager$app_prodRelease(@Nullable GoogleFitDataManager googleFitDataManager) {
            GoogleFitSyncService.s = googleFitDataManager;
        }

        public final void setTAG$app_prodRelease(String str) {
            GoogleFitSyncService.q = str;
        }
    }

    public static final void l() {
        GoogleFitDataManager googleFitDataManager = s;
        if (googleFitDataManager != null) {
            Intrinsics.checkNotNull(googleFitDataManager);
            googleFitDataManager.insertAndReadData();
            return;
        }
        LogHelper.d(q, "data manager is null");
    }

    @Override // com.google.android.gms.gcm.GcmTaskService
    public int onRunTask(@NotNull TaskParams taskParams) {
        Intrinsics.checkNotNullParameter(taskParams, "taskParams");
        Handler handler = new Handler(getMainLooper());
        if (Intrinsics.areEqual(taskParams.getTag(), o)) {
            handler.post(new Runnable() { // from class: com.coveiot.android.leonardo.googlefit.n
                @Override // java.lang.Runnable
                public final void run() {
                    GoogleFitSyncService.l();
                }
            });
            return 0;
        }
        return 0;
    }
}
