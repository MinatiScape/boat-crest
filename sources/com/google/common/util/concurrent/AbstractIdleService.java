package com.google.common.util.concurrent;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class AbstractIdleService implements Service {

    /* renamed from: a  reason: collision with root package name */
    public final Supplier<String> f10747a = new c(this, null);
    public final Service b = new b(this, null);

    /* loaded from: classes10.dex */
    public class a implements Executor {
        public a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            MoreExecutors.c((String) AbstractIdleService.this.f10747a.get(), runnable).start();
        }
    }

    /* loaded from: classes10.dex */
    public final class b extends AbstractService {

        /* loaded from: classes10.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    AbstractIdleService.this.startUp();
                    b.this.notifyStarted();
                } catch (Throwable th) {
                    b.this.notifyFailed(th);
                }
            }
        }

        /* renamed from: com.google.common.util.concurrent.AbstractIdleService$b$b  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class RunnableC0522b implements Runnable {
            public RunnableC0522b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    AbstractIdleService.this.shutDown();
                    b.this.notifyStopped();
                } catch (Throwable th) {
                    b.this.notifyFailed(th);
                }
            }
        }

        public b() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            MoreExecutors.e(AbstractIdleService.this.executor(), AbstractIdleService.this.f10747a).execute(new a());
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStop() {
            MoreExecutors.e(AbstractIdleService.this.executor(), AbstractIdleService.this.f10747a).execute(new RunnableC0522b());
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractIdleService.this.toString();
        }

        public /* synthetic */ b(AbstractIdleService abstractIdleService, a aVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public final class c implements Supplier<String> {
        public c() {
        }

        @Override // com.google.common.base.Supplier
        /* renamed from: a */
        public String get() {
            String serviceName = AbstractIdleService.this.serviceName();
            String valueOf = String.valueOf(AbstractIdleService.this.state());
            StringBuilder sb = new StringBuilder(String.valueOf(serviceName).length() + 1 + valueOf.length());
            sb.append(serviceName);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(valueOf);
            return sb.toString();
        }

        public /* synthetic */ c(AbstractIdleService abstractIdleService, a aVar) {
            this();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.b.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.b.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.b.awaitTerminated();
    }

    public Executor executor() {
        return new a();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.b.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.b.isRunning();
    }

    public String serviceName() {
        return getClass().getSimpleName();
    }

    public abstract void shutDown() throws Exception;

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.b.startAsync();
        return this;
    }

    public abstract void startUp() throws Exception;

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.b.state();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.b.stopAsync();
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

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        this.b.awaitRunning(j, timeUnit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        this.b.awaitTerminated(j, timeUnit);
    }
}
