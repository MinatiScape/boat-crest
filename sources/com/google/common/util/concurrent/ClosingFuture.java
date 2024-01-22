package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Closeable;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use ClosingFuture.from(Futures.immediate*Future)")
@Beta
/* loaded from: classes10.dex */
public final class ClosingFuture<V> {
    public static final Logger d = Logger.getLogger(ClosingFuture.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference<n> f10760a;
    public final m b;
    public final FluentFuture<V> c;

    /* loaded from: classes10.dex */
    public interface AsyncClosingCallable<V> {
        ClosingFuture<V> call(DeferredCloser deferredCloser) throws Exception;
    }

    /* loaded from: classes10.dex */
    public interface AsyncClosingFunction<T, U> {
        ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl T t) throws Exception;
    }

    /* loaded from: classes10.dex */
    public interface ClosingCallable<V> {
        @NullableDecl
        V call(DeferredCloser deferredCloser) throws Exception;
    }

    /* loaded from: classes10.dex */
    public interface ClosingFunction<T, U> {
        @NullableDecl
        U apply(DeferredCloser deferredCloser, @NullableDecl T t) throws Exception;
    }

    @DoNotMock("Use ClosingFuture.whenAllSucceed() or .whenAllComplete() instead.")
    /* loaded from: classes10.dex */
    public static class Combiner {
        public static final Function<ClosingFuture<?>, FluentFuture<?>> c = new c();

        /* renamed from: a  reason: collision with root package name */
        public final m f10761a;
        public final boolean b;
        public final ImmutableList<ClosingFuture<?>> inputs;

        /* loaded from: classes10.dex */
        public interface AsyncCombiningCallable<V> {
            ClosingFuture<V> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception;
        }

        /* loaded from: classes10.dex */
        public interface CombiningCallable<V> {
            @NullableDecl
            V call(DeferredCloser deferredCloser, Peeker peeker) throws Exception;
        }

        /* loaded from: classes10.dex */
        public class a implements Callable<V> {
            public final /* synthetic */ CombiningCallable h;

            public a(CombiningCallable combiningCallable) {
                this.h = combiningCallable;
            }

            @Override // java.util.concurrent.Callable
            public V call() throws Exception {
                return (V) new Peeker(Combiner.this.inputs, null).c(this.h, Combiner.this.f10761a);
            }

            public String toString() {
                return this.h.toString();
            }
        }

        /* loaded from: classes10.dex */
        public class b implements AsyncCallable<V> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AsyncCombiningCallable f10762a;

            public b(AsyncCombiningCallable asyncCombiningCallable) {
                this.f10762a = asyncCombiningCallable;
            }

            @Override // com.google.common.util.concurrent.AsyncCallable
            public ListenableFuture<V> call() throws Exception {
                return new Peeker(Combiner.this.inputs, null).d(this.f10762a, Combiner.this.f10761a);
            }

            public String toString() {
                return this.f10762a.toString();
            }
        }

        /* loaded from: classes10.dex */
        public class c implements Function<ClosingFuture<?>, FluentFuture<?>> {
            @Override // com.google.common.base.Function
            /* renamed from: a */
            public FluentFuture<?> apply(ClosingFuture<?> closingFuture) {
                return closingFuture.c;
            }
        }

        public /* synthetic */ Combiner(boolean z, Iterable iterable, d dVar) {
            this(z, iterable);
        }

        public final Futures.FutureCombiner<Object> b() {
            if (this.b) {
                return Futures.whenAllSucceed(c());
            }
            return Futures.whenAllComplete(c());
        }

        public final ImmutableList<FluentFuture<?>> c() {
            return FluentIterable.from(this.inputs).transform(c).toList();
        }

        public <V> ClosingFuture<V> call(CombiningCallable<V> combiningCallable, Executor executor) {
            ClosingFuture<V> closingFuture = new ClosingFuture<>(b().call(new a(combiningCallable), executor), (d) null);
            closingFuture.b.add(this.f10761a, MoreExecutors.directExecutor());
            return closingFuture;
        }

        public <V> ClosingFuture<V> callAsync(AsyncCombiningCallable<V> asyncCombiningCallable, Executor executor) {
            ClosingFuture<V> closingFuture = new ClosingFuture<>(b().callAsync(new b(asyncCombiningCallable), executor), (d) null);
            closingFuture.b.add(this.f10761a, MoreExecutors.directExecutor());
            return closingFuture;
        }

