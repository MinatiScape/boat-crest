package com.clevertap.android.sdk.task;

import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import java.util.HashMap;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class CTExecutors {
    public final MainThreadExecutor DEFAULT_CALLBACK_EXECUTOR;
    public final c IO_EXECUTOR = new c();
    public final MainThreadExecutor MAIN_EXECUTOR;

    /* renamed from: a  reason: collision with root package name */
    public final HashMap<String, d> f2683a;
    public final CleverTapInstanceConfig config;

    public CTExecutors(CleverTapInstanceConfig cleverTapInstanceConfig) {
        MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();
        this.MAIN_EXECUTOR = mainThreadExecutor;
        this.DEFAULT_CALLBACK_EXECUTOR = mainThreadExecutor;
        this.f2683a = new HashMap<>();
        this.config = cleverTapInstanceConfig;
    }

    public <TResult> Task<TResult> ioTask() {
        return taskOnExecutorWithName(this.IO_EXECUTOR, this.DEFAULT_CALLBACK_EXECUTOR, "ioTask");
    }

    public <TResult> Task<TResult> mainTask() {
        return taskOnExecutorWithName(this.MAIN_EXECUTOR, this.DEFAULT_CALLBACK_EXECUTOR, "Main");
    }

    public <TResult> Task<TResult> postAsyncSafelyTask(String str) {
        if (str != null) {
            d dVar = this.f2683a.get(str);
            if (dVar == null) {
                dVar = new d();
                this.f2683a.put(str, dVar);
            }
            return taskOnExecutorWithName(dVar, this.DEFAULT_CALLBACK_EXECUTOR, "PostAsyncSafely");
        }
        throw new IllegalArgumentException("Tag can't be null");
    }

    public <TResult> Task<TResult> taskOnExecutor(Executor executor, String str) {
        return taskOnExecutorWithName(executor, this.DEFAULT_CALLBACK_EXECUTOR, str);
    }

    public <TResult> Task<TResult> taskOnExecutorWithName(Executor executor, Executor executor2, String str) {
        if (executor != null && executor2 != null) {
            return new Task<>(this.config, executor, executor2, str);
        }
        throw new IllegalArgumentException("Can't create task " + str + " with null executors");
    }

    public <TResult> Task<TResult> postAsyncSafelyTask() {
        return postAsyncSafelyTask(this.config.getAccountId());
    }
}
