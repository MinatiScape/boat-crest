package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.p;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class ServiceManager {
    public static final Logger c = Logger.getLogger(ServiceManager.class.getName());
    public static final p.a<Listener> d = new a();
    public static final p.a<Listener> e = new b();

    /* renamed from: a  reason: collision with root package name */
    public final f f10797a;
    public final ImmutableList<Service> b;

    /* loaded from: classes10.dex */
    public static abstract class Listener {
        public void failure(Service service) {
        }

        public void healthy() {
        }

        public void stopped() {
        }
    }

    /* loaded from: classes10.dex */
    public class a implements p.a<Listener> {
        @Override // com.google.common.util.concurrent.p.a
        /* renamed from: a */
        public void call(Listener listener) {
            listener.healthy();
        }

        public String toString() {
            return "healthy()";
        }
    }

    /* loaded from: classes10.dex */
    public class b implements p.a<Listener> {
        @Override // com.google.common.util.concurrent.p.a
        /* renamed from: a */
        public void call(Listener listener) {
            listener.stopped();
        }

        public String toString() {
            return "stopped()";
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends Throwable {
        private c() {
        }

        public /* synthetic */ c(a aVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends AbstractService {
        public d() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public void doStart() {
            notifyStarted();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public void doStop() {
            notifyStopped();
        }

        public /* synthetic */ d(a aVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public static final class e extends Service.Listener {

        /* renamed from: a  reason: collision with root package name */
        public final Service f10798a;
        public final WeakReference<f> b;

        public e(Service service, WeakReference<f> weakReference) {
            this.f10798a = service;
            this.b = weakReference;
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void failed(Service.State state, Throwable th) {
            f fVar = this.b.get();
            if (fVar != null) {
                if (!(this.f10798a instanceof d)) {
                    Logger logger = ServiceManager.c;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(this.f10798a);
                    String valueOf2 = String.valueOf(state);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 34 + valueOf2.length());
                    sb.append("Service ");
                    sb.append(valueOf);
                    sb.append(" has failed in the ");
                    sb.append(valueOf2);
                    sb.append(" state.");
                    logger.log(level, sb.toString(), th);
                }
                fVar.n(this.f10798a, state, Service.State.FAILED);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void running() {
            f fVar = this.b.get();
            if (fVar != null) {
                fVar.n(this.f10798a, Service.State.STARTING, Service.State.RUNNING);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void starting() {
            f fVar = this.b.get();
            if (fVar != null) {
                fVar.n(this.f10798a, Service.State.NEW, Service.State.STARTING);
                if (this.f10798a instanceof d) {
                    return;
                }
                ServiceManager.c.log(Level.FINE, "Starting {0}.", this.f10798a);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void stopping(Service.State state) {
            f fVar = this.b.get();
            if (fVar != null) {
                fVar.n(this.f10798a, state, Service.State.STOPPING);
            }
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void terminated(Service.State state) {
            f fVar = this.b.get();
            if (fVar != null) {
                if (!(this.f10798a instanceof d)) {
                    ServiceManager.c.log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[]{this.f10798a, state});
                }
                fVar.n(this.f10798a, state, Service.State.TERMINATED);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class f {

        /* renamed from: a  reason: collision with root package name */
        public final Monitor f10799a = new Monitor();
        @GuardedBy("monitor")
        public final SetMultimap<Service.State, Service> b;
        @GuardedBy("monitor")
        public final Multiset<Service.State> c;
        @GuardedBy("monitor")
        public final Map<Service, Stopwatch> d;
        @GuardedBy("monitor")
        public boolean e;
        @GuardedBy("monitor")
        public boolean f;
        public final int g;
        public final Monitor.Guard h;
        public final Monitor.Guard i;
        public final p<Listener> j;

        /* loaded from: classes10.dex */
        public class a implements Function<Map.Entry<Service, Long>, Long> {
            public a(f fVar) {
            }

            @Override // com.google.common.base.Function
            /* renamed from: a */
            public Long apply(Map.Entry<Service, Long> entry) {
                return entry.getValue();
            }
        }

        /* loaded from: classes10.dex */
        public class b implements p.a<Listener> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Service f10800a;

            public b(f fVar, Service service) {
                this.f10800a = service;
            }

            @Override // com.google.common.util.concurrent.p.a
            /* renamed from: a */
            public void call(Listener listener) {
                listener.failure(this.f10800a);
            }

            public String toString() {
                String valueOf = String.valueOf(this.f10800a);
                StringBuilder sb = new StringBuilder(valueOf.length() + 18);
                sb.append("failed({service=");
                sb.append(valueOf);
                sb.append("})");
                return sb.toString();
            }
        }

        /* loaded from: classes10.dex */
        public final class c extends Monitor.Guard {
            public c() {
                super(f.this.f10799a);
            }

            @Override // com.google.common.util.concurrent.Monitor.Guard
            @GuardedBy("ServiceManagerState.this.monitor")
            public boolean isSatisfied() {
                int count = f.this.c.count(Service.State.RUNNING);
                f fVar = f.this;
                return count == fVar.g || fVar.c.contains(Service.State.STOPPING) || f.this.c.contains(Service.State.TERMINATED) || f.this.c.contains(Service.State.FAILED);
            }
        }

        /* loaded from: classes10.dex */
        public final class d extends Monitor.Guard {
            public d() {
                super(f.this.f10799a);
            }

            @Override // com.google.common.util.concurrent.Monitor.Guard
            @GuardedBy("ServiceManagerState.this.monitor")
            public boolean isSatisfied() {
                return f.this.c.count(Service.State.TERMINATED) + f.this.c.count(Service.State.FAILED) == f.this.g;
            }
        }

        public f(ImmutableCollection<Service> immutableCollection) {
            SetMultimap<Service.State, Service> build = MultimapBuilder.enumKeys(Service.State.class).linkedHashSetValues().build();
            this.b = build;
            this.c = build.keys();
            this.d = Maps.newIdentityHashMap();
            this.h = new c();
            this.i = new d();
            this.j = new p<>();
            this.g = immutableCollection.size();
            build.putAll(Service.State.NEW, immutableCollection);
        }

        public void a(Listener listener, Executor executor) {
            this.j.b(listener, executor);
        }

        public void b() {
            this.f10799a.enterWhenUninterruptibly(this.h);
            try {
                f();
            } finally {
                this.f10799a.leave();
            }
        }

        public void c(long j, TimeUnit timeUnit) throws TimeoutException {
            this.f10799a.enter();
            try {
                if (this.f10799a.waitForUninterruptibly(this.h, j, timeUnit)) {
                    f();
                    return;
                }
                String valueOf = String.valueOf(Multimaps.filterKeys((SetMultimap) this.b, Predicates.in(ImmutableSet.of(Service.State.NEW, Service.State.STARTING))));
                StringBuilder sb = new StringBuilder(valueOf.length() + 93);
                sb.append("Timeout waiting for the services to become healthy. The following services have not started: ");
                sb.append(valueOf);
                throw new TimeoutException(sb.toString());
            } finally {
                this.f10799a.leave();
            }
        }

        public void d() {
            this.f10799a.enterWhenUninterruptibly(this.i);
            this.f10799a.leave();
        }

        public void e(long j, TimeUnit timeUnit) throws TimeoutException {
            this.f10799a.enter();
            try {
                if (this.f10799a.waitForUninterruptibly(this.i, j, timeUnit)) {
                    return;
                }
                String valueOf = String.valueOf(Multimaps.filterKeys((SetMultimap) this.b, Predicates.not(Predicates.in(EnumSet.of(Service.State.TERMINATED, Service.State.FAILED)))));
                StringBuilder sb = new StringBuilder(valueOf.length() + 83);
                sb.append("Timeout waiting for the services to stop. The following services have not stopped: ");
                sb.append(valueOf);
                throw new TimeoutException(sb.toString());
            } finally {
                this.f10799a.leave();
            }
        }

        @GuardedBy("monitor")
        public void f() {
            Multiset<Service.State> multiset = this.c;
            Service.State state = Service.State.RUNNING;
            if (multiset.count(state) == this.g) {
                return;
            }
            String valueOf = String.valueOf(Multimaps.filterKeys((SetMultimap) this.b, Predicates.not(Predicates.equalTo(state))));
            StringBuilder sb = new StringBuilder(valueOf.length() + 79);
            sb.append("Expected to be healthy after starting. The following services are not running: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }

        public void g() {
            Preconditions.checkState(!this.f10799a.isOccupiedByCurrentThread(), "It is incorrect to execute listeners with the monitor held.");
            this.j.c();
        }

        public void h(Service service) {
            this.j.d(new b(this, service));
        }

        public void i() {
            this.j.d(ServiceManager.d);
        }

        public void j() {
            this.j.d(ServiceManager.e);
        }

        public void k() {
            this.f10799a.enter();
            try {
                if (!this.f) {
                    this.e = true;
                    return;
                }
                ArrayList newArrayList = Lists.newArrayList();
                UnmodifiableIterator<Service> it = l().values().iterator();
                while (it.hasNext()) {
                    Service next = it.next();
                    if (next.state() != Service.State.NEW) {
                        newArrayList.add(next);
                    }
                }
                String valueOf = String.valueOf(newArrayList);
                StringBuilder sb = new StringBuilder(valueOf.length() + 89);
                sb.append("Services started transitioning asynchronously before the ServiceManager was constructed: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            } finally {
                this.f10799a.leave();
            }
        }

        public ImmutableSetMultimap<Service.State, Service> l() {
            ImmutableSetMultimap.Builder builder = ImmutableSetMultimap.builder();
            this.f10799a.enter();
            try {
                for (Map.Entry<Service.State, Service> entry : this.b.entries()) {
                    if (!(entry.getValue() instanceof d)) {
                        builder.put((Map.Entry) entry);
                    }
                }
                this.f10799a.leave();
                return builder.build();
            } catch (Throwable th) {
                this.f10799a.leave();
                throw th;
            }
        }

        public ImmutableMap<Service, Long> m() {
            this.f10799a.enter();
            try {
                ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(this.d.size());
                for (Map.Entry<Service, Stopwatch> entry : this.d.entrySet()) {
                    Service key = entry.getKey();
                    Stopwatch value = entry.getValue();
                    if (!value.isRunning() && !(key instanceof d)) {
                        newArrayListWithCapacity.add(Maps.immutableEntry(key, Long.valueOf(value.elapsed(TimeUnit.MILLISECONDS))));
                    }
                }
                this.f10799a.leave();
                Collections.sort(newArrayListWithCapacity, Ordering.natural().onResultOf(new a(this)));
                return ImmutableMap.copyOf(newArrayListWithCapacity);
            } catch (Throwable th) {
                this.f10799a.leave();
                throw th;
            }
        }

        public void n(Service service, Service.State state, Service.State state2) {
            Preconditions.checkNotNull(service);
            Preconditions.checkArgument(state != state2);
            this.f10799a.enter();
            try {
                this.f = true;
                if (this.e) {
                    Preconditions.checkState(this.b.remove(state, service), "Service %s not at the expected location in the state map %s", service, state);
                    Preconditions.checkState(this.b.put(state2, service), "Service %s in the state map unexpectedly at %s", service, state2);
                    Stopwatch stopwatch = this.d.get(service);
                    if (stopwatch == null) {
                        stopwatch = Stopwatch.createStarted();
                        this.d.put(service, stopwatch);
                    }
                    Service.State state3 = Service.State.RUNNING;
                    if (state2.compareTo(state3) >= 0 && stopwatch.isRunning()) {
                        stopwatch.stop();
                        if (!(service instanceof d)) {
                            ServiceManager.c.log(Level.FINE, "Started {0} in {1}.", new Object[]{service, stopwatch});
                        }
                    }
                    Service.State state4 = Service.State.FAILED;
                    if (state2 == state4) {
                        h(service);
                    }
                    if (this.c.count(state3) == this.g) {
                        i();
                    } else if (this.c.count(Service.State.TERMINATED) + this.c.count(state4) == this.g) {
                        j();
                    }
                }
            } finally {
                this.f10799a.leave();
                g();
            }
        }

        public void o(Service service) {
            this.f10799a.enter();
            try {
                if (this.d.get(service) == null) {
                    this.d.put(service, Stopwatch.createStarted());
                }
            } finally {
                this.f10799a.leave();
            }
        }
    }

    public ServiceManager(Iterable<? extends Service> iterable) {
        ImmutableList<Service> copyOf = ImmutableList.copyOf(iterable);
        if (copyOf.isEmpty()) {
            c.log(Level.WARNING, "ServiceManager configured with no services.  Is your application configured properly?", (Throwable) new c(null));
            copyOf = ImmutableList.of(new d(null));
        }
        f fVar = new f(copyOf);
        this.f10797a = fVar;
        this.b = copyOf;
        WeakReference weakReference = new WeakReference(fVar);
        UnmodifiableIterator<Service> it = copyOf.iterator();
        while (it.hasNext()) {
            Service next = it.next();
            next.addListener(new e(next, weakReference), MoreExecutors.directExecutor());
            Preconditions.checkArgument(next.state() == Service.State.NEW, "Can only manage NEW services, %s", next);
        }
        this.f10797a.k();
    }

    public void addListener(Listener listener, Executor executor) {
        this.f10797a.a(listener, executor);
    }

    public void awaitHealthy() {
        this.f10797a.b();
    }

    public void awaitStopped() {
        this.f10797a.d();
    }

    public boolean isHealthy() {
        UnmodifiableIterator<Service> it = this.b.iterator();
        while (it.hasNext()) {
            if (!it.next().isRunning()) {
                return false;
            }
        }
        return true;
    }

    @CanIgnoreReturnValue
    public ServiceManager startAsync() {
        UnmodifiableIterator<Service> it = this.b.iterator();
        while (it.hasNext()) {
            Service next = it.next();
            Service.State state = next.state();
            Preconditions.checkState(state == Service.State.NEW, "Service %s is %s, cannot start it.", next, state);
        }
        UnmodifiableIterator<Service> it2 = this.b.iterator();
        while (it2.hasNext()) {
            Service next2 = it2.next();
            try {
                this.f10797a.o(next2);
                next2.startAsync();
            } catch (IllegalStateException e2) {
                Logger logger = c;
                Level level = Level.WARNING;
                String valueOf = String.valueOf(next2);
                StringBuilder sb = new StringBuilder(valueOf.length() + 24);
                sb.append("Unable to start Service ");
                sb.append(valueOf);
                logger.log(level, sb.toString(), (Throwable) e2);
            }
        }
        return this;
    }

    public ImmutableMap<Service, Long> startupTimes() {
        return this.f10797a.m();
    }

    @CanIgnoreReturnValue
    public ServiceManager stopAsync() {
        UnmodifiableIterator<Service> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().stopAsync();
        }
        return this;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Class<?>) ServiceManager.class).add("services", Collections2.filter(this.b, Predicates.not(Predicates.instanceOf(d.class)))).toString();
    }

    public void awaitHealthy(long j, TimeUnit timeUnit) throws TimeoutException {
        this.f10797a.c(j, timeUnit);
    }

    public void awaitStopped(long j, TimeUnit timeUnit) throws TimeoutException {
        this.f10797a.e(j, timeUnit);
    }

    public ImmutableSetMultimap<Service.State, Service> servicesByState() {
        return this.f10797a.l();
    }
}
