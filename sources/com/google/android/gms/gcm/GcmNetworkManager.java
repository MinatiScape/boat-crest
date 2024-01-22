package com.google.android.gms.gcm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.internal.gcm.zzq;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public class GcmNetworkManager {
    public static final int RESULT_FAILURE = 2;
    public static final int RESULT_RESCHEDULE = 1;
    public static final int RESULT_SUCCESS = 0;
    @GuardedBy("GcmNetworkManager.class")
    public static GcmNetworkManager c;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8473a;
    @GuardedBy("this")
    public final Map<String, Map<String, Boolean>> b = new ArrayMap();

    public GcmNetworkManager(Context context) {
        this.f8473a = context;
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (100 < str.length()) {
                throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
            }
            return;
        }
        throw new IllegalArgumentException("Must provide a valid tag.");
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

    public static GcmNetworkManager getInstance(Context context) {
        GcmNetworkManager gcmNetworkManager;
        synchronized (GcmNetworkManager.class) {
            if (c == null) {
                c = new GcmNetworkManager(context.getApplicationContext());
            }
            gcmNetworkManager = c;
        }
        return gcmNetworkManager;
    }

    public final synchronized boolean c(String str, String str2) {
        Map<String, Boolean> map;
        map = this.b.get(str2);
        if (map == null) {
            map = new ArrayMap<>();
            this.b.put(str2, map);
        }
        return map.put(str, Boolean.FALSE) == null;
    }

    @WorkerThread
    public void cancelAllTasks(Class<? extends GcmTaskService> cls) {
        ComponentName componentName = new ComponentName(this.f8473a, cls);
        zzp zzpVar = new zzp("nts:client:cancelAll");
        try {
            f(componentName.getClassName());
            d().a(componentName);
            b(null, zzpVar);
        } finally {
        }
    }

    @WorkerThread
    public void cancelTask(String str, Class<? extends GcmTaskService> cls) {
        ComponentName componentName = new ComponentName(this.f8473a, cls);
        String valueOf = String.valueOf(str);
        zzp zzpVar = new zzp(valueOf.length() != 0 ? "nts:client:cancel:".concat(valueOf) : new String("nts:client:cancel:"));
        try {
            a(str);
            f(componentName.getClassName());
            d().b(componentName, str);
            b(null, zzpVar);
        } finally {
        }
    }

    @NonNull
    public final h d() {
        if (GoogleCloudMessaging.zzf(this.f8473a) < 5000000) {
            Log.e("GcmNetworkManager", "Google Play services is not available, dropping all GcmNetworkManager requests");
            return new i();
        }
        return new g(this.f8473a);
    }

    public final synchronized void e(String str, String str2) {
        Map<String, Boolean> map = this.b.get(str2);
        if (map != null) {
            if ((map.remove(str) != null) && map.isEmpty()) {
                this.b.remove(str2);
            }
        }
    }

    public final boolean f(String str) {
        Intent intent;
        List<ResolveInfo> queryIntentServices;
        Preconditions.checkNotNull(str, "GcmTaskService must not be null.");
        PackageManager packageManager = this.f8473a.getPackageManager();
        if (packageManager == null) {
            queryIntentServices = Collections.emptyList();
        } else {
            if (str != null) {
                intent = new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK).setClassName(this.f8473a, str);
            } else {
                intent = new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK).setPackage(this.f8473a.getPackageName());
            }
            queryIntentServices = packageManager.queryIntentServices(intent, 0);
        }
        if (CollectionUtils.isEmpty(queryIntentServices)) {
            Log.e("GcmNetworkManager", String.valueOf(str).concat(" is not available. This may cause the task to be lost."));
            return true;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 118);
        sb.append("The GcmTaskService class you provided ");
        sb.append(str);
        sb.append(" does not seem to support receiving com.google.android.gms.gcm.ACTION_TASK_READY");
        throw new IllegalArgumentException(sb.toString());
    }

    public final synchronized boolean g(String str) {
        return this.b.containsKey(str);
    }

    public final synchronized boolean h(String str, String str2) {
        Map<String, Boolean> map = this.b.get(str2);
        if (map != null) {
            Boolean bool = map.get(str);
            if (bool == null) {
                return false;
            }
            return bool.booleanValue();
        }
        return false;
    }

    @WorkerThread
    public synchronized void schedule(Task task) {
        Map<String, Boolean> map;
        String valueOf = String.valueOf(task.getTag());
        zzp zzpVar = new zzp(valueOf.length() != 0 ? "nts:client:schedule:".concat(valueOf) : new String("nts:client:schedule:"));
        f(task.getServiceName());
        if (d().c(task) && (map = this.b.get(task.getServiceName())) != null && map.containsKey(task.getTag())) {
            map.put(task.getTag(), Boolean.TRUE);
        }
        b(null, zzpVar);
    }
}
