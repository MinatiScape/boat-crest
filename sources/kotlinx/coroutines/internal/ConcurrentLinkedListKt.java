package kotlinx.coroutines.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class ConcurrentLinkedListKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Symbol f14171a = new Symbol("CLOSED");

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode] */
    @NotNull
    public static final <N extends ConcurrentLinkedListNode<N>> N close(@NotNull N n) {
        while (true) {
            Object access$getNextOrClosed = ConcurrentLinkedListNode.access$getNextOrClosed(n);
            if (access$getNextOrClosed == f14171a) {
                return n;
            }
            ?? r0 = (ConcurrentLinkedListNode) access$getNextOrClosed;
            if (r0 != 0) {
                n = r0;
            } else if (n.markAsClosed()) {
                return n;
            }
        }
    }
}
