package com.goodix.ble.libcomx.task;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes5.dex */
public class TaskExecutor {

    /* renamed from: a  reason: collision with root package name */
    public static Executor f8043a;
    public static Executor b;

    public static Executor getDefaultExecutor() {
        if (f8043a == null) {
            synchronized (TaskExecutor.class) {
                if (f8043a == null) {
                    f8043a = Executors.newScheduledThreadPool(1);
                }
            }
        }
        return f8043a;
    }

    public static Executor getImmediateExecutor() {
        if (b == null) {
            synchronized (TaskExecutor.class) {
                if (b == null) {
                    b = a.h;
                }
            }
        }
        return b;
    }

    public static void setDefaultExecutor(Executor executor) {
        ExecutorService executorService;
        if (executor == null) {
            return;
        }
        synchronized (TaskExecutor.class) {
            Executor executor2 = f8043a;
            if (executor != executor2) {
                executorService = executor2 instanceof ExecutorService ? (ExecutorService) executor2 : null;
                f8043a = executor;
            }
        }
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        try {
            executorService.shutdown();
        } catch (Exception unused) {
        }
    }
}
