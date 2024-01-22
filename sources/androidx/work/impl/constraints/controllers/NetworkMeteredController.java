package androidx.work.impl.constraints.controllers;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
/* loaded from: classes.dex */
public class NetworkMeteredController extends ConstraintController<NetworkState> {
    public static final String e = Logger.tagWithPrefix("NetworkMeteredCtrlr");

    public NetworkMeteredController(Context context, TaskExecutor taskExecutor) {
        super(Trackers.getInstance(context, taskExecutor).getNetworkStateTracker());
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean a(@NonNull WorkSpec workSpec) {
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.METERED;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    /* renamed from: d */
    public boolean b(@NonNull NetworkState networkState) {
        if (Build.VERSION.SDK_INT >= 26) {
            return (networkState.isConnected() && networkState.isMetered()) ? false : true;
        }
        Logger.get().debug(e, "Metered network constraint is not supported before API 26, only checking for connected state.", new Throwable[0]);
        return !networkState.isConnected();
    }
}
