package kotlinx.coroutines.test;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Deprecated(level = DeprecationLevel.ERROR, message = "This API has been deprecated to integrate with Structured Concurrency.", replaceWith = @ReplaceWith(expression = "TestCoroutineScope", imports = {"kotlin.coroutines.test"}))
/* loaded from: classes12.dex */
public final class TestCoroutineContext implements CoroutineContext {
    @Nullable
    public final String h;
    @NotNull
    public final List<Throwable> i;
    @NotNull
    public final Dispatcher j;
    @NotNull
    public final CoroutineExceptionHandler k;
    @NotNull
    public final ThreadSafeHeap<a> l;
    public long m;
    public long n;

    /* loaded from: classes12.dex */
    public final class Dispatcher extends EventLoop implements Delay {
        public Dispatcher() {
            EventLoop.incrementUseCount$default(this, false, 1, null);
        }

        @Override // kotlinx.coroutines.Delay
        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
        @Nullable
        public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
            return Delay.DefaultImpls.delay(this, j, continuation);
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        public void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
            TestCoroutineContext.this.a(runnable);
        }

        @Override // kotlinx.coroutines.Delay
        @NotNull
        public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable, @NotNull CoroutineContext coroutineContext) {
            final a b = TestCoroutineContext.this.b(runnable, j);
            final TestCoroutineContext testCoroutineContext = TestCoroutineContext.this;
            return new DisposableHandle() { // from class: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$invokeOnTimeout$1
                @Override // kotlinx.coroutines.DisposableHandle
                public void dispose() {
                    ThreadSafeHeap threadSafeHeap;
                    threadSafeHeap = TestCoroutineContext.this.l;
                    threadSafeHeap.remove(b);
                }
            };
        }

        @Override // kotlinx.coroutines.EventLoop
        public long processNextEvent() {
            return TestCoroutineContext.this.c();
        }

        @Override // kotlinx.coroutines.Delay
        public void scheduleResumeAfterDelay(long j, @NotNull final CancellableContinuation<? super Unit> cancellableContinuation) {
            TestCoroutineContext.this.b(new Runnable() { // from class: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$scheduleResumeAfterDelay$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    CancellableContinuation.this.resumeUndispatched(this, Unit.INSTANCE);
                }
            }, j);
        }

        @Override // kotlinx.coroutines.EventLoop
        public boolean shouldBeProcessedFromContext() {
            return true;
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        @NotNull
        public String toString() {
            return "Dispatcher(" + TestCoroutineContext.this + HexStringBuilder.COMMENT_END_CHAR;
        }
    }

    public TestCoroutineContext() {
        this(null, 1, null);
    }

    public TestCoroutineContext(@Nullable String str) {
        this.h = str;
        this.i = new ArrayList();
        this.j = new Dispatcher();
        this.k = new TestCoroutineContext$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, this);
        this.l = new ThreadSafeHeap<>();
    }

    public static /* synthetic */ long advanceTimeBy$default(TestCoroutineContext testCoroutineContext, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.advanceTimeBy(j, timeUnit);
    }

    public static /* synthetic */ void advanceTimeTo$default(TestCoroutineContext testCoroutineContext, long j, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 2) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        testCoroutineContext.advanceTimeTo(j, timeUnit);
    }

    public static /* synthetic */ void assertAllUnhandledExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAllUnhandledExceptions(str, function1);
    }

    public static /* synthetic */ void assertAnyUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertAnyUnhandledException(str, function1);
    }

    public static /* synthetic */ void assertExceptions$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertExceptions(str, function1);
    }

    public static /* synthetic */ void assertUnhandledException$default(TestCoroutineContext testCoroutineContext, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        testCoroutineContext.assertUnhandledException(str, function1);
    }

    public static /* synthetic */ long now$default(TestCoroutineContext testCoroutineContext, TimeUnit timeUnit, int i, Object obj) {
        if ((i & 1) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return testCoroutineContext.now(timeUnit);
    }

    public final void a(Runnable runnable) {
        ThreadSafeHeap<a> threadSafeHeap = this.l;
        long j = this.m;
        this.m = 1 + j;
        threadSafeHeap.addLast(new a(runnable, j, 0L, 4, null));
    }

    public final long advanceTimeBy(long j, @NotNull TimeUnit timeUnit) {
        long j2 = this.n;
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        advanceTimeTo(timeUnit.toNanos(j) + j2, timeUnit2);
        return timeUnit.convert(this.n - j2, timeUnit2);
    }

    public final void advanceTimeTo(long j, @NotNull TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        d(nanos);
        if (nanos > this.n) {
            this.n = nanos;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void assertAllUnhandledExceptions(@NotNull String str, @NotNull Function1<? super Throwable, Boolean> function1) {
        List<Throwable> list = this.i;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!function1.invoke(it.next()).booleanValue()) {
                    z = false;
                    break;
                }
            }
        }
        if (z) {
            this.i.clear();
            return;
        }
        throw new AssertionError(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void assertAnyUnhandledException(@NotNull String str, @NotNull Function1<? super Throwable, Boolean> function1) {
        List<Throwable> list = this.i;
        boolean z = false;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (function1.invoke(it.next()).booleanValue()) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            this.i.clear();
            return;
        }
        throw new AssertionError(str);
    }

    public final void assertExceptions(@NotNull String str, @NotNull Function1<? super List<? extends Throwable>, Boolean> function1) {
        if (function1.invoke(this.i).booleanValue()) {
            this.i.clear();
            return;
        }
        throw new AssertionError(str);
    }

    public final void assertUnhandledException(@NotNull String str, @NotNull Function1<? super Throwable, Boolean> function1) {
        if (this.i.size() == 1 && function1.invoke(this.i.get(0)).booleanValue()) {
            this.i.clear();
            return;
        }
        throw new AssertionError(str);
    }

    public final a b(Runnable runnable, long j) {
        long j2 = this.m;
        this.m = 1 + j2;
        a aVar = new a(runnable, j2, this.n + TimeUnit.MILLISECONDS.toNanos(j));
        this.l.addLast(aVar);
        return aVar;
    }

    public final long c() {
        a peek = this.l.peek();
        if (peek != null) {
            d(peek.j);
        }
        return this.l.isEmpty() ? Long.MAX_VALUE : 0L;
    }

    public final void cancelAllActions() {
        if (this.l.isEmpty()) {
            return;
        }
        this.l.clear();
    }

    public final void d(long j) {
        a aVar;
        while (true) {
            ThreadSafeHeap<a> threadSafeHeap = this.l;
            synchronized (threadSafeHeap) {
                a firstImpl = threadSafeHeap.firstImpl();
                if (firstImpl != null) {
                    aVar = (firstImpl.j > j ? 1 : (firstImpl.j == j ? 0 : -1)) <= 0 ? threadSafeHeap.removeAtImpl(0) : null;
                }
            }
            a aVar2 = aVar;
            if (aVar2 == null) {
                return;
            }
            long j2 = aVar2.j;
            if (j2 != 0) {
                this.n = j2;
            }
            aVar2.run();
        }
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return function2.invoke((R) function2.invoke(r, this.j), this.k);
    }

    @Override // kotlin.coroutines.CoroutineContext
    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        if (key == ContinuationInterceptor.Key) {
            return this.j;
        }
        if (key == CoroutineExceptionHandler.Key) {
            return this.k;
        }
        return null;
    }

    @NotNull
    public final List<Throwable> getExceptions() {
        return this.i;
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        return key == ContinuationInterceptor.Key ? this.k : key == CoroutineExceptionHandler.Key ? this.j : this;
    }

    public final long now(@NotNull TimeUnit timeUnit) {
        return timeUnit.convert(this.n, TimeUnit.NANOSECONDS);
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }

    @NotNull
    public String toString() {
        String str = this.h;
        return str == null ? Intrinsics.stringPlus("TestCoroutineContext@", DebugStringsKt.getHexAddress(this)) : str;
    }

    public final void triggerActions() {
        d(this.n);
    }

    public /* synthetic */ TestCoroutineContext(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }
}
