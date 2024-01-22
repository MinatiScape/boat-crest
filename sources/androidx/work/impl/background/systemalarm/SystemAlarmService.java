package androidx.work.impl.background.systemalarm;

import android.content.Intent;
import androidx.annotation.MainThread;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.utils.WakeLocks;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SystemAlarmService extends LifecycleService implements SystemAlarmDispatcher.c {
    public static final String k = Logger.tagWithPrefix("SystemAlarmService");
    public SystemAlarmDispatcher i;
    public boolean j;

    @MainThread
    public final void a() {
        SystemAlarmDispatcher systemAlarmDispatcher = new SystemAlarmDispatcher(this);
        this.i = systemAlarmDispatcher;
        systemAlarmDispatcher.k(this);
    }

    @Override // androidx.work.impl.background.systemalarm.SystemAlarmDispatcher.c
    @MainThread
    public void onAllCommandsCompleted() {
        this.j = true;
        Logger.get().debug(k, "All commands completed in dispatcher", new Throwable[0]);
        WakeLocks.checkWakeLocks();
        stopSelf();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        a();
        this.j = false;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.j = true;
        this.i.h();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.j) {
            Logger.get().info(k, "Re-initializing SystemAlarmDispatcher after a request to shut-down.", new Throwable[0]);
            this.i.h();
            a();
            this.j = false;
        }
        if (intent != null) {
            this.i.add(intent, i2);
            return 3;
        }
        return 3;
    }
}
