package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArraySet;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes6.dex */
public class GoogleApiManager implements Handler.Callback {
    @Nullable
    @GuardedBy("lock")
    public static GoogleApiManager A;
    @Nullable
    public TelemetryData l;
    @Nullable
    public TelemetryLoggingClient m;
    public final Context n;
    public final GoogleApiAvailability o;
    public final com.google.android.gms.common.internal.zal p;
    @NotOnlyInitialized
    public final Handler w;
    public volatile boolean x;
    @NonNull
    public static final Status zaa = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status y = new Status(4, "The user must be signed in to make this API call.");
    public static final Object z = new Object();
    public long h = 5000;
    public long i = 120000;
    public long j = 10000;
    public boolean k = false;
    public final AtomicInteger q = new AtomicInteger(1);
    public final AtomicInteger r = new AtomicInteger(0);
    public final Map s = new ConcurrentHashMap(5, 0.75f, 1);
    @Nullable
    @GuardedBy("lock")
    public zaae t = null;
    @GuardedBy("lock")
    public final Set u = new ArraySet();
    public final Set v = new ArraySet();

    @KeepForSdk
    public GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.x = true;
        this.n = context;
        com.google.android.gms.internal.base.zau zauVar = new com.google.android.gms.internal.base.zau(looper, this);
        this.w = zauVar;
        this.o = googleApiAvailability;
        this.p = new com.google.android.gms.common.internal.zal(googleApiAvailability);
        if (DeviceProperties.isAuto(context)) {
            this.x = false;
        }
        zauVar.sendMessage(zauVar.obtainMessage(6));
    }

    public static Status e(ApiKey apiKey, ConnectionResult connectionResult) {
        String zaa2 = apiKey.zaa();
        String valueOf = String.valueOf(connectionResult);
        return new Status(connectionResult, "API: " + zaa2 + " is not available on this device. Connection failed with: " + valueOf);
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (z) {
            GoogleApiManager googleApiManager = A;
            if (googleApiManager != null) {
                googleApiManager.r.incrementAndGet();
                Handler handler = googleApiManager.w;
                handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
            }
        }
    }

    @NonNull
    public static GoogleApiManager zal() {
        GoogleApiManager googleApiManager;
        synchronized (z) {
            Preconditions.checkNotNull(A, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = A;
        }
        return googleApiManager;
    }

    @NonNull
    public static GoogleApiManager zam(@NonNull Context context) {
        GoogleApiManager googleApiManager;
        synchronized (z) {
            if (A == null) {
                A = new GoogleApiManager(context.getApplicationContext(), GmsClientSupervisor.getOrStartHandlerThread().getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = A;
        }
        return googleApiManager;
    }

    public final void a(@NonNull zaae zaaeVar) {
        synchronized (z) {
            if (this.t == zaaeVar) {
                this.t = null;
                this.u.clear();
            }
        }
    }

    @WorkerThread
    public final boolean c() {
        if (this.k) {
            return false;
        }
        RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
        if (config == null || config.getMethodInvocationTelemetryEnabled()) {
            int zaa2 = this.p.zaa(this.n, 203400000);
            return zaa2 == -1 || zaa2 == 0;
        }
        return false;
    }

    public final boolean d(ConnectionResult connectionResult, int i) {
        return this.o.zah(this.n, connectionResult, i);
    }

    @WorkerThread
    public final zabq f(GoogleApi googleApi) {
        ApiKey apiKey = googleApi.getApiKey();
        zabq zabqVar = (zabq) this.s.get(apiKey);
        if (zabqVar == null) {
            zabqVar = new zabq(this, googleApi);
            this.s.put(apiKey, zabqVar);
        }
        if (zabqVar.zaz()) {
            this.v.add(apiKey);
        }
        zabqVar.zao();
        return zabqVar;
    }

    @WorkerThread
    public final TelemetryLoggingClient g() {
        if (this.m == null) {
            this.m = TelemetryLogging.getClient(this.n);
        }
        return this.m;
    }

    @WorkerThread
    public final void h() {
        TelemetryData telemetryData = this.l;
        if (telemetryData != null) {
            if (telemetryData.zaa() > 0 || c()) {
                g().log(telemetryData);
            }
            this.l = null;
        }
    }

    @Override // android.os.Handler.Callback
    @WorkerThread
    public final boolean handleMessage(@NonNull Message message) {
        zabq zabqVar;
        ApiKey apiKey;
        ApiKey apiKey2;
        ApiKey apiKey3;
        ApiKey apiKey4;
        int i = message.what;
        long j = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
        switch (i) {
            case 1:
                if (true == ((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.j = j;
                this.w.removeMessages(12);
                for (ApiKey apiKey5 : this.s.keySet()) {
                    Handler handler = this.w;
                    handler.sendMessageDelayed(handler.obtainMessage(12, apiKey5), this.j);
                }
                break;
            case 2:
                zal zalVar = (zal) message.obj;
                Iterator it = zalVar.zab().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else {
                        ApiKey apiKey6 = (ApiKey) it.next();
                        zabq zabqVar2 = (zabq) this.s.get(apiKey6);
                        if (zabqVar2 == null) {
                            zalVar.zac(apiKey6, new ConnectionResult(13), null);
                            break;
                        } else if (zabqVar2.x()) {
                            zalVar.zac(apiKey6, ConnectionResult.RESULT_SUCCESS, zabqVar2.zaf().getEndpointPackageName());
                        } else {
                            ConnectionResult zad = zabqVar2.zad();
                            if (zad != null) {
                                zalVar.zac(apiKey6, zad, null);
                            } else {
                                zabqVar2.zat(zalVar);
                                zabqVar2.zao();
                            }
                        }
                    }
                }
            case 3:
                for (zabq zabqVar3 : this.s.values()) {
                    zabqVar3.zan();
                    zabqVar3.zao();
                }
                break;
            case 4:
            case 8:
            case 13:
                zach zachVar = (zach) message.obj;
                zabq zabqVar4 = (zabq) this.s.get(zachVar.zac.getApiKey());
                if (zabqVar4 == null) {
                    zabqVar4 = f(zachVar.zac);
                }
                if (zabqVar4.zaz() && this.r.get() != zachVar.zab) {
                    zachVar.zaa.zad(zaa);
                    zabqVar4.zav();
                    break;
                } else {
                    zabqVar4.zap(zachVar.zaa);
                    break;
                }
            case 5:
                int i2 = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator it2 = this.s.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zabq zabqVar5 = (zabq) it2.next();
                        zabqVar = zabqVar5.zab() == i2 ? zabqVar5 : null;
                    }
                }
                if (zabqVar == null) {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i2 + " while trying to fail enqueued calls.", new Exception());
                    break;
                } else if (connectionResult.getErrorCode() == 13) {
                    String errorString = this.o.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    zabq.q(zabqVar, new Status(17, "Error resolution was canceled by the user, original error message: " + errorString + ": " + errorMessage));
                    break;
                } else {
                    zabq.q(zabqVar, e(zabq.p(zabqVar), connectionResult));
                    break;
                }
            case 6:
                if (this.n.getApplicationContext() instanceof Application) {
                    BackgroundDetector.initialize((Application) this.n.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new z(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.j = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
                        break;
                    }
                }
                break;
            case 7:
                f((GoogleApi) message.obj);
                break;
            case 9:
                if (this.s.containsKey(message.obj)) {
                    ((zabq) this.s.get(message.obj)).zau();
                    break;
                }
                break;
            case 10:
                for (ApiKey apiKey7 : this.v) {
                    zabq zabqVar6 = (zabq) this.s.remove(apiKey7);
                    if (zabqVar6 != null) {
                        zabqVar6.zav();
                    }
                }
                this.v.clear();
                break;
            case 11:
                if (this.s.containsKey(message.obj)) {
                    ((zabq) this.s.get(message.obj)).zaw();
                    break;
                }
                break;
            case 12:
                if (this.s.containsKey(message.obj)) {
                    ((zabq) this.s.get(message.obj)).zaA();
                    break;
                }
                break;
            case 14:
                e eVar = (e) message.obj;
                ApiKey a2 = eVar.a();
                if (!this.s.containsKey(a2)) {
                    eVar.b().setResult(Boolean.FALSE);
                    break;
                } else {
                    eVar.b().setResult(Boolean.valueOf(zabq.w((zabq) this.s.get(a2), false)));
                    break;
                }
            case 15:
                e0 e0Var = (e0) message.obj;
                Map map = this.s;
                apiKey = e0Var.f8275a;
                if (map.containsKey(apiKey)) {
                    Map map2 = this.s;
                    apiKey2 = e0Var.f8275a;
                    zabq.t((zabq) map2.get(apiKey2), e0Var);
                    break;
                }
                break;
            case 16:
                e0 e0Var2 = (e0) message.obj;
                Map map3 = this.s;
                apiKey3 = e0Var2.f8275a;
                if (map3.containsKey(apiKey3)) {
                    Map map4 = this.s;
                    apiKey4 = e0Var2.f8275a;
                    zabq.u((zabq) map4.get(apiKey4), e0Var2);
                    break;
                }
                break;
            case 17:
                h();
                break;
            case 18:
                i0 i0Var = (i0) message.obj;
                if (i0Var.c == 0) {
                    g().log(new TelemetryData(i0Var.b, Arrays.asList(i0Var.f8279a)));
                    break;
                } else {
                    TelemetryData telemetryData = this.l;
                    if (telemetryData != null) {
                        List zab = telemetryData.zab();
                        if (telemetryData.zaa() == i0Var.b && (zab == null || zab.size() < i0Var.d)) {
                            this.l.zac(i0Var.f8279a);
                        } else {
                            this.w.removeMessages(17);
                            h();
                        }
                    }
                    if (this.l == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(i0Var.f8279a);
                        this.l = new TelemetryData(i0Var.b, arrayList);
                        Handler handler2 = this.w;
                        handler2.sendMessageDelayed(handler2.obtainMessage(17), i0Var.c);
                        break;
                    }
                }
                break;
            case 19:
                this.k = false;
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + i);
                return false;
        }
        return true;
    }

    public final void i(TaskCompletionSource taskCompletionSource, int i, GoogleApi googleApi) {
        h0 a2;
        if (i == 0 || (a2 = h0.a(this, i, googleApi.getApiKey())) == null) {
            return;
        }
        Task task = taskCompletionSource.getTask();
        final Handler handler = this.w;
        handler.getClass();
        task.addOnCompleteListener(new Executor() { // from class: com.google.android.gms.common.api.internal.zabk
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        }, a2);
    }

    @Nullable
    public final zabq s(ApiKey apiKey) {
        return (zabq) this.s.get(apiKey);
    }

    public final void y(MethodInvocation methodInvocation, int i, long j, int i2) {
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(18, new i0(methodInvocation, i, j, i2)));
    }

    public final void zaA() {
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void zaB(@NonNull GoogleApi googleApi) {
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }

    public final void zaC(@NonNull zaae zaaeVar) {
        synchronized (z) {
            if (this.t != zaaeVar) {
                this.t = zaaeVar;
                this.u.clear();
            }
            this.u.addAll(zaaeVar.f());
        }
    }

    public final int zaa() {
        return this.q.getAndIncrement();
    }

    @NonNull
    public final Task zao(@NonNull Iterable iterable) {
        zal zalVar = new zal(iterable);
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(2, zalVar));
        return zalVar.zaa();
    }

    @NonNull
    public final Task zap(@NonNull GoogleApi googleApi) {
        e eVar = new e(googleApi.getApiKey());
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(14, eVar));
        return eVar.b().getTask();
    }

    @NonNull
    public final Task zaq(@NonNull GoogleApi googleApi, @NonNull RegisterListenerMethod registerListenerMethod, @NonNull UnregisterListenerMethod unregisterListenerMethod, @NonNull Runnable runnable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        i(taskCompletionSource, registerListenerMethod.zaa(), googleApi);
        zaf zafVar = new zaf(new zaci(registerListenerMethod, unregisterListenerMethod, runnable), taskCompletionSource);
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(8, new zach(zafVar, this.r.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    @NonNull
    public final Task zar(@NonNull GoogleApi googleApi, @NonNull ListenerHolder.ListenerKey listenerKey, int i) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        i(taskCompletionSource, i, googleApi);
        zah zahVar = new zah(listenerKey, taskCompletionSource);
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(13, new zach(zahVar, this.r.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final void zaw(@NonNull GoogleApi googleApi, int i, @NonNull BaseImplementation.ApiMethodImpl apiMethodImpl) {
        zae zaeVar = new zae(i, apiMethodImpl);
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(4, new zach(zaeVar, this.r.get(), googleApi)));
    }

    public final void zax(@NonNull GoogleApi googleApi, int i, @NonNull TaskApiCall taskApiCall, @NonNull TaskCompletionSource taskCompletionSource, @NonNull StatusExceptionMapper statusExceptionMapper) {
        i(taskCompletionSource, taskApiCall.zaa(), googleApi);
        zag zagVar = new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper);
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(4, new zach(zagVar, this.r.get(), googleApi)));
    }

    public final void zaz(@NonNull ConnectionResult connectionResult, int i) {
        if (d(connectionResult, i)) {
            return;
        }
        Handler handler = this.w;
        handler.sendMessage(handler.obtainMessage(5, i, 0, connectionResult));
    }
}
