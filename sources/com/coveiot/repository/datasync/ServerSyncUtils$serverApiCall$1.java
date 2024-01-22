package com.coveiot.repository.datasync;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class ServerSyncUtils$serverApiCall$1 implements CoveApiListener<ActivityRes, CoveApiErrorModel> {
    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
        Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onSuccess(@NotNull ActivityRes activityRes) {
        Intrinsics.checkNotNullParameter(activityRes, "activityRes");
    }
}