        public Combiner(boolean z, Iterable<? extends ClosingFuture<?>> iterable) {
            this.f10761a = new m(null);
            this.b = z;
            this.inputs = ImmutableList.copyOf(iterable);
            for (ClosingFuture<?> closingFuture : iterable) {
                closingFuture.i(this.f10761a);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class Combiner2<V1, V2> extends Combiner {
        public final ClosingFuture<V1> d;
        public final ClosingFuture<V2> e;

        /* loaded from: classes10.dex */
        public interface AsyncClosingFunction2<V1, V2, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2) throws Exception;
        }

        /* loaded from: classes10.dex */
        public interface ClosingFunction2<V1, V2, U> {
            @NullableDecl
            U apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2) throws Exception;
        }

        /* JADX INFO: Add missing generic type declarations: [U] */
        /* loaded from: classes10.dex */
        public class a<U> implements Combiner.CombiningCallable<U> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ ClosingFunction2 f10763a;

            public a(ClosingFunction2 closingFunction2) {
                this.f10763a = closingFunction2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.CombiningCallable
            @NullableDecl
            public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                return (U) this.f10763a.apply(deferredCloser, peeker.getDone(Combiner2.this.d), peeker.getDone(Combiner2.this.e));
            }

            public String toString() {
                return this.f10763a.toString();
            }
        }

        /* JADX INFO: Add missing generic type declarations: [U] */
        /* loaded from: classes10.dex */
        public class b<U> implements Combiner.AsyncCombiningCallable<U> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AsyncClosingFunction2 f10764a;

            public b(AsyncClosingFunction2 asyncClosingFunction2) {
                this.f10764a = asyncClosingFunction2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.AsyncCombiningCallable
            public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                return this.f10764a.apply(deferredCloser, peeker.getDone(Combiner2.this.d), peeker.getDone(Combiner2.this.e));
            }

            public String toString() {
                return this.f10764a.toString();
            }
        }

        public /* synthetic */ Combiner2(ClosingFuture closingFuture, ClosingFuture closingFuture2, d dVar) {
            this(closingFuture, closingFuture2);
        }

        public <U> ClosingFuture<U> call(ClosingFunction2<V1, V2, U> closingFunction2, Executor executor) {
            return (ClosingFuture<V>) call(new a(closingFunction2), executor);
        }

        public <U> ClosingFuture<U> callAsync(AsyncClosingFunction2<V1, V2, U> asyncClosingFunction2, Executor executor) {
            return (ClosingFuture<V>) callAsync(new b(asyncClosingFunction2), executor);
        }

        public Combiner2(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2) {
            super(true, ImmutableList.of((ClosingFuture<V2>) closingFuture, closingFuture2), null);
            this.d = closingFuture;
            this.e = closingFuture2;
        }
    }

    /* loaded from: classes10.dex */
    public static final class Combiner3<V1, V2, V3> extends Combiner {
        public final ClosingFuture<V1> d;
        public final ClosingFuture<V2> e;
        public final ClosingFuture<V3> f;

        /* loaded from: classes10.dex */
        public interface AsyncClosingFunction3<V1, V2, V3, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3) throws Exception;
        }

        /* loaded from: classes10.dex */
        public interface ClosingFunction3<V1, V2, V3, U> {
            @NullableDecl
            U apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3) throws Exception;
        }

        /* JADX INFO: Add missing generic type declarations: [U] */
        /* loaded from: classes10.dex */
        public class a<U> implements Combiner.CombiningCallable<U> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ ClosingFunction3 f10765a;

            public a(ClosingFunction3 closingFunction3) {
                this.f10765a = closingFunction3;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.CombiningCallable
            @NullableDecl
            public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                return (U) this.f10765a.apply(deferredCloser, peeker.getDone(Combiner3.this.d), peeker.getDone(Combiner3.this.e), peeker.getDone(Combiner3.this.f));
            }

            public String toString() {
                return this.f10765a.toString();
            }
        }

        /* JADX INFO: Add missing generic type declarations: [U] */
        /* loaded from: classes10.dex */
        public class b<U> implements Combiner.AsyncCombiningCallable<U> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AsyncClosingFunction3 f10766a;

            public b(AsyncClosingFunction3 asyncClosingFunction3) {
                this.f10766a = asyncClosingFunction3;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.AsyncCombiningCallable
            public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                return this.f10766a.apply(deferredCloser, peeker.getDone(Combiner3.this.d), peeker.getDone(Combiner3.this.e), peeker.getDone(Combiner3.this.f));
            }

            public String toString() {
                return this.f10766a.toString();
            }
        }

        public /* synthetic */ Combiner3(ClosingFuture closingFuture, ClosingFuture closingFuture2, ClosingFuture closingFuture3, d dVar) {
            this(closingFuture, closingFuture2, closingFuture3);
        }

        public <U> ClosingFuture<U> call(ClosingFunction3<V1, V2, V3, U> closingFunction3, Executor executor) {
            return (ClosingFuture<V>) call(new a(closingFunction3), executor);
        }

        public <U> ClosingFuture<U> callAsync(AsyncClosingFunction3<V1, V2, V3, U> asyncClosingFunction3, Executor executor) {
            return (ClosingFuture<V>) callAsync(new b(asyncClosingFunction3), executor);
        }

        public Combiner3(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3) {
            super(true, ImmutableList.of((ClosingFuture<V3>) closingFuture, (ClosingFuture<V3>) closingFuture2, closingFuture3), null);
            this.d = closingFuture;
            this.e = closingFuture2;
            this.f = closingFuture3;
        }
    }

    /* loaded from: classes10.dex */
    public static final class Combiner4<V1, V2, V3, V4> extends Combiner {
        public final ClosingFuture<V1> d;
        public final ClosingFuture<V2> e;
        public final ClosingFuture<V3> f;
        public final ClosingFuture<V4> g;

        /* loaded from: classes10.dex */
        public interface AsyncClosingFunction4<V1, V2, V3, V4, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3, @NullableDecl V4 v4) throws Exception;
        }

        /* loaded from: classes10.dex */
        public interface ClosingFunction4<V1, V2, V3, V4, U> {
            @NullableDecl
            U apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3, @NullableDecl V4 v4) throws Exception;
        }

        /* JADX INFO: Add missing generic type declarations: [U] */
        /* loaded from: classes10.dex */
        public class a<U> implements Combiner.CombiningCallable<U> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ ClosingFunction4 f10767a;

            public a(ClosingFunction4 closingFunction4) {
                this.f10767a = closingFunction4;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.CombiningCallable
            @NullableDecl
            public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                return (U) this.f10767a.apply(deferredCloser, peeker.getDone(Combiner4.this.d), peeker.getDone(Combiner4.this.e), peeker.getDone(Combiner4.this.f), peeker.getDone(Combiner4.this.g));
            }

            public String toString() {
                return this.f10767a.toString();
            }
        }

        /* JADX INFO: Add missing generic type declarations: [U] */
        /* loaded from: classes10.dex */
        public class b<U> implements Combiner.AsyncCombiningCallable<U> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AsyncClosingFunction4 f10768a;

            public b(AsyncClosingFunction4 asyncClosingFunction4) {
                this.f10768a = asyncClosingFunction4;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.AsyncCombiningCallable
            public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                return this.f10768a.apply(deferredCloser, peeker.getDone(Combiner4.this.d), peeker.getDone(Combiner4.this.e), peeker.getDone(Combiner4.this.f), peeker.getDone(Combiner4.this.g));
            }

            public String toString() {
                return this.f10768a.toString();
            }
        }

        public /* synthetic */ Combiner4(ClosingFuture closingFuture, ClosingFuture closingFuture2, ClosingFuture closingFuture3, ClosingFuture closingFuture4, d dVar) {
            this(closingFuture, closingFuture2, closingFuture3, closingFuture4);
        }

        public <U> ClosingFuture<U> call(ClosingFunction4<V1, V2, V3, V4, U> closingFunction4, Executor executor) {
            return (ClosingFuture<V>) call(new a(closingFunction4), executor);
        }

        public <U> ClosingFuture<U> callAsync(AsyncClosingFunction4<V1, V2, V3, V4, U> asyncClosingFunction4, Executor executor) {
            return (ClosingFuture<V>) callAsync(new b(asyncClosingFunction4), executor);
        }

        public Combiner4(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4) {
            super(true, ImmutableList.of((ClosingFuture<V4>) closingFuture, (ClosingFuture<V4>) closingFuture2, (ClosingFuture<V4>) closingFuture3, closingFuture4), null);
            this.d = closingFuture;
            this.e = closingFuture2;
            this.f = closingFuture3;
            this.g = closingFuture4;
        }
    }

    /* loaded from: classes10.dex */
    public static final class Combiner5<V1, V2, V3, V4, V5> extends Combiner {
        public final ClosingFuture<V1> d;
        public final ClosingFuture<V2> e;
        public final ClosingFuture<V3> f;
        public final ClosingFuture<V4> g;
        public final ClosingFuture<V5> h;

        /* loaded from: classes10.dex */
        public interface AsyncClosingFunction5<V1, V2, V3, V4, V5, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3, @NullableDecl V4 v4, @NullableDecl V5 v5) throws Exception;
        }

        /* loaded from: classes10.dex */
        public interface ClosingFunction5<V1, V2, V3, V4, V5, U> {
            @NullableDecl
            U apply(DeferredCloser deferredCloser, @NullableDecl V1 v1, @NullableDecl V2 v2, @NullableDecl V3 v3, @NullableDecl V4 v4, @NullableDecl V5 v5) throws Exception;
        }

        /* JADX INFO: Add missing generic type declarations: [U] */
        /* loaded from: classes10.dex */
        public class a<U> implements Combiner.CombiningCallable<U> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ ClosingFunction5 f10769a;

            public a(ClosingFunction5 closingFunction5) {
                this.f10769a = closingFunction5;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.CombiningCallable
            @NullableDecl
            public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                return (U) this.f10769a.apply(deferredCloser, peeker.getDone(Combiner5.this.d), peeker.getDone(Combiner5.this.e), peeker.getDone(Combiner5.this.f), peeker.getDone(Combiner5.this.g), peeker.getDone(Combiner5.this.h));
            }

            public String toString() {
                return this.f10769a.toString();
            }
        }

        /* JADX INFO: Add missing generic type declarations: [U] */
        /* loaded from: classes10.dex */
        public class b<U> implements Combiner.AsyncCombiningCallable<U> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AsyncClosingFunction5 f10770a;

            public b(AsyncClosingFunction5 asyncClosingFunction5) {
                this.f10770a = asyncClosingFunction5;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.ClosingFuture.Combiner.AsyncCombiningCallable
            public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                return this.f10770a.apply(deferredCloser, peeker.getDone(Combiner5.this.d), peeker.getDone(Combiner5.this.e), peeker.getDone(Combiner5.this.f), peeker.getDone(Combiner5.this.g), peeker.getDone(Combiner5.this.h));
            }

            public String toString() {
                return this.f10770a.toString();
            }
        }

        public /* synthetic */ Combiner5(ClosingFuture closingFuture, ClosingFuture closingFuture2, ClosingFuture closingFuture3, ClosingFuture closingFuture4, ClosingFuture closingFuture5, d dVar) {
            this(closingFuture, closingFuture2, closingFuture3, closingFuture4, closingFuture5);
        }

        public <U> ClosingFuture<U> call(ClosingFunction5<V1, V2, V3, V4, V5, U> closingFunction5, Executor executor) {
            return (ClosingFuture<V>) call(new a(closingFunction5), executor);
        }

        public <U> ClosingFuture<U> callAsync(AsyncClosingFunction5<V1, V2, V3, V4, V5, U> asyncClosingFunction5, Executor executor) {
            return (ClosingFuture<V>) callAsync(new b(asyncClosingFunction5), executor);
        }

        public Combiner5(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4, ClosingFuture<V5> closingFuture5) {
            super(true, ImmutableList.of((ClosingFuture<V5>) closingFuture, (ClosingFuture<V5>) closingFuture2, (ClosingFuture<V5>) closingFuture3, (ClosingFuture<V5>) closingFuture4, closingFuture5), null);
            this.d = closingFuture;
            this.e = closingFuture2;
            this.f = closingFuture3;
            this.g = closingFuture4;
            this.h = closingFuture5;
        }
    }

    /* loaded from: classes10.dex */
    public static final class DeferredCloser {
        @RetainedWith

        /* renamed from: a  reason: collision with root package name */
        public final m f10771a;

        public DeferredCloser(m mVar) {
            this.f10771a = mVar;
        }

        @CanIgnoreReturnValue
        @NullableDecl
        public <C extends Closeable> C eventuallyClose(@NullableDecl C c, Executor executor) {
            Preconditions.checkNotNull(executor);
            if (c != null) {
                this.f10771a.add(c, executor);
            }
            return c;
        }
    }

    /* loaded from: classes10.dex */
    public static final class Peeker {

        /* renamed from: a  reason: collision with root package name */
        public final ImmutableList<ClosingFuture<?>> f10772a;
        public volatile boolean b;

        public /* synthetic */ Peeker(ImmutableList immutableList, d dVar) {
            this(immutableList);
        }

        @NullableDecl
        public final <V> V c(Combiner.CombiningCallable<V> combiningCallable, m mVar) throws Exception {
            this.b = true;
            m mVar2 = new m(null);
            try {
                return combiningCallable.call(mVar2.closer, this);
            } finally {
                mVar.add(mVar2, MoreExecutors.directExecutor());
                this.b = false;
            }
        }

        public final <V> FluentFuture<V> d(Combiner.AsyncCombiningCallable<V> asyncCombiningCallable, m mVar) throws Exception {
            this.b = true;
            m mVar2 = new m(null);
            try {
                ClosingFuture<V> call = asyncCombiningCallable.call(mVar2.closer, this);
                call.i(mVar);
                return call.c;
            } finally {
                mVar.add(mVar2, MoreExecutors.directExecutor());
                this.b = false;
            }
        }

        @NullableDecl
        public final <D> D getDone(ClosingFuture<D> closingFuture) throws ExecutionException {
            Preconditions.checkState(this.b);
            Preconditions.checkArgument(this.f10772a.contains(closingFuture));
            return (D) Futures.getDone(closingFuture.c);
        }

        public Peeker(ImmutableList<ClosingFuture<?>> immutableList) {
            this.f10772a = (ImmutableList) Preconditions.checkNotNull(immutableList);
        }
    }

    /* loaded from: classes10.dex */
    public static final class ValueAndCloser<V> {

        /* renamed from: a  reason: collision with root package name */
        public final ClosingFuture<? extends V> f10773a;

        public ValueAndCloser(ClosingFuture<? extends V> closingFuture) {
            this.f10773a = (ClosingFuture) Preconditions.checkNotNull(closingFuture);
        }

        public void closeAsync() {
            this.f10773a.m();
        }

        @NullableDecl
        public V get() throws ExecutionException {
            return (V) Futures.getDone(this.f10773a.c);
        }
    }

    /* loaded from: classes10.dex */
    public interface ValueAndCloserConsumer<V> {
        void accept(ValueAndCloser<V> valueAndCloser);
    }

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ ValueAndCloserConsumer h;

        public a(ValueAndCloserConsumer valueAndCloserConsumer) {
            this.h = valueAndCloserConsumer;
        }

        @Override // java.lang.Runnable
        public void run() {
            ClosingFuture.q(this.h, ClosingFuture.this);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public final /* synthetic */ Closeable h;

        public b(Closeable closeable) {
            this.h = closeable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.h.close();
            } catch (IOException | RuntimeException e) {
                ClosingFuture.d.log(Level.WARNING, "thrown by close()", e);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10774a;

        static {
            int[] iArr = new int[n.values().length];
            f10774a = iArr;
            try {
                iArr[n.SUBSUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10774a[n.WILL_CREATE_VALUE_AND_CLOSER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10774a[n.WILL_CLOSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10774a[n.CLOSING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10774a[n.CLOSED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10774a[n.OPEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public class d implements FutureCallback<Closeable> {
        public final /* synthetic */ Executor b;

        public d(Executor executor) {
            this.b = executor;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        /* renamed from: a */
        public void onSuccess(@NullableDecl Closeable closeable) {
            ClosingFuture.this.b.closer.eventuallyClose(closeable, this.b);
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
        }
    }

    /* loaded from: classes10.dex */
    public class e implements Callable<V> {
        public final /* synthetic */ ClosingCallable h;

        public e(ClosingCallable closingCallable) {
            this.h = closingCallable;
        }

        @Override // java.util.concurrent.Callable
        public V call() throws Exception {
            return (V) this.h.call(ClosingFuture.this.b.closer);
        }

        public String toString() {
            return this.h.toString();
        }
    }

    /* loaded from: classes10.dex */
    public class f implements AsyncCallable<V> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AsyncClosingCallable f10776a;

        public f(AsyncClosingCallable asyncClosingCallable) {
            this.f10776a = asyncClosingCallable;
        }

        @Override // com.google.common.util.concurrent.AsyncCallable
        public ListenableFuture<V> call() throws Exception {
            m mVar = new m(null);
            try {
                ClosingFuture<V> call = this.f10776a.call(mVar.closer);
                call.i(ClosingFuture.this.b);
                return call.c;
            } finally {
                ClosingFuture.this.b.add(mVar, MoreExecutors.directExecutor());
            }
        }

        public String toString() {
            return this.f10776a.toString();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [U] */
    /* loaded from: classes10.dex */
    public class g<U> implements AsyncFunction<V, U> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ClosingFunction f10777a;

        public g(ClosingFunction closingFunction) {
            this.f10777a = closingFunction;
        }

        @Override // com.google.common.util.concurrent.AsyncFunction
        public ListenableFuture<U> apply(V v) throws Exception {
            return ClosingFuture.this.b.applyClosingFunction(this.f10777a, v);
        }

        public String toString() {
            return this.f10777a.toString();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [U] */
    /* loaded from: classes10.dex */
    public class h<U> implements AsyncFunction<V, U> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AsyncClosingFunction f10778a;

        public h(AsyncClosingFunction asyncClosingFunction) {
            this.f10778a = asyncClosingFunction;
        }

        @Override // com.google.common.util.concurrent.AsyncFunction
        public ListenableFuture<U> apply(V v) throws Exception {
            return ClosingFuture.this.b.applyAsyncClosingFunction(this.f10778a, v);
        }

        public String toString() {
            return this.f10778a.toString();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [U] */
    /* loaded from: classes10.dex */
    public class i<U> implements AsyncClosingFunction<V, U> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AsyncFunction f10779a;

        public i(AsyncFunction asyncFunction) {
            this.f10779a = asyncFunction;
        }

        @Override // com.google.common.util.concurrent.ClosingFuture.AsyncClosingFunction
        public ClosingFuture<U> apply(DeferredCloser deferredCloser, V v) throws Exception {
            return ClosingFuture.from(this.f10779a.apply(v));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [W, X] */
    /* loaded from: classes10.dex */
    public class j<W, X> implements AsyncFunction<X, W> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ClosingFunction f10780a;

        public j(ClosingFunction closingFunction) {
            this.f10780a = closingFunction;
        }

        /* JADX WARN: Incorrect types in method signature: (TX;)Lcom/google/common/util/concurrent/ListenableFuture<TW;>; */
        @Override // com.google.common.util.concurrent.AsyncFunction
        /* renamed from: a */
        public ListenableFuture apply(Throwable th) throws Exception {
            return ClosingFuture.this.b.applyClosingFunction(this.f10780a, th);
        }

        public String toString() {
            return this.f10780a.toString();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [W, X] */
    /* loaded from: classes10.dex */
    public class k<W, X> implements AsyncFunction<X, W> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AsyncClosingFunction f10781a;

        public k(AsyncClosingFunction asyncClosingFunction) {
            this.f10781a = asyncClosingFunction;
        }

        /* JADX WARN: Incorrect types in method signature: (TX;)Lcom/google/common/util/concurrent/ListenableFuture<TW;>; */
        @Override // com.google.common.util.concurrent.AsyncFunction
        /* renamed from: a */
        public ListenableFuture apply(Throwable th) throws Exception {
            return ClosingFuture.this.b.applyAsyncClosingFunction(this.f10781a, th);
        }

        public String toString() {
            return this.f10781a.toString();
        }
    }

    /* loaded from: classes10.dex */
    public class l implements Runnable {
        public l() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ClosingFuture closingFuture = ClosingFuture.this;
            n nVar = n.WILL_CLOSE;
            n nVar2 = n.CLOSING;
            closingFuture.l(nVar, nVar2);
            ClosingFuture.this.m();
            ClosingFuture.this.l(nVar2, n.CLOSED);
        }
    }

    /* loaded from: classes10.dex */
    public enum n {
        OPEN,
        SUBSUMED,
        WILL_CLOSE,
        CLOSING,
        CLOSED,
        WILL_CREATE_VALUE_AND_CLOSER
    }

    public /* synthetic */ ClosingFuture(ListenableFuture listenableFuture, d dVar) {
        this(listenableFuture);
    }

    @Deprecated
    public static <C extends Closeable> ClosingFuture<C> eventuallyClosing(ListenableFuture<C> listenableFuture, Executor executor) {
        Preconditions.checkNotNull(executor);
        ClosingFuture<C> closingFuture = new ClosingFuture<>(Futures.nonCancellationPropagating(listenableFuture));
        Futures.addCallback(listenableFuture, new d(executor), MoreExecutors.directExecutor());
        return closingFuture;
    }

    public static <V> ClosingFuture<V> from(ListenableFuture<V> listenableFuture) {
        return new ClosingFuture<>(listenableFuture);
    }

    public static void n(Closeable closeable, Executor executor) {
        if (closeable == null) {
            return;
        }
        try {
            executor.execute(new b(closeable));
        } catch (RejectedExecutionException e2) {
            Logger logger = d;
            Level level = Level.WARNING;
            if (logger.isLoggable(level)) {
                logger.log(level, String.format("while submitting close to %s; will close inline", executor), (Throwable) e2);
            }
            n(closeable, MoreExecutors.directExecutor());
        }
    }

    public static <C, V extends C> void q(ValueAndCloserConsumer<C> valueAndCloserConsumer, ClosingFuture<V> closingFuture) {
        valueAndCloserConsumer.accept(new ValueAndCloser<>(closingFuture));
    }

    public static <V> ClosingFuture<V> submit(ClosingCallable<V> closingCallable, Executor executor) {
        return new ClosingFuture<>(closingCallable, executor);
    }

    public static <V> ClosingFuture<V> submitAsync(AsyncClosingCallable<V> asyncClosingCallable, Executor executor) {
        return new ClosingFuture<>(asyncClosingCallable, executor);
    }

    public static Combiner whenAllComplete(Iterable<? extends ClosingFuture<?>> iterable) {
        return new Combiner(false, iterable, null);
    }

    public static Combiner whenAllSucceed(Iterable<? extends ClosingFuture<?>> iterable) {
        return new Combiner(true, iterable, null);
    }

    public static <V, U> AsyncClosingFunction<V, U> withoutCloser(AsyncFunction<V, U> asyncFunction) {
        Preconditions.checkNotNull(asyncFunction);
        return new i(asyncFunction);
    }

    @CanIgnoreReturnValue
    public boolean cancel(boolean z) {
        d.log(Level.FINER, "cancelling {0}", this);
        boolean cancel = this.c.cancel(z);
        if (cancel) {
            m();
        }
        return cancel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <X extends Throwable> ClosingFuture<V> catching(Class<X> cls, ClosingFunction<? super X, ? extends V> closingFunction, Executor executor) {
        return k(cls, closingFunction, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <X extends Throwable> ClosingFuture<V> catchingAsync(Class<X> cls, AsyncClosingFunction<? super X, ? extends V> asyncClosingFunction, Executor executor) {
        return j(cls, asyncClosingFunction, executor);
    }

    public void finalize() {
        if (this.f10760a.get().equals(n.OPEN)) {
            d.log(Level.SEVERE, "Uh oh! An open ClosingFuture has leaked and will close: {0}", this);
            finishToFuture();
        }
    }

    public FluentFuture<V> finishToFuture() {
        if (o(n.OPEN, n.WILL_CLOSE)) {
            d.log(Level.FINER, "will close {0}", this);
            this.c.addListener(new l(), MoreExecutors.directExecutor());
        } else {
            switch (c.f10774a[this.f10760a.get().ordinal()]) {
                case 1:
                    throw new IllegalStateException("Cannot call finishToFuture() after deriving another step");
                case 2:
                    throw new IllegalStateException("Cannot call finishToFuture() after calling finishToValueAndCloser()");
                case 3:
                case 4:
                case 5:
                    throw new IllegalStateException("Cannot call finishToFuture() twice");
                case 6:
                    throw new AssertionError();
            }
        }
        return this.c;
    }

    public void finishToValueAndCloser(ValueAndCloserConsumer<? super V> valueAndCloserConsumer, Executor executor) {
        Preconditions.checkNotNull(valueAndCloserConsumer);
        if (!o(n.OPEN, n.WILL_CREATE_VALUE_AND_CLOSER)) {
            int i2 = c.f10774a[this.f10760a.get().ordinal()];
            if (i2 == 1) {
                throw new IllegalStateException("Cannot call finishToValueAndCloser() after deriving another step");
            }
            if (i2 == 2) {
                throw new IllegalStateException("Cannot call finishToValueAndCloser() twice");
            }
            if (i2 != 3 && i2 != 4 && i2 != 5) {
                throw new AssertionError(this.f10760a);
            }
            throw new IllegalStateException("Cannot call finishToValueAndCloser() after calling finishToFuture()");
        }
        this.c.addListener(new a(valueAndCloserConsumer), executor);
    }

    public final void i(m mVar) {
        l(n.OPEN, n.SUBSUMED);
        mVar.add(this.b, MoreExecutors.directExecutor());
    }

    public final <X extends Throwable, W extends V> ClosingFuture<V> j(Class<X> cls, AsyncClosingFunction<? super X, W> asyncClosingFunction, Executor executor) {
        Preconditions.checkNotNull(asyncClosingFunction);
        return (ClosingFuture<V>) p((FluentFuture<V>) this.c.catchingAsync(cls, new k(asyncClosingFunction), executor));
    }

    public final <X extends Throwable, W extends V> ClosingFuture<V> k(Class<X> cls, ClosingFunction<? super X, W> closingFunction, Executor executor) {
        Preconditions.checkNotNull(closingFunction);
        return (ClosingFuture<V>) p((FluentFuture<V>) this.c.catchingAsync(cls, new j(closingFunction), executor));
    }

    public final void l(n nVar, n nVar2) {
        Preconditions.checkState(o(nVar, nVar2), "Expected state to be %s, but it was %s", nVar, nVar2);
    }

    public final void m() {
        d.log(Level.FINER, "closing {0}", this);
        this.b.close();
    }

    public final boolean o(n nVar, n nVar2) {
        return this.f10760a.compareAndSet(nVar, nVar2);
    }

    public final <U> ClosingFuture<U> p(FluentFuture<U> fluentFuture) {
        ClosingFuture<U> closingFuture = new ClosingFuture<>(fluentFuture);
        i(closingFuture.b);
        return closingFuture;
    }

    public ListenableFuture<?> statusFuture() {
        return Futures.nonCancellationPropagating(this.c.transform(Functions.constant(null), MoreExecutors.directExecutor()));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("state", this.f10760a.get()).addValue(this.c).toString();
    }

    public <U> ClosingFuture<U> transform(ClosingFunction<? super V, U> closingFunction, Executor executor) {
        Preconditions.checkNotNull(closingFunction);
        return p(this.c.transformAsync(new g(closingFunction), executor));
    }

    public <U> ClosingFuture<U> transformAsync(AsyncClosingFunction<? super V, U> asyncClosingFunction, Executor executor) {
        Preconditions.checkNotNull(asyncClosingFunction);
        return p(this.c.transformAsync(new h(asyncClosingFunction), executor));
    }

    /* loaded from: classes10.dex */
    public static final class m extends IdentityHashMap<Closeable, Executor> implements Closeable {
        private volatile boolean closed;
        private final DeferredCloser closer;
        private volatile CountDownLatch whenClosed;

        private m() {
            this.closer = new DeferredCloser(this);
        }

        public void add(@NullableDecl Closeable closeable, Executor executor) {
            Preconditions.checkNotNull(executor);
            if (closeable == null) {
                return;
            }
            synchronized (this) {
                if (this.closed) {
                    ClosingFuture.n(closeable, executor);
                } else {
                    put(closeable, executor);
                }
            }
        }

        public <V, U> FluentFuture<U> applyAsyncClosingFunction(AsyncClosingFunction<V, U> asyncClosingFunction, V v) throws Exception {
            m mVar = new m();
            try {
                ClosingFuture<U> apply = asyncClosingFunction.apply(mVar.closer, v);
                apply.i(mVar);
                return apply.c;
            } finally {
                add(mVar, MoreExecutors.directExecutor());
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public <V, U> ListenableFuture<U> applyClosingFunction(ClosingFunction<? super V, U> closingFunction, V v) throws Exception {
            m mVar = new m();
            try {
                return Futures.immediateFuture(closingFunction.apply(mVar.closer, v));
            } finally {
                add(mVar, MoreExecutors.directExecutor());
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            synchronized (this) {
                if (this.closed) {
                    return;
                }
                this.closed = true;
                for (Map.Entry<Closeable, Executor> entry : entrySet()) {
                    ClosingFuture.n(entry.getKey(), entry.getValue());
                }
                clear();
                if (this.whenClosed != null) {
                    this.whenClosed.countDown();
                }
            }
        }

        public CountDownLatch whenClosedCountDown() {
            if (this.closed) {
                return new CountDownLatch(0);
            }
            synchronized (this) {
                if (this.closed) {
                    return new CountDownLatch(0);
                }
                Preconditions.checkState(this.whenClosed == null);
                CountDownLatch countDownLatch = new CountDownLatch(1);
                this.whenClosed = countDownLatch;
                return countDownLatch;
            }
        }

        public /* synthetic */ m(d dVar) {
            this();
        }
    }

    public ClosingFuture(ListenableFuture<V> listenableFuture) {
        this.f10760a = new AtomicReference<>(n.OPEN);
        this.b = new m(null);
        this.c = FluentFuture.from(listenableFuture);
    }

    public static Combiner whenAllComplete(ClosingFuture<?> closingFuture, ClosingFuture<?>... closingFutureArr) {
        return whenAllComplete(Lists.asList(closingFuture, closingFutureArr));
    }

    public static <V1, V2> Combiner2<V1, V2> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2) {
        return new Combiner2<>(closingFuture, closingFuture2, null);
    }

    public static <V1, V2, V3> Combiner3<V1, V2, V3> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3) {
        return new Combiner3<>(closingFuture, closingFuture2, closingFuture3, null);
    }

    public static <V1, V2, V3, V4> Combiner4<V1, V2, V3, V4> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4) {
        return new Combiner4<>(closingFuture, closingFuture2, closingFuture3, closingFuture4, null);
    }

    public static <V1, V2, V3, V4, V5> Combiner5<V1, V2, V3, V4, V5> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4, ClosingFuture<V5> closingFuture5) {
        return new Combiner5<>(closingFuture, closingFuture2, closingFuture3, closingFuture4, closingFuture5, null);
    }

    public ClosingFuture(ClosingCallable<V> closingCallable, Executor executor) {
        this.f10760a = new AtomicReference<>(n.OPEN);
        this.b = new m(null);
        Preconditions.checkNotNull(closingCallable);
        w A = w.A(new e(closingCallable));
        executor.execute(A);
        this.c = A;
    }

    public static Combiner whenAllSucceed(ClosingFuture<?> closingFuture, ClosingFuture<?> closingFuture2, ClosingFuture<?> closingFuture3, ClosingFuture<?> closingFuture4, ClosingFuture<?> closingFuture5, ClosingFuture<?> closingFuture6, ClosingFuture<?>... closingFutureArr) {
        return whenAllSucceed(FluentIterable.of(closingFuture, closingFuture2, closingFuture3, closingFuture4, closingFuture5, closingFuture6).append(closingFutureArr));
    }

    public ClosingFuture(AsyncClosingCallable<V> asyncClosingCallable, Executor executor) {
        this.f10760a = new AtomicReference<>(n.OPEN);
        this.b = new m(null);
        Preconditions.checkNotNull(asyncClosingCallable);
        w y = w.y(new f(asyncClosingCallable));
        executor.execute(y);
        this.c = y;
    }
}
