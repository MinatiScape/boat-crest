package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class AbstractExecutionThreadService implements Service {
    public static final Logger b = Logger.getLogger(AbstractExecutionThreadService.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final Service f10740a = new a();

    /* loaded from: classes10.dex */
    public class a extends AbstractService {

        /* renamed from: com.google.common.util.concurrent.AbstractExecutionThreadService$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0521a implements Supplier<String> {
            public C0521a() {
            }

            @Override // com.google.common.base.Supplier
            /* renamed from: a */
            public String get() {
                return AbstractExecutionThreadService.this.serviceName();
            }
        }

        /* loaded from: classes10.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    AbstractExecutionThreadService.this.startUp();
                    a.this.notifyStarted();
                    if (a.this.isRunning()) {
                        AbstractExecutionThreadService.this.run();
                    }
                    AbstractExecutionThreadService.this.shutDown();
                    a.this.notifyStopped();
                } catch (Throwable th) {
                    a.this.notifyFailed(th);
                }
            }
        }

        public a() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            MoreExecutors.e(AbstractExecutionThreadService.this.executor(), new C0521a()).execute(new b());
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public void doStop() {
            AbstractExecutionThreadService.this.triggerShutdown();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractExecutionThreadService.this.toString();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Executor {
        public b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            MoreExecutors.c(AbstractExecutionThreadService.this.serviceName(), runnable).start();
        }
    }

    public static /* synthetic */ Logger a() {
        return b;
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.f10740a.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.f10740a.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.f10740a.awaitTerminated();
    }

    public Executor executor() {
        return new b();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.f10740a.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.f10740a.isRunning();
    }

    public abstract void run() throws Exception;

    public String serviceName() {
        return getClass().getSimpleName();
    }

    public void shutDown() throws Exception {
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.f10740a.startAsync();
        return this;
    }

    public void startUp() throws Exception {
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.f10740a.state();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.f10740a.stopAsync();
        return this;
    }

    public String toString() {
        String serviceName = serviceName();
        String valueOf = String.valueOf(state());
        StringBuilder sb = new StringBuilder(String.valueOf(serviceName).length() + 3 + valueOf.length());
        sb.append(serviceName);
        sb.append(" [");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    @Beta
    public void triggerShutdown() {
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        this.f10740a.awaitRunning(j, timeUnit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        this.f10740a.awaitTerminated(j, timeUnit);
    }
}
