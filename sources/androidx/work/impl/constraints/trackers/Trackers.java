package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class Trackers {
    public static Trackers e;

    /* renamed from: a  reason: collision with root package name */
    public BatteryChargingTracker f1818a;
    public BatteryNotLowTracker b;
    public NetworkStateTracker c;
    public StorageNotLowTracker d;

    public Trackers(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        Context applicationContext = context.getApplicationContext();
        this.f1818a = new BatteryChargingTracker(applicationContext, taskExecutor);
        this.b = new BatteryNotLowTracker(applicationContext, taskExecutor);
        this.c = new NetworkStateTracker(applicationContext, taskExecutor);
        this.d = new StorageNotLowTracker(applicationContext, taskExecutor);
    }

    @NonNull
    public static synchronized Trackers getInstance(Context context, TaskExecutor taskExecutor) {
        Trackers trackers;
        synchronized (Trackers.class) {
            if (e == null) {
                e = new Trackers(context, taskExecutor);
            }
            trackers = e;
        }
        return trackers;
    }

    @VisibleForTesting
    public static synchronized void setInstance(@NonNull Trackers trackers) {
        synchronized (Trackers.class) {
            e = trackers;
        }
    }

    @NonNull
    public BatteryChargingTracker getBatteryChargingTracker() {
        return this.f1818a;
    }

    @NonNull
    public BatteryNotLowTracker getBatteryNotLowTracker() {
        return this.b;
    }

    @NonNull
    public NetworkStateTracker getNetworkStateTracker() {
        return this.c;
    }

    @NonNull
    public StorageNotLowTracker getStorageNotLowTracker() {
        return this.d;
    }
}
