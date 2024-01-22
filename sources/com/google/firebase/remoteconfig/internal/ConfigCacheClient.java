package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
@AnyThread
/* loaded from: classes10.dex */
public class ConfigCacheClient {
    @GuardedBy("ConfigCacheClient.class")
    public static final Map<String, ConfigCacheClient> d = new HashMap();
    public static final Executor e = com.goodix.ble.libcomx.task.a.h;

    /* renamed from: a  reason: collision with root package name */
    public final ExecutorService f11489a;
    public final ConfigStorageClient b;
    @Nullable
    @GuardedBy("this")
    public Task<ConfigContainer> c = null;

    /* loaded from: classes10.dex */
    public static class b<TResult> implements OnSuccessListener<TResult>, OnFailureListener, OnCanceledListener {

        /* renamed from: a  reason: collision with root package name */
        public final CountDownLatch f11490a;

        public b() {
            this.f11490a = new CountDownLatch(1);
        }

        public boolean a(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.f11490a.await(j, timeUnit);
        }

        @Override // com.google.android.gms.tasks.OnCanceledListener
        public void onCanceled() {
            this.f11490a.countDown();
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public void onFailure(@NonNull Exception exc) {
            this.f11490a.countDown();
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public void onSuccess(TResult tresult) {
            this.f11490a.countDown();
        }
    }

    public ConfigCacheClient(ExecutorService executorService, ConfigStorageClient configStorageClient) {
        this.f11489a = executorService;
        this.b = configStorageClient;
    }

    public static <TResult> TResult c(Task<TResult> task, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        b bVar = new b();
        Executor executor = e;
        task.addOnSuccessListener(executor, bVar);
        task.addOnFailureListener(executor, bVar);
        task.addOnCanceledListener(executor, bVar);
        if (bVar.a(j, timeUnit)) {
            if (task.isSuccessful()) {
                return task.getResult();
            }
            throw new ExecutionException(task.getException());
        }
        throw new TimeoutException("Task await timed out.");
    }

    @VisibleForTesting
    public static synchronized void clearInstancesForTest() {
        synchronized (ConfigCacheClient.class) {
            d.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void e(ConfigContainer configContainer) throws Exception {
        return this.b.write(configContainer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task f(boolean z, ConfigContainer configContainer, Void r3) throws Exception {
        if (z) {
            g(configContainer);
        }
        return Tasks.forResult(configContainer);
    }

    public static synchronized ConfigCacheClient getInstance(ExecutorService executorService, ConfigStorageClient configStorageClient) {
        ConfigCacheClient configCacheClient;
        synchronized (ConfigCacheClient.class) {
            String a2 = configStorageClient.a();
            Map<String, ConfigCacheClient> map = d;
            if (!map.containsKey(a2)) {
                map.put(a2, new ConfigCacheClient(executorService, configStorageClient));
            }
            configCacheClient = map.get(a2);
        }
        return configCacheClient;
    }

    public void clear() {
        synchronized (this) {
            this.c = Tasks.forResult(null);
        }
        this.b.clear();
    }

    @Nullable
    @VisibleForTesting
    public ConfigContainer d(long j) {
        synchronized (this) {
            Task<ConfigContainer> task = this.c;
            if (task != null && task.isSuccessful()) {
                return this.c.getResult();
            }
            try {
                return (ConfigContainer) c(get(), j, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                Log.d(FirebaseRemoteConfig.TAG, "Reading from storage file failed.", e2);
                return null;
            }
        }
    }

    public final synchronized void g(ConfigContainer configContainer) {
        this.c = Tasks.forResult(configContainer);
    }

    public synchronized Task<ConfigContainer> get() {
        Task<ConfigContainer> task = this.c;
        if (task == null || (task.isComplete() && !this.c.isSuccessful())) {
            ExecutorService executorService = this.f11489a;
            final ConfigStorageClient configStorageClient = this.b;
            Objects.requireNonNull(configStorageClient);
            this.c = Tasks.call(executorService, new Callable() { // from class: com.google.firebase.remoteconfig.internal.c
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return ConfigStorageClient.this.read();
                }
            });
        }
        return this.c;
    }

    @Nullable
    public ConfigContainer getBlocking() {
        return d(5L);
    }

    public Task<ConfigContainer> put(ConfigContainer configContainer) {
        return put(configContainer, true);
    }

    public Task<ConfigContainer> put(final ConfigContainer configContainer, final boolean z) {
        return Tasks.call(this.f11489a, new Callable() { // from class: com.google.firebase.remoteconfig.internal.b
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void e2;
                e2 = ConfigCacheClient.this.e(configContainer);
                return e2;
            }
        }).onSuccessTask(this.f11489a, new SuccessContinuation() { // from class: com.google.firebase.remoteconfig.internal.a
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                Task f;
                f = ConfigCacheClient.this.f(z, configContainer, (Void) obj);
                return f;
            }
        });
    }
}
