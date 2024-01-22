package com.google.android.gms.gcm;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gcm.zzj;
import com.google.android.gms.internal.gcm.zzm;
import com.google.android.gms.internal.gcm.zzq;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import javax.annotation.concurrent.GuardedBy;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes6.dex */
public abstract class GcmTaskService extends Service {
    public static final String SERVICE_ACTION_EXECUTE_TASK = "com.google.android.gms.gcm.ACTION_TASK_READY";
    public static final String SERVICE_ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
    public static final String SERVICE_PERMISSION = "com.google.android.gms.permission.BIND_NETWORK_TASK_SERVICE";
    public final Object h = new Object();
    @GuardedBy("lock")
    public int i;
    public ExecutorService j;
    public Messenger k;
    public ComponentName l;
    public GcmNetworkManager m;
    public com.google.android.gms.internal.gcm.zzl n;

    @VisibleForTesting
    @TargetApi(21)
    /* loaded from: classes6.dex */
    public class a extends zzj {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Messenger messenger;
            if (!UidVerifier.uidHasPackageName(GcmTaskService.this, message.sendingUid, "com.google.android.gms")) {
                Log.e("GcmTaskService", "unable to verify presence of Google Play Services");
                return;
            }
            int i = message.what;
            if (i == 1) {
                Bundle data = message.getData();
                if (data.isEmpty() || (messenger = message.replyTo) == null) {
                    return;
                }
                String string = data.getString(HeaderParameterNames.AUTHENTICATION_TAG);
                ArrayList parcelableArrayList = data.getParcelableArrayList("triggered_uris");
                long j = data.getLong("max_exec_duration", 180L);
                if (GcmTaskService.this.i(string)) {
                    return;
                }
                GcmTaskService.this.c(new b(string, messenger, data.getBundle(NotificationCompat.MessagingStyle.Message.KEY_EXTRAS_BUNDLE), j, parcelableArrayList));
            } else if (i == 2) {
                if (Log.isLoggable("GcmTaskService", 3)) {
                    String valueOf = String.valueOf(message);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 45);
                    sb.append("ignoring unimplemented stop message for now: ");
                    sb.append(valueOf);
                    Log.d("GcmTaskService", sb.toString());
                }
            } else if (i != 4) {
                String valueOf2 = String.valueOf(message);
                StringBuilder sb2 = new StringBuilder(valueOf2.length() + 31);
                sb2.append("Unrecognized message received: ");
                sb2.append(valueOf2);
                Log.e("GcmTaskService", sb2.toString());
            } else {
                GcmTaskService.this.onInitializeTasks();
            }
        }
    }

    public final void b(int i) {
        synchronized (this.h) {
            this.i = i;
            if (!this.m.g(this.l.getClassName())) {
                stopSelf(this.i);
            }
        }
    }

    public final void c(b bVar) {
        try {
            this.j.execute(bVar);
        } catch (RejectedExecutionException e) {
            Log.e("GcmTaskService", "Executor is shutdown. onDestroy was called but main looper had an unprocessed start task message. The task will be retried with backoff delay.", e);
            bVar.c(1);
        }
    }

    public final boolean i(String str) {
        boolean z;
        synchronized (this.h) {
            z = !this.m.c(str, this.l.getClassName());
            if (z) {
                String packageName = getPackageName();
                StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 44 + String.valueOf(str).length());
                sb.append(packageName);
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append(str);
                sb.append(": Task already running, won't start another");
                Log.w("GcmTaskService", sb.toString());
            }
        }
        return z;
    }

    @Override // android.app.Service
    @CallSuper
    public IBinder onBind(Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastLollipop() && SERVICE_ACTION_EXECUTE_TASK.equals(intent.getAction())) {
            return this.k.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    @CallSuper
    public void onCreate() {
        super.onCreate();
        this.m = GcmNetworkManager.getInstance(this);
        this.j = com.google.android.gms.internal.gcm.zzg.zzaa().zzd(10, new com.google.android.gms.gcm.b(this), 10);
        this.k = new Messenger(new a(Looper.getMainLooper()));
        this.l = new ComponentName(this, getClass());
        zzm.zzab();
        this.n = zzm.zzdk;
    }

    @Override // android.app.Service
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        List<Runnable> shutdownNow = this.j.shutdownNow();
        if (shutdownNow.isEmpty()) {
            return;
        }
        int size = shutdownNow.size();
        StringBuilder sb = new StringBuilder(79);
        sb.append("Shutting down, but not all tasks are finished executing. Remaining: ");
        sb.append(size);
        Log.e("GcmTaskService", sb.toString());
    }

    public void onInitializeTasks() {
    }

    public abstract int onRunTask(TaskParams taskParams);

    @Override // android.app.Service
    @CallSuper
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 2;
        }
        try {
            intent.setExtrasClassLoader(PendingCallback.class.getClassLoader());
            String action = intent.getAction();
            if (SERVICE_ACTION_EXECUTE_TASK.equals(action)) {
                String stringExtra = intent.getStringExtra(HeaderParameterNames.AUTHENTICATION_TAG);
                Parcelable parcelableExtra = intent.getParcelableExtra("callback");
                Bundle bundleExtra = intent.getBundleExtra(NotificationCompat.MessagingStyle.Message.KEY_EXTRAS_BUNDLE);
                ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("triggered_uris");
                long longExtra = intent.getLongExtra("max_exec_duration", 180L);
                if (!(parcelableExtra instanceof PendingCallback)) {
                    String packageName = getPackageName();
                    StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 47 + String.valueOf(stringExtra).length());
                    sb.append(packageName);
                    sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                    sb.append(stringExtra);
                    sb.append(": Could not process request, invalid callback.");
                    Log.e("GcmTaskService", sb.toString());
                    return 2;
                } else if (i(stringExtra)) {
                    return 2;
                } else {
                    c(new b(stringExtra, ((PendingCallback) parcelableExtra).h, bundleExtra, longExtra, parcelableArrayListExtra));
                }
            } else if (SERVICE_ACTION_INITIALIZE.equals(action)) {
                onInitializeTasks();
            } else {
                StringBuilder sb2 = new StringBuilder(String.valueOf(action).length() + 37);
                sb2.append("Unknown action received ");
                sb2.append(action);
                sb2.append(", terminating");
                Log.e("GcmTaskService", sb2.toString());
            }
            return 2;
        } finally {
            b(i2);
        }
    }

    /* loaded from: classes6.dex */
    public class b implements Runnable {
        public final String h;
        public final Bundle i;
        public final List<Uri> j;
        public final long k;
        @Nullable
        public final zzg l;
        @Nullable
        public final Messenger m;

        public b(String str, @NonNull IBinder iBinder, Bundle bundle, long j, List<Uri> list) {
            zzg zzhVar;
            this.h = str;
            if (iBinder == null) {
                zzhVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.gcm.INetworkTaskCallback");
                if (queryLocalInterface instanceof zzg) {
                    zzhVar = (zzg) queryLocalInterface;
                } else {
                    zzhVar = new zzh(iBinder);
                }
            }
            this.l = zzhVar;
            this.i = bundle;
            this.k = j;
            this.j = list;
            this.m = null;
        }

        public static /* synthetic */ void b(Throwable th, zzp zzpVar) {
            if (th == null) {
                zzpVar.close();
                return;
            }
            try {
                zzpVar.close();
            } catch (Throwable th2) {
                zzq.zzd(th, th2);
            }
        }

        public final void c(int i) {
            synchronized (GcmTaskService.this.h) {
                try {
                } catch (RemoteException unused) {
                    String valueOf = String.valueOf(this.h);
                    Log.e("GcmTaskService", valueOf.length() != 0 ? "Error reporting result of operation to scheduler for ".concat(valueOf) : new String("Error reporting result of operation to scheduler for "));
                    GcmTaskService.this.m.e(this.h, GcmTaskService.this.l.getClassName());
                    if (!d() && !GcmTaskService.this.m.g(GcmTaskService.this.l.getClassName())) {
                        GcmTaskService gcmTaskService = GcmTaskService.this;
                        gcmTaskService.stopSelf(gcmTaskService.i);
                    }
                }
                if (GcmTaskService.this.m.h(this.h, GcmTaskService.this.l.getClassName())) {
                    GcmTaskService.this.m.e(this.h, GcmTaskService.this.l.getClassName());
                    if (!d() && !GcmTaskService.this.m.g(GcmTaskService.this.l.getClassName())) {
                        GcmTaskService gcmTaskService2 = GcmTaskService.this;
                        gcmTaskService2.stopSelf(gcmTaskService2.i);
                    }
                    return;
                }
                if (d()) {
                    Messenger messenger = this.m;
                    Message obtain = Message.obtain();
                    obtain.what = 3;
                    obtain.arg1 = i;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("component", GcmTaskService.this.l);
                    bundle.putString(HeaderParameterNames.AUTHENTICATION_TAG, this.h);
                    obtain.setData(bundle);
                    messenger.send(obtain);
                } else {
                    this.l.zzf(i);
                }
                GcmTaskService.this.m.e(this.h, GcmTaskService.this.l.getClassName());
                if (!d() && !GcmTaskService.this.m.g(GcmTaskService.this.l.getClassName())) {
                    GcmTaskService gcmTaskService3 = GcmTaskService.this;
                    gcmTaskService3.stopSelf(gcmTaskService3.i);
                }
            }
        }

        public final boolean d() {
            return this.m != null;
        }

        @Override // java.lang.Runnable
        public final void run() {
            String valueOf = String.valueOf(this.h);
            zzp zzpVar = new zzp(valueOf.length() != 0 ? "nts:client:onRunTask:".concat(valueOf) : new String("nts:client:onRunTask:"));
            try {
                TaskParams taskParams = new TaskParams(this.h, this.i, this.k, this.j);
                GcmTaskService.this.n.zzd("onRunTask", com.google.android.gms.internal.gcm.zzp.zzdo);
                c(GcmTaskService.this.onRunTask(taskParams));
                b(null, zzpVar);
            } finally {
            }
        }

        public b(String str, @NonNull Messenger messenger, Bundle bundle, long j, List<Uri> list) {
            this.h = str;
            this.m = messenger;
            this.i = bundle;
            this.k = j;
            this.j = list;
            this.l = null;
        }
    }
}
