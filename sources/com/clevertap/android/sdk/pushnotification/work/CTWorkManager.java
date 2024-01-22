package com.clevertap.android.sdk.pushnotification.work;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CTWorkManager {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2669a;
    @NotNull
    public final String b;
    @NotNull
    public final Logger c;

    public CTWorkManager(@NotNull Context context, @NotNull CleverTapInstanceConfig config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.f2669a = context;
        String accountId = config.getAccountId();
        Intrinsics.checkNotNullExpressionValue(accountId, "config.accountId");
        this.b = accountId;
        Logger logger = config.getLogger();
        Intrinsics.checkNotNullExpressionValue(logger, "config.logger");
        this.c = logger;
    }

    public final void a() {
        this.c.verbose(this.b, "scheduling one time work request to flush push impressions...");
        try {
            Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresCharging(true).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …\n                .build()");
            OneTimeWorkRequest build2 = new OneTimeWorkRequest.Builder(CTFlushPushImpressionsWork.class).setConstraints(build).build();
            Intrinsics.checkNotNullExpressionValue(build2, "Builder(CTFlushPushImpre…\n                .build()");
            WorkManager.getInstance(this.f2669a).enqueueUniqueWork(Constants.FLUSH_PUSH_IMPRESSIONS_ONE_TIME_WORKER_NAME, ExistingWorkPolicy.KEEP, build2);
            this.c.verbose(this.b, "Finished scheduling one time work request to flush push impressions...");
        } catch (Throwable th) {
            this.c.verbose(this.b, "Failed to schedule one time work request to flush push impressions.", th);
            th.printStackTrace();
        }
    }

    public final void init() {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.f2669a, 26)) {
            Context context = this.f2669a;
            if (Utils.isMainProcess(context, context.getPackageName())) {
                a();
            }
        }
    }
}
