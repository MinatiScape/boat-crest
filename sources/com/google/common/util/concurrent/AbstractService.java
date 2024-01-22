package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.p;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class AbstractService implements Service {
    public static final p.a<Service.Listener> h = new a();
    public static final p.a<Service.Listener> i = new b();
    public static final p.a<Service.Listener> j;
    public static final p.a<Service.Listener> k;
    public static final p.a<Service.Listener> l;
    public static final p.a<Service.Listener> m;
    public static final p.a<Service.Listener> n;
    public static final p.a<Service.Listener> o;

    /* renamed from: a  reason: collision with root package name */
    public final Monitor f10753a = new Monitor();
    public final Monitor.Guard b = new h();
    public final Monitor.Guard c = new i();
    public final Monitor.Guard d = new g();
    public final Monitor.Guard e = new j();
    public final p<Service.Listener> f = new p<>();
    public volatile k g = new k(Service.State.NEW);

    /* loaded from: classes10.dex */
    public class a implements p.a<Service.Listener> {
        @Override // com.google.common.util.concurrent.p.a
        /* renamed from: a */
        public void call(Service.Listener listener) {
            listener.starting();
        }

        public String toString() {
            return "starting()";
        }
    }

    /* loaded from: classes10.dex */
    public class b implements p.a<Service.Listener> {
        @Override // com.google.common.util.concurrent.p.a
        /* renamed from: a */
        public void call(Service.Listener listener) {
            listener.running();
        }

        public String toString() {
            return "running()";
        }
    }

    /* loaded from: classes10.dex */
    public class c implements p.a<Service.Listener> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Service.State f10754a;

        public c(Service.State state) {
            this.f10754a = state;
        }

        @Override // com.google.common.util.concurrent.p.a
        /* renamed from: a */
        public void call(Service.Listener listener) {
            listener.terminated(this.f10754a);
        }

        public String toString() {
            String valueOf = String.valueOf(this.f10754a);
            StringBuilder sb = new StringBuilder(valueOf.length() + 21);
            sb.append("terminated({from = ");
            sb.append(valueOf);
            sb.append("})");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public class d implements p.a<Service.Listener> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Service.State f10755a;

        public d(Service.State state) {
            this.f10755a = state;
        }

        @Override // com.google.common.util.concurrent.p.a
        /* renamed from: a */
        public void call(Service.Listener listener) {
            listener.stopping(this.f10755a);
        }

        public String toString() {
            String valueOf = String.valueOf(this.f10755a);
            StringBuilder sb = new StringBuilder(valueOf.length() + 19);
            sb.append("stopping({from = ");
            sb.append(valueOf);
            sb.append("})");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public class e implements p.a<Service.Listener> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Service.State f10756a;
        public final /* synthetic */ Throwable b;

        public e(AbstractService abstractService, Service.State state, Throwable th) {
            this.f10756a = state;
            this.b = th;
        }

        @Override // com.google.common.util.concurrent.p.a
        /* renamed from: a */
        public void call(Service.Listener listener) {
            listener.failed(this.f10756a, this.b);
        }

        public String toString() {
            String valueOf = String.valueOf(this.f10756a);
            String valueOf2 = String.valueOf(this.b);
            StringBuilder sb = new StringBuilder(valueOf.length() + 27 + valueOf2.length());
            sb.append("failed({from = ");
            sb.append(valueOf);
            sb.append(", cause = ");
            sb.append(valueOf2);
            sb.append("})");
            return sb.toString();
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class f {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10757a;

        static {
            int[] iArr = new int[Service.State.values().length];
            f10757a = iArr;
            try {
                iArr[Service.State.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10757a[Service.State.STARTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10757a[Service.State.RUNNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10757a[Service.State.STOPPING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10757a[Service.State.TERMINATED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10757a[Service.State.FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public final class g extends Monitor.Guard {
        public g() {
            super(AbstractService.this.f10753a);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) >= 0;
        }
    }

    /* loaded from: classes10.dex */
    public final class h extends Monitor.Guard {
        public h() {
            super(AbstractService.this.f10753a);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state() == Service.State.NEW;
        }
    }

    /* loaded from: classes10.dex */
    public final class i extends Monitor.Guard {
        public i() {
            super(AbstractService.this.f10753a);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) <= 0;
        }
    }

    /* loaded from: classes10.dex */
    public final class j extends Monitor.Guard {
        public j() {
            super(AbstractService.this.f10753a);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    }

    /* loaded from: classes10.dex */
    public static final class k {

        /* renamed from: a  reason: collision with root package name */
        public final Service.State f10758a;
        public final boolean b;
        @NullableDecl
        public final Throwable c;

        public k(Service.State state) {
            this(state, false, null);
        }

        public Service.State a() {
            if (this.b && this.f10758a == Service.State.STARTING) {
                return Service.State.STOPPING;
            }
            return this.f10758a;
        }

        public Throwable b() {
            Service.State state = this.f10758a;
            Preconditions.checkState(state == Service.State.FAILED, "failureCause() is only valid if the service has failed, service is %s", state);
            return this.c;
        }

        public k(Service.State state, boolean z, @NullableDecl Throwable th) {
            Preconditions.checkArgument(!z || state == Service.State.STARTING, "shutdownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", state);
            Preconditions.checkArgument(!((state == Service.State.FAILED) ^ (th != null)), "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", state, th);
            this.f10758a = state;
            this.b = z;
            this.c = th;
        }
    }

    static {
        Service.State state = Service.State.STARTING;
        j = i(state);
        Service.State state2 = Service.State.RUNNING;
        k = i(state2);
        l = j(Service.State.NEW);
        m = j(state);
        n = j(state2);
        o = j(Service.State.STOPPING);
    }

    public static p.a<Service.Listener> i(Service.State state) {
        return new d(state);
    }

    public static p.a<Service.Listener> j(Service.State state) {
        return new c(state);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.f.b(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.f10753a.enterWhenUninterruptibly(this.d);
        try {
            b(Service.State.RUNNING);
        } finally {
            this.f10753a.leave();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.f10753a.enterWhenUninterruptibly(this.e);
        try {
            b(Service.State.TERMINATED);
        } finally {
            this.f10753a.leave();
        }
    }

    @GuardedBy("monitor")
    public final void b(Service.State state) {
        Service.State state2 = state();
        if (state2 != state) {
            if (state2 == Service.State.FAILED) {
                String valueOf = String.valueOf(this);
                String valueOf2 = String.valueOf(state);
                StringBuilder sb = new StringBuilder(valueOf.length() + 56 + valueOf2.length());
                sb.append("Expected the service ");
                sb.append(valueOf);
                sb.append(" to be ");
                sb.append(valueOf2);
                sb.append(", but the service has FAILED");
                throw new IllegalStateException(sb.toString(), failureCause());
            }
            String valueOf3 = String.valueOf(this);
            String valueOf4 = String.valueOf(state);
            String valueOf5 = String.valueOf(state2);
            StringBuilder sb2 = new StringBuilder(valueOf3.length() + 38 + valueOf4.length() + valueOf5.length());
            sb2.append("Expected the service ");
            sb2.append(valueOf3);
            sb2.append(" to be ");
            sb2.append(valueOf4);
            sb2.append(", but was ");
            sb2.append(valueOf5);
            throw new IllegalStateException(sb2.toString());
        }
    }

    public final void c() {
        if (this.f10753a.isOccupiedByCurrentThread()) {
            return;
        }
        this.f.c();
    }

    public final void d(Service.State state, Throwable th) {
        this.f.d(new e(this, state, th));
    }

    @Beta
    @ForOverride
    public void doCancelStart() {
    }

    @ForOverride
    public abstract void doStart();

    @ForOverride
    public abstract void doStop();

    public final void e() {
        this.f.d(i);
    }

    public final void f() {
        this.f.d(h);
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.g.b();
    }

    public final void g(Service.State state) {
        if (state == Service.State.STARTING) {
            this.f.d(j);
        } else if (state == Service.State.RUNNING) {
            this.f.d(k);
        } else {
            throw new AssertionError();
        }
    }

    public final void h(Service.State state) {
        switch (f.f10757a[state.ordinal()]) {
            case 1:
                this.f.d(l);
                return;
            case 2:
                this.f.d(m);
                return;
            case 3:
                this.f.d(n);
                return;
            case 4:
                this.f.d(o);
                return;
            case 5:
            case 6:
                throw new AssertionError();
            default:
                return;
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return state() == Service.State.RUNNING;
    }

    public final void notifyFailed(Throwable th) {
        Preconditions.checkNotNull(th);
        this.f10753a.enter();
        try {
            Service.State state = state();
            int i2 = f.f10757a[state.ordinal()];
            if (i2 != 1) {
                if (i2 == 2 || i2 == 3 || i2 == 4) {
                    this.g = new k(Service.State.FAILED, false, th);
                    d(state, th);
                } else if (i2 != 5) {
                }
                return;
            }
            String valueOf = String.valueOf(state);
            StringBuilder sb = new StringBuilder(valueOf.length() + 22);
            sb.append("Failed while in state:");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString(), th);
        } finally {
            this.f10753a.leave();
            c();
        }
    }

    public final void notifyStarted() {
        this.f10753a.enter();
        try {
            if (this.g.f10758a == Service.State.STARTING) {
                if (this.g.b) {
                    this.g = new k(Service.State.STOPPING);
                    doStop();
                } else {
                    this.g = new k(Service.State.RUNNING);
                    e();
                }
                return;
            }
            String valueOf = String.valueOf(this.g.f10758a);
            StringBuilder sb = new StringBuilder(valueOf.length() + 43);
            sb.append("Cannot notifyStarted() when the service is ");
            sb.append(valueOf);
            IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
            notifyFailed(illegalStateException);
            throw illegalStateException;
        } finally {
            this.f10753a.leave();
            c();
        }
    }

    public final void notifyStopped() {
        this.f10753a.enter();
        try {
            Service.State state = state();
            switch (f.f10757a[state.ordinal()]) {
                case 1:
                case 5:
                case 6:
                    String valueOf = String.valueOf(state);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 43);
                    sb.append("Cannot notifyStopped() when the service is ");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
                case 2:
                case 3:
                case 4:
                    this.g = new k(Service.State.TERMINATED);
                    h(state);
                    break;
            }
        } finally {
            this.f10753a.leave();
            c();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        if (this.f10753a.enterIf(this.b)) {
            try {
                this.g = new k(Service.State.STARTING);
                f();
                doStart();
            } finally {
                try {
                    return this;
                } finally {
                }
            }
            return this;
        }
        String valueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(valueOf.length() + 33);
        sb.append("Service ");
        sb.append(valueOf);
        sb.append(" has already been started");
        throw new IllegalStateException(sb.toString());
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.g.a();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        if (this.f10753a.enterIf(this.c)) {
            try {
                Service.State state = state();
                switch (f.f10757a[state.ordinal()]) {
                    case 1:
                        this.g = new k(Service.State.TERMINATED);
                        h(Service.State.NEW);
                        break;
                    case 2:
                        Service.State state2 = Service.State.STARTING;
                        this.g = new k(state2, true, null);
                        g(state2);
                        doCancelStart();
                        break;
                    case 3:
                        this.g = new k(Service.State.STOPPING);
                        g(Service.State.RUNNING);
                        doStop();
                        break;
                    case 4:
                    case 5:
                    case 6:
                        String valueOf = String.valueOf(state);
                        StringBuilder sb = new StringBuilder(valueOf.length() + 45);
                        sb.append("isStoppable is incorrectly implemented, saw: ");
                        sb.append(valueOf);
                        throw new AssertionError(sb.toString());
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return this;
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        String valueOf = String.valueOf(state());
        StringBuilder sb = new StringBuilder(simpleName.length() + 3 + valueOf.length());
        sb.append(simpleName);
        sb.append(" [");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long j2, TimeUnit timeUnit) throws TimeoutException {
        if (this.f10753a.enterWhenUninterruptibly(this.d, j2, timeUnit)) {
            try {
                b(Service.State.RUNNING);
                return;
            } finally {
                this.f10753a.leave();
            }
        }
        String valueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(valueOf.length() + 50);
        sb.append("Timed out waiting for ");
        sb.append(valueOf);
        sb.append(" to reach the RUNNING state.");
        throw new TimeoutException(sb.toString());
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long j2, TimeUnit timeUnit) throws TimeoutException {
        if (this.f10753a.enterWhenUninterruptibly(this.e, j2, timeUnit)) {
            try {
                b(Service.State.TERMINATED);
                return;
            } finally {
                this.f10753a.leave();
            }
        }
        String valueOf = String.valueOf(this);
        String valueOf2 = String.valueOf(state());
        StringBuilder sb = new StringBuilder(valueOf.length() + 65 + valueOf2.length());
        sb.append("Timed out waiting for ");
        sb.append(valueOf);
        sb.append(" to reach a terminal state. Current state: ");
        sb.append(valueOf2);
        throw new TimeoutException(sb.toString());
    }
}
