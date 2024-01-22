package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.Constants;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import javax.annotation.concurrent.GuardedBy;
@KeepForSdk
/* loaded from: classes10.dex */
public class FcmBroadcastProcessor {
    public static final Object c = new Object();
    @GuardedBy("lock")
    public static z0 d;

    /* renamed from: a  reason: collision with root package name */
    public final Context f11329a;
    public final Executor b;

    public FcmBroadcastProcessor(@NonNull Context context) {
        this.f11329a = context;
        this.b = e.h;
    }

    public FcmBroadcastProcessor(@NonNull Context context, @NonNull ExecutorService executorService) {
        this.f11329a = context;
        this.b = executorService;
    }

    public static Task<Integer> a(Context context, Intent intent) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Binding to service");
        }
        return b(context, com.google.firebase.iid.ServiceStarter.ACTION_MESSAGING_EVENT).c(intent).continueWith(h.h, i.f11344a);
    }

    public static z0 b(Context context, String str) {
        z0 z0Var;
        synchronized (c) {
            if (d == null) {
                d = new z0(context, com.google.firebase.iid.ServiceStarter.ACTION_MESSAGING_EVENT);
            }
            z0Var = d;
        }
        return z0Var;
    }

    public static final /* synthetic */ Integer c(Task task) throws Exception {
        return -1;
    }

    public static final /* synthetic */ Integer e(Task task) throws Exception {
        return 403;
    }

    public static final /* synthetic */ Task f(Context context, Intent intent, Task task) throws Exception {
        return (PlatformVersion.isAtLeastO() && ((Integer) task.getResult()).intValue() == 402) ? a(context, intent).continueWith(j.h, k.f11347a) : task;
    }

    @VisibleForTesting
    public static void reset() {
        synchronized (c) {
            d = null;
        }
    }

    @NonNull
    @KeepForSdk
    public Task<Integer> process(@NonNull Intent intent) {
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra(Constants.MessagePayloadKeys.RAW_DATA, Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        return startMessagingService(this.f11329a, intent);
    }

    @NonNull
    @SuppressLint({"InlinedApi"})
    public Task<Integer> startMessagingService(@NonNull final Context context, @NonNull final Intent intent) {
        boolean z = false;
        if (PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26) {
            z = true;
        }
        int flags = intent.getFlags() & 268435456;
        if (z && flags == 0) {
            return a(context, intent);
        }
        return Tasks.call(this.b, new Callable(context, intent) { // from class: com.google.firebase.messaging.f
            public final Context h;
            public final Intent i;

            {
                this.h = context;
                this.i = intent;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                Integer valueOf;
                valueOf = Integer.valueOf(ServiceStarter.b().startMessagingService(this.h, this.i));
                return valueOf;
            }
        }).continueWithTask(this.b, new Continuation(context, intent) { // from class: com.google.firebase.messaging.g

            /* renamed from: a  reason: collision with root package name */
            public final Context f11341a;
            public final Intent b;

            {
                this.f11341a = context;
                this.b = intent;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public Object then(Task task) {
                return FcmBroadcastProcessor.f(this.f11341a, this.b, task);
            }
        });
    }
}
