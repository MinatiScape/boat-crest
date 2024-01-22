package androidx.work.impl.foreground;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SystemForegroundService extends LifecycleService implements SystemForegroundDispatcher.b {
    public static final String m = Logger.tagWithPrefix("SystemFgService");
    @Nullable
    public static SystemForegroundService n = null;
    public Handler i;
    public boolean j;
    public SystemForegroundDispatcher k;
    public NotificationManager l;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ int h;
        public final /* synthetic */ Notification i;
        public final /* synthetic */ int j;

        public a(int i, Notification notification, int i2) {
            this.h = i;
            this.i = notification;
            this.j = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Build.VERSION.SDK_INT >= 29) {
                SystemForegroundService.this.startForeground(this.h, this.i, this.j);
            } else {
                SystemForegroundService.this.startForeground(this.h, this.i);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ int h;
        public final /* synthetic */ Notification i;

        public b(int i, Notification notification) {
            this.h = i;
            this.i = notification;
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemForegroundService.this.l.notify(this.h, this.i);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ int h;

        public c(int i) {
            this.h = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemForegroundService.this.l.cancel(this.h);
        }
    }

    @Nullable
    public static SystemForegroundService getInstance() {
        return n;
    }

    @MainThread
    public final void a() {
        this.i = new Handler(Looper.getMainLooper());
        this.l = (NotificationManager) getApplicationContext().getSystemService("notification");
        SystemForegroundDispatcher systemForegroundDispatcher = new SystemForegroundDispatcher(getApplicationContext());
        this.k = systemForegroundDispatcher;
        systemForegroundDispatcher.g(this);
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.b
    public void cancelNotification(int i) {
        this.i.post(new c(i));
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.b
    public void notify(int i, @NonNull Notification notification) {
        this.i.post(new b(i, notification));
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onCreate() {
        super.onCreate();
        n = this;
        a();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.k.e();
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.j) {
            Logger.get().info(m, "Re-initializing SystemForegroundService after a request to shut-down.", new Throwable[0]);
            this.k.e();
            a();
            this.j = false;
        }
        if (intent != null) {
            this.k.f(intent);
            return 3;
        }
        return 3;
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.b
    public void startForeground(int i, int i2, @NonNull Notification notification) {
        this.i.post(new a(i, notification, i2));
    }

    @Override // androidx.work.impl.foreground.SystemForegroundDispatcher.b
    @MainThread
    public void stop() {
        this.j = true;
        Logger.get().debug(m, "All commands completed.", new Throwable[0]);
        if (Build.VERSION.SDK_INT >= 26) {
            stopForeground(true);
        }
        n = null;
        stopSelf();
    }
}
