package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SystemForegroundDispatcher implements WorkConstraintsCallback, ExecutionListener {
    public static final String r = Logger.tagWithPrefix("SystemFgDispatcher");
    public Context h;
    public WorkManagerImpl i;
    public final TaskExecutor j;
    public final Object k = new Object();
    public String l;
    public final Map<String, ForegroundInfo> m;
    public final Map<String, WorkSpec> n;
    public final Set<WorkSpec> o;
    public final WorkConstraintsTracker p;
    @Nullable
    public b q;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ WorkDatabase h;
        public final /* synthetic */ String i;

        public a(WorkDatabase workDatabase, String str) {
            this.h = workDatabase;
            this.i = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            WorkSpec workSpec = this.h.workSpecDao().getWorkSpec(this.i);
            if (workSpec == null || !workSpec.hasConstraints()) {
                return;
            }
            synchronized (SystemForegroundDispatcher.this.k) {
                SystemForegroundDispatcher.this.n.put(this.i, workSpec);
                SystemForegroundDispatcher.this.o.add(workSpec);
                SystemForegroundDispatcher systemForegroundDispatcher = SystemForegroundDispatcher.this;
                systemForegroundDispatcher.p.replace(systemForegroundDispatcher.o);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void cancelNotification(int i);

        void notify(int i, @NonNull Notification notification);

        void startForeground(int i, int i2, @NonNull Notification notification);

        void stop();
    }

    public SystemForegroundDispatcher(@NonNull Context context) {
        this.h = context;
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
        this.i = workManagerImpl;
        TaskExecutor workTaskExecutor = workManagerImpl.getWorkTaskExecutor();
        this.j = workTaskExecutor;
        this.l = null;
        this.m = new LinkedHashMap();
        this.o = new HashSet();
        this.n = new HashMap();
        this.p = new WorkConstraintsTracker(this.h, workTaskExecutor, this);
        this.i.getProcessor().addExecutionListener(this);
    }

    @NonNull
    public static Intent createCancelWorkIntent(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_CANCEL_WORK");
        intent.setData(Uri.parse(String.format("workspec://%s", str)));
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    @NonNull
    public static Intent createNotifyIntent(@NonNull Context context, @NonNull String str, @NonNull ForegroundInfo foregroundInfo) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_NOTIFY");
        intent.putExtra("KEY_NOTIFICATION_ID", foregroundInfo.getNotificationId());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo.getForegroundServiceType());
        intent.putExtra("KEY_NOTIFICATION", foregroundInfo.getNotification());
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    @NonNull
    public static Intent createStartForegroundIntent(@NonNull Context context, @NonNull String str, @NonNull ForegroundInfo foregroundInfo) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_START_FOREGROUND");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_NOTIFICATION_ID", foregroundInfo.getNotificationId());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo.getForegroundServiceType());
        intent.putExtra("KEY_NOTIFICATION", foregroundInfo.getNotification());
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    @NonNull
    public static Intent createStopForegroundIntent(@NonNull Context context) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_STOP_FOREGROUND");
        return intent;
    }

    @MainThread
    public final void a(@NonNull Intent intent) {
        Logger.get().info(r, String.format("Stopping foreground work for %s", intent), new Throwable[0]);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra == null || TextUtils.isEmpty(stringExtra)) {
            return;
        }
        this.i.cancelWorkById(UUID.fromString(stringExtra));
    }

    @MainThread
    public final void b(@NonNull Intent intent) {
        int i = 0;
        int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
        Logger.get().debug(r, String.format("Notifying with (id: %s, workSpecId: %s, notificationType: %s)", Integer.valueOf(intExtra), stringExtra, Integer.valueOf(intExtra2)), new Throwable[0]);
        if (notification == null || this.q == null) {
            return;
        }
        this.m.put(stringExtra, new ForegroundInfo(intExtra, notification, intExtra2));
        if (TextUtils.isEmpty(this.l)) {
            this.l = stringExtra;
            this.q.startForeground(intExtra, intExtra2, notification);
            return;
        }
        this.q.notify(intExtra, notification);
        if (intExtra2 == 0 || Build.VERSION.SDK_INT < 29) {
            return;
        }
        for (Map.Entry<String, ForegroundInfo> entry : this.m.entrySet()) {
            i |= entry.getValue().getForegroundServiceType();
        }
        ForegroundInfo foregroundInfo = this.m.get(this.l);
        if (foregroundInfo != null) {
            this.q.startForeground(foregroundInfo.getNotificationId(), i, foregroundInfo.getNotification());
        }
    }

    @MainThread
    public final void c(@NonNull Intent intent) {
        Logger.get().info(r, String.format("Started foreground service %s", intent), new Throwable[0]);
        this.j.executeOnBackgroundThread(new a(this.i.getWorkDatabase(), intent.getStringExtra("KEY_WORKSPEC_ID")));
    }

    @MainThread
    public void d(@NonNull Intent intent) {
        Logger.get().info(r, "Stopping foreground service", new Throwable[0]);
        b bVar = this.q;
        if (bVar != null) {
            bVar.stop();
        }
    }

    @MainThread
    public void e() {
        this.q = null;
        synchronized (this.k) {
            this.p.reset();
        }
        this.i.getProcessor().removeExecutionListener(this);
    }

    public void f(@NonNull Intent intent) {
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            c(intent);
            b(intent);
        } else if ("ACTION_NOTIFY".equals(action)) {
            b(intent);
        } else if ("ACTION_CANCEL_WORK".equals(action)) {
            a(intent);
        } else if ("ACTION_STOP_FOREGROUND".equals(action)) {
            d(intent);
        }
    }

    @MainThread
    public void g(@NonNull b bVar) {
        if (this.q != null) {
            Logger.get().error(r, "A callback already exists.", new Throwable[0]);
        } else {
            this.q = bVar;
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> list) {
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> list) {
        if (list.isEmpty()) {
            return;
        }
        for (String str : list) {
            Logger.get().debug(r, String.format("Constraints unmet for WorkSpec %s", str), new Throwable[0]);
            this.i.stopForegroundWork(str);
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    @MainThread
    public void onExecuted(@NonNull String str, boolean z) {
        Map.Entry<String, ForegroundInfo> entry;
        synchronized (this.k) {
            WorkSpec remove = this.n.remove(str);
            if (remove != null ? this.o.remove(remove) : false) {
                this.p.replace(this.o);
            }
        }
        ForegroundInfo remove2 = this.m.remove(str);
        if (str.equals(this.l) && this.m.size() > 0) {
            Iterator<Map.Entry<String, ForegroundInfo>> it = this.m.entrySet().iterator();
            Map.Entry<String, ForegroundInfo> next = it.next();
            while (true) {
                entry = next;
                if (!it.hasNext()) {
                    break;
                }
                next = it.next();
            }
            this.l = entry.getKey();
            if (this.q != null) {
                ForegroundInfo value = entry.getValue();
                this.q.startForeground(value.getNotificationId(), value.getForegroundServiceType(), value.getNotification());
                this.q.cancelNotification(value.getNotificationId());
            }
        }
        b bVar = this.q;
        if (remove2 == null || bVar == null) {
            return;
        }
        Logger.get().debug(r, String.format("Removing Notification (id: %s, workSpecId: %s ,notificationType: %s)", Integer.valueOf(remove2.getNotificationId()), str, Integer.valueOf(remove2.getForegroundServiceType())), new Throwable[0]);
        bVar.cancelNotification(remove2.getNotificationId());
    }
}
