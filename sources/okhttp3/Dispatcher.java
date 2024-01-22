package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.f;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Dispatcher {

    /* renamed from: a  reason: collision with root package name */
    public int f14222a;
    public int b;
    @Nullable
    public Runnable c;
    @Nullable
    public ExecutorService d;
    @NotNull
    public final ArrayDeque<RealCall.AsyncCall> e;
    @NotNull
    public final ArrayDeque<RealCall.AsyncCall> f;
    @NotNull
    public final ArrayDeque<RealCall> g;

    public Dispatcher() {
        this.f14222a = 64;
        this.b = 5;
        this.e = new ArrayDeque<>();
        this.f = new ArrayDeque<>();
        this.g = new ArrayDeque<>();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "executorService", imports = {}))
    @JvmName(name = "-deprecated_executorService")
    @NotNull
    /* renamed from: -deprecated_executorService  reason: not valid java name */
    public final ExecutorService m826deprecated_executorService() {
        return executorService();
    }

    public final RealCall.AsyncCall a(String str) {
        Iterator<RealCall.AsyncCall> it = this.f.iterator();
        while (it.hasNext()) {
            RealCall.AsyncCall next = it.next();
            if (Intrinsics.areEqual(next.getHost(), str)) {
                return next;
            }
        }
        Iterator<RealCall.AsyncCall> it2 = this.e.iterator();
        while (it2.hasNext()) {
            RealCall.AsyncCall next2 = it2.next();
            if (Intrinsics.areEqual(next2.getHost(), str)) {
                return next2;
            }
        }
        return null;
    }

    public final <T> void b(Deque<T> deque, T t) {
        Runnable idleCallback;
        synchronized (this) {
            if (deque.remove(t)) {
                idleCallback = getIdleCallback();
                Unit unit = Unit.INSTANCE;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (c() || idleCallback == null) {
            return;
        }
        idleCallback.run();
    }

    public final boolean c() {
        int i;
        boolean z;
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<RealCall.AsyncCall> it = this.e.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "readyAsyncCalls.iterator()");
            while (it.hasNext()) {
                RealCall.AsyncCall asyncCall = it.next();
                if (this.f.size() >= getMaxRequests()) {
                    break;
                } else if (asyncCall.getCallsPerHost().get() < getMaxRequestsPerHost()) {
                    it.remove();
                    asyncCall.getCallsPerHost().incrementAndGet();
                    Intrinsics.checkNotNullExpressionValue(asyncCall, "asyncCall");
                    arrayList.add(asyncCall);
                    this.f.add(asyncCall);
                }
            }
            z = runningCallsCount() > 0;
            Unit unit = Unit.INSTANCE;
        }
        int size = arrayList.size();
        for (i = 0; i < size; i++) {
            ((RealCall.AsyncCall) arrayList.get(i)).executeOn(executorService());
        }
        return z;
    }

    public final synchronized void cancelAll() {
        Iterator<RealCall.AsyncCall> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().getCall().cancel();
        }
        Iterator<RealCall.AsyncCall> it2 = this.f.iterator();
        while (it2.hasNext()) {
            it2.next().getCall().cancel();
        }
        Iterator<RealCall> it3 = this.g.iterator();
        while (it3.hasNext()) {
            it3.next().cancel();
        }
    }

    public final void enqueue$okhttp(@NotNull RealCall.AsyncCall call) {
        RealCall.AsyncCall a2;
        Intrinsics.checkNotNullParameter(call, "call");
        synchronized (this) {
            this.e.add(call);
            if (!call.getCall().getForWebSocket() && (a2 = a(call.getHost())) != null) {
                call.reuseCallsPerHostFrom(a2);
            }
            Unit unit = Unit.INSTANCE;
        }
        c();
    }

    public final synchronized void executed$okhttp(@NotNull RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.g.add(call);
    }

    @JvmName(name = "executorService")
    @NotNull
    public final synchronized ExecutorService executorService() {
        ExecutorService executorService;
        if (this.d == null) {
            this.d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory(Intrinsics.stringPlus(Util.okHttpName, " Dispatcher"), false));
        }
        executorService = this.d;
        Intrinsics.checkNotNull(executorService);
        return executorService;
    }

    public final void finished$okhttp(@NotNull RealCall.AsyncCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.getCallsPerHost().decrementAndGet();
        b(this.f, call);
    }

    @Nullable
    public final synchronized Runnable getIdleCallback() {
        return this.c;
    }

    public final synchronized int getMaxRequests() {
        return this.f14222a;
    }

    public final synchronized int getMaxRequestsPerHost() {
        return this.b;
    }

    @NotNull
    public final synchronized List<Call> queuedCalls() {
        List<Call> unmodifiableList;
        ArrayDeque<RealCall.AsyncCall> arrayDeque = this.e;
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(arrayDeque, 10));
        for (RealCall.AsyncCall asyncCall : arrayDeque) {
            arrayList.add(asyncCall.getCall());
        }
        unmodifiableList = Collections.unmodifiableList(arrayList);
        Intrinsics.checkNotNullExpressionValue(unmodifiableList, "unmodifiableList(readyAsyncCalls.map { it.call })");
        return unmodifiableList;
    }

    public final synchronized int queuedCallsCount() {
        return this.e.size();
    }

    @NotNull
    public final synchronized List<Call> runningCalls() {
        List<Call> unmodifiableList;
        ArrayDeque<RealCall> arrayDeque = this.g;
        ArrayDeque<RealCall.AsyncCall> arrayDeque2 = this.f;
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(arrayDeque2, 10));
        for (RealCall.AsyncCall asyncCall : arrayDeque2) {
            arrayList.add(asyncCall.getCall());
        }
        unmodifiableList = Collections.unmodifiableList(CollectionsKt___CollectionsKt.plus((Collection) arrayDeque, (Iterable) arrayList));
        Intrinsics.checkNotNullExpressionValue(unmodifiableList, "unmodifiableList(running…yncCalls.map { it.call })");
        return unmodifiableList;
    }

    public final synchronized int runningCallsCount() {
        return this.f.size() + this.g.size();
    }

    public final synchronized void setIdleCallback(@Nullable Runnable runnable) {
        this.c = runnable;
    }

    public final void setMaxRequests(int i) {
        if (i >= 1) {
            synchronized (this) {
                this.f14222a = i;
                Unit unit = Unit.INSTANCE;
            }
            c();
            return;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("max < 1: ", Integer.valueOf(i)).toString());
    }

    public final void setMaxRequestsPerHost(int i) {
        if (i >= 1) {
            synchronized (this) {
                this.b = i;
                Unit unit = Unit.INSTANCE;
            }
            c();
            return;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("max < 1: ", Integer.valueOf(i)).toString());
    }

    public final void finished$okhttp(@NotNull RealCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        b(this.g, call);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Dispatcher(@NotNull ExecutorService executorService) {
        this();
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        this.d = executorService;
    }
}
