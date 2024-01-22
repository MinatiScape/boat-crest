package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class c<InputT, OutputT> extends d<OutputT> {
    public static final Logger v = Logger.getLogger(c.class.getName());
    @NullableDecl
    public ImmutableCollection<? extends ListenableFuture<? extends InputT>> s;
    public final boolean t;
    public final boolean u;

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ ListenableFuture h;
        public final /* synthetic */ int i;

        public a(ListenableFuture listenableFuture, int i) {
            this.h = listenableFuture;
            this.i = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.h.isCancelled()) {
                    c.this.s = null;
                    c.this.cancel(false);
                } else {
                    c.this.K(this.i, this.h);
                }
            } finally {
                c.this.L(null);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public final /* synthetic */ ImmutableCollection h;

        public b(ImmutableCollection immutableCollection) {
            this.h = immutableCollection;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.L(this.h);
        }
    }

    /* renamed from: com.google.common.util.concurrent.c$c  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public enum EnumC0529c {
        OUTPUT_FUTURE_DONE,
        ALL_INPUT_FUTURES_PROCESSED
    }

    public c(ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection, boolean z, boolean z2) {
        super(immutableCollection.size());
        this.s = (ImmutableCollection) Preconditions.checkNotNull(immutableCollection);
        this.t = z;
        this.u = z2;
    }

    public static boolean I(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    @Override // com.google.common.util.concurrent.d
    public final void B(Set<Throwable> set) {
        Preconditions.checkNotNull(set);
        if (isCancelled()) {
            return;
        }
        I(set, tryInternalFastPathGetFailure());
    }

    public abstract void J(int i, @NullableDecl InputT inputt);

    /* JADX WARN: Multi-variable type inference failed */
    public final void K(int i, Future<? extends InputT> future) {
        try {
            J(i, Futures.getDone(future));
        } catch (ExecutionException e) {
            N(e.getCause());
        } catch (Throwable th) {
            N(th);
        }
    }

    public final void L(@NullableDecl ImmutableCollection<? extends Future<? extends InputT>> immutableCollection) {
        int D = D();
        Preconditions.checkState(D >= 0, "Less than 0 remaining futures");
        if (D == 0) {
            Q(immutableCollection);
        }
    }

    public abstract void M();

    public final void N(Throwable th) {
        Preconditions.checkNotNull(th);
        if (this.t && !setException(th) && I(E(), th)) {
            P(th);
        } else if (th instanceof Error) {
            P(th);
        }
    }

    public final void O() {
        if (this.s.isEmpty()) {
            M();
        } else if (this.t) {
            int i = 0;
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.s.iterator();
            while (it.hasNext()) {
                ListenableFuture<? extends InputT> next = it.next();
                next.addListener(new a(next, i), MoreExecutors.directExecutor());
                i++;
            }
        } else {
            b bVar = new b(this.u ? this.s : null);
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = this.s.iterator();
            while (it2.hasNext()) {
                it2.next().addListener(bVar, MoreExecutors.directExecutor());
            }
        }
    }

    public final void P(Throwable th) {
        v.log(Level.SEVERE, th instanceof Error ? "Input Future failed with Error" : "An additional input failed after the first. Logging it after adding the first failure as a suppressed exception.", th);
    }

    public final void Q(@NullableDecl ImmutableCollection<? extends Future<? extends InputT>> immutableCollection) {
        if (immutableCollection != null) {
            int i = 0;
            UnmodifiableIterator<? extends Future<? extends InputT>> it = immutableCollection.iterator();
            while (it.hasNext()) {
                Future<? extends InputT> next = it.next();
                if (!next.isCancelled()) {
                    K(i, next);
                }
                i++;
            }
        }
        C();
        M();
        R(EnumC0529c.ALL_INPUT_FUTURES_PROCESSED);
    }

    @ForOverride
    @OverridingMethodsMustInvokeSuper
    public void R(EnumC0529c enumC0529c) {
        Preconditions.checkNotNull(enumC0529c);
        this.s = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        super.afterDone();
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.s;
        R(EnumC0529c.OUTPUT_FUTURE_DONE);
        if (isCancelled() && (immutableCollection != null)) {
            boolean wasInterrupted = wasInterrupted();
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = immutableCollection.iterator();
            while (it.hasNext()) {
                it.next().cancel(wasInterrupted);
            }
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.s;
        if (immutableCollection != null) {
            String valueOf = String.valueOf(immutableCollection);
            StringBuilder sb = new StringBuilder(valueOf.length() + 8);
            sb.append("futures=");
            sb.append(valueOf);
            return sb.toString();
        }
        return super.pendingToString();
    }
}
