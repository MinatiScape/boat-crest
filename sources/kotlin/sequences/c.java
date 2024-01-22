package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class c<T> extends SequenceScope<T> implements Iterator<T>, Continuation<Unit>, KMappedMarker {
    public int h;
    @Nullable
    public T i;
    @Nullable
    public Iterator<? extends T> j;
    @Nullable
    public Continuation<? super Unit> k;

    public final Throwable a() {
        int i = this.h;
        if (i != 4) {
            if (i != 5) {
                return new IllegalStateException("Unexpected state of the iterator: " + this.h);
            }
            return new IllegalStateException("Iterator has failed.");
        }
        return new NoSuchElementException();
    }

    public final T b() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    public final void d(@Nullable Continuation<? super Unit> continuation) {
        this.k = continuation;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (true) {
            int i = this.h;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2 || i == 3) {
                        return true;
                    }
                    if (i == 4) {
                        return false;
                    }
                    throw a();
                }
                Iterator<? extends T> it = this.j;
                Intrinsics.checkNotNull(it);
                if (it.hasNext()) {
                    this.h = 2;
                    return true;
                }
                this.j = null;
            }
            this.h = 5;
            Continuation<? super Unit> continuation = this.k;
            Intrinsics.checkNotNull(continuation);
            this.k = null;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m123constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // java.util.Iterator
    public T next() {
        int i = this.h;
        if (i == 0 || i == 1) {
            return b();
        }
        if (i == 2) {
            this.h = 1;
            Iterator<? extends T> it = this.j;
            Intrinsics.checkNotNull(it);
            return it.next();
        } else if (i == 3) {
            this.h = 0;
            T t = this.i;
            this.i = null;
            return t;
        } else {
            throw a();
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object obj) {
        ResultKt.throwOnFailure(obj);
        this.h = 4;
    }

    @Override // kotlin.sequences.SequenceScope
    @Nullable
    public Object yield(T t, @NotNull Continuation<? super Unit> continuation) {
        this.i = t;
        this.h = 3;
        this.k = continuation;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? coroutine_suspended : Unit.INSTANCE;
    }

    @Override // kotlin.sequences.SequenceScope
    @Nullable
    public Object yieldAll(@NotNull Iterator<? extends T> it, @NotNull Continuation<? super Unit> continuation) {
        if (it.hasNext()) {
            this.j = it;
            this.h = 2;
            this.k = continuation;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (coroutine_suspended == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return coroutine_suspended == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? coroutine_suspended : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
