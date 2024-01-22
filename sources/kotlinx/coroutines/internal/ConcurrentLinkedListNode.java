package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {

    /* renamed from: a */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f14172a = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
    public static final /* synthetic */ AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");
    @NotNull
    private volatile /* synthetic */ Object _next = null;
    @NotNull
    private volatile /* synthetic */ Object _prev;

    public ConcurrentLinkedListNode(@Nullable N n) {
        this._prev = n;
    }

    public final N a() {
        N prev = getPrev();
        while (prev != null && prev.getRemoved()) {
            prev = (N) prev._prev;
        }
        return prev;
    }

    public final Object b() {
        return this._next;
    }

    public final N c() {
        if (!DebugKt.getASSERTIONS_ENABLED() || (!isTail())) {
            N next = getNext();
            Intrinsics.checkNotNull(next);
            while (next.getRemoved()) {
                next = (N) next.getNext();
                Intrinsics.checkNotNull(next);
            }
            return next;
        }
        throw new AssertionError();
    }

    public final void cleanPrev() {
        b.lazySet(this, null);
    }

    @Nullable
    public final N getNext() {
        Object b2 = b();
        if (b2 == ConcurrentLinkedListKt.f14171a) {
            return null;
        }
        return (N) b2;
    }

    @Nullable
    public final N getPrev() {
        return (N) this._prev;
    }

    public abstract boolean getRemoved();

    public final boolean isTail() {
        return getNext() == null;
    }

    public final boolean markAsClosed() {
        return f14172a.compareAndSet(this, null, ConcurrentLinkedListKt.f14171a);
    }

    @Nullable
    public final N nextOrIfClosed(@NotNull Function0 function0) {
        Object b2 = b();
        if (b2 != ConcurrentLinkedListKt.f14171a) {
            return (N) b2;
        }
        function0.invoke();
        throw new KotlinNothingValueException();
    }

    public final void remove() {
        if (DebugKt.getASSERTIONS_ENABLED() && !getRemoved()) {
            throw new AssertionError();
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !(!isTail())) {
            throw new AssertionError();
        }
        while (true) {
            N a2 = a();
            N c = c();
            c._prev = a2;
            if (a2 != null) {
                a2._next = c;
            }
            if (!c.getRemoved() && (a2 == null || !a2.getRemoved())) {
                return;
            }
        }
    }

    public final boolean trySetNext(@NotNull N n) {
        return f14172a.compareAndSet(this, null, n);
    }
}
