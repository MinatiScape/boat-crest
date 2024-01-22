package com.google.android.gms.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.cloudmessaging.zza;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public class Rpc {
    public static int h;
    public static PendingIntent i;
    public static final Executor j = v.h;
    public final Context b;
    public final zzr c;
    public final ScheduledExecutorService d;
    public Messenger f;
    public zza g;
    @GuardedBy("responseCallbacks")

    /* renamed from: a  reason: collision with root package name */
    public final SimpleArrayMap<String, TaskCompletionSource<Bundle>> f8224a = new SimpleArrayMap<>();
    public Messenger e = new Messenger(new u(this, Looper.getMainLooper()));

    public Rpc(@NonNull Context context) {
        this.b = context;
        this.c = new zzr(context);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.setKeepAliveTime(60L, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        this.d = scheduledThreadPoolExecutor;
    }

    public static final /* synthetic */ Bundle a(Task task) throws Exception {
        if (task.isSuccessful()) {
            return (Bundle) task.getResult();
        }
        if (Log.isLoggable("Rpc", 3)) {
            String valueOf = String.valueOf(task.getException());
            StringBuilder sb = new StringBuilder(valueOf.length() + 22);
            sb.append("Error making request: ");
            sb.append(valueOf);
            Log.d("Rpc", sb.toString());
        }
        throw new IOException("SERVICE_NOT_AVAILABLE", task.getException());
    }

    public static final /* synthetic */ Task b(Bundle bundle) throws Exception {
        if (k(bundle)) {
            return Tasks.forResult(null);
        }
        return Tasks.forResult(bundle);
    }

    public static synchronized String d() {
        String num;
        synchronized (Rpc.class) {
            int i2 = h;
            h = i2 + 1;
            num = Integer.toString(i2);
        }
        return num;
    }

    public static synchronized void e(Context context, Intent intent) {
        synchronized (Rpc.class) {
            if (i == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                i = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", i);
        }
    }

    public static final /* synthetic */ void h(TaskCompletionSource taskCompletionSource) {
        if (taskCompletionSource.trySetException(new IOException(InstanceID.ERROR_TIMEOUT))) {
            Log.w("Rpc", "No response");
        }
    }

    public static boolean k(Bundle bundle) {
        return bundle != null && bundle.containsKey("google.messenger");
    }

    public final /* synthetic */ Task c(Bundle bundle, Task task) throws Exception {
        return (task.isSuccessful() && k((Bundle) task.getResult())) ? l(bundle).onSuccessTask(j, s.f8229a) : task;
    }

    public final void f(Message message) {
        if (message != null) {
            Object obj = message.obj;
            if (obj instanceof Intent) {
                Intent intent = (Intent) obj;
                intent.setExtrasClassLoader(new zza.C0373zza());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof zza) {
                        this.g = (zza) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.f = (Messenger) parcelableExtra;
                    }
                }
                Intent intent2 = (Intent) message.obj;
                String action = intent2.getAction();
                if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                    if (Log.isLoggable("Rpc", 3)) {
                        String valueOf = String.valueOf(action);
                        Log.d("Rpc", valueOf.length() != 0 ? "Unexpected response action: ".concat(valueOf) : new String("Unexpected response action: "));
                        return;
                    }
                    return;
                }
                String stringExtra = intent2.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent2.getStringExtra("unregistered");
                }
                if (stringExtra == null) {
                    String stringExtra2 = intent2.getStringExtra("error");
                    if (stringExtra2 == null) {
                        String valueOf2 = String.valueOf(intent2.getExtras());
                        StringBuilder sb = new StringBuilder(valueOf2.length() + 49);
                        sb.append("Unexpected response, no error or registration id ");
                        sb.append(valueOf2);
                        Log.w("Rpc", sb.toString());
                        return;
                    }
                    if (Log.isLoggable("Rpc", 3)) {
                        Log.d("Rpc", stringExtra2.length() != 0 ? "Received InstanceID error ".concat(stringExtra2) : new String("Received InstanceID error "));
                    }
                    if (stringExtra2.startsWith("|")) {
                        String[] split = stringExtra2.split("\\|");
                        if (split.length > 2 && "ID".equals(split[1])) {
                            String str = split[2];
                            String str2 = split[3];
                            if (str2.startsWith(":")) {
                                str2 = str2.substring(1);
                            }
                            i(str, intent2.putExtra("error", str2).getExtras());
                            return;
                        }
                        Log.w("Rpc", stringExtra2.length() != 0 ? "Unexpected structured response ".concat(stringExtra2) : new String("Unexpected structured response "));
                        return;
                    }
                    synchronized (this.f8224a) {
                        for (int i2 = 0; i2 < this.f8224a.size(); i2++) {
                            i(this.f8224a.keyAt(i2), intent2.getExtras());
                        }
                    }
                    return;
                }
                Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                if (!matcher.matches()) {
                    if (Log.isLoggable("Rpc", 3)) {
                        Log.d("Rpc", stringExtra.length() != 0 ? "Unexpected response string: ".concat(stringExtra) : new String("Unexpected response string: "));
                        return;
                    }
                    return;
                }
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                if (group != null) {
                    Bundle extras = intent2.getExtras();
                    extras.putString("registration_id", group2);
                    i(group, extras);
                    return;
                }
                return;
            }
        }
        Log.w("Rpc", "Dropping invalid message");
    }

    public final void i(String str, @Nullable Bundle bundle) {
        synchronized (this.f8224a) {
            TaskCompletionSource<Bundle> remove = this.f8224a.remove(str);
            if (remove == null) {
                String valueOf = String.valueOf(str);
                Log.w("Rpc", valueOf.length() != 0 ? "Missing callback for ".concat(valueOf) : new String("Missing callback for "));
                return;
            }
            remove.setResult(bundle);
        }
    }

    public final /* synthetic */ void j(String str, ScheduledFuture scheduledFuture, Task task) {
        synchronized (this.f8224a) {
            this.f8224a.remove(str);
        }
        scheduledFuture.cancel(false);
    }

    @AnyThread
    public final Task<Bundle> l(Bundle bundle) {
        final String d = d();
        final TaskCompletionSource<Bundle> taskCompletionSource = new TaskCompletionSource<>();
        synchronized (this.f8224a) {
            this.f8224a.put(d, taskCompletionSource);
        }
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.c.zza() == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        e(this.b, intent);
        StringBuilder sb = new StringBuilder(String.valueOf(d).length() + 5);
        sb.append("|ID|");
        sb.append(d);
        sb.append("|");
        intent.putExtra("kid", sb.toString());
        if (Log.isLoggable("Rpc", 3)) {
            String valueOf = String.valueOf(intent.getExtras());
            StringBuilder sb2 = new StringBuilder(valueOf.length() + 8);
            sb2.append("Sending ");
            sb2.append(valueOf);
            Log.d("Rpc", sb2.toString());
        }
        intent.putExtra("google.messenger", this.e);
        if (this.f != null || this.g != null) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                Messenger messenger = this.f;
                if (messenger != null) {
                    messenger.send(obtain);
                } else {
                    this.g.zza(obtain);
                }
            } catch (RemoteException unused) {
                if (Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", "Messenger failed, fallback to startService");
                }
            }
            final ScheduledFuture<?> schedule = this.d.schedule(new Runnable(taskCompletionSource) { // from class: com.google.android.gms.cloudmessaging.p
                public final TaskCompletionSource h;

                {
                    this.h = taskCompletionSource;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    Rpc.h(this.h);
                }
            }, 30L, TimeUnit.SECONDS);
            taskCompletionSource.getTask().addOnCompleteListener(j, new OnCompleteListener(this, d, schedule) { // from class: com.google.android.gms.cloudmessaging.t

                /* renamed from: a  reason: collision with root package name */
                public final Rpc f8230a;
                public final String b;
                public final ScheduledFuture c;

                {
                    this.f8230a = this;
                    this.b = d;
                    this.c = schedule;
                }

                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.f8230a.j(this.b, this.c, task);
                }
            });
            return taskCompletionSource.getTask();
        }
        if (this.c.zza() == 2) {
            this.b.sendBroadcast(intent);
        } else {
            this.b.startService(intent);
        }
        final ScheduledFuture schedule2 = this.d.schedule(new Runnable(taskCompletionSource) { // from class: com.google.android.gms.cloudmessaging.p
            public final TaskCompletionSource h;

            {
                this.h = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public final void run() {
                Rpc.h(this.h);
            }
        }, 30L, TimeUnit.SECONDS);
        taskCompletionSource.getTask().addOnCompleteListener(j, new OnCompleteListener(this, d, schedule2) { // from class: com.google.android.gms.cloudmessaging.t

            /* renamed from: a  reason: collision with root package name */
            public final Rpc f8230a;
            public final String b;
            public final ScheduledFuture c;

            {
                this.f8230a = this;
                this.b = d;
                this.c = schedule2;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f8230a.j(this.b, this.c, task);
            }
        });
        return taskCompletionSource.getTask();
    }

    @NonNull
    public Task<Bundle> send(@NonNull final Bundle bundle) {
        if (this.c.zzb() >= 12000000) {
            return zze.zza(this.b).zzb(1, bundle).continueWith(j, o.f8227a);
        }
        if (!(this.c.zza() != 0)) {
            return Tasks.forException(new IOException(InstanceID.ERROR_MISSING_INSTANCEID_SERVICE));
        }
        return l(bundle).continueWithTask(j, new Continuation(this, bundle) { // from class: com.google.android.gms.cloudmessaging.q

            /* renamed from: a  reason: collision with root package name */
            public final Rpc f8228a;
            public final Bundle b;

            {
                this.f8228a = this;
                this.b = bundle;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f8228a.c(this.b, task);
            }
        });
    }
}
