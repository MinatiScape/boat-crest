package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class v<V> extends FluentFuture.a<V> {
    @NullableDecl
    public ListenableFuture<V> o;
    @NullableDecl
    public ScheduledFuture<?> p;

    /* loaded from: classes10.dex */
    public static final class b<V> implements Runnable {
        @NullableDecl
        public v<V> h;

        public b(v<V> vVar) {
            this.h = vVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenableFuture<? extends V> listenableFuture;
            v<V> vVar = this.h;
            if (vVar == null || (listenableFuture = vVar.o) == null) {
                return;
            }
            this.h = null;
            if (!listenableFuture.isDone()) {
                try {
                    ScheduledFuture scheduledFuture = vVar.p;
                    vVar.p = null;
                    String str = "Timed out";
                    if (scheduledFuture != null) {
                        long abs = Math.abs(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
                        if (abs > 10) {
                            StringBuilder sb = new StringBuilder("Timed out".length() + 66);
                            sb.append("Timed out");
                            sb.append(" (timeout delayed by ");
                            sb.append(abs);
                            sb.append(" ms after scheduled time)");
                            str = sb.toString();
                        }
                    }
                    String valueOf = String.valueOf(str);
                    String valueOf2 = String.valueOf(listenableFuture);
                    StringBuilder sb2 = new StringBuilder(valueOf.length() + 2 + valueOf2.length());
                    sb2.append(valueOf);
                    sb2.append(": ");
                    sb2.append(valueOf2);
                    vVar.setException(new c(sb2.toString()));
                    return;
                } finally {
                    listenableFuture.cancel(true);
                }
            }
            vVar.setFuture(listenableFuture);
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends TimeoutException {
        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }

        private c(String str) {
            super(str);
        }
    }

    public v(ListenableFuture<V> listenableFuture) {
        this.o = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
    }

    public static <V> ListenableFuture<V> B(ListenableFuture<V> listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        v vVar = new v(listenableFuture);
        b bVar = new b(vVar);
        vVar.p = scheduledExecutorService.schedule(bVar, j, timeUnit);
        listenableFuture.addListener(bVar, MoreExecutors.directExecutor());
        return vVar;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public void afterDone() {
        v(this.o);
        ScheduledFuture<?> scheduledFuture = this.p;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.o = null;
        this.p = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ListenableFuture<V> listenableFuture = this.o;
        ScheduledFuture<?> scheduledFuture = this.p;
        if (listenableFuture != null) {
            String valueOf = String.valueOf(listenableFuture);
            StringBuilder sb = new StringBuilder(valueOf.length() + 14);
            sb.append("inputFuture=[");
            sb.append(valueOf);
            sb.append("]");
            String sb2 = sb.toString();
            if (scheduledFuture != null) {
                long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
                if (delay > 0) {
                    String valueOf2 = String.valueOf(sb2);
                    StringBuilder sb3 = new StringBuilder(valueOf2.length() + 43);
                    sb3.append(valueOf2);
                    sb3.append(", remaining delay=[");
                    sb3.append(delay);
                    sb3.append(" ms]");
                    return sb3.toString();
                }
                return sb2;
            }
            return sb2;
        }
        return null;
    }
}
