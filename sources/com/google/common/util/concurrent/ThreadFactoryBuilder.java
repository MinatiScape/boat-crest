package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import java.lang.Thread;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
@CanIgnoreReturnValue
@GwtIncompatible
/* loaded from: classes10.dex */
public final class ThreadFactoryBuilder {

    /* renamed from: a  reason: collision with root package name */
    public String f10805a = null;
    public Boolean b = null;
    public Integer c = null;
    public Thread.UncaughtExceptionHandler d = null;
    public ThreadFactory e = null;

    /* loaded from: classes10.dex */
    public class a implements ThreadFactory {
        public final /* synthetic */ ThreadFactory h;
        public final /* synthetic */ String i;
        public final /* synthetic */ AtomicLong j;
        public final /* synthetic */ Boolean k;
        public final /* synthetic */ Integer l;
        public final /* synthetic */ Thread.UncaughtExceptionHandler m;

        public a(ThreadFactory threadFactory, String str, AtomicLong atomicLong, Boolean bool, Integer num, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.h = threadFactory;
            this.i = str;
            this.j = atomicLong;
            this.k = bool;
            this.l = num;
            this.m = uncaughtExceptionHandler;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread newThread = this.h.newThread(runnable);
            String str = this.i;
            if (str != null) {
                newThread.setName(ThreadFactoryBuilder.c(str, Long.valueOf(this.j.getAndIncrement())));
            }
            Boolean bool = this.k;
            if (bool != null) {
                newThread.setDaemon(bool.booleanValue());
            }
            Integer num = this.l;
            if (num != null) {
                newThread.setPriority(num.intValue());
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.m;
            if (uncaughtExceptionHandler != null) {
                newThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            }
            return newThread;
        }
    }

    public static ThreadFactory b(ThreadFactoryBuilder threadFactoryBuilder) {
        String str = threadFactoryBuilder.f10805a;
        Boolean bool = threadFactoryBuilder.b;
        Integer num = threadFactoryBuilder.c;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = threadFactoryBuilder.d;
        ThreadFactory threadFactory = threadFactoryBuilder.e;
        if (threadFactory == null) {
            threadFactory = Executors.defaultThreadFactory();
        }
        return new a(threadFactory, str, str != null ? new AtomicLong(0L) : null, bool, num, uncaughtExceptionHandler);
    }

    public static String c(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    @CheckReturnValue
    public ThreadFactory build() {
        return b(this);
    }

    public ThreadFactoryBuilder setDaemon(boolean z) {
        this.b = Boolean.valueOf(z);
        return this;
    }

    public ThreadFactoryBuilder setNameFormat(String str) {
        c(str, 0);
        this.f10805a = str;
        return this;
    }

    public ThreadFactoryBuilder setPriority(int i) {
        Preconditions.checkArgument(i >= 1, "Thread priority (%s) must be >= %s", i, 1);
        Preconditions.checkArgument(i <= 10, "Thread priority (%s) must be <= %s", i, 10);
        this.c = Integer.valueOf(i);
        return this;
    }

    public ThreadFactoryBuilder setThreadFactory(ThreadFactory threadFactory) {
        this.e = (ThreadFactory) Preconditions.checkNotNull(threadFactory);
        return this;
    }

    public ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.d = (Thread.UncaughtExceptionHandler) Preconditions.checkNotNull(uncaughtExceptionHandler);
        return this;
    }
}
