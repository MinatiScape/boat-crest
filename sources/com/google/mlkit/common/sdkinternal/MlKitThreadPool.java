package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzbh;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@KeepForSdk
/* loaded from: classes10.dex */
public class MlKitThreadPool extends zzbh {
    public static final ThreadLocal i = new ThreadLocal();
    public final ThreadPoolExecutor h;

    public MlKitThreadPool() {
        final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.google.mlkit.common.sdkinternal.zzj
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(final Runnable runnable) {
                return defaultThreadFactory.newThread(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzi
                    @Override // java.lang.Runnable
                    public final void run() {
                        MlKitThreadPool.b(runnable);
                    }
                });
            }
        });
        this.h = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    public static /* synthetic */ void b(Runnable runnable) {
        i.set(new ArrayDeque());
        runnable.run();
    }

    public static void c(Deque deque, Runnable runnable) {
        Preconditions.checkNotNull(deque);
        deque.add(runnable);
        if (deque.size() <= 1) {
            do {
                runnable.run();
                deque.removeFirst();
                runnable = (Runnable) deque.peekFirst();
            } while (runnable != null);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbh, java.util.concurrent.Executor
    public final void execute(@NonNull final Runnable runnable) {
        Deque deque = (Deque) i.get();
        if (deque != null && deque.size() <= 1) {
            c(deque, runnable);
        } else {
            this.h.execute(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzk
                @Override // java.lang.Runnable
                public final void run() {
                    MlKitThreadPool.c((Deque) MlKitThreadPool.i.get(), runnable);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbh, com.google.android.gms.internal.mlkit_common.zzaj
    @NonNull
    public final /* synthetic */ Object zza() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbh
    @NonNull
    public final ExecutorService zzb() {
        return this.h;
    }
}
